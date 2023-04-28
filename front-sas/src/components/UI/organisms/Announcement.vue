<script setup>
import { ref, watchEffect } from 'vue';
import DeleteIcon from '../atoms/DeleteIcon.vue';
import EditIcon from '../atoms/EditIcon.vue';
import BackIcon from '../atoms/BackIcon.vue';
import TextDescription from '../molecules/TextDescription.vue';
import AnnouncementService from '../../../lib/AnnouncementService';
import BadgeCategories from '../molecules/BadgeCategories.vue';
import PageTemplate from '../../templates/PageTemplate.vue';
import { getLocaleDateTime } from '@/lib/DateTimeManagement.js';

const emits = defineEmits(['hideModal']);
const props = defineProps({
  announcementId: {
    type: String,
    required: true,
  },
  isOpen: {
    type: Boolean,
    required: false,
    default: false,
  },
});

const categoryName = ref('');
const categoryId = ref(null);
const announcementService = new AnnouncementService();

const announcementDetail = ref([]);

watchEffect(async () => {
  announcementDetail.value = await announcementService.getAnnouncementDetail(
    props.announcementId
  );
  console.log(announcementDetail.value);
  categoryName.value = announcementDetail.value.category.announcementCategory;
  categoryId.value = announcementDetail.value.category.categoryId;
});
</script>

<template>
  <!-- Button -->
  <PageTemplate class="my-10">
    <div class="flex w-full h-12 text-xl gap-5">
      <RouterLink to="/admin/announcements">
        <button
          class="flex bg-[#E87B92] item-center w-full h-full p-3 rounded-t-lg"
        >
          <BackIcon />
        </button>
      </RouterLink>

      <button
        class="flex gap-2 bg-[#FFC371] items-center justify-center w-[30%] md:w-[15%] h-full p-3 rounded-t-lg"
      >
        <EditIcon /> Edit
      </button>
      <button
        class="flex gap-2 bg-[#FF5F6D] items-center justify-center w-[30%] md:w-[15%] h-full p-3 rounded-t-lg"
      >
        <DeleteIcon /> Delete
      </button>
    </div>
    <div
      class="bg-[#F5F5F5] flex flex-col w-full h-full rounded-bl-lg rounded-r-lg p-5 md:p-10 gap-10 text-2xl items-center xl:flex-row xl:h-96"
    >
      <div class="w-full h-full flex flex-col gap-y-5 md:w-3/5">
        <div class="text-3xl">
          {{ announcementDetail.announcementTitle }}
        </div>
        <div>
          <div class="text-[#E87B92]">Description</div>
          <div>{{ announcementDetail.announcementDescription }}</div>
        </div>
      </div>
      <div class="w-full md:w-3/5 xl:w-2/5 h-full p-5 rounded-xl bg-[#E5E5E5]">
        <TextDescription>
          <template #header>Category</template>
          <BadgeCategories :category-id="categoryId">{{
            categoryName
          }}</BadgeCategories>
        </TextDescription>

        <TextDescription>
          <template #header>Publish Date</template>
          {{ announcementDetail.publishDate }}
        </TextDescription>

        <TextDescription>
          <template #header>Close Date</template>
          {{ announcementDetail.closeDate }}
        </TextDescription>

        <TextDescription>
          <template #header>Display</template>
          {{ announcementDetail.display }}
        </TextDescription>
      </div>
    </div>
  </PageTemplate>
</template>

<style scoped></style>
