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
    class="flex flex-col items-center xl:flex-row"
    :class="
      props.header
        ? 'text-[#737373]'
        : 'items-center bg-gray-100 w-full gap-5  my-2 rounded-xl  h-full  xl:justify-evenly xl:h-24  '
    "
  >
    <div
      :class="props.header ? 'bg-transparent' : 'bg-[#E87B92]  '"
      class="flex w-full items-center justify-center h-full rounded-t-xl xl:aspect-square xl:w-1/5 xl:rounded-none xl:rounded-l-xl text-2xl"
    >
      <slot name="announcementNo"></slot>
    </div>
    <div
      class="flex w-full basis-full pt-3 px-3 gap-10 xl:gap-0 xl:p-0 xl:w-2/3 xl:justify-center text-xl"
    >
      <slot name="title"></slot>
    </div>
    <div
      class="flex w-full px-3 text-xl gap-10 xl:gap-0 xl:p-0 xl:w-2/4 xl:justify-center"
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
      class="flex w-full px-3 text-xl gap-10 xl:gap-0 xl:p-0 xl:w-1/4 xl:justify-center"
      :class="props.header ? '' : 'text-[#737373]'"
    >
      <slot name="display"></slot>
    </div>
    <div
      class="flex w-full pr-3 pb-3 gap-10 justify-end xl:gap-0 xl:p-0 xl:w-1/4 xl:justify-center"
    >
      <slot name="detail"></slot>
    </div>
  </div>
</template>

<style scoped></style>
