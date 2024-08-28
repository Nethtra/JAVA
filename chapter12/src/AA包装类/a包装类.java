package AA包装类;

/**
 * @author 王天一
 * @version 1.0
 */
//Wrapper
//针对八种基本数据类型相应的引用类型
//有了类的特点之后就可以调方法
//boolean  Boolean
//char     Character//前两个直接继承Object
//int      Integer
//byte     Byte
//short    Short
//long     Long
//float    Float
//double   Double//后面这几个先继承Number再继承Object

//包装类和基本数据类型的转换
//基本数据类型->相应的包装类  叫装箱
//包装类->相应的基本数据类型  叫拆箱
//jdk5以前采用手动装拆箱
//用int来演示
class Test {
    public static void main(String[] args) {
        int n1 = 0;
        //手动装箱 法1
        Integer integer1 = new Integer(n1);
        //法2
        Integer integer2 = Integer.valueOf(n1);

        //拆箱
        int n2 = integer1.intValue();
    }
}

//jdk5以后自动拆装箱
class Test1 {
    public static void main(String[] args) {
        int n1 = 0;
        //装箱
        Integer integer = n1;

        //拆箱
        int n2 = integer;
    }
}

//虽然自动拆装但是底层使用的还是手动的方法
//小做一道题
class Test2 {
    public static void main(String[] args) {
        Object obj1 = true ? new Integer(1) : new Double(2);
        System.out.println(obj1);//手动装箱输出的是1.0因为三目运算符是个运算符 1在转换的时候要先升到最高精度double
    }
}

//String转包装类
//首先这两个相互转我不好说是装还是拆 直接认为互相装
class Test3 {
    public static void main(String[] args) {
        String str = "12";
        Integer integer1 = new Integer(str);//
        Integer integer2 = Integer.valueOf(str);//这两个都是手动装箱
        //还有一种思路是先转成int 然后靠自动装箱
        Integer integer3 = Integer.parseInt(str);
    }
}

//包装类转String
class Test4 {
    public static void main(String[] args) {
        Integer n1 = 0;
        String str1 = n1 + "";//这里直接转成字符串也相当于直接new对象
        String str2 = n1.toString();//返回字符串
        String str3 = String.valueOf(n1);//手动装箱
    }
}
//其他类也有很多方法 边用边记

//看道题
class Test5 {
    public static void main(String[] args) {
        Integer i = new Integer(1);
        Integer j = new Integer(1);
        System.out.println(i == j);//这里的装箱是new了两个对象 地址不同所以false
        Integer m = 1;//自动装箱 拆开是Integer.valueOf()我们看看源码
        //发现在一个范围内会返回一个数组中的元素 而这个范围是-128--127 再看这个数组是Integer类型的 所以直接返回一个Integer对象
        //在类加载时已经存好了这些对象的信息  对于这道题返回的就是同一对象
        Integer n = 1;
        System.out.println(m == n);//综上这里是true

        Integer x = 128;
        Integer y = 128;
        System.out.println(x == y);//不在范围内new对象 所以是false

        Integer k = 1;
        int z = 1;
        System.out.println(z == k);//只要有基本数据类型比的都是值 所以true
    }
}