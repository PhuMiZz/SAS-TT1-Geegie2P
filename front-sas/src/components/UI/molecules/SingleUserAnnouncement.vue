<script setup>
import { getLocaleDateTime } from '@/lib/DateTimeManagement.js';
import { useRouter } from 'vue-router';
import { ref } from 'vue';
import AnnouncementUserTemplate from '../../templates/AnnouncementUserTemplate.vue';
import BadgeCategories from './BadgeCategories.vue';
import AnnouncementTemplate from '../../templates/AnnouncementTemplate.vue';
import ConfirmModal from '@/components/UI/organisms/ConfirmModal.vue';
import OverlayTemplate from '../../templates/OverlayTemplate.vue';
import AnnouncementService from '../../../lib/AnnouncementService';

defineEmits(['announcementId']);
const showModal = ref(false);
const selectedAnnouncement = ref();
const announcementService = new AnnouncementService();
const props = defineProps({
  announcementItem: {
    type: Object,
    required: true,
  },
  index: {
    type: Number,
    required: false,
  },
});
</script>

<template>
  <AnnouncementUserTemplate
    @click="$emit('announcementId', announcementItem.id)"
  >
    <template #announcementNo> {{ index + 1 }} </template>
    <template #title>
      <div class="text-[#737373] w-1/3 md:w-1/3 xl:hidden block">Title</div>
      <p class="ann-title">{{ announcementItem.announcementTitle }}</p>
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
