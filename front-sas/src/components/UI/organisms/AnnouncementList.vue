<script setup>
import { ref } from "vue";
import SingleAnnouncement from "../molecules/SingleAnnouncement.vue";
const props = defineProps({
  announcementList: {
    type: Object,
    required: true,
  },
});
defineEmits(["announcementDetail", "showModal"]);
</script>

<template>
  <div
    v-for="announcement in announcementList"
    :key="announcement.id"
    :id="announcement.id"
  >
    <SingleAnnouncement
      :announcement-item="announcement"
      @announcement-detail="announcementDetail"
      @showModal="showModal"
    />
  </div>
</template>

<style scoped></style>
