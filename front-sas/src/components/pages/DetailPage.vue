<script setup>
import { ref, watchEffect } from "vue";
import { useRoute } from "vue-router";
import Announcement from "../UI/organisms/Announcement.vue";
import AnnouncementService from "@/lib/AnnouncementService";
import AlertOverlay from "../UI/organisms/AlertOverlay.vue";
import OverlayTemplate from "../templates/OverlayTemplate.vue";

const { params } = useRoute();
const announcementService = new AnnouncementService();

const announcementId = Number(params.id);
const announcementDetail = ref([]);

watchEffect(async () => {
  announcementDetail.value = await announcementService.getAnnouncementDetail(
    announcementId
  );
});
</script>

<template>
  <Announcement :announcementDetail="announcementDetail" />
</template>

<style scoped></style>
