<script setup></script>

<template>
  <div class="flex w-full text-xl gap-5 mb-5">
    <div class="w-[50%] p-0 md:w-1/4 md:pl-10"><slot name="header" /></div>
    <div class="w-full md:w-2/3"><slot /></div>
  </div>
</template>

<style scoped></style>
