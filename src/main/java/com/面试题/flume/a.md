Flume相关
7-1）flume 不采集 Nginx 日志，通过 Logger4j 采集日志，优缺点是什么？
在nginx采集日志时无法获取session的信息，然而logger4j则可以获取session的信息，logger4j的方式比较稳定，不会宕机。缺点：不够灵活，logger4j的方式和项目结合过滤紧密，二flume的方式就比较灵活，便于插拔式比较好，不会影响项目的性能。

 

7-2）flume 和 kafka 采集日志区别，采集日志时中间停了，怎么记录之前的日志。
Flume 采集日志是通过流的方式直接将日志收集到存储层，而 kafka 试讲日志缓存在 kafka

集群，待后期可以采集到存储层。Flume 采集中间停了，可以采用文件的方式记录之前的日志，而 kafka 是采用 offset(偏移量) 的方式记录之前的日志。
