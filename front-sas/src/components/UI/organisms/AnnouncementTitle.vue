<script setup>
import AddIcon from "../atoms/AddIcon.vue";
import CategoryIcon from "../atoms/CategoryIcon.vue";
import InputTemplate from "../../templates/InputTemplate.vue";
import AnnouncementService from "@/lib/AnnouncementService.js";
import { ref, watchEffect } from "vue";
import router from "@/router";
import { usePageStore } from "@/stores/pageStore";
import { storeToRefs } from "pinia";

const pageStore = usePageStore();
const { currentStatus } = storeToRefs(pageStore);
const { toggleStatusAnnouncement } = pageStore;

const props = defineProps({
  isUserPage: {
    type: Boolean,
    require: false,
  },
});

const announcementService = new AnnouncementService();
const categories = ref([]);

watchEffect(async () => {
  categories.value = await announcementService.getAllCategory();
});

const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone;
const createAnnouncement = () => {
  router.push({ name: "CreateAnnouncement" });
};
</script>

<template>
  <div class="h-full xl:h-32 w-full my-5 xl:mt-5">
    <div
      class="h-2/3 flex flex-col md:flex-row items-center justify-evenly md:justify-between"
    >
      <!-- Header -->
      <div>
        <div class="text-[#00000] text-3xl text-center md:text-left">
          SIT Announcement System (SAS)
        </div>
        <div
          class="text-[#737373] text-xl text-center my-2 md:my-0 md:text-left"
        >
          Timezone: {{ timezone }}
        </div>
      </div>
    </div>
    <div class="h-3/5 flex flex-col items-center justify-between md:flex-row">
      <div
        class="flex items-center flex-wrap justify-center md:justify-start xl:gap-10"
      >
        <InputTemplate>
          <CategoryIcon />
          Category:
          <select
            class="ann-category bg-[#FAFAFA] p-1 h-9 w-full rounded-lg"
            v-model="currentStatus.categoryId"
            @change="$emit('changeCategory', currentStatus.categoryId)"
          >
            <option :value="0">ทั้งหมด</option>
            <option v-for="category in categories" :value="category.id">
              {{ category.categoryName }}
            </option>
          </select>
        </InputTemplate>
      </div>
      <div class="flex h-full xl:h-3/5 items-center">
        <button
          v-if="isUserPage"
          @click="toggleStatusAnnouncement"
          class="ann-button bg-[#336699] hover:bg-[#23476b] active:bg-[#23476b] text-white px-5 py-2 rounded-md h-full truncate ease-linear transition-all duration-150"
        >
          <div class="flex gap-1 items-center text-[#00000] text-xl">
            {{
              currentStatus.isActive
                ? "Active Announcements"
                : "Closed Announcements"
            }}
          </div>
        </button>
        <button
          v-else
          @click="createAnnouncement"
          class="ann-button bg-[#336699] hover:bg-[#23476b] active:bg-[#23476b] text-white px-5 py-2 rounded-md h-full truncate ease-linear transition-all duration-150"
        >
          <div class="flex gap-1 items-center text-[#00000] text-xl">
            <AddIcon />
            Add Announcement
          </div>
        </button>
      </div>
    </div>
  </div>
</template>
