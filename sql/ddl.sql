-- 创建数据库
CREATE DATABASE account;

-- 微信用户信息表
CREATE TABLE wechat_user (
    wechat_openid varchar(36) DEFAULT NULL COMMIT '微信openid',
    nick_name varchar(36) DEFAULT NULL COMMIT '用户昵称',
    avatar_url varchar(255) DEFAULT NULL COMMIT '用户头像',
    gender tinyint(4) DEFAULT NULL COMMIT '用户性别,值为1时是男性，值为2时是女性，值为0时是未知',
    city varchar(36) DEFAULT NULL COMMIT '用户所在城市',
    province varchar(36) DEFAULT NULL COMMIT '用户所在省份',
    country varchar(36) DEFAULT NULL COMMIT '用户所在国家',
    continuity_account_days int DEFAULT NULL COMMENT '连续记账天数',
    create_time datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
    last_login_time datetime NOT NULL DEFAULT NOW() COMMENT '最后登录时间',
    last_account_time datetime DEFAULT NULL COMMENT '最后记账时间',
    PRIMARY KEY (`wechat_openid`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '微信用户信息表'

-- 账本列表 
CREATE TABLE account_books (
    id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账本id',
    name varchar(36) NOT NULL COMMENT '账本名称',
    cover_img varchar(255) DEFAULT NULL COMMENT '账本封面url',
    owner_id varchar(36) NOT NULL COMMENT '账本拥有者',
    creator_id varchar(36) NOT NULL COMMENT '账本创建者',
    default_book varchar(4) DEFAULT NULL COMMENT '是否是默认账本',
    budgetary_amount int DEFAULT 0 COMMENT '预算金额（分）', 
    create_time datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
    last_account_time datetime DEFAULT NULL COMMENT '最后记账时间',
    PRIMARY KEY (`wechat_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '账本'

-- 周期账

-- 账本参与者
CREATE TABLE account_parters (
    book_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账本id',
    wechat_openid varchar(36) DEFAULT NULL COMMIT '微信用户openid',
    PRIMARY KEY (book_id, wechat_openid),
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '账本参与者'

-- 账本记录
CREATE TABLE account_record (
    id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账本记录id',
    book_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账本id',
    amount int NOT NULL COMMENT '金额',
    record_type tinyint(4) NOT NULL COMMENT '收入(1)or支出(2)',
    main_type bigint(20) NOT NULL COMMENT '主要分类',
    secondary_type bigint(20) DEFAULT NULL COMMENT '次要分类',
    pay_income_way bigint(20) DEFAULT NULL COMMENT '收支途径',
    creator_id varchar(36) NOT NULL COMMENT '创建者',
    remark text DEFAULT NULL COMMENT '备注',
    record_time datetime DEFAULT NULL COMMENT '记录关联日期',
    create_time datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_creator_id` (`creator_id`),
    KEY `idx_record_type` (`record_type`),
    KEY `idx_book_id` (`book_id`),
    KEY `idx_record_time` (`record_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '账本记录了'

-- 账本记录关联的用户


-- 支出分类 
CREATE TABLE expenditure_type (
    id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类id',
    pid bigint(20) NOT NULL COMMENT '分类父id',
    name varchar(36) NOT NULL COMMENT '分类名称',
    creator_id varchar(36) DEFAULT '-1' COMMENT '分类创建者， -1表示所有人都有',
    icon_url varchar(255) DEFAULT NULL COMMENT '分类icon url'
    PRIMARY KEY (id),
    KEY `idx_creator_id` (`creator_id`),
    KEY `idx_pid` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '支出分类'

-- 收入分类
CREATE TABLE income_type (
    id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类id',
    pid bigint(20) NOT NULL COMMENT '分类父id',
    name varchar(36) NOT NULL COMMENT '分类名称',
    creator_id varchar(36) DEFAULT '-1' COMMENT '分类创建者， -1表示所有人都有',
    icon_url varchar(255) DEFAULT NULL COMMENT '分类icon url'
    PRIMARY KEY (id),
    KEY `idx_creator_id` (`creator_id`),
    KEY `idx_pid` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '收入分类'


-- 收支方式
CREATE TABLE pay_income_ways (
    id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '方式id',
    name varchar(36) NOT NULL COMMENT '收支方式名称',
    creator_id varchar(36) DEFAULT '-1' COMMENT '收支方式创建者， -1表示所有人都有',
    PRIMARY KEY (id),
    KEY `idx_creator_id` (`creator_id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '收支方式'

