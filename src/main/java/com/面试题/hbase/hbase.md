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

12 hbase怎么预分区？
13.hbase怎么给web前台提供接口来访问？
14.htable API有没有线程安全问题，在程序中是单例还是多例？
15.我们的hbase大概在公司业务中（主要是网上商城）大概都几个表，几个表簇，大概都存什么样的数据？
16.hbase的并发问题？

Hbase 相关
6-1）Hbase 的 rowkey 怎么创建比较好？列族怎么创建比较好？
 

 

Rowkey是一个二进制码流，Rowkey的长度被很多开发者建议说设计在10~100个字节，不过建议是越短越好，不要超过16个字节。在查找时有索引会加快速度。

 

Rowkey散列原则 、 Rowkey唯一原则 、 针对事务数据Rowkey设计 、 针对统计数据的Rowkey设计 、 针对通用数据的Rowkey设计、 支持多条件查询的RowKey设计。 

 

 

总结设计列族：

1、一般不建议设计多个列族

2、数据块的缓存的设计

3、激进缓存设计

4、布隆过滤器的设计(可以提高随机读取的速度)

5、生产日期的设计

6、列族压缩

7、单元时间版本

 

6-2）Hbase 的实现原理
Hbase  的实现原理是rpc Protocol 

 

6-3) hbase 过滤器实现原则
感觉这个问题有问题，过滤器多的是啦，说的是哪一个不知道!!!!

Hbase的过滤器有：RowFilter、PrefixFilter、KeyOnlyFilter、RandomRowFilter、InclusiveStopFilter、FirstKeyOnlyFilter、ColumnPrefixFilter、ValueFilter、ColumnCountGetFilter、SingleColumnValueFilter、SingleColumnValueExcludeFilter、WhileMatchFilter、FilterList 

   你看这么多过滤波器呢，谁知道你问的那个啊！！

   比较常用的过滤器有：RowFilter 一看就知道是行的过滤器，来过滤行的信息。PrefixFilter前缀的过滤器，就是把前缀作为参数来查找数据呗！剩下的不解释了看过滤器的直接意思就OK了很简单。

 

6-4）描述 HBase, zookeeper 搭建过程
Zookeeper 的问题楼上爬爬有步骤，hbase 主要的配置文件有hbase.env.sh 主要配置的是JDK的路径以及是否使用外部的ZK，hbase-site.xml 主要配置的是与HDFS的链接的路径以及zk的信息，修改regionservers的链接其他机器的配置。

 

6-5）hive 如何调优？
在优化时要注意数据的问题，尽量减少数据倾斜的问题，减少job的数量，
同事对小的文件进行成大的文件，如果优化的设计那就更好了，因为hive的运算就是mapReduce所以调节mapreduce的参数也会使性能提高，如调节task的数目。

 

6-6）hive的权限的设置
Hive的权限需要在hive-site.xml文件中设置才会起作用，配置默认的是false，需要把hive.security.authorization.enabled设置为true，
并对不同的用户设置不同的权限，例如select ,drop等的操作。

 

6-7 ) hbase 写数据的原理
 

1. 首先，Client通过访问ZK来请求目标数据的地址。

2. ZK中保存了-ROOT-表的地址，所以ZK通过访问-ROOT-表来请求数据地址。

3. 同样，-ROOT-表中保存的是.META.的信息，通过访问.META.表来获取具体的RS。

4. .META.表查询到具体RS信息后返回具体RS地址给Client。

5. Client端获取到目标地址后，然后直接向该地址发送数据请求。

 

 

6-8）hbase宕机了如何处理？
HBase的RegionServer宕机超过一定时间后，HMaster会将其所管理的region重新分布到其他活动的RegionServer上，由于数据和日志都持久在HDFS中，
该操作不会导致数据丢失。所以数据的一致性和安全性是有保障的。
但是重新分配的region需要根据日志恢复原RegionServer中的内存MemoryStore表，这会导致宕机的region在这段时间内无法对外提供服务。
而一旦重分布，宕机的节点重新启动后就相当于一个新的RegionServer加入集群，为了平衡，需要再次将某些region分布到该server。 
因此，Region Server的内存表memstore如何在节点间做到更高的可用，是HBase的一个较大的挑战。

 

6-9）Hbase 中的 metastore 用来做什么的？
Hbase的metastore是用来保存数据的，其中保存数据的方式有有三种第一种于第二种是本地储存，第二种是远程储存这一种企业用的比较多

 

6-10)hbase客户端在客户端怎样优化？
Hbase使用JAVA来运算的，索引Java的优化也适用于hbase，在使用过滤器事记得开启bloomfilter可以是性能提高3-4倍，设置HBASE_HEAPSIZE设置大一些

 

6-11）hbase是怎样预分区的？
如何去进行预分区，可以采用下面三步：
  1.取样，先随机生成一定数量的rowkey,将取样数据按升序排序放到一个集合里
  2.根据预分区的region个数，对整个集合平均分割，即是相关的splitKeys.
  3.HBaseAdmin.createTable(HTableDescriptor tableDescriptor,byte[][] splitkeys)可以指定预分区的splitKey，即是指定region间的rowkey临界值

6-12）怎样将 mysql 的数据导入到 hbase 中？
不能使用 sqoop，速度太慢了，提示如下：

A、一种可以加快批量写入速度的方法是通过预先创建一些空的 regions，这样当

数据写入 HBase 时，会按照 region 分区情况，在集群内做数据的负载均衡。

B、hbase 里面有这样一个 hfileoutputformat 类，他的实现可以将数据转换成 hfile

格式，通过 new 一个这个类，进行相关配置,这样会在 hdfs 下面产生一个文件，这个

时候利用 hbase 提供的 jruby 的 loadtable.rb 脚本就可以进行批量导入。

 

6-13）谈谈 HBase 集群安装注意事项？
     需要注意的地方是 ZooKeeper 的配置。这与 hbase-env.sh 文件相关，文件中

HBASE_MANAGES_ZK 环境变量用来设置是使用 hbase 默认自带的 Zookeeper 还

是使用独立的 ZooKeeper。HBASE_MANAGES_ZK=false 时使用独立的，为 true 时

使用默认自带的。

    某个节点的 HRegionServer 启动失败，这是由于这 3 个节点的系统时间不一致相

差超过集群的检查时间 30s。

6-14)简述 HBase 的瓶颈
Hbase主要的瓶颈就是传输问题，在操作时大部分的操作都是需要对磁盘操作的

 

6-15）Redis, 传统数据库,hbase,hive  每个之间的区别
Redis 是基于内存的数据库，注重实用内存的计算，hbase是列式数据库，无法创建主键，地从是基于HDFS的，每一行可以保存很多的列，
hive是数据的仓库，是为了减轻mapreduce而设计的，不是数据库是用来与红薯做交互的。

 

6-16）Hbase 的特性,以及你怎么去设计 rowkey 和 columnFamily ,怎么去建一个 table
因为hbase是列式数据库，列非表schema的一部分，所以只需要考虑rowkey和columnFamily 即可，rowkey有为的相关性，最好数据库添加一个前缀，文件越小，查询速度越快，再设计列是有一个列簇，但是列簇不宜过多。

 

 

6-17）Hhase与hive的区别
     Apache HBase是一种Key/Value系统，它运行在HDFS之上。
	 和Hive不一样，Hbase的能够在它的数据库上实时运行，而不是运行MapReduce任务。
	 Hive被分区为表格，表格又被进一步分割为列簇。列簇必须使用schema定义，列簇将某一类型列集合起来（列不要求schema定义）。
	 例如，“message”列簇可能包含：“to”, ”from” “date”, “subject”, 和”body”. 每一个 key/value对在Hbase中被定义为一个cell，
	 每一个key由row-key，列簇、列和时间戳。在Hbase中，行是key/value映射的集合，这个映射通过row-key来唯一标识。Hbase利用Hadoop的基础设施，
	 可以利用通用的设备进行水平的扩展。

 

6-18）描述hbase的scan和get功能以及实现的异同
 

      HBase的查询实现只提供两种方式： 
	  1、按指定RowKey获取唯一一条记录，get方法（org.apache.hadoop.hbase.client.Get）
	  2、按指定的条件获取一批记录，scan方法（org.apache.hadoop.hbase.client.Scan） 实现条件查询功能使用的就是scan方式

 

6-19）HBase scan setBatch和setCaching的区别
      can可以通过setCaching与setBatch方法提高速度（以空间换时间），

setCaching设置的值为每次rpc的请求记录数，默认是1；cache大可以优化性能，但是太大了会花费很长的时间进行一次传输。

   setBatch设置每次取的column size；有些row特别大，所以需要分开传给client，就是一次传一个row的几个column。

6-20）hbase 中cell的结构
cell中的数据是没有类型的，全部是字节码形式存贮。

 

6-21）hbase 中region太多和region太大带来的冲突
Hbase的region会自动split，当region太时，regio太大时分布会不均衡，同时对于大批量的代入数据建议如下：

1、还是必须让业务方对rowkey进行预分片，对业务数据rowkey进行md5或者其他的hash策略，让数据尽量随机分布而不是顺序写入。

2、随时观察region的大小，是否出现大region的情况。
