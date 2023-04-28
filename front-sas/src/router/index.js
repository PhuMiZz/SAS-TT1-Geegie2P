import { createRouter, createWebHistory } from "vue-router";
import Homepage from "@/components/pages/Homepage.vue";
import AnnouncementDetail from "@/components/pages/AnnouncementDetail.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/home",
      name: "Home",
      component: Homepage,
    },
    {
      path: "/announcements/:id",
      name: "AnnouncementDetail",
      component: AnnouncementDetail,
    },
  ],
});

export default router;
