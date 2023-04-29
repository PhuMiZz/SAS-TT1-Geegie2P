import { createRouter, createWebHistory } from "vue-router";
import Homepage from "@/components/pages/Homepage.vue";
import DetailPage from "@/components/pages/DetailPage.vue";
import AlertOverlay from "@/components/UI/organisms/AlertOverlay.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      redirect: "/admin/announcements",
    },
    {
      path: "/admin/announcements",
      name: "Announcements",
      component: Homepage,
    },
    {
      path: "/admin/announcements/:id",
      name: "DetailPage",
      component: DetailPage,
    },
    {
      path: "/:pathMatch(.*)*",
      name: "NotFoundPage",
      component: AlertOverlay,
    },
  ],
});

export default router;
