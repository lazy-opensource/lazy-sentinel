/*
Navicat MySQL Data Transfer

Source Server         : 10.73.129.169_3306
Source Server Version : 50717
Source Host           : 10.73.129.169:3306
Source Database       : b2b_api_oauth2

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-18 08:58:41
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
-- Records of t_app_info
-- ----------------------------
INSERT INTO `t_app_info` VALUES ('402478777487065088', '苏果API', '127.0.0.1', null, 'Y', '2018-01-15 15:06:58', '2018-01-15 15:06:58');

-- ----------------------------
-- Table structure for t_client_info
-- ----------------------------
DROP TABLE IF EXISTS `t_client_info`;
CREATE TABLE `t_client_info` (
  `id` bigint(40) NOT NULL COMMENT '主键',
  `cli_name` varchar(40) NOT NULL COMMENT '对接客户端名称',
  `cli_id` varchar(60) NOT NULL COMMENT '对接客户端id',
  `cli_secret` varchar(60) NOT NULL COMMENT '客户端安全key',
  `cli_ip` varchar(30) DEFAULT NULL COMMENT '客户端ip',
  `valid_status` varchar(5) NOT NULL DEFAULT 'Y' COMMENT '有效状态 Y:有效 N:无效、停用',
  `use_status` varchar(5) NOT NULL DEFAULT 'N' COMMENT '使用状态：Y：使用过 N：从未使用',
  `init_refresh_token_count` int(5) DEFAULT NULL COMMENT '一个周期内已经初始化refresh_token的次数',
  `init_refresh_token_begin_time` datetime DEFAULT NULL COMMENT '新的生命周期初始化refresh_token开始时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_client_info_client_id` (`cli_id`,`cli_ip`) USING BTREE COMMENT '客户端Id + 客户端ip唯一，不可重复'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_client_info
-- ----------------------------
INSERT INTO `t_client_info` VALUES ('402416186358235136', '苏果API', 'a7410047a02b4ac1a215a549cd2454a4', 'd519d7f0f7044258aa912c204511a203', '127.0.0.1', 'Y', 'Y', '70', '2018-01-15 12:27:25', '2018-01-15 11:07:32', '2018-01-15 16:29:57');

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
-- Records of t_client_resource_rel
-- ----------------------------
INSERT INTO `t_client_resource_rel` VALUES ('402422296431558656', '402416186358235136', '402421745052549120', 'Y', '2018-01-15 11:32:23', '2018-01-15 11:32:23');

-- ----------------------------
-- Table structure for t_example
-- ----------------------------
DROP TABLE IF EXISTS `t_example`;
CREATE TABLE `t_example` (
  `id` bigint(40) NOT NULL COMMENT '主键',
  `name` varchar(30) NOT NULL COMMENT '名称',
  `age` int(5) NOT NULL COMMENT '年龄',
  `valid_status` varchar(5) NOT NULL DEFAULT 'Y' COMMENT '有效状态 Y:有效 N:无效、停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_example
-- ----------------------------
INSERT INTO `t_example` VALUES ('401874931366756352', 'hello', '32', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402112091969814528', '66666', '50', 'Y', '2018-01-15 10:40:20', '2018-01-15 10:40:20');
INSERT INTO `t_example` VALUES ('402199105352237056', 'lzy-1', '26', 'Y', '2018-01-15 10:47:45', '2018-01-15 10:47:45');
INSERT INTO `t_example` VALUES ('402405083905523712', 'youhairu', '73', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084337537024', 'lisise', '88', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084350119936', 'lizhangh', '31', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084366897152', 'hello', '98', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084379480064', 'lzy-2', '26', 'Y', '2018-01-15 10:47:45', '2018-01-15 10:47:45');
INSERT INTO `t_example` VALUES ('402405084421423104', 'haoli', '77', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084438200320', 'test', '52', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084446588928', 'sdife', '27', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084454977536', 'niha', '54', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084475949056', 'zhangsan', '56', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084484337664', 'sdife', '29', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084492726272', 'test', '32', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084501114880', 'niha', '28', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084517892096', 'weusxin', '17', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084526280704', 'yxse', '79', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084538863616', 'niha', '78', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084559835136', 'liyun', '30', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084568223744', 'zhaowu', '28', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084576612352', 'aiebe', '5', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084585000960', 'liyun', '97', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084593389568', 'sdife', '60', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084601778176', 'haoli', '62', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084610166784', 'tcltt', '99', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084618555392', 'yxse', '72', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084626944000', 'haoli', '75', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084635332608', 'zhaoliu', '10', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084643721216', 'haoli', '73', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084652109824', 'weusxin', '71', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084664692736', 'wangwu', '87', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084673081344', 'youhairu', '17', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084681469952', 'niha', '46', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084689858560', 'sdife', '10', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084702441472', 'lizhangh', '13', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084710830080', 'test', '30', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084719218688', 'lizhangh', '42', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084727607296', 'smith', '79', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084735995904', 'weusxin', '49', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084744384512', 'liyun', '92', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084752773120', 'lizhangh', '21', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084756967424', 'youhairu', '52', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084765356032', 'yxse', '94', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084769550336', 'wangwu', '55', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084777938944', 'youhairu', '71', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084790521856', 'hello', '62', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084798910464', 'wangwu', '18', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084807299072', 'youhairu', '53', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084811493376', 'tcltt', '64', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084819881984', 'hello', '18', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084828270592', 'lizhangh', '17', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084836659200', 'aiebe', '93', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084845047808', 'zhaowu', '76', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084853436416', 'niha', '50', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084861825024', 'youhairu', '46', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084870213632', 'zhaoliu', '87', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084878602240', 'lisise', '96', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084891185152', 'hello', '28', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084899573760', 'lisi', '3', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084912156672', 'zhaowu', '86', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084920545280', 'asdiex', '94', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084928933888', 'lisise', '51', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084937322496', 'kudvisdf', '60', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084945711104', 'zhaoliu', '46', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084954099712', 'weusxin', '93', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084958294016', 'haoli', '64', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084966682624', 'haoli', '25', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084975071232', 'smith', '48', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084983459840', 'wangwu', '44', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405084991848448', 'test', '94', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085000237056', 'test', '55', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085008625664', 'zhangsan', '73', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085017014272', 'aiebe', '87', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085025402880', 'zhaowu', '97', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085029597184', 'niha', '38', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085037985792', 'lisi', '3', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085046374400', 'weusxin', '74', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085054763008', 'sdife', '43', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085063151616', 'zhaoliu', '7', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085067345920', 'sdife', '13', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085075734528', 'yxse', '64', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085079928832', 'wangwu', '59', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085088317440', 'lisi', '92', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085092511744', 'tcltt', '34', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085100900352', 'smith', '48', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085109288960', 'zhaoliu', '93', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085113483264', 'smith', '37', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085126066176', 'tcltt', '43', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085130260480', 'niha', '17', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085138649088', 'hello', '85', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085142843392', 'yxse', '13', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085151232000', 'test', '32', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085159620608', 'wangwu', '82', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085163814912', 'tcltt', '56', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085172203520', 'sdife', '94', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085180592128', 'tcltt', '15', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085184786432', 'zhaowu', '75', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085193175040', 'lisi', '9', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085201563648', 'test', '6', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085205757952', 'asdiex', '13', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085214146560', 'lisi', '28', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402405085218340864', 'yxse', '82', 'Y', '2018-01-15 10:14:08', '2018-01-15 10:14:08');
INSERT INTO `t_example` VALUES ('402410474982866944', 'zhangsan', '50', 'Y', '2018-01-15 10:35:33', '2018-01-15 10:35:33');
INSERT INTO `t_example` VALUES ('402412325253939200', 'lzy-2', '26', 'Y', '2018-01-15 10:42:54', '2018-01-15 10:42:54');
INSERT INTO `t_example` VALUES ('402412325258133504', 'lzy-3', '26', 'Y', '2018-01-15 10:42:54', '2018-01-15 10:42:54');
INSERT INTO `t_example` VALUES ('402412435824181248', 'lzy-2', '26', 'Y', '2018-01-15 10:43:21', '2018-01-15 10:43:21');
INSERT INTO `t_example` VALUES ('402412435824181249', 'lzy-3', '26', 'Y', '2018-01-15 10:43:21', '2018-01-15 10:43:21');
INSERT INTO `t_example` VALUES ('402413546270359552', 'lzy-3', '26', 'Y', '2018-01-15 10:47:45', '2018-01-15 10:47:45');

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
-- Records of t_resource
-- ----------------------------
INSERT INTO `t_resource` VALUES ('402421745052549120', '查询库存', 'QUERY.STOCK', null, null, '402421008436297728', '402421008436297728', 'Y', '2018-01-15 11:20:20', '2018-01-15 11:20:20');

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
-- Records of t_resource_type
-- ----------------------------
INSERT INTO `t_resource_type` VALUES ('402419775596134400', '接口', 'URI', 'Y', '2018-01-15 11:16:12', '2018-01-15 11:16:12');

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
-- Records of t_system_type
-- ----------------------------
INSERT INTO `t_system_type` VALUES ('402421008436297728', '苏果API', 'SG-API', 'Y', '2018-01-15 11:17:25', '2018-01-15 11:17:25');

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

-- ----------------------------
-- Records of t_token_cycle_rule
-- ----------------------------
INSERT INTO `t_token_cycle_rule` VALUES ('402438516413300736', '30', '202', '35', '604800', '15552000', 'Y', '2018-01-18 08:57:47', '2018-01-18 08:57:47');
