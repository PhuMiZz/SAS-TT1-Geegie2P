<script setup>
import { ref } from 'vue';
import NavigationBar from './components/UI/organisms/NavigationBar.vue';

const isClicked = ref(false);
const userCount = (state) => {
  isClicked.value = state;
};
</script>

<template>
  <NavigationBar />
  <RouterView @userClick="userCount" :isClicked="isClicked" />
</template>

<style scoped></style>
