<script setup>
import { ref, watchEffect } from 'vue';
import { useRoute } from 'vue-router';
import Announcement from '../UI/organisms/Announcement.vue';
import AnnouncementService from '@/lib/AnnouncementService';
import AlertOverlay from '../UI/organisms/AlertOverlay.vue';
import OverlayTemplate from '../templates/OverlayTemplate.vue';
import LoadingPage from '../UI/organisms/LoadingPage.vue';

const { params } = useRoute();
const announcementService = new AnnouncementService();

const announcementId = params.id;
const foundAnnouncement = ref(false);
const announcementDetail = ref({});
const isLoading = ref(true);

watchEffect(async () => {
  isLoading.value = true;
  const announcements = await announcementService.getAllAnnouncement();
  const allAnnouncementId = announcements.map((e) => e.id);
  foundAnnouncement.value = allAnnouncementId.some((e) => e === announcementId);
  announcementDetail.value = await announcementService.getAnnouncementDetail(
    announcementId
  );
  if (announcementDetail.value) {
    isLoading.value = false;
  }
  isLoading.value = false;
});
</script>

<template>
  <LoadingPage v-if="isLoading" />
  <Announcement
    :announcementDetail="announcementDetail"
    v-else-if="announcementDetail && !isLoading"
  />
  <!-- <OverlayTemplate v-else :showModal="!announcementDetail">
    <AlertOverlay />
  </OverlayTemplate> -->
</template>

<style scoped></style>
