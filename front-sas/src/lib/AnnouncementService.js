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
      } else {
        return Promise.reject(response.statusText), false;
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
}
export default AnnouncementService;
