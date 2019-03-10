Hive 相关
5-1）hive是怎样保存元数据的
保存元数据的方式有：内存数据库rerdy，本地MySQL数据库，远程mysql数据库，但是本地的mysql数据用的比较多，因为本地读写速度都比较快

 

5-2）外部表与内部表的区别
 

先来说下Hive中内部表与外部表的区别： 

Hive 创建内部表时，会将数据移动到数据仓库指向的路径；若创建外部表，仅记录数据所在的路径，不对数据的位置做任何改变。在删除表的时候，内部表的元数据和数据会被一起删除，而外部表只删除元数据，不删除数据。这样外部表相对来说更加安全些，数据组织也更加灵活，方便共享源数据。

 

5-3）对于 hive，你写过哪些 UDF 函数，作用是什么
UDF: user  defined  function  的缩写，编写hive udf的两种方式extends UDF 重写evaluate第二种extends GenericUDF重写initialize、getDisplayString、evaluate方法

 

5-4)Hive 的 sort by 和 order by 的区别
  order by 会对输入做全局排序，因此只有一个reducer（多个reducer无法保证全局有序）只有一个reducer，会导致当输入规模较大时，需要较长的计算时间。

   sort by不是全局排序，其在数据进入reducer前完成排序.

因此，如果用sort by进行排序，并且设置mapred.reduce.tasks>1， 则sort by只保证每个reducer的输出有序，不保证全局有序。

 

5-5）hive保存元数据的方式以及各有什么特点？
1、Hive有内存数据库derby数据库，特点是保存数据小，不稳定

2、mysql数据库，储存方式可以自己设定，持久化好，一般企业开发都用mysql做支撑

5-6）在开发中问什么建议使用外部表？
1、外部表不会加载到hive中只会有一个引用加入到元数据中

2、在删除时不会删除表，只会删除元数据，所以不必担心数据的

 

5-7）hive partition 分区
分区表，动态分区

 

5-8）insert into 和 override write 区别？
insert into：将某一张表中的数据写到另一张表中

override write：覆盖之前的内容。