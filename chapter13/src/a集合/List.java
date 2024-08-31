package a集合;

import java.util.*;

/**
 * @author 王天一
 * @version 1.0
 */
//前面保存多个数据用数组
//不足之处是 创建时长度必须指定 不能更改 然后必须保存同一类型 添加删除元素麻烦
//引出集合 动态保存多个类型的对象 提供了一系列方便操作对象的方法
//记住集合框架体系 看画的.uml
//集合分为 单列集合（一次存一个对象） 双列集合（存放k-v键值对）
//Collection接口有两个重要的子接口 List Set 他们的实现子类都是单列集合
//Map接口的实现子类是双列集合

//我的理解 实现了List Set或者Map接口的类的对象就可以叫集合 集合里可以存其他不同类型的对象 还有很多操作对象的方法
//先来看Collection接口常用方法 用实现子类ArrayList演示
class Test1 {
    public static void main(String[] args) {
        List list = new ArrayList();//接口的引用指向实现类的对象，向上转型 现在list就是一个集合
        //1add 添加单个元素
        list.add("jack");
        list.add(true);
        list.add(1342.4);
        System.out.println(list);//把集合输出看看
        //2remove删除指定元素
        list.remove(0);//参数是索引
        list.remove(true);//或者直接指定对象
        System.out.println(list);
        //3contains检查元素是否存在
        System.out.println(list.contains("jack"));//返回布尔值
        //4size获取元素个数
        System.out.println(list.size());//返回元素个数
        //5isEmpty判断是否为空
        System.out.println(list.isEmpty());//返回布尔值
        //6clear清空集合元素
        list.clear();
        System.out.println(list);
        //7addAll添加多个元素 注意参数要传入一个集合 看例子
        List list1 = new ArrayList();
        list1.add("你好");
        list1.add("世界");
        list1.add("好好好");
        list.addAll(list1);
        System.out.println(list);
        //8containsAll查找多个元素是否都存在 也是传入集合
        System.out.println(list.containsAll(list1));//就是在list里查找list1的元素
        //9removeAll删除多个元素 传入一个集合
        list.removeAll(list1);//就是删除list里的list1元素
        System.out.println(list);
    }
}

//当想要遍历集合时 就没法用for了 引出一个叫迭代器的东西 专门用来遍历集合
//实现了Iterator接口的对象称为迭代器（Iterator是一个接口）  用来遍历Collection集合中的元素
//而Collection接口中有（实现接口的也有）一个iterator方法 会返回一个迭代器对象
class Book {//自定义个Book类
    private String name;
    private String price;

    public Book(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}

class Test2 {
    public static void main(String[] args) {
        Collection col = new ArrayList();//new一个集合
        col.add(new Book("红楼梦", "90"));//添加点对象
        col.add(new Book("颜如玉", "100.0"));
        col.add(new Book("黄金屋", "66.9"));
        Iterator ite = col.iterator();//用集合返回一个迭代器
        //迭代器中的方法 next 移动到下一个元素然后以Object返回移动后的元素对象
        //             hasNext检查下一个元素是否存在并返回布尔值
        while (ite.hasNext())//开始循环  （快捷键itit）
        {
            Object obj = ite.next();//用Object接收
            System.out.println(obj);
        }
        //类似于指针 现在指向最后一个元素 如果想重置的话
        //可以
        Iterator ite1 = col.iterator();
    }
}

//当然也不是只能用迭代器
//可以用增强for  但只能用于遍历数组或者集合
//注意增强for底层仍然调用的是迭代器
class Test3 {
    public static void main(String[] args) {
        Collection col = new ArrayList();
        col.add(new Book("金瓶梅", "90"));
        col.add(new Book("颜如玉", "100.0"));
        col.add(new Book("黄金屋", "66.9"));
        //上面都是复制的
        for (Object o : col) {//快捷键I  因为不确定集合中的元素类型 所以用Object接收
            System.out.println(o);
        }

        int[] arr = {1, 2, 3, 4, 5, 7};
        for (int i : arr) {
            System.out.println(i);
        }
        //相当于把：后的数组或集合中的元素扔出来放到前面的引用或变量接收 然后在for里输出
    }
}
//因为List有索引 所以实现类都可以用三种遍历方式
//Collection的实现类都能用迭代器和增强for

//List接口特点和一些方法
//注意Collection有List和Set两个子接口 单独讲List是因为要与Set区分开来
//特点
//1List集合中元素添加和输出顺序一致 可以重复
//2List集合支持索引 即get方法
class Test4 {//演示 以ArrayList

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(new Book("金瓶梅", "90"));
        list.add(new Book("颜如玉", "100.0"));
        list.add(new Book("黄金屋", "66.9"));
        System.out.println(list);//演示顺序一致
        System.out.println(list.get(2));//演示支持索引
    }
}

//一些方法
class Test5 {
    public static void main(String[] args) {
        //还是用ArrayList来演示
        ArrayList arrayList = new ArrayList();
        arrayList.add("test");
        List list = new ArrayList();
        list.add("guailing");
        list.add("珍珠");
        //1void add(int index,Object obj) 在index位置插入元素
        list.add(1, "丁真");
        //2boolean addAll(int index,Collection objs)在index位置插入objs中的所有元素
        //不演示了
        //3Object get(int index)返回指定位置的元素 注意返回的是Object
        //4int indexOf(Object obj)返回指定元素在集合第一次出现的位置
        //5int lastIndexOf(Object obj)
        //6Object remove(int index)移除指定位置的元素并返回
        //7Object set(int index,Object obj)替换指定位置的元素
        //8List subList(int fromIndex,int toIndex)返回>=fromIndex<toIndex位置组成的集合
    }
}

//这里要注意当编译类型是List时可以用普通for来遍历集合 但是编译类型为Collection时就不行 Collection没有get方法
class Test6 {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(new Book("金瓶梅", "90"));
        list.add(new Book("颜如玉", "100.0"));
        list.add(new Book("黄金屋", "66.9"));
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
//运行类型为LinkedList Vector ArrayList时三种集合遍历方式都可以

//写一个sort方法来对书的集合中的元素按价格进行排序
class Book1 {
    private String name;
    private double price;

    public Book1(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "name=" + name + '\t' + "price=" + price;
    }
}

class Test7 {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(new Book1("金瓶梅", 90));
        list.add(new Book1("颜如玉", 100.0));
        list.add(new Book1("黄金屋", 66.9));
        sort(list);
        System.out.println(list);
    }

    public static void sort(List list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (((Book1) list.get(j)).getPrice() > ((Book1) list.get(j + 1)).getPrice()) {
                    //这里应该用不了=来赋 得用set方法替换元素
                    Book1 temp = (Book1) list.get(j);
                    list.set(j, (Book1) list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}

//关于ArrayList
//可以加入null
//基本等同于Vector  ArrayList线程不安全但效率高 Vector线程安全但效率低
//ArrayList是由数组来实现数据存储的 类中维护了一个transient Object[] elementData数组
//当创建对象时如果调用无参构造器 则数组初始容量为0 当add时会扩容到10 之后如果再需要扩容 会扩容为当前容量的1.5倍
//当使用指定大小的有参构造器时 初始化时会括为指定的容量 以后需要扩容时会直接扩容为当前容量的1.5倍
//p511 512讲了无参构造器的源码 可以看看 我觉得注意一点1.5倍是>>1来的
//至于Vector扩容时有一点不同 就是默认2倍 但也可以自己指定每次扩容的大小capacityIncrement 还是要看源码


//LinkedList
//底层实现了双向链表和双端队列特点
//可以添加任意元素  包括null 支持索引
//线程不安全 没有实现同步
//LinkedList底层维护了一个双向链表 还有两个属性first和last分别指向首尾节点
//所以LinkedList中的元素添加和删除靠的不是数组 效率较高
//在讲LinkedList之前我们先来看看java的双向链表是什么

//java中的链表每一个节点都是Node类型的
//我们来定义
class Node {
    public Object item;//实际存放的数据
    public Node next;//指向下一个节点
    public Node pre;//指向上一个节点

    //注意这里java没有指针类型 所以用来指向的还是Node类型
    //构造器
    public Node(Object item) {
        this.item = item;
    }

    //重写一下toString
    @Override
    public String toString() {
        return "" + item;
    }
}

class Test8 {
    public static void main(String[] args) {
        //先建几个节点
        Node niko = new Node("Niko");
        Node roman = new Node("Roman");
        Node vlad = new Node("vlad");
        //然后链接
        niko.next = roman;
        roman.next = vlad;//向后连
        vlad.pre = roman;//因为是双向链表 所以还要向前连
        roman.pre = niko;
        //现在就构成了一个双向链表 我们来遍历试试
        //先拿first和last指向首尾
        Node first = niko;
        Node last = vlad;
        while (first != null) {
            System.out.println(first.item);
            first = first.next;
        }
        //还可以从后向前遍历
        while (last != null) {
            System.out.println(last.item);
            last = last.pre;
        }
        //如果要添加的话 和c差不多
        //例如在niko和roman间添加michial
        Node michial = new Node("michial");
        michial.next = roman;
        michial.pre = niko;
        niko.next = michial;
        roman.pre = michial;//只不过要改四次

        //first last 不变
        while (first != null) {
            System.out.println(first.item);
            first = first.next;
        }
    }
}

//现在再来看LinkedList
//先讲 增删改查方法 一定要看源码理解
class Test9 {
    public static void main(String[] args) {
        //算了还是看看p516 理解一下源码
        LinkedList ll = new LinkedList();
        //添加
        ll.add(1);
        ll.add(2);
        System.out.println(ll);
        //删除
        ll.remove();//没指定时默认删第一个 看看重载的remove
        //改
        ll.set(0, "你好世界");
        //得到节点对象
        System.out.println(ll.get(0));
        System.out.println("=====================");
        ll.add(3);
        ll.add(4);
        //遍历 可以用迭代器
        Iterator ite = ll.iterator();
        while (ite.hasNext()) {
            Object next = ite.next();
            System.out.println(next);
        }
        //可以增强for
        for (Object o : ll) {
            System.out.println(o);
        }
        //也可以普通for
        for (int i = 0; i < ll.size(); i++) {
            System.out.println(ll.get(i));
        }

    }
}
//关于ArrayList和LinkedList
//ArrayList底层为可变数组LinkedList底层为双向链表 ArrayList改查的效率高 LinkedList增删的效率高
//都是线程不安全