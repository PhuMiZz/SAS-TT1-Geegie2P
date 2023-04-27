<script setup>
import AddIcon from "../atoms/AddIcon.vue";
</script>

<template>
  <div class="h-32 w-full">
    <div class="h-full flex items-center justify-between px-4">
      <div class="ml-20">
        <div class="text-[#00000] text-2xl">SIT Announcement System</div>
        <div class="text-[#737373]">
          Date/Time shown in Timezone: Asia/Bangkok
        </div>
      </div>
      <div class="flex-grow"></div>
      <div class="flex items-center space-x-4 mr-20">
        <div class="text-[#00000]">
          <button class="bg-[#E87B92] p-3 rounded-md">
            <div class="flex gap-1">
              <AddIcon />
              Add Announcement
            </div>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
