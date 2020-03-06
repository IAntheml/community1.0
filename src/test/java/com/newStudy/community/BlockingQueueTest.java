package com.newStudy.community;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author shkstart
 * @create 2020-03-03-14:01
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        BlockingQueue queue = new ArrayBlockingQueue(10);
        new Thread(new Producter(queue)).start();
        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();
    }
}
class Producter implements Runnable{

    private BlockingQueue<Integer> queue;//阻塞队列

    public Producter(BlockingQueue<Integer> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            for (int i = 0; i < 100; i++) {
                Thread.sleep(20);
                queue.put(i);
                System.out.println(Thread.currentThread().getName()+"生产"+queue.size());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable{

    private BlockingQueue<Integer> queue;//阻塞队列

    public Consumer(BlockingQueue<Integer> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try{

            while(true){
                Thread.sleep(new Random().nextInt(1000));//在0到1000内随机一个数
                queue.take();
                System.out.println(Thread.currentThread().getName()+"消费"+queue.size());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}