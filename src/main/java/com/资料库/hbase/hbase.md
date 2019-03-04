Hbase

_**一: 架构**_
_**hbase架构**_

 client : 通过rpc与hbaseMaster和hRegionServe通讯
       
              
              
 zookeeper    | HRegionServer以Ephemeral方式注册到 Zookeeper中
              | Zookeeper Quorum中存储了-ROOT-表的地址和HMaster的地址
       
  
                       
 hbaseMaster  | 可以启动多个HMaster，通过Zookeeper的Master Election机制保证总有一个Master运行
              | HMaster在功能上主要负责Table和Region的管理工作：
                          
                  1. 管理用户对Table的增、删、改、查操作
                  
                  2. 管理HRegionServer的负载均衡，调整Region分布 保证region相对均匀
                  
                  3. 在Region Split后，负责新Region的分配
                  
                  4. 在HRegionServer停机后，负责失效HRegionServer 上的Regions迁移 ??? 没有写入fstore的数据会不会丢失
       
hRegionServe  | 主要负责响应用户I/O请求，向HDFS文件系统中读写数据，是HBase中最核心的模块。


_**hRegionServe架构**_
HRegionServer内部管理了一系列HRegion对象，每个HRegion对应了Table中的一个 Region，HRegion中由多个HStore组成。
每个HStore对应了Table中的一个Column Family的存储，可以看出每个Column Family其实就是一个集中的存储单元，
因此最好将具备共同IO特性的column放在一个Column Family中，这样最高效。

HStore存储是HBase存储的核心了，其中由两部分组成，一部分是MemStore，一部分是StoreFiles。 MemStore是Sorted Memory Buffer，用户写入的数据首先会放入MemStore，当MemStore满了以后会Flush成一个StoreFile(底层实现是HFile)， 当StoreFile文件数量增长到一定阈值，会触发Compact合并操作，将多个StoreFiles合并成一个StoreFile，合并过程中会进 行版本合并和数据删除，因此可以看出HBase其实只有增加数据，所有的更新和删除操作都是在后续的compact过程中进行的，这使得用户的写操作只要 进入内存中就可以立即返回，保证了HBase I/O的高性能。当StoreFiles Compact后，会逐步形成越来越大的StoreFile，当单个StoreFile大小超过一定阈值后，会触发Split操作，同时把当前 Region Split成2个Region，父Region会下线，新Split出的2个孩子Region会被HMaster分配到相应的HRegionServer 上，使得原先1个Region的压力得以分流到2个Region上。下图描述了Compaction和Split的过程：


