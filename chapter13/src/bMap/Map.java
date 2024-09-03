package bMap;

import java.util.*;

/**
 * @author 王天一
 * @version 1.0
 */
//p531 532 map的原理
//Map接口
//Map与Collection并列存在 用于保存具有映射关系的数据 即k-v键值对 双列集合
//Set其实也是存的k-v键值对 只不过value是一个固定的值 只用到了key 而在这里可以自定义
//Map中的key不能重复(重复时会替换(覆盖)相同key的value)  value可以重复
//key可以为null但只能出现一次 value也可以null可以出现多次
//常用String类作为key 因为String是不可变字符序列
//HashMap
//key 和 value可以是任何类型的数据 会被封装到HashMap$Node对象中然后存入Map集合中(熟悉吗)
//    key就是那个对象
//与HashSet一样添加与取出顺序不一致，添加时按key的hash值添加 但每次取出都一致
//线程不安全
//基本可以认为和HashSet一样 因为HashSet调的也是HashMap
class Test1 {
    public static void main(String[] args) {
        //用HashMap演示一下
        Map hashmap = new HashMap();
        hashmap.put("1", "abc");//这里添加叫put
        hashmap.put("2", "def");
        hashmap.put(null, null);
        System.out.println(hashmap.get("1"));//用get传入key能返回value
        System.out.println(hashmap);//添加取出顺序不一致
    }
}

//继续分析
//首先知道：
//HashMap$Node是HashMap的静态内部类
//Entry是Map里的一个接口 定义了getKey和getValue等方法
//EntrySet KeySet Values 是HashMap的成员内部类
//EntrySet实现了Set
//HashMap$Node implements Map.Entry
//刚才我们说k-v最后会被封装在HashMap$Node对象中,然后存在Map集合里
//但其实为了方便遍历，一般会手动创建entrySet集合（EntrySet类型 集合的名字叫entrySet，Map里有entrySet方法来返回这个集合）并用Set接收
//这个集合定义存放的元素的类型是Map.Entry 但实际上传入的是HashMap$Node类型，将Entry实例化为Node传入
//这时我们就把HashMap$Node对象存到entrySet里 方便我们的遍历 因为Entry中提供了getKey和getValue方法
//而且因为用Set类型接收 所以可以用迭代器  进而方便了遍历
//总结 就是把HashMap$Node放到entrySet集合里，set集合本身可以用构造器，且Entry中提供了getKey和getValue方法方便遍历
//而实际上entrySet中的一个个Entry是以地址的形式存储的  其中地址指向Node中的key和value Node还是在table表里
//Map中还有keySet和values方法返回单独key或者value组成的集合，分别定义为KeySet和Values类型
//所以entrySet返回kv集合 keySet返回k集合 values返回v集合
class Test2 {
    public Test2() {
    }

    public static void main(String[] args) {
        Map hashMap = new HashMap();
        hashMap.put("1", "abc");
        hashMap.put("2", "def");
        hashMap.put(null, null);
        //用set接
        Set set = hashMap.entrySet();//拿到这个集合 注意是Set类型
        System.out.println(set.getClass());//EntrySet
        for (Object o : set) {
            Map.Entry entry = (Map.Entry) o;//拿出来用Entry向下转型
            System.out.println(entry.getKey());//现在可以用get了
            System.out.println(entry.getClass());//Node
        }
        System.out.println("=====================================");
        //keySet和values
        Set set1 = hashMap.keySet();
        Collection values = hashMap.values();
        System.out.println(set1.getClass());
        System.out.println(values.getClass());
    }
}
//实际上entrySet中的key和value是以地址的形式存储  这个地址指向Node中的key和value
//综上Map的结构 就是table表里的链表＋红黑树 然后表里的node被封装成Entry放到entrySet集合里方便管理
//此外Map还提供keySet和values方法返回单独key或者set组成的集合

//Map的常用方法
//put 添加
//remove传入key返回删除
//get()传入key返回value
//size获取元素个数
//isEmpty判断元素个数是否为0
//clear清空
//containsKey查找key是否存在传入key返回boolean


//遍历Map的方式
class Test3 {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("Michael", "DeSanta");
        map.put("Franklin", "Clinton");
        map.put("Trevor", "Philips");

        //思路1通过keySet 先取出map中的key再用get方法得到value
        Set keySet = map.keySet();//都是要用到keySet
        //增强for
        for (Object o : keySet) {
            System.out.println(o + " " + map.get(o));
        }
        //迭代器
        Iterator iterator = keySet.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println(next + " " + map.get(next));
        }
        //不能用普通for 因为Set虽然有size但没有索引也没有get

        System.out.println("=================================");

        //思路2通过values 只取value和上面基本一样 但是只能取出value
        Collection values = map.values();
        //增强for
        for (Object o : values) {
            System.out.println(o);
        }
        //迭代器
        Iterator iterator1 = values.iterator();
        while (iterator1.hasNext()) {
            Object next = iterator1.next();
            System.out.println(next);
        }

        System.out.println("===================================");

        //思路3通过entrySet
        Set entrySet = map.entrySet();
        //增强for
        for (Object o : entrySet) {
            Map.Entry entry = (Map.Entry) o;//先向下转型为Entry
            //然后使用getkey和getvalue方法
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        //迭代器
        Iterator iterator2 = entrySet.iterator();
        while (iterator2.hasNext()) {
            Object next = iterator2.next();
            //也是先向下转型
            Map.Entry entry = (Map.Entry) next;
            System.out.println(entry.getKey() + " " + entry.getValue());

        }

    }
}


//小题
//使用HashMap添加三个员工对象 key:num   value:员工对象
//并遍历显示工资大于1w8的员工
class Employ {
    private String name;
    private double sal;
    private int id;

    public Employ(String name, double sal, int id) {
        this.name = name;
        this.sal = sal;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employ{" +
                "name='" + name + '\'' +
                ", sal=" + sal +
                ", id=" + id +
                '}';
    }
}

class Test4 {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put(1, new Employ("tom", 18001, 001));
        map.put(2, new Employ("mice", 3000, 002));
        map.put(3, new Employ("jack", 540000, 003));

        Set entrySet = map.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry next = (Map.Entry) iterator.next();//这里拿出来是Object 我们可以先直接转成Entry
            //然后调Entry的getvalue 因为put的时候是以obj的形式 所以getvalue返回的是obj 所以要先向下转型
            Employ employ = (Employ) next.getValue();
            if (employ.getSal() > 18000)
                System.out.println(employ);
        }
    }
}

//HashMap的扩容机制和HashSet一样（但是注意key相同时替换value）
//p538讲了添加的源码p539讲了扩容的源码

//HashTable
//和HashMap基本一样
//有几点不同
//HashTable线程安全但效率较低 HashMap线程不安全
//HashTable中的kv都不能是null
//HashTable的底层数组是HashTable$Entry[]类型 看源码也是继承了Map.Entry
//当第一次添加时大小括为11 临界为8  因子也是0.75 第二次扩容为23 临界为17
//看源码扩容的规则是<<2再+1 即11*2+1就是23

//Properties 继承了HashTable
//与HashTable类似 可以用于.properties文件中（常作为配置文件） 加载外部数据到Properties类对象并进行读取修改
//一些方法也是 put增改   remove删  get或者getProperty查

//开发中集合的选择
//首先判断是需要对象还是键值对即单列还是双列集合
//如果是单列 Collection
//      允许元素重复 List
//             增删多LinkedList(底层维护双向链表)
//             改查多ArrayList(底层维护Object类型可变数组)
//      不允许重复   Set
//             无序 HashSet(底层HashMap 维护哈希表（数组链表红黑树）)
//             有序 TreeSet
//             插入取出顺序一致 LinkedHashSet（底层维护数组+双向链表）
//
//如果是双列  Map
//            键无序 HashMap(底层是哈希表（数组链表红黑树）)
//            键有序 TreeMap
//            键插入和取出顺序一致 LinkedHashMap
//            读取文件 Properties