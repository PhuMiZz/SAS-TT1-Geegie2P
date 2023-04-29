<script setup>
import { reactive, watchEffect, ref, onMounted } from "vue";
import AnnouncementService from "@/lib/AnnouncementService.js";
const announcementService = new AnnouncementService();
const categoryId = ref(0);

const props = defineProps({
  textColor: {
    type: String,
    required: false,
  },
  bgColor: {
    type: String,
    required: false,
  },
  category: {
    type: String,
    required: false,
  },
});

const badgeCategoryColor = reactive({
  textColor: props.textColor ?? "",
  bgColor: props.bgColor ?? "",
});

watchEffect(async () => {
  categoryId.value = await announcementService.getCategoryIdByName(
    props.category
  );

  switch (categoryId.value) {
    case 1:
      badgeCategoryColor.bgColor = "bg-green-300";
      badgeCategoryColor.textColor = "text-[#145941]";
      // console.log('ทั่วไป');
      break;
    case 2:
      badgeCategoryColor.bgColor = "bg-[#55CAF5]";
      badgeCategoryColor.textColor = "text-[#244F5F]";
      // console.log('ทุนการศึกษา');
      break;
    case 3:
      badgeCategoryColor.bgColor = "bg-[#FF9DA8]";
      badgeCategoryColor.textColor = "text-[#8D212D]";
      // console.log('หางาน');
      break;
    case 4:
      badgeCategoryColor.bgColor = "bg-yellow-300";
      badgeCategoryColor.textColor = "text-yellow-800";
      // console.log('ฝึกงาน');
      break;
    default:
      badgeCategoryColor.bgColor = "bg-gray-400";
      badgeCategoryColor.textColor = "text-black";
  }
});
</script>

<template>
  <span
    :class="[badgeCategoryColor.bgColor, badgeCategoryColor.textColor]"
    class="w-fit h-fit text-lg font-medium mr-2 px-2.5 py-0.5 rounded-full truncate"
    ><slot></slot
  ></span>
</template>

<style scoped></style>
