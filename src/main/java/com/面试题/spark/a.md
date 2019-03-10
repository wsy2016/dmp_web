 
Spark 相关
9-1）mr 和 spark 区别，怎么理解 spark-rdd
     Mr 是文件方式的分布式计算框架，是将中间结果和最终结果记录在文件中，map 和 reduce的数据分发也是在文件中。

 

Spark 是内存迭代式的计算框架，计算的中间结果可以缓存内存，也可以缓存硬盘，但是不是每一步计算都需要缓存的。

 

Spark-rdd 是一个数据的分区记录集合，是利用内存来计算的，spark之所以快是因为有内存的模式

 

9-2）简单描述spark的wordCount的执行过程
Scala> sc.textFile("/usr/local/words.txt")

res0: org.apache.spark.rdd.RDD[String] = /usr/local/words.txt MapPartitionsRDD[1] at textFile at <console>:22

 

scala> sc.textFile("/usr/local/words.txt").flatMap(_.split(" "))

res2: org.apache.spark.rdd.RDD[String] = MapPartitionsRDD[4] at flatMap at <console>:22

 

scala> sc.textFile("/usr/local/words.txt").flatMap(_.split(" ")).map((_,1))

res3: org.apache.spark.rdd.RDD[(String, Int)] = MapPartitionsRDD[8] at map at <console>:22

 

scala> sc.textFile("/usr/local/words.txt").flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)

res5: org.apache.spark.rdd.RDD[(String, Int)] = ShuffledRDD[17] at reduceByKey at <console>:22

 

scala> sc.textFile("/usr/local/words.txt").flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).collect

res6: Array[(String, Int)] = Array((dageda,1), (xiaoli,1), (hellow,4), (xisdsd,1), (xiaozhang,1))

 

 

9-3）按照需求使用spark编写一下程序
A、当前文件a.text的格式为，请统计每个单词出现的个数

A,b,c,d

B,b,f,e

A,a,c,f

 

sc.textFile(“/user/local/a.text”).flatMap(_.split(“,”)).map((_,1)).ReduceByKey(_+_).collect()

或：

package cn.bigdata

 

import org.apache.spark.SparkConf

import org.apache.spark.SparkContext

import org.apache.spark.rdd.RDD

 

object Demo {

 

  /*

a,b,c,d

b,b,f,e

a,a,c,f

c,c,a,d

   * 计算第四列每个元素出现的个数

   */

  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setAppName("demo").setMaster("local")

    val sc: SparkContext = new SparkContext(conf)

    val data: RDD[String] = sc.textFile("f://demo.txt")

    //数据切分

    val fourthData: RDD[(String, Int)] = data.map { x =>

      val arr: Array[String] = x.split(",")

      val fourth: String = arr(3)

      (fourth, 1)

    }

    val result: RDD[(String, Int)] = fourthData.reduceByKey(_ + _);

    println(result.collect().toBuffer)

  }

}

 

B、HDFS中有两个文件a.text与b.text,文件的格式为(ip,username),如：a.text,b.text

a.text

127.0.0.1  xiaozhang

127.0.0.1  xiaoli

127.0.0.2  wangwu

127.0.0.3  lisi

 

B.text

127.0.0.4  lixiaolu

127.0.0.5  lisi

 

每个文件至少有1000万行，请用程序完成一下工作，

1）每个文件的个子的IP

2)出现在b.text而没有出现在a.text的IP

3)每个user出现的次数以及每个user对应的IP的个数

 

代码如下：

1）各个文件的ip数

package cn.bigdata

 

import java.util.concurrent.Executors

 

import org.apache.hadoop.conf.Configuration

import org.apache.hadoop.fs.FileSystem

import org.apache.hadoop.fs.LocatedFileStatus

import org.apache.hadoop.fs.Path

import org.apache.hadoop.fs.RemoteIterator

import org.apache.spark.SparkConf

import org.apache.spark.SparkContext

import org.apache.spark.rdd.RDD

import org.apache.spark.rdd.RDD.rddToPairRDDFunctions

 

//各个文件的ip数

object Demo2 {

 

  val cachedThreadPool = Executors.newCachedThreadPool()

 

  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setAppName("demo2").setMaster("local")

    val sc: SparkContext = new SparkContext(conf)

    val hdpConf: Configuration = new Configuration

    val fs: FileSystem = FileSystem.get(hdpConf)

    val listFiles: RemoteIterator[LocatedFileStatus] = fs.listFiles(new Path("f://txt/2/"), true)

    while (listFiles.hasNext) {

      val fileStatus = listFiles.next

      val pathName = fileStatus.getPath.getName

      cachedThreadPool.execute(new Runnable() {

        override def run(): Unit = {

          println("=======================" + pathName)

          analyseData(pathName, sc)

        }

      })

    }

  }

 

  def analyseData(pathName: String, sc: SparkContext): Unit = {

    val data: RDD[String] = sc.textFile("f://txt/2/" + pathName)

    val dataArr: RDD[Array[String]] = data.map(_.split(" "))

    val ipAndOne: RDD[(String, Int)] = dataArr.map(x => {

      val ip = x(0)

      (ip, 1)

    })

    val counts: RDD[(String, Int)] = ipAndOne.reduceByKey(_ + _)

    val sortedSort: RDD[(String, Int)] = counts.sortBy(_._2, false)

    sortedSort.saveAsTextFile("f://txt/3/" + pathName)

  }

}

 

2）出现在b.txt而没有出现在a.txt的ip

package cn.bigdata

 

import java.util.concurrent.Executors

 

import org.apache.spark.SparkConf

import org.apache.spark.SparkContext

import org.apache.spark.rdd.RDD

 

/*

 * 出现在b.txt而没有出现在a.txt的ip

 */

object Demo3 {

  

  val cachedThreadPool = Executors.newCachedThreadPool()

  

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Demo3").setMaster("local")

    val sc = new SparkContext(conf)

    val data_a = sc.textFile("f://txt/2/a.txt")

    val data_b = sc.textFile("f://txt/2/b.txt")

    val splitArr_a = data_a.map(_.split(" "))

    val ip_a: RDD[String] = splitArr_a.map(x => x(0))

    val splitArr_b = data_b.map(_.split(" "))

    val ip_b: RDD[String] = splitArr_b.map(x => x(0))

    val subRdd: RDD[String] = ip_b.subtract(ip_a)

    subRdd.saveAsTextFile("f://txt/4/")

  }

}

 

3）

package cn.bigdata

 

import org.apache.spark.SparkConf

import org.apache.spark.SparkContext

import org.apache.spark.rdd.RDD

import scala.collection.mutable.Set

 

/*

 * 每个user出现的次数以及每个user对应的ip数

 */

object Demo4 {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Demo4").setMaster("local")

    val sc = new SparkContext(conf)

    val data: RDD[String] = sc.textFile("f://txt/5/")

    val lines = data.map(_.split(" "))

    val userIpOne = lines.map(x => {

      val ip = x(0)

      val user = x(1)

      (user, (ip, 1))

    })

 

    val userListIpCount: RDD[(String, (Set[String], Int))] = userIpOne.combineByKey(

      x => (Set(x._1), x._2),

      (a: (Set[String], Int), b: (String, Int)) => {

        (a._1 + b._1, a._2 + b._2)

      },

      (m: (Set[String], Int), n: (Set[String], Int)) => {

        (m._1 ++ n._1, m._2 + n._2)

      })

 

    val result: RDD[String] = userListIpCount.map(x => {

      x._1 + ":userCount:" + x._2._2 + ",ipCount:" + x._2._1.size

    })

 

    println(result.collect().toBuffer)

 

  }

}

 

Sqoop 相关
10-1）sqoop在导入到MySql数据库是怎样保证数据重复，如果重复了该怎么办？？

在导入时在语句的后面加上一下命令作为节点：

--incremental append \

--check-column id \

--last-value 1208

 

Redis 相关
10-1）redis保存磁盘的时间
#   Note: you can disable saving at all commenting all the "save" lines.

#

#   It is also possible to remove all the previously configured save

#   points by adding a save directive with a single empty string argument

#   like in the following example:

#

#   save ""

 

save 900 1

save 300 10

save 60 10000
