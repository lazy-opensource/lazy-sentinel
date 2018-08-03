/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50639
Source Host           : localhost:3306
Source Database       : lazy-sentinel

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-08-02 21:38:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_example
-- ----------------------------
DROP TABLE IF EXISTS `t_example`;
CREATE TABLE `t_example` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `valid_status` varchar(5) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` datetime NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_example
-- ----------------------------
INSERT INTO `t_example` VALUES ('1', 'Y', '2018-04-17 11:44:38', '2018-04-17 11:44:38', 'lisi', '29', '10594463@qq.com');
INSERT INTO `t_example` VALUES ('2', 'Y', '2018-04-17 11:44:38', '2018-04-17 11:44:38', 'chengxi', '38', '16566760594463@qq.com');
INSERT INTO `t_example` VALUES ('3', 'Y', '2018-04-17 11:44:38', '2018-04-17 11:44:38', 'chenghai', '21', '7866510594463@qq.com');
INSERT INTO `t_example` VALUES ('4', 'N', '2018-04-17 11:44:38', '2018-05-16 21:33:33', 'zhaoliu', '32', '1059477777463@qq.com');
INSERT INTO `t_example` VALUES ('5', 'Y', '2018-04-17 11:44:38', '2018-04-17 11:44:38', 'wangwu', '26', '10594463@qq.com');
INSERT INTO `t_example` VALUES ('6', 'Y', '2018-04-17 11:44:38', '2018-04-17 11:44:38', 'lisi', '25', '10594463@qq.com');

-- ----------------------------
-- Table structure for t_permissions_group
-- ----------------------------
DROP TABLE IF EXISTS `t_permissions_group`;
CREATE TABLE `t_permissions_group` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `pid` bigint(20) NOT NULL DEFAULT '-1' COMMENT '父级用户组id',
  `group_name` varchar(30) NOT NULL DEFAULT '' COMMENT '用户组名称',
  `group_code` varchar(40) NOT NULL DEFAULT '' COMMENT '用户组code',
  `valid_status` varchar(5) NOT NULL DEFAULT 'Y' COMMENT '数据有效状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_code` (`group_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限-用户组表';

-- ----------------------------
-- Records of t_permissions_group
-- ----------------------------
INSERT INTO `t_permissions_group` VALUES ('464819009519353856', '-1', '合隆总部', 'helongzongbu', 'Y', '2018-07-06 15:44:47', '2018-07-06 15:44:47');
INSERT INTO `t_permissions_group` VALUES ('464819593978839040', '464819009519353856', '工程部', 'gongchengbu', 'Y', '2018-07-06 15:47:07', '2018-07-06 15:47:07');
INSERT INTO `t_permissions_group` VALUES ('464819665877598208', '464819009519353856', '人事部', 'renshibu', 'Y', '2018-07-06 15:47:24', '2018-07-06 15:47:24');
INSERT INTO `t_permissions_group` VALUES ('464819718457393152', '464819009519353856', '物业部', 'wuyebu', 'Y', '2018-07-06 15:47:36', '2018-07-06 15:47:36');
INSERT INTO `t_permissions_group` VALUES ('464819786627416064', '464819009519353856', '环卫部', 'huanweibu', 'Y', '2018-07-06 15:47:53', '2018-07-06 15:47:53');
INSERT INTO `t_permissions_group` VALUES ('464819977229172736', '464819009519353856', '行政部', 'xingzhengbu', 'Y', '2018-07-06 15:48:38', '2018-07-06 15:48:38');
INSERT INTO `t_permissions_group` VALUES ('464820347250671616', '464819718457393152', '宝安环境园', 'baoanhuanjingyuan', 'Y', '2018-07-06 15:50:06', '2018-07-06 15:50:06');
INSERT INTO `t_permissions_group` VALUES ('464820487730495488', '464819786627416064', '南澳清运', 'nanaoqingyun', 'Y', '2018-07-06 15:50:40', '2018-07-06 15:50:40');
INSERT INTO `t_permissions_group` VALUES ('464821227593138176', '464820487730495488', '镇中心区三组', 'nanaoqingyun_zhenzhongxinqusanzu', 'Y', '2018-07-06 15:53:36', '2018-07-06 15:53:36');
INSERT INTO `t_permissions_group` VALUES ('464821346321301504', '464820487730495488', '社区五组', 'nanaoqingyun_shequwuzu', 'Y', '2018-07-06 15:54:04', '2018-07-06 15:54:04');
INSERT INTO `t_permissions_group` VALUES ('464821578819960832', '464820487730495488', '公厕组', 'nanaoqingyun_gongcezu', 'Y', '2018-07-06 15:55:00', '2018-07-06 15:55:00');
INSERT INTO `t_permissions_group` VALUES ('464821852506685440', '464820487730495488', '机动组', 'nanaoqingyun_jidongzu', 'Y', '2018-07-06 15:56:05', '2018-07-06 15:56:05');
INSERT INTO `t_permissions_group` VALUES ('464821954654765056', '464820487730495488', '司机组', 'nanaoqingyun_sijizu', 'Y', '2018-07-06 15:56:29', '2018-07-06 15:56:29');
INSERT INTO `t_permissions_group` VALUES ('464822088696332288', '464820487730495488', '行政组', 'nanaoqingyun_xingzhengzu', 'Y', '2018-07-06 15:57:01', '2018-07-06 15:57:01');
INSERT INTO `t_permissions_group` VALUES ('464822506688086016', '464820347250671616', '行政后勤组', 'baoanhuanjingyuan_xingzhenghouqinzu', 'Y', '2018-07-06 15:58:41', '2018-07-06 15:58:41');
INSERT INTO `t_permissions_group` VALUES ('464822575000715264', '464820347250671616', '秩序队', 'baoanhuanjingyuan_zhixudui', 'Y', '2018-07-06 15:58:57', '2018-07-06 15:58:57');
INSERT INTO `t_permissions_group` VALUES ('464822703421915136', '464820347250671616', '清洁绿化组', 'baoanhuanjingyuan_qingjielvhuazu', 'Y', '2018-07-06 15:59:28', '2018-07-06 15:59:28');
INSERT INTO `t_permissions_group` VALUES ('464822768228106240', '464820347250671616', '工程组', 'baoanhuanjingyuan_gongchengzu', 'Y', '2018-07-06 15:59:43', '2018-07-06 15:59:43');
INSERT INTO `t_permissions_group` VALUES ('466191316229816320', '464819786627416064', '坪山清运', 'pingshanqingyun', 'Y', '2018-07-10 10:37:51', '2018-07-10 10:37:51');
INSERT INTO `t_permissions_group` VALUES ('466191608409227264', '466191316229816320', '坪山街道', 'pingshanqingyun_pingshanjiedao', 'Y', '2018-07-10 10:39:00', '2018-07-10 10:39:00');
INSERT INTO `t_permissions_group` VALUES ('466191723408654336', '466191316229816320', '碧岭街道', 'pingshanqingyun_bilingjiedao', 'Y', '2018-07-10 10:39:28', '2018-07-10 10:39:28');
INSERT INTO `t_permissions_group` VALUES ('466192678770442240', '466191316229816320', '马峦街道', 'pingshanqingyun_maluanjiedao', 'Y', '2018-07-10 10:43:16', '2018-07-10 10:43:16');
INSERT INTO `t_permissions_group` VALUES ('466192870391414784', '466191316229816320', '石井街道', 'pingshanqingyun_shijingjiedao', 'Y', '2018-07-10 10:44:01', '2018-07-10 10:44:01');
INSERT INTO `t_permissions_group` VALUES ('466197751298785280', '466191316229816320', '大件垃圾清运', 'pingshanqingyun_dajianlajiqingyun', 'Y', '2018-07-10 11:03:25', '2018-07-10 11:03:25');
INSERT INTO `t_permissions_group` VALUES ('466283549301932032', '464819786627416064', '马峦清运', 'maluanqingyun', 'Y', '2018-07-10 16:44:21', '2018-07-10 16:44:21');
INSERT INTO `t_permissions_group` VALUES ('466300560908222464', '466283549301932032', '清扫一组', 'maluanqingyun_qingsaoyizu', 'Y', '2018-07-10 17:51:57', '2018-07-10 17:51:57');
INSERT INTO `t_permissions_group` VALUES ('466300655338782720', '466283549301932032', '清扫二组', 'maluanqingyun_qingsaoerzu', 'Y', '2018-07-10 17:52:19', '2018-07-10 17:52:19');
INSERT INTO `t_permissions_group` VALUES ('466321014507175936', '466283549301932032', '清运-司机组', 'maluanqingyun_sijizu', 'Y', '2018-07-10 19:13:13', '2018-07-10 19:13:13');
INSERT INTO `t_permissions_group` VALUES ('466321243130298368', '466283549301932032', '清运-站点组', 'maluanqingyun_zhandianzu', 'Y', '2018-07-10 19:14:08', '2018-07-10 19:14:08');
INSERT INTO `t_permissions_group` VALUES ('466321398114025472', '466283549301932032', '清运-行政组', 'maluanqingyun_xingzhengzu', 'Y', '2018-07-10 19:14:45', '2018-07-10 19:14:45');

-- ----------------------------
-- Table structure for t_permissions_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_permissions_resource`;
CREATE TABLE `t_permissions_resource` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `pid` bigint(20) NOT NULL DEFAULT '-1' COMMENT '父级资源',
  `res_name` varchar(50) NOT NULL DEFAULT '' COMMENT '资源名称',
  `res_desc` varchar(50) NOT NULL DEFAULT '' COMMENT '资源描述',
  `res_code` varchar(60) NOT NULL DEFAULT '' COMMENT '资源编码',
  `res_order` int(5) NOT NULL DEFAULT '0' COMMENT '排序',
  `method` varchar(10) NOT NULL DEFAULT '' COMMENT '请求方式',
  `icon` varchar(50) NOT NULL DEFAULT '&#xe62d;' COMMENT '资源图标',
  `uri` varchar(100) NOT NULL DEFAULT '' COMMENT '资源URI',
  `open_window` varchar(5) NOT NULL DEFAULT 'N' COMMENT '是否打开新窗口',
  `res_type` varchar(20) NOT NULL DEFAULT 'menu' COMMENT '资源类型menu:菜单uri:后台路由',
  `valid_status` varchar(20) NOT NULL DEFAULT 'Y' COMMENT '数据有效性',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_res_code` (`res_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限-权限资源表';

-- ----------------------------
-- Records of t_permissions_resource
-- ----------------------------
INSERT INTO `t_permissions_resource` VALUES ('1', '-1', '系统设置', '系统设置导航菜单', 'nav_menu_permissions', '200', '', '&#xe62d;', '', 'N', 'menu', 'Y', '2018-04-11 23:13:08', '2018-04-11 23:13:08');
INSERT INTO `t_permissions_resource` VALUES ('2', '1', '系统设置', '系统设置一级菜单', 'menu_permissions_sys', '0', '', '&#xe62d;', '', 'N', 'menu', 'Y', '2018-04-11 23:13:21', '2018-04-11 23:13:21');
INSERT INTO `t_permissions_resource` VALUES ('3', '2', '管理员配置', '后台管理员', 'menu_permissions_sys_admin', '0', '', '&#xe62d;', '/permissions/user/list', 'N', 'menu', 'Y', '2018-04-11 23:14:22', '2018-04-11 23:14:22');
INSERT INTO `t_permissions_resource` VALUES ('4', '2', '角色管理', '角色配置', 'menu_permissions_sys_role', '0', '', '&#xe62d;', '/permissions/role/list', 'N', 'menu', 'Y', '2018-04-11 23:14:49', '2018-04-11 23:14:49');
INSERT INTO `t_permissions_resource` VALUES ('5', '2', '权限管理', '资源权限设置', 'menu_permissions_sys_resource', '0', '', '&#xe62d;', '/permissions/resource/list', 'N', 'menu', 'Y', '2018-04-11 23:14:56', '2018-04-11 23:14:56');
INSERT INTO `t_permissions_resource` VALUES ('434734723189702656', '-1', '项目管理', '项目管理导航菜单', 'nav_menu_projects', '197', '', '', '', 'N', 'menu', 'Y', '2018-04-14 15:20:34', '2018-05-15 19:45:56');
INSERT INTO `t_permissions_resource` VALUES ('434737187070672896', '434734723189702656', '项目管理', '项目管理-项目管理', 'menu_projects_projects', '100', '', '', '', 'N', 'menu', 'Y', '2018-04-14 15:30:22', '2018-04-14 15:30:22');
INSERT INTO `t_permissions_resource` VALUES ('434738851383083008', '434737187070672896', '项目管理', '项目管理-项目管理-项目管理', 'menu_projects_projects_project', '100', '', '', '/projects/project/list', 'N', 'menu', 'Y', '2018-04-14 15:36:59', '2018-04-14 15:36:59');
INSERT INTO `t_permissions_resource` VALUES ('434739230539776000', '-1', '车辆管理', '车辆管理导航菜单', 'nav_menu_cars', '194', '', '', '', 'N', 'menu', 'Y', '2018-04-14 15:38:29', '2018-05-15 19:47:00');
INSERT INTO `t_permissions_resource` VALUES ('434739598573174784', '434739230539776000', '车辆管理', '车辆管理-车辆管理', 'menu_cars_cars', '100', '', '', '', 'N', 'menu', 'Y', '2018-04-14 15:39:57', '2018-04-14 15:39:57');
INSERT INTO `t_permissions_resource` VALUES ('434739982310047744', '434739598573174784', '车辆管理', '车辆管理-车辆管理-车辆管理', 'menu_cars_cars_car', '100', '', '', '/cars/car/list', 'N', 'menu', 'Y', '2018-04-14 15:41:28', '2018-04-14 15:41:28');
INSERT INTO `t_permissions_resource` VALUES ('434740766938497024', '-1', '人员管理', '人员管理导航菜单', 'nav_menu_employees', '195', '', '', '', 'N', 'menu', 'Y', '2018-04-14 15:44:35', '2018-05-15 19:46:35');
INSERT INTO `t_permissions_resource` VALUES ('434742060482822144', '434740766938497024', '人员管理', '人员管理-人员管理', 'menu_employees_employees', '100', '', '', '', 'N', 'menu', 'Y', '2018-04-14 15:49:44', '2018-04-14 15:49:44');
INSERT INTO `t_permissions_resource` VALUES ('434742673841061888', '441709645522796544', '员工管理', '项目管理-人事管理-员工管理', 'menu_projects_hr_employee', '100', '', '', '/employees/employee/list', 'N', 'menu', 'Y', '2018-04-14 15:52:10', '2018-04-14 15:52:10');
INSERT INTO `t_permissions_resource` VALUES ('434748331961352192', '434739598573174784', '围栏设置', '车辆管理-车辆管理-围栏设置', 'menu_cars_cars_fence', '100', '', '', '/cars/fence/list', 'N', 'menu', 'Y', '2018-04-14 16:14:39', '2018-04-14 16:14:39');
INSERT INTO `t_permissions_resource` VALUES ('434748752922673152', '434739598573174784', '线路管理', '车辆管理-车辆管理-线路管理', 'menu_cars_cars_route', '100', '', '', '/cars/route/list', 'N', 'menu', 'Y', '2018-04-14 16:16:19', '2018-04-14 16:16:19');
INSERT INTO `t_permissions_resource` VALUES ('434749113242746880', '434752726371401728', '车辆里程报表', '车辆管理-报表中心-车辆里程报表', 'menu_cars_reports_mile', '100', '', '', '/cars/mile/list', 'N', 'menu', 'Y', '2018-04-14 16:17:45', '2018-04-14 16:17:45');
INSERT INTO `t_permissions_resource` VALUES ('434749497046728704', '434752726371401728', '车辆油耗曲线', '车辆管理-报表中心-车辆油耗曲线', 'menu_cars_reports_oil', '100', '', '', '/cars/oil/list', 'N', 'menu', 'Y', '2018-04-14 16:19:17', '2018-04-14 16:19:17');
INSERT INTO `t_permissions_resource` VALUES ('434751632819879936', '434752726371401728', '车辆违规信息', '车辆管理-报表中心-车辆违规信息', 'menu_cars_reports_illegal', '100', '', '', '/cars/illegal/list', 'N', 'menu', 'Y', '2018-04-14 16:27:46', '2018-04-14 16:27:46');
INSERT INTO `t_permissions_resource` VALUES ('434752726371401728', '434739230539776000', '报表中心', '车辆管理-报表中心', 'menu_cars_reports', '100', '', '', '', 'N', 'menu', 'Y', '2018-04-14 16:32:07', '2018-04-14 16:32:07');
INSERT INTO `t_permissions_resource` VALUES ('434758918527254528', '-1', '街道清扫', 'app端菜单-街道清扫', 'app_menu_JieDaoQingSao', '100', '', '', '', 'N', 'menu', 'Y', '2018-04-14 16:56:43', '2018-04-14 16:56:43');
INSERT INTO `t_permissions_resource` VALUES ('434759909502877696', '441709645522796544', '岗位管理', '项目管理-人事管理-岗位管理', 'menu_projects_hr_job', '100', '', '', '/employees/job/list', 'N', 'menu', 'Y', '2018-04-14 17:00:39', '2018-04-14 17:00:39');
INSERT INTO `t_permissions_resource` VALUES ('434761338422558720', '434742060482822144', '围栏管理', '人员管理-人员管理-围栏管理', 'menu_employees_employees_fence', '100', '', '', '/employees/fence/list', 'N', 'menu', 'Y', '2018-04-14 17:06:20', '2018-04-14 17:06:20');
INSERT INTO `t_permissions_resource` VALUES ('434762251837112320', '434762570218340352', '巡检情况', '人员管理-报表中心-巡检情况', 'menu_employees_reports_check', '100', '', '', '/employees/check/list', 'N', 'menu', 'Y', '2018-04-14 17:09:58', '2018-04-14 17:09:58');
INSERT INTO `t_permissions_resource` VALUES ('434762570218340352', '434740766938497024', '报表中心', '人员管理-报表中心', 'menu_employees_reports', '100', '', '', '', 'N', 'menu', 'Y', '2018-04-14 17:11:14', '2018-04-14 17:11:14');
INSERT INTO `t_permissions_resource` VALUES ('434763520723124224', '434762570218340352', '考勤报表', '人员管理-报表中心-考勤报表', 'menu_employees_reports_attend', '100', '', '', '/employees/attend/list', 'N', 'menu', 'Y', '2018-04-14 17:15:00', '2018-04-14 17:15:00');
INSERT INTO `t_permissions_resource` VALUES ('434763923162398720', '434762570218340352', '违规信息', '人员管理-报表中心-违规信息', 'menu_employees_reports_illegal', '100', '', '', '/employees/illegal/list', 'N', 'menu', 'Y', '2018-04-14 17:16:36', '2018-04-14 17:16:36');
INSERT INTO `t_permissions_resource` VALUES ('435815397527126016', '-1', '设施管理', '设施管理导航菜单', 'nav_menu_facilitys', '196', '', '', '', 'N', 'menu', 'Y', '2018-04-17 14:54:47', '2018-05-15 19:46:15');
INSERT INTO `t_permissions_resource` VALUES ('435816974342160384', '435815397527126016', '设施管理', '设施管理-设施管理', 'menu_facilitys_facilitys', '100', '', '', '', 'N', 'menu', 'Y', '2018-04-17 15:01:03', '2018-04-17 15:01:03');
INSERT INTO `t_permissions_resource` VALUES ('435817582738538496', '435816974342160384', '转运箱管理', '设施管理-设施管理-转运箱管理', 'menu_facilitys_facilitys_transbox', '100', '', '', '/facilitys/transbox/list', 'N', 'menu', 'Y', '2018-04-17 15:03:28', '2018-04-17 15:03:28');
INSERT INTO `t_permissions_resource` VALUES ('435919652141400064', '435816974342160384', '垃圾箱管理', '设施管理-设施管理-垃圾箱管理', 'menu_facilitys_facilitys_garbagebox', '100', '', '', '/facilitys/garbagebox/list', 'N', 'menu', 'Y', '2018-04-17 21:49:04', '2018-04-17 21:49:04');
INSERT INTO `t_permissions_resource` VALUES ('436539514479968256', '435816974342160384', '公厕管理', '设施管理-设施管理-公厕管理', 'menu_facilitys_facilitys_toilet', '100', '', '', '/facilitys/toilet/list', 'N', 'menu', 'Y', '2018-04-19 14:52:10', '2018-04-19 14:52:10');
INSERT INTO `t_permissions_resource` VALUES ('436539858660360192', '435816974342160384', '中转站管理', '设施管理-设施管理-中转站管理', 'menu_facilitys_facilitys_transferstation', '100', '', '', '/facilitys/transferstation/list', 'N', 'menu', 'Y', '2018-04-19 14:53:32', '2018-04-19 14:53:32');
INSERT INTO `t_permissions_resource` VALUES ('436584333541638144', '435815397527126016', '报表中心', '设施管理-报表中心', 'menu_facilitys_reports', '100', '', '', '', 'N', 'menu', 'Y', '2018-04-19 17:50:16', '2018-04-19 17:50:16');
INSERT INTO `t_permissions_resource` VALUES ('436584895695814656', '436584333541638144', '公厕消杀', '设施管理-报表中心-公厕消杀', 'menu_facilitys_reports_antivirus', '100', '', '', '/facilitys/toilet_antivirus_record/list', 'N', 'menu', 'Y', '2018-04-19 17:52:30', '2018-04-19 17:52:30');
INSERT INTO `t_permissions_resource` VALUES ('437033786681589760', '436584333541638144', '中转站装箱统计', '设施管理-报表中心-中转站装箱统计', 'menu_facilitys_reports_transreport', '100', '', '', '/facilitys/transferstation_trans_report/list', 'N', 'menu', 'Y', '2018-04-20 23:36:14', '2018-04-20 23:36:14');
INSERT INTO `t_permissions_resource` VALUES ('437035216695984128', '-1', '进销存管理', '进销存管理导航菜单', 'nav_menu_stocks', '198', '', '', '', 'N', 'menu', 'Y', '2018-04-20 23:41:55', '2018-05-15 19:45:27');
INSERT INTO `t_permissions_resource` VALUES ('437036920611012608', '437035216695984128', '采购管理', '进销存管理-采购管理', 'menu_stocks_purchases', '100', '', '', '', 'N', 'menu', 'Y', '2018-04-20 23:48:41', '2018-04-20 23:48:41');
INSERT INTO `t_permissions_resource` VALUES ('437037866221043712', '437035216695984128', '库存管理', '进销存管理-库存管理', 'menu_stocks_assets', '100', '', '', '', 'N', 'menu', 'Y', '2018-04-20 23:52:27', '2018-04-20 23:52:27');
INSERT INTO `t_permissions_resource` VALUES ('437297102716928000', '437037866221043712', '品类配置', '进销存管理-库存管理-品类配置', 'menu_stocks_assets_assettype', '100', '', '', '/stocks/asset_type/list', 'N', 'menu', 'Y', '2018-04-21 17:02:33', '2018-04-21 17:02:33');
INSERT INTO `t_permissions_resource` VALUES ('437298037925085184', '437037866221043712', '资产管理', '进销存管理-库存管理-资产管理', 'menu_stocks_assets_asset', '100', '', '', '/stocks/asset/list', 'N', 'menu', 'Y', '2018-04-21 17:06:16', '2018-04-21 17:06:16');
INSERT INTO `t_permissions_resource` VALUES ('437536164954505216', '-1', '绩效管理', '绩效管理导航菜单', 'nav_menu_performances', '199', '', '', '', 'N', 'menu', 'Y', '2018-04-22 08:52:30', '2018-05-15 19:45:12');
INSERT INTO `t_permissions_resource` VALUES ('437538285678493696', '437036920611012608', '商品采购', '进销存管理-采购管理-商品采购', 'menu_stocks_purchases_purchaseorder', '100', '', '', '/orders/purchaseorder/head/list', 'N', 'menu', 'Y', '2018-04-22 09:00:56', '2018-04-22 09:00:56');
INSERT INTO `t_permissions_resource` VALUES ('437576990187323392', '437036920611012608', '审批人设置', '进销存管理-采购管理-审批人设置', 'menu_stocks_purchases_approver', '100', '', '', '/orders/purchaseorder/approveuser/list', 'N', 'menu', 'Y', '2018-04-22 11:34:44', '2018-04-22 11:34:44');
INSERT INTO `t_permissions_resource` VALUES ('437577699603513344', '437036920611012608', '预算管理', '进销存管理-采购管理-预算管理', 'menu_stocks_purchases_budget', '100', '', '', '/orders/purchaseorder/budget/list', 'N', 'menu', 'Y', '2018-04-22 11:37:33', '2018-04-22 11:37:33');
INSERT INTO `t_permissions_resource` VALUES ('437578378489364480', '437036920611012608', '采购审批', '进销存管理-采购管理-采购审批', 'menu_stocks_purchases_purchase-approve', '100', '', '', '/orders/purchase/approve/list', 'N', 'menu', 'Y', '2018-04-22 11:40:15', '2018-04-22 11:40:15');
INSERT INTO `t_permissions_resource` VALUES ('437578898448842752', '437036920611012608', '采购统计', '进销存管理-采购管理-采购统计', 'menu_stocks_purchases_count', '100', '', '', '/orders/purchaseorder/report/list', 'N', 'menu', 'Y', '2018-04-22 11:42:19', '2018-04-22 11:42:19');
INSERT INTO `t_permissions_resource` VALUES ('437580040239054848', '437536164954505216', '绩效管理', '绩效管理-绩效管理', 'menu_performances_performances', '100', '', '', '', 'N', 'menu', 'Y', '2018-04-22 11:46:51', '2018-04-22 11:46:51');
INSERT INTO `t_permissions_resource` VALUES ('437580253221617664', '437536164954505216', '报表中心', '绩效管理-报表中心', 'menu_performances_reports', '100', '', '', '', 'N', 'menu', 'Y', '2018-04-22 11:47:42', '2018-04-22 11:47:42');
INSERT INTO `t_permissions_resource` VALUES ('437666184964341760', '437580040239054848', '考勤管理（人工导入）', '绩效管理-绩效管理-考勤管理（人工导入）', 'menu_performances_performances_attend', '100', '', '', '/employees/attendreport/list', 'N', 'menu', 'Y', '2018-04-22 17:29:09', '2018-04-22 17:29:09');
INSERT INTO `t_permissions_resource` VALUES ('437667203601399808', '437580040239054848', '薪酬管理（人工导入）', '绩效管理-绩效管理-薪酬管理（人工导入）', 'menu_performances_performances_salary', '100', '', '', '/employees/salaryreport/list', 'N', 'menu', 'Y', '2018-04-22 17:33:12', '2018-04-22 17:33:12');
INSERT INTO `t_permissions_resource` VALUES ('437667886727692288', '437580040239054848', '任务管理', '绩效管理-绩效管理-任务管理', 'menu_performances_performances_mission', '100', '', '', '/employees/mission/list', 'N', 'menu', 'Y', '2018-04-22 17:35:55', '2018-04-22 17:35:55');
INSERT INTO `t_permissions_resource` VALUES ('437668694869409792', '437580253221617664', '工资成本汇总', '绩效管理-报表中心-工资成本汇总', 'menu_performances_reports_salaryall', '100', '', '', '/employees/salarycostreport/list', 'N', 'menu', 'Y', '2018-04-22 17:39:08', '2018-04-22 17:39:08');
INSERT INTO `t_permissions_resource` VALUES ('437669129126674432', '437580253221617664', '工资成本变化趋势', '绩效管理-报表中心-工资成本变化趋势', 'menu_performances_reports_salarycostchangereport', '100', '', '', '/employees/salarycostreport/costchange', 'N', 'menu', 'Y', '2018-04-22 17:40:51', '2018-04-22 17:40:51');
INSERT INTO `t_permissions_resource` VALUES ('438346565640257536', '-1', '司机打卡', 'app端菜单', 'menu_app_driver_attend', '100', '', '', '', 'N', 'menu', 'Y', '2018-04-24 14:32:45', '2018-04-24 14:32:45');
INSERT INTO `t_permissions_resource` VALUES ('438346869643411456', '-1', '街道巡查', 'app端菜单', 'menu_app_street_check', '100', '', '', '', 'N', 'menu', 'Y', '2018-04-24 14:33:57', '2018-04-24 14:33:57');
INSERT INTO `t_permissions_resource` VALUES ('438347083624218624', '-1', '工单报修', 'app端菜单', 'menu_app_work_order', '100', '', '', '', 'N', 'menu', 'Y', '2018-04-24 14:34:48', '2018-04-24 14:34:48');
INSERT INTO `t_permissions_resource` VALUES ('438347340772802560', '-1', '公厕消杀', 'app端菜单', 'menu_app_toilet_kill', '100', '', '', '', 'N', 'menu', 'Y', '2018-04-24 14:35:50', '2018-04-24 14:35:50');
INSERT INTO `t_permissions_resource` VALUES ('438347559719665664', '-1', '中转站管理', 'app端菜单', 'menu_app_transfer_station', '100', '', '', '', 'N', 'menu', 'Y', '2018-04-24 14:36:42', '2018-04-24 14:36:42');
INSERT INTO `t_permissions_resource` VALUES ('441709645522796544', '434734723189702656', '人事管理', '项目管理-人事管理', 'menu_projects_hr', '100', '', '', '', 'N', 'menu', 'Y', '2018-05-03 21:16:26', '2018-05-03 21:16:26');
INSERT INTO `t_permissions_resource` VALUES ('441710339642359808', '441709645522796544', '部门架构', '项目管理-人事管理 - 部门架构', 'menu_projects_hr_department', '100', '', '', '/permissions/group/list', 'N', 'menu', 'Y', '2018-05-03 21:19:11', '2018-05-03 21:19:11');
INSERT INTO `t_permissions_resource` VALUES ('442089084836380672', '-1', '数据看板', '数据看板导航菜单', 'nav_menu_databoards', '199', '', '', '/databoards/list', 'Y', 'menu', 'Y', '2018-05-04 22:24:11', '2018-05-29 21:20:21');
INSERT INTO `t_permissions_resource` VALUES ('442089600949682176', '442089084836380672', '数据看板', '数据看板-数据看板', 'menu_databoards_databoards', '100', '', '', '', 'N', 'menu', 'Y', '2018-05-04 22:26:14', '2018-05-04 22:26:14');
INSERT INTO `t_permissions_resource` VALUES ('442090414854373376', '442089600949682176', '数据中心', '数据看板-数据看板-数据中心', 'menu_databoards_databoards_datacenter', '100', '', '', '/databoards/list', 'Y', 'menu', 'Y', '2018-05-04 22:29:28', '2018-05-26 13:53:53');
INSERT INTO `t_permissions_resource` VALUES ('442371604459028480', '434739230539776000', '监控中心', '车辆管理-监控中心', 'menu_cars_monitors', '100', '', '', '', 'N', 'menu', 'Y', '2018-05-05 17:06:49', '2018-05-05 17:06:49');
INSERT INTO `t_permissions_resource` VALUES ('442376095619088384', '442371604459028480', '车辆监控地图', '车辆管理-监控中心-车辆监控地图', 'menu_cars_monitors_map', '100', '', '', '/cars/map/list', 'N', 'menu', 'Y', '2018-05-05 17:24:40', '2018-05-05 17:24:40');
INSERT INTO `t_permissions_resource` VALUES ('442376495852158976', '434740766938497024', '监控中心', '人员管理-监控中心', 'menu_employees_monitors', '100', '', '', '', 'N', 'menu', 'Y', '2018-05-05 17:26:15', '2018-05-05 17:26:15');
INSERT INTO `t_permissions_resource` VALUES ('442376964775346176', '442376495852158976', '人员监控地图', '人员管理-监控中心-人员监控地图', 'menu_employees_monitors_map', '100', '', '', '/employees/map/list', 'N', 'menu', 'Y', '2018-05-05 17:28:07', '2018-05-05 17:28:07');
INSERT INTO `t_permissions_resource` VALUES ('442385910072344576', '435815397527126016', '监控中心', '设施管理-监控中心', 'menu_facilitys_monitors', '100', '', '', '', 'N', 'menu', 'Y', '2018-05-05 18:03:40', '2018-05-05 18:03:40');
INSERT INTO `t_permissions_resource` VALUES ('443168598895099904', '442385910072344576', '设施地图', '设施管理-监控中心-设施地图', 'menu_facilitys_monitors_map', '100', '', '', '/facilitys/map/list', 'N', 'menu', 'Y', '2018-05-07 21:53:47', '2018-05-07 21:53:47');
INSERT INTO `t_permissions_resource` VALUES ('456851483820818432', '434752726371401728', '司机绩效统计', '车辆管理-报表中心-司机绩效统计', 'menu_cars_reports_driver', '100', '', '', '/employees/driverperformance/list', 'N', 'menu', 'Y', '2018-06-14 16:04:41', '2018-06-15 10:07:31');
INSERT INTO `t_permissions_resource` VALUES ('465843305804988416', '434734723189702656', '工单管理', '项目管理-工单管理', 'menu_projects_workorder', '100', '', '', '', 'N', 'menu', 'Y', '2018-07-09 11:34:59', '2018-07-09 11:34:59');
INSERT INTO `t_permissions_resource` VALUES ('465843933704880128', '465843305804988416', '工单管理', '项目管理-工单管理-工单管理', 'menu_projects_workorder_workorder', '100', '', '', '/orders/workorder/list', 'N', 'menu', 'Y', '2018-07-09 11:37:28', '2018-07-09 11:37:28');

-- ----------------------------
-- Table structure for t_permissions_res_group_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_permissions_res_group_rel`;
CREATE TABLE `t_permissions_res_group_rel` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `rid` bigint(20) NOT NULL COMMENT '资源表主键',
  `gid` bigint(20) NOT NULL COMMENT '用户组主键',
  `valid_status` varchar(5) NOT NULL DEFAULT 'Y' COMMENT '数据有效性Y:有效N:无效',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限-资源和用户组多对多关联关系表';

-- ----------------------------
-- Records of t_permissions_res_group_rel
-- ----------------------------

-- ----------------------------
-- Table structure for t_permissions_res_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_permissions_res_role_rel`;
CREATE TABLE `t_permissions_res_role_rel` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `res_id` bigint(20) NOT NULL COMMENT '资源表主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色表主键',
  `allocated` varchar(10) NOT NULL DEFAULT 'CAN' COMMENT 'CAN: 可以再次配置 CAN_NOT：不可以再次配置',
  `valid_status` varchar(5) NOT NULL DEFAULT 'Y' COMMENT '数据有效状态 Y:有效 N:无效',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限-资源和角色多对多关联关系表';

-- ----------------------------
-- Records of t_permissions_res_role_rel
-- ----------------------------
INSERT INTO `t_permissions_res_role_rel` VALUES ('468816002528837632', '438346565640257536', '464430524866232320', 'CAN', 'Y', '2018-07-17 16:27:25', '2018-07-17 16:27:25');
INSERT INTO `t_permissions_res_role_rel` VALUES ('468816002528837633', '438347083624218624', '464430524866232320', 'CAN', 'Y', '2018-07-17 16:27:25', '2018-07-17 16:27:25');
INSERT INTO `t_permissions_res_role_rel` VALUES ('468817503225643008', '438346869643411456', '466203084872220672', 'CAN', 'Y', '2018-07-17 16:33:22', '2018-07-17 16:33:22');
INSERT INTO `t_permissions_res_role_rel` VALUES ('468817503225643009', '438347083624218624', '466203084872220672', 'CAN', 'Y', '2018-07-17 16:33:22', '2018-07-17 16:33:22');
INSERT INTO `t_permissions_res_role_rel` VALUES ('468817667311009792', '438347083624218624', '464473029796167680', 'CAN', 'Y', '2018-07-17 16:34:02', '2018-07-17 16:34:02');
INSERT INTO `t_permissions_res_role_rel` VALUES ('468817667311009793', '438347340772802560', '464473029796167680', 'CAN', 'Y', '2018-07-17 16:34:02', '2018-07-17 16:34:02');
INSERT INTO `t_permissions_res_role_rel` VALUES ('468817667311009794', '438347559719665664', '464473029796167680', 'CAN', 'Y', '2018-07-17 16:34:02', '2018-07-17 16:34:02');
INSERT INTO `t_permissions_res_role_rel` VALUES ('468818561440153600', '438346869643411456', '464460410204979200', 'CAN', 'Y', '2018-07-17 16:37:35', '2018-07-17 16:37:35');
INSERT INTO `t_permissions_res_role_rel` VALUES ('468818561440153601', '438347083624218624', '464460410204979200', 'CAN', 'Y', '2018-07-17 16:37:35', '2018-07-17 16:37:35');
INSERT INTO `t_permissions_res_role_rel` VALUES ('468818561440153602', '438347559719665664', '464460410204979200', 'CAN', 'Y', '2018-07-17 16:37:35', '2018-07-17 16:37:35');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469082995232342016', '434758918527254528', '434307543834034176', 'CAN', 'Y', '2018-07-18 10:08:21', '2018-07-18 10:08:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469082995232342017', '438347083624218624', '434307543834034176', 'CAN', 'Y', '2018-07-18 10:08:21', '2018-07-18 10:08:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744832', '434739230539776000', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744833', '434739598573174784', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744834', '434739982310047744', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744835', '434748331961352192', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744836', '434748752922673152', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744837', '434752726371401728', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744838', '434749113242746880', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744839', '434749497046728704', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744840', '434751632819879936', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744841', '456851483820818432', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744842', '442371604459028480', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744843', '442376095619088384', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744844', '434740766938497024', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744845', '434742060482822144', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744846', '434761338422558720', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744847', '434762570218340352', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744848', '434762251837112320', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744849', '434763520723124224', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744850', '434763923162398720', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744851', '442376495852158976', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744852', '442376964775346176', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744853', '435815397527126016', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744854', '435816974342160384', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744855', '435817582738538496', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744856', '435919652141400064', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780794744857', '436539514479968256', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939136', '436539858660360192', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939137', '436584333541638144', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939138', '436584895695814656', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939139', '437033786681589760', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939140', '442385910072344576', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939141', '443168598895099904', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939142', '434734723189702656', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939143', '434737187070672896', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939144', '434738851383083008', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939145', '441709645522796544', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939146', '434742673841061888', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939147', '434759909502877696', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939148', '441710339642359808', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939149', '465843305804988416', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939150', '465843933704880128', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939151', '437035216695984128', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939152', '437036920611012608', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939153', '437538285678493696', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939154', '437577699603513344', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939155', '437578378489364480', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939156', '437578898448842752', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939157', '437037866221043712', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939158', '437297102716928000', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939159', '437298037925085184', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939160', '437536164954505216', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939161', '437580040239054848', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939162', '437666184964341760', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939163', '437667203601399808', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939164', '437667886727692288', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939165', '437580253221617664', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939166', '437668694869409792', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939167', '437669129126674432', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939168', '442089084836380672', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939169', '442089600949682176', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('469163780798939170', '442090414854373376', '460769272470175744', 'CAN', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763333472256', '434739230539776000', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666560', '434739598573174784', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666561', '434739982310047744', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666562', '434748331961352192', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666563', '434748752922673152', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666564', '434752726371401728', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666565', '434749113242746880', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666566', '434749497046728704', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666567', '434751632819879936', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666568', '456851483820818432', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666569', '442371604459028480', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666570', '442376095619088384', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666571', '434740766938497024', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666572', '434742060482822144', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666573', '434761338422558720', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666574', '434762570218340352', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666575', '434762251837112320', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666576', '434763520723124224', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666577', '434763923162398720', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666578', '442376495852158976', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666579', '442376964775346176', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666580', '435815397527126016', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666581', '435816974342160384', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666582', '435817582738538496', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666583', '435919652141400064', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666584', '436539514479968256', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666585', '436539858660360192', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666586', '436584333541638144', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666587', '436584895695814656', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666588', '437033786681589760', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666589', '442385910072344576', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666590', '443168598895099904', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666591', '434734723189702656', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666592', '434737187070672896', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666593', '434738851383083008', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666594', '441709645522796544', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666595', '434742673841061888', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763337666596', '434759909502877696', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860864', '441710339642359808', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860865', '465843305804988416', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860866', '465843933704880128', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860867', '437035216695984128', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860868', '437036920611012608', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860869', '437538285678493696', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860870', '437577699603513344', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860871', '437578898448842752', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860872', '437037866221043712', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860873', '437297102716928000', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860874', '437298037925085184', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860875', '437536164954505216', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860876', '437580040239054848', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860877', '437666184964341760', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860878', '437667203601399808', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860879', '437667886727692288', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860880', '437580253221617664', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860881', '437668694869409792', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860882', '437669129126674432', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860883', '442089084836380672', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860884', '442089600949682176', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860885', '442090414854373376', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860886', '1', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860887', '2', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860888', '3', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860889', '4', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471465763341860890', '5', '-1', 'CAN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712027308032', '434758918527254528', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712027308033', '438346565640257536', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712027308034', '438346869643411456', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712027308035', '438347083624218624', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712027308036', '438347340772802560', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712027308037', '438347559719665664', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712027308038', '434739230539776000', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712027308039', '434739598573174784', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712027308040', '434739982310047744', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712027308041', '434748331961352192', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712027308042', '434748752922673152', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712027308043', '434752726371401728', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712027308044', '434749113242746880', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712027308045', '434749497046728704', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712027308046', '434751632819879936', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712027308047', '456851483820818432', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712027308048', '442371604459028480', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712027308049', '442376095619088384', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712027308050', '434740766938497024', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502336', '434742060482822144', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502337', '434761338422558720', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502338', '434762570218340352', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502339', '434762251837112320', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502340', '434763520723124224', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502341', '434763923162398720', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502342', '442376495852158976', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502343', '442376964775346176', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502344', '435815397527126016', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502345', '435816974342160384', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502346', '435817582738538496', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502347', '435919652141400064', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502348', '436539514479968256', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502349', '436539858660360192', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502350', '436584333541638144', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502351', '436584895695814656', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502352', '437033786681589760', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502353', '442385910072344576', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502354', '443168598895099904', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502355', '434734723189702656', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502356', '434737187070672896', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502357', '434738851383083008', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502358', '441709645522796544', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502359', '434742673841061888', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502360', '434759909502877696', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502361', '441710339642359808', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502362', '465843305804988416', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502363', '465843933704880128', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502364', '437035216695984128', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502365', '437036920611012608', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712031502366', '437538285678493696', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712035696640', '437576990187323392', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712035696641', '437577699603513344', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712035696642', '437578378489364480', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712035696643', '437578898448842752', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712035696644', '437037866221043712', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712035696645', '437297102716928000', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712035696646', '437298037925085184', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712035696647', '437536164954505216', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712035696648', '437580040239054848', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712035696649', '437666184964341760', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712035696650', '437667203601399808', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712035696651', '437667886727692288', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712035696652', '437580253221617664', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712035696653', '437668694869409792', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712035696654', '437669129126674432', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712035696655', '442089084836380672', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712035696656', '442089600949682176', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712035696657', '442090414854373376', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712035696658', '1', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712035696659', '2', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712035696660', '3', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712035696661', '4', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');
INSERT INTO `t_permissions_res_role_rel` VALUES ('471640712035696662', '5', '471640711893090304', 'CAN', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');

-- ----------------------------
-- Table structure for t_permissions_role
-- ----------------------------
DROP TABLE IF EXISTS `t_permissions_role`;
CREATE TABLE `t_permissions_role` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `pid` bigint(20) NOT NULL DEFAULT '-1' COMMENT '上级角色id',
  `role_name` varchar(30) NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_code` varchar(40) NOT NULL COMMENT '角色编码',
  `valid_status` varchar(5) NOT NULL DEFAULT 'Y' COMMENT '数据有效状态Y:有效N:无效',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_code` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限-角色表';

-- ----------------------------
-- Records of t_permissions_role
-- ----------------------------
INSERT INTO `t_permissions_role` VALUES ('-1', '-1', '超级管理员', 'ADMIN', 'Y', '2018-07-24 23:56:37', '2018-07-24 23:56:37');
INSERT INTO `t_permissions_role` VALUES ('434307543834034176', '-1', '清洁工(APP)', 'QingJieGong', 'Y', '2018-07-18 10:08:21', '2018-07-18 10:08:21');
INSERT INTO `t_permissions_role` VALUES ('460769272470175744', '-1', '管理员', 'manager', 'Y', '2018-07-18 15:29:21', '2018-07-18 15:29:21');
INSERT INTO `t_permissions_role` VALUES ('464430524866232320', '-1', '司机(APP)', 'SiJi', 'Y', '2018-07-17 16:27:25', '2018-07-17 16:27:25');
INSERT INTO `t_permissions_role` VALUES ('464460410204979200', '-1', '无关联角色（APP）', 'NoRole', 'Y', '2018-07-17 16:37:35', '2018-07-17 16:37:35');
INSERT INTO `t_permissions_role` VALUES ('464473029796167680', '-1', '公厕管理员(APP)', 'GongCeGuanLiYuan', 'Y', '2018-07-17 16:34:02', '2018-07-17 16:34:02');
INSERT INTO `t_permissions_role` VALUES ('466203084872220672', '-1', '巡检员(APP)', 'XunJianYuan', 'Y', '2018-07-17 16:33:22', '2018-07-17 16:33:22');
INSERT INTO `t_permissions_role` VALUES ('471640711893090304', '-1', '测试专用', 'Test', 'Y', '2018-07-25 11:31:48', '2018-07-25 11:31:48');

-- ----------------------------
-- Table structure for t_permissions_role_group_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_permissions_role_group_rel`;
CREATE TABLE `t_permissions_role_group_rel` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `rid` bigint(20) NOT NULL COMMENT '角色表主键',
  `gid` bigint(20) NOT NULL COMMENT '用户组表主键',
  `valid_status` varchar(5) NOT NULL DEFAULT 'Y' COMMENT '数据有效状态 Y:有效 N:无效',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限-角色和用户组多对多关联关系';

-- ----------------------------
-- Records of t_permissions_role_group_rel
-- ----------------------------

-- ----------------------------
-- Table structure for t_permissions_user
-- ----------------------------
DROP TABLE IF EXISTS `t_permissions_user`;
CREATE TABLE `t_permissions_user` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `nickname` varchar(50) NOT NULL COMMENT '昵称',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(40) NOT NULL COMMENT '加密盐',
  `login_name` varchar(60) NOT NULL COMMENT '登录名称',
  `valid_status` varchar(5) NOT NULL DEFAULT 'Y' COMMENT '数据有效状态 Y:有效 N:无效',
  `contact` varchar(20) NOT NULL DEFAULT '' COMMENT '联系方式',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_name` (`login_name`) USING BTREE COMMENT '邮箱不能重复',
  UNIQUE KEY `uk_login_name` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限-用户表';

-- ----------------------------
-- Records of t_permissions_user
-- ----------------------------
INSERT INTO `t_permissions_user` VALUES ('-1', '超级管理员', '906192f7635b626623b16c55e746e30e', '70bf42b84493d12cafb55c9c0a8e08ea', 'admin', 'Y', '13666666666', '2018-03-27 00:00:00', '2018-04-06 13:55:14');
INSERT INTO `t_permissions_user` VALUES ('460769416259305472', '系统管理员', 'c321762ccd4581b4ffc1f470855e7aab', '3c738d952e7c2e869eccd91377407d75', 'manager', 'Y', '13222222222', '2018-06-25 11:33:09', '2018-07-18 10:19:47');

-- ----------------------------
-- Table structure for t_permissions_user_group_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_permissions_user_group_rel`;
CREATE TABLE `t_permissions_user_group_rel` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `uid` bigint(20) NOT NULL COMMENT '用户主键',
  `gid` bigint(20) NOT NULL COMMENT '用户组主键',
  `valid_status` varchar(5) CHARACTER SET utf8 NOT NULL DEFAULT 'Y' COMMENT '数据有效性Y:有效N:无效',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=sjis COMMENT='系统权限-用户和用户组多对多关系表';

-- ----------------------------
-- Records of t_permissions_user_group_rel
-- ----------------------------

-- ----------------------------
-- Table structure for t_permissions_user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_permissions_user_role_rel`;
CREATE TABLE `t_permissions_user_role_rel` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `uid` bigint(20) NOT NULL COMMENT '用户表主键',
  `rid` bigint(20) NOT NULL COMMENT '角色表主键',
  `valid_status` varchar(5) NOT NULL DEFAULT 'Y' COMMENT '数据有效状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限-用户和角色关联关系表';

-- ----------------------------
-- Records of t_permissions_user_role_rel
-- ----------------------------
INSERT INTO `t_permissions_user_role_rel` VALUES ('421814144925106176', '-1', '-1', 'Y', '2018-04-06 17:33:05', '2018-04-06 17:33:05');
INSERT INTO `t_permissions_user_role_rel` VALUES ('431814144925106176', '429960820168851456', '429961471259049984', 'Y', '2018-04-06 13:55:14', '2018-04-06 13:55:14');
INSERT INTO `t_permissions_user_role_rel` VALUES ('469085873166614528', '460769416259305472', '460769272470175744', 'Y', '2018-07-18 10:19:47', '2018-07-18 10:19:47');
