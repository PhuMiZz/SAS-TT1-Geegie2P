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
    class="flex flex-col items-center md:flex-row"
    :class="
      props.header
        ? 'text-[#737373]'
        : 'items-center bg-gray-100 w-full gap-1 md:justify-evenly md:h-24 my-2 rounded-xl'
    "
  >
    <div
      class="flex w-full md:w-1/5 items-center justify-center h-full bg-[#E87B92] rounded-t-xl md:rounded-none md:rounded-l-xl text-2xl"
    >
      <slot name="announcementNo"></slot>
    </div>
    <div
      class="flex w-full pt-3 px-3 md:p-0 md:w-2/3 md:justify-center text-xl"
    >
      <slot name="title"></slot>
    </div>
    <div class="flex w-full px-3 md:p-0 md:w-2/4 md:justify-center text-xl">
      <slot name="category"></slot>
    </div>
    <div
      class="flex w-full px-3 md:p-0 md:w-2/3 md:justify-center text-xl"
      :class="props.header ? '' : 'text-[#737373]'"
    >
      <slot name="publishDate"></slot>
    </div>
    <div
      class="flex w-full px-3 md:p-0 md:w-2/3 md:justify-center text-xl"
      :class="props.header ? '' : 'text-[#737373]'"
    >
      <slot name="closeDate"></slot>
    </div>
    <div
      class="flex w-full px-3 md:p-0 md:w-1/4 md:justify-center text-xl"
      :class="props.header ? '' : 'text-[#737373]'"
    >
      <slot name="display"></slot>
    </div>
    <div
      class="flex w-full md:w-1/4 justify-end md:justify-center pr-3 pb-3 md:p-0"
    >
      <slot name="detail"></slot>
    </div>
  </div>
</template>

<style scoped></style>
