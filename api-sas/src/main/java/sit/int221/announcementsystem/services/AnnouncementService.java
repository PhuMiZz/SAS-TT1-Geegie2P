package sit.int221.announcementsystem.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sit.int221.announcementsystem.dtos.AnnouncementCreateUpdateDto;
import sit.int221.announcementsystem.dtos.AnnouncementCreateUpdateViewDto;
import sit.int221.announcementsystem.entities.Announcement;
import sit.int221.announcementsystem.entities.Category;
import sit.int221.announcementsystem.exceptions.BadRequest;
import sit.int221.announcementsystem.exceptions.ItemNotFoundException;
import sit.int221.announcementsystem.repositories.AnnouncementRepository;
import sit.int221.announcementsystem.repositories.CategoryRepository;

import java.util.List;

@Service
public class AnnouncementService {
    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Announcement> getAnnouncements() {
        return announcementRepository.findAllByOrderByPublishDateDescCloseDateDesc();
    }

    public Announcement getAnnouncementDetail(int announcementId) {
        return announcementRepository.findById(announcementId).orElseThrow(
                () -> new ItemNotFoundException("Announcement id: " + announcementId + " does not exist!.")
        );
    }

    public List<Announcement> getAnnouncementByCategory(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ItemNotFoundException("Category ID: " + categoryId + " does not exist!.")
        );
        return announcementRepository.getAnnouncementsByCategory(category);
    }

    public AnnouncementCreateUpdateViewDto createAnnouncement(AnnouncementCreateUpdateDto newAnnouncement) {
        Announcement announcement = modelMapper.map(newAnnouncement, Announcement.class);
        if (announcement.getAnnouncementDisplay() == null) {
            announcement.setAnnouncementDisplay(Announcement.DisplayStatus.N);
        } else  if (announcement.getAnnouncementTitle().isBlank() || announcement.getAnnouncementDescription().isBlank()){
            throw new BadRequest("Not Found");
        }
        return modelMapper.map(announcementRepository.saveAndFlush(announcement), AnnouncementCreateUpdateViewDto.class);
    }

    public void DeleteAnnouncement(int announcementId) {
        announcementRepository.findById(announcementId).
                orElseThrow(() -> new ItemNotFoundException("The announcement is not found"));
        announcementRepository.deleteById(announcementId);
    }

    public AnnouncementCreateUpdateViewDto updateAnnouncement(AnnouncementCreateUpdateDto updateAnnouncement,AnnouncementCreateUpdateDto oldAnnouncement){
//        AnnouncementCreateUpdateDto oldAnnouncement = modelMapper.map(announcementRepository.findById(announcementId).
//                orElseThrow(() -> new ItemNotFoundException("The announcement is not found")),AnnouncementCreateUpdateDto.class);
        oldAnnouncement.setAnnouncementTitle(updateAnnouncement.getAnnouncementTitle().isBlank() ? null : updateAnnouncement.getAnnouncementTitle());
        oldAnnouncement.setAnnouncementDescription(updateAnnouncement.getAnnouncementDescription().isBlank() ? null : updateAnnouncement.getAnnouncementDescription());
        oldAnnouncement.setCategoryId(updateAnnouncement.getCategoryId());
        oldAnnouncement.setPublishDate(updateAnnouncement.getPublishDate());
        oldAnnouncement.setCloseDate(updateAnnouncement.getCloseDate());
        oldAnnouncement.setAnnouncementDisplay(updateAnnouncement.getAnnouncementDisplay().isBlank() ? "N" : updateAnnouncement.getAnnouncementDisplay());
        Announcement announcement = modelMapper.map(oldAnnouncement,Announcement.class);
//        if (announcement.getAnnouncementDisplay() == null){
//            announcement.setAnnouncementDisplay(Announcement.DisplayStatus.N);
//        }
    return modelMapper.map(announcementRepository.saveAndFlush(announcement), AnnouncementCreateUpdateViewDto.class);
    }

}
