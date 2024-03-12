package chapter09;

//当父类的一些方法不确定时（没有方法体） 可以用abstract来修饰该方法 然后用abstract修饰该类
//该方法就是抽象方法 类就是抽象类
//由子类继承实现
//注意点
//抽象类不能被实例化
//抽象类不一定要有抽象方法
//当类中存在抽象方法时类必须也用abstract修饰
//abstract关键字只能修饰类或者方法
//抽象方法不能有方法体就是{}
//如果一个类继承了抽象类 则必须实现抽象类的所抽象有方法 除非他也声明为抽象类
//抽象方法不能用private final static修饰

//模板设计模式 抽象类的实践？
//先别把注意力放在抽象类上 主要看这个模式
//编写方法计算代码耗时
//job
abstract class Templet {
    abstract void job();

    public void calculate()//把子类方法共同要做的拿到父类里
    {
        long startTime = System.currentTimeMillis();//开始时间
        job();
        long endTime = System.currentTimeMillis();//结束时间
        System.out.println(endTime - startTime);
    }
}

class A extends Templet {
    @Override
    public void job() {
        int sum = 0;
        for (int i = 0; i < 900000; i++) {
            sum += i;
            sum += i + 1;
        }
    }
}

class B extends Templet {
    @Override
    public void job() {
        int sum = 1;
        for (int i = 0; i < 999999; i++) {
            sum *= i + 1;
        }
    }
}

class Test1 {
    public static void main(String[] args) {
        B b = new B();
        A a = new A();
        a.calculate();//调用时会找到父类的calculate 然后到job时会动态绑定
        b.calculate();

    }
}

//好了开始骂抽象类
//上面这个模板设计模式不用抽象类一样可以继承重写实现
//所以抽象类到底有什么用呢
//抽象类相当于是一种规范,它里面的抽象方法规定了所有的子类中都必须有这些方法
//这是知乎的回答 就是一种规范 然后p用没有