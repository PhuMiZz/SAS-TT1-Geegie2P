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
  async getPagesAllAnnouncement(mode = 'admin', categoryId = 0, pageNo = 0) {
    let modeItem = mode === 'admin' ? '' : `?mode=${mode}`;
    let categoryItem =
      categoryId === 0
        ? ''
        : mode === 'admin'
        ? `?category=${categoryId}`
        : `&category=${categoryId}`;
    let pageItem =
      pageNo === 0
        ? ''
        : mode === 'admin' && categoryId === 0
        ? `?page=${pageNo}`
        : `&page=${pageNo}`;

    try {
      const response = await fetch(
        `${
          import.meta.env.VITE_API_URL
        }/api/announcements/pages${modeItem}${categoryItem}${pageItem}`
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
  async getAnnouncementDetail(id, mode = 'admin', count = false) {
    try {
      const response = await fetch(
        `${import.meta.env.VITE_API_URL}/api/announcements/${id}?count=${count}`
      );
      if (response.ok) {
        return await response.json();
      } else if (response.status === 404 || response.status === 400) {
        alert('The request page is not available');
        window.location =
          mode === 'admin'
            ? `${import.meta.env.VITE_BASE_URL}/admin/announcement`
            : `${import.meta.env.VITE_BASE_URL}/announcement`;
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
  async getAllAnnouncementByCategory(categoryId) {
    try {
      const response = await fetch(
        `${
          import.meta.env.VITE_API_URL
        }/api/announcements/category/${categoryId}`
      );
      if (response.ok) {
        return await response.json();
      } else if (response.status === 404 || response.status === 400) {
        const errorResponse = await response.json();
        alert('The request page is not available');
        window.location = window.location =
          mode === 'admin'
            ? `${import.meta.env.VITE_BASE_URL}/admin/announcement`
            : `${import.meta.env.VITE_BASE_URL}/announcement`;
        return JSON.stringify(errorResponse.detail);
      } else {
        return Promise.reject(response.statusText);
      }
    } catch (error) {
      console.error(`ERROR FETCHING DETAIL: ${error.message}`);
    }
  }

  //CRUD
  async createAnnouncement(newAnnouncement) {
    try {
      const response = await fetch(
        `${import.meta.env.VITE_API_URL}/api/announcements`,
        {
          method: 'POST',
          body: JSON.stringify(newAnnouncement),
          headers: { 'Content-Type': 'application/json' },
        }
      );
      if (response.ok) {
        return await response.json();
      } else {
        const errorResponse = await response.json();
        console.error(
          `ERROR CREATING ANNOUNCEMENT: ${JSON.stringify(errorResponse)}`
        );
        return JSON.stringify(errorResponse.detail);
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
          method: 'DELETE',
          headers: { 'Content-Type': 'application/json' },
        }
      );
      if (response.ok) {
        // return await response.json();
        // console.log("Delete Successfully !");
      } else {
        return Promise.reject(response.statusText);
      }
    } catch (error) {
      console.error(`ERROR DELETING ANNOUNCEMENT: ${error.message}`);
    }
  }
  async updateAnnouncement(id, announcementData) {
    try {
      const response = await fetch(
        `${import.meta.env.VITE_API_URL}/api/announcements/${id}`,
        {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(announcementData),
        }
      );
      if (response.ok) {
        return await response.json();
      } else {
        const errorResponse = await response.json();
        console.error(
          `ERROR UPDATING ANNOUNCEMENT: ${JSON.stringify(errorResponse.detail)}`
        );
        return JSON.stringify(errorResponse.detail);
      }
    } catch (error) {
      console.error(`ERROR UPDATING ANNOUNCEMENT: ${error.message}`);
    }
  }
}
export default AnnouncementService;
