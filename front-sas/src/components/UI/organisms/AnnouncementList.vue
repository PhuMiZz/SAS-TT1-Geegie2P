<script setup>
const props = defineProps({
  announcementList: {
    type: Object,
    required: true,
  },
});
</script>

<template>
  <div
    v-for="(announcement, index) in announcementList"
    :key="announcement.id"
    :id="announcement.id"
    class="ann-item"
  >
    <slot :index="index" :announcement-item="announcement"></slot>
  </div>
</template>

<style scoped></style>
