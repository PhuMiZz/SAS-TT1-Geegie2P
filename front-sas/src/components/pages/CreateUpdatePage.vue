<script setup>
import TextDescription from '@/components/UI/molecules/TextDescription.vue';
import AnnouncementCard from '@/components/templates/AnnouncementCard.vue';
import AnnouncementService from '@/lib/AnnouncementService.js';
import SuccessModal from '../UI/organisms/SuccessModal.vue';
import OverlayTemplate from '../templates/OverlayTemplate.vue';
import { watchEffect, ref, reactive, computed, onMounted } from 'vue';
import PageTemplate from '@/components/templates/PageTemplate.vue';
import { useRoute } from 'vue-router';
import {
  extractDateAndTime,
  getISODateTime,
} from '@/lib/DateTimeManagement.js';

const announcementService = new AnnouncementService();
const categories = ref([]);
const router = useRoute();
const newAnnouncementData = reactive({
  announcementTitle: '',
  announcementDescription: '',
  announcementCategory: 1,
  publishDate: '',
  publishTime: '',
  closeDate: '',
  closeTime: '',
  display: false,
});
const newAnnouncementDataJSON = computed(() =>
  JSON.stringify(newAnnouncementData, null, 2)
);
const checkUpdate = computed(() => {
  if (router.name === 'UpdateAnnouncement') {
    return (
      JSON.stringify(newAnnouncementData) !==
      JSON.stringify(originalAnnouncementData)
    );
  } else {
    return (
      newAnnouncementData.announcementTitle.trim().length > 0 &&
      newAnnouncementData.announcementDescription.trim().length > 0
    );
  }
});
const originalAnnouncementData = reactive({});
const announcement = ref();
const showModal = ref(false);
//function
const updateCheck = () => {
  checkUpdate.value;
};
const toggleModal = () => {
  showModal.value = !showModal.value;
};
const submitAnnouncement = async () => {
  const newAnnouncement = {
    announcementTitle: newAnnouncementData.announcementTitle,
    announcementDescription: newAnnouncementData.announcementDescription,
    categoryId: newAnnouncementData.announcementCategory,
    publishDate:
      newAnnouncementData.publishDate || newAnnouncementData.publishTime
        ? getISODateTime(
            newAnnouncementData.publishDate,
            newAnnouncementData.publishTime
          )
        : null,
    closeDate:
      newAnnouncementData.closeDate || newAnnouncementData.closeTime
        ? getISODateTime(
            newAnnouncementData.closeDate,
            newAnnouncementData.closeTime
          )
        : null,
    announcementDisplay: newAnnouncementData.display ? 'Y' : 'N',
  };
  try {
    if (router.name === 'UpdateAnnouncement') {
      await announcementService.updateAnnouncement(
        router.params.id,
        newAnnouncement
      );
      // alert("Announcement updated successfully");
      // window.location = `/admin/announcement/${router.params.id}`;
      toggleModal();
    } else {
      await announcementService.createAnnouncement(newAnnouncement);
      // alert("Announcement created successfully");
      // window.location = "/admin/announcement";
      toggleModal();
    }
  } catch (error) {
    console.error('Error submitting announcement:', error);
    alert('Error submitting announcement, please try again');
  }
};

const fetchAnnouncement = async () => {
  if (router.name === 'UpdateAnnouncement') {
    const announcementId = router.params.id;
    announcement.value = await announcementService.getAnnouncementDetail(
      announcementId
    );

    const [publishDate, publishTime] = extractDateAndTime(
      announcement.value.publishDate
    );
    const [closeDate, closeTime] = extractDateAndTime(
      announcement.value.closeDate
    );

    Object.assign(originalAnnouncementData, {
      announcementTitle: announcement.value.announcementTitle,
      announcementDescription: announcement.value.announcementDescription,
      announcementCategory: categories.value.find(
        (e) => e.categoryName === announcement.value.announcementCategory
      ).id,
      publishDate,
      publishTime,
      closeDate,
      closeTime,
      display: announcement.value.announcementDisplay === 'Y',
    });

    Object.assign(newAnnouncementData, originalAnnouncementData);
  }
};

watchEffect(async () => {
  categories.value = await announcementService.getAllCategory();
  await fetchAnnouncement();
});
</script>

<template>
  <!-- <pre>{{ newAnnouncementDataJSON }}</pre> -->

  <PageTemplate class="my-10">
    <AnnouncementCard
      :viewComponent="false"
      @routerPage="
        router.name === 'UpdateAnnouncement'
          ? $router.push(`/admin/announcement/${router.params.id}`)
          : $router.push('/admin/announcement')
      "
    >
      <template #title>
        <div class="text-[#336699]">Title</div>
        <input
          @input="updateCheck"
          v-model="newAnnouncementData.announcementTitle"
          type="text"
          placeholder="insert title here..."
          class="ann-description w-full md:w-96 rounded-lg p-1 text-[#404040]"
      /></template>
      <template #description>
        <div class="text-[#336699]">Description</div>
        <textarea
          @input="updateCheck"
          v-model="newAnnouncementData.announcementDescription"
          placeholder="insert description here..."
          class="ann-description w-full md:w-96 rounded-lg p-1 text-[#404040]"
          rows="4"
          cols="50"
        ></textarea>
      </template>
      <template #detail>
        <TextDescription class="flex-wrap xl:flex-nowrap">
          <template #header>Category</template>
          <template #default>
            <select
              class="bg-[#FAFAFA] p-1 h-9 w-full rounded-lg text-[#404040]"
              v-model="newAnnouncementData.announcementCategory"
              @change="updateCheck"
            >
              <option v-for="category in categories" :value="category.id">
                {{ category.categoryName }}
              </option>
            </select></template
          >
        </TextDescription>

        <TextDescription class="ann-publish-date flex-wrap xl:flex-nowrap">
          <template #header>Publish Date</template>
          <template #default>
            <div class="flex gap-5 flex-col xl:flex-row xl:w-full">
              <input
                @change="updateCheck"
                v-model="newAnnouncementData.publishDate"
                type="date"
                id="publishDate"
                name="publishDate"
                class="ann-publish-date bg-[#FAFAFA] p-1 h-9 rounded-lg w-full text-[#404040]"
              />
              <input
                @change="updateCheck"
                v-model="newAnnouncementData.publishTime"
                type="time"
                id="publishTime"
                name="publishTime"
                class="ann-publish-time bg-[#FAFAFA] p-1 h-9 rounded-lg w-full"
              /></div
          ></template>
        </TextDescription>

        <TextDescription class="ann-close-date flex-wrap xl:flex-nowrap">
          <template #header>Close Date</template>
          <template #default>
            <div class="flex gap-5 flex-col xl:flex-row xl:w-full">
              <input
                @change="updateCheck"
                v-model="newAnnouncementData.closeDate"
                type="date"
                id="closeDate"
                name="closeDate"
                class="ann-close-date bg-[#FAFAFA] p-1 h-9 rounded-lg w-full text-[#404040]"
              />
              <input
                @change="updateCheck"
                v-model="newAnnouncementData.closeTime"
                type="time"
                id="closeTime"
                name="closeTime"
                class="ann-close-time bg-[#FAFAFA] p-1 h-9 rounded-lg w-full text-[#404040]"
              /></div
          ></template>
        </TextDescription>

        <TextDescription class="ann-display flex-wrap xl:flex-nowrap">
          <template #header>Display</template>
          <template #default
            ><div class="flex gap-x-2">
              <input
                @change="updateCheck"
                v-model="newAnnouncementData.display"
                type="checkbox"
                name="display"
                id="display"
              />
              <label
                for="display"
                class="text-[#737373]"
                :class="newAnnouncementData.display ? 'text-[#404040]' : ''"
                >Check to show this announcement</label
              >
            </div></template
          >
        </TextDescription>
      </template>
    </AnnouncementCard>

    <div class="flex row gap-5 justify-end mt-10">
      <button
        class="w-48 bg-[#EF4444] hover:bg-[#B91C1C] active:bg-[#B91C1C] text-white text-xl p-3 rounded ease-linear transition-all duration-150"
        @click="
          router.name === 'UpdateAnnouncement'
            ? $router.push(`/admin/announcement/${router.params.id}`)
            : $router.push('/admin/announcement')
        "
      >
        Cancel
      </button>
      <button
        @click="submitAnnouncement"
        :disabled="!checkUpdate"
        class="w-48 bg-[#22C55E] hover:bg-[#15803D] active:bg-[#15803D] text-white text-xl p-3 rounded disabled:opacity-50 disabled:hover:bg-[#22C55E] ease-linear transition-all duration-150"
        v-if="router.name !== 'UpdateAnnouncement'"
      >
        Submit
      </button>
      <button
        @click="submitAnnouncement"
        :disabled="!checkUpdate"
        class="w-48 bg-[#22C55E] hover:bg-[#15803D] active:bg-[#15803D] text-white text-xl p-3 rounded disabled:opacity-50 disabled:hover:bg-[#22C55E] ease-linear transition-all duration-150"
        v-else
      >
        Edit
      </button>
    </div>
  </PageTemplate>
  <OverlayTemplate :showModal="showModal">
    <SuccessModal
      :createAnnouncement="router.name !== 'UpdateAnnouncement'"
      @goPreviousPage="
        router.name === 'UpdateAnnouncement'
          ? $router.push(`/admin/announcement/${router.params.id}`)
          : $router.push('/admin/announcement')
      "
    />
  </OverlayTemplate>
</template>

<style scoped></style>
