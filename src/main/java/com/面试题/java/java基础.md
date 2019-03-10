Big Data 面试题总结
JAVA相关
1-1）List 与set 的区别？
老掉牙的问题了，还在这里老生常谈：List特点：元素有放入顺序，元素可重复 ，Set特点：元素无放入顺序，元素不可重复。

 

1-2）数据库的三大范式？
原子性、一致性、唯一性

 

1-3）java 的io类的图解
 

1-4）对象与引用对象的区别
对象就是好没有初始化的对象，引用对象即使对这个对象进行了初始化，这个初始化可以使自己的直接new的也可以是直接其他的赋值的，那么背new或者背其他赋值的我们叫做是引用对象，最大的区别于

 

1-5）谈谈你对反射机制的理解及其用途？
反射有三种获取的方式，分别是：forName  / getClass / 直接使用class方式 使用反射可以获取类的实例

1-6）列出至少五种设计模式
设计方式有工厂法，懒加载，观察者模式，静态工厂，迭代器模式，外观模式、、、、

 

1-7）RPC 原理？
Rpc分为同步调用和一部调用，异步与同步的区别在于是否等待服务器端的返回值。Rpc的组件有RpcServer,RpcClick,RpcProxy,RpcConnection,RpcChannel,RpcProtocol,RpcInvoker等组件，

 

1-8）ArrayList、Vector、LinkedList 的区别及其优缺点？HashMap、HashTable 的区别及优缺点？
    ArrayList 和 Vector 是采用数组方式存储数据的,是根据索引来访问元素的，都可以

根据需要自动扩展内部数据长度，以便增加和插入元素，都允许直接序号索引元素，但

是插入数据要涉及到数组元素移动等内存操作，所以索引数据快插入数据慢，他们最大

的区别就是 synchronized 同步的使用。

    LinkedList 使用双向链表实现存储，按序号索引数据需要进行向前或向后遍历，但

是插入数据时只需要记录本项的前后项即可，所以插入数度较快！

如果只是查找特定位置的元素或只在集合的末端增加、移除元素，那么使用 Vector

或 ArrayList 都可以。如果是对其它指定位置的插入、删除操作，最好选择 LinkedList

HashMap、HashTable 的区别及其优缺点：

     HashTable 中的方法是同步的 HashMap 的方法在缺省情况下是非同步的 因此在多线程环境下需要做额外的同步机制。

    HashTable 不允许有 null 值 key 和 value 都不允许，而 HashMap 允许有 null 值 key和 value 都允许 因此 HashMap 使用 containKey（）来判断是否存在某个键。

HashTable 使用 Enumeration ，而 HashMap 使用 iterator。

     Hashtable 是 Dictionary 的子类，HashMap 是 Map 接口的一个实现类。

1-9）使用 StringBuffer 而不是 String
当需要对字符串进行操作时，使用 StringBuffer 而不是 String，String 是 read-only 的，如果对它进行修改，会产生临时对象，而 StringBuffer 是可修改的，不会产生临时对象。

1-10）集合的扩充
ArrayList  list = new ArrayList(90000); list扩充多少次？？

 public ArrayList() {

        this(10);

}

默认的扩充是10由此计算

 

1-11）java的拆包与封包的问题
 

System.out.println("5" + 2);

52

 

1-12）Java中Class.forName和ClassLoader.loadClass的区别
Class.forName("xx.xx")等同于Class.forName("xx.xx",true,CALLClass.class.getClassLoader())，第二个参数(bool)表示装载类的时候是否初始化该类，即调用类的静态块的语句及初始化静态成员变量。

 

ClassLoader loader = Thread.currentThread.getContextClassLoader(); //也可以用(ClassLoader.getSystemClassLoader())

 

Class cls = loader.loadClass("xx.xx"); //这句话没有执行初始化

 

forName可以控制是否初始化类，而loadClass加载时是没有初始化的。

 

1-13）hashMap与hashTable的区别
                   HashMap                Hashtable

 

父类               AbstractMap          Dictiionary

 

是否同步            否                            是

 

k，v可否null        是                            否

 

 

Hashtable和HashMap采用的hash/rehash算法都大概一样，所以性能不会有很大的差异。

 

1-14）怎样实现数组的反转
ArrayList arrayList = new ArrayList();  

 arrayList.add("A");  

 arrayList.add("B");

 

对数组进行反转

Collections.reverse(arrayList);

 

1-15）请使用JAVA实现二分查找
一般的面试者都是些向看看你的思路，所以一般答题时只需要把思路写出来即可。

具体的实现如下：

二分查找就是折半查找，要想折半就必须把原来的数据进行排序，才能方便的查找：

实现代码如下：

 public static int binarySearch(int[] srcArray, int des){   

        int low = 0;   

        int high = srcArray.length-1;   

        while(low <= high) {   

            int middle = (low + high)/2;   

            if(des == srcArray[middle]) {   

                return middle;   

            }else if(des <srcArray[middle]) {   

                high = middle - 1;   

            }else {   

                low = middle + 1;   

            }  

        }  

        return -1;  

   }

 

 

1-16）java 中有两个线程怎样等待一个线程执行完毕
可以使用join关键字

 

1-17）hashmap hashtable currentHashMap的使用区别
     hashmap hashtable 的醉的的区别在于hashtable 是线程安全的，而hashmap 不是线程安全的，currentHashMap也是线程安全的。

     ConcurrentHashMap是使用了锁分段技术技术来保证线程安全的。所分段的技术是：讲数据分成一段一段的储存，给每一段的数据添加一把锁，当线程访问一个数据时，其他的数据可以被访问。

 

1-18）简单描述一下java的gc机制，常用的JAVA调优的方法，OOM如何产生的，如何处理OOM问题？？？
1、程序在运行时会产生很多的对象的信息，当这些对象的信息没有用时，则会被gc回收

2、调优的方式主要是调节年轻代与老年代的内存的大小

3、OOM是OutOfMemory的缩写(搞得跟多高大上似的)就是线程创建的多了，没有及时的回收过来所产生的，代码如下：

public class JavaVMStackOOM {  

    private void dontStop() {  

        while (true) {  

              

        }  

    }  

      

    public void stackLeakByThread() {  

        while (true) {  

            Thread thread = new Thread(new Runnable() {  

                @Override  

                public void run() {  

                    dontStop();  

                }  

            });  

            thread.start();  

        }  

    }  

      

    public static void main(String[] args) {  

        JavaVMStackOOM oom = new JavaVMStackOOM();  

        oom.stackLeakByThread();  

}  

 

4、既然知道以上的现象，在写代码时应该注意，不要过多的创建线程的数目。

 

 

 

 




 

 

 

 

