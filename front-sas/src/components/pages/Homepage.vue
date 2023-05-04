<script setup>
import { ref, watchEffect } from "vue";
import PageTemplate from "../templates/PageTemplate.vue";
import AnnouncementService from "@/lib/AnnouncementService";
import AnnouncementList from "../UI/organisms/AnnouncementList.vue";
import AnnouncementTitle from "@/components/UI/organisms/AnnouncementTitle.vue";
import AnnouncementTemplate from "../templates/AnnouncementTemplate.vue";
import LoadingPage from "../UI/organisms/LoadingPage.vue";

const announcementService = new AnnouncementService();

const allAnnouncement = ref([]);
const isAnnouncementEmpty = ref(false);
const isLoading = ref(true);

const filterDeletedData = (announcementDeletedId) => {
  allAnnouncement.value = allAnnouncement.value.filter(
    (announcement) => announcement.id !== announcementDeletedId
  );
  // console.log(announcementDeletedId);
};

watchEffect(async () => {
  isLoading.value = true;
  allAnnouncement.value = await announcementService.getAllAnnouncement();
  isAnnouncementEmpty.value = Object.keys(allAnnouncement.value).length === 0;
  isLoading.value = false;
});
</script>

<template>
  <LoadingPage v-if="isLoading" />
  <PageTemplate v-else>
    <AnnouncementTitle />
    <div
      v-if="isAnnouncementEmpty"
      class="text-[#737373] w-full h-96 flex items-center justify-center text-2xl"
    >
      No Announcement
    </div>
    <div v-else>
      <AnnouncementTemplate :header="true" class="hidden xl:flex">
        <template #title>Title</template>
        <template #category>Category</template>
        <template #publishDate>Publish Date</template>
        <template #closeDate>Close Date</template>
        <template #display>Display</template>
        <template #action>Action</template>
      </AnnouncementTemplate>
      <AnnouncementList
        @refresh-data="filterDeletedData"
        :announcement-list="allAnnouncement"
      />
    </div>
  </PageTemplate>
</template>

<style scoped></style>
