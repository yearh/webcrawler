CREATE TABLE `link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `page_id` int(11) NOT NULL DEFAULT '0',
  `url` varchar(250) NOT NULL,
  `lock_flag` int(1) NOT NULL DEFAULT '0',
  `status` int(2) NOT NULL DEFAULT '0',
  `update_time` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_link_url` (`url`)
);

CREATE TABLE `link_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_link_id` int(11) NOT NULL,
  `to_link_id` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

CREATE TABLE `page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(2) NOT NULL DEFAULT '0',
  `url` varchar(250) DEFAULT NULL,
  `content` longblob,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `url_UNIQUE` (`url`)
);