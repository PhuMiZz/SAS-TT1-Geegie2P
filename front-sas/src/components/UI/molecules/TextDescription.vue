<script setup></script>

<template>
  <div class="flex w-full items-center text-xl gap-10">
    <div class="w-36"><slot name="header" /></div>
    <div class="w-full"><slot /></div>
  </div>
</template>

<style scoped></style>
