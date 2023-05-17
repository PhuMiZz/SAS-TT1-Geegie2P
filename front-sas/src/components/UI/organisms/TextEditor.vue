<script setup>
import { QuillEditor } from "@vueup/vue-quill";
import "@vueup/vue-quill/dist/vue-quill.snow.css";
</script>

<template>
  <QuillEditor
    theme="snow"
    toolbar="full"
    class="quil-container border-hidden bg-white"
    style="min-height: 300px"
    contentType="html"
  />
</template>

<style scoped>
.quil-container {
  border: 0px;
}
</style>
