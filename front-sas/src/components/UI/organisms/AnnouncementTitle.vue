<script setup>
import AddIcon from "../atoms/AddIcon.vue";
import DropDown from "../molecules/DropDown.vue";
import CategoryIcon from "../atoms/CategoryIcon.vue";
import SortIcon from "../atoms/SortIcon.vue";
import InputTemplate from "../../templates/InputTemplate.vue";

const categories = ["ทั้งหมด", "ทั่วไป", "ทุนการศึกษา", "หางาน", "ฝึกงาน"];
const sort = ["Title", "Category", "Publish Date", "Close Date", "Display"];

const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone;
</script>

<template>
  <div class="h-32 w-full mb-5">
    <div
      class="h-2/3 flex flex-col md:flex-row items-center justify-evenly md:justify-between"
    >
      <!-- Header -->
      <div>
        <div class="text-[#00000] text-2xl text-center md:text-left">
          SIT Announcement System
        </div>
        <div class="text-[#737373]">Timezone: {{ timezone }}</div>
      </div>
    </div>
    <div class="h-1/3 flex place-content-between">
      <div class="flex gap-x-3 items-center">
        <InputTemplate>
          <CategoryIcon />
          Category:
          <DropDown :input="categories" />
        </InputTemplate>
        <InputTemplate>
          <SortIcon />
          Category:
          <DropDown :input="sort" />
        </InputTemplate>
      </div>

      <div class="justify-self-end flex items-center">
        <button class="bg-[#E87B92] p-3 rounded-md h-12">
          <div class="flex gap-1 items-center text-[#00000]">
            <AddIcon />
            Add Announcement
          </div>
        </button>
      </div>
    </div>
  </div>
</template>
