<script setup>
import { useRouter } from 'vue-router';
import PageTemplate from '../templates/PageTemplate.vue';
import AnnouncementTitle from '../UI/organisms/AnnouncementTitle.vue';
import LoadingPage from '../UI/organisms/LoadingPage.vue';
import AnnouncementUserTemplate from '../templates/AnnouncementUserTemplate.vue';
import AnnouncementList from '../UI/organisms/AnnouncementList.vue';
import SingleUserAnnouncement from '../UI/molecules/SingleUserAnnouncement.vue';
import PaginationTemplate from '../templates/PaginationTemplate.vue';
import { usePageStore } from '../../stores/pageStore';
import { storeToRefs } from 'pinia';
import { onBeforeMount } from 'vue';

const pageStore = usePageStore();
const { changeCategory, refreshAnnouncement, getTotalIndex } = pageStore;
const { currentStatus, allAnnouncement, isAnnouncementEmpty, isLoading } =
  storeToRefs(pageStore);
const router = useRouter();
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
      <AnnouncementUserTemplate :header="true" class="hidden xl:flex">
        <template #announcementNo>No.</template>
        <template #title>Title</template>
        <template #closeDate v-if="!currentStatus.isActive"
          >Close Date</template
        >
        <template #category>Category</template>
      </AnnouncementUserTemplate>
    </div>
    <AnnouncementList
      :announcementList="allAnnouncement.content"
      v-slot="announcement"
    >
      <SingleUserAnnouncement
        @announcementId="
          (id) => router.push({ name: 'UserDetailPage', params: { id: id } })
        "
        :isActive="!currentStatus.isActive"
        :index="getTotalIndex(announcement.index)"
        :announcementItem="announcement.announcementItem"
        class="cursor-pointer transition duration-300 ease-in-out hover:bg-slate-200 hover:shadow-lg"
      />
    </AnnouncementList>
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
