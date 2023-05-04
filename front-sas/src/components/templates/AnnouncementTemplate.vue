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
    class="flex flex-col items-center w-full gap-5 my-2 xl:flex-row xl:justify-evenly xl:h-24"
    :class="props.header ? 'text-[#737373] ' : 'bg-gray-100 rounded-xl h-full'"
  >
    <div
      :class="
        props.header
          ? 'bg-transparent text-xl'
          : 'bg-[#336699] text-white text-2xl'
      "
      class="flex w-full items-center justify-center h-full rounded-t-xl xl:aspect-square xl:w-1/5 xl:rounded-none xl:rounded-l-xl"
    >
      <slot name="announcementNo"></slot>
    </div>
    <div
      class="flex w-full basis-full pt-3 px-3 gap-10 xl:gap-0 xl:p-0 xl:w-1/3 text-xl xl:justify-start"
    >
      <slot name="title"></slot>
    </div>
    <div
      class="flex w-full px-3 text-xl gap-10 xl:gap-0 xl:p-0 xl:w-1/3 xl:justify-center"
    >
      <slot name="category"></slot>
    </div>
    <div
      class="flex w-full px-3 text-xl gap-10 xl:gap-0 xl:p-0 xl:w-2/3 xl:justify-center"
      :class="props.header ? '' : 'text-[#737373]'"
    >
      <slot name="publishDate"></slot>
    </div>
    <div
      class="flex w-full px-3 text-xl gap-10 xl:gap-0 xl:p-0 xl:w-2/3 xl:justify-center"
      :class="props.header ? '' : 'text-[#737373]'"
    >
      <slot name="closeDate"></slot>
    </div>
    <div
      class="flex w-full px-3 text-xl gap-10 xl:gap-0 xl:p-0 xl:w-1/5 xl:justify-center"
      :class="props.header ? '' : 'text-[#737373]'"
    >
      <slot name="display"></slot>
    </div>
    <div
      class="flex text-xl w-full pr-3 pb-3 gap-3 justify-end xl:gap-3 xl:p-0 xl:w-2/3 xl:justify-center"
      :class="props.header ? '' : 'text-[#737373]'"
    >
      <slot name="action"></slot>
    </div>
  </div>
</template>

<style scoped></style>
