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
      class="bg-[#FAFAFA] p-1 h-9 rounded-lg w-40"
    >
      <option v-for="(item, index) in input" :key="index" :value="item">
        {{ item }}
      </option>
    </select>
  </div>
</template>

<style scoped></style>
