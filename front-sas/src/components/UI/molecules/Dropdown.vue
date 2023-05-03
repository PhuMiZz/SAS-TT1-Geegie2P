<script setup>
const props = defineProps({
    modelValue: [String,Number],
    input: {
        type: Array,
        required: true,
    },
});
// Add this method to the script section of the Dropdown component

</script>

<template>
    <div>
        <select
                :id="input"
                :name="input"
                class="bg-[#FAFAFA] p-1 h-9 rounded-lg w-40"
        >
            <option v-for="(item, index) in input" :key="index" :value="item.id">
                {{ item }}
            </option>
        </select>
    </div>
</template>

<style scoped></style>
