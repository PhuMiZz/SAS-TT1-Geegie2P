<script setup>
import ModalTemplate from '../../templates/ModalTemplate.vue';

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
    <ModalTemplate :showModal="isOpen" @hideModal="$emit('hideModal')">
    </ModalTemplate>
  </Teleport>
</template>

<style scoped></style>
