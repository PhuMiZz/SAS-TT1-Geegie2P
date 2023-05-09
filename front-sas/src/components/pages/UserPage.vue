<script setup>
import { ref, watchEffect } from 'vue';
import { useRouter } from 'vue-router';
import PageTemplate from '../templates/PageTemplate.vue';
import AnnouncementTitle from '../UI/organisms/AnnouncementTitle.vue';
import AnnouncementService from '@/lib/AnnouncementService';
import LoadingPage from '../UI/organisms/LoadingPage.vue';
import AnnouncementUserTemplate from '../templates/AnnouncementUserTemplate.vue';
import AnnouncementList from '../UI/organisms/AnnouncementList.vue';
import SingleUserAnnouncement from '../UI/molecules/SingleUserAnnouncement.vue';

const announcementService = new AnnouncementService();
const router = useRouter();

const allAnnouncement = ref([]);
const isAnnouncementEmpty = ref(false);
const isLoading = ref(true);

watchEffect(async () => {
  isLoading.value = true;
  allAnnouncement.value = await announcementService.getAllAnnouncement();
  isAnnouncementEmpty.value = Object.keys(allAnnouncement.value).length === 0;
  isLoading.value = false;
  console.log(Object.keys(allAnnouncement.value).length === 0);
});
const test = (n) => {
  console.log(n);
};
</script>

<template>
  <PageTemplate>
    <LoadingPage v-if="isLoading" />
    <AnnouncementTitle :isUserPage="true" />
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
        <template #category>Category</template>
      </AnnouncementUserTemplate>
    </div>
    <AnnouncementList :announcementList="allAnnouncement" v-slot="announcement">
      <SingleUserAnnouncement
        @announcementId="
          (id) => router.push({ name: 'UserDetailPage', params: { id: id } })
        "
        :index="announcement.index"
        :announcementItem="announcement.announcementItem"
        class="cursor-pointer transition duration-300 ease-in-out hover:bg-slate-200 hover:shadow-lg"
      />
    </AnnouncementList>
  </PageTemplate>
</template>

<style scoped></style>
