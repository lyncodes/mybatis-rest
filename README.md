@[TOC](一定能看懂的Spring boot + Mybatis + Mysql搭建Restful服务)
github地址:https://github.com/lyncodes/mybatis-rest
如果有用,给我一个赞!!!

一直被spring boot 和mybatis整合的问题所困扰,由于spring boot大量使用了反射机制,虽然说是方便了熟手快速开发一个服务,但是对于新手却是比较难上手的,所以我再趟过了无数的坑之后,决定回馈社区,帮助更多的人.

## 综述
首先,你要明白你的restful api要对外界提供那些资源,说白了也就是那些数据需要被塞到json里面去,所以就先准备数据,那自然是自己先往数据库先创建一些咯,然后通过mybatis在spring boot和mysql之间做桥梁,完成数据的CRUD,然后spring boot再把获取的数据打包成json传到客户端或者从客户端接收到表单,然后把数据存到数据库.
所以步骤如下
1. 往数据库先塞数据
2. 创建根据数据库的字段创建相应的实体类
3. 创建dao层负责和数据库的交互
4. 创建service层作为controller和dao层的中介(业务逻辑也是在这里发生,但是我们的demo不会写具体的复杂业务逻辑)
5. controller负责和客户端进行数据的交互





![在这里插入图片描述](https://img-blog.csdnimg.cn/20190827215403925.png)
## 建表和插入数据
```sql
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

```
注意三张表的数据类型使用了时间戳和枚举类型,并且所有字段不允许为空,有助于提高鲁棒性,并且方便后续做数据分析的清洗.
接下来是插入数据,由于我在开发环境下,需要常常删表和重新恢复数据,所以这里用来删除自增主键,然后插入数据,然后再恢复自增主键的sql代码.
```sql
alter table test.person
    modify user_id int;
alter table test.person
    drop primary key;


INSERT INTO test.person (user_id, name, birthday, edu, marry, income, gender, career) VALUES (1, 'tom', '2019-08-27 15:53:34', 'graduate', 'married', 10000, 'male', 'engineer');
INSERT INTO test.person (user_id, name, birthday, edu, marry, income, gender, career) VALUES (2, 'jim', '2019-08-27 16:26:34', 'phd', 'unmarried', 20000, 'male', 'docter');
# -----------------插入---------------------
alter table test.person
    add primary key (user_id);
alter table test.person
    modify user_id int auto_increment;

# ==================下一个表=====================
alter table test.diet
    modify diet_id int;
alter table test.diet
    drop primary key;


INSERT INTO test.diet (diet_id, user_id, diet_start, diet_end, staple_kind, staple_amount, water, protein, vegetable_kind, vegetable_amount) VALUES (1, 1, '2019-08-27 16:16:21', '2019-08-27 16:16:23', 'rice', 100, 300, 30, 'potato', 200);
INSERT INTO test.diet (diet_id, user_id, diet_start, diet_end, staple_kind, staple_amount, water, protein, vegetable_kind, vegetable_amount) VALUES (2, 1, '2019-08-27 16:16:45', '2019-08-27 16:16:47', 'noddle', 200, 400, 40, 'tomato', 300);
INSERT INTO test.diet (diet_id, user_id, diet_start, diet_end, staple_kind, staple_amount, water, protein, vegetable_kind, vegetable_amount) VALUES (3, 1, '2019-08-27 16:17:54', '2019-08-27 16:17:56', 'pizza', 200, 200, 50, 'onion', 100);
# -----------------插入---------------------


alter table test.diet
    add primary key (diet_id);
alter table test.diet
    modify diet_id int auto_increment;

# ==================下一个表=====================
alter table test.training
    modify training_id int;
alter table test.training
    drop primary key;



INSERT INTO test.training (training_id, user_id, training_start, training_end, training_kind, coach, position, performance) VALUES (1, 1, '2019-08-27 16:19:11', '2019-08-27 16:19:13', 'speed', 'jim', 'outdoor', 'A+');
INSERT INTO test.training (training_id, user_id, training_start, training_end, training_kind, coach, position, performance) VALUES (2, 1, '2019-08-27 16:19:36', '2019-08-27 16:19:37', 'endurance', 'jack', 'playground', 'B');
INSERT INTO test.training (training_id, user_id, training_start, training_end, training_kind, coach, position, performance) VALUES (3, 1, '2019-08-27 16:20:59', '2019-08-27 16:21:00', 'flexibility', 'john', 'pool', 'A');
INSERT INTO test.training (training_id, user_id, training_start, training_end, training_kind, coach, position, performance) VALUES (4, 2, '2019-08-27 16:27:34', '2019-08-27 16:27:35', 'flexibility', 'jim', 'playground', 'A-');
# -----------------插入---------------------


alter table test.training
    add primary key (training_id);
alter table test.training
    modify training_id int auto_increment;

# ==================下一个表=====================
```
分别是两个用户的用户信息和训练,饮食记录,使用user_id作为三张表关联的外键,并且person表的user_id作为自增主键,保证一致性.


## 项目结构
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190827220717339.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2x5bmNvZGU=,size_16,color_FFFFFF,t_70)

如上面的图所示,用户的请求到达controller后,会把服务转发到service层,然后根据service层的接口中的函数,去接口的实现类中去拿相应的数据,这时候请求被转发到dao层,dao层只有interface,interface中的函数则不会用过java代码去执行了,而是通过了反射机制,转发到了resource/mybatis/mappers中写好的xml配置文件,这个时候才会去执行xml中定义好的sql语句,拿到真正的数据后,再一层一层往上回传到controller,在返回给请求端.

## 数据字段
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190827221328537.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2x5bmNvZGU=,size_16,color_FFFFFF,t_70)
可以看见training表有若干字段,然后在entity中建立好对应的实体类Training,java,

其实就是一个pojo,`记得要自动生成所有字段的getter 和 setter,还有constructor`

```java
public class Training {

    private long trainingId;
    private long userId;
    private java.sql.Timestamp trainingStart;
    private java.sql.Timestamp trainingEnd;
    private String trainingKind;
    private String coach;
    private String position;
    private String performance;

    public Training(long trainingId, long userId, Timestamp trainingStart,
                    Timestamp trainingEnd, String trainingKind, String coach,
                    String position, String performance) {
        this.trainingId = trainingId;
        this.userId = userId;
        this.trainingStart = trainingStart;
        this.trainingEnd = trainingEnd;
        this.trainingKind = trainingKind;
        this.coach = coach;
        this.position = position;
        this.performance = performance;
    }
    

    public long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(long trainingId) {
        this.trainingId = trainingId;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public java.sql.Timestamp getTrainingStart() {
        return trainingStart;
    }

    public void setTrainingStart(java.sql.Timestamp trainingStart) {
        this.trainingStart = trainingStart;
    }


    public java.sql.Timestamp getTrainingEnd() {
        return trainingEnd;
    }

    public void setTrainingEnd(java.sql.Timestamp trainingEnd) {
        this.trainingEnd = trainingEnd;
    }


    public String getTrainingKind() {
        return trainingKind;
    }

    public void setTrainingKind(String trainingKind) {
        this.trainingKind = trainingKind;
    }


    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

}
```

## dao层

dao层负责了实体类和数据库表的映射,所以在这里编写数据交互的接口
`spring.boot.mybatisrest.dao.Training.TrainingMapper.java`
```java
// spring.boot.mybatisrest.dao.Training.TrainingMapper.java
package spring.boot.mybatisrest.dao.Training;

import org.springframework.stereotype.Repository;
import spring.boot.mybatisrest.entity.Training;

import java.util.List;

@Repository
public interface TrainingMapper {
    List<Training> getTrainings(int user_id);
}

```
关键点来了,这里的`getTrainings`函数是没有对应java代码实现的,而是通过了配置文件来实现的,也就是对应的training-mapper.xml
`resources/mybatis/mappers/training-mapper.xml`

其中的select标签中,id后就跟着上面的`getTrainings`函数,resultType则是把查询回来的数据映射到这个类型的实体类中,也就是training类,(**所以,,我们的实体类需要显示的声明构造函数**), 当然,这个sql语句是很简单的,只需要说明一点就是变量的获取是`#{}`,相似的操作在linux中,其实就是`${}`,所以还是很好理解的.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="spring.boot.mybatisrest.dao.Training.TrainingMapper">

    <select id="getTrainings" resultType="spring.boot.mybatisrest.entity.Training">
        SELECT * from training where user_id = #{user_id}
    </select>

</mapper>
```

再往上的内容,其实就很简单了,就是controller到service层,再到service接口的实现类,为了简单起见,只实现了一个查询功能,其余的功能大家可以自行试试看.
`spring.boot.mybatisrest.service.Training.TrainingService.java`
```java
package spring.boot.mybatisrest.service.Training;

import spring.boot.mybatisrest.entity.Training;

import java.util.List;

public interface TrainingService {

    List<Training> getTrainings(int user_id);

    TrainingService addTraining(int user_id);

    TrainingService deleteTraining(int training_id);

    TrainingService editTraining(int training_id);
}

```
`spring.boot.mybatisrest.service.Training.TrainingServiceImpl.java`
主要要把实现类加上@Service的注解
可以清晰的看到,这里就是通过去调用的mapper接口的函数去实现了和数据库的交互
```java
 	package spring.boot.mybatisrest.service.Training;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.mybatisrest.dao.Training.TrainingMapper;
import spring.boot.mybatisrest.entity.Training;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {


    @Autowired
    private TrainingMapper trainingMapper;

    @Override
    public List<Training> getTrainings(int user_id) {
        return trainingMapper.getTrainings(user_id);
    }

    @Override
    public TrainingService addTraining(int user_id) {
        return null;
    }

    @Override
    public TrainingService deleteTraining(int training_id) {
        return null;
    }

    @Override
    public TrainingService editTraining(int training_id) {
        return null;
    }
}

```

最后,不要忘了修改主启动类`MybatisRestAppliation.ajva`
一定要加上MapperScan,中间添加上你的mapper所在的路径
```java
package spring.boot.mybatisrest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("spring.boot.mybatisrest.dao")
public class MybatisRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisRestApplication.class, args);
    }

}

```
还有整个project的全局配置文件,指定mybatis的配置文件和所有的mapper映射文件的路径,当然还有数据库的相关配置
```yml
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/test
    username: root
    password: root

mybatis:
  mapper-locations: classpath:mybatis/mappers/*.xml
  config-location: classpath:mybatis/mybatis-config.xml
```
运行一下,OK
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190827224821413.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2x5bmNvZGU=,size_16,color_FFFFFF,t_70)

