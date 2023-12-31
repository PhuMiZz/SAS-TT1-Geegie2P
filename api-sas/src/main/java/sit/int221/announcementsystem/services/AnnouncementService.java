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

import java.time.Instant;
import java.time.ZoneId;
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
    ZonedDateTime now = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));

    public Announcement getAnnouncementDetail(int announcementId, boolean incrementViewCount) {
        Announcement announcement = announcementRepository.findById(announcementId).orElseThrow(
                () -> new ItemNotFoundException("Announcement id: " + announcementId + " does not exist!.")
        );
        if (incrementViewCount) {
            announcement.setViewCount(announcement.getViewCount() != null ? announcement.getViewCount() + 1 : 1);
            announcementRepository.saveAndFlush(announcement);
        }

        return announcement;
    }
    public List<Announcement> getAnnouncementByCategory(int categoryId) {
        if (categoryId == 0) return announcementRepository.findAllByOrderById();
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ItemNotFoundException("Category ID: " + categoryId + " does not exist!.")
        );
        return announcementRepository.getAnnouncementsByCategory(category);
    }

    public AnnouncementCreateUpdateViewDto createAnnouncement(AnnouncementCreateUpdateDto newAnnouncement) {
        Announcement announcement = modelMapper.map(newAnnouncement, Announcement.class);
            announcement.setViewCount(0);
        if (announcement.getAnnouncementDisplay() == null) {
            announcement.setAnnouncementDisplay(Announcement.DisplayStatus.N);
        } else if (announcement.getAnnouncementDisplay().describeConstable().isEmpty()) {
            announcement.setAnnouncementDisplay(Announcement.DisplayStatus.N);
        }

        return modelMapper.map(announcementRepository.saveAndFlush(announcement), AnnouncementCreateUpdateViewDto.class);
    }



    public void DeleteAnnouncement(int announcementId) {
        announcementRepository.findById(announcementId).
                orElseThrow(() -> new ItemNotFoundException("The announcement is not found"));
        announcementRepository.deleteById(announcementId);
    }

    public AnnouncementCreateUpdateViewDto updateAnnouncement(AnnouncementCreateUpdateDto updateAnnouncement, AnnouncementCreateUpdateDto oldAnnouncement) {
        oldAnnouncement.setAnnouncementTitle(updateAnnouncement.getAnnouncementTitle());
        oldAnnouncement.setAnnouncementDescription(updateAnnouncement.getAnnouncementDescription());
        oldAnnouncement.setCategoryId(updateAnnouncement.getCategoryId());
        oldAnnouncement.setViewCount(oldAnnouncement.getViewCount());
        oldAnnouncement.setPublishDate(updateAnnouncement.getPublishDate());
        oldAnnouncement.setCloseDate(updateAnnouncement.getCloseDate());
        oldAnnouncement.setAnnouncementDisplay(updateAnnouncement.getAnnouncementDisplay());
        Announcement announcement = modelMapper.map(oldAnnouncement, Announcement.class);
        return modelMapper.map(announcementRepository.saveAndFlush(announcement), AnnouncementCreateUpdateViewDto.class);
    }

    public List<Announcement> getAnnouncementsByAdmin(String mode){
        return switch (mode.toLowerCase()){
            case "admin" -> announcementRepository.findAllByOrderById();
            case "close" -> announcementRepository.findClosedAnnouncementsWithDisplayStatus(Announcement.DisplayStatus.Y,now);
            case "active" -> announcementRepository.findActiveAnnouncementsWithDisplayStatus(Announcement.DisplayStatus.Y,now);
            default -> throw new BadRequestException("Invalid mode parameter. Expected 'admin', 'close', or 'active'.");
        };
    }
    public Page<Announcement> getAnnouncementsByModeAndCategory(String mode, Integer categoryId, Pageable pageable) {
        return switch (mode.toLowerCase()) {
            case "admin" ->
                    categoryId != null ? announcementRepository.findByCategoryOrderById(categoryId, pageable) : announcementRepository.findAllByOrderById(pageable);
            case "close" ->
                    categoryId != null ? announcementRepository.findClosedAnnouncementsByCategory(categoryId, pageable,now) : announcementRepository.findClosedAnnouncements(pageable,now);
            case "active" ->
                    categoryId != null ? announcementRepository.findActiveAnnouncementsByCategory(categoryId, pageable,now) : announcementRepository.findActiveAnnouncements(pageable,now);
            default -> throw new BadRequestException("Invalid mode parameter. Expected 'admin', 'close', or 'active'.");
        };
    }



}