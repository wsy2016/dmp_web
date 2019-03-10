环境配置
1）你们的集群规模？
这个得看个人在公司的规模，下面介绍一下我们公司的一些配置：

 

联想System x3750  服务器，价格3.5万，内存容量32G，产品类型机架式，
硬盘接口SSD,CPU频率2.6GH,CPU数量2颗，三级缓存15MB,cpu核心6核，
cpu线程数12线程,最大内存支持1.5T,网络是千兆网卡,可插拔时硬盘接口12个卡槽,配置1T的容量

 

详细：http://detail.zol.com.cn/server/index1101243.shtml

 

名字                               软件                     运行管理

Hadoop1                           JDK,hadoop                namenode

Hadoop2                           JDK,hadoop                namenode

Hadoop3                           JDK,hadoop                secondaryNamenode

Hadoop4                           JDK,hadoop                secondaryNamenode

Hadoop5                           JDK,hadoop                datanode

Hadoop6                           JDK,hadoop                datanode

Hadoop7                           JDK,hadoop                datanode

Hadoop8                           JDK,hadoop                datanode

Hadoop9                           JDK,hadoop                datanode

Hadoop10                          JDK,zookeeper,tomcat,mvn,kafka leader

Hadoop11                          JDK,zookeeper,tomcat,mvn,kafka  follower

Hadoop12                          JDK,zookeeper,tomcat,mvn,kafka  follower

Hadoop13                          JDK,hive,mysql,svn,logstarh    hive,mysql,svn

Hadoop14                          JDK,hbase,mysql备份        datanode

Hadoop15                          JDK,nginx,Log日志手机       datanode

 

 

数据就是每天访问的Log日志不是很大，有的时候大有的时候小的可怜

 

2)你在项目中遇到了哪些难题，是怎么解决的？
1、在执行任务时发现副本的个数不对，经过一番的查找发现是超时的原因，修改了配置文件hdfs-site.xml：中修改了超时时间。

2、由于当时在分配各个目录空间大小时，没有很好的分配导致有的目录的空间浪费，于是整体商量后把储存的空间调大了一些。

 

 

设计题
1-1）采集nginx产生的日志，日志的格式为user  ip   time  url   htmlId  每天产生的文件的数据量上亿条，
请设计方案把数据保存到HDFS上，并提供一下实时查询的功能（响应时间小于3s）

A、某个用户某天访问某个URL的次数

B、某个URL某天被访问的总次数

 

实时思路是：使用Logstash + Kafka + Spark-streaming + Redis + 报表展示平台

离线的思路是：Logstash + Kafka + Elasticsearch +  Spark-streaming + 关系型数据库

 

A、B、数据在进入到Spark-streaming 中进行过滤，把符合要求的数据保存到Redis中