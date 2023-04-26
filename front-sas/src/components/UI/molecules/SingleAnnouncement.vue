<script setup>
import BadgeCategories from './BadgeCategories.vue';
import DetailIcon from '../atoms/DetailIcon.vue';
const props = defineProps({
  announcementItem: {
    type: Object,
    required: true,
  },
});
</script>

<template>
  <div
    class="flex items-center justify-evenly bg-gray-100 w-full h-28 my-2 rounded-xl"
  >
    <div
      class="flex items-center justify-center w-1/5 h-full bg-[#E87B92] rounded-l-xl"
    >
      <h1 class="text-3xl">{{ announcementItem.id }}</h1>
    </div>
    <div class="text-xl w-full p-5">
      {{ announcementItem.announcementTitle }}
    </div>
    <div class="w-1/3 text-center">
      <BadgeCategories bg-color="bg-green-300" text-color="text-[#145941]">{{
        announcementItem.category.announcementCategory
      }}</BadgeCategories>
    </div>

    <div class="text-xl text-center w-2/3">
      {{ announcementItem.publishDate }}
    </div>
    <div class="text-xl text-center w-2/3">
      {{ announcementItem.closeDate }}
    </div>
    <div class="text-xl text-center w-1/4">{{ announcementItem.display }}</div>
    <button class="flex justify-center w-1/4">
      <DetailIcon />
    </button>
  </div>
</template>

<style scoped></style>
