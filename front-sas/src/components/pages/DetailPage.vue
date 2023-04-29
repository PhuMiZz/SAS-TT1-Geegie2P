<script setup>
import { ref, watchEffect } from "vue";
import { useRoute } from "vue-router";
import Announcement from "../UI/organisms/Announcement.vue";
import AnnouncementService from "@/lib/AnnouncementService";
import AlertOverlay from "../UI/organisms/AlertOverlay.vue";

const { params } = useRoute();
const announcementService = new AnnouncementService();

const announcementId = Number(params.id);
const foundAnnouncement = ref(false);

watchEffect(async () => {
  const announcements = await announcementService.getAllAnnouncement();
  const allAnnouncementId = announcements.map((e) => e.id);
  foundAnnouncement.value = allAnnouncementId.some((e) => e === announcementId);
});
</script>

<template>
  <Announcement :announcementId="announcementId" v-if="foundAnnouncement" />
  <AlertOverlay v-else />
</template>

<style scoped></style>
