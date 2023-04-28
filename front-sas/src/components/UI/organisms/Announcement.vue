<script setup>
import { ref, watchEffect } from "vue";
import DeleteIcon from "../atoms/DeleteIcon.vue";
import EditIcon from "../atoms/EditIcon.vue";
import BackIcon from "../atoms/BackIcon.vue";
import TextDescription from "../molecules/TextDescription.vue";
import AnnouncementService from "../../../lib/AnnouncementService";
import { getLocaleDateTime } from "@/lib/DateTimeManagement.js";

const emits = defineEmits(["hideModal"]);
const props = defineProps({
  announcementId: {
    type: Number,
    required: true,
  },
  isOpen: {
    type: Boolean,
    required: false,
    default: false,
  },
});

const announcementService = new AnnouncementService();

const announcementDetail = ref([]);

watchEffect(async () => {
  announcementDetail.value = await announcementService.getAnnouncementDetail(
    props.announcementId
  );
});
</script>

<template>
  <!-- Button -->
  <div class="flex text-xl gap-5">
    <button class="flex bg-[#E87B92] item-center p-5 rounded-t-lg">
      <BackIcon />
    </button>
    <button class="flex gap-2 bg-[#FFC371] items-center w-1/7 p-5 rounded-t-lg">
      <EditIcon /> Edit
    </button>
    <button class="flex gap-2 bg-[#FF5F6D] items-center w-1/7 p-5 rounded-t-lg">
      <DeleteIcon /> Delete
    </button>
  </div>
  <div
    class="bg-[#F5F5F5] w-5/6 h-96 rounded-bl-lg rounded-r-lg p-10 text-2xl items-center"
  >
    <div class="flex flex-col gap-y-5">
      <div class="text-3xl">
        {{ announcementDetail.announcementTitle }}
      </div>
      <div>
        <div class="text-[#E87B92]">Description</div>
        <div>{{ announcementDetail.announcementDescription }}</div>
      </div>
    </div>
    <div bg-[#E5E5E5]>
      <TextDescription>
        <template #header>Category</template>
        <!-- <template #default>{{announcementDetail.}}</template> -->
      </TextDescription>
    </div>
  </div>
</template>

<style scoped></style>
