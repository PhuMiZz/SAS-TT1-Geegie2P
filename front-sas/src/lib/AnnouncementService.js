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
}
export default AnnouncementService