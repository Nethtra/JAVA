package AA包装类;

import java.util.Scanner;

/**
 * @author 王天一
 * @version 1.0
 */
//对象保存字符串常量 不可变字符序列
//字符串中的字符采用unicode编码 一个字符（包括汉字）占两字节
//构造器重载了很多
//String是final类
//最重要的是他的属性是private final char value[]; 而value在注释中说明了是用来存放字符串的内容
//所以字符串的本质还是字符数组 还要注意的是value是final类型 因为数组是引用类型 所以这里final指的是
//不可修改数组名指向的地址 但是数组内容还是可以变的

//创建对象
//法1直接赋值String str1="ff";
//法2调用构造器String str2=new String("ff");
//现在我们来看这两种的区别
//先来复习一下jvm内存 栈 堆 方法区 方法区中还有常量池
//法1先从常量池找是否有ff 如果有就直接指向 没有就创建再指向 这时str指向的是常量池中字符串的地址
//法2会先在堆中创建对象获得一个地址 对象中含有value属性 然后value指向常量池地址 如果常量池中有ff就直接指向
//没有就先创建再指向 str最后指向的是在堆中new的对象的地址

//练习题
class Test6 {
    public static void main(String[] args) {
        String a = "ff";
        String b = new String("ff");
        System.out.println(a == b.intern());//因为a创建在前 所以b.intern返回ff的地址 true
        System.out.println(b == b.intern());//false
        //调用intern时如果常量池中已包含该对象的字符串则返回该字符串地址
        //反之在池中添加字符串并返回String对象的引用
    }
}

//String对象特性
class Test7 {
    public static void main(String[] args) {
        String a = "hello";
        String b = "world";
        String c = "hello" + "world";
        String d = a + b;
        //探寻一下c和d的区别
        //c是在常量池中又新开了一个字符串helloworld c指向常量池中的字符串
        //而d和c不一样 打断点一点一点看 先StringBuilder sb=new StringBuilder();
        //然后sb.append("hello"); sb.append("world");
        //然后String d=sb.toString()这里的toString方法return了一个新的String对象（这个对象的value指向helloworld）
        //所以d现在指向的是堆空间的对象
        System.out.println(c == d);//所以false
    }
}
//由此我们总结出一点规则 String c1="ab"+"cd" 常量相加 在常量池中新开字符串 c1指向的是常量池
//                   String c2=a+b;      变量相加是new了一个新的Sting对象并返回 c2指向的是堆中的对象
//                   String c3=a+"bc"    这种和上面一样 都是new了个新对象再返回

//例题 问输出什么
//我们来好好看看
class Test8 {
    //这里要注意的是newtest8对象时又创建了对象 所以是在对象里建对象 str和ch两个引用现在在堆里而不是之前的栈
    String str = new String("hsp");//在ex这个对象里呢 又new了一个str引用指向value再指向常量池中的hsp
    final char[] ch = {'j', 'v', 'a', 'v'};//还是在这个对象里 又一个新的引用ch 指向堆中的j a v a数组

    public void change(String str, char ch[]) {//注意形参
        str = "java";//这里str是形参 因为常量池中没有java所以会在常量池新开一块再指向
        //现在的str指向的是常量池 但是形参 所以并没有改变ex中属性的内容
        ch[0] = 'h';//这个直接改变了堆里的j
    }

    public static void main(String[] args) {//从这里开始
        Test8 ex = new Test8();//首先new了个对象ex
        ex.change(ex.str, ex.ch);//然后看这个方法传过去了str指向的value的地址和ch指向堆中的地址
        System.out.print(ex.str + " and ");
        System.out.println(ex.ch);
    }
}

//String类常用方法
//首先纠正一个误区  比如String str="hello";
//不能用诸如str[0]来取'h'(原因就是str只是一个引用 字符串是放在value里的) 要用方法charAt

//注意这些都不是static方法 都得建对象的
//equals            判断字符串内容是否相同（大小写也区分）
//equalsIgnoreCase  忽略大小写判断字符串内容是否相等
//length            返回字符串长度
//indexOf           返回字符（串）在字符串对象中第一次出现的索引 没有返回-1
//lastIndexAOf      返回字符（串）在字符串对象中最后一次出现的索引 没有返回-1
//注意从这里开始方法返回的都是一个新Sting对象 原来的字符串并没有被改动
//substring         截取指定范围的字符串并返回
//toUpperCase       将字符串转换成大写再返回
//toLowerCase       将字符串转换成小写再返回
//concat            拼接字符串   例
//replace           替换字符串中字符（串） 例
//split             分割字符串 要用数组接收  例
//toCharArray       将字符串转换成字符数组 要用数组接收
//compareTo         长度相等时像c那样比大小返回差值 不等时如果有部分相等就比长度返回差值  如果已有部分不等就比已有部分返回差值 可以看看源码
//format            格式字符串 是一个静态方法 要用占位符 和c差不多 注意的是%.2f这种会四舍五入

class Test9 {
    public static void main(String[] args) {
        String str = "hello,world!";
        System.out.println(str.substring(1));//这样是从下标开始（包括）截取到最后
        System.out.println(str.substring(3, 8));//这样是从下标开始（包括）到下标结束（不包括）

        String str1 = "动";
        str1 = str1.concat("力").concat("小").concat("滋");//可以拼好几次
        System.out.println(str1);//注意是新对象 要接收

        str1 = str1.replace("小滋", "牛至"); //是用后面的替换前面的
        System.out.println(str1);

        String slogan = "Non Terrae Plus Ultra!";
        String[] split = slogan.split(" ");//（）里是作为间隔的部分 一个部分作为一个对象放到数组里
        for (int f = 0; f < split.length; f++) {
            System.out.println(split[f]);
        }
        //注意的是如果是\作为分隔要用转义字符\\

        String name1 = "动力";
        String name2 = "动力d";
        System.out.println(name1.compareTo(name2));

        String name = "张三";
        int age = 0;
        char gender = '男';
        //String info="姓名"+name+"年龄"+age+"性别"+gender; 这是之前的写法
        String info = "姓名%s年龄%d性别%c";
        String info1 = String.format(info/*"姓名%s年龄%d性别%c"*/, name, age, gender);//现在可以用占位符
        System.out.println(info1);
    }
}

//StringBuffer类
//可变字符序列
//是一个final类
//直接父类是 AbstractStringBuilder 再往上就是Object
//实现了接口Serializable 对象可以串行化（对象可以网络传输 可以保存到文件）
//与String不同 StringBuffer中是private char[] value少了final 即可以改变value指向的地址
//且字符串的内容是直接放在堆里value中的
//效率要高于String因为在改动字符串内容时可以直接改在value里 而String因为value final的原因
//每次都要 先在常量池中新创建字符串 然后改对象引用的指向

//这里插一点理解
class Test10 {
    public static void main(String[] args) {
        String s1 = new String("hello");
        s1 = "ddd";
        String s2 = "ddd";
        System.out.println(s1 == s2);//true
        //可以看到我们把s1重新赋值后确实是直接改了对象的引用的指向 现在s1不再指向value指向的都是常量池中的空间
    }
}

//StringBuffer的构造器
class Test11 {
    public static void main(String[] args) {
        //看源码
        //1无参构造器 创建一个char[] 初始容量为16
        StringBuffer buffer = new StringBuffer();
        //2创建char[]内容是你输入的内容 走源码可以看到value的长度是hello的长度加上16
        StringBuffer buffer1 = new StringBuffer("hello");
        //3相较于1指定了长度
        StringBuffer buffer2 = new StringBuffer(10);
    }
}

class Test12 {
    public static void main(String[] args) {
        //String转StringBuffer
        String s1 = "name";
        //1new的时候用构造器 就是上面的2
        StringBuffer buffer1 = new StringBuffer(s1);
        //2使用append方法 注意这个方法在StringBuffer里不是静态的 和之前一样s1是没有变的 要用引用接收
        StringBuffer buffer2 = new StringBuffer();
        buffer2 = buffer2.append(s1);

        //StringBuffer转String
        StringBuffer buffer3 = new StringBuffer("hello");
        //1用StringBuffer里的toString方法来返回字符串
        String s2 = buffer3.toString();
        //2String构造器直接传
        String s3 = new String(buffer3);
    }
}

//StringBuffer常见方法
class Test13 {
    public static void main(String[] args) {
        //1append添加
        StringBuffer buffer1 = new StringBuffer("hello");
        String s = null;
        buffer1.append(200.1).append("world").append(true).append(s);
        //这个和String的concat还不一样 concat只能连字符串 这个是别的类型写上他也能转成SB然后再给连上
        System.out.println(buffer1);//buffer类型没有变 只不过sout时调用了tostring方法所以才正常输出
        //2delete删除
        buffer1.delete(7, 10);//意思是删除>=start <end的内容
        System.out.println(buffer1);
        //3replace替换
        buffer1.replace(10, 13, "动力");//把索引>=start到<end的内容换掉(不用担心越界)
        System.out.println(buffer1);
        //4查找indexOf
        int z = buffer1.indexOf("20");//查找字符串第一次出现的位置并返回索引 没有返回-1
        System.out.println(z);
        //5插入insert
        buffer1.insert(9, "地平线");//前面是插入位置 后面是插入内容 原来索引的内容会顺延
        System.out.println(buffer1);
        //6长度
        buffer1.length();//可以返回StringBuffer的长度
    }
}

//例题输入一串数字实现自动截位 比如21444.78就截成21,444.78
//这里就用到了SB因为insert方法是独有的 还要考虑到如果输的不是数字 或者输的是一串整数的处理方法
class Test14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s;
        while (true) {
            s = scanner.next();//接收字符串
            try {
                Double.parseDouble(s);
                break;
            } catch (NumberFormatException e) {//这里是输入了不是数字的处理情况
                //e.printStackTrace();
                System.out.println("让你输数字你不输?\n重新输");
            }
        }
        StringBuffer buffer = new StringBuffer(s);//转成SB类型
        int z = buffer.indexOf(".");//找小数点的位置 后面的思路就是-3然后插，
        if (z == -1)//如果输了个整数
        {
            buffer.append(".0");
            for (int i = buffer.length() - 2; i > 3; i -= 3) {
                buffer.insert(i - 3, ",");
            }
        } else {//正常的情况
            for (int i = z; i > 3; i -= 3) {
                buffer.insert(i - 3, ",");
            }
        }
        System.out.println(buffer);//输出
    }
}

//StringBuilder类
//和StringBuffer一样均代表可变字符序列 里面的方法也一样 可以看作StringBuffer的替换
//不同的是StringBuilder用在单线程比buffer快 buffer用在多线程
//仅此而已 其他都和buffer一样

//来看String StringBuffer StringBuilder的比较
//String 不可变字符序列 效率最低 复用率高
//StringBuffer 可变字符序列 效率较高 线程安全
//StringBuilder 可变字符序列 效率最高 线程不安全

//因为String修改时会产生大量副本 加之如果放在循环中 耗时会很恐怖
//所以 如果字符串存在大量修改操作 并在单线程情况 使用StringBuilder
//    如果字符串存在大量修改操作 并在多线程情况 使用StringBuffer
//如果字符串很少修改并被多个对象引用 使用String