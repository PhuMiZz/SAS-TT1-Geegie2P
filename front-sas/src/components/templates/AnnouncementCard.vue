<script setup>
import DeleteIcon from "../UI/atoms/DeleteIcon.vue";
import EditIcon from "../UI/atoms/EditIcon.vue";
import BackIcon from "../UI/atoms/BackIcon.vue";
import { useRouter } from "vue-router";

const router = useRouter();
const props = defineProps({
  viewComponent: {
    type: Boolean,
    default: true,
  },
  announcementId: {
    type: Number,
    required: false,
  },
  announcementDetail: {
    type: Object,
    required: false,
  },
});

const editAnnouncement = () => {
  router.push({
    name: "UpdateAnnouncement",
    params: { id: props.announcementId },
  });
};

defineEmits(["routerPage"]);
</script>

<template>
  <!-- Button -->
  <div class="flex w-full h-12 text-xl gap-5">
    <button
      class="ann-button flex bg-[#336699] hover:bg-[#23476b] active:bg-[#23476b] item-center w-14 h-full p-3 rounded-t-lg ease-linear transition-all duration-150"
      @click="$emit('routerPage')"
    >
      <BackIcon />
      <p class="hidden">Back</p>
    </button>

    <button
      @click="editAnnouncement"
      :class="viewComponent ? '' : 'hidden'"
      class="flex gap-2 bg-[#F59E0B] hover:bg-[#d68d0e] active:bg-[#d68d0e] text-white items-center justify-center w-[30%] md:w-[15%] h-full p-3 rounded-t-lg ease-linear transition-all duration-150"
    >
      <EditIcon /> Edit
    </button>
    <button
      :class="viewComponent ? '' : 'hidden'"
      class="flex gap-2 bg-[#EF4444] hover:bg-[#B91C1C] active:bg-[#B91C1C] text-white items-center justify-center w-[30%] md:w-[15%] h-full p-3 rounded-t-lg ease-linear transition-all duration-150"
    >
      <DeleteIcon /> Delete
    </button>
  </div>

  <!-- Detail -->
  <div
    class="ann-item bg-[#F5F5F5] flex flex-col w-full h-full rounded-bl-lg rounded-r-lg p-5 md:p-10 gap-10 text-2xl items-center xl:flex-row xl:h-96"
  >
    <div class="w-full h-full flex flex-col gap-y-5 md:w-3/5">
      <slot name="title"></slot>
      <div>
        <slot name="description"></slot>
      </div>
    </div>
    <div class="w-full md:w-3/5 xl:w-3/5 h-full p-5 rounded-xl bg-[#E5E5E5]">
      <slot name="detail"></slot>
    </div>
  </div>
</template>

<style scoped></style>
