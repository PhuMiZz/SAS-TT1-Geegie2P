<script setup>
const props = defineProps({
  announcementId: {
    type: Number,
    requied: true,
  },
});
</script>

<template>
  <div class="relative w-full max-w-md max-h-full">
    <div class="relative rounded-lg shadow bg-[#1e3e5e]">
      <button
        @click="$emit('hideModal')"
        type="button"
        class="absolute top-3 right-2.5 text-white bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center"
      >
        <svg
          aria-hidden="true"
          class="w-5 h-5"
          fill="white"
          viewBox="0 0 20 20"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            fill="white"
            d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z"
            clip-rule="evenodd"
          ></path>
        </svg>
        <span class="sr-only">Close modal</span>
      </button>
      <div class="p-6 text-center">
        <svg
          aria-hidden="true"
          class="mx-auto mb-4 text-white w-14 h-14"
          fill="none"
          stroke="currentColor"
          viewBox="0 0 24 24"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
          ></path>
        </svg>
        <h3 class="mb-5 text-lg font-normal text-white">
          Are you sure you want to delete this announcement?
        </h3>
        <button
          @click="$emit('deleteAnnouncement')"
          class="text-white bg-[#EF4444] hover:bg-red-800 rounded-lg text-sm px-5 py-2.5 mr-2"
        >
          Yes, I'm sure
        </button>
        <button
          @click="$emit('hideModal')"
          class="text-white bg-gray-600 hover:bg-slate-800 rounded-lg text-sm px-5 py-2.5"
        >
          No, cancel
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
