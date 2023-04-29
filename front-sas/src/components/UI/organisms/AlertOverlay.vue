<script setup></script>

<template>
  <div class="bg-red-900 text-center py-4 lg:px-4 flex gap-x-3 justify-center">
    <div
      class="p-2 bg-red-800 items-center text-slate-100 leading-none lg:rounded-full flex lg:inline-flex"
      role="alert"
    >
      <span
        class="flex rounded-full bg-red-500 uppercase px-2 py-1 text-xs font-bold mr-3"
        >error!
      </span>
      <span class="mr-2 text-left flex-auto"
        >The requested page is not available!</span
      >
    </div>
    <button class="text-slate-100 bg-red-800 px-3 lg:rounded-full">
      Go Home
    </button>
  </div>
</template>

<style scoped></style>
