<script setup>
import TextDescription from '@/components/UI/molecules/TextDescription.vue';
import AnnouncementCard from '@/components/templates/AnnouncementCard.vue';
import AnnouncementService from '@/lib/AnnouncementService.js';
import { watchEffect, ref, reactive, computed, onMounted } from 'vue';
import PageTemplate from '@/components/templates/PageTemplate.vue';
import { useRoute } from 'vue-router';
import { getISODateTime } from '@/lib/DateTimeManagement.js';

const announcementService = new AnnouncementService();
const categories = ref([]);
const router = useRoute();
const newAnnouncementData = reactive({
  announcementTitle: '',
  announcementDescription: '',
  announcementCategory: 1,
  publishDate: '',
  publishTime: '',
  closeDate: '',
  closeTime: '',
  display: false,
});
const newAnnouncementDataJSON = computed(() =>
  JSON.stringify(newAnnouncementData, null, 2)
);

const updateInput = ref(true);
const announcement = ref({});

const submitAnnouncement = async () => {
  const newAnnouncement = {
    announcementTitle: newAnnouncementData.announcementTitle,
    announcementDescription: newAnnouncementData.announcementDescription,
    categoryId: newAnnouncementData.announcementCategory,
    publishDate:
      newAnnouncementData.publishDate || newAnnouncementData.publishTime
        ? getISODateTime(
            newAnnouncementData.publishDate,
            newAnnouncementData.publishTime
          )
        : null,
    closeDate:
      newAnnouncementData.closeDate || newAnnouncementData.closeTime
        ? getISODateTime(
            newAnnouncementData.closeDate,
            newAnnouncementData.closeTime
          )
        : null,
    announcementDisplay: newAnnouncementData.display ? 'Y' : 'N',
  };
  try {
    if (router.name === 'UpdateAnnouncement') {
      await announcementService.updateAnnouncement(
        router.params.id,
        newAnnouncement
      );
      alert('Announcement updated successfully');
      window.location = `/admin/announcement/${router.params.id}`;
    } else {
      await announcementService.createAnnouncement(newAnnouncement);
      alert('Announcement created successfully');
      window.location = '/admin/announcement';
    }
  } catch (error) {
    console.error('Error submitting announcement:', error);
    alert('Error submitting announcement, please try again');
  }
};
const fetchAnnouncement = async () => {
  if (router.name === 'UpdateAnnouncement') {
    const announcementId = router.params.id;
    announcement.value = await announcementService.getAnnouncementDetail(
      announcementId
    );
    newAnnouncementData.announcementTitle =
      announcement.value.announcementTitle;
    newAnnouncementData.announcementDescription =
      announcement.value.announcementDescription;
    newAnnouncementData.announcementCategory = categories.value.find(
      (e) => e.categoryName === announcement.value.announcementCategory
    ).id;

    if (announcement.value.publishDate) {
      const publishDateTime = new Date(announcement.value.publishDate);
      newAnnouncementData.publishDate = publishDateTime
        .toISOString()
        .split('T')[0];
      newAnnouncementData.publishTime = publishDateTime
        .toISOString()
        .split('T')[1]
        .substring(0, 5);
    }
    if (announcement.value.closeDate) {
      const closeDateTime = new Date(announcement.value.closeDate);
      newAnnouncementData.closeDate = closeDateTime.toISOString().split('T')[0];
      newAnnouncementData.closeTime = closeDateTime
        .toISOString()
        .split('T')[1]
        .substring(0, 5);
    }

    newAnnouncementData.display =
      announcement.value.announcementDisplay === 'Y';
  }
};
const checkUpdate = computed(() => {
  // for add announcement
  if (
    newAnnouncementData.announcementTitle.trim().length === 0 ||
    newAnnouncementData.announcementDescription.trim().length === 0
  ) {
    updateInput.value = true;
  } else {
    updateInput.value = false;
  }
});

watchEffect(async () => {
  categories.value = await announcementService.getAllCategory();
  await fetchAnnouncement();
  
});

onMounted(async () => {
  // await fetchAnnouncement();
});
</script>

<template>
  <!-- <pre>{{ newAnnouncementDataJSON }}</pre> -->

  <PageTemplate class="my-10">
    <AnnouncementCard :viewComponent="false">
      <template #title>
        <div class="text-[#336699]">Title</div>
        <input
          @input="checkUpdate"
          v-model="newAnnouncementData.announcementTitle"
          type="text"
          placeholder="insert title here..."
          class="ann-description w-full md:w-96 rounded-lg p-1"
      /></template>
      <template #description>
        <div class="text-[#336699]">Description</div>
        <textarea
          @input="checkUpdate"
          v-model="newAnnouncementData.announcementDescription"
          placeholder="insert description here..."
          class="ann-description w-full md:w-96 rounded-lg p-1"
          rows="4"
          cols="50"
        ></textarea>
      </template>
      <template #detail>
        <TextDescription class="flex-wrap xl:flex-nowrap">
          <template #header>Category</template>
          <template #default>
            <select
              class="bg-[#FAFAFA] p-1 h-9 w-full rounded-lg"
              v-model="newAnnouncementData.announcementCategory"
              @change="checkUpdate"
            >
              <option v-for="category in categories" :value="category.id">
                {{ category.categoryName }}
              </option>
            </select></template
          >
        </TextDescription>

        <TextDescription class="ann-publish-date flex-wrap xl:flex-nowrap">
          <template #header>Publish Date</template>
          <template #default>
            <div class="flex gap-5 flex-col xl:flex-row xl:w-full">
              <input
                @change="checkUpdate"
                v-model="newAnnouncementData.publishDate"
                type="date"
                id="publishDate"
                name="publishDate"
                class="ann-publish-date bg-[#FAFAFA] p-1 h-9 rounded-lg w-full"
              />
              <input
                @change="checkUpdate"
                v-model="newAnnouncementData.publishTime"
                type="time"
                id="publishTime"
                name="publishTime"
                class="ann-publish-time bg-[#FAFAFA] p-1 h-9 rounded-lg w-full"
              /></div
          ></template>
        </TextDescription>

        <TextDescription class="ann-close-date flex-wrap xl:flex-nowrap">
          <template #header>Close Date</template>
          <template #default>
            <div class="flex gap-5 flex-col xl:flex-row xl:w-full">
              <input
                @change="checkUpdate"
                v-model="newAnnouncementData.closeDate"
                type="date"
                id="closeDate"
                name="closeDate"
                class="ann-close-date bg-[#FAFAFA] p-1 h-9 rounded-lg w-full"
              />
              <input
                @change="checkUpdate"
                v-model="newAnnouncementData.closeTime"
                type="time"
                id="closeTime"
                name="closeTime"
                class="ann-close-time bg-[#FAFAFA] p-1 h-9 rounded-lg w-full"
              /></div
          ></template>
        </TextDescription>

        <TextDescription class="ann-display flex-wrap xl:flex-nowrap">
          <template #header>Display</template>
          <template #default
            ><div class="flex gap-x-2">
              <input
                @change="checkUpdate"
                v-model="newAnnouncementData.display"
                type="checkbox"
                name="display"
                id="display"
              />
              <label for="display">Check to show this announcement</label>
            </div></template
          >
        </TextDescription>
      </template>
    </AnnouncementCard>

    <div class="flex row gap-5 justify-end mt-10">
      <button
        class="w-48 bg-[#EF4444] text-white text-xl p-3 rounded"
        @click="
          router.name !== 'UpdateAnnouncement'
            ? $router.push(`/admin/announcement`)
            : $router.push(`/admin/announcement/${router.params.id}`)
        "
      >
        Cancel
      </button>
      <button
        @click="submitAnnouncement"
        :disabled="updateInput"
        class="w-48 bg-[#22C55E] text-white text-xl p-3 rounded disabled:opacity-50"
        v-if="router.name !== 'UpdateAnnouncement'"
      >
        Submit
      </button>
      <button
        @click="submitAnnouncement"
        :disabled="updateInput"
        class="w-48 bg-[#22C55E] text-white text-xl p-3 rounded disabled:opacity-50"
        v-else
      >
        Edit
      </button>
    </div>
  </PageTemplate>
</template>

<style scoped></style>
