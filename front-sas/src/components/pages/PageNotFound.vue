<script setup></script>

<template>
  <div class="h-96 w-full flex flex-col justify-center items-center my-40">
    <div class="flex flex-col justify-center items-center">
      <h1 class="text-9xl font-extrabold text-[#336699] tracking-widest">
        404
      </h1>
      <div
        class="bg-[#EF4444] text-white px-2 text-sm rounded rotate-12 absolute"
      >
        Page Not Found
      </div>
    </div>

    <button class="mt-5 bg-[#336699] text-white text-xl rounded-lg w-36 p-5">
      <router-link to="/"> Go Home </router-link>
    </button>
  </div>
</template>

<style scoped></style>
