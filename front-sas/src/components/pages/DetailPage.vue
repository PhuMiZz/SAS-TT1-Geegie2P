<script setup>
import { ref, watchEffect } from 'vue';
import { useRoute } from 'vue-router';
import Announcement from '../UI/organisms/Announcement.vue';
import AnnouncementService from '@/lib/AnnouncementService';
import AlertOverlay from '../UI/organisms/AlertOverlay.vue';
import OverlayTemplate from '../templates/OverlayTemplate.vue';

const { params } = useRoute();
const announcementService = new AnnouncementService();

const announcementId = Number(params.id);
const foundAnnouncement = ref(false);
const announcementDetail = ref([]);

watchEffect(async () => {
  const announcements = await announcementService.getAllAnnouncement();
  const allAnnouncementId = announcements.map((e) => e.id);
  foundAnnouncement.value = allAnnouncementId.some((e) => e === announcementId);
  announcementDetail.value = await announcementService.getAnnouncementDetail(
    announcementId
  );
});
</script>

<template>
  <Announcement :announcementDetail="announcementDetail" v-if="foundAnnouncement" />
  <OverlayTemplate v-else :showModal="!foundAnnouncement">
    <AlertOverlay />
  </OverlayTemplate>
</template>

<style scoped></style>
