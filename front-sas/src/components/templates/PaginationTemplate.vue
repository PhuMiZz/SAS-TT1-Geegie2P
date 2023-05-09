<script setup>
const props = defineProps([
  {
    totalPages: {
      type: Number,
      require: false,
    },
    offset: {
      type: Number,
      require: false,
    },
    pageSize: {
      type: Number,
      require: false,
    },
  },
]);
</script>

<template>
  <div class="p-5">
    <nav
      class="isolate inline-flex -space-x-px rounded-md shadow-sm"
      aria-label="Pagination"
    >
      <a
        href="#"
        class="w-20 relative inline-flex items-center justify-center rounded-l-md px-2 py-2 text-gray-400 ring-1 ring-inset ring-gray-300 transition duration-300 ease-in-out hover:bg-[#336699] hover:text-white focus:z-20 focus:outline-offset-0"
      >
        <span class="">Previous</span>
      </a>
      <!-- Current: "z-10 bg-indigo-600 text-white focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600", Default: "text-gray-900 ring-1 ring-inset ring-gray-300 hover:bg-gray-50 focus:outline-offset-0" -->
      <a
        href="#"
        aria-current="page"
        class="relative z-10 inline-flex items-center px-4 py-2 text-sm font-semibold text-gray-400 ring-1 ring-inset ring-gray-300 transition duration-300 ease-in-out hover:bg-[#336699] hover:text-white focus:z-20 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:bg-[#336699]"
        >1</a
      >
      <a
        href="#"
        class="w-20 relative inline-flex items-center justify-center rounded-r-md px-2 py-2 text-gray-400 ring-1 ring-inset ring-gray-300 transition duration-300 ease-in-out hover:bg-[#336699] hover:text-white focus:z-20 focus:outline-offset-0"
      >
        <span>Next</span>
      </a>
    </nav>
  </div>
</template>

<style scoped></style>
