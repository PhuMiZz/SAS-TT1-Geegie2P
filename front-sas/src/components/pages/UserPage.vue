<script setup>
import { ref, watchEffect, computed, reactive } from "vue";
import { useRouter } from "vue-router";
import PageTemplate from "../templates/PageTemplate.vue";
import AnnouncementTitle from "../UI/organisms/AnnouncementTitle.vue";
import AnnouncementService from "@/lib/AnnouncementService";
import LoadingPage from "../UI/organisms/LoadingPage.vue";
import AnnouncementUserTemplate from "../templates/AnnouncementUserTemplate.vue";
import AnnouncementList from "../UI/organisms/AnnouncementList.vue";
import SingleUserAnnouncement from "../UI/molecules/SingleUserAnnouncement.vue";
import PaginationTemplate from "../templates/PaginationTemplate.vue";

const announcementService = new AnnouncementService();
const router = useRouter();

const allAnnouncement = ref([]);
const isAnnouncementEmpty = ref(false);
const isLoading = ref(true);

//status
const currentStatus = reactive({
  isActive: true,
  statusMode: "active",
  categoryId: 0,
  pageNo: 0,
});

const getTotalIndex = (index) => {
  return allAnnouncement.value.page * allAnnouncement.value.size + index;
};
const refreshAnnouncement = async (pageNo) => {
  currentStatus.pageNo = pageNo;
  allAnnouncement.value = await announcementService.getPagesAllAnnouncement(
    currentStatus.statusMode,
    currentStatus.categoryId,
    currentStatus.pageNo
  );
};
watchEffect(async () => {
  isLoading.value = true;
  // allAnnouncement.value = await announcementService.getAllAnnouncement();
  allAnnouncement.value = await announcementService.getPagesAllAnnouncement(
    currentStatus.statusMode
  );
  isAnnouncementEmpty.value =
    Object.keys(allAnnouncement.value.content).length === 0;
  isLoading.value = false;
});

const toggleStatusAnnouncement = () => {
  currentStatus.isActive = !currentStatus.isActive;
  if (currentStatus.isActive) {
    currentStatus.statusMode = "active";
  } else {
    currentStatus.statusMode = "close";
  }
};

const changeCategory = async (id) => {
  currentStatus.categoryId = id;
  allAnnouncement.value = await announcementService.getPagesAllAnnouncement(
    currentStatus.statusMode,
    currentStatus.categoryId
  );
  isAnnouncementEmpty.value =
    Object.keys(allAnnouncement.value.content).length === 0;
};
</script>

<template>
  <LoadingPage v-if="isLoading" />
  <PageTemplate v-else>
    <AnnouncementTitle
      :isUserPage="true"
      @toggleStatusAnnouncement="toggleStatusAnnouncement"
      :isActive="currentStatus.isActive"
      @changeCategory="changeCategory"
    />
    <div
      v-if="isAnnouncementEmpty"
      class="text-[#737373] w-full h-96 flex items-center justify-center text-2xl"
    >
      No Announcement
    </div>
    <div v-else>
      <AnnouncementUserTemplate :header="true" class="hidden xl:flex">
        <template #announcementNo>No.</template>
        <template #title>Title</template>
        <template #closeDate v-if="currentStatus.isActive">Close Date</template>
        <template #category>Category</template>
      </AnnouncementUserTemplate>
    </div>
    <AnnouncementList
      :announcementList="allAnnouncement.content"
      v-slot="announcement"
    >
      <SingleUserAnnouncement
        @announcementId="
          (id) => router.push({ name: 'UserDetailPage', params: { id: id } })
        "
        :isActive="currentStatus.isActive"
        :index="getTotalIndex(announcement.index)"
        :announcementItem="announcement.announcementItem"
        class="cursor-pointer transition duration-300 ease-in-out hover:bg-slate-200 hover:shadow-lg"
      />
    </AnnouncementList>
    <PaginationTemplate
      v-if="!isAnnouncementEmpty"
      @select-page="refreshAnnouncement"
      :total-pages="allAnnouncement.totalPages"
      :offset="allAnnouncement.page"
      :page-size="allAnnouncement.size"
    />
  </PageTemplate>
</template>

<style scoped></style>
