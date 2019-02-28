package thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Author: wsy
 * <p>
 * Date: 2019/2/26 17:43
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolTest test = new ThreadPoolTest();
        //自定义ThreadPoolExecutor 在系统多请求时对线程进行管理和重用，避免重复创建和销毁线程，造成损耗
        //ThreadPoolExecutor extend AbstractExecutorService implements ExecutorService extends Executor
        TestTask task = new TestTask("ThreadPoolExecutor-Test");

        //无界队列 且SynchronousQueue队列不会缓存任务
        //第一次激活三个线程，第二次再激活三个线程，运行结束后保持6个核心线程可用
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
//        test.run(threadPoolExecutor, task);

        //无界队列 且LinkedBlockingQueue会将任务缓存在队列中
        //第一次激活三个线程，第二次放在缓存队列中，由于没有边界限制，最大线程数没有意义，运行结束后保持3个核心线程可用，
        threadPoolExecutor = new ThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        test.run(threadPoolExecutor, task);

        //第一次激活三个线程，第二次激活三个线程，运行结束后保持3个核心线程可用，非核心线程超时被回收
        threadPoolExecutor = new ThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
//        test.run(threadPoolExecutor, task);

        //缓存队列加上大小限制
        //第一次激活三个线程，第二次两个任务放入缓存队列中，另外激活一个线程执行多余的任务
        threadPoolExecutor = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(2));
//        test.run(threadPoolExecutor, task);

        //缓存队列已满，且提交任务>最大线程数
        threadPoolExecutor = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1));
//        test.run(threadPoolExecutor, task);
    }

    public void run(ThreadPoolExecutor threadPoolExecutor, Runnable runnable) throws InterruptedException {
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        System.out.println("111-------------先执行三个任务");
        System.out.println("111核心线程数: " + threadPoolExecutor.getCorePoolSize());
        System.out.println("111线程总数: " + threadPoolExecutor.getPoolSize());
        System.out.println("111活跃: " + threadPoolExecutor.getActiveCount());
        System.out.println("111任务数: " + threadPoolExecutor.getTaskCount());
        System.out.println("111完成任务数: " + threadPoolExecutor.getCompletedTaskCount());
        System.out.println("111队列中任务数: " + threadPoolExecutor.getQueue().size());
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        System.out.println("222-------------再执行三个任务");
        System.out.println("222核心线程数: " + threadPoolExecutor.getCorePoolSize());
        System.out.println("222线程总数: " + threadPoolExecutor.getPoolSize());
        System.out.println("111活跃: " + threadPoolExecutor.getActiveCount());
        System.out.println("111任务数: " + threadPoolExecutor.getTaskCount());
        System.out.println("111完成任务数: " + threadPoolExecutor.getCompletedTaskCount());
        System.out.println("222队列中任务数: " + threadPoolExecutor.getQueue().size());
        Thread.sleep(8000);
        System.out.println("8秒之后");
        System.out.println("核心线程数: " + threadPoolExecutor.getCorePoolSize());
        System.out.println("线程总数: " + threadPoolExecutor.getPoolSize());
        System.out.println("111活跃: " + threadPoolExecutor.getActiveCount());
        System.out.println("111任务数: " + threadPoolExecutor.getTaskCount());
        System.out.println("111完成任务数: " + threadPoolExecutor.getCompletedTaskCount());
        System.out.println("队列中任务数: " + threadPoolExecutor.getQueue().size());
        threadPoolExecutor.shutdown();
    }

    public static class TestTask implements Runnable {
        private String threadName;

        public TestTask(String threadName) {
            this.threadName = threadName;
        }

        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("ThreadName: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


