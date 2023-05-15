<script setup>
import { getLocaleDateTime } from '@/lib/dateTimeManagement.js';
import AnnouncementUserTemplate from '../../templates/AnnouncementUserTemplate.vue';
import BadgeCategories from './BadgeCategories.vue';

defineEmits(['announcementId']);
const props = defineProps({
  announcementItem: {
    type: Object,
    required: true,
  },
  index: {
    type: Number,
    required: false,
  },
  isActive: {
    type: Boolean,
    default: true,
  },
});
</script>

<template>
  <AnnouncementUserTemplate
    @click="$emit('announcementId', announcementItem.id)"
  >
    <template #announcementNo>
      {{ index + 1 }}
    </template>
    <template #title>
      <div class="text-[#737373] w-1/3 md:w-1/3 xl:hidden block">Title</div>
      <p class="ann-title">{{ announcementItem.announcementTitle }}</p>
    </template>
    <template #closeDate v-if="isActive"
      ><div class="text-[#737373] w-1/3 xl:hidden block">Close Date</div>
      <p class="ann-close-date text-[#737373]">
        {{ getLocaleDateTime(announcementItem.closeDate) }}
      </p>
    </template>
    <template #category>
      <div class="text-[#737373] w-1/3 xl:hidden block">Category</div>
      <BadgeCategories :category="announcementItem.announcementCategory">
        <p class="ann-category">
          {{ announcementItem.announcementCategory }}
        </p></BadgeCategories
      >
    </template>
  </AnnouncementUserTemplate>
</template>

<style scoped></style>
