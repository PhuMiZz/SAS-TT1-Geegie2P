<script setup></script>

<template>
  <div class="h-screen w-full flex flex-col justify-center items-center">
    <div class="flex flex-col justify-center items-center">
      <h1 class="text-9xl font-extrabold text-[#E87B92] tracking-widest">
        404
      </h1>
      <div
        class="bg-[#BD3455] text-[#FAFAFA] px-2 text-sm rounded rotate-12 absolute"
      >
        Page Not Found
      </div>
    </div>

    <button
      class="mt-5 bg-[#BD3455] text-[#FAFAFA] text-xl rounded-lg w-36 p-5"
    >
      <router-link to="/"> Go Home </router-link>
    </button>
  </div>
</template>

<style scoped></style>
