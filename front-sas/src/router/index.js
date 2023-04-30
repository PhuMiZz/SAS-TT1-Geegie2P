import { createRouter, createWebHistory } from "vue-router";
import Homepage from "@/components/pages/Homepage.vue";
import DetailPage from "@/components/pages/DetailPage.vue";
import PageNotFound from "@/components/pages/PageNotFound.vue";
import AnnouncementService from "@/lib/AnnouncementService.js";

const announcementService = new AnnouncementService();

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      redirect: "/admin/announcement",
    },
    {
      path: "/admin/announcement",
      name: "Announcements",
      component: Homepage,
    },
    {
      path: "/admin/announcement/:id",
      name: "DetailPage",
      component: DetailPage,
    },
    {
      path: "/:pathMatch(.*)*",
      name: "PageNotFound",
      component: PageNotFound,
    },
  ],
});

export default router;
