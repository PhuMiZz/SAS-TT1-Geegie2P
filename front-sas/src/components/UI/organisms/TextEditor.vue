<script setup>
import { ref } from "vue";
import { QuillEditor } from "@vueup/vue-quill";
import "@vueup/vue-quill/dist/vue-quill.snow.css";

const editor = ref("");
</script>

<template>
  <QuillEditor
    @textChange="$emit('checkDescriptionLength', editor)"
    theme="snow"
    ref="editor"
    toolbar="#my-toolbar"
    class="quil-container border-hidden bg-white"
    style="min-height: 300px; border: none"
    contentType="html"
  >
    <template #toolbar>
      <div id="my-toolbar">
        <select class="ql-font"></select>
        <select class="ql-size"></select>
        <button class="ql-bold"></button>
        <button class="ql-italic"></button>
        <button class="ql-underline"></button>

        <select class="ql-color"></select>
        <select class="ql-background"></select>

        <button class="ql-strike"></button>
        <button class="ql-script" value="sub"></button>
        <button class="ql-script" value="super"></button>

        <button class="ql-blockquote"></button>
        <select class="ql-header"></select>
        <!-- <button class="ql-indent"></button> -->

        <button class="ql-list" value="ordered"></button>
        <button class="ql-list" value="bullet"></button>

        <!-- <button class="ql-direction"></button> -->
        <select class="ql-align"></select>

        <button class="ql-code-block"></button>
        <button class="ql-link"></button>
      </div>
    </template>
  </QuillEditor>
</template>

<style scoped></style>
