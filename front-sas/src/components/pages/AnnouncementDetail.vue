<script setup>
import { useRoute } from "vue-router";
const { params } = useRoute();
const announcementId = params.id;
console.log(announcementId);
</script>

<template>
  <div>
    <h1>Question: {{ this.$route.params.id }}</h1>
  </div>
</template>

<style scoped></style>
