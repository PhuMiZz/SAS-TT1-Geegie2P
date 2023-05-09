import { createRouter, createWebHistory } from "vue-router";

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
      component: () => import("@/components/pages/AdminPage.vue"),
    },
    {
      path: "/admin/announcement/:id",
      name: "DetailPage",
      component: () => import("@/components/pages/DetailPage.vue"),
    },
    {
      path: "/admin/announcement/add",
      name: "CreateAnnouncement",
      component: () => import("@/components/pages/CreateUpdatePage.vue"),
    },
    {
      path: "/admin/announcement/:id/edit",
      name: "UpdateAnnouncement",
      component: () => import("@/components/pages/CreateUpdatePage.vue"),
    },
    {
      path: "/announcement",
      name: "UserAnnouncement",
      component: () => import("@/components/pages/UserPage.vue"),
    },

    {
      path: "/announcement/:id",
      name: "UserDetailPage",
      component: () => import("@/components/pages/UserDetailPage.vue"),
    },
    {
      path: "/:pathMatch(.*)*",
      name: "PageNotFound",
      component: () => import("@/components/pages/PageNotFound.vue"),
    },
  ],
});

export default router;
