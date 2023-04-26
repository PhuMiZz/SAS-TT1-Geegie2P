<script setup>
import { onMounted, ref } from 'vue';
import PageTemplate from '../templates/PageTemplate.vue';
import NavigationBar from '../UI/organisms/NavigationBar.vue';
import AnnouncementService from '../../lib/AnnouncementService';
import AnnouncementList from '../UI/organisms/AnnouncementList.vue';
import AnnouncementTitle from "@/components/UI/organisms/AnnouncementTitle.vue";

const announcementService = new AnnouncementService();

const allAnnouncement = ref({});

onMounted(async () => {
  allAnnouncement.value = await announcementService.getAllAnnouncement();
  console.log(allAnnouncement.value);
});
</script>

<template>
  <PageTemplate>
    <NavigationBar />
    <AnnouncementTitle/>
    <AnnouncementList :announcement-list="allAnnouncement" />
  </PageTemplate>
</template>

<style scoped></style>
