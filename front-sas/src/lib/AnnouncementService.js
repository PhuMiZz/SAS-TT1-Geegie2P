class AnnouncementService {
  async getAllAnnouncement() {
    try {
      const response = await fetch(
        `${import.meta.env.VITE_API_URL}/api/announcements`
      );
      if (response.ok) {
        return await response.json();
      } else {
        return Promise.reject(response.statusText);
      }
    } catch (error) {
      console.error(`ERROR FETCHING DATA: ${error.message}`);
    }
  }
  async getAnnouncementDetail(id) {
    try {
      const response = await fetch(
        `${import.meta.env.VITE_API_URL}/api/announcements/${id}`
      );
      if (response.ok) {
        return await response.json();
      } else if (response.status === 404 || response.status === 400) {
        alert("The request page is not available");
        window.location = "/admin/announcement";
      } else {
        return Promise.reject(response.statusText);
      }
    } catch (error) {
      console.error(`ERROR FETCHING DETAIL: ${error.message}`);
    }
  }
  async getAllCategory() {
    try {
      const response = await fetch(
        `${import.meta.env.VITE_API_URL}/api/categories`
      );
      if (response.ok) {
        return await response.json();
      } else {
        return Promise.reject(response.statusText);
      }
    } catch (error) {
      console.error(`ERROR FETCHING DATA: ${error.message}`);
    }
  }
  async getCategoryIdByName(categoryName) {
    try {
      const response = await fetch(
        `${import.meta.env.VITE_API_URL}/api/categories/find/${categoryName}`
      );
      if (response.ok) {
        return await response.json();
      } else {
        return Promise.reject(response.statusText);
      }
    } catch (error) {
      console.error(`ERROR FETCHING DATA: ${error.message}`);
    }
  }

  //CRUD
  async createAnnouncement(newAnnouncement) {
    try {
      const response = await fetch(
        `${import.meta.env.VITE_API_URL}/api/announcements`,
        {
          method: "POST",
          body: JSON.stringify(newAnnouncement),
          headers: { "Content-Type": "application/json" },
        }
      );
      if (response.ok) {
        return await response.json();
      } else {
        return Promise.reject(response.statusText);
      }
    } catch (error) {
      console.error(`ERROR CREATING ANNOUNCEMENT: ${error.message}`);
    }
  }
  async deleteAnnouncement(id) {
    try {
      const response = await fetch(
        `${import.meta.env.VITE_API_URL}/api/announcements/${id}`,
        {
          method: "DELETE",
          headers: { "Content-Type": "application/json" },
        }
      );
      if (response.ok) {
        // return await response.json();
        console.log("Delete Successfully !");
      } else {
        return Promise.reject(response.statusText);
      }
    } catch (error) {
      console.error(`ERROR DELETING ANNOUNCEMENT: ${error.message}`);
    }
  }
  async updateAnnouncement(id, announcementData) {
    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/api/announcements/${id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(announcementData),
      });
      if (response.ok) {
        return await response.json();
      } else {
        return Promise.reject(response.statusText);
      }
    } catch (error) {
      console.error(`ERROR UPDATING ANNOUNCEMENT: ${error.message}`);
    }
  }


}
export default AnnouncementService;
