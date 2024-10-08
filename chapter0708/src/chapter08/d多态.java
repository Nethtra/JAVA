package chapter08;
//Polymorphism
//多态

//多态前提
//两个类存在继承关系
//有父类引用指向子类对象
//父类一定要有重写的方法
//首先知道编译类型看=左边 运行类型看=右边 一个对象的编译类型和运行类型可以不一样
//例如Animal类是Dog的父类
//Animal animal=new Dog();  编译类型是Animal运行类型是Dog
//其实animal就是一个对象的引用  **可以把父类的引用指向子类的对象** 这个dog对象可以是Animal也可以是Dog
//好处
//提高了程序扩展性 定义方法的时候使用父类型作为参数 在使用时可以传入具体的子类型 减少重载的次数
//例子就是比如master类  animal类 food类
//animal类和food类都有很多子类  在master类里重载多个方法 参数列表是animal和food的子类
//就可以只写一个方法 参数列表是animal类和food类 不用再每个子类写一遍


//多态的向上转型
//父类的引用指向了子类的对象 语法：父类类型 引用名=new 子类对象
//用父类的引用可以调用父类有的方法和成员变量，都可以调用，（遵守访问修饰符的前提下）如果被子类重写了，则会调用子类的方法(动态绑定)。
//父类没有的方法，而子类存在，即子类的特有成员不能调用（重写不算特有）
//向上转型只对方法有影响，对属性没影响。属性不存在重写。


//想要调用子类的特有成员 就引出了向下转型
//语法 子类类型 引用名（新）=（子类类型） 父类引用     其实就是强制转换
//要求父类的引用原来的指向必须是当前目标类型的对象**
//这样就可以用新的引用名来调用子类类型的成员


//向下转型是为了可以拿到子类的方法，向上转型是为了可以调用父类成员和子类重写的方法

//比较运算符instanceof 返回true或者false
//用于判断一个具体的对象（左边）的运行类型是否属于（右边）的类或者他的子类

//Java动态绑定机制**
//动态绑定是指在执行期间（非编译期）判断所引用对象的实际类型，根据其实际的类型调用其相应的方法。
//Java中只有static，final，private和构造方法，以及成员变量属于静态绑定，其他的都属于动态绑定。动态绑定看运行类型，静态绑定看编译类型
//动态绑定是实现多态的原因
//当调用对象除上述方法时，方法会与对象的运行类型绑定（内存地址）
//https://blog.csdn.net/weixin_43465312/article/details/101542326
//由于Java动态绑定机制，当对象向上转型时，用父类的引用调用重写方法时，会动态绑定到运行类型子类，所以可以调到子类的重写方法，子类的特有方法调不到。调用属性时，没有动态绑定，所以看编译类型，调用自己的成员。当向下转型时相当于和普通创建对象一样，可以调用子类的方法
//通过上下转型可以灵活的调用子类和父类的不同成员
//用一道题来讲***
class A1 {
    public int i = 10;
    int t = 1;

    public int sum() {
        return getI() + 10;
    }

    public int sum1() {
        return i + 10;
    }

    public int getI() {
        return i;
    }

    public int test() {
        return 1;
    }
}

class B1 extends A1 {//B1继承了A1类 成员名全部相同
    public int i = 20;

    //    public int sum() {
//        return i + 20;
//    }
//    public int sum1()
//    {
//        return i+10;
//    }
    public int getI() {
        return i;
    }

}

class Test3 {
    public static void main(String[] args) {
        A1 a = new B1();//向上转型
        //先来看输出
        System.out.println(a.sum());//40
        System.out.println(a.sum1());//30
        B1 b = (B1) a;
        System.out.println(b.test() + b.t);
        //如果我们注释掉两个方法 结果就是30和20
        //比如调a.sum 方法会与a的运行类型B1绑定  所以geti 就会用B1里的
        //相反a.sum1 属性没有动态绑定 所以i用的还是a的
        //我觉得还是因为同名的问题引出的重写
    }
}

//多态的应用

//多态数组 数组定义为父类类型 保存的元素是子类类型
//也是用题来讲
//要求有一个继承结构并创建一个Person1对象 2个Student1对象 2个Teacher对象
//放在同一数组中 并调用say(返回String)方法 和学生和老师的特有方法
//Person1有name和age属性  Student1多了score属性和study方法  Teacher多了salary属性和teach方法

class Person1 {
    private String name;
    private int age;

    public Person1(String name, int age) {
        this.name = name;
        this.age = age;
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

    public String say() {
        return "姓名 " + name + " 年龄 " + age;
    }
}

class Student1 extends Person1 {
    private double score;

    public Student1(String name, int age, double score) {
        super(name, age);
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String say() {
        return super.say() + " 成绩 " + score;
    }

    public void study() {
        System.out.println("学生 " + getName() + "正在study");
        //这里因为name私有 所以得用get方法获取  这也体现了get方法的作用
    }
}

class Teacher extends Person1 {
    private double salary;

    public Teacher(String name, int age, double salary) {
        super(name, age);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String say() {
        return super.say() + " 薪水 " + salary;
    }

    public void teach() {
        System.out.println("老师 " + getName() + "正在教Java");
    }
}

class Test4 {
    public static void main(String[] args) {
        Person1[] person = new Person1[5];//因为父类引用可以指向子类对象 所以数组也可以放子类
        person[0] = new Person1("张三", 21);
        person[1] = new Student1("昊京", 25, 0);
        person[2] = new Student1("益西", 18, 100);
        person[3] = new Teacher("丁真", 21, 10000);
        person[4] = new Teacher("孙笑川", 21, 0);
        for (int i = 0; i < person.length; i++) {
            //Person1是编译类型  运行类型每一个元素都不一样

            System.out.println(person[i].say());//动态绑定运行类型
            //因为study和teach是子类特有方法 所以用父类引用直接.是.不到的
            //考虑怎么实现
            if (person[i] instanceof Student1)//巧用instanceof
                ((Student1) person[i]).study();//然后向下转型
            else if (person[i] instanceof Teacher)
                ((Teacher) person[i]).teach();
        }

    }
}

//多态参数 方法定义的形参类型为父类类型 实参类型允许为子类类型
//也是来看题
//定义员工类Employee， 包含姓名和月工资，以及计算年工资getAnnual的方法
//普通员工和经理继承了员工，经理类多了奖金bonus厲性和管理manage方法
//普通员工类多了work方法，普通员工和经理类要求分别重写getAnnual方法
//测试类中添加一个方法showEmpAnnal(Employee e)，实现获取任何员工对象的年工资
//井在main方法中调用该方法测试类中添加一个方法testWork
//如果是普通员工，则调用work方法，如果是经理，则调用manage方法
class Employee {
    private String name;
    private double salary;

    public double getAnnual()//计算一年工资
    {
        return salary * 12;
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

class Worker extends Employee {
    public Worker(String name, double salary) {
        super(name, salary);
    }

    public void work() {
        System.out.println("工人 " + getName() + " 正在打工 ");
    }

    @Override
    public double getAnnual() {
        return super.getAnnual();
    }
}

class Manager extends Employee {
    private double bonus;

    @Override
    public double getAnnual() {//经理的要加上奖金
        return super.getAnnual() + bonus;
    }

    public void manage() {
        System.out.println("经理 " + getName() + "正在manage");
    }

    public Manager(String name, double salary, double bonus) {
        super(name, salary);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}

class Test5 {
    public static void main(String[] args) {
        Worker tom = new Worker("tom", 10000);
        Manager mary = new Manager("mary", 20000, 340000);//注意这里没有向上转型
        Test5 test5 = new Test5();

        test5.showEmpAnnal(tom);
        test5.showEmpAnnal(mary);
        test5.testWork(tom);
        test5.testWork(mary);
    }

    public void showEmpAnnal(Employee e)//传进来的是worker和manager但是接收他的却是它们的父类Employee，接受的时候已经相当于帮你自动向上转型了
    {//这里相当于已经向上转型 e就是
        System.out.println(e.getAnnual());//已经动态绑定了运行类型
    }

    public void testWork(Employee e) {//它传进来的的确是 worker 和manager
        // 但是接收他的却是它们的父类Employee，接受的时候已经相当于帮你自动向上转型了
        // 因为work和manage都是特有方法 所以用的时候需要向下转型
        if (e instanceof Worker)
            ((Worker) e).work();
        else if (e instanceof Manager)
            ((Manager) e).manage();
    }

}