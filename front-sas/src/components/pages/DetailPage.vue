<script setup>
import { ref, watchEffect } from "vue";
import { useRoute } from "vue-router";
import AnnouncementService from "@/lib/announcementService";
import LoadingPage from "../UI/organisms/LoadingPage.vue";
import AnnouncementCard from "../templates/AnnouncementCard.vue";
import TextDescription from "../UI/molecules/TextDescription.vue";
import { getLocaleDateTime } from "@/lib/dateTimeManagement.js";
import BadgeCategories from "../UI/molecules/BadgeCategories.vue";
import PageTemplate from "../templates/PageTemplate.vue";

const { params } = useRoute();
const announcementService = new AnnouncementService();

const announcementId = params.id;
// const foundAnnouncement = ref(false);
const announcementDetail = ref({});
const isLoading = ref(true);

watchEffect(async () => {
  isLoading.value = true;
  announcementDetail.value = await announcementService.getAnnouncementDetail(
    announcementId
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
      :announcementId="Number(announcementId)"
      :announcementDetail="announcementDetail"
      @routerPage="$router.push('/admin/announcement')"
    >
      <template #title
        ><div class="ann-title text-3xl">
          {{ announcementDetail.announcementTitle }}
        </div></template
      >
      <template #description>
        <div class="text-[#336699] text-xl">Description</div>
        <div class="ann-description text-lg">
          {{ JSON.parse(announcementDetail.announcementDescription) }}
        </div>
      </template>
      <template #detail>
        <TextDescription>
          <template #header>Category</template>
          <BadgeCategories
            class="ann-category"
            :category="announcementDetail.announcementCategory"
            >{{ announcementDetail.announcementCategory }}</BadgeCategories
          >
        </TextDescription>

        <TextDescription class="ann-publish-date">
          <template #header>Publish Date</template>

          <p class="text-lg">
            {{ getLocaleDateTime(announcementDetail.publishDate) }}
          </p>
        </TextDescription>

        <TextDescription class="ann-close-date">
          <template #header>Close Date</template>

          <p class="text-lg">
            {{ getLocaleDateTime(announcementDetail.closeDate) }}
          </p>
        </TextDescription>

        <TextDescription class="ann-display">
          <template #header>Display</template>
          <p class="text-lg">
            {{ announcementDetail.announcementDisplay }}
          </p>
        </TextDescription>
      </template>
    </AnnouncementCard>
    <!-- <OverlayTemplate v-else :showModal="!announcementDetail">
    <AlertOverlay />
  </OverlayTemplate> -->
  </PageTemplate>
</template>

<style scoped></style>
