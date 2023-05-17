<script setup></script>

<template>
  <div class="flex w-full text-xl gap-5 mb-5 items-center">
    <div class="w-full lg:w-1/3 h-fit text-xl"><slot name="header" /></div>
    <div class="w-full lg:w-2/3 h-fit"><slot /></div>
  </div>
</template>

<style scoped></style>
