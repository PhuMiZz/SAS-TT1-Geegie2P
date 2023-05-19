<script setup>
import { ref, watchEffect } from 'vue';
import PageTemplate from '../templates/PageTemplate.vue';
import AnnouncementService from '@/lib/announcementService';
import AnnouncementList from '../UI/organisms/AnnouncementList.vue';
import AnnouncementTitle from '@/components/UI/organisms/AnnouncementTitle.vue';
import AnnouncementTemplate from '../templates/AnnouncementTemplate.vue';
import SingleAnnouncement from '../UI/molecules/SingleAnnouncement.vue';
import LoadingPage from '../UI/organisms/LoadingPage.vue';

const announcementService = new AnnouncementService();

const allAnnouncement = ref([]);
const isAnnouncementEmpty = ref(false);
const isLoading = ref(true);

const filterDeletedData = (announcementDeletedId) => {
  allAnnouncement.value = allAnnouncement.value.filter(
    (announcement) => announcement.id !== announcementDeletedId
  );
  // console.log(announcementDeletedId);
};

watchEffect(async () => {
  isLoading.value = true;
  allAnnouncement.value = await announcementService.getAllAnnouncement();
  isAnnouncementEmpty.value = Object.keys(allAnnouncement.value).length === 0;
  isLoading.value = false;
});

const changeCategory = async (id) => {
  allAnnouncement.value =
    await announcementService.getAllAnnouncementByCategory(id);
  isAnnouncementEmpty.value = Object.keys(allAnnouncement.value).length === 0;
};
</script>

<template>
  <LoadingPage v-if="isLoading" />
  <PageTemplate v-else>
    <AnnouncementTitle @changeCategory="changeCategory" />
    <div
      v-if="isAnnouncementEmpty"
      class="text-[#737373] w-full h-96 flex items-center justify-center text-2xl"
    >
      No Announcement
    </div>
    <div v-else>
      <AnnouncementTemplate :header="true" class="hidden lg:flex">
        <template #title>Title</template>
        <template #category>Category</template>
        <template #publishDate>Publish Date</template>
        <template #closeDate>Close Date</template>
        <template #display>Display</template>
        <template #view>#Views</template>
        <template #action>Action</template>
      </AnnouncementTemplate>
      <AnnouncementList
        :announcement-list="allAnnouncement"
        v-slot="announcement"
      >
        <SingleAnnouncement
          @refresh-data="filterDeletedData"
          :index="announcement.index"
          :announcement-item="announcement.announcementItem"
        />
      </AnnouncementList>
    </div>
  </PageTemplate>
</template>

<style scoped></style>
