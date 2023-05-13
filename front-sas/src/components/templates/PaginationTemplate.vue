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
  pageRange: {
    type: Number,
    default: 10,
  },
});
const pages = computed(() => {
  const pages = [];
  for (let i = pageStart.value; i < pageEnd.value; i++) {
    pages.push(i);
  }
  return pages;
});
const pageStart = computed(() => {
  const currentPage = props.offset + 1; // default offset 0
  if (currentPage < props.pageRange || props.pageRange >= props.totalPages) {
    return 0;
  } else if (currentPage === props.totalPages) {
    return currentPage - props.pageRange;
  } else {
    return currentPage - props.pageRange;
  }
});

const pageEnd = computed(() => {
  return Math.min(pageStart.value + props.pageRange, props.totalPages);
});

const nextPage = computed(() => {
  return props.offset + 1;
});

const previousPage = computed(() => {
  return props.offset - 1;
});

const selectPage = (e, action) => {
  if (action >= 0 && action < props.totalPages) {
    emit('selectPage', action);
  } else if (e) {
    const pageNo = e.target.value;
    emit('selectPage', pageNo);
  }
};
const hasFirst = () => {
  return props.offset + 1 === 1;
};
const hasLast = () => {
  return props.offset + 1 === props.totalPages;
};
</script>

<template>
  <div class="px-0 py-5 md:p-5 w-full flex justify-center items-center">
    <nav class="isolate w-fit inline-flex -space-x-px rounded-md shadow-sm">
      <button
        @click="selectPage(e, previousPage)"
        :disabled="hasFirst()"
        class="ann-page-prev w-16 md:w-20 relative inline-flex items-center justify-center rounded-l-md px-2 py-2 text-gray-400 ring-1 ring-inset ring-gray-300 transition duration-300 ease-in-out hover:bg-[#336699] hover:text-white focus:z-20 focus:outline-offset-0 disabled:hover:text-gray-400 disabled:hover:bg-white disabled:opacity-50"
      >
        <span>Previous</span>
      </button>

      <button
        v-for="(page, index) in pages"
        v-on="page === offset ? {} : { click: selectPage }"
        :key="page"
        :value="page"
        :class="
          page === offset
            ? `bg-[#336699] text-white ann-page-${index}`
            : `bg-white ann-page-${index} `
        "
        class="relative z-10 inline-flex items-center px-2 md:px-4 md:py-2 text-sm font-semibold text-gray-400 ring-1 ring-inset ring-gray-300 hover:bg-[#336699] hover:text-white focus:z-20 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:bg-[#336699]"
      >
        {{ page + 1 }}
      </button>

      <button
        @click="selectPage(e, nextPage)"
        :disabled="hasLast()"
        class="ann-page-next w-16 md:w-20 relative inline-flex items-center justify-center rounded-r-md px-2 py-2 text-gray-400 ring-1 ring-inset ring-gray-300 transition duration-300 ease-in-out hover:bg-[#336699] hover:text-white focus:z-20 focus:outline-offset-0 disabled:hover:text-gray-400 disabled:hover:bg-white disabled:opacity-50"
      >
        <span>Next</span>
      </button>
    </nav>
  </div>
</template>

<style scoped></style>
