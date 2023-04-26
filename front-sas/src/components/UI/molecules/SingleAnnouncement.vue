<script setup>
import BadgeCategories from './BadgeCategories.vue';
import DetailIcon from '../atoms/DetailIcon.vue';
import { onMounted, reactive, ref, watchEffect } from 'vue';
const props = defineProps({
  announcementItem: {
    type: Object,
    required: true,
  },
});
const badgeCategoryColor = reactive({
  textColor: '',
  bgColor: '',
});
watchEffect(() => {
  const categoryId = props.announcementItem.category.categoryId;
  switch (categoryId) {
    case 1:
      badgeCategoryColor.bgColor = 'bg-green-300';
      badgeCategoryColor.textColor = 'text-[#145941]';
      // console.log('ทั่วไป');
      break;
    case 2:
      badgeCategoryColor.bgColor = 'bg-[#55CAF5]';
      badgeCategoryColor.textColor = 'text-[#244F5F]';
      // console.log('ทุนการศึกษา');
      break;
    case 3:
      badgeCategoryColor.bgColor = 'bg-[#FF9DA8]';
      badgeCategoryColor.textColor = 'text-[#8D212D]';
      // console.log('หางาน');
      break;
    case 4:
      badgeCategoryColor.bgColor = 'bg-yellow-300';
      badgeCategoryColor.textColor = 'text-yellow-800';
      // console.log('ฝึกงาน');
      break;
  }
});
const onClickDetail = () => {
  console.log(props.announcementItem.id);
};
</script>

<template>
  <div
    class="flex items-center justify-evenly bg-gray-100 w-full h-28 my-2 rounded-xl"
  >
    <div
      class="flex items-center justify-center w-1/5 h-full bg-[#E87B92] rounded-l-xl"
    >
      <h1 class="text-3xl">{{ announcementItem.id }}</h1>
    </div>
    <div class="text-xl w-full p-5">
      {{ announcementItem.announcementTitle }}
    </div>
    <div class="w-1/3 text-center">
      <BadgeCategories
        :bg-color="badgeCategoryColor.bgColor"
        :text-color="badgeCategoryColor.textColor"
        >{{ announcementItem.category.announcementCategory }}</BadgeCategories
      >
    </div>

    <div class="text-xl text-center w-2/3">
      {{ announcementItem.publishDate }}
    </div>
    <div class="text-xl text-center w-2/3">
      {{ announcementItem.closeDate }}
    </div>
    <div class="text-xl text-center w-1/4">{{ announcementItem.display }}</div>
    <button class="flex justify-center w-1/4" @click="onClickDetail">
      <DetailIcon />
    </button>
  </div>
</template>

<style scoped></style>
