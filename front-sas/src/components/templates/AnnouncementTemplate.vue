<script setup>
const props = defineProps({
  header: {
    type: Boolean,
    default: false,
  },
});
</script>

<template>
  <div
    class="flex flex-col items-center w-full gap-5 my-2 lg:flex-row lg:justify-evenly lg:h-24"
    :class="props.header ? 'text-[#737373] ' : 'bg-gray-100 rounded-xl h-full'"
  >
    <div
      :class="
        props.header
          ? 'bg-transparent text-lg'
          : 'bg-[#336699] text-white text-lg'
      "
      class="flex w-full items-center justify-center h-full rounded-t-xl lg:aspect-square lg:w-1/5 lg:rounded-none lg:rounded-l-xl"
    >
      <slot name="announcementNo"></slot>
    </div>
    <div
      class="flex w-full basis-full pt-3 px-3 gap-10 scrollbar-hide lg:overflow-auto lg:max-h-full lg:gap-0 lg:py-3 lg:w-1/3 text-lg lg:justify-start"
    >
      <slot name="title"></slot>
    </div>
    <div
      class="flex w-full px-3 text-lg gap-10 lg:gap-0 lg:p-0 lg:w-1/3 lg:justify-center"
    >
      <slot name="category"></slot>
    </div>
    <div
      class="flex w-full px-3 text-lg gap-10 lg:gap-0 lg:p-0 lg:w-2/3 lg:justify-center"
      :class="props.header ? '' : 'text-[#737373]'"
    >
      <slot name="publishDate"></slot>
    </div>
    <div
      class="flex w-full px-3 text-lg gap-10 lg:gap-0 lg:p-0 lg:w-2/3 lg:justify-center"
      :class="props.header ? '' : 'text-[#737373]'"
    >
      <slot name="closeDate"></slot>
    </div>
    <div
      class="flex w-full px-3 text-lg gap-10 lg:gap-0 lg:p-0 lg:w-1/5 lg:justify-center"
      :class="props.header ? '' : 'text-[#737373]'"
    >
      <slot name="display"></slot>
    </div>
    <div
      class="flex text-lg w-full pr-3 pb-3 gap-3 justify-end lg:gap-3 lg:p-5 lg:w-2/3 lg:justify-center"
      :class="props.header ? '' : 'text-[#737373]'"
    >
      <slot name="action"></slot>
    </div>
  </div>
</template>

<style scoped>
/* For Webkit-based browsers (Chrome, Safari and Opera) */
.scrollbar-hide::-webkit-scrollbar {
  display: none;
}

/* For IE, Edge and Firefox */
.scrollbar-hide {
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
}
</style>
