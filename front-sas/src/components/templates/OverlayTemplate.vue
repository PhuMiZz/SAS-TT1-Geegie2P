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
        class="absolute top-0 left-0 w-screen h-screen bg-gray-900/50 flex flex-col items-center justify-center z-[999]"
        @click.self="$emit('hideModal')"
      >
        <div class="flex flex-col w-[60%] h-2/5" style="max-width: 700px">
          <div class="bg-[#E87B92] shadow-xl w-[70%] h-[15%] rounded-t-xl">
            <slot />
          </div>
          <div
            class="bg-white shadow-xl w-[100%] h-full rounded-b-xl rounded-tr-xl"
          ></div>
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
