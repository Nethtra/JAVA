package chapter07;

//构造方法
//主要作用是用来在创建对象时 就直接 完成对象的初始化
//本质还是一个特殊的方法
//规则
//没有返回值也不能写void 可以有访问修饰符
//方法名和类名必须一致
//在创建对象时 如果类有构造器 系统会自动调用该类的构造方法  不能用.自己调用
//构造器可以重载 其实就是参数列表不一样
//没有定义构造器时 系统会自动给类生成一个无参构造器 大约是
//Dog()
//{}
//所以你看在new的时候无论怎么样都会带个()
//但是一旦自己定义了构造器 这个默认的就会被覆盖 想用的话需要再显式定义一下 就像下面
class Dog{
    String name;
    int age;

    public Dog(String name1,int age1)//这里形参的名与成员变量区别  如果重名的话 相当于形参给自己赋 所以属性还是没变 这样就引出了this
    {
        name=name1;//给属性赋值
        age=age1;
    }
    public Dog(String name1)//重载
    {
        name=name1;
        age=1;//这样每次这样创建对象时年龄会默认
    }
    public Dog()//显示定义默认的无参构造器
    {

    }
}

class Constructor{
    public static void main(String[] args) {
        Dog dog1=new Dog("丁真");
        Dog dog2=new Dog("孙笑川",18);
        Dog dog3=new Dog();//无参构造器
    }
}

//回过头来我们再看对象的创建流程
//Person p=new Person("丁真",18);
//首先在方法区加载Person类信息
//然后在 堆 里分配空间 得到一个地址
//再就是初始化  默认初始化 null和0 如果成员变量指定了值则再进行显式初始化
//然后是构造器初始化 在常量池开辟空间存放“丁真” 得到一个地址 堆中的对象的name存放这个地址（指向它）
//最后把对象的地址赋给p这个引用（p指向堆里的对象）
//注意p是一个引用或者说是个对象名 真正的对象在堆里 就像人名和人的关系


//this关键字 用来解决刚才说的构造器形参命名问题
//java虚拟机会给每个对象分配this,代表当前对象 哪个对象调用this就代表哪个 所以this也可以访问本类的属性 方法 构造器
//关于this访问构造器 this(参数列表); this访问构造器只能在构造器中使用 而且必须放在第一条语句
//this只能在类定义的方法中使用 不能在外部使用


class Person {
    String name="a";
    int age=0;

    public Person(String name, int age) {//this.name就代表当前对象的属性
        this.name = name;//实际上创建对象时还有一个this属性存着对象的地址 所以用的是this.name
        this.age = age;
    }

    public Person()//另一个构造器
    {
        this("理塘",5000);//在这个构造器访问上一个
    }
    //this还可以实现复用构造器  和这个差不多比如第一个构造器要初始化两个第二个要初始化三个第三个要初始化五个
    //那么第三个构造器就可以复用第二个然后只用再初始化剩下两个就行了
    public void Constructor()//一个演示this作用的方法
    {
        String name="b";//一个局部变量
        System.out.println(this.name+this.age);//this访问的是对象的属性
        System.out.println(name+age);//这里访问的name就是局部变量
    }//其实this.name 和name有区别的情况就在有局部变量或者形参与属性重名的时候
    //没有局部变量时访问的都是该对象的属性
}

//做道题
//定义一个类有name和age属性 并提供compareTo方法用于判断是否和另一个人相等返回boolean
class Person1{
    String name;
    int age;
    public Person1(String name,int age)
    {
        this.name=name;
        this.age=age;
    }
    public boolean compareTo(Person1 p)
    {
        return this.name.equals(p.name)&&this.age==p.age;
    }
}
class ThisExercise{
    public static void main(String[] args) {
        Person1 p1=new Person1("丁真",20);
        Person1 p2=new Person1("孙笑川",100);
        System.out.println(p1.compareTo(p2));//哪个对象调用this就代表哪个
    }
}
