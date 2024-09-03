package 线程;

import com.sun.media.jfxmediaimpl.HostUtils;

/**
 * @author 王天一
 * @version 1.0
 */
//明确概念 程序 进程（有生命周期）
//线程由进程创建 一个进程可以有多个线程
//单线程：同一时刻只允许执行一个线程
//多线程：同一时刻可以执行多个线程
//并发：同一时刻多个任务交替进行 例如单核cpu处理多任务
//并行：同一时刻多个任务同时执行 例如多核cpu处理多任务
//并发并行经常同时存在 格局要大一点

//创建线程的两种方式
//1继承Thread类 重写run方法
//2实现Runnable接口 重写run方法
//run中写线程要完成的任务
//两种方法本质没有区别 都是调用start0方法来启动线程
//Thread类也实现了Runnable接口
//    1
class Test1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());//可以看到主线程叫main
        Cat cat = new Cat();//还是要先创建对象
        cat.start();//启动子线程
        for (int i = 1; i <= 60; i++) {
            System.out.println("ಹಲೋ ವರ್ಲ್ಡ್" + i);
            Thread.sleep(1000);
        }
    }
}

class Cat extends Thread {
    //当我们继承Thread类后 就可以把Cat当成一个线程
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());//获取线程的名字 Thread-0
        int i = 1;
        while (i <= 80) {
            System.out.println("你好世界" + i);

            try {//这里会抛异常 try-catch是保证该线程在sleep时还是能感知响应，能够响应中断，不会睡死
                Thread.sleep(1000);//让线程休眠 单位毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}

//我们来分析一下上面的程序
//当我们点击运行后 程序生成了一个进程 进程开启了一个主线程main   main又通过start启动了一个子线程Thread-0
//当启动子线程后 主线程不会阻塞 会继续运行 所以看到的效果是交替输出
//当所有线程执行完毕后 程序才会退出
//可以用jconsole来连接监测线程
//子线程也可以再开启子线程

//关于为什么是start方法
//如果直接.run这样调用的话相当于还是在主线程里调用了这个方法 并没有开启子线程 而且会阻塞 即run完了才继续for 不会交替进行
//而start源码调用了start0 start0由jvm实现 开启线程 并由cpu具体决定什么时候执行


//2通过实现接口Runnable来开启线程
//在某些情况下一个类已经继承了另一个类 因为单继承机制 所以不再可能继承Thread 这时就要用Runnable接口
class Test2 {
    public static void main(String[] args) {
        Dog dog = new Dog();
        //dog.start();
        //遗憾的是Runnable中没有start方法 要
        Thread thread = new Thread(dog);
        thread.start();
        //原理是使用了一种叫静态代理模式
        System.out.println(Thread.currentThread().getName());
    }
}

class Dog implements Runnable {
    @Override
    public void run() {
        System.out.println("你好世界  " + Thread.currentThread().getName());
    }
}


//现在我们来模拟一下静态代理模式
class ThreadProxy implements Runnable {
    //这个类就是模拟Thread类 就是一个代理类
    private Runnable target = null;//Runnable类型的target

    @Override
    public void run() {
        if (target != null)
            target.run();//这个动态绑定是传入对象target的run 执行线程的内容
    }

    //构造器传入要代理的对象
    public ThreadProxy(Runnable target) {//构造器传入target
        this.target = target;
    }

    public void start()//因为第二种方式是没有start的 所以new了thread对象后要用thread的start 就是这个
    {
        start0();//这个start只是模拟一下 没有细写 这样就创建线程了
    }

    public void start0() {
        run();//这个是代理类的run 就是上面那个
    }
}

class Animal {

}

class Tiger extends Animal implements Runnable {
    @Override
    public void run() {
        System.out.println("静态代理");
    }
}

class Test3 {
    public static void main(String[] args) {
        Tiger tiger = new Tiger();
        ThreadProxy threadProxy = new ThreadProxy(tiger);
        threadProxy.start();//这时候用代理对象的start 就能创建线程
    }
}


//1 资源是独享的 每一个new的对象就是一个线程  每个线程中只用自己对象的资源
//2 资源是共享的 使用代理 一个代理可以开多个线程 多个线程可以传入相同的对象 资源共享

//通知线程退出
//在一个线程里设置变量作为调教 在另一个线程里控制变量 从而决定何时退出线程
//这种叫通知线程退出

//线程常用方法
//setName 设置线程名称
//getName 返回线程名称
//start   线程开始执行 由jvm调用start0创建新线程
//run     简单的方法调用 不会启动新线程
//setPriority 更改线程优先级  MAX_PRIORITY10  MIN_PRIORITY1  NORM_PRIORITY5
//getPriority
//sleep
//interrupt 中断线程但不是终止线程 常配合sleep使用

class Test5 {
    public static void main(String[] args) throws InterruptedException {
        AA aa = new AA();
        aa.setName("Thread001");
        aa.setPriority(Thread.MIN_PRIORITY);

        aa.start();
        Thread.sleep(1000);
        aa.interrupt();//手动打断一下  在下面try就会被捕获
    }
}

class AA extends Thread {
    @Override
    public void run() {
        while (true) {
            for (int j = 0; j < 10; j++) {
                System.out.println(j);
            }
            try {
                Thread.sleep(10000);//休眠10s
            } catch (InterruptedException e) {//这个捕获的就是Interrupt异常
                //如果休眠时捕到interrupt 就会终止休眠 来执行catch 所以说配合sleep使用
                System.out.println(Thread.currentThread().getName() + "睡眠被中断");
            }

        }
    }
}

//线程插队
//yield 当前线程主动礼让 执行其他线程 但不一定礼让成功
//join  指定线程插队 等到插队的线程结束后才会继续执行当前线程
//程序大意是子线程和主线程都循环20次 但当主线程循环5次后让子线程插个队
class Test4 {
    public static void main(String[] args) throws InterruptedException {
        BB bb = new BB();
        bb.start();//启动起来线程 现在是main和子线程同步执行
        for (int i = 1; i <= 20; i++) {
            System.out.println("主线程" + Thread.currentThread().getName() + "第" + i);
            Thread.sleep(1000);
            if (i == 5)//当循环五次后 我们让子线程插队
            {
                System.out.println("子线程插队");
                bb.join();//让谁插队就调谁的join  和sleep一样也会抛异常 在main里就直接throw就行
//                Thread.yield();//主动礼让 并不一定成功

            }//当子线程执行完后才会继续主线程
        }
    }
}

class BB extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            System.out.println("子线程" + Thread.currentThread().getName() + "第" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//守护线程
//当main线程结束后想让没执行完的子线程也结束 就可以把他设为守护线程 这样main结束后守护线程会自动结束
//setDaemon(true);


//线程七大状态
//重点：因为Runnable受线程调度器的影响，所以可以细分为Ready和Running两种状态
//线程礼让就是让线程退回到就绪(ready)状态，再由调度器重新选择调度，所以不一定礼让成功
//而插队就是到waiting状态


//线程同步
//多线程编程时 一些数据不允许被多个线程同时访问 会产生安全问题 这时可以使用线程同步
//即当一个线程对内存操作时 其他线程都不可以操作此内存 直到该线程完成操作
//售票问题  如果不使用线程同步  多个线程同时访问售票方法  会产生超卖现象
//实现
//1同步代码块
// synchronized(对象){得到对象的锁才能操作同步代码
// 需要被同步的代码
// }
//2同步方法 放在方法声明中
//public synchronized void m(){  需要被同步的代码  }
//可以把synchronized理解为锁，一个线程只有拿到锁后才能执行后面的方法，锁未被释放前即方法未执行完成前其他线程不能访问，因为没有拿到锁
//但是要考虑锁的数量 一个对象有一把锁  所以如果多个对象就会有多把锁，不能出现多把锁，目的是让多个线程争取一把锁，保证多个线程的锁是同一个对象的锁，如果多把锁的话就不会实现线程同步
//还要考虑把锁加在哪个对象或者方法上，如下我们不能把锁加到run上 加到run上相当于一个线程拿到锁后进入死循环会一直卖 就不符合三个窗口的情况 所以锁必须要有释放的机会

//**售票问题**
//60张票由三个窗口同时卖
class Test6 {
    public static void main(String[] args) {
//        Ticket ticket = new Ticket();
//        new Thread(ticket).start();
//        new Thread(ticket).start();
//        new Thread(ticket).start();

//        new Ticket1().start();
//        new Ticket1().start();
//        new Ticket1().start();
        //上面两种方式有概率产生超卖问题
        //不止会产生超卖，运行过程中还能看到有票数重复现象 都是因为多个线程同时访问了ticketNum

        Ticket2 ticket2 = new Ticket2();//Ticket2对象只有一个
        new Thread(ticket2).start();
        new Thread(ticket2).start();
        new Thread(ticket2).start();

//        new Ticket3().start();
//        new Ticket3().start();
//        new Ticket3().start();
    }
}

//实现Runnable接口
class Ticket implements Runnable {
    public int ticketNum = 60;//不用static

    @Override
    public void run() {
        while (true) {
            if (ticketNum <= 0) {
                System.out.println("已售完");
                break;
            }
            System.out.println(Thread.currentThread().getName() + "售出一张票 " + " 剩余票数" + (--ticketNum));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//继承Thread
class Ticket1 extends Thread {
    public static int ticketNum = 100;//加static使多个对象共享

    @Override
    public void run() {
        while (true) {
            if (ticketNum <= 0) {
                System.out.println("已售完");
                break;
            }
            System.out.println(Thread.currentThread().getName() + "售出一张票 " + " 剩余票数" + (--ticketNum));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//解决超卖
//1实现Runnable
class Ticket2 implements Runnable {
    public int ticketNum = 60;
    public boolean loop = true;

    public synchronized void sale()//用线程同步 保证同时只有一个线程在sale
    {
        if (ticketNum <= 0) {
            System.out.println("已售完");
            loop = false;
            return;//注意
        }
        System.out.println(Thread.currentThread().getName() + "售出一张票 " + " 剩余票数" + (--ticketNum));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    //关于为什么不在run上锁 因为run里面是while死循环即run方法在卖完前不会结束
    //所以一个线程拿到锁后没退出run之前就一直卖 所以直到卖完还是只有一个窗口在卖
    //如果是在sale上锁，就可以满足拿到锁就卖，卖完一张还锁，继续下一次竞争
    //所以拿出来建一个新方法sale
    public void run() {
        while (loop) {
            sale();
        }
    }
}
//发现不会再出现票数重复的现象
//这样能成功的原因是  这里调用的是同一个对象的run方法，想想静态代理模式，最后绑定的都是同一个对象的方法
//如果用的继承的方式实现多线程的 new了三个对象就有三把锁 三个对象的run就三个线程分别执行 锁不住
//syn关键字是多个线程进入同一个对象的同一方法的，针对同一个对象才有用，如果你是不同对象，那每次都会有一个线程，syn没有任何作用，所以要考虑把锁给谁
//这里也能看出第二种方法即静态代理模式的资源共享

//2继承Thread
//想用继承的话得将sale方法设为静态方法，因为如果不设为静态方法的话，会使得三个对象调用的是不同的方法，那同步就没有什么作用了
//所以可以认为 继承Thread本身是资源独享 我们用static让他共享起来 同样解决问题
class Ticket3 extends Thread {
    public static int ticketNum = 60;//静态
    public static boolean loop = true;

    public static synchronized void sale() {//static
        if (ticketNum <= 0) {
            System.out.println("已售完");
            loop = false;
            return;//注意
        }
        System.out.println(Thread.currentThread().getName() + "售出一张票 " + " 剩余票数" + (--ticketNum));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (loop) {
            sale();
        }
    }
}


//锁（互斥锁）
//Java中引入互斥锁来保证共享数据的完整性
//关键字synchronized与互斥锁联系 下面这一段应该是对synchronized锁的介绍 写的很好
//在C/C++可直接使用操作系统提供的互斥锁实现同步和线程的阻塞和唤起，与之不同的是，java要把这些底层封装，
//而synchronized就是一个典型的互斥锁，同时它也是一个JVM级别的锁，它的实现细节全部封装在JVM中实现，
//对开发人员只提供了synchronized关键词。根据锁的颗粒度，可以用synchronized对一个变量、一个方法、一个对象和一个类等加锁。
//被synchronized修饰的程序块经过编译后，会在前后生成monitorenter和monitorexit两个字节码指令，
//其中涉及到锁定和解锁对象的确定，这就要根据synchronized来确定了，假如明确指定了锁对象，
//例如synchronized(变量)、synchronized(this)等，说明加解锁对象为变量或运行时对象。
//假如没有明确指定对象，则根据synchronized修饰的方法去找对应的锁对象，
//如修饰一个非静态方法表示此方法对应的对象为锁对象，如修饰一个静态方法则表示此方法对应的类对象为锁对象。
//当一个对象被锁住时，对象里面所有用synchronized修饰的方法都将产生堵塞，
//而对象里非synchronized修饰的方法可正常被调用，不受锁影响。
//局限性：程序效率降低


//加锁的方式
//非静态方法加锁  可以加在方法声明、this，也可以加在类的属性
//静态方法加锁  加在方法声明或者在方法内部使用同步代码块
//下面是演示加锁  具体代码内容可以不用管
class Lock implements Runnable {
    public int ticketNum = 100;
    public boolean loop = true;
    Object object = new Object();//new

    public /*synchronized*/ void sell()//非静态方法加锁
    {
        //synchronized (this) {//这是用同步代码块加锁
        synchronized (object) {//这个锁加在this对象中的的对象  也算成功加锁
            //object是成员变量，当有线程占用该对象时，object实际也被占用了，所以给object上锁是一样的效果。
            if (ticketNum <= 0) {
                System.out.println("已售完");
                loop = false;
                return;
            }
            System.out.println(Thread.currentThread().getName() + "售出一张票 " + " 剩余票数" + (--ticketNum));
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //演示静态方法加锁
    public static synchronized void m1() {//在方法声明时加在方法上

    }

    public static void m2() {                //或者对象.class
        synchronized (Lock.class) {//在方法内部使用同步代码块对类加锁

        }
    }

    @Override
    public void run() {
        while (loop) {
            sell();
        }
    }
}

//我的理解 线程同步的目的就是让数据不能被多个线程同时访问 而要实现线程同步我们就要给对象，类等加上锁
//加锁的方式就是synchronized关键字  而synchronized的实现有两种方式 同步方法或者同步代码块  我们要分析上锁的代码
//选择同步代码块或者同步方法  最终要保证的是多个线程的锁在是同一个对象(保证一把锁)  这样线程只有拿到锁才能继续执行
//想想如果锁存在于多个对象 那么每个线程都能拿到锁 也就不能线程同步

//线程死锁
//线程互相占用对方想要的锁
//下面模拟死锁现象
class DeadLock extends Thread {
    static Object o1 = new Object();
    static Object o2 = new Object();//static
    boolean flag;

    public DeadLock(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag) {
            //把锁加在o1o2上  因为只有一把锁 所以要static
            //这样如果true false两个线程同时来 一个拿到o1等o2  另一个拿到o2等o1
            //这样就阻塞了
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + "拿到o1锁");
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + "拿到o2锁2");
                }
            }

        } else {
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + "拿到o2锁");
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + "拿到o1锁2");
                }
            }
        }
    }
}

class Test7 {
    public static void main(String[] args) {
        DeadLock deadLock1 = new DeadLock(true);
        DeadLock deadLock2 = new DeadLock(false);
        deadLock1.start();
        deadLock2.start();//启动两个线程
    }
}//可以看到程序不会结束，一直在阻塞


//什么时候释放锁
//同步方法同步代码块执行结束 return 出现异常 执行wait()方法
//什么时候不会释放锁
//调用Thread.sleep() Thread.yield  其他线程调用该线程的suspend()挂起