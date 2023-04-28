<script setup>
import { ref, watchEffect } from "vue";
import OverlayTemplate from "../../templates/OverlayTemplate.vue";
import DeleteIcon from "../atoms/DeleteIcon.vue";
import CloseIcon from "../atoms/CloseIcon.vue";
import EditIcon from "../atoms/EditIcon.vue";
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
const onClickCloseButton = () => {
  emits("hideModal");
};
watchEffect(async () => {
  announcementDetail.value = await announcementService.getAnnouncementDetail(
    props.announcementId
  );
});
</script>

<template>
  <Teleport to="body">
    <OverlayTemplate :showModal="isOpen" @hideModal="onClickCloseButton">
      <template #header>
        <div class="flex h-full px-5">
          <div class="w-full text-center text-xl self-center">
            {{ announcementDetail.announcementTitle }}
          </div>
          <button>
            <EditIcon />
          </button>
        </div>
      </template>
      <template #default>
        <button
          class="w-fit h-fit self-end px-5 pt-5"
          @click="onClickCloseButton"
        >
          <CloseIcon />
        </button>
        <div class="flex flex-col h-full justify-between px-5 pb-5">
          <!-- Category -->
          <TextDescription>
            <template #header>Category</template>
            <template #default>{{
              announcementDetail.category.announcementCategory
            }}</template>
          </TextDescription>
          <!-- Description -->
          <TextDescription>
            <template #header>Description</template>
            <template #default>
              {{ announcementDetail.announcementDescription }}
            </template>
          </TextDescription>
          <!-- Publish Date -->
          <TextDescription>
            <template #header>Publish Date</template>
            <template #default>{{
              announcementDetail.publishDate === null
                ? "-"
                : getLocaleDateTime(announcementDetail.publishDate)
            }}</template>
          </TextDescription>
          <!-- Close Date -->
          <TextDescription>
            <template #header>Close Date</template>
            <template #default>{{
              announcementDetail.closeDate === null
                ? "-"
                : getLocaleDateTime(announcementDetail.closeDate)
            }}</template>
          </TextDescription>
          <!-- Display & DeleteBtn -->
          <TextDescription>
            <template #header>Display</template>
            <template #default>{{ announcementDetail.display }}</template>
          </TextDescription>
          <div class="flex justify-end">
            <button class="bg-[#FF5F6D] rounded-lg px-5 py-2">
              <div class="flex gap-2 items-center">
                <DeleteIcon />
                Delete
              </div>
            </button>
          </div>
        </div>
      </template>
    </OverlayTemplate>
  </Teleport>
</template>

<style scoped></style>
