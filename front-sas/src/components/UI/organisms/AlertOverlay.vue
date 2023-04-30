<script setup></script>

<template>
  <div
    class="bg-red-900 text-center py-4 px-4 flex flex-col gap-2 mx-5 md:mx-0 md:gap-3 md:flex-row justify-center rounded-2xl md:rounded-full"
  >
    <div
      class="p-2 bg-red-800 items-center text-slate-100 leading-none rounded-full flex lg:inline-flex"
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
    <button
      class="text-slate-100 bg-red-800 py-1 px-10 w-fit mx-auto md:mx-0 md:py-0 md:px-4 rounded-full"
    >
      <router-link to="/"> OK </router-link>
    </button>
  </div>
</template>

<style scoped></style>
