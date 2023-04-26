<script setup>
import { onMounted, ref } from 'vue';
import PageTemplate from '../templates/PageTemplate.vue';
import NavigationBar from '../UI/organisms/NavigationBar.vue';
import AnnouncementService from '../../lib/AnnouncementService';

const announcementService = new AnnouncementService();

const allAnnounce = ref({});

onMounted(async () => {
  allAnnounce.value = await announcementService.getAllAnnouncement();
  console.log(allAnnounce.value);
});
</script>

<template>
  <PageTemplate>
    <NavigationBar />
  </PageTemplate>
</template>

<style scoped></style>
