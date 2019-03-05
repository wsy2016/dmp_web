第1部分           选择题

1.1     Hadoop选择题
1.1.1  Hdfs
1.      下面哪个程序负责 HDFS 数据存储？ c

a)NameNode  

b)Jobtracker  

c)Datanode  

d)secondaryNameNode  

e)tasktracker

2.      HDfS 中的 block 默认保存几份？ a

a)3份

b)2份

c)1份

d)不确定

3.      下列哪个程序通常与NameNode 在一个节点启动？ c

a)SecondaryNameNode

b)DataNode

c)TaskTracker

d)Jobtracker

注：haoop1.X

分析：

hadoop 的集群是基于 master/slave 模式，namenode 和 jobtracker 属于 master，datanode 和 tasktracker属于 slave，
master 只有一个，而 slave 有多个。SecondaryNameNode 内存需求和 NameNode 在一个数量级上，
所以通常 secondary NameNode（运行在单独的物理机器上）和 NameNode 运行在不同的机器上。

JobTracker 和 TaskTracker

JobTracker 对应于 NameNode

TaskTracker 对应于 DataNode

DataNode 和 NameNode 是针对数据存放来而言的

JobTracker 和 TaskTracker 是对于 MapReduce 执行而言的

mapreduce 中几个主要概念，mapreduce 整体上可以分为这么几条执行线索：

jobclient，JobTracker 与 TaskTracker。

1、JobClient 会在用户端通过 JobClient 类将应用已经配置参数打包成 jar 文件存储到 hdfs，并把路径提交到 Jobtracker,然后由 JobTracker 创建每一个 Task（即 MapTask 和 ReduceTask）并将它们分发到各个 TaskTracker 服务中去执行

2、JobTracker 是一个 master 服务，软件启动之后 JobTracker 接收 Job，负责调度 Job 的每一个子任务 task运行于 TaskTracker 上，并监控它们，如果发现有失败的 task 就重新运行它。一般情况应该把 JobTracker 部署在单独的机器上。

3、TaskTracker 是运行在多个节点上的 slaver 服务。TaskTracker 主动与 JobTracker 通信，接收作业，并负责直接执行每一个任务。TaskTracker 都需要运行在 HDFS 的 DataNode 上

4.      HDFS 默认 Block Size

a)32MB  

b)64MB

c)128MB

注：旧版本是64MB

5.      Client 端上传文件的时候下列哪项正确

a)数据经过 NameNode 传递给 DataNode

b)Client 端将文件切分为 Block，依次上传

c)Client 只上传数据到一台 DataNode，然后由 NameNode 负责 Block 复制工作

分析：

Client 向 NameNode 发起文件写入的请求。

NameNode 根据文件大小和文件块配置情况，返回给 Client 它所管理部分 DataNode 的信息。

Client 将文件划分为多个 Block，根据 DataNode 的地址信息，按顺序写入到每一个 DataNode 块中。

6.      下面与 HDFS 类似的框架是？C

A NTFS

B FAT32

C GFS

D EXT3

7.      的

8.      的

1.1.2  集群管理
1.      下列哪项通常是集群的最主要瓶颈

a)CPU  

b)网络  

c)磁盘 IO  

d)内存

解析：

由于大数据面临海量数据，读写数据都需要 io，然后还要冗余数据，hadoop 一般备 3 份数据，所以 IO就会打折扣。

2.      关于SecondaryNameNode 哪项是正确的？c

a)它是 NameNode 的热备

b)它对内存没有要求

c)它的目的是帮助 NameNode 合并编辑日志，减少 NameNode 启动时间

d)SecondaryNameNode 应与 NameNode 部署到一个节点

3.      下列哪项可以作为集群的管理？

a)Puppet  b)Pdsh  c)ClouderaManager  d)Zookeeper

分析：

A：puppetpuppet 是一种 Linux、Unix、windows 平台的集中配置管理系统

B：pdsh 可以实现在在多台机器上执行相同的命令

详细参考：集群管理小工具介绍-pdsh

C：可以参考 Cloudera Manager 四大功能【翻译】

首先这里给管理下一个定义：部署、配置、调试、监控，属于管理


4.      配置机架感知的下面哪项正确

a)如果一个机架出问题，不会影响数据读写

b)写入数据的时候会写到不同机架的 DataNode 中

c)MapReduce 会根据机架获取离自己比较近的网络数据

5.      下列哪个是 Hadoop 运行的模式

a)单机版 b)伪分布式 c)分布式

6.      Cloudera 提供哪几种安装 CDH 的方法

a)Cloudera manager  b)Tarball  c)Yum  d)Rpm

7.      的

8.       D d

9.      的

10.  的

1.2     Hbase选择题
1.2.1  Hbase基础
1.      HBase 来源于哪篇博文？ C

A TheGoogle File System

BMapReduce

CBigTable

D Chubby

2.      下面对 HBase 的描述哪些是正确的？ B、C、D

A 不是开源的

B 是面向列的

C 是分布式的

D 是一种 NoSQL 数据库

3.      HBase 依靠（）存储底层数据 A

A HDFS

B Hadoop

C Memory

DMapReduce

4.      HBase 依赖（）提供消息通信机制 A

A Zookeeper

B Chubby

C RPC

D Socket

5.      HBase 依赖（）提供强大的计算能力 D

A Zookeeper

B Chubby

C RPC

D MapReduce

6.      MapReduce 与 HBase 的关系，哪些描述是正确的？ B、C

A 两者不可或缺，MapReduce 是 HBase 可以正常运行的保证

B 两者不是强关联关系，没有 MapReduce，HBase 可以正常运行

C MapReduce 可以直接访问 HBase

D 它们之间没有任何关系

7.      下面哪些选项正确描述了HBase 的特性？ A、B、C、D

A 高可靠性

B 高性能

C 面向列

D 可伸缩

8.      下面哪些概念是 HBase 框架中使用的？A、C

A HDFS

B GridFS

CZookeeper

D EXT3

9.       D

1.2.2  Hbase核心

1.      LSM 含义是？A

A 日志结构合并树

B 二叉树

C 平衡二叉树

D 长平衡二叉树

2.      下面对 LSM 结构描述正确的是？ A、C

A 顺序存储

B 直接写硬盘

C 需要将数据 Flush 到磁盘

D 是一种搜索平衡树

3.      LSM 更能保证哪种操作的性能？B

A 读

B 写

C 随机读

D 合并

4.      LSM 的读操作和写操作是独立的？A

A 是。

B 否。

C LSM 并不区分读和写

D LSM 中读写是同一种操作

5.      LSM 结构的数据首先存储在（）。 B

A 硬盘上

B 内存中

C 磁盘阵列中

D 闪存中

6.      HFile 数据格式中的 Data 字段用于（）。A

A 存储实际的 KeyValue 数据

B 存储数据的起点

C 指定字段的长度

D 存储数据块的起点

7.      HFile 数据格式中的 MetaIndex 字段用于（）。D

A Meta 块的长度

B Meta 块的结束点

C Meta 块数据内容

D Meta 块的起始点

8.      HFile 数据格式中的 Magic 字段用于（）。A

A 存储随机数，防止数据损坏

B 存储数据的起点

C 存储数据块的起点

D 指定字段的长度

9.      HFile 数据格式中的 KeyValue 数据格式，下列选项描述正确的是（）。A、D

A 是 byte[]数组

B 没有固定的结构

C 数据的大小是定长的

D 有固定的结构

10.  HFile 数据格式中的 KeyValue 数据格式中 Value 部分是（）。C

A 拥有复杂结构的字符串

B 字符串

C 二进制数据

D 压缩数据

11.  D

1.2.3  HBase 高级应用介绍
1.      HBase 中的批量加载底层使用（）实现。A

A MapReduce

B Hive

CCoprocessor

D BloomFilter

2.      HBase 性能优化包含下面的哪些选项？A、B、C、D

A 读优化

B 写优化

C 配置优化

D JVM 优化

3.      Rowkey 设计的原则，下列哪些选项的描述是正确的？A、B、C

A 尽量保证越短越好

B 可以使用汉字

C 可以使用字符串

D 本身是无序的

4.      HBase 构建二级索引的实现方式有哪些？ A、B

AMapReduce

BCoprocessor

C BloomFilter

D Filter

5.      关于 HBase 二级索引的描述，哪些是正确的？A、B

A 核心是倒排表

B 二级索引概念是对应 Rowkey 这个“一级”索引

C 二级索引使用平衡二叉树

D 二级索引使用 LSM 结构

6.      下列关于 Bloom Filter 的描述正确的是？A、C

A 是一个很长的二进制向量和一系列随机映射函数

B 没有误算率

C 有一定的误算率

D 可以在 Bloom Filter 中删除元素

7.      D

1.2.4  HBase 安装、部署、启动
1.      HBase 官方版本可以安装在什么操作系统上？A、B、C

A CentOS

B Ubuntu

C RedHat

D Windows

2.      HBase 虚拟分布式模式需要（）个节点？A

A 1

B 2

C 3

D 最少 3 个

3.      HBase 分布式模式最好需要（）个节点？C

A 1

B 2

C 3

D 最少

4.      下列哪些选项是安装 HBase 前所必须安装的？A、B

A 操作系统

B JDK

C ShellScript

D JavaCode

5.      解压.tar.gz 结尾的 HBase 压缩包使用的 Linux 命令是？A

A tar-zxvf

B tar -zx

C tar -s

D tar -nf

6.      D

7.      D

1.3     Zookeeper选择题
1.3.1  Zookeeper基础
1.      下面与 Zookeeper 类似的框架是？D

A Protobuf

B Java

C Kafka

D Chubby

2.      的

3.      D

4.      D

5.      D

6.      D

7.      D

8.      D

9.      d

 

第2部分           判断题

第2部分      

2.1     Hadoop判断题
2.1.1  集群管理
1.      Ganglia 不仅可以进行监控，也可以进行告警。（正确）

解析：

ganglia 作为一款最常用的 Linux 环境中的监控软件，它擅长的的是从节点中按照用户的需求以较低的代价

采集数据。但是 ganglia 在预警以及发生事件后通知用户上并不擅长。最新的 ganglia 已经有了部分这方面

的功能。但是更擅长做警告的还有 Nagios。Nagios，就是一款精于预警、通知的软件。通过将 Ganglia 和

Nagios 组合起来，把 Ganglia 采集的数据作为 Nagios 的数据源，然后利用 Nagios 来发送预警通知，可以

完美的实现一整套监控管理的系统。

2.      Nagios 不可以监控 Hadoop 集群，因为它不提供 Hadoop支持。（错误 ）

分析：

Nagios 是集群监控工具，而且是云计算三大利器之一

3.      如果 NameNode 意外终止，SecondaryNameNode 会接替它使集群继续工作。（错误 ）

分析：

SecondaryNameNode 是帮助恢复，而不是替代

4.      Cloudera CDH 是需要付费使用的。（错误）

分析：

第一套付费产品是 Cloudera Enterpris，Cloudera Enterprise 在美国加州举行的 Hadoop 大会 (HadoopSummit) 上公开，以若干私有管理、监控、运作工具加强 Hadoop 的功能。收费采取合约订购方式，价格随用的 Hadoop 叢集大小变动。

5.      NameNode 负责管理 metadata，client 端每次读写请求，它都会从磁盘中读取或则会写入 metadata信息并反馈 client 端。（错误）

分析：

NameNode 不需要从磁盘读取 metadata，所有数据都在内存中，硬盘上的只是序列化的结果，只有每次namenode 启动的时候才会读取。

1）文件写入

Client 向 NameNode 发起文件写入的请求。

NameNode 根据文件大小和文件块配置情况，返回给 Client 它所管理部分 DataNode 的信息。

Client 将文件划分为多个 Block，根据 DataNode 的地址信息，按顺序写入到每一个 DataNode 块中。

2）文件读取

Client 向 NameNode 发起文件读取的请求。

NameNode 返回文件存储的 DataNode 的信息。

Client 读取文件信息。

6.      NameNode 本地磁盘保存了 Block 的位置信息。（ 个人认为正确，欢迎提出其它意见）

分析：

DataNode 是文件存储的基本单元，它将 Block 存储在本地文件系统中，保存了 Block 的 Meta-data，同时周期性地将所有存在的 Block 信息发送给 NameNode。

7.      DataNode 通过长连接与 NameNode 保持通信。错误

分析：

通过心跳机制。

（1）.长连接

Client 方与 Server 方先建立通讯连接，连接建立后不断开，然后再进行报文发送和接收。这种方式下由于通讯连接一直存在，此种方式常用于点对点通讯。

（2）.短连接

Client 方与 Server 每进行一次报文收发交易时才进行通讯连接，交易完毕后立即断开连接。此种方式常用于一点对多点通讯，比如多个 Client 连接一个 Server.

8.      Hadoop 自身具有严格的权限管理和安全措施保障集群正常运行。（错误）

9.      Slave 节点要存储数据，所以它的磁盘越大越好。（错误）

分析：

一旦 Slave 节点宕机，数据恢复是一个难题

10.  hadoop dfsadmin –report 命令用于检测 HDFS 损坏块。（错误）

分析：

hadoop dfsadmin -report

用这个命令可以快速定位出哪些节点 down 掉了，HDFS 的容量以及使用了多少，以及每个节点的硬盘使用情况。

当然 NameNode 有个 http 页面也可以查询，但是这个命令的输出更适合我们的脚本监控 dfs 的使用状况

11.  Hadoop 默认调度器策略为 FIFO（正确 ）

12.  集群内每个节点都应该配 RAID，这样避免单磁盘损坏，影响整个节点运行。（错误）

分析：

首先明白什么是 RAID：磁盘阵列（Redundant Arrays of Independent Disks，RAID），有“独立磁盘构成的具有冗余能力的阵列”之意。

这句话错误的地方在于太绝对，具体情况具体分析。题目不是重点，知识才是最重要的。

因为 hadoop 本身就具有冗余能力，所以如果不是很严格不需要都配备 RAID。

13.  Hadoop 环境变量中的 HADOOP_HEAPSIZE 用于设置所有 Hadoop 守护线程的内存。它默认是 200 GB。（ 错误）

分析：

hadoop 为各个守护进程（namenode,secondarynamenode,jobtracker,datanode,tasktracker）统一分配的内存在 hadoop-env.sh 中设置，参数为 HADOOP_HEAPSIZE，默认为 1000M。

14.  DataNode 首次加入 cluster 的时候，如果 log 中报告不兼容文件版本，那需要 NameNode执行―Hadoopnamenode -format‖操作格式化磁盘。（错误 ）

分析：

这个报错是说明 DataNode 所装的 Hadoop 版本和其它节点不一致，应该检查 DataNode 的 Hadoop 版本

 

15.  D

16.  D

17.  的

18.   D

19.  的

20.   D的

21.   D的

22.   D

23.   d

2.1.2  Hdfs
1.      Block Size 是不可以修改的。（错误）

解析：

Hadoop 的基础配置文件是 hadoop-default.xml，默认建立一个 Job 的时候会建立 Job 的 Config，Config

首先读入hadoop-default.xml的配置，然后再读入hadoop-site.xml的配置（这个文件初始的时候配置为空），

hadoop-site.xml 中主要配置需要覆盖的 hadoop-default.xml 的系统级配置。具体配置可以参考下：

<property>

  <name>dfs.block.size</name>//block 的大小，单位字节，后面会提到用处，必须是 512 的倍数，因

为采用 crc 作文件完整性校验，默认配置 512 是 checksum 的最小单元。

  <value>5120000</value>

  <description>The default block size for new files.</description>

</property>

2.      Hadoop 支持数据的随机读写。（错）

分析：

lucene 是支持随机读写的，而 hdfs 只支持随机读。但是 HBase 可以来补救。

HBase 提供随机读写，来解决 Hadoop 不能处理的问题。HBase 自底层设计开始即聚焦于各种可伸缩性问题：表可以很―高‖，有数十亿个数据行；也可以很―宽‖，有数百万个列；水平分区并在上千个普通商用机节点上自动复制。表的模式是物理存储的直接反映，使系统有可能提高高效的数据结构的序列化、存储和检索。

3.      因为 HDFS 有多个副本，所以 NameNode 是不存在单点问题的。（错误 ）

分析：

副本针对DataName而讲的

 

4.      的

5.      的

6.      的

2.1.3  MapReduce
1.      Hadoop 是 Java 开发的，所以 MapReduce 只支持 Java 语言编写。（错误 ）

分析：

支持c++等语言，需要通过接口。

2.      每个 map 槽就是一个线程。（错误）

分析：

一个task对应一个线程

分析：首先我们知道什么是 map 槽,map 槽->map slot，map slot 只是一个逻辑值 ( org.apache.hadoop.mapred.TaskTracker.TaskLauncher.numFreeSlots )，而不是对应着一个线程或者进程

3.      Mapreduce 的 input split 就是一个 block。（错误）

分析：

   应该是一个block数组

1、运行mapred程序；
2、本次运行将生成一个Job，于是JobClient向JobTracker申请一个JobID以标识这个Job；
3、JobClient将Job所需要的资源提交到HDFS中一个以JobID命名的目录中。这些资源包括JAR包、配置文件、InputSplit、等；
4、JobClient向JobTracker提交这个Job；
5、JobTracker初始化这个Job；
6、JobTracker从HDFS获取这个Job的Split等信息；
7、JobTracker向TaskTracker分配任务；
8、TaskTracker从HDFS获取这个Job的相关资源；
9、TaskTracker开启一个新的JVM；
10、TaskTracker用新的JVM来执行Map或Reduce；

 

InputSplit也是一个interface，具体返回什么样的implement，这是由具体的InputFormat来决定的。InputSplit也只有两个接口函数：
long getLength() throws IOException;
String[] getLocations() throws IOException;
这个interface仅仅描述了Split有多长，以及存放这个Split的Location信息（也就是这个Split在HDFS上存放的机器。它可能有多个replication，存在于多台机器上）。除此之外，就再没有任何直接描述Split的信息了。比如：Split对应于哪个文件？在文件中的起始和结束位置是什么？等等重要的特征都没有描述到。
为什么会这样呢？因为关于Split的那些描述信息，对于MapReduce框架来说是不需要关心的。框架只关心Split的长度（主要用于一些统计信息）和Split的Location（主要用于Split的调度，后面会细说）。
而Split中真正重要的描述信息还是只有InputFormat会关心。在需要读取一个Split的时候，其对应的InputSplit会被传递到InputFormat的第二个接口函数getRecordReader，然后被用于初始化一个RecordReader，以解析输入数据。也就是说，描述Split的重要信息都被隐藏了，只有具体的InputFormat自己知道。它只需要保证getSplits返回的InputSplit和getRecordReader所关心的InputSplit是同样的implement就行了。这就给InputFormat的实现提供了巨大的灵活性。

4.      的

5.      的

6.      的

7.      D

8.      的

第3部分           叙述题

第3部分      

3.1     Hadoop叙述题
3.1.1  Hadoop部署
1.      hdfs的体系结构

解答：

hdfs有namenode、secondraynamenode、datanode组成。

为n+1模式

namenode负责管理datanode和记录元数据

secondraynamenode负责合并日志

datanode负责存储数据

 

2.      简要描述如何安装配置一个apache开原本hadoop，只描述即可，无需列出完整步骤，能列出步骤更好。

流程：

1.创建hadoop用户

2.修改IP

3.安装JDK，并配置环境变量

4.修改host文件映射

5.安装SSH，配置无秘钥通信

6.上传解压hadoop安装包

7.配置conf文件夹下的hadoop-env.sh、core-site.xlmapre-site.xml、hdfs-site.xml

8.配置hadoop的环境变量

9.Hadoop namenode -format

10.start-all

3.      启动hadoop集群时报下图错误，分析什么原因：

解答：

1、权限问题，可能曾经用root启动过集群。(例如hadoop搭建的集群,是tmp/hadoop-hadoop/.....)

2、可能是文件夹不存在

3、解决: 删掉tmp下的那个文件,或改成当前用户

 

4.      请列出hadoop的进程名称

解答：

1.namenode:管理集群，并记录datanode文件信息。

2.Secondname:可以做冷备，对一定范围内的数据做快照性备份。

3.Datanode：存储数据。

4.Jobtracker：管理任务，并将任务分配给tasktracker。

5.Tasktracker:任务执行者

 

5.      Hadoop的核心配置是什么？

解答：

Hadoop的核心配置通过两个xml文件来完成：

1.hadoop-default.xml；

2.hadoop-site.xml。

这些文件都使用xml格式，因此每个xml中都有一些属性，包括名称和值，但是当下这些文件都已不复存在。

6.      那当下又该如何配置？

解答：

Hadoop现在拥有3个配置文件：

1，core-site.xml；

2，hdfs-site.xml；

3，mapred-site.xml。

这些文件都保存在conf/子目录下。

7.      “jps”命令的用处？

解答：

这个命令可以检查Namenode、Datanode、Task Tracker、 Job Tracker是否正常工作。

 

8.      简要描述如何安装配置一个apache 开源版 hadoop，描述即可，列出步骤更好

解答：

9.      请列出正常工作的 hadoop 集群中 hadoop 都需要启动哪些进程，他们的作用分别是什么？

解答：

10.  启动 hadoop 报如下错误，该如何解决？

error org.apache.hadoop.hdfs.server.namenode.NameNode

org.apache.hadoop.hdfs.server.common.inconsistentFSStateExceptio

n Directory /tmp/hadoop-root/dfs/name is in an inconsistent

state storage direction does not exist or is not accessible?

11.  请写出以下执行命令

1)杀死一个 job?

2)删除 hdfs 上的/tmp/aaa 目录

3)加入一个新的存储节点和删除一个计算节点需要刷新集群状态命令？

解答：

hadoop job -list 记录job-id、hadoop job -kill job-id

 

hadoop fs -rmr /tmp/aaa

 

添加新节点：

hadoop -daemon.sh start datanode

hadoop -daemon.sh start tasktracker

 

移除一个节点：

hadoop mradmin -refreshnodes

hadoop dfsadmin -refreshnodes

12.  请列出你所知道的 hadoop 调度器，并简要说明其工作方法？

解答：

1.FIFO schedular:默认，先进先出的原则

2.Capacity schedular:计算能力调度器，选择占用最小，优先级高的先执行，以此类推。

3.Fair schedular:公平调度，所有的job具有相同的资源。

13.  请列出在你以前工作中所使用过的开发 mapreduce 的语言？

解答：

14.  你认为用 Java，Streaming,pipe 方式开发 mapreduce,各有哪些优缺点？

解答：

15.  hadoop框架中怎么来优化

解答：

（1）  从应用程序角度进行优化。由于mapreduce是迭代逐行解析数据文件的，怎样在迭代的情况下，编写高效率的应用程序，是一种优化思路。

（2）  对Hadoop参数进行调优。当前hadoop系统有190多个配置参数，怎样调整这些参数，使hadoop作业运行尽可能的快，也是一种优化思路。

（3） 从系统实现角度进行优化。这种优化难度是最大的，它是从hadoop实现机制角度，发现当前Hadoop设计和实现上的缺点，然后进行源码级地修改。该方法虽难度大，但往往效果明显。

（4）linux内核参数调整

1. 使用自定义Writable

自带的Text很好用，但是字符串转换开销较大，故根据实际需要自定义Writable，注意作为Key时要实现WritableCompareable接口

避免output.collect(new Text( ),new Text())

提倡key.set( ) value.set( ) output.collect(key,value)

前者会产生大量的Text对象，使用完后Java垃圾回收器会花费大量的时间去收集这些对象

 

2. 使用StringBuilder

不要使用Formatter StringBuffer（ 线程安全）

StringBuffer尽量少使用多个append方法，适当使用+

 

3. 使用DistributedCache加载文件

比如配置文件，词典，共享文件，避免使用static变量

 

4. 充分使用Combiner Parttitioner Comparator。

Combiner : 对map任务进行本地聚合

Parttitioner ： 合适的Parttitioner避免reduce端负载不均

Comparator ： 二次排序

比如求每天的最大气温，map结果为日期：气温，若气温是降序的，直接取列表首元素即可

 

5. 使用自定义InputFormat和OutputFormat

 

6. MR应避免

 

静态变量：不能用于计数，应使用Counter

 

大对象：Map List

 

递归：避免递归深度过大

 

超长正则表达式：消耗性能，要在map或reduce函数外编译正则表达式

 

不要创建本地文件：变向的把HDFS里面的数据转移到TaskTracker，占用网络带宽

 

不要大量创建目录和文件

 

不要大量使用System.out.println，而使用Logger

 

不要自定义过多的Counter，最好不要超过100个

 

不要配置过大内存，mapred.child.java.opts -Xmx2000m是用来设置mapreduce任务使用的最大heap量

 

7.关于map的数目

map数目过大[创建和初始化map的开销]，一般是由大量小文件造成的，或者dfs.block.size设置的太小，对于小文件可以archive文件或者Hadoop fs -merge合并成一个大文件.

map数目过少，造成单个map任务执行时间过长，频繁推测执行，且容易内存溢出，并行性优势不能体现出来。dfs.block.size一般为256M-512M

压缩的Text 文件是不能被分割的，所以尽量使用SequenceFile，可以切分

 

8.关于reduce的数目

reduce数目过大，产生大量的小文件，消耗大量不必要的资源，reduce数目过低呢，造成数据倾斜问题，且通常不能通过修改参数改变。

可选方案：mapred.reduce.tasks设为-1变成AutoReduce。

Key的分布，也在某种程度上决定了Reduce数目，所以要根据Key的特点设计相对应的Parttitioner 避免数据倾斜

 

9.Map-side相关参数优化

io.sort.mb（100MB）：通常k个map tasks会对应一个buffer，buffer主要用来缓存map部分计算结果，并做一些预排序提高map性能，若map输出结果较大，可以调高这个参数，减少map任务进行spill任务个数，降低 I/O的操作次数。若map任务的瓶颈在I/O的话，那么将会大大提高map性能。如何判断map任务的瓶颈？

io.sort.spill.percent(0.8)：spill操作就是当内存buffer超过一定阈值（这里通常是百分比）的时候，会将buffer中得数据写到Disk中。而不是等buffer满后在spill，否则会造成map的计算任务等待buffer的释放。一般来说，调整 io.sort.mb而不是这个参数。

io.sort.factor（10）：map任务会产生很多的spill文件，而map任务在正常退出之前会将这些spill文件合并成一个文件，即merger过程，缺省是一次合并10个参数，调大io.sort.factor，减少merge的次数，减少Disk I/O操作，提高map性能。

min.num.spill.for.combine：通常为了减少map和reduce数据传输量，我们会制定一个combiner，将map结果进行本地聚集。这里combiner可能在merger之前，也可能在其之后。那么什么时候在其之前呢？当spill个数至少为min.num.spill.for.combine指定的数目时同时程序指定了Combiner，Combiner会在其之前运行，减少写入到Disk的数据量，减少I/O次数。

 

10.压缩（时间换空间）

MR中的数据无论是中间数据还是输入输出结果都是巨大的，若不使用压缩不仅浪费磁盘空间且会消耗大量网络带宽。同样在spill，merge（reduce也对有一个merge）亦可以使用压缩。若想在cpu时间和压缩比之间寻找一个平衡，LzoCodec比较适合。通常MR任务的瓶颈不在CPU而在于I/O，所以大部分的MR任务都适合使用压缩。

 

11. reduce-side相关参数优化

reduce：copy->sort->reduce，也称shuffle

mapred.reduce.parellel.copies（5）：任一个map任务可能包含一个或者多个reduce所需要数据，故一个map任务完成后，相应的reduce就会立即启动线程下载自己所需要的数据。调大这个参数比较适合map任务比较多且完成时间比较短的Job。

mapred.reduce.copy.backoff：reduce端从map端下载数据也有可能由于网络故障，map端机器故障而失败。那么reduce下载线程肯定不会无限等待，当等待时间超过mapred.reduce.copy.backoff时，便放弃，尝试从其他地方下载。需注意：在网络情况比较差的环境，我们需要调大这个参数，避免reduce下载线程被误判为失败。

io.sort.factor：recude将map结果下载到本地时，亦需要merge，如果reduce的瓶颈在于I/O，可尝试调高增加merge的并发吞吐，提高reduce性能、

mapred.job.shuffle.input.buffer.percent（0.7）：reduce从map下载的数据不会立刻就写到Disk中，而是先缓存在内存中，mapred.job.shuffle.input.buffer.percent指定内存的多少比例用于缓存数据，内存大小可通过mapred.child.java.opts来设置。和map类似，buffer不是等到写满才往磁盘中写，也是到达阈值就写，阈值由mapred.job,shuffle.merge.percent来指定。若Reduce下载速度很快，容易内存溢出，适当增大这个参数对增加reduce性能有些帮助。

mapred.job.reduce.input.buffer.percent (0)：当Reduce下载map数据完成之后，就会开始真正的reduce的计算，reduce的计算必然也是要消耗内存的，那么在读物reduce所需要的数据时，同样需要内存作为buffer，这个参数是决定多少的内存百分比作为buffer。默认为0，也就是说reduce全部从磁盘读数据。若redcue计算任务消耗内存很小，那么可以设置这个参数大于0，使一部分内存用来缓存数据。

 

16.  从应用程序角度进行优化

解答：

（1） 避免不必要的reduce任务

如果mapreduce程序中reduce是不必要的，那么我们可以在map中处理数据, Reducer设置为0。这样避免了多余的reduce任务。

（2） 为job添加一个Combiner

为job添加一个combiner可以大大减少shuffle阶段从map task拷贝给远程reduce task的数据量。一般而言，combiner与reducer相同。

（3） 根据处理数据特征使用最适合和简洁的Writable类型

Text对象使用起来很方便，但它在由数值转换到文本或是由UTF8字符串转换到文本时都是低效的，且会消耗大量的CPU时间。当处理那些非文本的数据时，可以使用二进制的Writable类型，如IntWritable， FloatWritable等。二进制writable好处：避免文件转换的消耗；使map task中间结果占用更少的空间。

（4） 重用Writable类型

很多MapReduce用户常犯的一个错误是，在一个map/reduce方法中为每个输出都创建Writable对象。例如，你的Wordcout mapper方法可能这样写：

 

public void map(...) {

  …

 

  for (String word : words) {

    output.collect(new Text(word), new IntWritable(1));

  }

}

这样会导致程序分配出成千上万个短周期的对象。Java垃圾收集器就要为此做很多的工作。更有效的写法是：

class MyMapper … {

  Text wordText = new Text();

  IntWritable one = new IntWritable(1);

  public void map(...) {

    for (String word: words) {

      wordText.set(word);

      output.collect(wordText, one);

    }

  }

}

（5） 使用StringBuffer而不是String

当需要对字符串进行操作时，使用StringBuffer而不是String，String是read-only的，如果对它进行修改，会产生临时对象，而StringBuffer是可修改的，不会产生临时对象。

17.  datanode在什么情况下不会备份

解答：

当分备份数为1时。

18.  combiner出现在那个过程

解答：

出现在map阶段的map方法后。

19.  3个datanode中有一个datanode出现错误会怎样？

解答：

这个datanode的数据会在其他的datanode上重新做备份。

20.  描述一下hadoop中，有哪些地方使用了缓存机制，作用分别是什么？

解答：

 

21.  如何确定hadoop集群的健康状态

解答：

 

22.  hadoop 的 namenode 宕机,怎么解决

解答：

先分析宕机后的损失，宕机后直接导致client无法访问，内存中的元数据丢失，但是硬盘中的元数据应该还存在，如果只是节点挂了，重启即可，如果是机器挂了，重启机器后看节点是否能重启，不能重启就要找到原因修复了。但是最终的解决方案应该是在设计集群的初期就考虑到这个问题，做namenode的HA。

 

23.  一个datanode 宕机,怎么一个流程恢复

解答：

Datanode宕机了后，如果是短暂的宕机，可以实现写好脚本监控，将它启动起来。如果是长时间宕机了，那么datanode上的数据应该已经被备份到其他机器了，那这台datanode就是一台新的datanode了，删除他的所有数据文件和状态文件，重新启动。

24.  的

25.  D

26.  的

27.  的

28.  的

29.  的

30.   d

3.1.2  Hadoop原理
1.      请简述 hadoop 怎么样实现二级排序？

解答：

在Reduce阶段，先对Key排序，再对Value排序

最常用的方法是将Value放到Key中，实现一个组合Key，然后自定义Key排序规则（为Key实现一个WritableComparable）。

2.      如何使用MapReduce实现两个表join，可以考虑一下几种情况：（1）一个表大，一个表小（可放到内存中）；（2）两个表都是大表？

解答：

第一种情况比较简单，只需将小表放到DistributedCache中即可；

第二种情况常用的方法有：map-side join（要求输入数据有序，通常用户Hbase中的数据表连接），reduce-side join，semi join（半连接）

3.      MapReduce中排序发生在哪几个阶段？这些排序是否可以避免？为什么？

解答：

一个MapReduce作业由Map阶段和Reduce阶段两部分组成，这两阶段会对数据排序，从这个意义上说，MapReduce框架本质就是一个Distributed Sort。在Map阶段，在Map阶段，Map Task会在本地磁盘输出一个按照key排序（采用的是快速排序）的文件（中间可能产生多个文件，但最终会合并成一个），在Reduce阶段，每个Reduce Task会对收到的数据排序，这样，数据便按照Key分成了若干组，之后以组为单位交给reduce（）处理。很多人的误解在Map阶段，如果不使用Combiner便不会排序，这是错误的，不管你用不用Combiner，Map Task均会对产生的数据排序（如果没有Reduce Task，则不会排序， 实际上Map阶段的排序就是为了减轻Reduce端排序负载）。由于这些排序是MapReduce自动完成的，用户无法控制，因此，在hadoop 1.x中无法避免，也不可以关闭，但hadoop2.x是可以关闭的。

 

4.      请简述 mapreduce 中，combiner，partition 作用？

解答：

combiner是reduce的实现，在map端运行计算任务，减少map端的输出数据。

作用就是优化。

但是combiner的使用场景是mapreduce的map输出结果和reduce输入输出一样。

 

partition的默认实现是hashpartition，是map端将数据按照reduce个数取余，进行分区，不同的reduce来copy自己的数据。

partition的作用是将数据分到不同的reduce进行计算，加快计算效果。

 

 

1、combiner最基本是实现本地key的聚合，对map输出的key排序，value进行迭代。如下所示：

　　map: (K1, V1) → list(K2, V2)

　　combine: (K2, list(V2)) → list(K2, V2)

　　reduce: (K2, list(V2)) → list(K3, V3)

　　2、combiner还具有类似本地的reduce功能.

　　例如hadoop自带的wordcount的例子和找出value的最大值的程序，combiner和reduce完全一致。如下所示：

　　map: (K1, V1) → list(K2, V2)

　　combine: (K2, list(V2)) → list(K3, V3)

　　reduce: (K3, list(V3)) → list(K4, V4)

　　3、如果不用combiner，那么，所有的结果都是reduce完成，效率会相对低下。使用combiner，先完成的map会在本地聚合，提升速度。

　　4、对于hadoop自带的wordcount的例子，value就是一个叠加的数字，所以map一结束就可以进行reduce的value叠加，而不必要等到所有的map结束再去进行reduce的value叠加。

　　combiner使用的合适，可以在满足业务的情况下提升job的速度，如果不合适，则将导致输出的结果不正确。

 

5.      解释―hadoop‖和―hadoop 生态系统‖两个概念

解答：

6.      说明 Hadoop 2.0 的基本构成

解答：

分别说明hdfs，yarn，mapreduce

7.      相比于 HDFS1.0,HDFS 2.0 最主要的改进在哪几方面？

解答：

8.      试使用―步骤 1，步骤 2，步骤 3…..‖说明 YARN 中运行应用程序的基本流程

解答：

9.      ―MapReduce 2.0‖与―YARN‖是否等同，尝试解释说明

解答：

10.  MapReduce 2.0 中，MRAppMaster 主要作用是什么，MRAppMaster如何实现任务容错的？

解答：

11.  hdfs 原理，以及各个模块的职责

解答：

12.  mr 的工作原理

解答：

Map—combiner—partition—sort—copy—sort—grouping—reduce

13.  map 方法是如何调用 reduce 方法的

解答：

14.  shell 如何判断文件是否存在，如果不存在该如何处理？

解答：

15.  fsimage 和 edit 的区别？

解答：

16.  hadoop1 和 hadoop2 的区别？

解答：

17.  hdfs 中的 block 默认报错几份？

解答：

18.  哪个程序通常与 nn 在一个节点启动？并做分析

解答：

19.  列举几个配置文件优化？

解答：

20.  datanode 首次加入 cluster 的时候，如果 log 报告不兼容文件版本，那需要 namenode 执行格式化操作，这样处理的原因是？

解答：

21.  用mapreduce怎么处理数据倾斜问题？

解答：

数据倾斜：map /reduce程序执行时，reduce节点大部分执行完毕，但是有一个或者几个reduce节点运行很慢，导致整个程序的处理时间很长，这是因为某一个key的条数比其他key多很多（有时是百倍或者千倍之多），这条key所在的reduce节点所处理的数据量比其他节点就大很多，从而导致某几个节点迟迟运行不完，此称之为数据倾斜。

 

用hadoop程序进行数据关联时，常碰到数据倾斜的情况，这里提供一种解决方法。

自己实现partition类，用key和value相加取hash值：

方式1：

源代码：

public int getPartition(K key, V value,

                          int numReduceTasks) {

    return (key.hashCode() & Integer.MAX_VALUE) % numReduceTasks;

  }

修改后

public int getPartition(K key, V value,

                          int numReduceTasks) {

    return ((（key).hashCode()+value.hashCode()） & Integer.MAX_VALUE) % numReduceTasks;

  }

 

方式2：

public class HashPartitioner<K, V> extends Partitioner<K, V> {

private int aa= 0;

  /** Use {@link Object#hashCode()} to partition. */

  public int getPartition(K key, V value,

                          int numReduceTasks) {

    return (key.hashCode()+(aa++) & Integer.MAX_VALUE) % numReduceTasks;

  }

 

 

22.  谈谈数据倾斜，如何发生的，并给出优化方案

解答：

23.  mapreduce 基本执行过程

解答：

24.  谈谈 hadoop1 和 hadoop2 的区别

解答：

25.  hadoop中Combiner的作用?

解答：

combiner是reduce的实现，在map端运行计算任务，减少map端的输出数据。

作用就是优化。

但是combiner的使用场景是mapreduce的map和reduce输入输出一样。

26.  Mapreduce 的 map 数量 和 reduce 数量 怎么确定 ,怎么配置

解答：

map的数量有数据块决定，reduce数量随便配置。

27.  在hadoop中文件的压缩带来了两大好处：

解答：

（1）它减少了存储文件所需的空间；

（2）加快了数据在网络上或者从磁盘上或到磁盘上的传输速度；

28.  mapreduce的调度模式

解答：

一个MapReduce作业的生命周期大体分为5个阶段 【1】 ：

1. 作业提交与初始化

2. 任务调度与监控

3. 任务运行环境准备

4. 任务执行

5. 作业完成

我们假设JobTracker已经启动，那么调度器是怎么启动的？JobTracker在启动时有以下代码：

JobTracker tracker = startTracker(new JobConf());

tracker.offerService();

其中offerService方法负责启动JobTracker提供的各个服务，有这样一行代码：

taskScheduler.start();

taskScheduler即为任务调度器。start方法是抽象类TaskScheduler提供的接口，用于启动调度器。每个调度器类都要继承TaskScheduler类。回忆一下，调度器启动时会将各个监听器对象注册到JobTracker，以FIFO调度器JobQueueTaskScheduler为例：

@Override

  public synchronized void start() throws IOException {

    super.start();

    taskTrackerManager.addJobInProgressListener(jobQueueJobInProgressListener);

    eagerTaskInitializationListener.setTaskTrackerManager(taskTrackerManager);

    eagerTaskInitializationListener.start();

    taskTrackerManager.addJobInProgressListener(

        eagerTaskInitializationListener);

  }

这里注册了两个监听器，其中eagerTaskInitializationListener负责作业初始化，而jobQueueJobInProgressListener则负责作业的执行和监控。当有作业提交到JobTracker时，JobTracker会执行所有订阅它消息的监听器的jobAdded方法。对于eagerTaskInitializationListener来说： 

@Override

  public void jobAdded(JobInProgress job) {

    synchronized (jobInitQueue) {

      jobInitQueue.add(job);

      resortInitQueue();

      jobInitQueue.notifyAll();

    }

  }

提交的作业的JobInProgress对象被添加到作业初始化队列jobInitQueue中，并唤醒初始化线程（若原来没有作业可以初始化）：

class JobInitManager implements Runnable {

    public void run() {

      JobInProgress job = null;

      while (true) {

        try {

          synchronized (jobInitQueue) {

            while (jobInitQueue.isEmpty()) {

              jobInitQueue.wait();

            }

            job = jobInitQueue.remove(0);

          }

          threadPool.execute(new InitJob(job));

        } catch (InterruptedException t) {

          LOG.info("JobInitManagerThread interrupted.");

          break;

        }

      }

      threadPool.shutdownNow();

    }

  }

这种工作方式是一种“生产者-消费者”模式：作业初始化线程是消费者，而监听器eagerTaskInitializationListener是生产者。这里可以有多个消费者线程，放到一个固定资源的线程池中，线程个数通过mapred.jobinit.threads参数配置，默认为4个。

下面我们重点来看调度器中的另一个监听器。 jobQueueJobInProgressListener对象在调度器中初始化时连续执行了两个构造器完成初始化：

public JobQueueJobInProgressListener() {

    this(new TreeMap<JobSchedulingInfo,

                     JobInProgress>(FIFO_JOB_QUEUE_COMPARATOR));

  }

  /**

   * For clients that want to provide their own job priorities.

   * @param jobQueue A collection whose iterator returns jobs in priority order.

   */

  protected JobQueueJobInProgressListener(Map<JobSchedulingInfo,

                                          JobInProgress> jobQueue) {

    this.jobQueue = Collections.synchronizedMap(jobQueue);

  }

其中，第一个构造器调用重载的第二个构造器。可以看到，调度器使用一个队列jobQueue来保存提交的作业。这个队列使用一个TreeMap对象实现，TreeMap的特点是底层使用红黑树实现，可以按照键来排序，并且由于是平衡树，效率较高。作为键的是一个JobSchedulingInfo对象，作为值就是提交的作业对应的JobInProgress对象。另外，由于TreeMap本身不是线程安全的，这里使用了集合类的同步方法构造了一个线程安全的Map。使用带有排序功能的数据结构的目的是使作业在队列中按照优先级的大小排列，这样每次调度器只需从队列头部获得作业即可。

作业的顺序由优先级决定，而优先级信息包含在JobSchedulingInfo对象中：

static class JobSchedulingInfo {

    private JobPriority priority;

    private long startTime;

    private JobID id;

...

}

该对象包含了作业的优先级、ID和开始时间等信息。在Hadoop中，作业的优先级有以下五种：VERY_HIGH、HIGH、NORMAL、LOW、VERY_LOW。这些字段是通过作业的JobStatus对象初始化的。由于该对象作为TreeMap的键，因此要实现自己的equals方法和hashCode方法：

@Override

    public boolean equals(Object obj) {

      if (obj == null || obj.getClass() != JobSchedulingInfo.class) {

        return false;

      } else if (obj == this) {

        return true;

      }

      else if (obj instanceof JobSchedulingInfo) {

        JobSchedulingInfo that = (JobSchedulingInfo)obj;

        return (this.id.equals(that.id) &&

                this.startTime == that.startTime &&

                this.priority == that.priority);

      }

      return false;

    }

我们看到，两个JobSchedulingInfo对象相等的条件是类型一致，并且作业ID、开始时间和优先级都相等。hashCode的计算比较简单：

@Override

    public int hashCode() {

      return (int)(id.hashCode() * priority.hashCode() + startTime);

    }

注意，监听器的第一个构造器有一个比较器参数，用于定义 JobSchedulingInfo的比较方式：

static final Comparator<JobSchedulingInfo> FIFO_JOB_QUEUE_COMPARATOR

    = new Comparator<JobSchedulingInfo>() {

    public int compare(JobSchedulingInfo o1, JobSchedulingInfo o2) {

      int res = o1.getPriority().compareTo(o2.getPriority());

      if (res == 0) {

        if (o1.getStartTime() < o2.getStartTime()) {

          res = -1;

        } else {

          res = (o1.getStartTime() == o2.getStartTime() ? 0 : 1);

       }

      }

      if (res == 0) {

        res = o1.getJobID().compareTo(o2.getJobID());

      }

      return res;

    }

  };

从上面看出，首先比较作业的优先级，若优先级相等则比较开始时间（FIFO），若再相等则比较作业ID。 我们在实现自己的调度器时可能要定义自己的作业队列，那么作业在队列中的顺序（即 JobSchedulingInfo的比较器 ）就要仔细定义，这是调度器能够正常运行基础。

Hadoop中的作业调度采用pull方式，即TaskTracker定时向JobTracker发送心跳信息索取一个新的任务，这些信息包括数据结点上作业和任务的运行情况，以及该TaskTracker上的资源使用情况。JobTracker会依据以上信息更新作业队列的状态，并调用调度器选择一个或多个任务以心跳响应的形式返回给TaskTracker。从上面描述可以看出，JobTracker和taskScheduler之间的互相利用关系：前者利用后者为TaskTracker分配任务；后者利用前者更新队列和作业信息。接下来，我们一步步详述该过程。

首先，当一个心跳到达JobTracker时（实际上这是一个来自TaskTracker的远程过程调用 heartbeat方法 ，协议接口是InterTrackerProtocol），会执行两种动作：更新状态和下达命令 【1】 。下达命令稍后关注。有关更新状态的一些代码片段如下：

if (!processHeartbeat(status, initialContact, now)) {

      if (prevHeartbeatResponse != null) {

        trackerToHeartbeatResponseMap.remove(trackerName);

      }

      return new HeartbeatResponse(newResponseId,

                   new TaskTrackerAction[] {new ReinitTrackerAction()});

    }

具体的心跳处理，由私有函数processHeartbeat完成。该函数中有以下两个方法调用：

updateTaskStatuses(trackerStatus);

    updateNodeHealthStatus(trackerStatus, timeStamp);

分别用来更新任务的状态和结点的健康状态。在第一个方法中有下面代码片段：

TaskInProgress tip = taskidToTIPMap.get(taskId);

      // Check if the tip is known to the jobtracker. In case of a restarted

      // jt, some tasks might join in later

      if (tip != null || hasRestarted()) {

        if (tip == null) {

          tip = job.getTaskInProgress(taskId.getTaskID());

          job.addRunningTaskToTIP(tip, taskId, status, false);

        }

       

        // Update the job and inform the listeners if necessary

        JobStatus prevStatus = (JobStatus)job.getStatus().clone();

        // Clone TaskStatus object here, because JobInProgress

        // or TaskInProgress can modify this object and

        // the changes should not get reflected in TaskTrackerStatus.

        // An old TaskTrackerStatus is used later in countMapTasks, etc.

        job.updateTaskStatus(tip, (TaskStatus)report.clone());

        JobStatus newStatus = (JobStatus)job.getStatus().clone();

        // Update the listeners if an incomplete job completes

        if (prevStatus.getRunState() != newStatus.getRunState()) {

          JobStatusChangeEvent event =

            new JobStatusChangeEvent(job, EventType.RUN_STATE_CHANGED,

                                     prevStatus, newStatus);

          updateJobInProgressListeners(event);

        }

      } else {

        LOG.info("Serious problem.  While updating status, cannot find taskid "

                 + report.getTaskID());

      }

这里的job对象通过从TaskTracker那里得到的task状态信息中抽取出来。注意，这里拷贝了原有作业状态的一个副本，然后修改这个副本的相关信息，调用的是updateJobStatus方法，更新任务的状态信息和JobInProgress的相关信息，如map和reduce任务的进度等，这里不展开了。这些信息的更新可以为调度器的工作提供依据。

作业状态的更新是通过updateJobInProgressListeners方法实现，该方法的参数是一个JobStatusChangeEvent对象，表示作业状态变化的事件。这种事件的类型可以是运行状态改变、开始时间改变、优先级改变等等。用户也可以根据需要自定义事件类型。事件对象维护了两个JobStatus对象，分别表示事件发生前后作业的状态。 
进入该方法后，我们又看到了熟悉的观察者模式：

// Update the listeners about the job

  // Assuming JobTracker is locked on entry.

  private void updateJobInProgressListeners(JobChangeEvent event) {

    for (JobInProgressListener listener : jobInProgressListeners) {

      listener.jobUpdated(event);

    }

  }

这次每个监听器要回调jobUpdated方法，表示作业有更新。对于jobQueueJobInProgressListener来说是这样做的：

@Override

  public synchronized void jobUpdated(JobChangeEvent event) {

    JobInProgress job = event.getJobInProgress();

    if (event instanceof JobStatusChangeEvent) {

      // Check if the ordering of the job has changed

      // For now priority and start-time can change the job ordering

      JobStatusChangeEvent statusEvent = (JobStatusChangeEvent)event;

      JobSchedulingInfo oldInfo = 

        new JobSchedulingInfo(statusEvent.getOldStatus());

      if (statusEvent.getEventType() == EventType.PRIORITY_CHANGED

          || statusEvent.getEventType() == EventType.START_TIME_CHANGED) {

        // Make a priority change

        reorderJobs(job, oldInfo);

      } else if (statusEvent.getEventType() == EventType.RUN_STATE_CHANGED) {

        // Check if the job is complete

        int runState = statusEvent.getNewStatus().getRunState();

        if (runState == JobStatus.SUCCEEDED

            || runState == JobStatus.FAILED

            || runState == JobStatus.KILLED) {

          jobCompleted(oldInfo);

        }

      }

    }

  }

首先，获取作业更新 前 的状态。然后根据事件的类型，进行相应的处理。比如，如果优先级变化了，则要重新排列队列中作业的顺序。这里直接取出原有作业，重新插入队列。插入后，作业会自动重新排序，体现了TreeMap的优越性。再比如，如果作业状态变为完成，那么就从队列中删除该作业。

private void reorderJobs(JobInProgress job, JobSchedulingInfo oldInfo) {

    synchronized (jobQueue) {

      jobQueue.remove(oldInfo);

      jobQueue.put(new JobSchedulingInfo(job), job);

    }

  }

下面就是调度器中最关键的一步了：任务选择。此时，作业队列中信息已经更新完毕，可以选择一些任务返回给TaskTracker执行了。heartbeat方法接下来会有这样的代码：

List<Task> tasks = getSetupAndCleanupTasks(taskTrackerStatus);

  if (tasks == null ) {

    tasks = taskScheduler.assignTasks(taskTrackers.get(trackerName));

  }

如果不需要setup和cleanup，就说明需要选择map或reduce任务。调用TaskScheduler的assignTasks方法完成任务选择。由于篇幅限制，我打算将这部分内容放到下一篇文章中，并关注heartbeat中JobTracker下达的命令过程以及JobInProgress和TaskInProgress对调度有影响的一些字段。

 

 

29.  是

30.   

3.1.3  Hadoop使用
1.      hdfs写流程

流程：

1.client链接namenode存数据

2.namenode记录一条数据位置信息（元数据），告诉client存哪。

3.client用hdfs的api将数据块（默认是64M）存储到datanode上。

4.datanode将数据水平备份。并且备份完将反馈client。

5.client通知namenode存储块完毕。

6.namenode将元数据同步到内存中。

7.另一块循环上面的过程。

 

2.      hdfs读流程

流程：

1.client链接namenode，查看元数据，找到数据的存储位置。

2.client通过hdfs的api并发读取数据。

3.关闭连接。

 

3.      举一个简单的例子说明mapreduce是怎么来运行的 ?

解答：

 

    Word count例子接口

============================

一个MapReduce作业（job）通常会把输入的数据集切分为若干独立的数据块，由map任务（task）以完全并行的方式处理它们。框架会对map的输出先进行排序，然后把结果输入给reduce任务。通常作业的输入和输出都会被存储在文件系统中。整个框架负责任务的调度和监控，以及重新执行已经失败的任务。

　　通常，MapReduce框架和分布式文件系统是运行在一组相同的节点上的，也就是说，计算节点和存储节点通常在一起。这种配置允许框架在那些已经存好数据的节点上高效地调度任务，这可以使整个集群的网络带宽被非常高效地利用。

　　MapReduce框架由一个单独的master JobTracker和每个集群节点一个slave TaskTracker共同组成。master负责调度构成一个作业的所有任务，这些任务分布在不同的slave上，master监控它们的执行，重新执行已经失败的任务。而slave仅负责执行由master指派的任务

 

4.      用mapreduce来实现下面需求？现在有10个文件夹,每个文件夹都有1000000个url.现在让你找出top1000000url。

 

5.      yarn流程

解答：

1)      用户向YARN 中提交应用程序， 其中包括ApplicationMaster 程序、启动ApplicationMaster 的命令、用户程序等。

2)      ResourceManager 为该应用程序分配第一个Container， 并与对应的NodeManager 通信，要求它在这个Container 中启动应用程序的ApplicationMaster。

3)      ApplicationMaster 首先向ResourceManager 注册， 这样用户可以直接通过ResourceManage 查看应用程序的运行状态，然后它将为各个任务申请资源，并监控它的运行状态，直到运行结束，即重复步骤4~7。

4)      ApplicationMaster 采用轮询的方式通过RPC 协议向ResourceManager 申请和领取资源。

5)      一旦ApplicationMaster 申请到资源后，便与对应的NodeManager 通信，要求它启动任务。

6)      NodeManager 为任务设置好运行环境（包括环境变量、JAR 包、二进制程序等）后，将任务启动命令写到一个脚本中，并通过运行该脚本启动任务。

7)      各个任务通过某个RPC 协议向ApplicationMaster 汇报自己的状态和进度，以让ApplicationMaster 随时掌握各个任务的运行状态，从而可以在任务失败时重新启动任务。在应用程序运行过程中，用户可随时通过RPC 向ApplicationMaster 查询应用程序的当前运行状态。

8)      应用程序运行完成后，ApplicationMaster 向ResourceManager 注销并关闭自己。

 

 

6.      的

7.      的

8.      的

9.      的

10.  的

11.   

12.   的

13.   

3.2     Hive叙述题
3.2.1  Hive基础
1.      hive 有哪些方式保存元数据，各有哪些特点？

解答：

1、内存数据库derby，安装小，但是数据存在内存，不稳定

2、mysql数据库，数据存储模式可以自己设置，持久化好，查看方便。

2.      hive内部表和外部表的区别

解答：

内部表：加载数据到hive所在的hdfs目录，删除时，元数据和数据文件都删除

外部表：不加载数据到hive所在的hdfs目录，删除时，只删除表结构。

3.      生产环境中为什么建议使用外部表？

解答：

1、因为外部表不会加载数据到hive，减少数据传输、数据还能共享。

2、hive不会修改数据，所以无需担心数据的损坏

3、删除表时，只删除表结构、不删除数据。

4.      你们数据库怎么导入hive 的,有没有出现问题

解答：

在导入hive的时候，如果数据库中有blob或者text字段，会报错。有个参数limit

5.      简述Hive中的虚拟列作用是什么，使用它的注意事项

解答：

Hive提供了三个虚拟列：

INPUT__FILE__NAME

BLOCK__OFFSET__INSIDE__FILE

ROW__OFFSET__INSIDE__BLOCK

但ROW__OFFSET__INSIDE__BLOCK默认是不可用的，需要设置hive.exec.rowoffset为true才可以。可以用来排查有问题的输入数据。

INPUT__FILE__NAME, mapper任务的输出文件名。

BLOCK__OFFSET__INSIDE__FILE, 当前全局文件的偏移量。对于块压缩文件，就是当前块的文件偏移量，即当前块的第一个字节在文件中的偏移量。

hive> SELECT INPUT__FILE__NAME, BLOCK__OFFSET__INSIDE__FILE, line

 

> FROM hive_text WHERE line LIKE '%hive%' LIMIT 2;

 

har://file/user/hive/warehouse/hive_text/folder=docs/

 

data.har/user/hive/warehouse/hive_text/folder=docs/README.txt  2243

 

har://file/user/hive/warehouse/hive_text/folder=docs/

 

data.har/user/hive/warehouse/hive_text/folder=docs/README.txt  3646

 

6.      hive partition分区

解答：

分区表，动态分区

7.      insert into 和 override write区别？

解答：

insert into：将某一张表中的数据写到另一张表中

override write：覆盖之前的内容。

8.      假如一个分区的数据主部错误怎么通过hivesql删除hdfs

解答：

alter table ptable drop partition (daytime='20140911',city='bj');

元数据，数据文件都删除，但目录daytime= 20140911还在

 

9.      Hive里面用什么代替in查询

解答：

提示：Hive中的left semi join替换sql中的in操作

 

10.  的

11.  的

12.   d

 

3.3     Hbase
3.3.1  Hbase基础
1.      介绍一下 hbase 过滤器

解答：

2.      hbase 集群安装注意事项

解答：

3.      hbase的rowkey怎么创建好？列族怎么创建比较好？

解答：

hbase存储时，数据按照Row key的字典序(byte order)排序存储。设计key时，要充分排序存储这个特性，将经常一起读取的行存储放到一起。(位置相关性)

一个列族在数据底层是一个文件，所以将经常一起查询的列放到一个列族中，列族尽量少，减少文件的寻址时间。

因为hbase是列式数据库，列非表schema的一部分，所以在设计初期只需要考虑rowkey 和 columnFamily即可，rowkey有位置相关性，所以如果数据是练习查询的，最好对同类数据加一个前缀，而每个columnFamily实际上在底层是一个文件，那么文件越小，查询越快，所以讲经常一起查询的列设计到一个列簇，但是列簇不宜过多。

 

 Rowkey长度原则

Rowkey是一个二进制码流，Rowkey的长度被很多开发者建议说设计在10~100个字节，不过建议是越短越好，不要超过16个字节。

原因如下：

（1）数据的持久化文件HFile中是按照KeyValue存储的，如果Rowkey过长比如100个字节，1000万列数据光Rowkey就要占用100*1000万=10亿个字节，将近1G数据，这会极大影响HFile的存储效率；

（2）MemStore将缓存部分数据到内存，如果Rowkey字段过长内存的有效利用率会降低，系统将无法缓存更多的数据，这会降低检索效率。因此Rowkey的字节长度越短越好。

（3）目前操作系统是都是64位系统，内存8字节对齐。控制在16个字节，8字节的整数倍利用操作系统的最佳特性。

Rowkey散列原则

如果Rowkey是按时间戳的方式递增，不要将时间放在二进制码的前面，建议将Rowkey的高位作为散列字段，由程序循环生成，低位放时间字段，这样将提高数据均衡分布在每个Regionserver实现负载均衡的几率。如果没有散列字段，首字段直接是时间信息将产生所有新数据都在一个 RegionServer上堆积的热点现象，这样在做数据检索的时候负载将会集中在个别RegionServer，降低查询效率。

Rowkey唯一原则

必须在设计上保证其唯一性。

4.      简述Hbase性能优化的思路

解答：

1、在库表设计的时候，尽量考虑rowkey和columnfamily的特性

2、进行hbase集群的调优

5.      简述Hbase filter的实现原理是什么？结合实际项目经验，写出几个使用filter的场景。

解答：

hbase的filter是通过scan设置的，所以是基于scan的查询结果进行过滤。

1.在进行订单开发的时候，我们使用rowkeyfilter过滤出某个用户的所有订单

2.在进行云笔记开发时，我们使用rowkey过滤器进行redis数据的恢复。

6.      ROWKEY的后缀匹配怎么实现？列如ROWKEY是yyyyMMDD-UserID形式，如UserID为条件查询数据，怎么实现。

解答：

 

7.      HBase的检索支持3种方式：

解答：

（1） 通过单个Rowkey访问，即按照某个Rowkey键值进行get操作，这样获取唯一一条记录；

（2） 通过Rowkey的range进行scan，即通过设置startRowKey和endRowKey，在这个范围内进行扫描。这样可以按指定的条件获取一批记录；

（3） 全表扫描，即直接扫描整张表中所有行记录。

 

8.      简述HBase的瓶颈

解答：

HBase的瓶颈就是硬盘传输速度。HBase的操作，它可以往数据里面insert,也可以update一些数据，但update的实际上也是insert，只是插入一个新的时间戳的一行。Delete数据，也是insert，只是insert一行带有delete标记的一行。Hbase的所有操作都是追加插入操作。Hbase是一种日志集数据库。它的存储方式，像是日志文件一样。它是批量大量的往硬盘中写，通常都是以文件形式的读写。这个读写速度，就取决于硬盘与机器之间的传输有多快。而Oracle的瓶颈是硬盘寻道时间。它经常的操作时随机读写。要update一个数据，先要在硬盘中找到这个block，然后把它读入内存，在内存中的缓存中修改，过段时间再回写回去。由于你寻找的block不同，这就存在一个随机的读。硬盘的寻道时间主要由转速来决定的。而寻道时间，技术基本没有改变，这就形成了寻道时间瓶颈。

 

9.      Hbase内部是什么机制？

解答：

在HMaster、RegionServer内部，创建了RpcServer实例，并与Client三者之间实现了Rpc调用，HBase0.95内部引入了Google-Protobuf作为中间数据组织方式，并在Protobuf提供的Rpc接口之上，实现了基于服务的Rpc实现，本文详细阐述了HBase-Rpc实现细节。

HBase的RPC Protocol

 在HMaster、RegionServer内部，实现了rpc 多个protocol来完成管理和应用逻辑，具体如下protocol如下：

HMaster支持的Rpc协议：
MasterMonitorProtocol，Client与Master之间的通信，Master是RpcServer端，主要实现HBase集群监控的目的。

MasterAdminProtocol，Client与Master之间的通信，Master是RpcServer端，主要实现HBase表格的管理。例如TableSchema的更改，Table-Region的迁移、合并、下线(Offline)、上线(Online)以及负载平衡，以及Table的删除、快照等相关功能。

RegionServerStatusProtoco，RegionServer与Master之间的通信，Master是RpcServer端，负责提供RegionServer向HMaster状态汇报的服务。

RegionServer支持的Rpc协议：

ClientProtocol，Client与RegionServer之间的通信，RegionServer是RpcServer端，主要实现用户的读写请求。例如get、multiGet、mutate、scan、bulkLoadHFile、执行Coprocessor等。

AdminProtocols，Client与RegionServer之间的通信，RegionServer是RpcServer端，主要实现Region、服务、文件的管理。例如storefile信息、Region的操作、WAL操作、Server的开关等。

(备注：以上提到的Client可以是用户Api、也可以是RegionServer或者HMaster)


 

 HBase-RPC实现机制分析

RpcServer配置三个队列：

1）普通队列callQueue，绝大部分Call请求存在该队列中：callQueue上maxQueueLength为${ipc.server.max.callqueue.length},默认是${hbase.master.handler.count}*DEFAULT_MAX_CALLQUEUE_LENGTH_PER_HANDLER，目前0.95.1中，每个Handler上CallQueue的最大个数默认值(DEFAULT_MAX_CALLQUEUE_LENGTH_PER_HANDLER)为10。

2）优先级队列: PriorityQueue。如果设置priorityHandlerCount的个数，会创建与callQueue相当容量的queue存储Call，该优先级队列对应的Handler的个数由rpcServer实例化时传入。

3）拷贝队列：replicationQueue。由于RpcServer由HMaster和RegionServer共用，该功能仅为RegionServer提供，queue的大小为${ipc.server.max.callqueue.size}指定，默认为1024*1024*1024，handler的个数为hbase.regionserver.replication.handler.count。

RpcServer由三个模块组成：

Listener ===Queue=== Responder

 

这里以HBaseAdmin.listTables为例，     分析一个Rpc请求的函数调用过程：

1) RpcClient创建一个BlockingRpcChannel。

2）以channel为参数创建执行RPC请求需要的stub，此时的stub已经被封装在具体Service下，stub下定义了可执行的rpc接口。

3）stub调用对应的接口，实际内部channel调用callBlockingMethod方法。

RpcClient内实现了protobuf提供的BlockingRpcChannel接口方法callBlockingMethod，  @OverridepublicMessage callBlockingMethod(MethodDescriptor md, RpcController controller,Message param, Message returnType)throwsServiceException {returnthis.rpcClient.callBlockingMethod(md, controller, param, returnType, this.ticket,this.isa, this.rpcTimeout);}
通过以上的实现细节，最终转换成rpcClient的调用，使用MethodDescriptor封装了不同rpc函数，使用Message基类可以接收基于Message的不同的Request和Response对象。

4）RpcClient创建Call对象，查找或者创建合适的Connection，并唤醒Connection。

5）Connection等待Call的Response，同时rpcClient调用函数中，会使用connection.writeRequest(Call call)将请求写入到RpcServer网络流中。

6）等待Call的Response，然后层层返回给更上层接口，从而完成此次RPC调用。

RPCServer收到的Rpc报文的内部组织如下：

Magic

(4Byte)

Version

(1Byte)

AuthMethod

(1Byte)

Connection

HeaderLength

(4Byte)

ConnectionHeader

Request

“HBas”

 

 

 

 

 

验证RpcServer的CURRENT_VERSION

与RPC报文一致

目前支持三类：

AuthMethod.SIMPLE

AuthMethod.KERBEROS

AuthMethod.DIGEST

RPC.proto定义
RPCProtos.ConnectionHeader
message ConnectionHeader {
optional UserInformation userInfo = 1;
optional string serviceName = 2;
// Cell block codec we will use sending over optional cell blocks.  Server throws exception
// if cannot deal.
optional string cellBlockCodecClass = 3 [default = "org.apache.hadoop.hbase.codec.KeyValueCodec"];
// Compressor we will use if cell block is compressed.  Server will throw exception if not supported.
// Class must implement hadoop’s CompressionCodec Interface
optional string cellBlockCompressorClass = 4;
}
序列化之后的数据

 

 

 

整个Request存储是经过编码之后的byte数组，包括如下几个部分：

RequestHeaderLength(RawVarint32)

RequestHeader

ParamSize(RawVarint32)

Param

CellScanner

RPC.proto定义：
message RequestHeader {
// Monotonically increasing callId to keep track of RPC requests and their response
optional uint32 callId = 1;
optional RPCTInfo traceInfo = 2;
optional string methodName = 3;
// If true, then a pb Message param follows.
optional bool requestParam = 4;
// If present, then an encoded data block follows.
optional CellBlockMeta cellBlockMeta = 5;
// TODO: Have client specify priority
}
序列化之后的数据
并从Header中确认是否存在Param和CellScanner，如果确认存在的情况下，会继续访问。

Protobuf的基本类型Message，
Request的Param继承了Message，
这个需要获取的Method类型决定。

 

 

 

从功能上讲，RpcServer上包含了三个模块，

1）Listener。包含了多个Reader线程，通过Selector获取ServerSocketChannel接收来自RpcClient发送来的Connection，并从中重构Call实例，添加到CallQueue队列中。

 ”IPC Server listener on 60021″ daemon prio=10 tid=0x00007f7210a97800 nid=0x14c6 runnable [0x00007f720e8d0000]
java.lang.Thread.State: RUNNABLE
at sun.nio.ch.EPollArrayWrapper.epollWait(Native Method)
at sun.nio.ch.EPollArrayWrapper.poll(EPollArrayWrapper.java:210)
at sun.nio.ch.EPollSelectorImpl.doSelect(EPollSelectorImpl.java:65)
at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:69)
- locked <0x00000000c43cae68> (a sun.nio.ch.Util$2)
- locked <0x00000000c43cae50> (a java.util.Collections$UnmodifiableSet)
- locked <0x00000000c4322ca8> (a sun.nio.ch.EPollSelectorImpl)
at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:80)
at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:84)
at org.apache.hadoop.hbase.ipc.RpcServer$Listener.run(RpcServer.java:646)

2）Handler。负责执行Call，调用Service的方法，然后返回Pair<Message,CellScanner>

“IPC Server handler 0 on 60021″ daemon prio=10 tid=0x00007f7210eab000 nid=0x14c7 waiting on condition [0x00007f720e7cf000]
java.lang.Thread.State: WAITING (parking)
at sun.misc.Unsafe.park(Native Method)
- parking to wait for  <0x00000000c43cad90> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
at java.util.concurrent.locks.LockSupport.park(LockSupport.java:156)
at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:1987)
at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:399)
at org.apache.hadoop.hbase.ipc.RpcServer$Handler.run(RpcServer.java:1804)

3) Responder。负责把Call的结果返回给RpcClient。

 ”IPC Server Responder” daemon prio=10 tid=0x00007f7210a97000 nid=0x14c5 runnable [0x00007f720e9d1000]
java.lang.Thread.State: RUNNABLE
at sun.nio.ch.EPollArrayWrapper.epollWait(Native Method)
at sun.nio.ch.EPollArrayWrapper.poll(EPollArrayWrapper.java:210)
at sun.nio.ch.EPollSelectorImpl.doSelect(EPollSelectorImpl.java:65)
at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:69)
- locked <0x00000000c4407078> (a sun.nio.ch.Util$2)
- locked <0x00000000c4407060> (a java.util.Collections$UnmodifiableSet)
- locked <0x00000000c4345b68> (a sun.nio.ch.EPollSelectorImpl)
at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:80)
at org.apache.hadoop.hbase.ipc.RpcServer$Responder.doRunLoop(RpcServer.java:833)
at org.apache.hadoop.hbase.ipc.RpcServer$Responder.run(RpcServer.java:816)

RpcClient为Rpc请求建立Connection，通过Connection将Call发送RpcServer，然后RpcClient等待结果的返回。

 

 

10.  的

11.  的

12.  的

13.  的

14.  的

 

3.4     Zookeeper
1.      写出你对 zookeeper 的理解

解答：



3.5     Storm
1.      storm 如果碰上了复杂逻辑,需要算很长的时间,你怎么去优化

解答：

拆分复杂的业务到多个bolt中，这样可以利用bolt的tree将速度提升

提高并行度

2.      开发流程，容错机制

解答：

开发流程：

写主类（设计spout和bolt的分发机制）

写spout收集数据

写bolt处理数据，根据数据量和业务的复杂程度，设计并行度。

 

容错机制：

采用ack和fail进行容错，失败的数据重新发送。

3.      storm和spark-streaming：为什么用storm不同spark-streaming

解答：

 

 



3.6     Flume
1.      flume管道内存，flume宕机了数据丢失怎么解决

解答：

1、Flume的channel分为很多种，可以将数据写入到文件

2、防止非首个agent宕机的方法数可以做集群或者主备

 

2.      flume配置方式，flume集群（问的很详细）

解答：

Flume的配置围绕着source、channel、sink叙述，flume的集群是做在agent上的，而非机器上。

 

3.      flume不采集Nginx日志，通过Logger4j采集日志，优缺点是什么？

解答：

优点：Nginx的日志格式是固定的，但是缺少sessionid，通过logger4j采集的日志是带有sessionid的，而session可以通过redis共享，保证了集群日志中的同一session落到不同的tomcat时，sessionId还是一样的，而且logger4j的方式比较稳定，不会宕机。

缺点：不够灵活，logger4j的方式和项目结合过于紧密，而flume的方式比较灵活，拔插式比较好，不会影响项目性能。

4.      flume和kafka采集日志区别，采集日志时中间停了，怎么记录之前的日志。

解答：

Flume采集日志是通过流的方式直接将日志收集到存储层，而kafka试讲日志缓存在kafka集群，待后期可以采集到存储层。

Flume采集中间停了，可以采用文件的方式记录之前的日志，而kafka是采用offset的方式记录之前的日志。

 



3.7     Kafka
1.      Kafka容错机制

解答：

分区备份，存在主备partition

2.      kafka数据流向

解答：

Producer à leader partition à follower partition(半数以上) àconsumer

3.      kafka+spark-streaming结合丢数据怎么解决？

解答：

spark streaming从1.2开始提供了数据的零丢失，想享受这个特性，需要满足如下条件：

数据输入需要可靠的sources和可靠的receivers

应用metadata必须通过应用driver checkpoint

WAL（write ahead log）

可靠的sources和receivers

spark streaming可以通过多种方式作为数据sources（包括kafka），输入数据通过receivers接收，通过replication存储于spark中（为了faultolerance，默认复制到两个spark executors），如果数据复制完成，receivers可以知道（例如kafka中更新offsets到zookeeper中）。这样当receivers在接收数据过程中crash掉，不会有数据丢失，receivers没有复制的数据，当receiver恢复后重新接收。


metadata checkpoint

可靠的sources和receivers，可以使数据在receivers失败后恢复，然而在driver失败后恢复是比较复杂的，一种方法是通过checkpoint metadata到HDFS或者S3。metadata包括：

configuration

code

一些排队等待处理但没有完成的RDD（仅仅是metadata，而不是data）

这样当driver失败时，可以通过metadata checkpoint，重构应用程序并知道执行到那个地方。

数据可能丢失的场景

可靠的sources和receivers，以及metadata checkpoint也不可以保证数据的不丢失，例如：

两个executor得到计算数据，并保存在他们的内存中

receivers知道数据已经输入

executors开始计算数据

driver突然失败

driver失败，那么executors都会被kill掉

因为executor被kill掉，那么他们内存中得数据都会丢失，但是这些数据不再被处理

executor中的数据不可恢复

WAL

为了避免上面情景的出现，spark streaming 1.2引入了WAL。所有接收的数据通过receivers写入HDFS或者S3中checkpoint目录，这样当driver失败后，executor中数据丢失后，可以通过checkpoint恢复。

At-Least-Once

尽管WAL可以保证数据零丢失，但是不能保证exactly-once，例如下面场景：

Receivers接收完数据并保存到HDFS或S3

在更新offset前，receivers失败了

Spark Streaming以为数据接收成功，但是Kafka以为数据没有接收成功，因为offset没有更新到zookeeper

随后receiver恢复了

从WAL可以读取的数据重新消费一次，因为使用的kafka High-Level消费API，从zookeeper中保存的offsets开始消费

WAL的缺点

通过上面描述，WAL有两个缺点：

降低了receivers的性能，因为数据还要存储到HDFS等分布式文件系统

对于一些resources，可能存在重复的数据，比如Kafka，在Kafka中存在一份数据，在Spark Streaming也存在一份（以WAL的形式存储在hadoop API兼容的文件系统中）

Kafka direct API

为了WAL的性能损失和exactly-once，spark streaming1.3中使用Kafka direct API。非常巧妙，Spark driver计算下个batch的offsets，指导executor消费对应的topics和partitions。消费Kafka消息，就像消费文件系统文件一样。


不再需要kafka receivers，executor直接通过Kafka API消费数据

WAL不再需要，如果从失败恢复，可以重新消费

exactly-once得到了保证，不会再从WAL中重复读取数据

总结

主要说的是spark streaming通过各种方式来保证数据不丢失，并保证exactly-once，每个版本都是spark streaming越来越稳定，越来越向生产环境使用发展。

 

4.      kafka中存储目录data/dir.....topic1和topic2怎么存储的，存储结构，data.....目录下有多少个分区，每个分区的存储格式是什么样的？

解答：

1、topic是按照“主题名-分区”存储的

2、分区个数由配置文件决定

3、每个分区下最重要的两个文件是0000000000.log和000000.index，0000000.log以默认1G大小回滚。

 

5.      的

6.      D 的

3.8     Spark
1.      mr和spark区别，怎么理解spark-rdd

解答：

Mr是文件方式的分布式计算框架，是将中间结果和最终结果记录在文件中，map和reduce的数据分发也是在文件中。

spark是内存迭代式的计算框架，计算的中间结果可以缓存内存，也可以缓存硬盘，但是不是每一步计算都需要缓存的。

Spark-rdd是一个数据的分区记录集合………………

2.      Spark应用转换流程

解答：

1、spark应用提交后，经历了一系列的转换，最后成为task在每个节点上执行

2、RDD的Action算子触发Job的提交，生成RDD DAG

3、由DAGScheduler将RDD DAG转化为Stage DAG，每个Stage中产生相应的Task集合

4、TaskScheduler将任务分发到Executor执行

5、每个任务对应相应的一个数据块，只用用户定义的函数处理数据块

3.      Driver运行在Worker上

解答：

通过org.apache.spark.deploy.Client类执行作业，作业运行命令如下：

作业执行流程描述：

1、客户端提交作业给Master

2、Master让一个Worker启动Driver，即SchedulerBackend。Worker创建一个DriverRunner线程，DriverRunner启动SchedulerBackend进程。

3、另外Master还会让其余Worker启动Exeuctor，即ExecutorBackend。Worker创建一个ExecutorRunner线程，ExecutorRunner会启动ExecutorBackend进程。

4、ExecutorBackend启动后会向Driver的SchedulerBackend注册。SchedulerBackend进程中包含DAGScheduler，它会根据用户程序，生成执行计划，并调度执行。对于每个stage的task，都会被存放到TaskScheduler中，ExecutorBackend向SchedulerBackend汇报的时候把TaskScheduler中的task调度到ExecutorBackend执行。

5、所有stage都完成后作业结束。

 

4.      Driver运行在客户端

解答：

作业执行流程描述：

1、客户端启动后直接运行用户程序，启动Driver相关的工作：DAGScheduler和BlockManagerMaster等。

2、客户端的Driver向Master注册。

3、Master还会让Worker启动Exeuctor。Worker创建一个ExecutorRunner线程，ExecutorRunner会启动ExecutorBackend进程。

4、ExecutorBackend启动后会向Driver的SchedulerBackend注册。Driver的DAGScheduler解析作业并生成相应的Stage，每个Stage包含的Task通过TaskScheduler分配给Executor执行。

5、所有stage都完成后作业结束。

 

5.      的

6.      的

7.      的

8.      的

3.9     Sqoop
1.      命令：

sqoop import --connect jdbc:mysql://192.168.56.204:3306/sqoop --username hive --password hive --table jobinfo --target-dir /sqoop/test7 --inline-lob-limit 16777216 --fields-terminated-by '\t' -m 2

 

sqoop create-hive-table --connect jdbc:mysql://192.168.56.204:3306/sqoop --table jobinfo --username hive --password hive --hive-table sqtest --fields-terminated-by "\t" --lines-terminated-by "\n";

2.      sqoop在导入数据到mysql中，如何让数据不重复导入？如果存在数据问题sqoop如何处理？

解答：

Sqoop是一个用来将Hadoop和关系型数据库中的数据相互转移的工具，可以将一个关系型数据库（例如 ： MySQL ,Oracle ,Postgres等）中的数据导进到Hadoop的HDFS中，也可以将HDFS的数据导进到关系型数据库中。

 

首先需以下要准备：

第一：hadoop的NameNode节点下lib文件夹中要有相应数据库驱动的jar包和sqoop的jar包。

第二：预先在相应的数据库创建Table，注：在HDFS的某个目录上的数据格式要和相应的表中的字段数量一致。

 

由于我这里使用的是Oracle数据库并且是使用Java来操作的。所以下面的代码以及截图都是以Java的例子：

首先标准化HDFS中文件格式，如下图：


 

Java代码如下：

Configuration conf = new Configuration();
conf.set("fs.default.name", "hdfs://192.168.115.5:9000");
conf.set("hadoop.job.ugi", "hadooper,hadoopgroup");
conf.set("mapred.job.tracker", "192.168.115.5:9001");


ArrayList<String> list = new ArrayList<String>(); // 定义一个list
list.add("--table");
list.add("A_BAAT_CLIENT"); // Oracle中的表。将来数据要导入到这个表中。
list.add("--export-dir");
list.add("/home/hadoop/traffic/capuse/near7date/activeUser/capuse_near7_activeUser_2013-02-06.log"); // hdfs上的目录。这个目录下的数据要导入到a_baat_client这个表中。
list.add("--connect");
list.add("jdbc:oracle:thin:@10.18.96.107:1521:life"); // Oracle的链接
list.add("--username");
list.add("TRAFFIC"); // Oracle的用户名
list.add("--password");
list.add("TRAFFIC"); // Oracle的密码
list.add("--input-fields-terminated-by");
list.add("|"); // 数据分隔符号
list.add("-m");
list.add("1");// 定义mapreduce的数量。


String[] arg = new String[1];
ExportTool exporter = new ExportTool();
Sqoop sqoop = new Sqoop(exporter);
sqoop.setConf(conf);
arg = list.toArray(new String[0]);
int result = Sqoop.runSqoop(sqoop, arg);
System.out.println("res:" + result); // 打印执行结果。

 

最后再在Main方法中运行即可，生成后表数据如下图所示：


通过上面的操作以及代码即可在Java中实现把HDFS数据生成对应的表数据；

不过除了可以用Java来实现，使用基本的命令也是可以的，命令如下：

在Hadoop bin目录中：

sqoop export --connect jdbc:oracle:thin:@10.18.96.107:1521:life \

--table A_BAAT_CLIENT --username TRAFFIC --password TRAFFIC \
--input-fields-terminated-by '|' \
--export-dir /home/hadoop/traffic/capuse/near7date/activeUser/test.log  -m 1

意思和上面Java中代码一样。

 

注意：

1、数据库表名、用户名、密码使用大写（这有可能会出现问题，因为我在测试过程中，使用小写时出现错误，出现No Columns这个经典错误。所以推荐大写，当然这不是必须）；

2、预先建好相应的Table；

 

 

3.      的

4.      的

5.      的

6.      的

3.10其他
3.10.1 Redis
1.      Redis,传统数据库,hbase,hive 每个之间的区别

解答：

redis：分布式缓存，强调缓存，内存中数据

传统数据库：注重关系

hbase：列式数据库，无法做关系数据库的主外键，用于存储海量数据，底层基于hdfs

hive：数据仓库工具，底层是mapreduce。不是数据库，不能用来做用户的交互存储

 

 

2.      是

3.10.2 数据库
1.      反向索引

解答：

倒排索引(Inverted index)

适用范围：搜索引擎，关键字查询

基本原理及要点：为何叫倒排索引？一种索引方法，被用来存储在全文搜索下某个单词在一个文档或者一组文档中的存储位置的映射。

以英文为例，下面是要被索引的文本：

T0 = “it is what it is”

T1 = “what is it”

T2 = “it is a banana”

我们就能得到下面的反向文件索引：

“a”: {2}

“banana”: {2}

“is”: {0, 1, 2}

“it”: {0, 1, 2}

“what”: {0, 1}

检索的条件”what”,”is”和”it”将对应集合的交集。

正向索引开发出来用来存储每个文档的单词的列表。正向索引的查询往往满足每个文档有序 频繁的全文查询和每个单词在校验文档中的验证这样的查询。在正向索引中，文档占据了中心的位置，每个文档指向了一个它所包含的索引项的序列。也就是说文档指向了它包含的那些单词，而反向索引则是单词指向了包含它的文档，很容易看到这个反向的关系。

 

 

2.      数据库的三大范式？

解答：

数据库范式1NF 2NF 3NF BCNF(实例）
    设计范式（范式,数据库设计范式,数据库的设计范式）是符合某一种级别的关系模式的集合。构造数据库必须遵循一定的规则。在关系数据库中，这种规则就是范式。关系数据库中的关系必须满足一定的要求，即满足不同的范式。目前关系数据库有六种范式：第一范式（1NF）、第二范式（2NF）、第三范式（3NF）、第四范式（4NF）、第五范式（5NF）和第六范式（6NF）。满足最低要求的范式是第一范式（1NF）。在第一范式的基础上进一步满足更多要求的称为第二范式（2NF），其余范式以次类推。一般说来，数据库只需满足第三范式（3NF）就行了。下面我们举例介绍第一范式（1NF）、第二范式（2NF）和第三范式（3NF）。 
    在创建一个数据库的过程中，范化是将其转化为一些表的过程，这种方法可以使从数据库得到的结果更加明确。这样可能使数据库产生重复数据，从而导致创建多余的表。范化是在识别数据库中的数据元素、关系，以及定义所需的表和各表中的项目这些初始工作之后的一个细化的过程。 
    下面是范化的一个例子 Customer Item purchased Purchase price Thomas Shirt $40 Maria Tennis shoes $35 Evelyn Shirt $40 Pajaro Trousers $25 
如果上面这个表用于保存物品的价格，而你想要删除其中的一个顾客，这时你就必须同时删除一个价格。范化就是要解决这个问题，你可以将这个表化为两个表，一个用于存储每个顾客和他所买物品的信息，另一个用于存储每件产品和其价格的信息，这样对其中一个表做添加或删除操作就不会影响另一个表。

关系数据库的几种设计范式介绍

1 第一范式（1NF）
    在任何一个关系数据库中，第一范式（1NF）是对关系模式的基本要求，不满足第一范式（1NF）的数据库就不是关系数据库。 
    所谓第一范式（1NF）是指数据库表的每一列都是不可分割的基本数据项，同一列中不能有多个值，即实体中的某个属性不能有多个值或者不能有重复的属性。如果出现重复的属性，就可能需要定义一个新的实体，新的实体由重复的属性构成，新实体与原实体之间为一对多关系。在第一范式（1NF）中表的每一行只包含一个实例的信息。例如，对于图3-2 中的员工信息表，不能将员工信息都放在一列中显示，也不能将其中的两列或多列在一列中显示；员工信息表的每一行只表示一个员工的信息，一个员工的信息在表中只出现一次。简而言之，第一范式就是无重复的列。

2 第二范式（2NF）
    第二范式（2NF）是在第一范式（1NF）的基础上建立起来的，即满足第二范式（2NF）必须先满足第一范式（1NF）。第二范式（2NF）要求数据库表中的每个实例或行必须可以被惟一地区分。为实现区分通常需要为表加上一个列，以存储各个实例的惟一标识。如图3-2 员工信息表中加上了员工编号（emp_id）列，因为每个员工的员工编号是惟一的，因此每个员工可以被惟一区分。这个惟一属性列被称为主关键字或主键、主码。 
第二范式（2NF）要求实体的属性完全依赖于主关键字。所谓完全依赖是指不能存在仅依赖主关键字一部分的属性，如果存在，那么这个属性和主关键字的这一部分应该分离出来形成一个新的实体，新实体与原实体之间是一对多的关系。为实现区分通常需要为表加上一个列，以存储各个实例的惟一标识。简而言之，第二范式就是非主属性非部分依赖于主关键字。

3 第三范式（3NF） 
    满足第三范式（3NF）必须先满足第二范式（2NF）。简而言之，第三范式（3NF）要求一个数据库表中不包含已在其它表中已包含的非主关键字信息。例如，存在一个部门信息表，其中每个部门有部门编号（dept_id）、部门名称、部门简介等信息。那么在图3-2的员工信息表中列出部门编号后就不能再将部门名称、部门简介等与部门有关的信息再加入员工信息表中。如果不存在部门信息表，则根据第三范式（3NF）也应该构建它，否则就会有大量的数据冗余。简而言之，第三范式就是属性不依赖于其它非主属性。
数据库设计三大范式应用实例剖析 
     数据库的设计范式是数据库设计所需要满足的规范，满足这些规范的数据库是简洁的、结构明晰的，同时，不会发生插入（insert）、删除（delete）和更新（update）操作异常。反之则是乱七八糟，不仅给数据库的编程人员制造麻烦，而且面目可憎，可能存储了大量不需要的冗余信息。 
    设计范式是不是很难懂呢？非也，大学教材上给我们一堆数学公式我们当然看不懂，也记不住。所以我们很多人就根本不按照范式来设计数据库。 
实质上，设计范式用很形象、很简洁的话语就能说清楚，道明白。本文将对范式进行通俗地说明，并以笔者曾经设计的一个简单论坛的数据库为例来讲解怎样将这些范式应用于实际工程。

范式说明 
    第一范式（1NF）：数据库表中的字段都是单一属性的，不可再分。这个单一属性由基本类型构成，包括整型、实数、字符型、逻辑型、日期型等。

    例如，如下的数据库表是符合第一范式的：

    字段1 字段2 字段3 字段4

    而这样的数据库表是不符合第一范式的：

    字段1 字段2 字段3 字段4 
    字段3.1 字段3.2 

    很显然，在当前的任何关系数据库管理系统（DBMS）中，傻瓜也不可能做出不符合第一范式的数据库，因为这些DBMS不允许你把数据库表的一列再分成二列或多列。因此，你想在现有的DBMS中设计出不符合第一范式的数据库都是不可能的。 
    第二范式（2NF）：数据库表中不存在非关键字段对任一候选关键字段的部分函数依赖（部分函数依赖指的是存在组合关键字中的某些字段决定非关键字段的情况），也即所有非关键字段都完全依赖于任意一组候选关键字。

    假定选课关系表为SelectCourse(学号, 姓名, 年龄, 课程名称, 成绩, 学分)，关键字为组合关键字(学号, 课程名称)，因为存在如下决定关系： 
    (学号, 课程名称) → (姓名, 年龄, 成绩, 学分) 

    这个数据库表不满足第二范式，因为存在如下决定关系： 
    (课程名称) → (学分) 
    (学号) → (姓名, 年龄) 
    即存在组合关键字中的字段决定非关键字的情况。 
    由于不符合2NF，这个选课关系表会存在如下问题： 
    (1) 数据冗余： 
    同一门课程由n个学生选修，"学分"就重复n-1次；同一个学生选修了m门课程，姓名和年龄就重复了m-1次。 
    (2) 更新异常： 
    若调整了某门课程的学分，数据表中所有行的"学分"值都要更新，否则会出现同一门课程学分不同的情况。 
    (3) 插入异常： 
    假设要开设一门新的课程，暂时还没有人选修。这样，由于还没有"学号"关键字，课程名称和学分也无法记录入数据库。 
    (4) 删除异常： 
    假设一批学生已经完成课程的选修，这些选修记录就应该从数据库表中删除。但是，与此同时，课程名称和学分信息也被删除了。很显然，这也会导致插入异常。 
    把选课关系表SelectCourse改为如下三个表： 
    学生：Student(学号, 姓名, 年龄)； 
    课程：Course(课程名称, 学分)； 
    选课关系：SelectCourse(学号, 课程名称, 成绩)。 
    这样的数据库表是符合第二范式的， 消除了数据冗余、更新异常、插入异常和删除异常。 
    另外，所有单关键字的数据库表都符合第二范式，因为不可能存在组合关键字。

    第三范式（3NF）：在第二范式的基础上，数据表中如果不存在非关键字段对任一候选关键字段的传递函数依赖则符合第三范式。所谓传递函数依赖，指的是如果存在"A → B → C"的决定关系，则C传递函数依赖于A。因此，满足第三范式的数据库表应该不存在如下依赖关系： 
    关键字段 → 非关键字段x → 非关键字段y 
    假定学生关系表为Student(学号, 姓名, 年龄, 所在学院, 学院地点, 学院电话)，关键字为单一关键字"学号"，因为存在如下决定关系： 
    (学号) → (姓名, 年龄, 所在学院, 学院地点, 学院电话) 
    这个数据库是符合2NF的，但是不符合3NF，因为存在如下决定关系： 
    (学号) → (所在学院) → (学院地点, 学院电话) 
    即存在非关键字段"学院地点"、"学院电话"对关键字段"学号"的传递函数依赖。    
    它也会存在数据冗余、更新异常、插入异常和删除异常的情况，读者可自行分析得知。 
    把学生关系表分为如下两个表： 
    学生：(学号, 姓名, 年龄, 所在学院)； 
   学院：(学院, 地点, 电话)。 

    这样的数据库表是符合第三范式的，消除了数据冗余、更新异常、插入异常和删除异常。 
鲍依斯-科得范式（BCNF）：在第三范式的基础上，数据库表中如果不存在任何字段对任一候选关键字段的传递函数依赖则符合第三范式。 
    假设仓库管理关系表为StorehouseManage(仓库ID, 存储物品ID, 管理员ID, 数量)，且有一个管理员只在一个仓库工作；一个仓库可以存储多种物品。这个数据库表中存在如下决定关系： 
    (仓库ID, 存储物品ID) →(管理员ID, 数量) 
    (管理员ID, 存储物品ID) → (仓库ID, 数量) 
    所以，(仓库ID, 存储物品ID)和(管理员ID, 存储物品ID)都是StorehouseManage的候选关键字，表中的唯一非关键字段为数量，它是符合第三范式的。但是，由于存在如下决定关系： 
    (仓库ID) → (管理员ID) 
    (管理员ID) → (仓库ID) 
    即存在关键字段决定关键字段的情况，所以其不符合BCNF范式。它会出现如下异常情况： 
    (1) 删除异常： 
    当仓库被清空后，所有"存储物品ID"和"数量"信息被删除的同时，"仓库ID"和"管理员ID"信息也被删除了。 
    (2) 插入异常： 
    当仓库没有存储任何物品时，无法给仓库分配管理员。 
    (3) 更新异常： 
    如果仓库换了管理员，则表中所有行的管理员ID都要修改。 
    把仓库管理关系表分解为二个关系表： 
    仓库管理：StorehouseManage(仓库ID, 管理员ID)； 
    仓库：Storehouse(仓库ID, 存储物品ID, 数量)。 
    这样的数据库表是符合BCNF范式的，消除了删除异常、插入异常和更新异常。 
范式应用 
    我们来逐步搞定一个论坛的数据库，有如下信息： 
    （1） 用户：用户名，email，主页，电话，联系地址 
    （2） 帖子：发帖标题，发帖内容，回复标题，回复内容 
    第一次我们将数据库设计为仅仅存在表： 
    用户名 email 主页 电话 联系地址 发帖标题 发帖内容 回复标题 回复内容 
    这个数据库表符合第一范式，但是没有任何一组候选关键字能决定数据库表的整行，唯一的关键字段用户名也不能完全决定整个元组。我们需要增加"发帖ID"、"回复ID"字段，即将表修改为： 
    用户名 email 主页 电话 联系地址 发帖ID 发帖标题 发帖内容 回复ID 回复标题 回复内容 
    这样数据表中的关键字(用户名，发帖ID，回复ID)能决定整行： 
    (用户名,发帖ID,回复ID) → (email,主页,电话,联系地址,发帖标题,发帖内容,回复标题,回复内容) 
    但是，这样的设计不符合第二范式，因为存在如下决定关系： 
    (用户名) → (email,主页,电话,联系地址) 
    (发帖ID) → (发帖标题,发帖内容) 
    (回复ID) → (回复标题,回复内容) 
    即非关键字段部分函数依赖于候选关键字段，很明显，这个设计会导致大量的数据冗余和操作异常。 
我们将数据库表分解为（带下划线的为关键字）： 
（1） 用户信息：用户名，email，主页，电话，联系地址 
（2） 帖子信息：发帖ID，标题，内容 
（3） 回复信息：回复ID，标题，内容 
（4） 发贴：用户名，发帖ID 
（5） 回复：发帖ID，回复ID 
    这样的设计是满足第1、2、3范式和BCNF范式要求的，但是这样的设计是不是最好的呢？ 
不一定。 
    观察可知，第4项"发帖"中的"用户名"和"发帖ID"之间是1：N的关系，因此我们可以把"发帖"合并到第2项的"帖子信息"中；第5项"回复"中的"发帖ID"和"回复ID"之间也是1：N的关系，因此我们可以把"回复"合并到第3项的"回复信息"中。这样可以一定量地减少数据冗余，新的设计为： 
（1） 用户信息：用户名，email，主页，电话，联系地址 
（2） 帖子信息：用户名，发帖ID，标题，内容 
（3） 回复信息：发帖ID，回复ID，标题，内容 
    数据库表1显然满足所有范式的要求； 
    数据库表2中存在非关键字“标题”、“内容”对关键字段“发帖ID”的部分函数依赖，即不满足第二范式的要求，但是这一设计并不会导致数据冗余和操作异常； 
    数据库表3中也存在非关键字段"标题"、"内容"对关键字段"回复ID"的部分函数依赖，也不满足第二范式的要求，但是与数据库表2相似，这一设计也不会导致数据冗余和操作异常。 
    由此可以看出，并不一定要强行满足范式的要求，对于1：N关系，当1的一边合并到N的那边后，N的那边就不再满足第二范式了，但是这种设计反而比较好！ 
    对于M：N的关系，不能将M一边或N一边合并到另一边去，这样会导致不符合范式要求，同时导致操作异常和数据冗余。 
    对于1：1的关系，我们可以将左边的1或者右边的1合并到另一边去，设计导致不符合范式要求，但是并不会导致操作异常和数据冗余。

 

3.       

4.      是

第4部分           解答题

第4部分      

4.1     Hadoop解答题
4.1.1  MapReduce编程题
1.      当前日志采样格式为，请用你最熟悉的语言编写一个 mapreduce，并计算第四列每个元素出现的个数。

1.  a,b,c,d

2.  b,b,f,e

3.  a,a,c,f

2.      给定 a、b 两个文件，各存放 50 亿个 url，每个 url 各占 64 字节，内存限制是 4G，让你找出 a、b 文件共同的 url？

解答：

方案 1：将大文件分成能够被内存加载的小文件。

可以估计每个文件安的大小为 50G×64=320G，远远大于内存限制的 4G。所以不可能将其完全加载到内存中处理。考虑采取分而治之的方法。

s 遍历文件 a，对每个 url 求取 ，然后根据所取得的值将 url 分别存储到 1000 个小文件（记为 ）中。

这样每个小文件的大约为 300M。

s 遍历文件 b，采取和 a 相同的方式将 url 分别存储到 1000 各小文件（记为 ）。这样处理后，所有可能相同的 url 都在对应的小文件（ ）中，不对应的小文件不可能有相同的 url。然后我们只要求出 1000 对小文件中相同的 url 即可。

s 求每对小文件中相同的 url 时，可以把其中一个小文件的 url 存储到 hash_set 中。然后遍历另一个小文件的每个 url，看其是否在刚才构建的 hash_set 中，如果是，那么就是共同的 url，存到文件里面就可以了。

方案 2：内存映射成 BIT 最小存储单元。

如果允许有一定的错误率，可以使用 Bloom filter，4G 内存大概可以表示 340 亿 bit。将其中一个文件中的url使用Bloom filter映射为这340亿bit，然后挨个读取另外一个文件的url，检查是否与Bloom filter，如果是，那么该 url 应该是共同的 url（注意会有一定的错误率）。

3.      有 10 个文件，每个文件 1G，每个文件的每一行存放的都是用户的 query，每个文件的 query 都可能重复。要求你按照query 的频度排序。

解答：

方案 1：

s 顺序读取 10 个文件，按照 hash(query)%10 的结果将 query 写入到另外 10 个文件（记为 ）中。这样新生成的文件每个的大小大约也 1G（假设 hash 函数是随机的）。

s 找一台内存在 2G 左右的机器，依次对 用 hash_map(query, query_count)来统计每个 query 出现的次数。利用快速/堆/归并排序按照出现次数进行排序。将排序好的 query 和对应的 query_cout 输出到文件中。这样得到了 10 个排好序的文件（记为 ）。

s 对 这 10 个文件进行归并排序（内排序与外排序相结合）。

方案 2：

一般 query 的总量是有限的，只是重复的次数比较多而已，可能对于所有的 query，一次性就可以加入到内存了。这样，我们就可以采用 trie 树/hash_map 等直接来统计每个 query 出现的次数，然后按出现

次数做快速/堆/归并排序就可以了。

方案 3：

与方案 1 类似，但在做完 hash，分成多个文件后，可以交给多个文件来处理，采用分布式的架构来处

理（比如 MapReduce），最后再进行合并。

//一般在大文件中找出出现频率高的，先把大文件映射成小文件，模 1000，在小文件中找到高频的

4.      有一个 1G 大小的一个文件，里面每一行是一个词，词的大小不超过 16 字节，内存限制大小是 1M。返回频数最高的 100 个词。

解答：

方案 1：顺序读文件中，对于每个词 x，取 ，然后按照该值存到 5000 个小文件（记为 ）中。这样每个文件大概是 200k 左右。如果其中的有的文件超过了 1M 大小，还可以按照类似的方法继续往下分，知道分解得到的小文件的大小都不超过 1M。 对每个小文件，统计每个文件中出现的词以及相应的频率（可以采用 trie 树/hash_map 等），并取出出现频率最大的 100 个词（可以用含 100 个结 点的最小堆），并把 100词及相应的频率存入文件，这样又得到了 5000 个文件。下一步就是把这 5000 个文件进行归并（类似与归并排序）的过程了。

方案2:

1.       将文件逐行读写到另一个文件中，并将每行单词全变成小写

2.       十六次循环执行，将每行单词按照a-z写到不同文件里

3.       最后相同的单词都写在了通一个文件里

4.       再将文件读写到各自另一个文件里，内容是“单词 个数”

5.       定义一个treemap，大小是100，依次插入大的，移除小的

6.       最后得出结果

5.      海量日志数据，提取出某日访问百度次数最多的那个 IP。

解答：

 

1.       先根据日期在日志文件中提取出ip，根据ip哈希进行分写N个文件。

2.       采用mapreduce的word cont

方案 1：首先是这一天，并且是访问百度的日志中的 IP 取出来，逐个写入到一个大文件中。注意到 IP是 32 位的，最多有 个 IP。同样可以采用映射的方法，比如模 1000，把整个大文件映射为 1000 个小文件，再找出每个小文中出现频率最大的 IP（可以采用 hash_map 进行频率统计，然后再找出频率最大的几个）及相应的频率。然后再在这 1000 个最大的 IP 中，找出那个频率最大的 IP，即为所求。

6.      在 2.5 亿个整数中找出不重复的整数，内存不足以容纳这 2.5 亿个整数。

解答：

方案 1：采用 2-Bitmap（每个数分配 2bit，00 表示不存在，01 表示出现一次，10 表示多次，11 无意义）进 行，共需内存 内存，还可以接受。然后扫描这 2.5 亿个整数，查看 Bitmap 中相对应位，如果是00 变 01，01 变 10，10 保持不变。所描完事后，查看 bitmap，把对应位是 01 的整数输出即可。

方案 2：也可采用上题类似的方法，进行划分小文件的方法。然后在小文件中找出不重复的整数，并排序。然后再进行归并，注意去除重复的元素。

方案3：

1.       将2.5亿个整数重写到一个文件里，内个整数占一行。

2.       进行对半排序重写到新的文件里，这样最后2.5亿个整数在文件里便是有序的了

3.       读取文本，将不重复的写到一个新的文件里即可。

7.      海量数据分布在 100 台电脑中，想个办法高校统计出这批数据的 TOP10。

解答：

方案 1：（方法不正确，取出来得不一定是top10）

s 在每台电脑上求出 TOP10，可以采用包含 10 个元素的堆完成（TOP10 小，用最大堆，TOP10 大，用最小堆）。比如求 TOP10 大，我们首先取前 10 个元素调整成最小堆，如果发现，然后扫描后面的数据，并与堆顶元素比较，如果比堆顶元素大，那么用该元素替换堆顶，然后再调整为最小堆。最后堆中的元 素就是 TOP10 大。

s 求出每台电脑上的 TOP10 后，然后把这 100 台电脑上的 TOP10 组合起来，共 1000 个数据，再利用上面类似的方法求出 TOP10 就可以了。

8.      怎么在海量数据中找出重复次数最多的一个？

解答：

方案 1：（同上，方法错误）

先做 hash，然后求模映射为小文件，求出每个小文件中重复次数最多的一个，并记录重复次数。然后找出上一步求出的数据中重复次数最多的一个就是所求（具体参考前面的题）。

 

正确的方法，先排除

9.      上千万或上亿数据（有重复），统计其中出现次数最多的前 N 个数据。

解答：

10.  1000 万字符串，其中有些是重复的，需要把重复的全部去掉，保留没有重复的字符串。请怎么设计和实现？

解答：

11.  一个文本文件，大约有一万行，每行一个词，要求统计出其中最频繁出现的前 10 个词，请给出思想，给出时间复杂度分析。

解答：

方案 1：这题是考虑时间效率。用 trie 树统计每个词出现的次数，时间复杂度是 O(n*le)（le 表示单词的平准长 度）。然后是找出出现最频繁的前 10 个词，可以用堆来实现，前面的题中已经讲到了，时间复杂度是 O(n*lg10)。所以总的时间复杂度，是 O(n*le)与 O(n*lg10)中较大的哪一个。

12.  一个文本文件，找出前 10 个经常出现的词，但这次文件比较长，说是上亿行或十亿行，总之无法一次读入内存，问最优解。

解答：

13.  100w 个数中找出最大的 100 个数。

解答：

14.   

15.  D

16.  D

17.  D

第5部分           处理海量数据问题之六把密匙

第5部分       

5.1      密匙一、分而治之/Hash映射 + Hash统计 + 堆/快速/归并排序
1、海量日志数据，提取出某日访问百度次数最多的那个IP。

    既然是海量数据处理，那么可想而知，给我们的数据那就一定是海量的。针对这个数据的海量，我们如何着手呢?对的，无非就是分而治之/hash映射 + hash统计 + 堆/快速/归并排序，说白了，就是先映射，而后统计，最后排序：

1. 分而治之/hash映射：针对数据太大，内存受限，只能是：把大文件化成(取模映射)小文件，即16字方针：大而化小，各个击破，缩小规模，逐个解决

2. hash统计：当大文件转化了小文件，那么我们便可以采用常规的hash_map(ip，value)来进行频率统计。

3. 堆/快速排序：统计完了之后，便进行排序(可采取堆排序)，得到次数最多的IP。

   具体而论，则是： “首先是这一天，并且是访问百度的日志中的IP取出来，逐个写入到一个大文件中。注意到IP是32位的，最多有个2^32个IP。同样可以采用映射的方 法，比如模1000，把整个大文件映射为1000个小文件，再找出每个小文中出现频率最大的IP（可以采用hash_map进行频率统计，然后再找出频率 最大的几个）及相应的频率。然后再在这1000个最大的IP中，找出那个频率最大的IP，即为所求。”--十道海量数据处理面试题与十个方法大总结。

    注：Hash取模是一种等价映射，不会存在同一个元素分散到不同小文件中去的情况，即这里采用的是mod1000算法，那么相同的IP在hash后，只可能落在同一个文件中，不可能被分散的。

    那到底什么是hash映射呢？我换个角度举个浅显直白的例子，如本文的URL是：http://blog.csdn.net/v_july_v/article/details/7382693，当我把这个URL发表在微博上，便被映射成了：http://t.cn/zOixljh，于此，我们发现URL本身的长度被缩短了，但这两个URL对应的文章的是同一篇即本文。OK，有兴趣的，还可以再了解下一致性hash算法，见此文第五部分：http://blog.csdn.net/v_july_v/article/details/6879101。

2、搜索引擎会通过日志文件把用户每次检索使用的所有检索串都记录下来，每个查询串的长度为1-255字节。

    假设目前有一千万个记录（这些查询串的重复度比较高，虽然总数是1千万，但如果除去重复后，不超过3百万个。一个查询串的重复度越高，说明查询它的用户越多，也就是越热门），请你统计最热门的10个查询串，要求使用的内存不能超过1G。

    由上面第1题，我们知道，数据大则划为小的，但如果数据规模比较小，能一次性装入内存呢?比如这第2题，虽然有一千万个Query，但是由于重复度比较 高，因此事实上只有300万的Query，每个Query255Byte，因此我们可以考虑把他们都放进内存中去，而现在只是需要一个合适的数据结构，在 这里，Hash Table绝对是我们优先的选择。所以我们摒弃分而治之/hash映射的方法，直接上hash统计，然后排序。So，

1. hash 统计：先对这批海量数据预处理(维护一个Key为Query字串，Value为该Query出现次数的HashTable，即 hash_map(Query，Value)，每次读取一个Query，如果该字串不在Table中，那么加入该字串，并且将Value值设为1；如果该 字串在Table中，那么将该字串的计数加一即可。最终我们在O(N)的时间复杂度内用Hash表完成了统计；

2. 堆排序：第二步、借助堆 这个数据结构，找出Top K，时间复杂度为N‘logK。即借助堆结构，我们可以在log量级的时间内查找和调整/移动。因此，维护一个K(该题目中是10)大小的小根堆，然后遍 历300万的Query，分别和根元素进行对比所以，我们最终的时间复杂度是：O（N） + N'*O（logK），（N为1000万，N’为300万）。

    别忘了这篇文章中所述的堆排序思路：“维 护k个元素的最小堆，即用容量为k的最小堆存储最先遍历到的k个数，并假设它们即是最大的k个数，建堆费时O（k），并调整堆（费时O（logk））后， 有k1>k2>...kmin（kmin设为小顶堆中最小元素）。继续遍历数列，每次遍历一个元素x，与堆顶元素比较，若 x>kmin，则更新堆（用时logk），否则不更新堆。这样下来，总费时O（k*logk+（n-k）*logk）=O（n*logk）。此方法 得益于在堆中，查找等各项操作时间复杂度均为logk。”--第三章续、Top K算法问题的实现。
    当然，你也可以采用trie树，关键字域存该查询串出现的次数，没有出现为0。最后用10个元素的最小推来对出现频率进行排序。

3、有一个1G大小的一个文件，里面每一行是一个词，词的大小不超过16字节，内存限制大小是1M。返回频数最高的100个词。
       由上面那两个例题，分而治之 + hash统计 + 堆/快速排序这个套路，我们已经开始有了屡试不爽的感觉。下面，再拿几道再多多验证下。请看此第3题：又是文件很大，又是内存受限，咋办?还能怎么办呢?无非还是：

1. 分 而治之/hash映射：顺序读文件中，对于每个词x，取hash(x)%5000，然后按照该值存到5000个小文件（记为 x0,x1,...x4999）中。这样每个文件大概是200k左右。如果其中的有的文件超过了1M大小，还可以按照类似的方法继续往下分，直到分解得到 的小文件的大小都不超过1M。

2. hash统计：对每个小文件，采用trie树/hash_map等统计每个文件中出现的词以及相应的频率。

3. 堆/归并排序：取出出现频率最大的100个词（可以用含100个结点的最小堆），并把100个词及相应的频率存入文件，这样又得到了5000个文件。最后就是把这5000个文件进行归并（类似于归并排序）的过程了。

4、海量数据分布在100台电脑中，想个办法高效统计出这批数据的TOP10。

    此题与上面第3题类似，

1. 堆排序：在每台电脑上求出TOP10，可以采用包含10个元素的堆完成（TOP10小，用最大堆，TOP10大，用最小堆）。比如求TOP10大，我们首先取前10个元素调整成最小堆，如果发现，然后扫描后面的数据，并与堆顶元素比较，如果比堆顶元素大，那么用该元素替换堆顶，然后再调整为最小堆。最后堆中的元素就是TOP10大。

2. 求出每台电脑上的TOP10后，然后把这100台电脑上的TOP10组合起来，共1000个数据，再利用上面类似的方法求出TOP10就可以了。

    上述第4题的此解法，经读者反应有问题，如举个例子如比如求2个文件中的top2，照你这种算法，如果第一个文件里有

a 49次

b 50次

c 2次

d 1次

    第二个文件里有

a 9次

b 1次

c 11次

d 10次

     虽然第 一个文件里出来top2是b（50次）,a（49次）,第二个文件里出来top2是c（11次）,d（10次）,然后2个top2：b（50次）a（49 次）与c（11次）d（10次）归并，则算出所有的文件的top2是b(50 次),a(49 次),但实际上是a(58 次),b(51 次)。是否真是如此呢?若真如此，那作何解决呢？

    正如老梦所述：

    首先，先把所有的数据遍历一遍做一次hash(保证相同的数据条目划分到同一台电脑上进行运算)，然后根据hash结果重新分布到100台电脑中，接下来的算法按照之前的即可。

    最后由于a可能出现在不同的电脑，各有一定的次数，再对每个相同条目进行求和（由于上一步骤中hash之后，也方便每台电脑只需要对自己分到的条目内进行求和，不涉及到别的电脑，规模缩小）。

5、有10个文件，每个文件1G，每个文件的每一行存放的都是用户的query，每个文件的query都可能重复。要求你按照query的频度排序。

   直接上：

1. hash映射：顺序读取10个文件，按照hash(query)%10的结果将query写入到另外10个文件（记为）中。这样新生成的文件每个的大小大约也1G（假设hash函数是随机的）。

2. hash统计：找一台内存在2G左右的机器，依次对用hash_map(query, query_count)来统计每个query出现的次数。注：hash_map(query,query_count)是用来统计每个query的出现次数，不是存储他们的值，出现一次，则count+1。

3. 堆/快速/归并排序：利用快速/堆/归并排序按照出现次数进行排序。将排序好的query和对应的query_cout输出到文件中。这样得到了10个排好序的文件（记为）。对这10个文件进行归并排序（内排序与外排序相结合）。

     除此之外，此题还有以下两个方法：
    方案2：一般query的总量是有限的，只是重复的次数比较多而已，可能对于所有的query，一次性就可以加入到内存了。这样，我们就可以采用trie树/hash_map等直接来统计每个query出现的次数，然后按出现次数做快速/堆/归并排序就可以了。

    方案3：与方案1类似，但在做完hash，分成多个文件后，可以交给多个文件来处理，采用分布式的架构来处理（比如MapReduce），最后再进行合并。

6、 给定a、b两个文件，各存放50亿个url，每个url各占64字节，内存限制是4G，让你找出a、b文件共同的url？

    可以估计每个文件安的大小为5G×64=320G，远远大于内存限制的4G。所以不可能将其完全加载到内存中处理。考虑采取分而治之的方法。

1. 分而治之/hash映射：遍历文件a，对每个url求取 ，然后根据所取得的值将url分别存储到1000个小文件（记为 ）中。这样每个小文件的大约为300M。遍历文件b，采取和a相同的方式将url分别存储到1000小文件中（记为 ）。这样处理后，所有可能相同的url都在对应的小文件（ ）中，不对应的小文件不可能有相同的url。然后我们只要求出1000对小文件中相同的url即可。

2. hash统计：求每对小文件中相同的url时，可以把其中一个小文件的url存储到hash_set中。然后遍历另一个小文件的每个url，看其是否在刚才构建的hash_set中，如果是，那么就是共同的url，存到文件里面就可以了。

    OK，此第一种方法：分而治之/hash映射 + hash统计 + 堆/快速/归并排序，再看最后三道题，如下：

7、怎么在海量数据中找出重复次数最多的一个？

    方案1：先做hash，然后求模映射为小文件，求出每个小文件中重复次数最多的一个，并记录重复次数。然后找出上一步求出的数据中重复次数最多的一个就是所求（具体参考前面的题）。

8、上千万或上亿数据（有重复），统计其中出现次数最多的钱N个数据。

    方案1：上千万或上亿的数据，现在的机器的内存应该能存下。所以考虑采用hash_map/搜索二叉树/红黑树等来进行统计次数。然后就是取出前N个出现次数最多的数据了，可以用第2题提到的堆机制完成。

9、一个文本文件，大约有一万行，每行一个词，要求统计出其中最频繁出现的前10个词，请给出思想，给出时间复杂度分析。

     方案1：这题是考虑时间效率。用trie树统计每个词出现的次数，时间复杂度是O(n*le)（le表示单词的平准长度）。然后是找出出现最频繁的前 10个词，可以用堆来实现，前面的题中已经讲到了，时间复杂度是O(n*lg10)。所以总的时间复杂度，是O(n*le)与O(n*lg10)中较大的 哪一个。

    接下来，咱们来看第二种方法，双层捅划分。

 

5.2      密匙二、双层桶划分
双层桶划分----其实本质上还是分而治之的思想，重在“分”的技巧上！
　　适用范围：第k大，中位数，不重复或重复的数字
　　基本原理及要点：因为元素范围很大，不能利用直接寻址表，所以通过多次划分，逐步确定范围，然后最后在一个可以接受的范围内进行。可以通过多次缩小，双层只是一个例子。
　　扩展：
　　问题实例：

        10、2.5亿个整数中找出不重复的整数的个数，内存空间不足以容纳这2.5亿个整数。
　　有点像鸽巢原理，整数个数为2^32,也就是，我们可以将这2^32个数，划分为2^8个区域(比如用单个文件代表一个区域)，然后将数据分离到不同的区域，然后不同的区域在利用bitmap就可以直接解决了。也就是说只要有足够的磁盘空间，就可以很方便的解决。

       11、5亿个int找它们的中位数。
　 　这个例子比上面那个更明显。首先我们将int划分为2^16个区域，然后读取数据统计落到各个区域里的数的个数，之后我们根据统计结果就可以判断中位数 落到那个区域，同时知道这个区域中的第几大数刚好是中位数。然后第二次扫描我们只统计落在这个区域中的那些数就可以了。
　　实际上，如果不是 int是int64，我们可以经过3次这样的划分即可降低到可以接受的程度。即可以先将int64分成2^24个区域，然后确定区域的第几大数，在将该区 域分成2^20个子区域，然后确定是子区域的第几大数，然后子区域里的数的个数只有2^20，就可以直接利用direct addr table进行统计了。

 

5.3      密匙三：Bloom filter/Bitmap
Bloom filter

关于什么是Bloom filter，请参看此文：海量数据处理之Bloom Filter详解。
　　适用范围：可以用来实现数据字典，进行数据的判重，或者集合求交集
　　基本原理及要点：
　 　对于原理来说很简单，位数组+k个独立hash函数。将hash函数对应的值的位数组置1，查找时如果发现所有hash函数对应位都是1说明存在，很明 显这个过程并不保证查找的结果是100%正确的。同时也不支持删除一个已经插入的关键字，因为该关键字对应的位会牵动到其他的关键字。所以一个简单的改进 就是 counting Bloom filter，用一个counter数组代替位数组，就可以支持删除了。
　　还有一个比较重要的问题，如何 根据输入元素个数n，确定位数组m的大小及hash函数个数。当hash函数个数k=(ln2)*(m/n)时错误率最小。在错误率不大于E的情况下，m 至少要等于n*lg(1/E)才能表示任意n个元素的集合。但m还应该更大些，因为还要保证bit数组里至少一半为0，则m应该>=nlg(1 /E)*lge 大概就是nlg(1/E)1.44倍(lg表示以2为底的对数)。
　　举个例子我们假设错误率为0.01，则此时m应大概是n的13倍。这样k大概是8个。
　　注意这里m与n的单位不同，m是bit为单位，而n则是以元素个数为单位(准确的说是不同元素的个数)。通常单个元素的长度都是有很多bit的。所以使用bloom filter内存上通常都是节省的。

　　扩展：
　 　Bloom filter将集合中的元素映射到位数组中，用k（k为哈希函数个数）个映射位是否全1表示元素在不在这个集合中。Counting bloom filter（CBF）将位数组中的每一位扩展为一个counter，从而支持了元素的删除操作。Spectral Bloom Filter（SBF）将其与集合元素的出现次数关联。SBF采用counter中的最小值来近似表示元素的出现频率。

        12、给你A,B两个文件，各存放50亿条URL，每条URL占用64字节，内存限制是4G，让你找出A,B文件共同的URL。如果是三个乃至n个文件呢？

　 　根据这个问题我们来计算下内存的占用，4G=2^32大概是40亿*8大概是340亿，n=50亿，如果按出错率0.01算需要的大概是650亿个 bit。现在可用的是340亿，相差并不多，这样可能会使出错率上升些。另外如果这些urlip是一一对应的，就可以转换成ip，则大大简单了。

    同时，上文的第5题：给定a、b两个文件，各存放50亿个url，每个url各占64字节，内存限制是4G，让你找出a、b文件共同的url？如果允许有 一定的错误率，可以使用Bloom filter，4G内存大概可以表示340亿bit。将其中一个文件中的url使用Bloom filter映射为这340亿bit，然后挨个读取另外一个文件的url，检查是否与Bloom filter，如果是，那么该url应该是共同的url（注意会有一定的错误率）。

Bitmap

    至于什么是Bitmap，请看此文：http://blog.csdn.net/v_july_v/article/details/6685962。下面关于Bitmap的应用，直接上题，如下第9、10道：

      13、在2.5亿个整数中找出不重复的整数，注，内存不足以容纳这2.5亿个整数。

    方案1：采用2-Bitmap（每个数分配2bit，00表示不存在，01表示出现一次，10表示多次，11无意义）进行，共需内存2^32 * 2 bit=1 GB内存，还可以接受。然后扫描这2.5亿个整数，查看Bitmap中相对应位，如果是00变01，01变10，10保持不变。所描完事后，查看 bitmap，把对应位是01的整数输出即可。
    方案2：也可采用与第1题类似的方法，进行划分小文件的方法。然后在小文件中找出不重复的整数，并排序。然后再进行归并，注意去除重复的元素。

      14、腾讯面试题：给40亿个不重复的unsigned int的整数，没排过序的，然后再给一个数，如何快速判断这个数是否在那40亿个数当中？
    方案1：frome oo，用位图/Bitmap的方法，申请512M的内存，一个bit位代表一个unsigned int值。读入40亿个数，设置相应的bit位，读入要查询的数，查看相应bit位是否为1，为1表示存在，为0表示不存在。

 

5.4      密匙四、Trie树/数据库/倒排索引
Trie树

　　适用范围：数据量大，重复多，但是数据种类小可以放入内存
　　基本原理及要点：实现方式，节点孩子的表示方式
　　扩展：压缩实现。

　　问题实例：

1. 有10个文件，每个文件1G，每个文件的每一行都存放的是用户的query，每个文件的query都可能重复。要你按照query的频度排序。

2. 1000万字符串，其中有些是相同的(重复),需要把重复的全部去掉，保留没有重复的字符串。请问怎么设计和实现？

3. 寻找热门查询：查询串的重复度比较高，虽然总数是1千万，但如果除去重复后，不超过3百万个，每个不超过255字节。

4. 上面的第8题：一个文本文件，大约有一万行，每行一个词，要求统计出其中最频繁出现的前10个词。其解决方法是：用trie树统计每个词出现的次数，时间复杂度是O(n*le)（le表示单词的平准长度），然后是找出出现最频繁的前10个词。

    更多有关Trie树的介绍，请参见此文：从Trie树（字典树）谈到后缀树。

数据库索引
　　适用范围：大数据量的增删改查
　　基本原理及要点：利用数据的设计实现方法，对海量数据的增删改查进行处理。

    关于数据库索引及其优化，更多可参见此文：http://www.cnblogs.com/pkuoliver/archive/2011/08/17/mass-data-topic-7-index-and-optimize.html。同时，关于MySQL索引背后的数据结构及算法原理，这里还有一篇很好的文章：http://www.codinglabs.org/html/theory-of-mysql-index.html。

倒排索引(Inverted index)
　　适用范围：搜索引擎，关键字查询
　　基本原理及要点：为何叫倒排索引？一种索引方法，被用来存储在全文搜索下某个单词在一个文档或者一组文档中的存储位置的映射。
　以英文为例，下面是要被索引的文本：
    T0 = "it is what it is"
    T1 = "what is it"
    T2 = "it is a banana"
    我们就能得到下面的反向文件索引：
    "a":      {2}
    "banana": {2}
    "is":     {0, 1, 2}
    "it":     {0, 1, 2}
    "what":   {0, 1}
　检索的条件"what","is"和"it"将对应集合的交集。

　 　正向索引开发出来用来存储每个文档的单词的列表。正向索引的查询往往满足每个文档有序频繁的全文查询和每个单词在校验文档中的验证这样的查询。在正向索 引中，文档占据了中心的位置，每个文档指向了一个它所包含的索引项的序列。也就是说文档指向了它包含的那些单词，而反向索引则是单词指向了包含它的文档， 很容易看到这个反向的关系。
　　扩展：
　　问题实例：文档检索系统，查询那些文件包含了某单词，比如常见的学术论文的关键字搜索。

    关于倒排索引的应用，更多请参见：第二十三、四章：杨氏矩阵查找，倒排索引关键词Hash不重复编码实践，及第二十六章：基于给定的文档生成倒排索引的编码与实践。

 

5.5        密匙五、外排序
适用范围：大数据的排序，去重
　　基本原理及要点：外排序的归并方法，置换选择败者树原理，最优归并树
　　扩展：
　　问题实例：
1).有一个1G大小的一个文件，里面每一行是一个词，词的大小不超过16个字节，内存限制大小是1M。返回频数最高的100个词。
　　这个数据具有很明显的特点，词的大小为16个字节，但是内存只有1M做hash明显不够，所以可以用来排序。内存可以当输入缓冲区使用。

    关于多路归并算法及外排序的具体应用场景，请参见此文：第十章、如何给10^7个数据量的磁盘文件排序。

 

5.6        密匙六、分布式处理之Mapreduce
 适用范围：数据量大，但是数据种类小可以放入内存
　　基本原理及要点：将数据交给不同的机器去处理，数据划分，结果归约。
　　扩展：
　　问题实例：

1. The canonical example application of MapReduce is a process to count the appearances of each different word in a set of documents:

2. 海量数据分布在100台电脑中，想个办法高效统计出这批数据的TOP10。

3. 一共有N个机器，每个机器上有N个数。每个机器最多存O(N)个数并对它们操作。如何找到N^2个数的中数(median)？

    更多具体阐述请参见：从Hadhoop框架与MapReduce模式中谈海量数据处理，及MapReduce技术的初步了解与学习。

 

 

的

18.   

19.  的

第6部分           设计题

5.7     日志收集分析系统
1.      日志分布在各个业务系统中，我们需要对当天的日志进行实时汇总统计，同时又能离线查询历史的汇总数据（PV、UV、IP）

解答：

1、通过flume将不同系统的日志收集到kafka中

2、通过storm实时的处理PV、UV、IP

3、通过kafka的consumer将日志生产到hbase中。

4、通过离线的mapreduce或者hive，处理hbase中的数据

 

2.      Hive语言实现word count

解答：

1.建表

2.分组（group by）统计wordcount

select word,count(1) from table1 group by word;

 

3.      实时数据统计会用到哪些技术，他们各自的应用场景及区别是什么?

解答：

flume：日志收集系统，主要用于系统日志的收集

kafka：消息队列，进行消息的缓存和系统的解耦

storm：实时计算框架，进行流式的计算。

 

 

4.      的

5.      的

6.      的

5.8     MapReduce
1.      有两个文本文件，文件中的数据按行存放，请编写MapReduce程序，找到两个文件中彼此不相同的行（写出思路即可）

解答：

写个mapreduce链  用依赖关系，一共三个mapreduce，第一个处理第一个文件，第二个处理第二个文件，第三个处理前两个的输出结果，第一个mapreduce将文件去重，第二个mapreduce也将文件去重，第三个做wordcount，wordcount为1的结果就是不同的

 

2.      共同朋友

1. A B CD E F

2. B A CD E

3. C A B E

4. D B E

5. E A BC D

6. F A

                   第一个字母表示本人，其他事他的朋友，找出有共同朋友的人，和共同的朋友是谁

解答：

思路：例如A，他的朋友是B\C\D\E\F\，那么BC的共同朋友就是A。所以将BC作为key，将A作为value，在map端输出即可！其他的朋友循环处理。

import java.io.IOException;

import java.util.Set;

import java.util.StringTokenizer;

import java.util.TreeSet;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;

10. import org.apache.hadoop.mapreduce.Mapper;

11. import org.apache.hadoop.mapreduce.Reducer;

12. import org.apache.hadoop.mapreduce.Mapper.Context;

13. import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

14. import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

15. import org.apache.hadoop.util.GenericOptionsParser;

 

public class FindFriend {

 

          public static class ChangeMapper extends Mapper<Object, Text, Text,

Text>{                      

                    @Override

                    public void map(Object key, Text value, Context context) throws

IOException, InterruptedException {

                              StringTokenizer itr = new StringTokenizer(value.toString());

                                  Text owner = new Text();

                                  Set<String> set = new TreeSet<String>();

                              owner.set(itr.nextToken());

                              while (itr.hasMoreTokens()) {

                                      set.add(itr.nextToken());

                              }             

                             String[] friends = new String[set.size()];

                             friends = set.toArray(friends);

                              

                             for(int i=0;i<friends.length;i++){

                                      for(int j=i+1;j<friends.length;j++){

                                              String outputkey = friends[i]+friends[j];

                                              context.write(new Text(outputkey),owner);

                                      }                                     

                              }

                    }

           }

           

           public static class FindReducer extends Reducer<Text,Text,Text,Text>

{                          

                         public void reduce(Text key, Iterable<Text> values, 

                                         Context context) throws IOException,

InterruptedException {

                                   String  commonfriends =""; 

                               for (Text val : values) {

                                  if(commonfriends == ""){

                                          commonfriends = val.toString();

                                   }else{

                                          commonfriends =

commonfriends+":"+val.toString();

                                   }

                                }

                              context.write(key, new

Text(commonfriends));                                

                         }                          

           }

          

 

        public static void main(String[] args) throws IOException,

         InterruptedException, ClassNotFoundException {

                 

            Configuration conf = new Configuration();

             String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();

             if (otherArgs.length < 2) {

               System.err.println("args error");

               System.exit(2);

            }

             Job job = new Job(conf, "word count");

            job.setJarByClass(FindFriend.class);

             job.setMapperClass(ChangeMapper.class);

             job.setCombinerClass(FindReducer.class);

             job.setReducerClass(FindReducer.class);

             job.setOutputKeyClass(Text.class);

             job.setOutputValueClass(Text.class);

             for (int i = 0; i < otherArgs.length - 1; ++i) {

               FileInputFormat.addInputPath(job, new Path(otherArgs[i]));

             }

             FileOutputFormat.setOutputPath(job,

               new Path(otherArgs[otherArgs.length - 1]));

             System.exit(job.waitForCompletion(true) ? 0 : 1);

                 

        }

 

}

 

结果：

1. AB      E:C:D

2. AC      E:B

3. AD      B:E

4. AE      C:B:D

5. BC      A:E

6. BD      A:E

7. BE      C:D:A

8. BF      A

9. CD      E:A:B

10. CE      A:B

11. CF      A

12. DE      B:A

13. DF      A

14. EF      A

 

3.      的

4.      的

5.      的

6.      的

5.9     优化
1.      如果要存储海量的小文件（大小都是几百K-几M）请简述自己的设计方案

解答：

1.将小文件打成har文件存储

2.将小文件序列化到hdfs中

 

2.       

3.      的

第7部分           涉及Java基础部分

1.      ArrayList、Vector、LinkedList的区别及其优缺点？HashMap、HashTable的区别及优缺点？

解答：

ArrayList 和Vector是采用数组方式存储数据的,是根据索引来访问元素的，都可以根据需要自动扩展内部数据长度，以便增加和插入元素，都允许直接序号索引元素，但是插入数据要涉及到数组元素移动等内存操作，所以索引数据快插入数据慢，他们最大的区别就是synchronized同步的使用。

LinkedList使用双向链表实现存储，按序号索引数据需要进行向前或向后遍历，但是插入数据时只需要记录本项的前后项即可，所以插入数度较快！

如果只是查找特定位置的元素或只在集合的末端增加、移除元素，那么使用Vector或ArrayList都可以。如果是对其它指定位置的插入、删除操作，最好选择LinkedList

HashMap、HashTable的区别及其优缺点：

HashTable 中的方法是同步的 HashMap的方法在缺省情况下是非同步的 因此在多线程环境下需要做额外的同步机制。

HashTable不允许有null值 key和value都不允许，而HashMap允许有null值 key和value都允许 因此HashMap 使用containKey（）来判断是否存在某个键。

HashTable 使用Enumeration ，而HashMap使用iterator。

Hashtable是Dictionary的子类，HashMap是Map接口的一个实现类。

 

2.      的

3.      的

第8部分           十道海量数据处理面试题

第6部分      

第7部分      

第8部分      

8.1     海量日志数据，提取出某日访问百度次数最多的那个 IP 。
此题，在我之前的一篇文章算法里头有所提到，当时给出的方案是：IP 的数目还是有限的，最多 2^32个，所以可以考虑使用 hash 将 ip 直接存入内存，然后进行统计。再详细介绍下此方案：首先是这一天，并且是访问百度的日志中的 IP 取出来，逐个写入到一个大文件中。注意到 IP 是 32 位的，最多有个 2^32 个 IP。同样可以采用映射的方法，比如模 1000，把整个大文件映射为 1000 个小文件，再找出每个小文中出现频率最大的 IP（可以采用 hash_map 进行频率统计，然后再找出频率最大的几个）及相应的频率。然后再在这 1000 个最大的 IP 中，找出那个频率最大的 IP，即为所求。

 

8.2     2 、搜 索引擎会通过日志文件把用户每次检索使用的所有检索串都记录下来，每个查询串的长度为1-255  字节。假设目前有一千万个记录（这些查询串的重复度比较高，虽然总数是 1 千万，但如果除去重复后，不超过 3 百万个。一个查询串的重复度越高，说明查询它的用户越多，也就是越热门。），请你统计最热门的10 个查询串，要求使用的内存不能超过 1G。
 

 

典型的 Top K 算法，还是在这篇文章里头有所阐述。文中，给出的最终算法是：第一步、先对这批海量数据预处理，在 O（N）的时间内用 Hash 表完成排序；然后，第二步、借助堆这个数据结构，找出 TopK，时间复杂度为 N‗logK。即，借助堆结构，我们可以在 log 量级的时间内查找和调整/移动。因此，维护一个 K(该题目中是 10)大小的小根堆，然后遍历 300 万的 Query，分别和根元素进行对比所以，我们最终的时间复杂度是：O（N） + N'*O（logK），（N 为 1000 万，N‘为 300 万）。ok，更多，详情，请参考原文。或者：采用 trie 树，关键字域存该查询串出现的次数，没有出现为 0。最后用 10 个元素的最小推来对出现频率进行排序。

 

8.3     有一个 1G 过 大小的一个文件，里面每一行是一个词，词的大小不超过 16  字节，内存限制大小是
1M 。返回频数最高的 100  个词。方案：顺序读文件中，对于每个词 x，取 hash(x)%5000，然后按照该值存到 5000 个小文件（记为

www.aboutyun.com

x0,x1,...x4999）中。这样每个文件大概是 200k 左右。如果其中的有的文件超过了 1M 大小，还可以按照类似的方法继续往下分，直到分解得到的小文件的大小都不超过 1M。 对每个小文件，统计每个文件中出现的词以及相应的频率（可以采用 trie 树/hash_map等），并取出出现频率最大的 100 个词（可以用含 100 个结点的最小堆），并把 100 个词及相应的频率存入文件，这样又得到了 5000个文件。下一步就是把这 5000 个文件进行归并（类似与归并排序）的过程了。

 

8.4     4 有 10 个文件 件，每个文件 1G ，每个文件的每一行存放的都是用户的 query ，每个文件的 query照 都可能重复。
 

要求你按照 query  的频度排序。还是典型的 TOP K 算法，解决方案如下：

 

方案 1：顺序读取 10 个文件，按照 hash(query)%10 的结果将 query 写入到另外 10 个文件（记为）中。这样新生成的文件每个的大小大约也 1G（假设 hash 函数是随机的）。找一台内存在 2G 左右的机器，依次对用 hash_map(query, query_count)来统计每个 query 出现的次数。利用快速/堆/归并排序按照出现次数进行排序。将排序好的query 和对应的 query_cout 输出到文件中。这样得到了 10 个排好序的文件（记为）。对这 10 个文件进行归并排序（内排序与外排序相结合）。

 

 

方案 2：一般 query 的总量是有限的，只是重复的次数比较多而已，可能对于所有的 query，一次性就可以加入到内存了。这样，我们就可以采用trie 树/hash_map 等直接来统计每个 query 出现的次数，然后按出现次数做快速/堆/归并排序就可以了。

 

方案 3：与方案 1 类似，但在做完 hash，分成多个文件后，可以交给多个文件来处理，采用分布式的架构来处理（比如 MapReduce），最后再进行合并。

 

8.5     5、  给定 a、、b 两个文件，各存放50  亿个 url ，每个 url  各占 64  字节，内存限制是 4G ，让你找出 a 、b 文件共同的 url ？
 

 

方案 1：可以估计每个文件安的大小为 5G×64=320G，远远大于内存限制的 4G。所以不可能将其完全

加载到内存中处理。考虑采取分而治之的方法。

遍历文件 a，对每个 url 求取 hash(url)%1000，然后根据所取得的值将 url 分别存储到 1000 个小文件

（记为 a0,a1,...,a999）中。这样每个小文件的大约为 300M。

遍历文件 b，采取和 a 相同的方式将 url 分别存储到 1000 小文件（记为 b0,b1,...,b999）。这样处理后，

所有可能相同的 url 都在对应的小文件（a0vsb0,a1vsb1,...,a999vsb999）中，不对应的小文件不可能有相

www.aboutyun.com

同的 url。然后我们只要求出 1000 对小文件中相同的 url 即可。

求每对小文件中相同的 url 时，可以把其中一个小文件的 url 存储到 hash_set 中。然后遍历另一个小

文件的每个 url，看其是否在刚才构建的 hash_set 中，如果是，那么就是共同的url，存到文件里面就可以

了。

方案 2：如果允许有一定的错误率，可以使用 Bloom filter，4G 内存大概可以表示 340 亿 bit。将其中

一个文件中的url使用Bloom filter映射为这340亿bit，然后挨个读取另外一个文件的url，检查是否与Bloom

filter，如果是，那么该 url 应该是共同的 url（注意会有一定的错误率）。

Bloom filter 日后会在本 BLOG 内详细阐述。

8.6     在 2.5  亿个整数中找出不重复的整数，注，内存不足以容纳这 2.5  亿个整数。
 

方案 1：采用 2-Bitmap（每个数分配 2bit，00 表示不存在，01 表示出现一次，10 表示多次，11 无意

义）进行，共需内存内存，还可以接受。然后扫描这2.5 亿个整数，查看 Bitmap 中相对应位，如果是 00

变 01，01 变 10，10 保持不变。所描完事后，查看 bitmap，把对应位是 01 的整数输出即可。

方案 2：也可采用与第 1 题类似的方法，进行划分小文件的方法。然后在小文件中找出不重复的整数，

并排序。然后再进行归并，注意去除重复的元素。

8.7     腾讯面试题：给 40  亿个不重复的 unsigned int  的整数，没排过序的，然后再给一个数，如何快那速判断这个数是否在那 40  亿个数当中？
与上第 6 题类似，我的第一反应时快速排序+二分查找。以下是其它更好的方法： 方案 1：oo，申请

512M 的内存，一个 bit 位代表一个 unsigned int 值。读入 40 亿个数，设置相应的 bit 位，读入要查询的数，

查看相应 bit 位是否为 1，为 1 表示存在，为 0 表示不存在。

dizengrong： 方案 2：这个问题在《编程珠玑》里有很好的描述，大家可以参考下面的思路，探讨一

下：又因为 2^32 为 40 亿多，所以给定一个数可能在，也可能不在其中；这里我们把 40 亿个数中的每一

个用 32 位的二进制来表示假设这 40 亿个数开始放在一个文件中。

然后将这 40 亿个数分成两类: 1.最高位为 0 2.最高位为 1 并将这两类分别写入到两个文件中，其中一

个文件中数的个数<=20 亿，而另一个>=20 亿（这相当于折半了）；与要查找的数的最高位比较并接着进

入相应的文件再查找

再然后把这个文件为又分成两类: 1.次最高位为 0 2.次最高位为 1

并将这两类分别写入到两个文件中，其中一个文件中数的个数<=10 亿，而另一个>=10 亿（这相当于

折半了）； 与要查找的数的次最高位比较并接着进入相应的文件再查找。 ....... 以此类推，就可以找到了,

而且时间复杂度为 O(logn)，方案 2 完。

附：这里，再简单介绍下，位图方法： 使用位图法判断整形数组是否存在重复判断集合中存在重复

是常见编程任务之一，当集合中数据量比较大时我们通常希望少进行几次扫描，这时双重循环法就不可取

了。

位图法比较适合于这种情况，它的做法是按照集合中最大元素max创建一个长度为max+1的新数组，

然后再次扫描原数组，遇到几就给新数组的第几位置上1，如遇到 5 就给新数组的第六个元素置 1，这样下

次再遇到 5 想置位时发现新数组的第六个元素已经是 1 了，这说明这次的数据肯定和以前的数据存在着重

复。这种给新数组初始化时置零其后置一的做法类似于位图的处理方法故称位图法。它的运算次数最坏的

情况为 2N。如果已知数组的最大值即能事先给新数组定长的话效率还能提高一倍。

8.8     8 、怎么在海量数据中找出重复次数最多的一个？
方案 1：先做 hash，然后求模映射为小文件，求出每个小文件中重复次数最多的一个，并记录重复次数。

然后找出上一步求出的数据中重复次数最多的一个就是所求（具体参考前面的题）。

8.9     9 、上千万或上亿数据（有重复），统计其中出现次数最多的钱 N  个数据。
方案 1：上千万或上亿的数据，现在的机器的内存应该能存下。所以考虑采用 hash_map/搜索二叉树/红黑树等来进行统计次数。然后就是取出前 N 个出现次数最多的数据了，可以用第 2 题提到的堆机制完成。

8.1010 、一个文本文件，大约有一万行，每行一个词，要求统计出其中最频繁出现的前10  个词，请给出
思想，给出时间复杂度分析。

方案 1：

这题是考虑时间效率。用 trie 树统计每个词出现的次数，时间复杂度是O(n*le)（le 表示单词的平准长度）。然后是找出出现最频繁的前 10 个词，可以用堆来实现，前面的题中已经讲到了，时间复杂度是 O(n*lg10)。所以总的时间复杂度，是O(n*le)与 O(n*lg10)中较大的哪一个。附、100w 个数中找出最大的 100 个数。

 

方案1：

在前面的题中，我们已经提到了，用一个含100个元素的最小堆完成。复杂度为O(100w*lg100)。

 

方案 2：

采用快速排序的思想，每次分割之后只考虑比轴大的一部分，知道比轴大的一部分在比 100多的时候，采用传统排序算法排序，取前 100 个。复杂度为 O(100w*100)。

 

方案 3：

采用局部淘汰法。选取前 100 个元素，并排序，记为序列 L。然后一次扫描剩余的元素 x，与排好序的 100 个元素中最小的元素比，如果比这个最小的要大，那么把这个最小的元素删除，并把 x 利用插入排序的思想，插入到序列 L 中。依次循环，知道扫描了所有的元素。复杂度为 O(100w*100)。

第9部分           遗留

311、在线安装ssh的命令以及文件解压的命令？

312、把公钥都追加到授权文件的命令？该命令是否在root用户下执行？

313、HadoopHA集群中，各个服务的启动和关闭的顺序？

314、HDFS中的block块默认保存几份？默认大小多少？

315、NameNode中的meta数据是存放在NameNode自身，还是DataNode等其他节点？DatNOde节点自身是否有Meta数据存在？

316、下列那个程序通常与NameNode在一个节点启动？

317、下面那个程序负责HDFS数据存储？

318、 在HadoopHA集群中，简述Zookeeper的主要作用，以及启动和查看状态的命令？

319、HBase在进行模型设计时重点在什么地方？一张表中国定义多少个Column　Family最合适？为什么？

320、如何提高HBase客户端的读写性能？请举例说明。

321、基于HadoopHA集群进行MapReduce开发时，Configuration如何设置hbase.zookeeper,quorum属性的值？

322、 在hadoop开发过程中使用过哪些算法？其应用场景是什么？

323、MapReduce程序如何发布？如果MapReduce中涉及到了第三方的jar包，该如何处理？

324、在实际工作中使用过哪些集群的运维工具，请分别阐述其作用。

325、hadoop中combiner的作用?

326、IO的原理，IO模型有几种?

327、Windows用什么样的模型，Linux用什么样的模型？

328、一台机器如何应对那么多的请求访问，高并发到底怎么实现，一个请求怎么产生的，

在服务端怎么处理的，最后怎么返回给用户的，整个的环节操作系统是怎么控制的？

329、hdfs的client端，复制到第三个副本时宕机，hdfs怎么恢复保证下次写第三副本?block块信息是先写dataNode还是先写nameNode?

330、快排现场写程序实现？

331、jvm的内存是怎么分配原理？

332、毒酒问题---1000桶酒，其中1桶有毒。而一旦吃了，毒性会在1周后发作。问最少需要多少只老鼠可在一周内找出毒酒？

333、用栈实现队列？

334、链表倒序实现？

335、多线程模型怎样（生产，消费者）？平时并发多线程都用哪些实现方式？

336、synchonized是同步悲观锁吗？互斥？怎么写同步提高效率？

337、4亿个数字，找出哪些重复的，要用最小的比较次数，写程序实现。

338、java是传值还是传址？

339、 java处理多线程，另一线程一直等待？

340、一个网络商城1天大概产生多少G的日志？

341、大概有多少条日志记录（在不清洗的情况下）？

342、日访问量大概有多少个？

343、注册数大概多少？

344、我们的日志是不是除了apache的访问日志是不是还有其他的日志？

345、假设我们有其他的日志是不是可以对这个日志有其他的业务分析？这些业务分析都有什么？

346、问：你们的服务器有多少台？

347、问：你们服务器的内存多大？

348、问：你们的服务器怎么分布的？（这里说地理位置分布，最好也从机架方面也谈谈）

349、问：你平常在公司都干些什么（一些建议）

350、hbase怎么预分区？

351、hbase怎么给web前台提供接口来访问（HTABLE可以提供对HTABLE的访问，但是怎么查询同一条记录的多个版本数据）？

352、.htable API有没有线程安全问题，在程序中是单例还是多例？

353、我们的hbase大概在公司业务中（主要是网上商城）大概都几个表，几个表簇，大概都存什么样的数据？

354、hbase的并发问题？

Storm问题：

355、metaq消息队列 zookeeper集群 storm集群（包括zeromq,jzmq,和storm本身）就可以完成对商城推荐系统功能吗？还有没有其他的中间件？

356、storm怎么完成对单词的计数？（个人看完storm一直都认为他是流处理，好像没有积攒数据的能力，都是处理完之后直接分发给下一个组件）

357、storm其他的一些面试经常问的问题？

二十三、面试题（18道）：

358、你们的集群规模？

开发集群：10台（8台可用）8核cpu

359、你们的数据是用什么导入到数据库的？导入到什么数据库？

处理之前的导入：通过hadoop命令导入到hdfs文件系统

处理完成之后的导出：利用hive处理完成之后的数据，通过sqoop导出到mysql数据库中，以供报表层使用。

360、你们业务数据量多大？有多少行数据？(面试了三家，都问这个问题)

开发时使用的是部分数据，不是全量数据，有将近一亿行（8、9千万，具体不详，一般开发中也没人会特别关心这个问题）

361、你们处理数据是直接读数据库的数据还是读文本数据？

将日志数据导入到hdfs之后进行处理

362、你们写hive的hql语句，大概有多少条？

不清楚，我自己写的时候也没有做过统计

363、你们提交的job任务大概有多少个？这些job执行完大概用多少时间？(面试了三家，都问这个问题)

没统计过，加上测试的，会与很多

364、hive跟hbase的区别是？

365、你在项目中主要的工作任务是？

利用hive分析数据

366、你在项目中遇到了哪些难题，是怎么解决的？

某些任务执行时间过长，且失败率过高，检查日志后发现没有执行完就失败，原因出在hadoop的job的timeout过短（相对于集群的能力来说），设置长一点即可

367、你自己写过udf函数么？写了哪些？

这个我没有写过

368、你的项目提交到job的时候数据量有多大？(面试了三家，都问这个问题)

不清楚是要问什么

369、reduce后输出的数据量有多大？

370、一个网络商城1天大概产生多少G的日志？ 4tb

371、大概有多少条日志记录（在不清洗的情况下）？ 7-8百万条

372、日访问量大概有多少个？百万

373、注册数大概多少？不清楚几十万吧

374、我们的日志是不是除了apache的访问日志是不是还有其他的日志？关注信息

375、假设我们有其他的日志是不是可以对这个日志有其他的业务分析？这些业务分析都有什么？

二十四、面试题(1道)：

376、有一千万条短信，有重复，以文本文件的形式保存，一行一条，有重复。

请用5分钟时间，找出重复出现最多的前10条。

分析：

常规方法是先排序，在遍历一次，找出重复最多的前10条。但是排序的算法复杂度最低为nlgn。

可以设计一个hash_table,hash_map<string, int>，依次读取一千万条短信，加载到hash_table表中，并且统计重复的次数，与此同时维护一张最多10条的短信表。

这样遍历一次就能找出最多的前10条，算法复杂度为O(n)。

二十五、面试题（5道）：

377、job的运行流程(提交一个job的流程)？

378、Hadoop生态圈中各种框架的运用场景？

379、hive中的压缩格式RCFile、TextFile、SequenceFile各有什么区别？

以上3种格式一样大的文件哪个占用空间大小.还有Hadoop中的一个HA压缩。

380、假如：Flume收集到的数据很多个小文件,我需要写MR处理时将这些文件合并

(是在MR中进行优化,不让一个小文件一个MapReduce)

他们公司主要做的是中国电信的流量计费为主,专门写MR。

383、解释“hadoop”和“hadoop生态系统”两个概念。

384、说明Hadoop 2.0的基本构成。

385、相比于HDFS1.0, HDFS 2.0最主要的改进在哪几方面？

386、试使用“步骤1，步骤2，步骤3…..”说明YARN中运行应用程序的基本流程。

387、“MapReduce 2.0”与“YARN”是否等同，尝试解释说明。

388、MapReduce 2.0中，MRAppMaster主要作用是什么，MRAppMaster如何实现任务容错的？

389、为什么会产生yarn,它解决了什么问题，有什么优势？

397、Hadoop体系结构（HDFS与MapReduce的体系结构）、Hadoop相比传统数据存储方式（比如mysql）的优势？

398、Hadoop集群的搭建步骤、Hadoop集群搭建过程中碰到了哪些常见问题（比如datanode没有起来）、Hadoop集群管理（如何动态增加和卸载节点、safe mode是什么、常用的命令kill等）？

399、HDFS的namenode与secondarynamenode的工作原理（重点是日志拉取和合并过程）、hadoop 1.x的HDFS的HA方案（namenode挂掉的情况如何处理、datanode挂掉的情况如何处理）？

400、HDFS的常用shell命令有哪些？分别对应哪些Client Java API？：显示文件列表、创建目录、文件上传与下载、文件内容查看、删除文件

401、HDFS的文件上传与下载底层工作原理（或HDFS部分源码分析）：FileSystem的create()和open()方法源码分析？

402、MapReduce计算模型、MapReduce基础知识点（MapReduce新旧API的使用、在linux命令行运行MapReduce程序、自定义Hadoop数据类型）？

403、MapReduce执行流程：“天龙八步”，计数器、自定义分区、自定义排序、自定义分组、如何对value进行排序：次排序+自定义分组、归约？

404、MapReduce的shuffle工作原理、MapReduce工作原理（MapReduce源码、InputStream源码、waitForCompletion()源码）、jobtracker如何创建map任务和reduce任务是面试的重点。

405、MapReduce进阶知识：Hadoop的几种文件格式、常见输入输出格式化类、多输入多输出机制、MapReduce的常见算法（各种join原理和优缺点、次排序和总排序）？

406、MapReduce性能优化（shuffle调优、压缩算法、更换调度器、设置InputSplit大小减少map任务数量、map和reduce的slot如何设置、数据倾斜原理和如何解决）？

407、HBase的体系结构和搭建步骤、shell命令与Java API、HBase作为MapReduce的输入输出源、高级JavaAPI、工作原理（重点是combine和split原理）、行键设计原则、性能优化？

408、Hive的工作原理、两种元数据存放方式、几种表之间的区别、数据导入的几种方式、几种文件格式、UDF函数、性能调优（重点是join的时候如何放置大小表）？

409、Zookeeper、Flume、Pig、Sqoop的基本概念和使用方式，ZooKeeper被问到过其如何维护高可用（如果某个节点挂掉了它的处理机制）？

410、Hadoop2：体系结构、HDFS HA、YARN？

411、关系型数据库和非关系型数据库的区别？

提示：

关系型数据库通过外键关联来建立表与表之间的关系，非关系型数据库通常指数据以对象的形式存储在数据库中，而对象之间的关系通过每个对象自身的属性来决定。

对数据库高并发读写、高可扩展性和高可用性的需求，对海量数据的高效率存储和访问的需求，存储的结构不一样，非关系数据库是列式存储，在存储结构上更加自由。

412、hive的两张表关联，使用mapreduce是怎么写的？

提示：打标记笛卡尔乘积

413、hive相对于Oracle来说有那些优点？

提示：

hive是数据仓库，oracle是数据库，hive能够存储海量数据，hive还有更重要的作用就是数据分析，最主要的是免费。

414、现在我们要对Oracle和HBase中的某些表进行更新，你是怎么操作？

提示：

disable '表名'

alter '表明', NAME => '列名', VERSIONS=>3

enable '表名'

415、HBase接收数据，如果短时间导入数量过多的话就会被锁，该怎么办？ 集群数16台 ，高可用性的环境。

参考：

通过调用HTable.setAutoFlush(false)方法可以将HTable写客户端的自动flush关闭，这样可以批量写入数据到HBase，而不是有一条put就执行一次更新，只有当put填满客户端写缓存时，才实际向HBase服务端发起写请求。默认情况下auto flush是开启的。

416、说说你们做的hadoop项目流程？

417、你们公司的服务器架构是怎么样的（分别说下web跟hadoop）？

418、假如有1000W用户同时访问同一个页面，怎么处理？

提示：优化代码、静态化页面、增加缓存机制、数据库集群、库表散列。。。

419、怎样将mysql的数据导入到hbase中？不能使用sqoop，速度太慢了

提示：

A、一种可以加快批量写入速度的方法是通过预先创建一些空的regions，这样当数据写入HBase时，会按照region分区情况，在集群内做数据的负载均衡。

B、hbase里面有这样一个hfileoutputformat类，他的实现可以将数据转换成hfile格式，通过new 一个这个类，进行相关配置,这样会在hdfs下面产生一个文件，这个时候利用hbase提供的jruby的loadtable.rb脚本就可以进行批量导入。

420、在hadoop组中你主要负责那部分？

提示：负责编写mapreduce程序，各个部分都要参加

提示：负责编写mapreduce程序，各个部分都要参加

421、怎么知道hbase表里哪些做索引？哪些没做索引？

提示：

有且仅有一个：rowkey，所以hbase的快速查找建立在rowkey的基础的，而不能像一般的关系型数据库那样建立多个索引来达到多条件查找的效果。

422、hdfs的原理以及各个模块的职责

423、mapreduce的工作原理

424、map方法是如何调用reduce方法的

425、fsimage和edit的区别？

提示：fsimage：是存储元数据的镜像文件，而edit只是保存的操作日志。

426、hadoop1和hadoop2的区别？

提示：

（1） hdfs的namenode和mapreduce的jobtracker都是单点。

（2） namenode所在的服务器的内存不够用时，那么集群就不能工作了。

（3）mapreduce集群的资源利用率比较低。

单NN的架构使得HDFS在集群扩展性和性能上都有潜在的问题，在集群规模变大后，NN成为了性能的瓶颈。Hadoop 2.0里的HDFS Federation就是为了解决这两个问题而开发的。扩大NN容量，共享DN数据，且方便客户端访问。

427、hdfs中的block默认报错几份？

提示：3份

428、哪个程序通常与nn在一个节点启动？并做分析

提示：jobtrack，将两者放在一起，减少网络访问，IO访问的时间，提高了效率。

429、列举几个配置文件优化？

430、写出你对zookeeper的理解

提示：大部分分布式应用需要一个主控、协调器或控制器来管理物理分布的子进程（如资源、任务分配等）。目前，大部分应用需要开发私有的协调程序，缺乏一个通用的机制协调程序的反复编写浪费，且难以形成通用、伸缩性好的协调器。

ZooKeeper：提供通用的分布式锁服务，用以协调分布式应用。

431、datanode首次加入cluster的时候，如果log报告不兼容文件版本，那需要namenode执行格式化操作，这样处理的原因是？

提示：

这样处理是不合理的，因为那么namenode格式化操作，是对文件系统进行格式化，namenode格式化时清空dfs/name下空两个目录下的所有文件，之后，会在目录dfs.name.dir下创建文件。

文本不兼容，有可能时namenode 与 datanode 的 数据里的namespaceID、clusterID不一致，找到两个ID位置，修改为一样即可解决。

432、谈谈数据倾斜，如何发生的，并给出优化方案。

原因：

（1）key分布不均匀

（2）业务数据本身的特性

（3）建表时考虑不周

（4）某些SQL语句本身就有数据倾斜

map处理数据量的差异取决于上一个stage的reduce输出，所以如何将数据均匀的分配到各个reduce中，就是解决数据倾斜的根本所在。

优化：参数调节；

433、介绍一下HBase过滤器

434、mapreduce基本执行过程

435、谈谈hadoop1和hadoop2的区别

436、谈谈HBase集群安装注意事项？

