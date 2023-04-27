<script setup>
const props = defineProps({
  showModal: {
    type: Boolean,
    required: true,
  },
});
defineEmits(['hideModal']);
</script>

<template>
  <Teleport to="body">
    <Transition>
      <div
        v-if="showModal"
        class="fixed top-0 left-0 w-full h-full bg-gray-900/50 flex flex-col items-center justify-center z-[999]"
        @click.self="$emit('hideModal')"
      >
        <div
          class="flex flex-col w-full h-4/6 px-3 xl:p-0 md:h-3/6 md:max-w-[900px]"
        >
          <div
            class="bg-[#E87B92] shadow-xl w-[80%] h-[15%] md:h-[12%] rounded-t-xl"
          >
            <slot name="header"></slot>
          </div>
          <div
            class="flex flex-col bg-white shadow-xl w-[100%] h-full rounded-b-xl rounded-tr-xl"
          >
            <slot></slot>
          </div>
        </div>
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
