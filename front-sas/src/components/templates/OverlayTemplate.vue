<script setup>
const props = defineProps({
  showModal: {
    type: Boolean,
    required: true,
  },
});
defineEmits(["hideModal"]);
</script>

<template>
  <Teleport to="body">
    <Transition>
      <div
        v-if="showModal"
        class="fixed top-0 left-0 w-full h-full bg-gray-900/50 flex flex-col items-center justify-center z-[999]"
        @click.self="$emit('hideModal')"
      >
        <slot></slot>
      </div>
    </Transition>
  </Teleport>
</template>

<style scoped>
.v-enter-active {
  transition: opacity 0.3s ease-in;
}

.v-leave-active {
  transition: opacity 0.3s ease-out;
}

.v-enter-from,
.v-leave-to {
  opacity: 0;
}
</style>
