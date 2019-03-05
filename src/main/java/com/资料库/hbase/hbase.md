Hbase

_**一: 架构**_
_**hbase架构**_

 client : 通过rpc与hbaseMaster和hRegionServe通讯
       
              
              
 zookeeper    | Zookeeper Quorum 存储服务器节点和-root表信息
              
               1. 可以启动多个HMaster，通过Zookeeper的Master Election机制保证总有一个Master运行)
               2. HRegionServer以Ephemeral方式注册到Zookeeper中(管理和监控)
               3. 存储ROOT表(schema,table元数据)
       
  
                       
 hbaseMaster   | HMaster在功能上主要负责Table和Region的管理工作：
                   
                  1. 管理HRegionServer的负载均衡(对regionq的IO,region的分配)
                  2. Region的分配 
                    2.1.在Region Split[regionServer自己操作]后负责Region的分配                
                    2.2. 在HRegionServer停机后，负责失效HRegionServer 上的Regions迁移 `??? 没有写入fstore的数据会不会丢失`
                  3. 管理用户对Table的增、删、改、查操作 
       
hRegionServe  |  region的维护
                  
                  1. 处理对region的I/O请求
                  2. 大region的切分


_**hRegionServer架构**_

  hRegionServer
     ↓
   n个hregion+HLog(为下面读写容灾考虑) →→| 每个HRegion对应了Table中的一个 Region(table的m行)
     ↓  
   n个hstore                        →→| 每个HStore对应了Table中的一个Column Family的存储，可以看出每个Column Family其实就是一个集中的存储单元，   
                                         --| 因此最好将具备共同IO特性的column放在一个Column Family中，这样最高效
     ↓
   n个storeFile + 一个 memstore     →→| MemStore是Sorted Memory Buffer，用户写入的数据首先会放入MemStore，
                                       当MemStore满了以后会`Flush`成一个StoreFile(底层实现是HFile)，
                                   →→| 当StoreFile文件数量增长到一定阈值，会触发`Compact`[minor,major]合并操作，将多个StoreFiles合并成一个StoreFile，
                                       合并过程中会进 行版本合并和数据删除，
                                 ` 总结`: 
                                        1.可以看出MemStore其实只有增加数据，所有的更新和删除操作都是在后续的compact过程中进行的，
                                          这使得用户的写操作只要进入内存中就可以立即返回，保证了HBase I/O的高性能。
                                        2. StoreFiles Compact后，会逐步形成越来越大的StoreFile，当单个StoreFile大小超过一定阈值后，
                                           会触发Split操作，同时把当前 Region Split成2个Region，父Region会下线，
                                        3.新Split出的2个孩子Region会被HMaster分配到相应的HRegionServer 上，使得原先1个Region的压力得以分流到2个Region上.


_**hbase写**_
预写日志WAL：

每次更新都会写入日志，只有写入成功才会通知客户端操作成功，然后服务器可以按需自由地批量处理或聚合内存中的数据。
 
处理过程：客户端通过RPC调用将KeyValue对象实例
         发送到含有匹配region的HRegionServer。接着这些实例被发送到管理相应行的HRegion实例，
         数据被写入到WAL，然后被放入到实际拥有记录的存储文件的MemStore中。
         当memstore中的数据达到一定的大小以后，数据会异步地连续写入到文件系统中，WAL能保证这一过程的数据不会丢失。

