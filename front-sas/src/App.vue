<script setup>
import { ref } from 'vue';
import NavigationBar from './components/UI/organisms/NavigationBar.vue';
const isClicked = ref(false);
</script>

<template>
  <NavigationBar />
  <RouterView
    @userClick="
      (state) => {
        isClicked = state;
      }
    "
    :isClicked="isClicked"
  />
</template>

<style scoped></style>
