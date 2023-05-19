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
    class="flex flex-col items-center w-full gap-5 pb-5 my-2 lg:pb-0 lg:flex-row lg:justify-evenly lg:h-full"
    :class="props.header ? 'text-[#737373] ' : 'bg-gray-100 rounded-xl h-full'"
  >
    <div
      :class="
        props.header
          ? 'bg-transparent text-lg '
          : 'bg-[#336699] text-white text-lg '
      "
      class="flex w-full items-center justify-center h-full rounded-t-xl lg:aspect-square lg:max-w-[5rem] lg:rounded-none lg:rounded-l-xl"
    >
      <slot name="announcementNo"></slot>
    </div>
    <div
      class="flex w-full basis-full px-3 gap-10 scrollbar-hide lg:overflow-auto lg:max-h-full lg:gap-0 lg:py-3 lg:w-1/3 text-lg lg:justify-start"
    >
      <slot name="title"></slot>
    </div>
    <div
      class="flex w-full px-3 text-lg gap-10 lg:gap-0 lg:p-0 lg:w-1/3 lg:justify-center"
    >
      <slot name="closeDate"></slot>
    </div>
    <div
      class="flex w-full px-3 text-lg gap-10 lg:gap-0 lg:p-0 lg:w-1/3 lg:justify-center"
    >
      <slot name="category"></slot>
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
