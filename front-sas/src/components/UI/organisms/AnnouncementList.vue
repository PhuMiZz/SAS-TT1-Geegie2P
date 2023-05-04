<script setup>
import SingleAnnouncement from '../molecules/SingleAnnouncement.vue';
const props = defineProps({
  announcementList: {
    type: Object,
    required: true,
  },
});
const emit = defineEmits(['refreshData']);
const getIdToFilterData = (announcementId) => {
  emit('refreshData', announcementId);
};
</script>

<template>
  <div
    v-for="(announcement, index) in announcementList"
    :key="announcement.id"
    :id="announcement.id"
    class="ann-item"
  >
    <SingleAnnouncement
      @refresh-data="getIdToFilterData"
      :index="index"
      :announcement-item="announcement"
    />
  </div>
</template>

<style scoped></style>
