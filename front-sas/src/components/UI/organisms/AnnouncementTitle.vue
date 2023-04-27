<script setup>
import AddIcon from "../atoms/AddIcon.vue";
const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone;
</script>

<template>
  <div class="h-32 w-full">
    <div class="h-full flex items-center justify-between">
      <!-- Header -->
      <div>
        <div class="text-[#00000] text-2xl">SIT Announcement System</div>
        <div class="text-[#737373]">
          Date/Time shown in Timezone: {{ timezone }}
        </div>
      </div>

      <!-- Add announcement button -->
      <div class="flex items-center">
        <button class="bg-[#E87B92] p-3 rounded-md">
          <div class="flex gap-1 text-[#00000]">
            <AddIcon />
            Add Announcement
          </div>
        </button>
      </div>
    </div>
  </div>
</template>
