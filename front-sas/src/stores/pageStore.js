import { defineStore, acceptHMRUpdate } from "pinia";
import { reactive } from "vue";

export const usePageStore = defineStore("page", () => {
  const currentStatus = reactive({
    isActive: true,
    statusMode: "active",
    categoryId: 0,
    pageNo: 0,
  });

  return { currentStatus };
});
if (import.meta.hot) {
  import.meta.hot.accept(acceptHMRUpdate(usePageStore, import.meta.hot));
}
