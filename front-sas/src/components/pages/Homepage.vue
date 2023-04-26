<script setup>
import { onMounted, ref } from "vue";
import PageTemplate from "../templates/PageTemplate.vue";
import NavigationBar from "../UI/organisms/NavigationBar.vue";
import AnnouncementService from "../../lib/AnnouncementService";
import AnnouncementList from "../UI/organisms/AnnouncementList.vue";

const announcementService = new AnnouncementService();

const allAnnouncement = ref({});

onMounted(async () => {
  allAnnouncement.value = await announcementService.getAllAnnouncement();
  console.log(allAnnouncement.value);
});
const announcementDetail = ref();
const showModal = ref();
console.log(announcementDetail);
console.log(showModal);
</script>

<template>
  <PageTemplate>
    <NavigationBar />
    <AnnouncementList
      :announcement-list="allAnnouncement"
      @announcement-detail="announcementDetail"
      @showModal="showModal"
    />
  </PageTemplate>
</template>

<style scoped></style>
