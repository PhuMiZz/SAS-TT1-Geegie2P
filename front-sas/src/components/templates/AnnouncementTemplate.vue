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
    class="flex"
    :class="
      header
        ? 'text-[#737373]'
        : 'items-center justify-evenly bg-gray-100 w-full h-24 my-2 rounded-xl'
    "
  >
    <div
      class="flex w-1/5 items-center justify-center h-full bg-[#E87B92] rounded-l-xl text-xl"
    >
      <slot name="announcementNo"></slot>
    </div>
    <div class="flex w-2/3 px-5"><slot name="title"></slot></div>
    <div class="flex w-2/4"><slot name="category"></slot></div>
    <div class="flex w-2/3" :class="header ? '' : 'text-[#737373]'">
      <slot name="publishDate"></slot>
    </div>
    <div class="flex w-2/3" :class="header ? '' : 'text-[#737373]'">
      <slot name="closeDate"></slot>
    </div>
    <div
      class="flex w-1/4 justify-center"
      :class="header ? '' : 'text-[#737373]'"
    >
      <slot name="display"></slot>
    </div>
    <div class="flex w-1/4 justify-center">
      <slot name="detail"></slot>
    </div>
  </div>
</template>

<style scoped></style>
