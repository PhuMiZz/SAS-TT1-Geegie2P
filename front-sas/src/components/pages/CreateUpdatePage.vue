<script setup>
import TextDescription from "@/components/UI/molecules/TextDescription.vue";
import AnnouncementCard from "@/components/templates/AnnouncementCard.vue";
import TextEditor from "../UI/organisms/TextEditor.vue";
import AnnouncementService from "@/lib/announcementService.js";
import SuccessModal from "../UI/organisms/SuccessModal.vue";
import OverlayTemplate from "../templates/OverlayTemplate.vue";
import { watchEffect, ref, reactive, computed } from "vue";
import PageTemplate from "@/components/templates/PageTemplate.vue";
import { useRoute } from "vue-router";
import { checkDateTime, checkLength, checkDate } from "@/lib/validation";
import {
  extractDateAndTime,
  getISODateTime,
} from "@/lib/dateTimeManagement.js";

const announcementService = new AnnouncementService();
const router = useRoute();
const categories = ref([]);
const currentDate = ref();
const currentTime = ref();
const announcement = ref();
const showModal = ref(false);
const newAnnouncementData = reactive({
  announcementTitle: "",
  announcementDescription: "",
  announcementCategory: 1,
  publishDate: "",
  publishTime: "",
  closeDate: "",
  closeTime: "",
  display: false,
});
const originalAnnouncementData = reactive({});
const checkUpdate = computed(() => {
  if (router.name === "UpdateAnnouncement") {
    return (
      JSON.stringify(newAnnouncementData) !==
        JSON.stringify(originalAnnouncementData) &&
      newAnnouncementData.announcementTitle.trim().length > 0 &&
      newAnnouncementData.announcementDescription.trim().length > 0
    );
  } else {
    return (
      newAnnouncementData.announcementTitle.trim().length > 0 &&
      newAnnouncementData.announcementDescription.trim().length > 0
    );
  }
});
// const newAnnouncementDataJSON = computed(() =>
//   JSON.stringify(newAnnouncementData, null, 2)
// );

//function
const fetchAnnouncement = async () => {
  if (router.name === "UpdateAnnouncement") {
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
      display: announcement.value.announcementDisplay === "Y",
    });

    Object.assign(newAnnouncementData, originalAnnouncementData);
  }
};
const submitAnnouncement = async () => {
  if (
    !checkDateTime(
      newAnnouncementData.publishDate,
      newAnnouncementData.publishTime,
      "publish"
    ) ||
    !checkDateTime(
      newAnnouncementData.closeDate,
      newAnnouncementData.closeTime,
      "close"
    ) ||
    !checkLength(
      newAnnouncementData.announcementTitle,
      200,
      "Announcement title"
    ) ||
    !checkLength(
      newAnnouncementData.announcementDescription,
      10000,
      "Announcement description"
    ) ||
    !checkDate(
      getISODateTime(
        newAnnouncementData.publishDate,
        newAnnouncementData.publishTime
      ),
      new Date().toISOString(),
      "publish"
    ) ||
    !checkDate(
      getISODateTime(
        newAnnouncementData.closeDate,
        newAnnouncementData.closeTime
      ),
      new Date().toISOString(),
      "close"
    )
  ) {
    return;
  }
  if (
    getISODateTime(
      newAnnouncementData.publishDate,
      newAnnouncementData.publishTime
    ) >
      getISODateTime(
        newAnnouncementData.closeDate,
        newAnnouncementData.closeTime
      ) &&
    getISODateTime(
      newAnnouncementData.publishDate,
      newAnnouncementData.publishTime
    ) &&
    getISODateTime(newAnnouncementData.closeDate, newAnnouncementData.closeTime)
  ) {
    alert("Publish date must be before close date");
    return;
  }

  const newAnnouncement = {
    announcementTitle: newAnnouncementData.announcementTitle,
    announcementDescription: newAnnouncementData.announcementDescription,
    categoryId: newAnnouncementData.announcementCategory,
    publishDate: getISODateTime(
      newAnnouncementData.publishDate,
      newAnnouncementData.publishTime
    ),
    closeDate: getISODateTime(
      newAnnouncementData.closeDate,
      newAnnouncementData.closeTime
    ),
    announcementDisplay: newAnnouncementData.display ? "Y" : "N",
  };
  await createOrUpdateAnnouncement(newAnnouncement);
};
const createOrUpdateAnnouncement = async (announcement) => {
  try {
    if (router.name === "UpdateAnnouncement") {
      await announcementService.updateAnnouncement(
        router.params.id,
        announcement
      );
      toggleModal();
    } else {
      await announcementService.createAnnouncement(announcement);

      toggleModal();
    }
  } catch (error) {
    console.error("Error submitting announcement:", error);
    alert("There is an error");
  }
};
const updateCheck = () => {
  checkUpdate.value;
};
const toggleModal = () => {
  showModal.value = !showModal.value;
};

watchEffect(async () => {
  categories.value = await announcementService.getAllCategory();
  await fetchAnnouncement();
  currentDate.value = new Date().toISOString().split("T")[0];
  currentTime.value = new Date().toISOString().split("T")[1].substring(0, 5);
});

const setTimeDefault = (event) => {
  if (event.currentTarget.id === "publishDate") {
    newAnnouncementData.publishTime = newAnnouncementData.publishDate
      ? "06:00"
      : "";
  } else {
    newAnnouncementData.closeTime = newAnnouncementData.closeDate
      ? "18:00"
      : "";
  }

  updateCheck();
};
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
        <div class="text-[#336699] flex place-content-between">
          <label for="announcementTitle"> Title </label>
          <label
            for="announcementTitle"
            class="text-lg text-[#404040] place-self-end"
            :class="
              newAnnouncementData.announcementTitle.length < 180
                ? 'hidden'
                : newAnnouncementData.announcementTitle.length >= 200
                ? 'text-[#EF4444]'
                : 'text-[#F59B0E]'
            "
          >
            {{
              newAnnouncementData.announcementTitle.length >= 200
                ? "max length!!"
                : `${newAnnouncementData.announcementTitle.length}/200`
            }}
          </label>
        </div>
        <input
          @input="updateCheck"
          id="announcementTitle"
          v-model="newAnnouncementData.announcementTitle"
          type="text"
          placeholder="insert title here..."
          class="ann-title w-full rounded-lg p-1 text-[#404040]"
          maxlength="200"
      /></template>
      <template #description>
        <div class="text-[#336699] flex place-content-between">
          <label for="announcementDescription">Description</label>
          <label
            for="announcementDescription"
            class="text-lg text-[#404040] place-self-end"
            :class="
              newAnnouncementData.announcementDescription.length < 9980
                ? 'hidden'
                : newAnnouncementData.announcementDescription.length >= 10000
                ? 'text-[#EF4444]'
                : 'text-[#F59B0E]'
            "
          >
            {{
              newAnnouncementData.announcementDescription.length >= 10000
                ? "max length!!"
                : `${newAnnouncementData.announcementDescription.length}/10000`
            }}
          </label>
        </div>
        <!-- <textarea
          @input="updateCheck"
          id="announcementDescription"
          v-model="newAnnouncementData.announcementDescription"
          placeholder="insert description here..."
          class="ann-description w-full rounded-lg p-1 text-[#404040]"
          rows="4"
          cols="50"
          maxlength="10000"
        ></textarea> -->
        <TextEditor
          @textChange="updateCheck"
          id="announcementDescription"
          v-model:content="newAnnouncementData.announcementDescription"
          placeholder="insert description here..."
          class="ann-description w-full rounded-lg p-1 text-[#404040] h-52"
        />
      </template>
      <template #detail>
        <TextDescription class="flex-wrap xl:flex-nowrap">
          <template #header>Category</template>
          <template #default>
            <select
              class="ann-category bg-[#FAFAFA] p-1 h-9 w-full rounded-lg text-[#404040]"
              v-model="newAnnouncementData.announcementCategory"
              @change="updateCheck"
            >
              <option v-for="category in categories" :value="category.id">
                {{ category.categoryName }}
              </option>
            </select></template
          >
        </TextDescription>

        <TextDescription class="flex-wrap xl:flex-nowrap">
          <template #header>Publish Date</template>
          <template #default>
            <div class="flex gap-5 flex-col xl:flex-row xl:w-full">
              <input
                @change="setTimeDefault($event)"
                v-model="newAnnouncementData.publishDate"
                type="date"
                id="publishDate"
                name="publishDate"
                class="ann-publish-date bg-[#FAFAFA] p-1 h-9 rounded-lg w-full text-[#404040]"
                :min="currentDate"
                :max="
                  newAnnouncementData.closeDate
                    ? newAnnouncementData.closeDate
                    : ' '
                "
              />
              <input
                @change="updateCheck"
                v-model="newAnnouncementData.publishTime"
                type="time"
                id="publishTime"
                name="publishTime"
                class="ann-publish-time bg-[#FAFAFA] p-1 h-9 rounded-lg w-full disabled:opacity-60"
                :disabled="newAnnouncementData.publishDate === ''"
              /></div
          ></template>
        </TextDescription>

        <TextDescription class="flex-wrap xl:flex-nowrap">
          <template #header>Close Date</template>
          <template #default>
            <div class="flex gap-5 flex-col xl:flex-row xl:w-full">
              <input
                @change="setTimeDefault($event)"
                v-model="newAnnouncementData.closeDate"
                type="date"
                id="closeDate"
                name="closeDate"
                class="ann-close-date bg-[#FAFAFA] p-1 h-9 rounded-lg w-full text-[#404040]"
                :min="
                  newAnnouncementData.publishDate
                    ? newAnnouncementData.publishDate
                    : currentDate
                "
              />
              <input
                @change="updateCheck"
                v-model="newAnnouncementData.closeTime"
                type="time"
                id="closeTime"
                name="closeTime"
                class="ann-close-time bg-[#FAFAFA] p-1 h-9 rounded-lg w-full text-[#404040] disabled:opacity-60"
                :disabled="newAnnouncementData.closeDate === ''"
              /></div
          ></template>
        </TextDescription>

        <TextDescription class="flex-wrap xl:flex-nowrap">
          <template #header>Display</template>
          <template #default
            ><div class="flex gap-x-2">
              <input
                @change="updateCheck"
                v-model="newAnnouncementData.display"
                type="checkbox"
                name="display"
                id="display"
                class="ann-display"
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
        class="ann-button w-48 bg-[#EF4444] hover:bg-[#B91C1C] active:bg-[#B91C1C] text-white text-xl p-3 rounded ease-linear transition-all duration-150"
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
        class="ann-button w-48 bg-[#22C55E] hover:bg-[#15803D] active:bg-[#15803D] text-white text-xl p-3 rounded disabled:opacity-50 disabled:hover:bg-[#22C55E] ease-linear transition-all duration-150"
        v-if="router.name !== 'UpdateAnnouncement'"
      >
        Submit
      </button>
      <button
        @click="submitAnnouncement"
        :disabled="!checkUpdate"
        class="ann-button w-48 bg-[#22C55E] hover:bg-[#15803D] active:bg-[#15803D] text-white text-xl p-3 rounded disabled:opacity-50 disabled:hover:bg-[#22C55E] ease-linear transition-all duration-150"
        v-else
      >
        Update
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
