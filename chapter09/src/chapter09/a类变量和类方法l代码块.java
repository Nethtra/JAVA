package chapter09;

//类变量（静态变量）              （普通变量是每个对象独享）
//用static修饰的变量 在类加载时生成 当在类中定义静态变量时，该类的所有对象实例会共享这个变量，即使没有创建对象
//可以通过对象名.静态变量或是类名.静态变量访问  依然遵循访问修饰符的范围
//语法 把static写在访问修饰符和数据类型中间
//生命周期从类加载开始到类的消亡
//什么时候需要类变量
//当要让某个类的所有对象共享一个变量时

//类方法（静态方法） 主要作用是可以不用new对象就用类名.调用方法 把方法当成工具使用
//语法 在修饰符和返回类型间加static
//注意也要满足修饰符的范围 这是前提
//细节
//类方法和普通方法都是随着类的加载而加载
//类方法中不能使用与对象有关的关键字比如this和super 原因就是还没有实例化对象
//静态方法中只能访问静态方法或静态变量
//非静态方法可以访问静态成员或非静态成员

//main方法
//main方法由Java虚拟机调用 所以必须是public 调用时不必创建对象 所以是static
//参数String[] args 字符串数组 可以通过cmd传入 idea右上角也能传program arguments
//在main中可以直接调用main所在类的静态成员 但是不能直接访问类中的非静态 得先new对象后才可以访问

//代码块
//语法 修饰符{}; 修饰符只能是static或者没有
//最后的;可以省略
//static代码块在类加载时被调用 因为类加载只会一次 所以static代码块只能执行一次
//普通代码块在创建对象时被调用 new一个对象就会被调用一次 调用时机在构造器之前
//只使用类的静态成员时 普通代码块并不会执行 因为还没有创建对象
//可以把普通代码块理解为特殊的构造器 只是发生在构造器之前而已
//总的来说要记住调用的时机
//作用
//如果多个构造器中有重复的语句 就可以拿出来放到代码块中
//类什么时候被加载
//new对象时
//创建子类的对象时 父类也会被加载 所以父类代码块一样会被调用
//使用静态成员时
//类加载的顺序
//1调用静态代码块和静态属性初始化 同时存在时看顺序
//（创建对象时才有23）
//2调用普通代码块和普通属性初始化 同时存在时看顺序
//3才是调用构造器
//关于继承时类加载的顺序
//其实构造器除了藏了个super还在super后隐含的调用普通代码块
//所以代码块优先于构造器 所以父类优先于子类加载
//顺序
//1父类的静态代码块和静态属性
//2子类的静态代码块和静态属性
//（创建对象时才有3456）
//3父类的普通代码块和普通属性
//4父类的构造器
//5子类的普通代码块和普通属性
//6子类的构造器
//总之就是静态优先 然后先父后子

//静态代码块只能调静态成员 普通代码块能调所有成员 （和静态方法差不多）

//静态变量和静态方法应用场景
//单例设计模式（一个类只创建一个对象）：  饿汉式  懒汉式
//步骤
//构造器私有化（防止外部new）
//类的内部创建对象
//向外暴露静态的公共方法返回对象

//饿汉式 在类加载时就创建唯一的对象
class GirlFriend {
    private String name;
    private static GirlFriend girlFriend = new GirlFriend("小明");//创建唯一对象 修饰为static
    //因为要用静态方法返回 所以必须static

    private GirlFriend(String name) {
        this.name = name;
    }

    public static GirlFriend getInstance()//如果不用static就必须得new对象才能调 所以必须static
    {
        return girlFriend;
    }

    @Override
    public String toString() {
        return "GirlFriend{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Test {
    public static void main(String[] args) {
        System.out.println(GirlFriend.getInstance());
        System.out.println(BoyFriend.getInstance());
    }
}

//懒汉式 new对象时才创建唯一的对象
class BoyFriend {
    private String name;
    private static BoyFriend boyFriend;//先创建个引用 不要new先

    private BoyFriend(String name) {
        this.name = name;
    }

    public static BoyFriend getInstance()//只有调用get方法时才会创建对象
    {
        if (boyFriend == null)
            boyFriend = new BoyFriend("小明");
        return boyFriend;
    }

    @Override
    public String toString() {
        return "BoyFriend{" +
                "name='" + name + '\'' +
                '}';
    }
}
//区别在于创建对象的时机不同 饿汉在类加载 懒汉在new对象
////饿汉式存在浪费资源问题 懒汉式存在程安全问题

//final关键字位置和static一样 可以修饰类 属性 方法 局部变量
//修饰类时该类不能再被继承
//修饰父类的方法时在子类中不能再重写
//修饰属性时属性不能再被修改
//修饰局部变量时局部变量不能再被修改 此时也称为局部常量一般用XX_XX_Y命名
//final修饰的属性必须初始化
//可以在1定义时2代码块中3构造器中初始化
//但是如果属性被static和final同时修饰 就不能在构造器中初始化 原因也很简单 static必须在类加载中完成 但构造器需要new对象先
//final不能修饰构造器
//final和static搭配修饰属性时 调用属性时不会导致类加载 效率更高
//包装类（就是为八种基本数据类型创建对象而提供的一种类）都是final类还有String类也是