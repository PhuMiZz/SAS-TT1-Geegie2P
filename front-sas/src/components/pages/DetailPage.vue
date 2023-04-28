<script setup>
import { useRoute } from 'vue-router';
import Announcement from '../UI/organisms/Announcement.vue';
const { params } = useRoute();
const announcementId = params.id;
</script>

<template>
  <Announcement :announcementId="announcementId" />
</template>

<style scoped></style>
