package Collections工具类;

import java.util.*;

/**
 * @author 王天一
 * @version 1.0
 */
//用于操作Set List Map等集合的工具类 提供一系列静态方法
class Test {
    public static void main(String[] args) {
        //以ArrayList集合为例 注意List是有序的
        ArrayList list = new ArrayList();
        list.add("Lester Crest");
        list.add("David Norton");
        list.add("Lamar Davis");
        list.add("Steven Haines");

        //排序操作

        //reverse(List) 翻转List
        Collections.reverse(list);
        System.out.println(list);
        //shuffle(List) 对List中元素随机排列
        Collections.shuffle(list);
        System.out.println(list);
        //sort(List)自然排序 默认升序
        Collections.sort(list);
        System.out.println(list);
        //sort(List,Comparator)//指定比较器产生的顺序对List排列
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String) o1).length() - ((String) o2).length();
            }
        });
        System.out.println(list);
        //swap(List,int i,int j)交换索引ij位置的元素
        Collections.swap(list, 1, 3);
        System.out.println(list);

        //查找操作

        //Object max(Collection)根据元素自然顺序返回集合中的最大元素
        //Object max(Collection ,Comparator)根据比较器指定的顺序 返回最后一个元素
        //Object min(Collection)最小
        //Object min(Collection ,Comparator)第一个
        //int frequency(Collection,Object)返回集合中指定元素的出现次数

        //替换操作
        System.out.println("============================");
        //void copy(List dest,List src)将集合src复制到dest
        List dest = new ArrayList(10);//为什么括不到10？？
        Collections.copy(dest, list);
        System.out.println(dest);
        //boolean replaceAll(List list,Object oldVal,Object newVal)
        //使用newVal替换List中的所有旧值
    }
}