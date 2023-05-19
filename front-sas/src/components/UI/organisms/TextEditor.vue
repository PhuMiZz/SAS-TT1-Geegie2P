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
        <span class="ql-formats">
          <select class="ql-font"></select>
          <select class="ql-header"></select>
        </span>

        <span class="ql-formats">
          <button class="ql-bold"></button>
          <button class="ql-italic"></button>
          <button class="ql-underline"></button>
          <button class="ql-strike"></button>
          <button class="ql-script" value="sub"></button>
          <button class="ql-script" value="super"></button>
        </span>

        <span class="ql-formats">
          <select class="ql-size"></select>
          <select class="ql-color"></select>
          <select class="ql-background"></select>
          <select class="ql-align"></select>
        </span>

        <span class="ql-formats">
          <button class="ql-list" value="ordered"></button>
          <button class="ql-list" value="bullet"></button
          ><button class="ql-blockquote"></button>
          <button class="ql-code-block"></button>
          <button class="ql-link"></button
        ></span>
      </div>
    </template>
  </QuillEditor>
</template>

<style scoped></style>
