USE blog;

DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog`  (
                           `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                           `appreciation` BIT(1) NOT NULL,
                           `commentable` BIT(1) NOT NULL,
                           `content` LONGTEXT CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
                           `create_time` DATETIME NULL DEFAULT NULL,
                           `add_picture` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `flag` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `published` BIT(1) NOT NULL,
                           `recommend` BIT(1) NOT NULL,
                           `share_statement` BIT(1) NOT NULL,
                           `title` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `update_time` DATETIME NULL DEFAULT NULL,
                           `views` INT(11) NULL DEFAULT NULL,
                           `type_id` BIGINT(20) NULL DEFAULT NULL,
                           `user_id` BIGINT(20) NULL DEFAULT NULL,
                           PRIMARY KEY (`id`) USING BTREE,
                           INDEX (`type_id`) USING BTREE,
                           INDEX (`user_id`) USING BTREE,
                           CONSTRAINT FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                           CONSTRAINT FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = INNODB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
                              `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                              `nickname` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `email` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `content` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `avatar` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `create_time` DATETIME NULL DEFAULT NULL,
                              `blog_id` BIGINT(20) NULL DEFAULT NULL,
                              `parent_comment_id` BIGINT(20) NULL DEFAULT NULL,
                              PRIMARY KEY (`id`) USING BTREE,
                              CONSTRAINT `FK_t1` FOREIGN KEY (`parent_comment_id`) REFERENCES `t_comment` (`id`) ON DELETE CASCADE
) ENGINE = INNODB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`  (
                           `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                           `name` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `t-tag`;
CREATE TABLE `t-tag`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
    PRIMARY KEY (`id`) USING BTREE
)ENGINE = INNODB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
                           `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                           `avatar` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `create_time` DATETIME NULL DEFAULT NULL,
                           `email` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `nickname` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `password` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `type` INT(11) NULL DEFAULT NULL,
                           `update_time` DATETIME NULL DEFAULT NULL,
                           `username` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `t_blog_tags`;
CREATE TABLE `t_blog_tags`  (
                                `id` INT(11) NOT NULL AUTO_INCREMENT,
                                `tag_id` BIGINT(20) NULL DEFAULT NULL,
                                `blog_id` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC ;

SET FOREIGN_KEY_CHECKS = 1;