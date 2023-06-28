ALTER TABLE gen_table ADD COLUMN data_name varchar(200) NULL DEFAULT '' COMMENT '数据源名称' AFTER table_id;

INSERT INTO `sys_menu` VALUES (1673538385829883905, '消息管理', 1, 11, 'messageManage', NULL, NULL, 0, 1, 'M', '1', '1', NULL, 'chat', 103, 1, now(), 1, now(), '');
INSERT INTO `sys_menu` VALUES (1673542995458084865, '消息配置', 1673538385829883905, 1, 'messageConfig', 'system/messageConfig/index', NULL, 0, 1, 'C', '1', '1', 'system:messageConfig:list', 'tools', 103, 1, now(), 1, now(), '消息配置菜单');
INSERT INTO `sys_menu` VALUES (1673542995458084866, '消息配置查询', 1673542995458084865, 1, '#', '', NULL, 0, 1, 'F', '1', '1', 'system:messageConfig:query', '#', 103, 1, now(), NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1673542995458084867, '消息配置新增', 1673542995458084865, 2, '#', '', NULL, 0, 1, 'F', '1', '1', 'system:messageConfig:add', '#', 103, 1, now(), NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1673542995458084868, '消息配置修改', 1673542995458084865, 3, '#', '', NULL, 0, 1, 'F', '1', '1', 'system:messageConfig:edit', '#', 103, 1, now(), NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1673542995458084869, '消息配置删除', 1673542995458084865, 4, '#', '', NULL, 0, 1, 'F', '1', '1', 'system:messageConfig:remove', '#', 103, 1, now(), NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1673542995458084870, '消息配置导出', 1673542995458084865, 5, '#', '', NULL, 0, 1, 'F', '1', '1', 'system:messageConfig:export', '#', 103, 1, now(), NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1673938617411493889, '消息常量', 1673538385829883905, 2, 'messageKey', 'system/messageKey/index', NULL, 0, 1, 'C', '1', '1', 'system:messageKey:list', 'root-list', 103, 1, now(), 1, now(), '消息常量菜单');
INSERT INTO `sys_menu` VALUES (1673938617411493890, '消息常量查询', 1673938617411493889, 1, '#', '', NULL, 0, 1, 'F', '1', '1', 'system:messageKey:query', '#', 103, 1, now(), NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1673938617411493891, '消息常量新增', 1673938617411493889, 2, '#', '', NULL, 0, 1, 'F', '1', '1', 'system:messageKey:add', '#', 103, 1, now(), NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1673938617411493892, '消息常量修改', 1673938617411493889, 3, '#', '', NULL, 0, 1, 'F', '1', '1', 'system:messageKey:edit', '#', 103, 1, now(), NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1673938617411493893, '消息常量删除', 1673938617411493889, 4, '#', '', NULL, 0, 1, 'F', '1', '1', 'system:messageKey:remove', '#', 103, 1, now(), NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1673938617411493894, '消息常量导出', 1673938617411493889, 5, '#', '', NULL, 0, 1, 'F', '1', '1', 'system:messageKey:export', '#', 103, 1, now(), NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1673958410416009218, '消息模板', 1673538385829883905, 3, 'messageTemplate', 'system/messageTemplate/index', NULL, 0, 1, 'C', '1', '1', 'system:messageTemplate:list', 'relativity', 103, 1, now(), 1, now(), '消息模板菜单');
INSERT INTO `sys_menu` VALUES (1673958410416009219, '消息模板查询', 1673958410416009218, 1, '#', '', NULL, 0, 1, 'F', '1', '1', 'system:messageTemplate:query', '#', 103, 1, now(), NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1673958410416009220, '消息模板新增', 1673958410416009218, 2, '#', '', NULL, 0, 1, 'F', '1', '1', 'system:messageTemplate:add', '#', 103, 1, now(), NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1673958410416009221, '消息模板修改', 1673958410416009218, 3, '#', '', NULL, 0, 1, 'F', '1', '1', 'system:messageTemplate:edit', '#', 103, 1, now(), NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1673958410416009222, '消息模板删除', 1673958410416009218, 4, '#', '', NULL, 0, 1, 'F', '1', '1', 'system:messageTemplate:remove', '#', 103, 1, now(), NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1673958410416009223, '消息模板导出', 1673958410416009218, 5, '#', '', NULL, 0, 1, 'F', '1', '1', 'system:messageTemplate:export', '#', 103, 1, now(), NULL, NULL, '');

INSERT INTO `sys_dict_type` VALUES (1673530912729395201, '000000', '消息类型', 'sys_message_type', '1', 103, 1, now(), 1, now(), NULL);
INSERT INTO `sys_dict_type` VALUES (1673531836864589825, '000000', '消息支持平台', 'sys_message_supplier_type', '1', 103, 1, now(), 1, now(), NULL);
INSERT INTO `sys_dict_type` VALUES (1673955788556578817, '000000', '消息模板类型', 'sys_message_template_mode', '1', 103, 1, now(), 1, now(), NULL);

INSERT INTO `sys_dict_data` VALUES (1673531114659966977, '000000', 0, '短信', 'SMS', 'sys_message_type', NULL, 'primary', 'N', '1', 103, 1, now(), 1, now(), NULL);
INSERT INTO `sys_dict_data` VALUES (1673531197132566529, '000000', 1, '邮箱', 'MAIL', 'sys_message_type', NULL, 'primary', 'N', '1', 103, 1, now(), 1, now(), NULL);
INSERT INTO `sys_dict_data` VALUES (1673533447162753025, '000000', 1, '阿里云短信', 'ALIBABA', 'sys_message_supplier_type', NULL, 'primary', 'N', '1', 103, 1, now(), 1, now(), NULL);
INSERT INTO `sys_dict_data` VALUES (1673533508210847746, '000000', 2, '华为云短信', 'HUAWEI', 'sys_message_supplier_type', NULL, 'primary', 'N', '1', 103, 1, now(), 1, now(), NULL);
INSERT INTO `sys_dict_data` VALUES (1673533566687834113, '000000', 3, '腾讯云短信', 'TENCENT', 'sys_message_supplier_type', NULL, 'primary', 'N', '1', 103, 1, now(), 1, now(), NULL);
INSERT INTO `sys_dict_data` VALUES (1673533618948861953, '000000', 4, '云片短信', 'YUNPIAN', 'sys_message_supplier_type', NULL, 'primary', 'N', '1', 103, 1, now(), 1, now(), NULL);
INSERT INTO `sys_dict_data` VALUES (1673533676184334338, '000000', 5, '合一短信', 'UNI_SMS', 'sys_message_supplier_type', NULL, 'primary', 'N', '1', 103, 1, now(), 1, now(), NULL);
INSERT INTO `sys_dict_data` VALUES (1673533735877668866, '000000', 6, '京东云短信', 'JD_CLOUD', 'sys_message_supplier_type', NULL, 'primary', 'N', '1', 103, 1, now(), 1, now(), NULL);
INSERT INTO `sys_dict_data` VALUES (1673533807113728001, '000000', 7, '容联云短信', 'CLOOPEN', 'sys_message_supplier_type', NULL, 'primary', 'N', '1', 103, 1, now(), 1, now(), NULL);
INSERT INTO `sys_dict_data` VALUES (1673533870267363329, '000000', 8, '亿美软通短信', 'EMAY', 'sys_message_supplier_type', NULL, 'primary', 'N', '1', 103, 1, now(), 1, now(), NULL);
INSERT INTO `sys_dict_data` VALUES (1673533943550242818, '000000', 9, '天翼云短信', 'CTYUN', 'sys_message_supplier_type', NULL, 'primary', 'N', '1', 103, 1, now(), 1, now(), NULL);
INSERT INTO `sys_dict_data` VALUES (1673534012785618946, '000000', 0, '邮箱', 'MAIL', 'sys_message_supplier_type', NULL, 'primary', 'N', '1', 103, 1, now(), 1, now(), NULL);
INSERT INTO `sys_dict_data` VALUES (1673862213713977345, '000000', 10, '网易云短信', 'NETEASE', 'sys_message_supplier_type', NULL, 'primary', 'N', '1', 103, 1, now(), 1, now(), NULL);
INSERT INTO `sys_dict_data` VALUES (1673955954198032385, '000000', 0, '模板ID', 'TEMPLATE_ID', 'sys_message_template_mode', NULL, 'primary', 'N', '1', 103, 1, now(), 1, now(), NULL);
INSERT INTO `sys_dict_data` VALUES (1673956012679213057, '000000', 1, '模板内容', 'TEMPLATE_CONTENT', 'sys_message_template_mode', NULL, 'primary', 'N', '1', 103, 1, now(), 1, now(), NULL);
