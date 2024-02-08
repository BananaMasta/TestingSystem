DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `groups` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `group_name` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
#
# DROP TABLE IF EXISTS `groups_student_list`;
# /*!40101 SET @saved_cs_client     = @@character_set_client */;
# /*!50503 SET character_set_client = utf8mb4 */;
# CREATE TABLE `groups_student_list` (
#                                        `groups_id` bigint(20) NOT NULL,
#                                        `student_list_id` bigint(20) NOT NULL,
#                                        UNIQUE KEY `UK_qg6uk2wg8kopf8nlgskucaecd` (`student_list_id`),
#                                        KEY `FKptfqo49pn85wdl2ek0623t7oi` (`groups_id`),
#                                        CONSTRAINT `FKptfqo49pn85wdl2ek0623t7oi` FOREIGN KEY (`groups_id`) REFERENCES `groups` (`id`),
#                                        CONSTRAINT `FKt0d2xt441093u1fatt5qshr8g` FOREIGN KEY (`student_list_id`) REFERENCES `user` (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
#
# DROP TABLE IF EXISTS `hibernate_sequence`;
# SET @saved_cs_client     = @@character_set_client;
# SET character_set_client = utf8mb4 ;
# CREATE TABLE `hibernate_sequence` (
#     `next_val` bigint(20) DEFAULT NULL
# ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
#
# DROP TABLE IF EXISTS `question`;
# SET @saved_cs_client     = @@character_set_client;
# SET character_set_client = utf8mb4;
# CREATE TABLE `question` (
#                             `id` bigint(20) NOT NULL,
#                             `answers` varchar(255) DEFAULT NULL,
#                             `quest_name` varchar(255) DEFAULT NULL,
#                             `question_difficult` int(11) DEFAULT NULL,
#                             `study_subject_id` bigint(20) DEFAULT NULL,
#                             PRIMARY KEY (`id`),
#                             KEY `FKn03fmaphk138gdxy3ggxdr9vb` (`study_subject_id`),
#                             CONSTRAINT `FKn03fmaphk138gdxy3ggxdr9vb` FOREIGN KEY (`study_subject_id`) REFERENCES `study_subject` (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
# DROP TABLE IF EXISTS `question_answer_variants`;
# /*!40101 SET @saved_cs_client     = @@character_set_client */;
# /*!50503 SET character_set_client = utf8mb4 */;
# CREATE TABLE `question_answer_variants` (
#                                             `question_id` bigint(20) NOT NULL,
#                                             `answer_variants` varchar(255) DEFAULT NULL,
#                                             KEY `FK1ib698anrjf8jf4mowy7rm4x0` (`question_id`),
#                                             CONSTRAINT `FK1ib698anrjf8jf4mowy7rm4x0` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
#
# DROP TABLE IF EXISTS `student_answers`;
# /*!40101 SET @saved_cs_client     = @@character_set_client */;
# /*!50503 SET character_set_client = utf8mb4 */;
# CREATE TABLE `student_answers` (
#                                    `id` bigint(20) NOT NULL,
#                                    `test_id` bigint(20) DEFAULT NULL,
#                                    `user_id` bigint(20) DEFAULT NULL,
#                                    PRIMARY KEY (`id`),
#                                    KEY `FK2we7hld10nwt2q97wopn9qldl` (`test_id`),
#                                    KEY `FKguo24bsbo6lxw8ajkej694fg9` (`user_id`),
#                                    CONSTRAINT `FK2we7hld10nwt2q97wopn9qldl` FOREIGN KEY (`test_id`) REFERENCES `test` (`id`),
#                                    CONSTRAINT `FKguo24bsbo6lxw8ajkej694fg9` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
#
# DROP TABLE IF EXISTS `student_answers_result_list`;
# /*!40101 SET @saved_cs_client     = @@character_set_client */;
# /*!50503 SET character_set_client = utf8mb4 */;
# CREATE TABLE `student_answers_result_list` (
#                                                `student_answers_id` bigint(20) NOT NULL,
#                                                `result_list` varchar(255) DEFAULT NULL,
#                                                KEY `FKm3wimymff5ewovvj98q5ba42i` (`student_answers_id`),
#                                                CONSTRAINT `FKm3wimymff5ewovvj98q5ba42i` FOREIGN KEY (`student_answers_id`) REFERENCES `student_answers` (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
#
DROP TABLE IF EXISTS `study_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `study_subject` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `name` varchar(255) DEFAULT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# DROP TABLE IF EXISTS `test`;
# /*!40101 SET @saved_cs_client     = @@character_set_client */;
# /*!50503 SET character_set_client = utf8mb4 */;
# CREATE TABLE `test` (
#                         `id` bigint(20) NOT NULL,
#                         `student_result` double NOT NULL,
#                         `test_name` varchar(255) DEFAULT NULL,
#                         `study_subject_list_id` bigint(20) DEFAULT NULL,
#                         `user_id` bigint(20) DEFAULT NULL,
#                         PRIMARY KEY (`id`),
#                         KEY `FK909lrryui9e43ypveigxlsxy5` (`study_subject_list_id`),
#                         KEY `FKswx8tgvx4biygw60uqgjjq7a7` (`user_id`),
#                         CONSTRAINT `FK909lrryui9e43ypveigxlsxy5` FOREIGN KEY (`study_subject_list_id`) REFERENCES `study_subject` (`id`),
#                         CONSTRAINT `FKswx8tgvx4biygw60uqgjjq7a7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
#
# DROP TABLE IF EXISTS `test_question_list`;
# /*!40101 SET @saved_cs_client     = @@character_set_client */;
# /*!50503 SET character_set_client = utf8mb4 */;
# CREATE TABLE `test_question_list` (
#                                       `test_id` bigint(20) NOT NULL,
#                                       `question_list_id` bigint(20) NOT NULL,
#                                       UNIQUE KEY `UK_5pyajb2y3j9nqx8hxadbkny2t` (`question_list_id`),
#                                       KEY `FKd80deomvcgvssbpoinr70bn17` (`test_id`),
#                                       CONSTRAINT `FK55ylhvyg5pqkw251np1f1h0rx` FOREIGN KEY (`question_list_id`) REFERENCES `question` (`id`),
#                                       CONSTRAINT `FKd80deomvcgvssbpoinr70bn17` FOREIGN KEY (`test_id`) REFERENCES `test` (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
#
DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        `group_id` bigint(20) DEFAULT NULL,
                        `study_subject_id` bigint(20) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        KEY `FKq26f3bgxxep4iicdrkgldrkic` (`group_id`),
                        KEY `FKhkcskfdffp2phnxhml91jlfgm` (`study_subject_id`),
                        CONSTRAINT `FKhkcskfdffp2phnxhml91jlfgm` FOREIGN KEY (`study_subject_id`) REFERENCES `study_subject` (`id`),
                        CONSTRAINT `FKq26f3bgxxep4iicdrkgldrkic` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# DROP TABLE IF EXISTS `user_group_list`;
# /*!40101 SET @saved_cs_client     = @@character_set_client */;
# /*!50503 SET character_set_client = utf8mb4 */;
# CREATE TABLE `user_group_list` (
#                                    `user_id` bigint(20) NOT NULL,
#                                    `group_list_id` bigint(20) NOT NULL,
#                                    UNIQUE KEY `UK_eyqfhu0mu04jueju7sl2gmso7` (`group_list_id`),
#                                    KEY `FK2pfjnhtk0rk4sb23nopwscl7p` (`user_id`),
#                                    CONSTRAINT `FK2pfjnhtk0rk4sb23nopwscl7p` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
#                                    CONSTRAINT `FKdn8l35ekp9fi2gonxwbnvftwi` FOREIGN KEY (`group_list_id`) REFERENCES `groups` (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# DROP TABLE IF EXISTS `user_statistic`;
# /*!40101 SET @saved_cs_client     = @@character_set_client */;
# /*!50503 SET character_set_client = utf8mb4 */;
# CREATE TABLE `user_statistic` (
#                                   `id` bigint(20) NOT NULL,
#                                   `test_status` varchar(255) DEFAULT NULL,
#                                   `user_test_score` double NOT NULL,
#                                   `test_id` bigint(20) DEFAULT NULL,
#                                   `user_id` bigint(20) DEFAULT NULL,
#                                   PRIMARY KEY (`id`),
#                                   KEY `FKqluyi4ly7v60c3dnj6gwlg3bf` (`test_id`),
#                                   KEY `FK359u30e5cnosyqydmmm4mcgu8` (`user_id`),
#                                   CONSTRAINT `FK359u30e5cnosyqydmmm4mcgu8` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
#                                   CONSTRAINT `FKqluyi4ly7v60c3dnj6gwlg3bf` FOREIGN KEY (`test_id`) REFERENCES `test` (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
#
# DROP TABLE IF EXISTS `user_user_roles`;
# /*!40101 SET @saved_cs_client     = @@character_set_client */;
# /*!50503 SET character_set_client = utf8mb4 */;
# CREATE TABLE `user_user_roles` (
#                                    `user_id` bigint(20) NOT NULL,
#                                    `user_roles` varchar(255) DEFAULT NULL,
#                                    KEY `FKhxmdhadm01kwqo2lvyv8l8ho7` (`user_id`),
#                                    CONSTRAINT `FKhxmdhadm01kwqo2lvyv8l8ho7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=latin1;