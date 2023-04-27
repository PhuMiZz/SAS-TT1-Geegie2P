<script setup>
import { onMounted, ref, watch, watchEffect } from "vue";
import PageTemplate from "../templates/PageTemplate.vue";
import NavigationBar from "../UI/organisms/NavigationBar.vue";
import AnnouncementService from "../../lib/AnnouncementService";
import AnnouncementList from "../UI/organisms/AnnouncementList.vue";
import AnnouncementTitle from "@/components/UI/organisms/AnnouncementTitle.vue";
import AnnouncementTemplate from "../templates/AnnouncementTemplate.vue";

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
      <AnnouncementTemplate header="true">
        <template v-slot:title>Title</template>
        <template v-slot:category>Category</template>
        <template v-slot:publishDate>Publish Date</template>
        <template v-slot:closeDate>Close Date</template>
        <template v-slot:display>Display</template>
        <template v-slot:detail>Detail</template>
      </AnnouncementTemplate>
      <AnnouncementList :announcement-list="allAnnouncement" />
    </div>
  </PageTemplate>
</template>

<style scoped></style>
