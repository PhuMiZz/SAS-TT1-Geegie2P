<script setup></script>

<template>
  <div class="flex w-full text-xl gap-5 mb-5">
    <div class="w-full xl:w-1/3"><slot name="header" /></div>
    <div class="w-full xl:w-2/3"><slot /></div>
  </div>
</template>

<style scoped></style>
