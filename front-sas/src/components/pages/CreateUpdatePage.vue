<script setup>
import TextDescription from '../UI/molecules/TextDescription.vue';
import AnnouncementCard from '../templates/AnnouncementCard.vue';
import AnnouncementService from '@/lib/AnnouncementService.js';
import { watchEffect, ref, reactive, computed } from 'vue';
import PageTemplate from '../templates/PageTemplate.vue';
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

const emptyInput = ref(true);

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
    await announcementService.createAnnouncement(newAnnouncement);
    alert('Announcement created successfully');
  } catch (error) {
    console.error('Error creating announcement:', error);
    alert('Error creating announcement, please try again');
  }
};
watchEffect(async () => {
  categories.value = await announcementService.getAllCategory();
});

const checkEmpty = () => {
  const titleInput = newAnnouncementData.announcementTitle;
  const descInput = newAnnouncementData.announcementDescription;
  if (titleInput.trim().length === 0 || descInput.trim().length === 0) {
    emptyInput.value = true;
  } else {
    emptyInput.value = false;
  }
};
</script>

<template>
  <!-- <pre>{{ newAnnouncementDataJSON }}</pre> -->

  <PageTemplate class="my-10">
    <AnnouncementCard :viewComponent="false">
      <template #title>
        <div class="text-[#336699]">Title</div>
        <input
          @input="checkEmpty"
          v-model="newAnnouncementData.announcementTitle"
          type="text"
          placeholder="insert title here..."
          class="ann-description w-full md:w-96 rounded-lg p-1"
      /></template>
      <template #description>
        <div class="text-[#336699]">Description</div>
        <textarea
          @input="checkEmpty"
          v-model="newAnnouncementData.announcementDescription"
          placeholder="insert description here..."
          class="ann-description w-full md:w-96 rounded-lg p-1"
          rows="4"
          cols="50"
        ></textarea>
      </template>
      <template #detail>
        <TextDescription>
          <template #header>Category</template>
          <select
            class="bg-[#FAFAFA] p-1 h-9 rounded-lg w-40"
            v-model="newAnnouncementData.announcementCategory"
          >
            <option v-for="category in categories" :value="category.id">
              {{ category.categoryName }}
            </option>
          </select>
        </TextDescription>

        <TextDescription class="ann-publish-date">
          <template #header>Publish Date</template>
          <div class="flex gap-5 flex-col xl:flex-row">
            <input
              v-model="newAnnouncementData.publishDate"
              type="date"
              id="publishDate"
              name="publishDate"
              class="ann-publish-date bg-[#FAFAFA] p-1 h-9 rounded-lg w-40"
            />
            <input
              v-model="newAnnouncementData.publishTime"
              type="time"
              id="publishTime"
              name="publishTime"
              class="ann-publish-time bg-[#FAFAFA] p-1 h-9 rounded-lg w-40"
            />
          </div>
        </TextDescription>

        <TextDescription class="ann-close-date">
          <template #header>Close Date</template>
          <div class="flex gap-5 flex-col xl:flex-row">
            <input
              v-model="newAnnouncementData.closeDate"
              type="date"
              id="closeDate"
              name="closeDate"
              class="ann-close-date bg-[#FAFAFA] p-1 h-9 rounded-lg w-40"
            />
            <input
              v-model="newAnnouncementData.closeTime"
              type="time"
              id="closeTime"
              name="closeTime"
              class="ann-close-time bg-[#FAFAFA] p-1 h-9 rounded-lg w-40"
            />
          </div>
        </TextDescription>

        <TextDescription class="ann-display">
          <template #header>Display</template>
          <div class="flex gap-x-2">
            <input
              v-model="newAnnouncementData.display"
              type="checkbox"
              value=""
              name="display"
              id="display"
            />
            <label for="display">Check to show this announcement</label>
          </div>
        </TextDescription>
      </template>
    </AnnouncementCard>

    <div class="flex row gap-5 justify-end mt-10">
      <button
        class="w-48 bg-[#EF4444] text-white text-xl p-3 rounded"
        @click="$router.go(-1)"
      >
        Cancel
      </button>
      <button
        @click="submitAnnouncement"
        :disabled="emptyInput"
        class="w-48 bg-[#22C55E] text-white text-xl p-3 rounded disabled:opacity-50"
        v-if="router.name !== 'UpdateAnnouncement'"
      >
        Submit
      </button>
      <button class="w-48 bg-[#22C55E] text-white text-xl p-3 rounded" v-else>
        Edit
      </button>
    </div>
  </PageTemplate>
</template>

<style scoped></style>
