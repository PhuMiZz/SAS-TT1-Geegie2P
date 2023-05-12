package sit.int221.announcementsystem.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sit.int221.announcementsystem.dtos.AnnouncementCreateUpdateDto;
import sit.int221.announcementsystem.dtos.AnnouncementCreateUpdateViewDto;
import sit.int221.announcementsystem.entities.Announcement;
import sit.int221.announcementsystem.entities.Category;
import sit.int221.announcementsystem.exceptions.BadRequestException;
import sit.int221.announcementsystem.exceptions.ItemNotFoundException;
import sit.int221.announcementsystem.repositories.AnnouncementRepository;
import sit.int221.announcementsystem.repositories.CategoryRepository;
import sit.int221.announcementsystem.utils.ListMapper;

import java.time.ZonedDateTime;
import java.util.List;


@Service
public class AnnouncementService {
    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper = ListMapper.getInstance();
    ZonedDateTime now = ZonedDateTime.now();

    public List<Announcement> getAnnouncements() {
        return announcementRepository.findAllByOrderByPublishDateDescCloseDateDesc();
    }

    public List<Announcement> getActiveAnnouncements() {
        return announcementRepository.findActiveAnnouncementsWithDisplayStatus(Announcement.DisplayStatus.Y, now);
    }

    public List<Announcement> getClosedAnnouncements() {
        return announcementRepository.findClosedAnnouncementsWithDisplayStatus(Announcement.DisplayStatus.Y,now);
    }


    public Announcement getAnnouncementDetail(int announcementId) {
        return announcementRepository.findById(announcementId).orElseThrow(
                () -> new ItemNotFoundException("Announcement id: " + announcementId + " does not exist!.")
        );
    }

    public List<Announcement> getAnnouncementByCategory(int categoryId) {
        if (categoryId == 0) return getAnnouncements();
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ItemNotFoundException("Category ID: " + categoryId + " does not exist!.")
        );
        return announcementRepository.getAnnouncementsByCategory(category);
    }

    public AnnouncementCreateUpdateViewDto createAnnouncement(AnnouncementCreateUpdateDto newAnnouncement) {
        Announcement announcement = modelMapper.map(newAnnouncement, Announcement.class);
        if (announcement.getAnnouncementDisplay() == null) {
            announcement.setAnnouncementDisplay(Announcement.DisplayStatus.N);
        }
        if (announcement.getAnnouncementTitle().isBlank() || announcement.getAnnouncementDescription().isBlank()) {
            throw new BadRequestException("Not Found");
        }
        return modelMapper.map(announcementRepository.saveAndFlush(announcement), AnnouncementCreateUpdateViewDto.class);
    }

    public void DeleteAnnouncement(int announcementId) {
        announcementRepository.findById(announcementId).
                orElseThrow(() -> new ItemNotFoundException("The announcement is not found"));
        announcementRepository.deleteById(announcementId);
    }

    public AnnouncementCreateUpdateViewDto updateAnnouncement(AnnouncementCreateUpdateDto updateAnnouncement, AnnouncementCreateUpdateDto oldAnnouncement) {
        oldAnnouncement.setAnnouncementTitle(updateAnnouncement.getAnnouncementTitle().isBlank() ? null : updateAnnouncement.getAnnouncementTitle());
        oldAnnouncement.setAnnouncementDescription(updateAnnouncement.getAnnouncementDescription().isBlank() ? null : updateAnnouncement.getAnnouncementDescription());
        oldAnnouncement.setCategoryId(updateAnnouncement.getCategoryId());
        oldAnnouncement.setPublishDate(updateAnnouncement.getPublishDate());
        oldAnnouncement.setCloseDate(updateAnnouncement.getCloseDate());
        oldAnnouncement.setAnnouncementDisplay(updateAnnouncement.getAnnouncementDisplay().isBlank() ? "N" : updateAnnouncement.getAnnouncementDisplay());
        Announcement announcement = modelMapper.map(oldAnnouncement, Announcement.class);
        return modelMapper.map(announcementRepository.saveAndFlush(announcement), AnnouncementCreateUpdateViewDto.class);
    }

    public Page<Announcement> getAnnouncementsByModeAndCategory(String mode, Integer categoryId, Pageable pageable) {
        return switch (mode.toLowerCase()) {
            case "admin" ->
                    categoryId != null ? announcementRepository.findByCategoryOrderByPublishDateDescCloseDateDesc(categoryId, pageable) : announcementRepository.findAllByOrderByPublishDateDescCloseDateDesc(pageable);
            case "close" ->
                    categoryId != null ? announcementRepository.findClosedAnnouncementsByCategory(categoryId, pageable,now) : announcementRepository.findClosedAnnouncements(pageable,now);
            default -> // active
                    categoryId != null ? announcementRepository.findActiveAnnouncementsByCategory(categoryId, pageable,now) : announcementRepository.findActiveAnnouncements(pageable,now);
        };
    }



}