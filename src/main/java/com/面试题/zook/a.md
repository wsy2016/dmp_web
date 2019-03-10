Zookeeper 相关
4-1）写出你对zookeeper的理解
     随着大数据的快速发展，多机器的协调工作，避免主要机器单点故障的问题，于是就引入管理机器的一个软件，他就是zookeeper来协助机器正常的运行。

     Zookeeper有两个角色分别是leader与follower ，其中leader是主节点，其他的是副节点，在安装配置上一定要注意配置奇数个的机器上，便于zookeeper快速切换选举其他的机器。

在其他的软件执行任务时在zookeeper注册时会在zookeeper下生成相对应的目录，以便zookeeper去管理机器。

 

4-2）zookeeper 的搭建过程
主要是配置文件zoo.cfg 配置dataDir 的路径一句dataLogDir 的路径以及myid的配置以及server的配置，心跳端口与选举端口
