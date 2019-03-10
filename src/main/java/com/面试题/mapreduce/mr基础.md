Hadoop相关
3-1）简单概述hdfs原理，以及各个模块的职责
 

 

 

 

1、客户端向 nameNode 发送要上传文件的请求

2、nameNode 返回给用户是否能上传数据的状态

3、加入用户端需要上传一个 1024M 的文件，客户端会通过 Rpc 请求 NameNode，并返回需要上传给那些 DataNode(分配机器的距离以及空间的大小等),namonode会选择就近原则分配机器。

4、客户端请求建立 block 传输管道 chnnel 上传数据

5、在上传是 datanode 会与其他的机器建立连接并把数据块传送到其他的机器上

6、dataNode 向 namenode 汇报自己的储存情况以及自己的信息

7、档第一个快上传完后再去执行其他的复制的传送

 

 

 

3-2）mr的工作原理
 

 

 

 

1、当执行mr程序是，会执行一个Job

2、客户端的jobClick会请求namenode的jobTracker要执行任务

3、jobClick会去HDFS端复制作业的资源文件

4、客户端的jobClick会向namenode提交作业,让namenode做准备

5、Namenode的jobTracker会去初始化创建的对象

6、Namenode会获取hdfs的划分的分区

7、Namenode去检查TaskTracker的心跳信息，查看存活的机器

8、当执行的datenode执行任务时Datenode会去HDFS获取作业的资源的文件

9、TaskTracker会去执行代码，并登陆JVM的执行渠道

10、JVM或执行MapTask或者ReduceTask

11、执行终结

 

 

3-3）怎样判断文件时候存在
这是Linux上的知识，只需要在IF[ -f ] 括号中加上-f参数即可判断文件是否存在

 

 

3-4）fsimage和edit的区别？
 

大家都知道namenode与secondary namenode 的关系，当他们要进行数据同步时叫做checkpoint时就用到了fsimage与edit，fsimage是保存最新的元数据的信息，当fsimage数据到一定的大小事会去生成一个新的文件来保存元数据的信息，这个新的文件就是edit，edit会回滚最新的数据。

 

3-5）hdfs中的block默认保存几份？
不管是hadoop1.x 还是hadoop2.x 都是默认的保存三份，可以通过参数dfs.replication就行修改，副本的数目要根据机器的个数来确定。

 

3-6）列举几个配置文件优化？
Core-site.xml 文件的优化

 

fs.trash.interval

默认值： 0

说明： 这个是开启hdfs文件删除自动转移到垃圾箱的选项，值为垃圾箱文件清除时间。一般开启这个会比较好，以防错误删除重要文件。单位是分钟。

 

dfs.namenode.handler.count

默认值：10

说明：Hadoop系统里启动的任务线程数，这里改为40，同样可以尝试该值大小对效率的影响变化进行最合适的值的设定。

 

mapreduce.tasktracker.http.threads

默认值：40

说明：map和reduce是通过http进行数据传输的，这个是设置传输的并行线程数。

 

3-7) 谈谈数据倾斜，如何发生的，并给出优化方案
     数据的倾斜主要是两个的数据相差的数量不在一个级别上，在只想任务时就造成了数据的倾斜，可以通过分区的方法减少reduce数据倾斜性能的方法，例如;抽样和范围的分区、自定义分区、数据大小倾斜的自定义侧咯

 

3-8）简单概括安装hadoop的步骤
1.创建 hadoop 帐户。

2.setup.改 IP。

3.安装 Java，并修改/etc/profile 文件，配置 java 的环境变量。

4.修改 Host 文件域名。

5.安装 SSH，配置无密钥通信。

6.解压 hadoop。

7.配置 conf 文件下 hadoop-env.sh、core-site.sh、mapre-site.sh、hdfs-site.sh。

8.配置 hadoop 的环境变量。

9.Hadoop namenode -format

10.Start-all.sh

 

3-9）简单概述hadoop中的角色的分配以及功能
Namenode:负责管理元数据的信息

SecondName:做namenode冷备份，对于namenode的机器当掉后能快速切换到制定的Secondname上

DateNode:主要做储存数据的。

JobTracker:管理任务，并把任务分配到taskTasker

TaskTracker：执行任务的

 

3-10）怎样快速的杀死一个job
1、执行hadoop  job -list  拿到job-id

2、Hadoop job kill hadoop-id

 

3-11)新增一个节点时怎样快速的启动
Hadoop-daemon.sh start datanode

 

3-12)你认为用java , streaming , pipe 方式开发map/reduce,各有什么优点
开发mapReduce只用过java与Hive,不过使用java开发mapreduce显得笨拙，效率也慢，基于java慢的原因于是hive，这样就方便了查询与设计

 

3-13）简单概述hadoop的join的方法
Hadoop 常用的jion有reduce side join  , map side  join ,  SemiJoin 不过reduce side join 与 map side join 比较常用，不过都是比较耗时的。

 

3-14）简单概述hadoop的combinet与partition的区别
combine和partition都是函数，中间的步骤应该只有shuffle！ combine分为map端和reduce端，作用是把同一个key的键值对合并在一起，可以自定义的,partition是分割map每个节点的结果，按照key分别映射给不同的reduce，也是可以自定义的。这里其实可以理解归类。

3-15 ) hdfs 的数据压缩算法
 Hadoop 的压缩算法有很多，其中比较常用的就是gzip算法与bzip2算法，都可以可通过CompressionCodec来实现

 

3-16）hadoop的调度
Hadoop 的调度有三种其中fifo的调度hadoop的默认的，这种方式是按照作业的优先级的高低与到达时间的先后执行的，还有公平调度器：名字见起意就是分配用户的公平获取共享集群呗!容量调度器:让程序都能货到执行的能力，在队列中获得资源。

 

3-17）reduce 后输出的数据量有多大？
输出的数据量还不是取决于map端给他的数据量，没有数据reduce也没法运算啊!!

 

3-18) datanode 在什么情况下不会备份？
Hadoop保存的三个副本如果不算备份的话，那就是在正常运行的情况下不会备份，也是就是在设置副本为1的时候不会备份，说白了就是单台机器呗！！还有datanode 在强制关闭或者非正常断电不会备份。

3-19）combine 出现在那个过程？
Hadoop的map过程，根据意思就知道结合的意思吗，剩下的你们就懂了。想想wordcound

 

3-20) hdfs 的体系结构？
HDFS有 namenode、secondraynamenode、datanode 组成。

namenode 负责管理 datanode 和记录元数据

secondraynamenode 负责合并日志

datanode 负责存储数据

3-21) hadoop flush 的过程？
Flush 就是把数据落到磁盘，把数据保存起来呗!

 

3-22) 什么是队列
队列的实现是链表，消费的顺序是先进先出。

 

3-23）三个 datanode，当有一个 datanode 出现错误会怎样？
第一不会给储存带来影响，因为有其他的副本保存着，不过建议尽快修复，第二会影响运算的效率，机器少了，reduce在保存数据时选择就少了，一个数据的块就大了所以就会慢。

 

3-24）mapReduce 的执行过程
首先map端会Text 接受到来自的数据，text可以把数据进行操作，最后通过context把key与value写入到下一步进行计算，一般的reduce接受的value是个集合可以运算，最后再通过context把数据持久化出来。

 

3-25）Cloudera 提供哪几种安装 CDH 的方法
· Cloudera manager

· Tarball

· Yum

· Rpm

 

3-26）选择题与判断题
http://blog.csdn.NET/jiangheng0535/article/details/16800415

 

3-27）hadoop的combinet与partition效果图
 

 

3-28）hadoop 的机架感知（或者说是扩普）
看图说话

数据块会优先储存在离namenode进的机器或者说成离namenode机架近的机器上，正好是验证了那句话不走网络就不走网络，不用磁盘就不用磁盘。

 

3-29）文件大小默认为 64M，改为 128M 有啥影响？
这样减少了namenode的处理能力，数据的元数据保存在namenode上，如果在网络不好的情况下会增到datanode的储存速度。可以根据自己的网络来设置大小。

 

3-30）datanode 首次加入 cluster 的时候，如果 log 报告不兼容文件版本，那需要namenode 执行格式化操作，这样处理的原因是？
     这样处理是不合理的，因为那么 namenode 格式化操作，是对文件系统进行格式

化，namenode 格式化时清空 dfs/name 下空两个目录下的所有文件，之后，会在目

录 dfs.name.dir 下创建文件。

     文本不兼容，有可能时 namenode 与 datanode 的 数据里的 namespaceID、

clusterID 不一致，找到两个 ID 位置，修改为一样即可解决。

3-31）什么 hadoop streaming？
提示：指的是用其它语言处理

 

3-32）MapReduce 中排序发生在哪几个阶段？这些排序是否可以避免？为什么？
     一个 MapReduce 作业由 Map 阶段和 Reduce 阶段两部分组成，这两阶段会对数

据排序，从这个意义上说，MapReduce 框架本质就是一个 Distributed Sort。在 Map

阶段，在 Map 阶段，Map Task 会在本地磁盘输出一个按照 key 排序（采用的是快速

排序）的文件（中间可能产生多个文件，但最终会合并成一个），在 Reduce 阶段，每

个 Reduce Task 会对收到的数据排序，这样，数据便按照 Key 分成了若干组，之后以

组为单位交给 reduce（）处理。很多人的误解在 Map 阶段，如果不使用 Combiner

便不会排序，这是错误的，不管你用不用 Combiner，Map Task 均会对产生的数据排

序（如果没有 Reduce Task，则不会排序，实际上 Map 阶段的排序就是为了减轻 Reduce

端排序负载）。由于这些排序是 MapReduce 自动完成的，用户无法控制，因此，在

hadoop 1.x 中无法避免，也不可以关闭，但 hadoop2.x 是可以关闭的。

 

3-33）hadoop的shuffer的概念
Shuffer是一个过程，实在map端到reduce在调reduce数据之前都叫shuffer,主要是分区与排序，也就是内部的缓存分分区以及分发（是reduce来拉数据的）和传输

 

3-34）hadoop的优化
1、优化的思路可以从配置文件和系统以及代码的设计思路来优化

2、配置文件的优化：调节适当的参数，在调参数时要进行测试

3、代码的优化：combiner的个数尽量与reduce的个数相同，数据的类型保持一致，可以减少拆包与封包的进度

4、系统的优化：可以设置linux系统打开最大的文件数预计网络的带宽MTU的配置

5、为 job 添加一个 Combiner，可以大大的减少shuffer阶段的maoTask拷贝过来给远程的   reduce task的数据量，一般而言combiner与reduce相同。

6、在开发中尽量使用stringBuffer而不是string，string的模式是read-only的，如果对它进行修改，会产生临时的对象，二stringBuffer是可修改的，不会产生临时对象。

7、修改一下配置：

一下是修改 mapred-site.xml 文件

修改最大槽位数

槽位数是在各个 tasktracker 上的 mapred-site.xml 上设置的，默认都是 2

<property>

<name>mapred.tasktracker.map.tasks.maximum</name>

task 的最大数

<value>2</value>

</property>

<property>

<name>mapred.tasktracker.reduce.tasks.maximum</name>

ducetask 的最大数

<value>2</value>

</property>

调整心跳间隔

集群规模小于 300 时，心跳间隔为 300 毫秒

mapreduce.jobtracker.heartbeat.interval.min 心跳时间

北京市昌平区建材城西路金燕龙办公楼一层 电话：400-618-9090

mapred.heartbeats.in.second 集群每增加多少节点，时间增加下面的值

mapreduce.jobtracker.heartbeat.scaling.factor 集群每增加上面的个数，心跳增多少

启动带外心跳

mapreduce.tasktracker.outofband.heartbeat 默认是 false

配置多块磁盘

mapreduce.local.dir

配置 RPC hander 数目

mapred.job.tracker.handler.count 默认是 10，可以改成 50，根据机器的能力

配置 HTTP 线程数目

tasktracker.http.threads 默认是 40，可以改成 100 根据机器的能力

选择合适的压缩方式

以 snappy 为例：

<property>

<name>mapred.compress.map.output</name>

<value>true</value>

</property>

<property>

<name>mapred.map.output.compression.codec</name>

<value>org.apache.hadoop.io.compress.SnappyCodec</value>

</property>

 

3-35)3 个 datanode 中有一个 个datanode 出现错误会怎样？
这个 datanode 的数据会在其他的 datanode 上重新做备份。

 

3-36）怎样决定mapreduce的中的map以及reduce的数量
在mapreduce中map是有块的大小来决定的，reduce的数量可以按照用户的业务来配置。

 

3-37）两个文件合并的问题
给定a、b两个文件，各存放50亿个url，每个url各占用64字节，内存限制是4G，如何找出a、b文件共同的url？

 

    主要的思想是把文件分开进行计算，在对每个文件进行对比，得出相同的URL,因为以上说是含有相同的URL所以不用考虑数据倾斜的问题。详细的解题思路为：

 

可以估计每个文件的大小为5G*64=300G，远大于4G。所以不可能将其完全加载到内存中处理。考虑采取分而治之的方法。 
    遍历文件a，对每个url求取hash(url)%1000，然后根据所得值将url分别存储到1000个小文件（设为a0,a1,...a999）当中。这样每个小文件的大小约为300M。遍历文件b，采取和a相同的方法将url分别存储到1000个小文件(b0,b1....b999)中。这样处理后，所有可能相同的url都在对应的小文件(a0 vs b0, a1 vs b1....a999 vs b999)当中，不对应的小文件（比如a0 vs b99）不可能有相同的url。然后我们只要求出1000对小文件中相同的url即可。 
    比如对于a0 vs b0，我们可以遍历a0，将其中的url存储到hash_map当中。然后遍历b0，如果url在hash_map中，则说明此url在a和b中同时存在，保存到文件中即可。 
    如果分成的小文件不均匀，导致有些小文件太大（比如大于2G），可以考虑将这些太大的小文件再按类似的方法分成小小文件即可

 

3-38）怎样决定一个job的map和reduce的数量
map的数量通常是由hadoop集群的DFS块大小确定的，也就是输入文件的总块数，reduce端是复制map端的数据，相对于map端的任务，reduce节点资源是相对于比较缺少的，同时运行的速度会变慢，争取的任务的个数应该是0.95过着1.75。

 

3-39）hadoop的sequencefile的格式，并说明下什么是JAVA的序列化，如何实现JAVA的序列化
1、hadoop的序列化(sequencefile)是一二进制的形式来保存的

2、Java的序列化是讲对象的内容进行流化

3、实现序列化需要实现Serializable接口便可以了

 

3-40）简单概述一下hadoop1与hadoop2的区别
Hadoop2与hadoop1最大的区别在于HDFS的架构与mapreduce的很大的区别，而且速度上有很大的提升，hadoop2最主要的两个变化是：namenode可以集群的部署了，hadoop2中的mapreduce中的jobTracker中的资源调度器与生命周期管理拆分成两个独立的组件，并命名为YARN

 

3-41）YARN的新特性
YARN是hadoop2.x之后才出的，主要是hadoop的HA(也就是集群)，磁盘的容错，资源调度器

 

3-42）hadoop join的原理
实现两个表的join首先在map端需要把表标示一下，把其中的一个表打标签，到reduce端再进行笛卡尔积的运算，就是reduce进行的实际的链接操作。

 

3-43）hadoop的二次排序
Hadoop默认的是HashPartitioner排序，当map端一个文件非常大另外一个文件非常小时就会产生资源的分配不均匀，既可以使用setPartitionerClass来设置分区，即形成了二次分区。

 

3-44）hadoop的mapreduce的排序发生在几个阶段？
发生在两个阶段即使map与reduce阶段

 

3-45）请描述mapreduce中shuffer阶段的工作流程，如何优化shuffer阶段的？
 

Mapreduce的shuffer是出在map task到reduce task的这段过程中，首先会进入到copy过程，会通过http方式请求map task所在的task Tracker获取map task 的输出的文件，因此当map  task结束，这些文件就会落到磁盘中，merge实在map端的动作，只是在map拷贝过来的数值，会放到内存缓冲区中，给shuffer使用，reduce阶段，不断的merge后最终会把文件放到磁盘中。

 

 

3-46）mapreduce的combiner的作用是什么，什么时候不易使用？？
Mapreduce中的Combiner就是为了避免map任务和reduce任务之间的数据传输而设置的，Hadoop允许用户针对map task的输出指定一个合并函数。即为了减少传输到Reduce中的数据量。它主要是为了削减Mapper的输出从而减少网络带宽和Reducer之上的负载。

在数据量较少时不宜使用。

 