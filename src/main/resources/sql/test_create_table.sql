drop table if exists `person`;
create table `person`
(
    `user_id`  INTEGER auto_increment primary key                         not null comment '用户id',
    `name`     varchar(20)                                                not null comment '名字',
    `birthday` timestamp                                                  not null comment '生日',
    `edu`      enum ("primary","middle","high","graduate","master","phd") not null comment '教育程度',
    `marry`    enum ("married","unmarried","divorced","widower","widow")  not null comment '婚姻状况',
    `income`   int                                                        not null comment '收入',
    `gender`   enum ("male","female")                                     not null comment '性别',
    `career`   varchar(30)                                                not null comment '职业'
) ENGINE = INNODB comment '用户基本信息表';

drop table if exists `training`;
create table `training`
(
    `training_id`    integer primary key auto_increment                  not null comment '训练id',
    `user_id`        INTEGER                                             not null comment '用户id',
    `training_start` timestamp                                           not null comment '训练开始时间',
    `training_end`   timestamp                                           not null comment '训练结束时间',
    `training_kind`  enum ("strength","endurance","speed","flexibility") not null comment '训练种类',
    `coach`          varchar(20)                                         not null comment '教练名称',
    `position`       enum ("outdoor","playground","pool")                not null comment '训练地点',
    `performance`    enum ("A+","A","A-","B+","B","B-","C+","C","C-")    not null COMMENT '训练评价'
) ENGINE = INNODB comment '训练表';



drop table if exists `diet`;
create table `diet`
(
    `diet_id`          integer primary key auto_increment not null comment '饮食id',
    `user_id`          INTEGER                            not null comment '用户id',
    `diet_start`       timestamp                          not null comment '饮食开始时间',
    `diet_end`         timestamp                          not null comment '饮食结束时间',
    `staple_kind`      varchar(20)                        not null comment '主食种类',
    `staple_amount`    int                                not null comment '主食量(克)',
    `water`            int                                not null comment '水的摄入量',
    `protein`          int                                not null comment '蛋白质摄入量(克)',
    `vegetable_kind`   varchar(20)                        not null comment '摄入蔬菜种类',
    `vegetable_amount` int                                not null comment '摄入蔬菜量(克)'
) ENGINE = INNODB comment '饮食表';
