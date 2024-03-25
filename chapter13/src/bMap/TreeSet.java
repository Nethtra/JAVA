package bMap;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author 王天一
 * @version 1.0
 */

//TreeSet是实现了Set的类 底层是TreeMap
//刚才说TreeSet跟HashSet比是有序的
//我们来看看怎么有序
class Test {
    public static void main(String[] args) {
        //TreeSet treeSet = new TreeSet(); //TreeSet里有四个构造器 当我们使用无参构造器时 默认使用key的compareTo方法排序
        TreeSet treeset = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                //return ((String)o1).compareTo((String)o2);//这里我们用compareTo
                return ((String) o1).length() - ((String) o2).length();//如果是根据长度排
                //有意思的就在这 因为调用compare时看的是返回>0<0还是 从而决定谁在前谁在后
                //而在=0时会认为key相等 不会add 所以表现在length就是长度相等不会添加
                //所以输出的时候只有三个
            }
        });//可以用有参构造器 传入一个比较器（用匿名内部类）来指定排序规则
        //传入比较器时就会调用有比较器的构造器 里面会调用你写的compare方法 根据return的值来排
        //要注意的是 这里指定的add的顺序 就是在添加的时候按指定的规则添加 所以取出时才是有序的
        treeset.add("vfy");
        treeset.add("dq");
        treeset.add("a");
        treeset.add("kcp");
        System.out.println(treeset);
    }
}