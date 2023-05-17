<script setup>
import AdminIcon from "@/components/UI/atoms/AdminIcon.vue";
import UserViewIcon from "@/components/UI/atoms/UserViewIcon.vue";
import AboutUsIcon from "@/components/UI/atoms/AboutUsIcon.vue";
// import UserIcon from "@/components/UI/atoms/UserIcon.vue";
import { RouterLink } from "vue-router";
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
      class="max-w-screen flex flex-wrap items-center lg:justify-between mx-auto p-4"
    >
      <!-- Logo -->
      <RouterLink
        to="/admin/announcement"
        class="flex items-center ml-10 grow lg:grow-0"
      >
        <span
          class="self-center text-2xl font-semibold whitespace-nowrap text-[#336699]"
          >Geegie2P</span
        >
      </RouterLink>

      <div
        class="hidden lg:flex items-center justify-center space-x-16"
        id="navbar-default"
      >
        <RouterLink
          to="/admin/announcement"
          class="flex items-center text-xl gap-x-3"
        >
          <AdminIcon class="icon" />
          <span class="text-page">Admin View</span>
        </RouterLink>

        <RouterLink
          to="/announcement"
          class="flex items-center text-xl gap-x-3"
        >
          <UserViewIcon class="icon" />
          <span class="text-page">User View</span>
        </RouterLink>
      </div>

      <div class="w-[10%] flex justify-center">
        <!-- <UserIcon /> -->
      </div>
      <button
        data-collapse-toggle="navbar-default"
        type="button"
        class="inline-flex items-center p-2 text-sm text-gray-500 rounded-lg lg:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200"
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
      class="w-full absolute flex z-50 bg-[#F5F5F5] lg:hidden flex-col items-center pb-3 gap-3"
      v-if="isOpenMenu"
    >
      <RouterLink to="/admin/announcement" class="flex items-center gap-2">
        <AdminIcon class="w-10 icon" />
        <span
          class="w-20 hover:text-[#336699] ease-linear transition-all duration-150"
          >Home</span
        ></RouterLink
      >

      <RouterLink to="/announcement" class="flex items-center gap-2">
        <UserViewIcon class="w-10 icon" />
        <span
          class="w-20 hover:text-[#336699] ease-linear transition-all duration-150"
          >User View</span
        >
      </RouterLink>
    </div>
  </nav>
</template>

<style scoped>
.router-link-active .icon {
  fill: #336699;
  stroke: #ffffff;
}
.text-page:hover {
  border-bottom: solid 0.1em #336699;
  transition-property: all;
  transition-duration: 150ms;
  transition-timing-function: linear;
  color: #336699;
}
</style>
