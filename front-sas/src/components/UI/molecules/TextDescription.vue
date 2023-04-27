<script setup></script>

<template>
  <div class="flex w-full text-xl mb-5">
    <div class="w-1/4 pl-10"><slot name="header" /></div>
    <div class="w-2/3"><slot /></div>
  </div>
</template>

<style scoped></style>
