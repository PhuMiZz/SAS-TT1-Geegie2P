import { createRouter, createWebHistory } from "vue-router";
import Homepage from "@/components/pages/Homepage.vue";
import DetailPage from "@/components/pages/DetailPage.vue";

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
      name: "DetailPage",
      component: DetailPage,
    },
  ],
});

export default router;
