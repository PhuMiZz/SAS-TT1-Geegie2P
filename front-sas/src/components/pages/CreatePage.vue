<script setup>
import TextDescription from "../UI/molecules/TextDescription.vue";
import AnnouncementCard from "../templates/AnnouncementCard.vue";
import Dropdown from "../UI/molecules/Dropdown.vue";
import AnnouncementService from "@/lib/AnnouncementService.js";
import { watchEffect, ref } from "vue";
import PageTemplate from "../templates/PageTemplate.vue";

const announcementService = new AnnouncementService();
const categories = ref([]);

watchEffect(async () => {
  categories.value = await announcementService.getAllCategory();
  categories.value = categories.value.map((e) => e.categoryName);
  console.log(categories.value);
});
</script>

<template>
  <PageTemplate class="my-10 flex gap-5">
    <AnnouncementCard :viewComponent="false">
      <template #title>
        <div class="text-[#336699]">Title</div>
        <input
          type="text"
          placeholder="insert title here..."
          class="ann-description w-96 rounded-lg p-1"
      /></template>
      <template #description>
        <div class="text-[#336699]">Description</div>
        <input
          type="textarea"
          placeholder="insert description here..."
          class="ann-description w-96 rounded-lg p-1"
          rows="4"
          cols="50"
        />
      </template>
      <template #detail>
        <TextDescription>
          <template #header>Category</template>
          <Dropdown :input="categories" class="ann-category" />
        </TextDescription>

        <TextDescription class="ann-publish-date">
          <template #header>Publish Date</template>
          <div class="flex gap-5">
            <input
              type="date"
              id="publishDate"
              name="publishDate"
              class="ann-publish-date bg-[#FAFAFA] p-1 h-9 rounded-lg w-40"
            />
            <input
              type="time"
              id="publishTime"
              name="publishTime"
              class="ann-publish-time bg-[#FAFAFA] p-1 h-9 rounded-lg w-40"
            />
          </div>
        </TextDescription>

        <TextDescription class="ann-close-date">
          <template #header>Close Date</template>
          <div class="flex gap-5">
            <input
              type="date"
              id="closeDate"
              name="closeDate"
              class="ann-close-date bg-[#FAFAFA] p-1 h-9 rounded-lg w-40"
            />
            <input
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
            <input type="checkbox" value="" name="display" id="display" />
            <label for="display">Check to show this announcement</label>
          </div>
        </TextDescription>
      </template>
    </AnnouncementCard>

    <div class="flex row gap-5 justify-end">
      <button class="w-48 bg-[#EF4444] text-white text-xl p-3 rounded">
        Cancel
      </button>
      <button class="w-48 bg-[#22C55E] text-white text-xl p-3 rounded">
        Submit
      </button>
    </div>
  </PageTemplate>
</template>

<style scoped></style>
