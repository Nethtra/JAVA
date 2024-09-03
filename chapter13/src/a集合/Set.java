package a集合;

import java.util.HashSet;
import java.util.Objects;

/**
 * @author 王天一
 * @version 1.0
 */
//Set接口
//添加和取出的顺序不一致（但是不管取多少次 每次取的顺序都是一样的）
//不能添加重复元素（添多了会自动忽略） 可以有null（但是也只能一次）
//没有索引 也没有get方法
//
//常用方法 因为继承Collection所以基本一样 add remove
//遍历可以用迭代器和增强for但是普通for用不了 因为虽然有size方法 但是没有索引和get方法
//
//来讲HashSet
//HashSet的底层是HashMap HashMap的底层是数组＋链表＋红黑树
//所谓数组+链表+红黑树 就是HashMap$Node类型的数组中（通常叫table）每一个索引位置的Node对象都可以再链接下去 当table扩展到一定程度时 就会产生红黑树
//我们来先模拟一下HashSet的底层（其实就是HashMap）
class Node1 {//先定义一下节点类型
    Object item;
    Node1 next;

    public Node1(Object item) {
        this.item = item;
    }
}

class Test10 {
    public static void main(String[] args) {
        //创建Node类型的数组 一般命table
        Node1[] table = new Node1[16];//先建16个
        Node1 hello = new Node1("hello");//创建node
        table[2] = hello;
        Node1 world = new Node1("world");//然后再来一个
        hello.next = world;//现在接上 注意world索引还是在2 理解为横向增长
        //**可以debug看一下table的内容** 这样就形成了数组＋链表
    }
}

//再来看HashSet添加元素是怎么实现的
//当HashSet添加元素时 会先得到元素的hash值（用到HashCode）然后转换成索引值
//根据索引将元素放入table表 如果这个索引位置没有元素就直接放入
//有的话就调equals(这个equals是元素所在的类的equals)（可以重写equals指定比较的内容）在这一层链表中比较 有相同就放弃添加 不同就添加到这一层链表的最后位置
//这也是为什么HashSet中不能有相同元素的原因
//在Java8里如果一条链表的节点达到TREEIFY_THRESHOLD（默认为8）且table大小达到64 则table会被树化
//扩容resize()
//第一次添加元素时table会扩容到16  临界值（threshold）为16*加载因子（loadFactor）=12
//即如果table使用到了12就会扩容到16*2=32 新的临界值为24
//关于扩容：这个临界值是size的临界值即节点的数量，而size在每添加一个Node就会++ 所以并不是要占满12个索引再括 而是添加了12个Node就括 即使他们在一个索引的链表上
//还有就是上面说的一个链表长度到了8再往这个链表加的时候会调用树化方法 而树化方法上来就是判断table到没到64,如果没到64,即使没到现在的临界也会先扩容table到64，这样才能完成树化
//还有就是注意扩容的时候计算的hash值没有变化，但是因为table长度变了，所以计算的索引也会变化
//这源码太他妈抽象了
//p522讲的是添加的源码523我不知道讲的是什么524验证了添加扩容和树化525讲了扩容的条件


//做道题
//定义Employee类包含private属性name sal birthday(MyDate类型包括year month day)
//要求创建三个Employee放入HashSet中 当name和birthday相同时认为是同一个员工 不能添加到HashSet中
class Employee {
    private String name;
    private double sal;
    private MyDate birthday;//birthday是MyDate类型

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

    //当HashSet添加元素时会调用对象所在类的HashCode来算哈希值并转换成索引值用来判断索引位置 会调用对象所在类的equals来判断是否为同一个元素决定是否添加
    //当使用自定义类时，也要满足Set不能添加重复元素的条件
    //所以我们判定为同一人时 既要返回相同的哈希值即索引位置一样  又要让equals确定为同一元素 就要在自定义类中重写这两个方法
    //快捷键alt insert重写 选择要判断的属性
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return name.equals(employee.name) &&
                birthday.equals(employee.birthday);//注意这里调了birthday的equals
        //而我们MyDate类的equals并没有被重写 所以equals会返回不同从而判断失败
        //所以我们还要到MyDate里重写equals
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday);//看源码
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", sal=" + sal +
                ", birthday=" + birthday +
                '}';
    }
}

class MyDate {
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

    //重写Date类的equals和HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyDate myDate = (MyDate) o;
        return year == myDate.year &&
                month == myDate.month &&
                day == myDate.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}

class Test11 {
    public static void main(String[] args) {
        HashSet hs = new HashSet();
        hs.add(new Employee("丁真", 9000, new MyDate(1945, 8, 10)));
        hs.add(new Employee("张三", 0, new MyDate(2023, 2, 6)));
        hs.add(new Employee("丁真", 18, new MyDate(1945, 8, 10)));
        System.out.println(hs);//看到效果就不会被重复添加
    }
}
//这么看来如果套了好几个类的话 所有类都得重写HashCode和equals


//LinkedHashSet
//是HashSet的子类
//底层为LinkedHashMap（HashMap的子类） 底层维护了数组＋双向链表
//不能添加重复元素
//添加和取出顺序相同即有序 但添加的时候还是要根据hash值算索引再添加 只不过每一次添加就会把节点链接起来 所以取出时有序
//其实添加元素是用的还是 HashMap的方法
//但有几点不同  LinkedHashMap底层维护的table是HashMap$Node[]类型的
//但是存放的节点是 LinkedHashMap$Entry类型的
//这个HashMap$Node是HashMap的一个静态内部类 LinkedHashMap$Entry是LinkedHashMap的一个静态内部类
//然后他俩还是继承关系
//然后LinkedHashMap$Entry节点 里有before item after
//然后LinkedHashSet有head和tail指向首尾
//当传入自定义类型想要不重复添加时 也可以用快捷键重写equals和HashCode