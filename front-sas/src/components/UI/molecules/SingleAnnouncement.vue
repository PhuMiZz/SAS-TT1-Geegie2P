<script setup>
import { getLocaleDateTime } from '@/lib/dateTimeManagement.js';
import { useRouter } from 'vue-router';
import { ref } from 'vue';
import BadgeCategories from './BadgeCategories.vue';
import AnnouncementTemplate from '../../templates/AnnouncementTemplate.vue';
import ConfirmModal from '@/components/UI/organisms/ConfirmModal.vue';
import OverlayTemplate from '../../templates/OverlayTemplate.vue';
import AnnouncementService from '../../../lib/announcementService';

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

const emit = defineEmits(['refreshData']);
const router = useRouter();
const getAnnouncementDetail = (id) => {
  router.push({ name: 'DetailPage', params: { id: id } });
};

const toggleModal = (id) => {
  showModal.value = !showModal.value;
  if (showModal.value) {
    selectedAnnouncement.value = id;
  }
};

const deleteAnnouncement = async () => {
  // console.log('delete: ' + selectedAnnouncement.value);
  await announcementService.deleteAnnouncement(selectedAnnouncement.value);
  emit('refreshData', selectedAnnouncement.value);
  toggleModal();
};
</script>

<template>
  <AnnouncementTemplate>
    <template #announcementNo>
      {{ index + 1 }}
    </template>
    <template #title>
      <div class="text-[#737373] w-fit lg:1/3 lg:hidden block">Title</div>
      <p class="ann-title">
        {{ announcementItem.announcementTitle }}
      </p>
    </template>
    <template #category>
      <div class="text-[#737373] w-1/3 lg:hidden block">Category</div>
      <BadgeCategories :category="announcementItem.announcementCategory">
        <p class="ann-category">
          {{ announcementItem.announcementCategory }}
        </p></BadgeCategories
      >
    </template>
    <template #publishDate>
      <div class="text-[#737373] w-1/3 lg:hidden block">Publish Date</div>
      <p class="ann-publish-date">
        {{ getLocaleDateTime(announcementItem.publishDate) }}
      </p>
    </template>
    <template #closeDate>
      <div class="text-[#737373] w-1/3 lg:hidden block">Close Date</div>
      <p class="ann-close-date">
        {{ getLocaleDateTime(announcementItem.closeDate) }}
      </p>
    </template>
    <template #display>
      <div class="text-[#737373] w-1/3 lg:hidden block">Display</div>
      <p class="ann-display">{{ announcementItem.announcementDisplay }}</p>
    </template>
    <template #view>
      <div class="text-[#737373] w-1/3 lg:hidden block">#Views</div>
      <p class="ann-counter">{{ announcementItem.viewCount }}</p>
    </template>
    <template #action>
      <button
        class="ann-button"
        @click="getAnnouncementDetail(announcementItem.id)"
      >
        <div
          class="bg-[#336699] w-20 text-white active:bg-[#23476b] hover:bg-[#23476b] font text-lg px-3 py-2 rounded shadow hover:shadow-md outline-none focus:outline-none ease-linear transition-all duration-150"
        >
          view
        </div>
      </button>
      <button class="ann-button" @click="toggleModal(announcementItem.id)">
        <div
          class="bg-[#EF4444] w-20 text-white active:bg-[#B91C1C] hover:bg-[#B91C1C] text-lg px-3 mr-3 py-2 rounded shadow hover:shadow-md outline-none focus:outline-none ease-linear transition-all duration-150"
        >
          delete
        </div>
      </button>
    </template>
  </AnnouncementTemplate>

  <OverlayTemplate :showModal="showModal" @hideModal="toggleModal">
    <ConfirmModal
      @hideModal="toggleModal"
      @deleteAnnouncement="deleteAnnouncement"
    />
  </OverlayTemplate>
</template>

<style scoped></style>
