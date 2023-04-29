<script setup>
import BadgeCategories from './BadgeCategories.vue';
import DetailIcon from '../atoms/DetailIcon.vue';
import AnnouncementTemplate from '../../templates/AnnouncementTemplate.vue';
import { onMounted, reactive, ref, watchEffect } from 'vue';
import { getLocaleDateTime } from '@/lib/DateTimeManagement.js';
import { useRouter } from 'vue-router';

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

const router = useRouter();
const getAnnouncementDetail = (id) => {
  router.push({ name: 'DetailPage', params: { id: id } });
};
</script>

<template>
  <AnnouncementTemplate>
    <template v-slot:announcementNo>
      {{ index + 1 }}
    </template>
    <template v-slot:title>
      <div class="text-[#737373] w-28 xl:hidden block">Title</div>
      {{ announcementItem.announcementTitle }}
    </template>
    <template v-slot:category>
      <div class="text-[#737373] w-28 xl:hidden block">Category</div>
      <BadgeCategories :category="announcementItem.announcementCategory">{{
        announcementItem.announcementCategory
      }}</BadgeCategories>
    </template>

    <template v-slot:publishDate>
      <div class="text-[#737373] w-28 xl:hidden block">Publish Date</div>
      {{
        announcementItem.publishDate === null
          ? '-'
          : getLocaleDateTime(announcementItem.publishDate)
      }}
    </template>
    <template v-slot:closeDate>
      <div class="text-[#737373] w-28 xl:hidden block">Close Date</div>
      {{
        announcementItem.closeDate === null
          ? '-'
          : getLocaleDateTime(announcementItem.closeDate)
      }}
    </template>
    <template v-slot:display>
      <div class="text-[#737373] w-28 xl:hidden block">Display</div>
      {{ announcementItem.announcementDisplay }}
    </template>
    <template v-slot:detail>
      <button @click="getAnnouncementDetail(announcementItem.id)">
        <DetailIcon />
      </button>
    </template>
  </AnnouncementTemplate>
</template>

<style scoped></style>
