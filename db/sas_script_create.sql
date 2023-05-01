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

truncate table announcements;

-- Insert default categories into categories table
INSERT INTO `categories` (`categoryName`)
VALUES ('ทั่วไป'),
       ('ทุนการศึกษา'),
       ('หางาน'),
       ('ฝึกงาน');


INSERT INTO announcements(ANNOUNCEMENTTITLE, ANNOUNCEMENTDESCRIPTION, ANNOUNCEMENTCATEGORY, PUBLISHDATE, CLOSEDATE,
                          ANNOUNCEMENTDISPLAY)
VALUES ('บริษัท เน็ตเซอร์พลัส จำกัด รับสมัครงาน 2 ตำแหน่ง', 'บริษัท เน็ตเซอร์พลัส จำกัด เปิดรับสมัครงาน 2 ตำแหน่ง Application Support และ Customer Support',3,null,null,'N'),
       ('รายชื่อนักศึกษาที่ได้รับทุนการศึกษาประเภท "ทุนจ้างงาน" 2/2565','คณะ ฯ ประกาศรายชื่อนักศึกษาที่ได้รับทุนการศึกษาประเภท "ทุนจ้างงาน" ประจำภาคการศึกษา 2/2565',2,null,'2023-05-31 11:00:00','Y'),
       ('แนวปฎิบัติการสอบออนไลน์ พ.ศ. 2565','ประกาศมหาวิทยาลัยเทคโนโลยีพระจอมเกล้าธนบุรี เรื่องแนวทางปฎิบัติการสอบออนไลน์ พ.ศ. 2565',1,'2023-01-26 23:00:00',null,'Y'),
       ('กิจกรรมพี่อ้อย พี่ฉอด On Tour 2566','ขอเชิญนักศึกษาทุกชั้นปี เข้าร่วมกิจกรรมพี่อ้อย พี่ฉอด On Tour',1,'2023-04-18 23:00:00','2023-05-08 11:00:00','Y');

