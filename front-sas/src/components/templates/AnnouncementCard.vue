<script setup>
import DeleteIcon from "../UI/atoms/DeleteIcon.vue";
import EditIcon from "../UI/atoms/EditIcon.vue";
import BackIcon from "../UI/atoms/BackIcon.vue";
import PageTemplate from "./PageTemplate.vue";
const props = defineProps({
  viewComponent: {
    type: Boolean,
    default: true,
  },
});
</script>

<template>
  <PageTemplate class="my-10">
    <!-- Button -->
    <div class="flex w-full h-12 text-xl gap-5">
      <RouterLink to="/admin/announcement">
        <button
          class="ann-button flex bg-[#336699] item-center w-full h-full p-3 rounded-t-lg"
        >
          <BackIcon />
          <p class="hidden">Back</p>
        </button>
      </RouterLink>

      <button
        :class="viewComponent ? '' : 'hidden'"
        class="flex gap-2 bg-[#F59E0B] text-white items-center justify-center w-[30%] md:w-[15%] h-full p-3 rounded-t-lg"
      >
        <EditIcon /> Edit
      </button>
      <button
        class="flex gap-2 bg-[#EF4444] text-white items-center justify-center w-[30%] md:w-[15%] h-full p-3 rounded-t-lg"
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
      <div class="w-full md:w-3/5 xl:w-2/5 h-full p-5 rounded-xl bg-[#E5E5E5]">
        <slot name="detail"></slot>
      </div>
    </div>
  </PageTemplate>
</template>

<style scoped></style>
