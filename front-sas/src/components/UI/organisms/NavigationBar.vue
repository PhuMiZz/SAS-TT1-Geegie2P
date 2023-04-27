<script setup>
import HomeIcon from "@/components/UI/atoms/HomeIcon.vue";
import UserViewIcon from "@/components/UI/atoms/UserViewIcon.vue";
import AboutUsIcon from "@/components/UI/atoms/AboutUsIcon.vue";
import UserIcon from "@/components/UI/atoms/UserIcon.vue";
</script>

<template>
  <nav class="sticky top-0 w-full bg-[#F5F5F5] border-gray-200">
    <div
      class="max-w-screen flex flex-wrap items-center justify-between mx-auto p-4 "
    >
      <!-- Add Logo & Name -->
      <a href="" class="flex items-center ml-10">
        <span
          class="self-center text-2xl font-semibold whitespace-nowrap text-[#E87B92]"
          >Geegie2P</span
        >
      </a>
        <div class="flex items-center justify-center space-x-16">
            <div class="flex items-center">
                <HomeIcon class="mr-3.5" />
                <button>
                    Home
                </button>
            </div>
            <div class="flex items-center">
                <UserViewIcon class="mr-3.5" />
                <button>
                    User View
                </button>
            </div>
            <div class="flex items-center">
                <AboutUsIcon class="mr-3.5" />
                <button>
                    About Us
                </button>
            </div>
        </div>
        <div class="mr-6">
            <UserIcon/>
        </div>
    </div>
  </nav>
</template>

<style scoped></style>
