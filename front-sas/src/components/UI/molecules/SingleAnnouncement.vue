<script setup>
import BadgeCategories from "./BadgeCategories.vue";
import DetailIcon from "../atoms/DetailIcon.vue";
// import AnnouncementDetail from "@/components/pages/AnnouncementDetail.vue";
import AnnouncementTemplate from "../../templates/AnnouncementTemplate.vue";
import { onMounted, reactive, ref, watchEffect } from "vue";
import { getLocaleDateTime } from "@/lib/DateTimeManagement.js";
import { useRouter } from "vue-router";

const props = defineProps({
  announcementItem: {
    type: Object,
    required: true,
  },
});

const badgeCategoryColor = reactive({
  textColor: "",
  bgColor: "",
});
const showModal = ref(false);

const router = useRouter();
const getAnnouncementDetail = (id) => {
  // showModal.value = true;
  router.push({ name: "AnnouncementDetail", params: { id } });
};

watchEffect(() => {
  const categoryId = props.announcementItem.category.categoryId;
  switch (categoryId) {
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
  }
});
</script>

<template>
  <AnnouncementTemplate>
    <template v-slot:announcementNo>
      {{ announcementItem.id }}
    </template>
    <template v-slot:title>
      <div class="text-[#737373] w-28 md:hidden block">Title</div>
      {{ announcementItem.announcementTitle }}
    </template>
    <template v-slot:category>
      <div class="text-[#737373] w-28 md:hidden block">Category</div>
      <BadgeCategories
        :bg-color="badgeCategoryColor.bgColor"
        :text-color="badgeCategoryColor.textColor"
        >{{ announcementItem.category.announcementCategory }}</BadgeCategories
      >
    </template>

    <template v-slot:publishDate>
      <div class="text-[#737373] w-28 md:hidden block">Publish Date</div>
      {{
        announcementItem.publishDate === null
          ? "-"
          : getLocaleDateTime(announcementItem.publishDate)
      }}
    </template>
    <template v-slot:closeDate>
      <div class="text-[#737373] w-28 md:hidden block">Close Date</div>
      {{
        announcementItem.closeDate === null
          ? "-"
          : getLocaleDateTime(announcementItem.closeDate)
      }}
    </template>
    <template v-slot:display>
      <div class="text-[#737373] w-28 md:hidden block">Display</div>
      {{ announcementItem.display }}
    </template>
    <template v-slot:detail>
      <button @click="getAnnouncementDetail(announcementItem.id)">
        <DetailIcon />
      </button>
    </template>
  </AnnouncementTemplate>
  <!-- <AnnouncementDetail
    :announcement-id="Number(announcementItem.id)"
    :is-open="showModal"
    @hide-modal="showModal = false"
  /> -->
</template>

<style scoped></style>
