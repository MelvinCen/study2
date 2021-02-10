1，创建springboot项目
2，添加依赖
3，配置文件
4，
传统方式：pojo-dao(连接mybatis，配置mybatis.xml文件)-service-controller
使用了mybatis-plus之后
4.1，写实体类
4.2，写接口mapper继承baseMapper
4.3，application类中配置扫描
4.4，写测试使用
5，配置日志在properties文件
6，CRUD操作
7，主键生成策略
8，自动填充，创建时间和更新时间
9，乐观锁,
更新实体类
添加乐观锁注解
配置
实现方式，表加version字段，每次增删改判断旧version值，更改时同时插入新的version（oldVersion+1）
实体类增加version字段，并注解mybais的@version
在mybatis配置类中增加乐观锁拦截器配置
10，mybatis分页，在mybatis配置类配置
11，用mybatis的baseMapper CRUD
12，逻辑删除
并不是物理删除，依旧在数据库，只是增加了一个deleted字段并标记为删除值
新版本是配置文件+注解
13，条件构造器wrapper

