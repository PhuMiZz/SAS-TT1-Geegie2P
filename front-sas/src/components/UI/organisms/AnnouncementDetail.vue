<script setup>
import OverlayTemplate from '../../templates/OverlayTemplate.vue';

defineEmits(['hideModal']);
const props = defineProps({
  announcementId: {
    type: Number,
    required: true,
  },
  isOpen: {
    type: Boolean,
    required: false,
    default: false,
  },
});
</script>

<template>
  <Teleport to="body">
    <OverlayTemplate :showModal="isOpen" @hideModal="$emit('hideModal')">
    </OverlayTemplate>
  </Teleport>
</template>

<style scoped></style>
