package com.zhuima.test;

import lombok.Synchronized;

public class Test01 implements Runnable{
    //static int a = 0;
    Object obj1 = new Object();
    Object obj2 = new Object();
    public static void main(String[] args) throws InterruptedException {
        Test01 syn = new Test01();
        Thread th1 = new Thread(syn);
        Thread th2 = new Thread(syn);
        th1.start();
        //th1.join();
        //System.out.println("th1.a-===="+a);
        th2.start();
        //th2.join();
        //System.out.println("th2.a-===="+a);
       // while (th1.isAlive() || th2.isAlive()){}
        //System.out.println("main程序运行结束");

    }
    @Override
    public void run() {
        if(Thread.currentThread().getName().equals("Thread-0")){
            try {
                method1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            try {
                method2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /* try {
            method1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        /*synchronized(obj1){
           try{
               System.out.println(Thread.currentThread().getName()+"线程执行了obj1");
               Thread.sleep(2000);
               System.out.println(Thread.currentThread().getName()+"执行obj1完毕");
           }catch (InterruptedException e) {
               e.printStackTrace();
           }
          *//*  for (int i = 0; i < 1000; i++) {
                a++;
            }*//*
        }*/
        /*synchronized(obj2){
            try{
                System.out.println(Thread.currentThread().getName()+"线程执行了obj2");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()+"执行obj2完毕");
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
          *//*  for (int i = 0; i < 1000; i++) {
                a++;
            }*//*
        }*/
    }

    public synchronized void method1() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"进入到了同步方法1");
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName()+"离开同步方法1，并释放锁");
    }
    public synchronized void method2() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"进入到了同步方法2");
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName()+"离开同步方法2，并释放锁");
    }
}
