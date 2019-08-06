/*INSERT INTO `users` VALUES ('aa', '$2a$10$hU3Z/H1SCLfBfRgQNFLRxOZewFknSU2s8inoM0K8vdSpv6E.TcnmG', '1');
INSERT INTO `users` VALUES ('bb', '$2a$10$7GVnC55OYsg3d36T7MKZ7e6aZBX6fNMt9ATCG6sa1tHbU6r3pRSc2', '1');
INSERT INTO `authorities` VALUES ('aa', 'ROLE_USER');
INSERT INTO `authorities` VALUES ('bb', 'ROLE_ADMIN');*/
/*INSERT INTO `user_domain` VALUES ('1', '$2a$10$WwwlPRrhQTN10jjw/9yqa.1xz9rsg8/4InZe97pxvo.LO4XnsrFga', 'ROLE_ADMIN', 'admin');
INSERT INTO `user_domain` VALUES ('2', '$2a$10$MEQ6x.gEaZfmPTfvp5I/T.ALR3LW7qgf5AEDblDtgcaKlJT9A6kkq', 'ROLE_USER', 'user');
*/

--初始化role表数据
INSERT INTO role (name,description,rid) values ('ROLE_ADMIN','管理员','1');
INSERT INTO role (name,description,rid) values ('ROLE_USER','普通用户',2);


--初始化user domain
INSERT INTO `user_domain` VALUES ('1', '$2a$10$WwwlPRrhQTN10jjw/9yqa.1xz9rsg8/4InZe97pxvo.LO4XnsrFga', 'admin');
INSERT INTO `user_domain` VALUES ('2', '$2a$10$MEQ6x.gEaZfmPTfvp5I/T.ALR3LW7qgf5AEDblDtgcaKlJT9A6kkq', 'user');
INSERT INTO `user_domain` VALUES ('3', '$2a$10$MEQ6x.gEaZfmPTfvp5I/T.ALR3LW7qgf5AEDblDtgcaKlJT9A6kkq', 'user2');

--给user 配置role
INSERT INTO `user_role`(uid, rid) VALUES ('1','1');
INSERT INTO `user_role`(uid, rid) VALUES ('2','2');
INSERT INTO `user_role`(uid, rid) VALUES ('3','2');

--初始化所有接口
INSERT INTO `permission`(pid, url) VALUES ('1','/user');
INSERT INTO `permission`(pid, url) VALUES ('2','/admin');
INSERT INTO `permission`(pid, url) VALUES ('3','/aa');
INSERT INTO `permission`(pid, url) VALUES ('4','/bb');
INSERT INTO `permission`(pid, url) VALUES ('5','/');

--动态授权
INSERT INTO `role_permission`(pid, rid) VALUES ('1','1');
INSERT INTO `role_permission`(pid, rid) VALUES ('2','1');
INSERT INTO `role_permission`(pid, rid) VALUES ('3','1');
INSERT INTO `role_permission`(pid, rid) VALUES ('4','1');
INSERT INTO `role_permission`(pid, rid) VALUES ('1','2');
INSERT INTO `role_permission`(pid, rid) VALUES ('3','2');
INSERT INTO `role_permission`(pid, rid) VALUES ('5','1');
INSERT INTO `role_permission`(pid, rid) VALUES ('5','2');

