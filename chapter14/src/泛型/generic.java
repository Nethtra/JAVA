package 泛型;

import java.util.*;

/**
 * @author 王天一
 * @version 1.0
 */
//引出泛型
//1集合中元素的类型有很多 当遍历时需要进行分别转型 如果数据量大时 对效率有很大影响
//2不能对加入集合中的数据类型做约束 不安全
//来看一个小例子
class Test0 {
    public static void main(String[] args) {
        //泛型就是用来指定集合存入对象的类型 这里指定Cat类
        ArrayList<Cat> cats = new ArrayList<Cat>();
        cats.add(new Cat("Chocola", 3));
        cats.add(new Cat("Vanilla", 3));
        //然后我们来遍历
        Iterator<Cat> iterator = cats.iterator();
        while (iterator.hasNext()) {
            //Cat cat = (Cat) iterator.next();//这里不知道为什么拿出来还是Obj 知道了 上面因为迭代器的泛型没有指定类型，所以默认是Obj
            //指定为Cat就不需要强转
            Cat cat = iterator.next();
            System.out.println(cat.getName() + " " + cat.getAge());
        }
        for (Cat cat : cats) {//增强for就可以用Cat接收 这样就不再需要像以前一样先向上转成Obj
            System.out.println(cat.getName() + " " + cat.getAge());
        }

    }
}

class Cat {
    private String name;
    private int age;

    public Cat(String name, int age) {
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
}

//这样看来泛型的好处
//在编译时检查元素类型 提高安全性
//减少类型转换次数 提高效率
//jdk5中加入泛型 目的是解决数据安全性问题

//语法<E> E代表数据类型 而且只能是引用类型 不能是基本数据类型
//在类声明通过<>表示类中属性的类型或者方法的返回类型或者参数的类型 在实例化时才指定该类型
//泛型可以在类使用 也可以在接口使用 最多三个比如<K,V,T>
//使用泛型的数组不能初始化 因为不知道大小
//静态成员不能使用泛型
//当接口使用泛型时 要在继承接口或者实现接口时指定泛型类型
//还有就是接口中的属性都是静态 所以不能用泛型
//再注意一点 使用泛型但没有指定类型时 默认是Obj
class Person<E> {//现在E是一个类型 但是没有指定
    E a;//属性

    public Person(E a) {//参数
        this.a = a;
    }

    public E b()//返回
    {
        return a;
    }
}

class Test1 {
    public static void main(String[] args) {
        Person<String> person1 = new Person<String>("现在在new对象时指定泛型的类型");
        Person<Integer> person2 = new Person<Integer>(1);
        System.out.println(person1.getClass());
        System.out.println(person2.getClass());//运行类型都是Person
    }
}

//我的理解 在类定义时使用泛型  而在实例化时指定类型  这样可以约束每一实例化后该对象中的一些方法和属性的类型
//对于集合来说 Set Map等接口在类定义时都使用了泛型
//而如果在new集合时指定类型 从而改了集合对象中的方法 影响存入元素的类型等 表现在外在就是指定了集合中对象的类型
//对于普通类的对象 好像也是这样
//说简单点可以把E看成形参 每次new对象时都会传入  从而能形成一种约束


//再来做道题
//创建三个学生对象 放入到HashMap中 要求key是String name   value就是学生对象 然后使用两种方式遍历
class Test2 {
    public static void main(String[] args) {
        HashMap<String, Student> hashmap = new HashMap<>();//这里的泛型指定了kv的类型
        hashmap.put("tom", new Student("tom", 3));
        hashmap.put("jack", new Student("jack", 18));
        hashmap.put("david", new Student("david", 10));
        //1使用entrySet
        Set<Map.Entry<String, Student>> entryset = hashmap.entrySet();
        //下面是entrySet的源码 因为返回类型就是这一堆 所以这样也指定了类型
//        public Set<Map.Entry<K,V>> entrySet() {
//            Set<Map.Entry<K,V>> es;
//            return (es = entrySet) == null ? (entrySet = new HashMap.EntrySet()) : es;
//        }
        Iterator<Map.Entry<String, Student>> iterator = entryset.iterator();
        //这个同理 指定了迭代器取出的类型
        while (iterator.hasNext()) {
            Map.Entry<String, Student> entry = iterator.next();
            //System.out.println(entry);//还能这样
            System.out.println(entry.getKey());
        }
    }
}//我觉得总结出的就是 看到有要返回对象的 或者用引用来接收的  就注意一下类的泛型

class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
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

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

//细节
//在指定泛型的具体类型后 可以传入该类型或者其子类类型
//关于泛型的写法   可以List<Integer> list=new ArrayList<>();即右边省略 而且编译器.var默认就是这么干的  要注意的是<>左右必须是同种类型
//当类定义时有泛型但实例化时未指定具体类型时  默认指定为Object 这也是上一章讲集合说能放Obj的原因
//而且所有集合的类定义都有泛型  所以我觉得集合的初衷就是想让你来指定这是个什么类型的集合 而不是可以放所有类型元素
//所以这么下来 迭代器 entrySet什么的也有泛型 初衷也不是返回obj  而是要指定类型
//再讲一点 K V M这些其实叫通配符
//<?>无界通配符            传入任意泛型类型
//<? extends E>上界通配符  只能传入E或者E的子类
//<? super E>下界通配符    只能传入E或者E的父类
//这三个通配符主要是在参数传递时起作用  比如用来限制集合的类型 (List <? extends E> list)

//例题
//定义Employee类 包含private属性name sal birthday(MyDate类的对象)
//MyDate类包含 year month day
//创建Emp类的三个对象并放入ArrayList集合中（使用泛型） 对集合中的元素进行排序 调用ArrayList的sort方法 使用比较器
//先按照name排序 如果name相同就按照birthday的先后顺序排序  最后输出
class MyDate implements Comparable<MyDate> {//Comparable就是内部比较器 compare方法在内部实现
    private int year;
    private int month;
    private int day;

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    //因为输出Emp时也会输出birthday 所以这里也要重写一下
    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

    @Override
    public int compareTo(MyDate o) {//这里实现compare方法
        if (year - o.getYear() != 0)
            return year - o.getYear();
        //=0就说明年相同 我们不返回 后面继续比月
        if (month - o.getMonth() != 0)
            return month - o.getMonth();
            //比日
        else
            return day - o.getDay();
    }
}

class Employee {
    private String name;
    private double sal;
    private MyDate birthday;

    public Employee(String name, double sal, MyDate birthday) {
        this.name = name;
        this.sal = sal;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "\nEmployee{" +
                "name='" + name + '\'' +
                ", sal=" + sal +
                ", birthday=" + birthday +
                '}';
    }
}

//现在两个类都定义好了
class Test3 {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("niko", 1000, new MyDate(1980, 10, 1)));
        employees.add(new Employee("cj", 20000, new MyDate(1978, 6, 1)));
        employees.add(new Employee("cj", 900, new MyDate(1978, 1, 19)));
        //调用ArrayList的sort方法 并指定一个规则
        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee emp1, Employee emp2) {
                if (emp1.getName().compareTo(emp2.getName()) != 0)//比名字 如果名字不同时
                    return emp1.getName().compareTo(emp2.getName());

                //这个Comparator其实叫外部比较器 即定义在类的外部 再新建一个类来实现这个方法
                //而下面是对生日的比较 显得冗余 我们可以用内部比较器使其更简洁
                //就是现在已经拿到对象了 不需要再...

//                //等于0时就是名字相同 这时不能返回 我们继续比生日
//                if(emp1.getBirthday().getYear()-emp2.getBirthday().getYear()!=0)
//                    return emp1.getBirthday().getYear()-emp2.getBirthday().getYear();
//                //=0就说明年相同 我们不返回 后面继续比月
//                if(emp1.getBirthday().getMonth()-emp2.getBirthday().getMonth()!=0)
//                    return emp1.getBirthday().getMonth()-emp2.getBirthday().getMonth();
//                //比日
//                else
//                    return emp1.getBirthday().getDay()-emp2.getBirthday().getDay();

                //现在我们把birthday的比较封装到了MyDate中 更加简洁
                return emp1.getBirthday().compareTo(emp2.getBirthday());


            }
        });
        System.out.println(employees);
    }
}

//刚才讲了类和接口的泛型 其实还有方法的泛型
//但要跟使用类的泛型的方法区分开
//先讲一些定义
//泛型方法可以定义在普通类和泛型类中
//当泛型方法被调用时才会确定类型
//语法或者标志就是 访问修饰符后有<> 也是确定泛型方法和使用类的泛型的方法的标志
//泛型方法可以和类的泛型搭配使用
class A<E> {
    E a;

    public <T, K> void method(T t, K k) {
        //这个就是泛型方法
    }

    public void method1(E e) {
        //而这个是使用的类的泛型的方法
    }

    public <U, M> void method2(U u, E e, M m) {
        //他们也可以搭配使用
    }
}

//这个与使用类的泛型的方法的区别就是 类的泛型需要在new对象时确定 而且一个对象就一次
//而泛型方法是调用一次就可以指定新的类型 更加灵活一点
class Test4 {
    public static void main(String[] args) {
        A<String> a = new A<>();
        a.method("调用方法随便指定类型", 1);
        a.method1("new对象时已经指定 只能传String");
        a.method2(1, "类的泛型", 1.1);
    }
}