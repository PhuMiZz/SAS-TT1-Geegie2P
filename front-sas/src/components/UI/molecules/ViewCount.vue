<script setup>
import ViewIcon from '../atoms/ViewIcon.vue';
const props = defineProps({
  viewCount: {
    type: Number,
    required: true,
  },
});
</script>

<template>
  <div class="flex h-full items-center gap-3 text-gray-600">
    <ViewIcon />
    <p class="text-lg">{{ viewCount }}</p>
  </div>
</template>

<style scoped></style>
