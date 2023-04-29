<script setup>
const props = defineProps({
  input: {
    type: Array,
    required: true,
  },
});
</script>

<template>
  <div>
    <select
      :id="input"
      :name="input"
      class="bg-[#FAFAFA] p-3 h-12 rounded-lg w-36"
    >
      <option v-for="(item, index) in input" :key="index" :value="item">
        {{ item }}
      </option>
    </select>
  </div>
</template>

<style scoped></style>
