<script setup>
import { onMounted, ref, watch, watchEffect } from 'vue';
import PageTemplate from '../templates/PageTemplate.vue';
import NavigationBar from '../UI/organisms/NavigationBar.vue';
import AnnouncementService from '@/lib/AnnouncementService';
import AnnouncementList from '../UI/organisms/AnnouncementList.vue';
import AnnouncementTitle from '@/components/UI/organisms/AnnouncementTitle.vue';
import AnnouncementTemplate from '../templates/AnnouncementTemplate.vue';

const announcementService = new AnnouncementService();

const allAnnouncement = ref([]);
const isAnnouncementEmpty = ref(false);

watchEffect(async () => {
  allAnnouncement.value = await announcementService.getAllAnnouncement();
  if (Object.keys(allAnnouncement.value).length === 0) {
    isAnnouncementEmpty.value = true;
  } else {
    isAnnouncementEmpty.value = false;
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
      <AnnouncementList :announcement-list="allAnnouncement" />
    </div>
  </PageTemplate>
</template>

<style scoped></style>
