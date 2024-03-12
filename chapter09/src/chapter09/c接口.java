package chapter09;

//interface接口 给出一些没有实现的方法（抽象）封装到一起
//接口和类是同一级的
//接口中可以有抽象方法 静态方法（可以不加static） 普通方法（但需要default在最前修饰） 属性也可以有
//语法 interface 接口名{
// }
//类implements接入接口 必须实现接口中的抽象方法
//就像协议一样 接口中有协议 类接入接口就必须也实现协议
//我来看 类实现接口和子类继承父类差不多 但是实现接口必须实现接口的方法 而且方法名还不能改
//规范了开发    反过来看抽象方法 也可以用重写来实现 但是更规范
//应用场景   多人开发时可以都接入同一接口 这样规范了方法名 而且调用时可以写一个方法传入接口类型的对象
//相当于向上转型 然后在方法里用形参.出方法名 根据动态绑定就会调用接入接口的相应方法
//接口不能被实例化 接口类型可以指向实现了该接口的类的对象示例
//接口中的方法默认都是public所以不用写 抽象方法不用写abstract (因为接入接口的大多不在一个包内所以设计的人就干脆默认public)
//接口中的属性默认是 public static final(必须初始化)
//抽象类实现接口 可以不用实现接口的方法
//一个类可以实现多个接口 但是只能继承一个类
//接口可以继承接口 但不能继承类
//接口的修饰符和类一样 只能是public和默认
//接口是对java但继承机制的补充

//关于extends和implements
//继承解决代码的复用性
//接口设计规范并让其他类来实现这些方法
//从字面意思来讲 继承是与生俱来的 而接口是后天学习来的
class BigMonkey{
    private String name;

    public BigMonkey(String name) {
        this.name = name;
    }
    public void climb()
    {
        System.out.println("猴子爬树");
    }
}
interface Fish{
    void swim();
}
class LittelMonkey extends BigMonkey implements Fish{
    public LittelMonkey(String name) {
        super(name);
    }

    @Override
    public void swim() {
        System.out.println("猴子学会了游泳");
    }
}
class Test2{
    public static void main(String[] args) {
        LittelMonkey tom = new LittelMonkey("Tom");
        tom.swim();
        tom.climb();
    }
}

//接口的多态特性
//1多态参数
//可以把方法形参写为接口类型 然后此方法就可以接收实现了这个接口的类的对象 相当于接口的引用指向了类的对象 向上转型
//2多态数组
//接口类型的数组可以存实现了这个接口的类的对象
//例题：Usb数组中存放Phone和Camera对象 Phone类和Camera类实现Usb接口 Phone类特有的方法call
//main方法中遍历数组如果是Phone对象除了调用接口定义的方法外还要调用特有方法
interface Usb{
    void usb();
}
class Phone implements Usb{
    @Override
    public void usb() {
        System.out.println("usb2.0");
    }
    public void call()
    {
        System.out.println("我可以打电话");
    }
}
class Camera implements Usb{
    @Override
    public void usb() {
        System.out.println("usb3.2gen2");
    }
}
class Test3{
    public static void main(String[] args) {
        Usb[] usb=new Usb[2];//创建接口类型的数组
        usb[0]=new Phone();//这里相当于已经向上转型
        usb[1]=new Camera();
        for (int i = 0; i < usb.length; i++) {
            usb[i].usb();//这里会动态绑定
            if(usb[i] instanceof Phone)
                ((Phone)usb[i]).call();//别忘了向下转型
        }

    }
}

//接口多态传递
interface AA{
    void hello();
}
interface BB extends AA{}
class CC implements BB{
    @Override
    public void hello() {
    }
}
class Test4{
    public static void main(String[] args) {
        AA aa=new CC();
    }
}
//想不到吧还能这么玩 相当于CC也实现了AA 而且AA的引用还能指向CC

//贴一段知乎的回答
//抽象方法和接口定义的方法，你可以理解为是一种协议或者规范。
//比如我们是行业的达人，需要定义车的规范，它能启动、跑起来、刹车和停止，这4项功能就是抽象方法。
//至于用什么引擎启动、跑地多快、怎么刹车，我们就不想管了，你爱怎么实现是你的事，
//但你必须实现我们定义好的抽象方法，否则你生产的就不是车子了。
