import { defineStore, acceptHMRUpdate } from 'pinia';
import { reactive, ref, watchEffect } from 'vue';
import AnnouncementService from '@/lib/announcementService.js';

export const usePageStore = defineStore('page', () => {
  const isLoading = ref(true);
  const allAnnouncement = ref([]);
  const isAnnouncementEmpty = ref(false);

  const announcementService = new AnnouncementService();

  const currentStatus = reactive({
    isActive: true,
    statusMode: 'active',
    categoryId: 0,
    pageNo: 0,
    showPaginate: false,
    isClicked : false
  });

  const changeCategory = async (id) => {
    currentStatus.categoryId = id;
    isAnnouncementEmpty.value =
      Object.keys(allAnnouncement.value.content).length === 0;
  };

  const toggleStatusAnnouncement = () => {
    currentStatus.isActive = !currentStatus.isActive;
    if (currentStatus.isActive) {
      currentStatus.statusMode = 'active';
    } else {
      currentStatus.statusMode = 'close';
    }
    currentStatus.pageNo = 0;
    // currentStatus.categoryId = 0;
  };

  watchEffect(async () => {
    isLoading.value = true;
    allAnnouncement.value = await announcementService.getPagesAllAnnouncement(
      currentStatus.statusMode,
      currentStatus.categoryId,
      currentStatus.pageNo
    );
    isAnnouncementEmpty.value =
      Object.keys(allAnnouncement.value.content).length === 0;
    isLoading.value = false;
    currentStatus.showPaginate =
      allAnnouncement.value.totalElements > 5 ? true : false;
  });

  const refreshAnnouncement = async (pageNo) => {
    currentStatus.pageNo = pageNo;
    allAnnouncement.value = await announcementService.getPagesAllAnnouncement(
      currentStatus.statusMode,
      currentStatus.categoryId,
      currentStatus.pageNo
    );
  };

  const getTotalIndex = (index) => {
    return allAnnouncement.value.page * allAnnouncement.value.size + index;
  };

  return {
    isLoading,
    allAnnouncement,
    isAnnouncementEmpty,
    currentStatus,
    changeCategory,
    toggleStatusAnnouncement,
    refreshAnnouncement,
    getTotalIndex,
  };
});
if (import.meta.hot) {
  import.meta.hot.accept(acceptHMRUpdate(usePageStore, import.meta.hot));
}
