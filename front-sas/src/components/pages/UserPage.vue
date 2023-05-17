<script setup>
import { useRouter } from 'vue-router';
import { usePageStore } from '../../stores/pageStore';
import { storeToRefs } from 'pinia';
import { onBeforeMount, ref } from 'vue';
import PageTemplate from '../templates/PageTemplate.vue';
import AnnouncementTitle from '../UI/organisms/AnnouncementTitle.vue';
import LoadingPage from '../UI/organisms/LoadingPage.vue';
import AnnouncementUserTemplate from '../templates/AnnouncementUserTemplate.vue';
import AnnouncementList from '../UI/organisms/AnnouncementList.vue';
import SingleUserAnnouncement from '../UI/molecules/SingleUserAnnouncement.vue';
import PaginationTemplate from '../templates/PaginationTemplate.vue';

const pageStore = usePageStore();
const { changeCategory, refreshAnnouncement, getTotalIndex } = pageStore;
const { currentStatus, allAnnouncement, isAnnouncementEmpty, isLoading } =
  storeToRefs(pageStore);
const router = useRouter();
const emit = defineEmits(['userClick']);

const isClicked = ref(false);

const onClickDetailPageHandler = (id) => {
  isClicked.value = true;
  emit('userClick', isClicked.value);
  router.push({
    name: 'UserDetailPage',
    params: { id: id },
  });

  // currentStatus.value.isClicked = true;
};
onBeforeMount(() => {
  currentStatus.value.categoryId = 0;
});
</script>

<template>
  <LoadingPage v-if="isLoading" />
  <PageTemplate v-else>
    <AnnouncementTitle :isUserPage="true" @changeCategory="changeCategory" />
    <div
      v-if="isAnnouncementEmpty"
      class="text-[#737373] w-full h-96 flex items-center justify-center text-2xl"
    >
      No Announcement
    </div>
    <div v-else>
      <AnnouncementUserTemplate :header="true" class="hidden lg:flex">
        <template #announcementNo>No.</template>
        <template #title>Title</template>
        <template #closeDate v-if="!currentStatus.isActive"
          >Close Date</template
        >
        <template #category>Category</template>
      </AnnouncementUserTemplate>
    </div>
    <div class="h-full">
      <AnnouncementList
        :announcementList="allAnnouncement.content"
        v-slot="announcement"
      >
        <SingleUserAnnouncement
          @announcementId="onClickDetailPageHandler"
          :isActive="!currentStatus.isActive"
          :index="getTotalIndex(announcement.index)"
          :announcementItem="announcement.announcementItem"
          class="cursor-pointer transition duration-300 ease-in-out hover:bg-slate-200 hover:shadow-lg"
        />
      </AnnouncementList>
    </div>

    <PaginationTemplate
      v-if="!isAnnouncementEmpty && currentStatus.showPaginate"
      @select-page="refreshAnnouncement"
      :total-pages="allAnnouncement.totalPages"
      :offset="allAnnouncement.page"
      :page-size="allAnnouncement.size"
    />
  </PageTemplate>
</template>

<style scoped></style>
