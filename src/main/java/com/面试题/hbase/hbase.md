1、Hbase的rowKey怎么创建比较好？列簇怎么创建比较好？
答：
rowKey最好要创建有规则的rowKey，即最好是有序的。
经常需要批量读取的数据应该让他们的rowkey连续；
将经常需要作为条件查询的关键词组织到rowkey中；
 列族的创建：
按照业务特点，把数据归类，不同类别的放在不同列族

2、hbase内部机制是什么
答：
Hbase是一个能适应联机业务的数据库系统
物理存储：hbase的持久化数据是存放在hdfs上
存储管理：一个表是划分为很多region的，这些region分布式地存放在很多regionserver上
Region内部还可以划分为store，store内部有memstore和storefile
版本管理：hbase中的数据更新本质上是不断追加新的版本，通过compact操作来做版本间的文件合并
Region的split
集群管理：zookeeper  + hmaster（职责）  + hregionserver（职责）

3、hbase过滤器实现原则
答：可以说一下过滤器的父类（比较过滤器，专用过滤器）
过滤器有什么用途：
增强hbase查询数据的功能
减少服务端返回给客户端的数据量

4、HBase写数据的原理是什么？

5、.HBase宕机如何处理
宕机分为
 --HMaster宕机，HMaster会将其所管理的region重新分布到其他活动的RegionServer上，
    由于数据和日志都持久在HDFS中，该操作不会导致数据丢失。所以数据的一致性和安全性是有保障的。
 --HMaster宕机:HMaster没有单点问题，HBase中可以启动多个HMaster，
    通过Zookeeper的Master Election机制保证总有一个Master运行。
    即ZooKeeper会保证总会有一个HMaster在对外提供服务。

6、hbase 怎么预分区？
建表时可以通过shell命令预分区，也可以在代码中建表做预分区

7、hbase 怎么给 web 前台提供接口来访问（HTABLE可以提供对 HBase的访问，但是怎么查询同一条记录的多个版本数据）？
答：使用HTable来提供对HBase的访问，可以使用时间戳来记录一条数据的多个版本。

8.HRegionServer启动不正常
jps检查节点进程是否启动，检查日志：Server slave2,60020,1405560498057 has been rejected; 
Reported time is too far out of sync with master，将节点时间同步即可

10.HBase shell执行list命令报错：
错误信息：client.HConnectionManager$HConnectionImplementation: Can't get connection to ZooKeeper: KeeperErrorCode = ConnectionLoss for /hbase 
解决方法：检查防火墙是否关闭，关闭防火墙

11.HBase Shell 增删改异常
错误信息：zookeeper.ClientCnxn: Session 0x0 for server null,unexpected error, closing socket connection and attempting reconnect 
解决方法：将hadoop中hadoop【对应版本】相关的jar包copy过来（${HABASE_HOME}/lib）替换可。
