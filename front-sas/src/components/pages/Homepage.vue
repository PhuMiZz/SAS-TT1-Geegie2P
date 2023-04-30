<script setup>
import { onMounted, ref, watch, watchEffect } from "vue";
import { useRouter, onBeforeRouteLeave } from "vue-router";
import PageTemplate from "../templates/PageTemplate.vue";
import NavigationBar from "../UI/organisms/NavigationBar.vue";
import AnnouncementService from "@/lib/AnnouncementService";
import AnnouncementList from "../UI/organisms/AnnouncementList.vue";
import AnnouncementTitle from "@/components/UI/organisms/AnnouncementTitle.vue";
import AnnouncementTemplate from "../templates/AnnouncementTemplate.vue";
import OverlayTemplate from "../templates/OverlayTemplate.vue";
import AlertOverlay from "../UI/organisms/AlertOverlay.vue";

const announcementService = new AnnouncementService();
const router = useRouter();

const allAnnouncement = ref([]);
const isAnnouncementEmpty = ref(false);
const foundAnnouncement = ref(true);

watchEffect(async () => {
  allAnnouncement.value = await announcementService.getAllAnnouncement();
  if (Object.keys(allAnnouncement.value).length === 0) {
    isAnnouncementEmpty.value = true;
  } else {
    isAnnouncementEmpty.value = false;
  }
});

const getDetail = (id) => {
  router.push({ name: "DetailPage", params: { id: to.params.id } });
};

onBeforeRouteLeave((to, from) => {
  const allAnnouncementId = allAnnouncement.value.map((e) => e.id);
  foundAnnouncement.value = allAnnouncementId.some(
    (e) => e === Number(to.params.id)
  );
  if (foundAnnouncement.value) {
    console.log(true);
  } else {
    console.log(false);
  }
});
</script>

<template>
  <PageTemplate>
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
        <template #detail>Detail</template>
      </AnnouncementTemplate>
      <AnnouncementList
        :announcement-list="allAnnouncement"
        :onClickDetail="getDetail"
      />
    </div>
  </PageTemplate>
  <OverlayTemplate :showModal="!foundAnnouncement">
    <AlertOverlay />
  </OverlayTemplate>
</template>

<style scoped></style>
