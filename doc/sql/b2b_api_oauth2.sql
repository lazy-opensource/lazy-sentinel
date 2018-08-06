/*
Navicat MySQL Data Transfer

Source Server         : 10.73.129.169_3306
Source Server Version : 50717
Source Host           : 10.73.129.169:3306
Source Database       : b2b_api_oauth2

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-03-21 14:47:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_app_info
-- ----------------------------
DROP TABLE IF EXISTS `t_app_info`;
CREATE TABLE `t_app_info` (
  `id` bigint(40) NOT NULL COMMENT '主键',
  `app_name` varchar(50) NOT NULL COMMENT '应用名称',
  `app_ip` varchar(40) NOT NULL COMMENT 'ip',
  `app_domain` varchar(40) DEFAULT NULL COMMENT '域名',
  `valid_status` varchar(5) NOT NULL DEFAULT 'Y' COMMENT '有效状态 Y:有效 N:无效、停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_app_info_ip` (`app_ip`) USING BTREE COMMENT 'ip字段唯一，不可重复'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_client_info
-- ----------------------------
DROP TABLE IF EXISTS `t_client_info`;
CREATE TABLE `t_client_info` (
  `id` bigint(40) NOT NULL COMMENT '主键',
  `cli_name` varchar(40) NOT NULL COMMENT '对接客户端名称',
  `cli_id` varchar(60) NOT NULL COMMENT '对接客户端id',
  `cli_secret` varchar(60) NOT NULL COMMENT '客户端安全key',
  `valid_status` varchar(5) NOT NULL DEFAULT 'Y' COMMENT '有效状态 Y:有效 N:无效、停用',
  `use_status` varchar(5) NOT NULL DEFAULT 'N' COMMENT '使用状态：Y：使用过 N：从未使用',
  `init_refresh_token_count` int(5) DEFAULT NULL COMMENT '一个周期内已经初始化refresh_token的次数',
  `init_refresh_token_begin_time` datetime DEFAULT NULL COMMENT '新的生命周期初始化refresh_token开始时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_client_info_client_id` (`cli_id`) USING BTREE COMMENT '客户端Id + 客户端ip唯一，不可重复'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_client_ip_info
-- ----------------------------
DROP TABLE IF EXISTS `t_client_ip_info`;
CREATE TABLE `t_client_ip_info` (
  `id` bigint(40) NOT NULL COMMENT '主键',
  `domain` varchar(30) NOT NULL DEFAULT '' COMMENT '域名',
  `ip` varchar(30) NOT NULL DEFAULT '' COMMENT 'ip地址',
  `cli_id` varchar(60) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `valid_status` varchar(5) NOT NULL DEFAULT 'Y' COMMENT '数据有效性',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_client_resource_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_client_resource_rel`;
CREATE TABLE `t_client_resource_rel` (
  `id` bigint(40) NOT NULL COMMENT '主键',
  `cli_id` bigint(40) NOT NULL COMMENT '客户端主键',
  `res_id` bigint(40) NOT NULL COMMENT '资源表主键',
  `valid_status` varchar(5) NOT NULL DEFAULT 'Y' COMMENT '有效状态 Y:有效 N:无效、停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_cli_res_rel` (`cli_id`,`res_id`) USING BTREE COMMENT '关联关系不可重复'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_example
-- ----------------------------
DROP TABLE IF EXISTS `t_example`;
CREATE TABLE `t_example` (
  `id` bigint(40) NOT NULL COMMENT '主键',
  `name` varchar(30) NOT NULL COMMENT '名称',
  `age` int(5) NOT NULL COMMENT '年龄',
  `code` varchar(40) NOT NULL,
  `valid_status` varchar(5) NOT NULL DEFAULT 'Y' COMMENT '有效状态 Y:有效 N:无效、停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_ex_code` (`name`,`code`) USING BTREE COMMENT 'code + name 必须唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource` (
  `id` bigint(40) NOT NULL COMMENT '主键',
  `res_name` varchar(60) NOT NULL COMMENT '资源名称',
  `res_code` varchar(60) NOT NULL COMMENT '资源编码',
  `res_serve_name` varchar(60) DEFAULT NULL COMMENT '资源服务名称',
  `res_uri` varchar(100) DEFAULT NULL COMMENT '服务URI',
  `own_sys_id` bigint(40) NOT NULL COMMENT '资源所属系统表主键',
  `res_type_id` bigint(40) NOT NULL COMMENT '资源类型表主键',
  `valid_status` varchar(5) NOT NULL DEFAULT 'Y' COMMENT '有效状态 Y:有效 N:无效、停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_res_code` (`res_code`,`own_sys_id`) USING BTREE COMMENT '资源'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_resource_type
-- ----------------------------
DROP TABLE IF EXISTS `t_resource_type`;
CREATE TABLE `t_resource_type` (
  `id` bigint(40) NOT NULL COMMENT '主键',
  `res_type_name` varchar(40) NOT NULL COMMENT '资源类型名称',
  `res_type_code` varchar(40) NOT NULL COMMENT '资源类型编码',
  `valid_status` varchar(5) NOT NULL DEFAULT 'Y' COMMENT '有效状态 Y:有效 N:无效、停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_resource_type_code` (`res_type_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_system_type
-- ----------------------------
DROP TABLE IF EXISTS `t_system_type`;
CREATE TABLE `t_system_type` (
  `id` bigint(40) NOT NULL,
  `sys_type_name` varchar(40) NOT NULL COMMENT '资源所属系统名称',
  `sys_type_code` varchar(40) NOT NULL COMMENT '资源所属系统编码',
  `valid_status` varchar(5) NOT NULL DEFAULT 'Y' COMMENT '有效状态 Y:有效 N:无效、停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_sys_type_code` (`sys_type_code`) USING BTREE COMMENT '系统类型编码唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_token_cycle_rule
-- ----------------------------
DROP TABLE IF EXISTS `t_token_cycle_rule`;
CREATE TABLE `t_token_cycle_rule` (
  `id` bigint(40) NOT NULL COMMENT '主键',
  `cycle` bigint(5) NOT NULL DEFAULT '30' COMMENT '配置token周期时间 单位:秒',
  `init_refresh_token_count` int(5) NOT NULL DEFAULT '202' COMMENT '在一个周期内初始化初始化refresh_token次数',
  `refresh_token_count` int(5) NOT NULL DEFAULT '35' COMMENT '在一个周期内刷新token次数',
  `token_expires` bigint(5) NOT NULL DEFAULT '604800' COMMENT '配置token有效期 单位:秒 默认7天',
  `refresh_token_expires` bigint(5) NOT NULL DEFAULT '15552000' COMMENT '配置refresh_token有效期 单位:秒 默认180天',
  `valid_status` varchar(5) NOT NULL DEFAULT 'Y' COMMENT '有效状态 Y:有效 N:无效、停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
