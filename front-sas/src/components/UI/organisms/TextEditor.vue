<script setup>
import { QuillEditor } from "@vueup/vue-quill";
import "@vueup/vue-quill/dist/vue-quill.snow.css";
</script>

<template>
  <QuillEditor
    theme="snow"
    toolbar="#my-toolbar"
    class="quil-container border-hidden bg-white"
    style="min-height: 300px"
    contentType="html"
  >
    <template #toolbar>
      <div id="my-toolbar">
        <button class="ql-bold"></button>
        <button class="ql-italic"></button>
        <select class="ql-underline"></select>
        <select class="ql-background"></select>
        <select class="ql-color"></select>
        <select class="ql-font"></select>
        <button class="ql-link"></button>
        <select class="ql-size"></select>
        <button class="ql-strike"></button>
        <button class="ql-script" value="sub"></button>
        <button class="ql-script" value="super"></button>

        <button class="ql-blockquote"></button>
        <select class="ql-header"></select>
        <select class="ql-indent"></select>
        <button class="ql-list" value="ordered"></button>
        <button class="ql-list" value="bullet"></button>
        <button class="ql-direction"></button>
        <select class="ql-align"></select>
        <button class="ql-code-block"></button>
      </div>
    </template>
  </QuillEditor>
</template>

<style scoped>
.quil-container {
  border: 0px;
}
</style>
