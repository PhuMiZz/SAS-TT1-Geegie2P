<script setup>
const props = defineProps({
  createAnnouncement: {
    type: Boolean,
    default: false,
  },
});
defineEmits(["hideModal", "goPreviousPage"]);
</script>

<template>
  <div class="relative w-full max-w-md max-h-full">
    <div class="relative rounded-lg shadow bg-[#1e3e5e]">
      <div class="p-6 text-center">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke-width="1.5"
          stroke="currentColor"
          class="w-14 h-14 text-[#22C55E] mx-auto mb-4"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            d="M9 12.75L11.25 15 15 9.75M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
          />
        </svg>

        <h3 class="mb-5 text-lg font-normal text-white">
          {{
            props.createAnnouncement
              ? "Announcement created successfully"
              : "Announcement updated successfully"
          }}
        </h3>
        <button
          @click.once="$emit('goPreviousPage')"
          class="text-white bg-gray-600 hover:bg-slate-800 rounded-lg text-sm px-5 py-2.5"
        >
          Go Back
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
