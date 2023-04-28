<script setup>
import { useRoute } from "vue-router";
import Announcement from "../UI/organisms/Announcement.vue";
const { params } = useRoute();
const announcementId = params.id;
console.log(announcementId);
</script>

<template>
  <div>
    <h1>Announcement: {{ this.$route.params.id }}</h1>
  </div>
  <Announcement :announcementId="announcementId" />
</template>

<style scoped></style>
