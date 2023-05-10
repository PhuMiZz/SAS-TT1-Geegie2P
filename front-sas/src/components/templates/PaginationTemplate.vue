<script setup>
import { ref, watchEffect, computed } from 'vue';
const emit = defineEmits(['selectPage']);
const props = defineProps({
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
});

const selectPage = (e, action) => {
  if (action >= 0 && action < props.totalPages) {
    emit('selectPage', action);
  } else if (e) {
    const pageNo = e.target.value;
    emit('selectPage', pageNo);
  }
};

const nextPage = computed(() => {
  return props.offset + 1;
});

const previousPage = computed(() => {
  return props.offset - 1;
});
</script>

<template>
  <div class="p-5">
    <nav
      class="isolate inline-flex -space-x-px rounded-md shadow-sm"
      aria-label="Pagination"
    >
      <button
        @click="selectPage(e, previousPage)"
        class="w-20 relative inline-flex items-center justify-center rounded-l-md px-2 py-2 text-gray-400 ring-1 ring-inset ring-gray-300 transition duration-300 ease-in-out hover:bg-[#336699] hover:text-white focus:z-20 focus:outline-offset-0"
      >
        <span class="">Previous</span>
      </button>
      <!-- Current: "z-10 bg-indigo-600 text-white focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600", Default: "text-gray-900 ring-1 ring-inset ring-gray-300 hover:bg-gray-50 focus:outline-offset-0" -->
      <button
        v-for="index in totalPages"
        v-on="index - 1 == offset ? {} : { click: selectPage }"
        :key="index"
        :value="index - 1"
        aria-current="page"
        :class="index - 1 == offset ? 'bg-[#336699] text-white' : 'bg-white'"
        class="relative z-10 inline-flex items-center px-4 py-2 text-sm font-semibold text-gray-400 ring-1 ring-inset ring-gray-300 transition duration-300 ease-in-out hover:bg-[#336699] hover:text-white focus:z-20 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:bg-[#336699]"
      >
        {{ index }}
      </button>
      <button
        @click="selectPage(e, nextPage)"
        class="w-20 relative inline-flex items-center justify-center rounded-r-md px-2 py-2 text-gray-400 ring-1 ring-inset ring-gray-300 transition duration-300 ease-in-out hover:bg-[#336699] hover:text-white focus:z-20 focus:outline-offset-0"
      >
        <span>Next</span>
      </button>
    </nav>
  </div>
</template>

<style scoped></style>
