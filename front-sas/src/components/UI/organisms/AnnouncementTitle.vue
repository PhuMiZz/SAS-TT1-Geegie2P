<script setup>
import AddIcon from '../atoms/AddIcon.vue';
import DropDown from '../molecules/DropDown.vue';
import CategoryIcon from '../atoms/CategoryIcon.vue';
import SortIcon from '../atoms/SortIcon.vue';
import InputTemplate from '../../templates/InputTemplate.vue';

//mockup data
const categories = ['ทั้งหมด', 'ทั่วไป', 'ทุนการศึกษา', 'หางาน', 'ฝึกงาน'];
const sort = ['Title', 'Category', 'Publish Date', 'Close Date', 'Display'];

const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone;
</script>

<template>
  <div class="h-full xl:h-32 w-full my-5 xl:mt-5">
    <div
      class="h-2/3 flex flex-col md:flex-row items-center justify-evenly md:justify-between"
    >
      <!-- Header -->
      <div>
        <div class="text-[#00000] text-3xl text-center md:text-left">
          SIT Announcement System
        </div>
        <div class="text-[#737373] text-xl text-center md:text-left">
          Timezone: {{ timezone }}
        </div>
      </div>
    </div>
    <div class="h-3/5 flex flex-col">
      <div class="flex gap-x-3 items-center justify-around flex-wrap">
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
        <div class="flex h-full items-center">
          <button class="bg-[#E87B92] p-2 xl:p-3 rounded-md h-full truncate">
            <div class="flex gap-1 items-center text-[#00000] text-xl">
              <AddIcon />
              Add Announcement
            </div>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
