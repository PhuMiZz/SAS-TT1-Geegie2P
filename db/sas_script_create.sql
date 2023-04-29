CREATE SCHEMA IF NOT EXISTS `announcementsystem` DEFAULT CHARACTER SET utf8;
USE `announcementsystem`;
DROP TABLE IF EXISTS announcements;
DROP TABLE IF EXISTS categories;
CREATE TABLE categories
(
    categoryId   INT AUTO_INCREMENT PRIMARY KEY,
    categoryName VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE announcements
(
    announcementId          INT AUTO_INCREMENT PRIMARY KEY,
    announcementTitle       VARCHAR(200)    NOT NULL,
    announcementDescription VARCHAR(10000)  NOT NULL,
    announcementCategory    INT             NOT NULL DEFAULT 1,
    publishDate             DATETIME        NULL,
    closeDate               DATETIME        NULL,
    announcementDisplay     ENUM ('Y', 'N') NOT NULL DEFAULT 'N',
    INDEX fk_announcements_categories_idx (announcementCategory ASC) VISIBLE,
    CONSTRAINT fk_announcements_categories
        FOREIGN KEY (announcementCategory)
            REFERENCES categories (categoryId)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- Insert default categories into categories table
INSERT INTO `categories` (`categoryName`)
VALUES ('ทั่วไป'),
       ('ทุนการศึกษา'),
       ('หางาน'),
       ('ฝึกงาน');
SELECT @@GLOBAL.time_zone, @@SESSION.time_zone;
SET time_zone = '+00:00';