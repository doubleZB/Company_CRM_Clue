/*
Navicat MySQL Data Transfer

Source Server         : remote
Source Server Version : 50620
Source Host           : 114.55.95.79:3306
Source Database       : soa-crm

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2018-01-08 16:29:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for business
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '业务名称',
  `company_id` int(11) DEFAULT NULL COMMENT '企业id',
  `show_name` varchar(32) DEFAULT NULL COMMENT '显示名称',
  `is_enabled` int(3) DEFAULT NULL COMMENT '是否启用 1.启用',
  `industry_type` int(3) DEFAULT NULL COMMENT '行业类别',
  `is_del` int(3) DEFAULT '1',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='业务表';

-- ----------------------------
-- Table structure for business_businesstype_tem
-- ----------------------------
DROP TABLE IF EXISTS `business_businesstype_tem`;
CREATE TABLE `business_businesstype_tem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `business_template_id` int(11) DEFAULT NULL COMMENT '业务模板id',
  `business_type_template_id` int(11) DEFAULT NULL COMMENT '业务类别模板id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for business_tem
-- ----------------------------
DROP TABLE IF EXISTS `business_tem`;
CREATE TABLE `business_tem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '业务名称',
  `show_name` varchar(32) DEFAULT NULL,
  `is_enabled` int(3) DEFAULT NULL,
  `industry_type` int(3) DEFAULT NULL COMMENT '行业类别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for business_type
-- ----------------------------
DROP TABLE IF EXISTS `business_type`;
CREATE TABLE `business_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '业务类型名称',
  `show_name` varchar(32) DEFAULT NULL COMMENT '业务类型显示名称',
  `is_enabled` int(3) DEFAULT NULL COMMENT '是否开启',
  `business_id` int(11) DEFAULT NULL COMMENT '业务id',
  `type_status` int(3) DEFAULT NULL COMMENT '类别 1.线索 2.客户 3.联系人 4.商机  5.合同  6.产品',
  `is_del` int(3) DEFAULT '1' COMMENT '是否删除 1，为删除',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='业务类型表';

-- ----------------------------
-- Table structure for business_type_field
-- ----------------------------
DROP TABLE IF EXISTS `business_type_field`;
CREATE TABLE `business_type_field` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `field_name` varchar(32) DEFAULT NULL COMMENT '字段名称',
  `field_show_name` varchar(32) DEFAULT NULL COMMENT '字段显示名称',
  `field_type` varchar(16) DEFAULT '2' COMMENT '是否首页显示 ，1显示，2不显示',
  `field_alias` varchar(32) DEFAULT NULL COMMENT '字段别名',
  `is_enabled` int(3) DEFAULT NULL COMMENT '是否启用',
  `is_required` int(3) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `business_type_id` int(11) DEFAULT NULL COMMENT '业务类别id',
  `is_del` int(3) DEFAULT '1' COMMENT '是否删除 1，为删除',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_show` int(3) DEFAULT NULL COMMENT '是否首页显示 1，显示 2，不显示',
  `enter_prompt` varchar(32) DEFAULT NULL,
  `is_modify` int(3) DEFAULT NULL COMMENT '1.不允许  2.允许修改',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8 COMMENT='业务类型字段表';

-- ----------------------------
-- Table structure for business_type_field_block_tem
-- ----------------------------
DROP TABLE IF EXISTS `business_type_field_block_tem`;
CREATE TABLE `business_type_field_block_tem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `field_name` varchar(32) DEFAULT NULL COMMENT '字段名称',
  `field_type` varchar(16) DEFAULT NULL COMMENT '字段类型',
  `field_alias` varchar(32) DEFAULT NULL COMMENT '字段别名',
  `is_enabled` int(3) DEFAULT NULL COMMENT '是否启用 1.启用',
  `is_required` int(3) DEFAULT NULL COMMENT '是否必填 1.必填',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='业务类型字段表';

-- ----------------------------
-- Table structure for business_type_field_tem
-- ----------------------------
DROP TABLE IF EXISTS `business_type_field_tem`;
CREATE TABLE `business_type_field_tem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `field_name` varchar(32) DEFAULT NULL COMMENT '字段名称',
  `field_show_name` varchar(32) DEFAULT NULL COMMENT '字段显示名称',
  `field_type` varchar(16) DEFAULT NULL COMMENT '字段类型',
  `field_alias` varchar(32) DEFAULT NULL COMMENT '字段别名',
  `is_enabled` int(3) DEFAULT NULL COMMENT '是否启用 1.启用',
  `is_required` int(3) DEFAULT NULL COMMENT '是否必填 1.必填',
  `enter_prompt` varchar(32) DEFAULT NULL COMMENT '输入提示',
  `is_modify` int(3) DEFAULT NULL COMMENT '是否允许修改 1.不允许  2.允许修改',
  `is_show` int(1) DEFAULT NULL COMMENT '是否显示 1 显示  2不显示',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='业务类型字段表';

-- ----------------------------
-- Table structure for business_type_field_value_choose
-- ----------------------------
DROP TABLE IF EXISTS `business_type_field_value_choose`;
CREATE TABLE `business_type_field_value_choose` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `value` varchar(32) DEFAULT NULL,
  `business_type_field_id` int(11) DEFAULT NULL,
  `is_enabled` int(3) DEFAULT NULL,
  `is_del` int(3) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for business_type_field_value_choose_tem
-- ----------------------------
DROP TABLE IF EXISTS `business_type_field_value_choose_tem`;
CREATE TABLE `business_type_field_value_choose_tem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '显示名称',
  `value` varchar(32) DEFAULT NULL COMMENT '选择值',
  `is_enabled` int(3) DEFAULT NULL COMMENT '是否启用 1.启用',
  `type_field_tem_id` int(11) DEFAULT NULL COMMENT '字段模板id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for business_type_field_value_datetime
-- ----------------------------
DROP TABLE IF EXISTS `business_type_field_value_datetime`;
CREATE TABLE `business_type_field_value_datetime` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `business_type_id` int(11) DEFAULT NULL,
  `business_type_field_id` int(11) DEFAULT NULL,
  `value` datetime DEFAULT NULL,
  `identify_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务字段对应的时间值表';

-- ----------------------------
-- Table structure for business_type_field_value_decimal
-- ----------------------------
DROP TABLE IF EXISTS `business_type_field_value_decimal`;
CREATE TABLE `business_type_field_value_decimal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `business_type_id` int(11) DEFAULT NULL,
  `business_type_field_id` int(11) DEFAULT NULL,
  `value` decimal(10,2) DEFAULT NULL,
  `identify_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务字段对应的数值表';

-- ----------------------------
-- Table structure for business_type_field_value_int
-- ----------------------------
DROP TABLE IF EXISTS `business_type_field_value_int`;
CREATE TABLE `business_type_field_value_int` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `business_type_id` int(11) DEFAULT NULL,
  `business_type_field_id` int(11) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  `identify_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务字段对应的int值表';

-- ----------------------------
-- Table structure for business_type_field_value_text
-- ----------------------------
DROP TABLE IF EXISTS `business_type_field_value_text`;
CREATE TABLE `business_type_field_value_text` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `business_type_id` int(11) DEFAULT NULL,
  `business_type_field_id` int(11) DEFAULT NULL,
  `value` text,
  `identify_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='业务字段对应的文本值表';

-- ----------------------------
-- Table structure for business_type_field_value_varchar
-- ----------------------------
DROP TABLE IF EXISTS `business_type_field_value_varchar`;
CREATE TABLE `business_type_field_value_varchar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `business_type_id` int(11) DEFAULT NULL,
  `business_type_field_id` int(11) DEFAULT NULL,
  `value` varchar(64) DEFAULT NULL,
  `identify_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1014 DEFAULT CHARSET=utf8 COMMENT='业务字段对应的varchar值表';

-- ----------------------------
-- Table structure for business_type_filed_user
-- ----------------------------
DROP TABLE IF EXISTS `business_type_filed_user`;
CREATE TABLE `business_type_filed_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `business_type_id` int(11) DEFAULT NULL COMMENT '模块id',
  `business_id` int(11) DEFAULT NULL COMMENT '类型id',
  `business_type_field_id` int(11) DEFAULT NULL COMMENT '字段id',
  `company_id` int(11) DEFAULT NULL COMMENT '公司id',
  `show_id` char(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for business_type_tem
-- ----------------------------
DROP TABLE IF EXISTS `business_type_tem`;
CREATE TABLE `business_type_tem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '业务类型名称',
  `show_name` varchar(32) DEFAULT NULL COMMENT '业务类型显示名称',
  `is_enabled` int(3) DEFAULT NULL COMMENT '是否开启',
  `type_status` int(3) DEFAULT NULL COMMENT '类别 1.线索 2.客户 3.联系人 4.商机  5.合同  6.产品',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='业务类型表';

-- ----------------------------
-- Table structure for business_type_value_identify
-- ----------------------------
DROP TABLE IF EXISTS `business_type_value_identify`;
CREATE TABLE `business_type_value_identify` (
  `id` varchar(64) NOT NULL DEFAULT '',
  `user_id` int(11) DEFAULT NULL,
  `business_type_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_del` int(3) DEFAULT NULL COMMENT '是否删除 ‘0’删除 ‘1’ 未删除',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bustype_block_tem
-- ----------------------------
DROP TABLE IF EXISTS `bustype_block_tem`;
CREATE TABLE `bustype_block_tem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `business_type_tem_id` int(11) DEFAULT NULL COMMENT '业务类型模板id',
  `field_block_tem_id` int(11) DEFAULT NULL COMMENT '区块模板id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for company_organization_business
-- ----------------------------
DROP TABLE IF EXISTS `company_organization_business`;
CREATE TABLE `company_organization_business` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) DEFAULT NULL COMMENT '企业id',
  `organization_id` int(11) DEFAULT NULL COMMENT '组织id',
  `business_id` int(11) DEFAULT NULL COMMENT '业务id',
  `is_del` int(3) DEFAULT '1' COMMENT '是否删除 1未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fieldblock_field_tem
-- ----------------------------
DROP TABLE IF EXISTS `fieldblock_field_tem`;
CREATE TABLE `fieldblock_field_tem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `field_block_tem_id` int(11) DEFAULT NULL COMMENT '区块模板id',
  `field_tem_id` int(11) DEFAULT NULL COMMENT '字段模板id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for follow_record
-- ----------------------------
DROP TABLE IF EXISTS `follow_record`;
CREATE TABLE `follow_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `follow_content` varchar(500) DEFAULT NULL COMMENT '内容',
  `follow_up` int(3) DEFAULT NULL COMMENT '1: 电话沟通  2:上门拜访 3:邮件沟通  4:其他方式',
  `follow_next_time` datetime DEFAULT NULL COMMENT '下次跟进时间',
  `follow_reminder_content` varchar(64) DEFAULT NULL COMMENT '跟进提醒内容',
  `follow_source_id` varchar(64) DEFAULT NULL COMMENT '来源id 例如：线索id',
  `follow_source_type` int(3) DEFAULT NULL COMMENT '来源类别 1.线索',
  `is_del` int(3) DEFAULT NULL COMMENT '1. 未删除  2.已删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='跟进记录表';

-- ----------------------------
-- Table structure for message_template
-- ----------------------------
DROP TABLE IF EXISTS `message_template`;
CREATE TABLE `message_template` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) DEFAULT NULL COMMENT '企业id',
  `name` varchar(16) DEFAULT NULL COMMENT '短信模板名称',
  `content` varchar(300) DEFAULT NULL COMMENT '短信模板内容',
  `signature` varchar(16) DEFAULT NULL COMMENT '模板签名',
  `status` int(1) DEFAULT NULL COMMENT 's审核状态 1 审核通过 2审核中 3 审核失败',
  `is_del` int(1) DEFAULT NULL COMMENT '是否删除  1 未删除  2 已删除',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for send_message
-- ----------------------------
DROP TABLE IF EXISTS `send_message`;
CREATE TABLE `send_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `send_userid` int(11) DEFAULT NULL COMMENT '发送人id',
  `status` int(3) DEFAULT NULL COMMENT '发送状态 1.发送成功，2.失败',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `send_content_tem` varchar(64) DEFAULT NULL COMMENT '发送内容id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COMMENT='短信发送表';

-- ----------------------------
-- Table structure for send_receive
-- ----------------------------
DROP TABLE IF EXISTS `send_receive`;
CREATE TABLE `send_receive` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `send_id` int(11) DEFAULT NULL COMMENT '短信id',
  `clue_id` varchar(64) DEFAULT NULL COMMENT '线索id',
  `status` int(3) DEFAULT NULL COMMENT '单条短信发送状态 1.发送成功，2.失败',
  `message_return` varchar(32) DEFAULT NULL COMMENT '短信返回码',
  `message_type` int(11) DEFAULT NULL COMMENT '类别0短信群发1模板短信',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='短信发送中间表';

-- ----------------------------
-- Table structure for sms
-- ----------------------------
DROP TABLE IF EXISTS `sms`;
CREATE TABLE `sms` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息提醒id',
  `sms_text` varchar(255) DEFAULT NULL,
  `sms_date` datetime DEFAULT NULL,
  `business_id` int(11) DEFAULT NULL COMMENT '业务id',
  `type_id` int(11) DEFAULT NULL COMMENT '业务类型id',
  `specific_id` varchar(64) DEFAULT NULL COMMENT '唯一标识 如线索唯一标识',
  `sms_status` char(3) DEFAULT NULL COMMENT '短信发送 1未发送 2 已发送',
  `show_status` char(3) DEFAULT NULL COMMENT '首页展示 1未展示 2 已展示',
  `fieldone` varchar(64) DEFAULT NULL COMMENT '待用字段',
  `fieldtwo` varchar(64) DEFAULT NULL COMMENT '待用字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COMMENT='时间提醒表';
