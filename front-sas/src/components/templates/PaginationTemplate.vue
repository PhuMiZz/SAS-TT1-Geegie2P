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
  if (props.offset <= Math.floor(props.pageRange / 2)) {
    console.log('if');
    return 0;
  } else if (
    props.offset + (Math.floor(props.pageRange / 2) - 1) >=
    props.totalPages
  ) {
    console.log('else if');
    return props.totalPages - props.pageRange + 1;
  } else {
    console.log('else');
    return props.offset - Math.floor(props.pageRange / 2);
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
  return props.offset === 1;
};
const hasLast = () => {
  return props.offset === props.totalPages;
};
</script>

<template>
  <div class="px-0 py-5 md:p-5 w-full flex justify-center items-center">
    <nav class="isolate w-fit inline-flex -space-x-px rounded-md shadow-sm">
      <button
        @click="selectPage(e, previousPage)"
        class="ann-page-prev w-16 md:w-20 relative inline-flex items-center justify-center rounded-l-md px-2 py-2 text-gray-400 ring-1 ring-inset ring-gray-300 transition duration-300 ease-in-out hover:bg-[#336699] hover:text-white focus:z-20 focus:outline-offset-0"
      >
        <span>Previous</span>
      </button>
      <!-- Current: "z-10 bg-indigo-600 text-white focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600", Default: "text-gray-900 ring-1 ring-inset ring-gray-300 hover:bg-gray-50 focus:outline-offset-0" -->

      <!-- <button
        v-if="hasFirst()"
        @click="selectPage(e, 0)"
        class="relative z-10 inline-flex items-center px-2 md:px-4 md:py-2 text-sm font-semibold text-gray-400 ring-1 ring-inset ring-gray-300 transition duration-300 ease-in-out hover:bg-[#336699] hover:text-white focus:z-20 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:bg-[#336699]"
      >
        <span>1</span>
      </button> -->
      <!-- <div
        v-if="hasFirst()"
        class="relative z-10 inline-flex items-center px-2 md:px-4 md:py-2 text-sm font-semibold text-gray-400 ring-1 ring-inset ring-gray-300 transition duration-300 ease-in-out"
      >
        <span>...</span>
      </div> -->

      <button
        v-for="page in pages"
        v-on="page === offset ? {} : { click: selectPage }"
        :key="page"
        :value="page"
        :class="
          page === offset
            ? `bg-[#336699] text-white ann-page-${page}`
            : `bg-white ann-page-${page}`
        "
        class="relative z-10 inline-flex items-center px-2 md:px-4 md:py-2 text-sm font-semibold text-gray-400 ring-1 ring-inset ring-gray-300 hover:bg-[#336699] hover:text-white focus:z-20 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:bg-[#336699]"
      >
        {{ page + 1 }}
      </button>
      <!-- <div
        v-if="hasLast()"
        class="relative z-10 inline-flex items-center px-2 md:px-4 md:py-2 text-sm font-semibold text-gray-400 ring-1 ring-inset ring-gray-300 transition duration-300 ease-in-out"
      >
        <span>...</span>
      </div> -->
      <!-- <button
        v-if="hasLast()"
        @click="selectPage(e, totalPages - 1)"
        class="relative z-10 inline-flex items-center px-2 md:px-4 md:py-2 text-sm font-semibold text-gray-400 ring-1 ring-inset ring-gray-300 transition duration-300 ease-in-out hover:bg-[#336699] hover:text-white focus:z-20 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:bg-[#336699]"
      >
        <span>{{ totalPages }}</span>
      </button> -->

      <button
        @click="selectPage(e, nextPage)"
        class="ann-page-next w-16 md:w-20 relative inline-flex items-center justify-center rounded-r-md px-2 py-2 text-gray-400 ring-1 ring-inset ring-gray-300 transition duration-300 ease-in-out hover:bg-[#336699] hover:text-white focus:z-20 focus:outline-offset-0"
      >
        <span>Next</span>
      </button>
    </nav>
  </div>
</template>

<style scoped></style>
