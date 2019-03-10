Linux 相关
2-1）关闭不必要的服务
A、使用ntsysv命令查看开启与关闭的服务

B、停止打印服务

[root@hadoop1 /]# /etc/init.d/cups stop

[root@hadoop1 /]# chkconfig cups off

2-2）关闭IP6
[root@hadoop1 /]# vim /etc/modprobe.conf

在下面添加一下配置：

alias net-pf-10 off

alias ipv6 off

2-3）调整文件的最大的打开数
查看当前的文件的数量：[root@hadoop1 /]#ulimit -a

修改配置：
[root@hadoop1 /]# vi /etc/security/limits.conf 在文件最后加上：

* soft nofile 65535

* hard nofile 65535

* soft nproc 65535

* hard nproc 65535

2-4）修改 linux 内核参数
[root@hadoop1 /]# vi /etc/sysctl.conf

在文本的最后追加一下内容：

net.core.somaxconn = 32768

 

表示物理内存使用到 90%（100-10=90）的时候才使用 swap 交换区

2-5）关闭 noatime
在最后追加一下内容

/dev/sda2 /data ext3 noatime,nodiratime 0 0

 

 

2-6)请用shell命令把某一个文件下的所有的文件分发到其他的机器上
Scp  -r  /user/local   hadoop2:/user/local

 

2-7）echo 1+1 && echo "1+1" 会输出什么
 

[root@hadoop1 ~]# echo 1+1 && echo "1+1"

1+1

1+1

 

[root@hadoop1 ~]# echo 1+1 && echo "1+1" && echo "1+" 1

1+1

1+1

1+ 1

 

2-8）在当前的额目录下找出包含祖母a并且文件的额大小大于55K的文件
[root@hadoop1 test]# find  .|  grep -ri "a"

a.text:a

 

后半句没有写出来，有时间在搞

 

 

2-9）linux用什么命令查看cpu,硬盘，内存的信息？
Top 命令
