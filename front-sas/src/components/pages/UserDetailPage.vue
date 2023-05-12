<script setup>
import { ref, watchEffect } from "vue";
import { useRoute } from "vue-router";
import { getLocaleDateTime } from "@/lib/DateTimeManagement.js";
import AnnouncementService from "@/lib/AnnouncementService";
import LoadingPage from "../UI/organisms/LoadingPage.vue";
import AnnouncementCard from "../templates/AnnouncementCard.vue";
import BadgeCategories from "../UI/molecules/BadgeCategories.vue";
import PageTemplate from "../templates/PageTemplate.vue";

const { params } = useRoute();
const announcementService = new AnnouncementService();

const announcementId = params.id;
const announcementDetail = ref({});
const isLoading = ref(true);

watchEffect(async () => {
  isLoading.value = true;
  announcementDetail.value = await announcementService.getAnnouncementDetail(
    announcementId,
    "user"
  );
  if (announcementDetail.value) {
    isLoading.value = false;
  }
  isLoading.value = false;
});
</script>

<template>
  <PageTemplate class="my-10">
    <LoadingPage v-if="isLoading" />
    <AnnouncementCard
      v-else
      @router-page="$router.push('/announcement')"
      :user-component="true"
      :announcement-detail="announcementDetail"
      :announcement-id="Number(announcementId)"
    >
      <template #title
        ><div class="ann-title text-3xl">
          {{ announcementDetail.announcementTitle }}
        </div>
        <div class="flex flex-col w-full h-full gap-2 md:gap-5 md:flex-row">
          <BadgeCategories
            class="ann-category"
            :category="announcementDetail.announcementCategory"
            >{{ announcementDetail.announcementCategory }}
          </BadgeCategories>
          <div
            v-if="announcementDetail.closeDate !== null"
            class="ann-close-date text-lg text-[#737373]"
          >
            <span class="text-red-500">Closed on:</span>
            {{ getLocaleDateTime(announcementDetail.closeDate) }}
          </div>
        </div>
      </template>
      <template #description>
        <div class="text-[#336699] text-xl">Description</div>
        <div class="ann-description text-lg">
          {{ announcementDetail.announcementDescription }}
        </div>
      </template>
    </AnnouncementCard>
  </PageTemplate>
</template>

<style scoped></style>
