<script setup>
import ModalTemplate from "../../templates/ModalTemplate.vue";
const props = defineProps({
  announcementDetail: {
    type: Object,
    required: true,
  },
});
</script>

<template>
  <Teleport to="body">
    <ModalTemplate :showModal="isOpen"> </ModalTemplate>
  </Teleport>
</template>

<style scoped></style>
