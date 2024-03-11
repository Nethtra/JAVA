package chapter08;

//为什么需要继承
//两个类的属性和方法有很多是相同的 代码复用性很差
//继承可以解决代码复用 当多个类存在相同的属性和方法时 可以从这些类中抽象出父类 并在父类中定义这些属性和方法
//然后子类只需要通过extends关键字声明就可以获得父类的属性和方法 子类还可以拥有自己的特有属性和方法 也可以再有子类
//使用继承除了提高复用性还能提高扩展性
//语法
//class 子类名 extends 父类名{
// }

//关于继承的细节
//1父类的私有属性和方法在子类中不能直接访问 可以通过父类间接访问（属性用方法return 方法用父类调用）
//子类与父类在同一个包下，子类可以访问父类默认修饰符修饰的属性
//子类与父类不在同一个包下，子类不可以访问父类默认修饰符修饰的属性，但可以访问受保护的

//2只要是类都需要通过构造器初始化，子类构造器一般只管自己的属性初始化 那么在内存当中，父类那一块的属性并没有初始化
//所以子类必须调用父类的构造器以完成父类属性的初始化
//先别急接着看3当创建子类对象时不管使用子类的哪个构造器都会默认调用父类的无参构造器
//即子类构造器代码中有一行隐藏的super();
//如果父类没有无参构造器 则必须在子类构造器中用super来指定父类使用哪个有参构造器完成父类的初始化 否则编译不通过
//总结来说就是要完成初始化工作
//这是弹幕写的
//子类如果想初始化对象，就得先让父类初始化对象
//如果父类没有，或者只有无参构造器，子类不管调用无参/有参构造器，都得先调用父类的无参构造器
//如果父类有一个有参构造器，并且把无参构造器覆盖。而子类想初始化对象，就得在构造器里面写super.(父类的有参构造器)

//4关于super 只能放在子类构造器的第一行（言外之意super访问父类构造器时只能在子类构造器中使用（和this差不多））
//而this访问构造器时也是只能放在构造器的第一行 所以this和super不能存在一个构造器

//5java所有的类都是Object类的子类（都继承Object）
//所以调构造器时一直往上追溯到object自上而下初始化
//6不能滥用继承 子类和父类必须有逻辑上的关系比如cat类继承animal类 pupil类继承student类

//import jdk.nashorn.internal.ir.CallNode;


//继承的内存布局
//先来写几个类
class GrandPa {
    String name = "爷爷";
    String hobby = "旅游";
}

class Father extends GrandPa {
    String name = "爸爸";
    int age = 33;
}

class Son extends Father {
    String name = "儿子";
    private int age = 12;
}

//这里儿子类继承爸爸类再继承爷爷类
class Test {
    public static void main(String[] args) {
        Son son = new Son();
        //这一条执行时 会先向上找到Object类 自上而下加载类信息在方法区
        //再GrandPa初始化name和hobby 再Father初始化name和age 再Son初始化name和age 在堆空间
        //提醒一下 字符串在常量池都
        //这里故意继承的类都写name new对象时不会有影响 都会在堆空间完成初始化
        //然后我们来看调用
        System.out.println(son.name);//如果不是私有的话.name会从子类开始往上找
        //System.out.println(son.age); 当遇到private会阻塞 即使父类还有.age
        System.out.println(son.hobby);
        //至于son怎么访问爹和爷爷的name 我也不知道 反正就是只能访到一层
    }

}

//练习题
//1看图说话
class A {
    public A() {//4来到了这里输出
        System.out.println("我是A类");
    }
}

class B extends A {
    public B() {
        System.out.println("我是B类的无参构造器");
    }

    public B(String name)//3
    {//这里没写出来还有个默认super()去调a的无参构造器
        System.out.println("我是B类的有参构造器");//5
    }
}

class C extends B {
    public C()//程序从这里开始
    {
        this("hello");//1这里调用了c的有参构造器 而且因为第一行是this所以没了super
        System.out.println("我是C类的无参构造器");//7
    }

    public C(String name) {
        super("haha");//2我们说子类初始化必须先调父类构造器 这里有手动的super找b的有参构造器
        System.out.println("我是C类的有参构造器");//6
    }
}

//然后main方法中new了一个C对象 问输出什么
//C c=new C(); 调用无参构造器
class Test1 {
    public static void main(String[] args) {
        C c = new C();
    }
}


//super关键字 代表父类的引用 用于访问父类的属性 方法 构造器
//和this差不多 只不过this是本类 super是在子类访问父类
//访问父类属性和方法 但不能访问private属性和方法
//访问父类构造器 只能放在子类构造器第一句且只能有一句super
//（遵守访问修饰符规则）
//这时候我们回过头来看刚才写的三个类
class GrandPa1 {
    String name = "爷爷";
    String hobby = "旅游";

    public void hello() {
        System.out.println("d");
    }
}

class Father1 extends GrandPa1 {
    String name = "爸爸";
    int age = 33;

    public void hello()//在这里建一个方法
    {
        System.out.println("我是father的hello");
    }
}

class Son1 extends Father1 {
    String name = "儿子";
    private int age = 12;

    public void hello() {
        System.out.println("我是son的hello");
    }

    //要在子类访问父类hello方法的话
    public void hello1() {
        hello();
        this.hello();//这两个是等价的 从本类开始找 没有找到就往上继续 直到找到 被private阻塞或者没找到就报错
        //和上面说的差不多 只不过上面是new了对象的
        super.hello();//这个是从父类开始找
        //这样也能解决上面提出的问题怎么访问爹和爷爷的name
        //用super 但如果多级同名就只能就近访问
    }
    //属性也差不多这样访问规则
    //这里提炼出一点规则 就是当子类父类有重名属性或者方法时为了在子类访问父类的属性和方法必须通过super
    //但是没重名的话用super this 直接访 都是一样的 （因为会自动往上找）
}
//this和super的比较
//当访问属性和方法时 super比this高一类 都会往上继续查找
//当访问构造器时   this必须放在本类构造器首行来访问本类的构造器 super放在子类构造器首行来访问父类构造器
//当new了对象 this表示当前类的对象 super表示父类的对象


//方法重写override
//构成重写的条件
//首先两个方法要分别在父类和子类
//子类中方法的名称 形参列表 返回类型 和父类的方法的完全一样或者
//名称和形参列表一样 但是子类的方法的返回类型是父类方法的返回类型子类 比如子类string 父类object
//然后就是修饰符  子类的方法不能缩小父类的方法的访问范围

//与方法重载比较 重载只要求名一样 参数列表不一样
//        发生范围   方法名     形参列表     返回类型      修饰符
//重载      本类      相同       不一致       无关        无关
//重写     父子类     相同        一致        可以不一样   不缩小

//例题
//Person类包括属性 name age 和构造器 方法say来返回自我介绍的字符串
//Student类继承Person类 增加属性id score及构造器 say方法重写来返回自我介绍的字符串
//在main中分别创建两个类的对象 并调用say方法
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String say() {
        return "我叫 " + name + " 年龄 " + age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class Student extends Person {
    private int id;
    private int score;

    public Student(String name, int age, int id, int score) {
        super(name, age);
        this.id = id;
        this.score = score;
    }

    public String say() {
        return super.say() + " id " + id + " 成绩 " + score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

class Test2 {
    public static void main(String[] args) {
        Person 东雪莲 = new Person("东雪莲", 40);
        System.out.println(东雪莲.say());
        Student 丁真 = new Student("丁真", 21, 15, 100);
        System.out.println(丁真.say());
    }
}//两个say构成了方法重写