CREATE DATABASE `final_project_spring`;

INSERT INTO `final_project_spring`.`roles` (`id`, `role`) VALUES ('0', 'ROLE_USER');
INSERT INTO `final_project_spring`.`roles` (`id`, `role`) VALUES ('1', 'ROLE_MANAGER');
INSERT INTO `final_project_spring`.`roles` (`id`, `role`) VALUES ('2', 'ROLE_MASTER');

INSERT INTO `final_project_spring`.`statuses` (`id`, `status`) VALUES ('0', 'CURRENT_REQUEST');
INSERT INTO `final_project_spring`.`statuses` (`id`, `status`) VALUES ('1', 'ACCEPTED_REQUEST');
INSERT INTO `final_project_spring`.`statuses` (`id`, `status`) VALUES ('2', 'CLOSED_REQUEST');
INSERT INTO `final_project_spring`.`statuses` (`id`, `status`) VALUES ('3', 'DECLINED_REQUEST');