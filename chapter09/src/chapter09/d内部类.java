package chapter09;

//一个类的内部完整嵌套了另一个类 被嵌套的类叫内部类 嵌套的类叫外部类
//内部类的分类
//局部内部类 匿名内部类（这两个在方法体或者代码块中相当于局部变量）
//成员内部类 静态内部类（这两个在外部类成员位置相当于成员）


//局部内部类  本质还是类
//定义在方法或者代码块中 有类名 作用域也是方法或者代码块
//不能添加访问修饰符 可以用final
//地位相当于一个局部变量 外部其他类不可以访问
//外部类和内部类成员重名时 就近  如果想访问外部类重名成员可以类名.this.成员


//匿名内部类 AnonymousInnerClass 还是内部类 类 没有名字 同时还是一个对象
//语法 类或接口 引用=new 类或接口(参数列表){类体};  好好看语法 首先这就是创建对象多了个{}
//然后有接口的和类的这两种匿名内部类  既有创建对象的特征 又有定义类的特征
//**本质还是接口的实现或者类的继承** 只不过在类的内部完成
//即class 匿名内部类 extends 父类
//引用的编译类型是左边 运行类型就是匿名内部类 可以用对象.getClass()查看对象的类
//匿名内部类就是你写的类体 类名是外部类加上$1 放在体内某个地方了 但是看不到
//当如上实现语法后 系统就会立即创建对象并返回地址给左边的引用 所以运行类型是匿名内部类
//由于动态绑定 所以.方法就会调用你定义的匿名内部类的方法
//所以在实现语法时也创建了对象 说同时也是一个对象
//匿名内部类只能使用一次 但留下来的对象可以一直用
//注意语法也可以new 类或接口={}.方法;这样
//然后就是注意和上面一样不能加访问修饰符 地位也是局部变量 作用域  外部其他类访问不了  外部类和匿名内部类重名时的顺序
//作用 当写的类只调用一次时 这样太浪费了 就可以用匿名内部类
//实践
//1当作实参直接传递
//仔细想
class Test5{
    public static void main(String[] args) {
//接口用匿名内部类实现后直接当作对象传入f1
        f1(new AAA() {
            @Override
            public void show() {
                System.out.println("面向对象好tm抽象");
            }
        });//括号里就是对象

    f1(new Mnmlgbdx());

    }
    public static void f1(AAA x)
    {
        x.show();
    }
}
interface AAA{
    void show();
}

//可以来想想正常写怎么写（硬编码）
class Mnmlgbdx implements AAA{
    @Override
    public void show() {
        System.out.println("好好好");
    }
}

//2
//铃声接口Bell里面有个ring方法
//手机类Cellphone有闹钟方法alarm 参数是Bell类型
//测试手机的闹钟功能 用匿名内部类作为参数 打印不同的闹钟内容
interface Bell{
    void ring();
}
class Cellphone{
    public void alarm(Bell bell)
    {
        bell.ring();
    }
}
class Test6{
    public static void main(String[] args) {
        Cellphone cellphone1 = new Cellphone();//new完对象然后调方法 时用匿名内部类
        cellphone1.alarm(new Bell() {
            @Override
            public void ring() {
                System.out.println("雷达");
            }
        });
        Cellphone cellphone2 = new Cellphone();
        cellphone2.alarm(new Bell() {
            @Override
            public void ring() {
                System.out.println("三全音");
            }
        });
        cellphone1.alarm(new Bell() {
            @Override
            public void ring() {
                System.out.println("???");
            }
        });
    }
}

//成员内部类
//定义在外部类的成员位置 地位相当于一个成员 所以可以加访问修饰符 但是不能加static
//作用域是整个类体
//外部类访问需要先创建对象
//外部其他类访问成员内部类
//法1 外部类的对象new了内部类的对象
class Outer{
    public int n1;
    class Inner{
        public void f()
        {
            System.out.println("hello");
        }
    }

    public Inner r()
    {
        return new Inner();//
    }
}
class Test7{//外部其他类
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();//外部类的对象new了内部类的对象
        inner.f();
        //法2 外部类方法返回成员内部类的对象
        Outer.Inner inner1=outer.r();
        inner1.f();
    }
}
//外部类和内部类成员重名时就近 然后想访问外部类的话 用外部类名.this

//静态内部类
//也是在外部类的成员位置 且有static修饰
//可以访问外部类的所有静态成员 但是不能访问非静态成员
//地位是一个成员 可以加访问修饰符
//作用域是整个类体
//外部其他类访问静态内部类
class Outer1{
    static class Inner1{
        public void f1()
        {
            System.out.println("hh");
        }
    }

}
class Test8{
    public static void main(String[] args) {
        //法1 外部类直接new内部类对象
        Outer1.Inner1 inner1 = new Outer1.Inner1();//因为Inner1是静态的所以不需要通过对象来访问
        //法2 外部类方法返回静态内部类对象
        //如果这个方法还是静态的话那也不用先new外部类对象 上面也 一样
    }
}
//关于重名的问题 只不过这次用外部类名.成员（不用this了）