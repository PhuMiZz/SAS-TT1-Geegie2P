<script setup>
const props = defineProps({
  textColor: {
    type: String,
    required: true,
  },
  bgColor: {
    type: String,
    required: true,
  },
});
</script>

<template>
  <span
    :class="[textColor, bgColor]"
    class="w-fit h-fit text-lg font-medium mr-2 px-2.5 py-0.5 rounded-full truncate"
    ><slot></slot
  ></span>
</template>

<style scoped></style>
