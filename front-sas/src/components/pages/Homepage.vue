<script setup>
import { onMounted, ref, watch, watchEffect } from "vue";
import PageTemplate from "../templates/PageTemplate.vue";
import NavigationBar from "../UI/organisms/NavigationBar.vue";
import AnnouncementService from "../../lib/AnnouncementService";
import AnnouncementList from "../UI/organisms/AnnouncementList.vue";
import AnnouncementTitle from "@/components/UI/organisms/AnnouncementTitle.vue";

const announcementService = new AnnouncementService();

const allAnnouncement = ref({});
const isAnnouncementEmpty = ref(false);

watchEffect(async () => {
  allAnnouncement.value = await announcementService.getAllAnnouncement();
  console.log(allAnnouncement.value);

  if (Object.keys(allAnnouncement.value).length === 0) {
    isAnnouncementEmpty.value = true;
  } else {
    isAnnouncementEmpty.value = false;
  }
});
</script>

<template>
  <NavigationBar />
  <PageTemplate>
    <AnnouncementTitle />
    <div
      v-if="isAnnouncementEmpty"
      class="text-[#737373] w-full h-64 flex items-center justify-center"
    >
      No Announcement
    </div>
    <div v-else>
      <div class="flex text-[#737373]">
        <div class="flex w-1/5"></div>
        <div class="flex w-2/3 px-5">Title</div>
        <div class="flex w-2/4">Category</div>

        <div class="flex w-2/3">Publish Date</div>
        <div class="flex w-2/3">Close Date</div>
        <div class="flex w-1/4 justify-center">Display</div>
        <div class="flex w-1/4 justify-center">Detail</div>
      </div>
      <AnnouncementList :announcement-list="allAnnouncement" />
    </div>
  </PageTemplate>
</template>

<style scoped></style>
