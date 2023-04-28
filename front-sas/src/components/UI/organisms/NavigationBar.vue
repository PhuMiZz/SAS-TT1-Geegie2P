<script setup>
import HomeIcon from "@/components/UI/atoms/HomeIcon.vue";
import UserViewIcon from "@/components/UI/atoms/UserViewIcon.vue";
import AboutUsIcon from "@/components/UI/atoms/AboutUsIcon.vue";
import UserIcon from "@/components/UI/atoms/UserIcon.vue";
import { ref } from "vue";

const isOpenMenu = ref(false);
const onClickMenuHandler = () => {
  if (!isOpenMenu.value) {
    isOpenMenu.value = true;
  } else isOpenMenu.value = false;
};
</script>

<template>
  <nav class="sticky top-0 w-full h-full bg-[#F5F5F5]">
    <div
      class="max-w-screen flex flex-wrap items-center xl:justify-between mx-auto p-4"
    >
      <!-- Add Logo & Name -->
      <a href="" class="flex items-center ml-10 grow xl:grow-0">
        <span
          class="self-center text-2xl font-semibold whitespace-nowrap text-[#E87B92]"
          >Geegie2P</span
        >
      </a>

      <div
        class="hidden xl:flex items-center justify-center space-x-16"
        id="navbar-default"
      >
        <div class="flex items-center text-xl">
          <HomeIcon class="mr-3.5" />
          <button>Home</button>
        </div>
        <div class="flex items-center text-xl">
          <UserViewIcon class="mr-3.5" />
          <button>User View</button>
        </div>
        <div class="flex items-center text-xl">
          <AboutUsIcon class="mr-3.5" />
          <button>About Us</button>
        </div>
      </div>

      <div class="w-[10%] flex justify-center">
        <UserIcon />
      </div>
      <button
        data-collapse-toggle="navbar-default"
        type="button"
        class="inline-flex items-center p-2 text-sm text-gray-500 rounded-lg xl:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200"
        aria-controls="navbar-default"
        aria-expanded="false"
        @click="onClickMenuHandler"
      >
        <svg
          class="w-6 h-6"
          aria-hidden="true"
          fill="currentColor"
          viewBox="0 0 20 20"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            fill-rule="evenodd"
            d="M3 5a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 10a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 15a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1z"
            clip-rule="evenodd"
          ></path>
        </svg>
      </button>
    </div>
    <div
      class="w-full absolute flex z-50 bg-[#F5F5F5] xl:hidden flex-col items-center pb-3 gap-3"
      v-if="isOpenMenu"
    >
      <div class="flex items-center gap-2">
        <HomeIcon class="w-10" />
        <button class="w-20">Home</button>
      </div>
      <div class="flex items-center gap-2">
        <UserViewIcon class="w-10" />
        <button class="w-20">User View</button>
      </div>
      <div class="flex items-center gap-2">
        <AboutUsIcon class="w-10" />
        <button class="w-20">About Us</button>
      </div>
    </div>
  </nav>
</template>

<style scoped></style>
