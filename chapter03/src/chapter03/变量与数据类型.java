package chapter03;

public class 变量与数据类型 {
    public static void main(String[] args)
    {
        int a=10;
        int b=20;
        System.out.println(a);
        System.out.println(b);
//变量在同一作用域内不能重名
    }
}
  //+的使用

//两边都是数值型时，做加法运算
//有一边是字符串时，做拼接运算
//从左到右运算
class plus{
    public static void main(String[] args)
    {
        System.out.println(100+98);
        System.out.println("100"+98);
        System.out.println("hello"+98+8);
        System.out.println(100+98+"hello");
        int a=10;
        System.out.println("a="+a);//+是连接符
    }
}


//java数据类型

              //数值型（存放整数）byte1  short2  int4  long8
//基本数据类型  //浮点型          float4 double8
              //字符型          char2
              //布尔型          boolean1    和c不同的是java的bool只能赋true和false  不能赋数值

              //类class
//引用数据类型  //接口interface
              //数组[]

//注意  String不是基本数据类型  是类

//java数据类型大小固定 不受操作系统影响

//声明数值型默认是int     想要变long需要加l/L    这里的自动指  赋值之前这个指就已经是int 或double了
//声明浮点型默认是double  可以加f/F或者d       c是赋完值后自动给转
//注意  这和c不一样  如下
//浮点型可能丢失精度的是尾数位
class num{
    public static void main(String[] args) {
        //int a=11l;这一句是错的   因为11l是long  给不下int
        //可以
        long a=11l;
        float b=2.4f;//不加f就报错 因为默认double
        System.out.println(b);
    }
}

 //浮点型陷阱
class xianjing{
    public static void main(String[] args) {
        double num1=2.7;
        double num2=8.1/3;
        System.out.println(num1);
        System.out.println(num2);//发现值不一样  因为计算机计算小数时会使用精度
//由此引出的问题
        //不要将小数大小比较用作条件
        if(num1==num2)
        {
            System.out.println("相等");
        }
        //我们可以
        if(Math.abs(num1-num2)<0.0000001)//在精度范围内认为相等
        {
            System.out.println("相等");
        }

System.out.println(Math.abs(num1-num2));//发现差值是非常小的
    }
}


  //JAVA  api   应用程序编程接口
//https://www.matools.com

//java类组织形式
//               接口..     构造器（构造方法）
//       包1      类..      字段
//JDK    包2      异常..     成员方法
//       包..

//java中的方法相当于c的函数


//char类型  2字节  本质是整数 可以进行运算
//可以存汉字
//存储时存储数值
//输出时默认输出 unicode码 对应的字符
class ch{
    public static void main(String[] args)
    {
        char c1='a';
        char c2='\n';
        char c3='王';
        char c4=97;//可以存数字
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);//输出时默认输出unicode码表示的字符
        System.out.println((int)c3);//强制类型转换可以输出数字
        System.out.println("===============");

        char c5='a'+1;
        System.out.println(c5);
        System.out.println((int)c5);

                     System.out.println('a'+1);//这样输出的就是数字
        char c10='男';
        char c11='女';
                     System.out.println(c10+c11);//这样也是数字  具体原因见下
    }

}
//关于字符编码
//https://zhuanlan.zhihu.com/p/38333902


//自动类型转换
//java赋值或运算时精度（容量）小的自动转换为大的
//精度排序
//char         int long float double

//byte short   int long float double


 //细节  多种类型混合运算系统会先将所有类型转成(右边)最大的再计算   例1
//       1大的赋给小的会报错  反之自动转换
//       2(byte short)和char间不会 自动 转换 如上顺序所示 分了两条 就是这么规定的 例3
//       3byte short char 三者可以参与计算 参与时 一定会 先 升为int  例4
//       4boolean不参与自动转换
class AutoConvert{
    public static void main(String[] args) {
        int a='c';
        double b=78;
        System.out.println(a);//如果是c 也输出99
        System.out.println(b);//如果是c 输出78.000000   这里java只有一位

//        //例1
//        int n1=1;
//        float n2=n1+1.1;//报错 因为右侧都转成double了 赋到左侧丢精度  解决可以在1.1后加f
//        //例3
//        char n3=99;//至于这么赋为什么对  因为在赋值给这三个类型时会先检查范围 范围内就可赋
//        System.out.println(n3);
//        //但是你不能
//        byte n4=n3;//在赋值具体数的时候可以判断范围，赋值变量直接上类型判断
//      //  想的话可以用强制转换
//        //例4
//        byte n5=1;
//        char n6=2;
//        short n7=n5+n6;//右边是int
//        char n8=9;
//        char n9=10;
//        char n10=n8+n9;//即使这样也不行  因为右边还是int

    }
}

//附 c精度排序
//char  int   unsigned int    long long    unsignde long long    float    double   long double
//的话要看编译器


//强制类型转换  可能导致数据溢出或精度丢失
//大转小  加()
class convert{
    public static void main(String[] args) {

        int a=(int)1.8;//精度丢失
        int b=129;
        byte c=(byte)b;//数据溢出

        System.out.println("a="+a);
        System.out.println("c="+c);
    }
}


//字符串与基本数据类型的转换
//java中有String类型   s要大写
class connver{
    public static void main(String[] args){
//基本类型转字符串 简单死   直接拼接+“”就行
        int n1=4;
        float n2=5.7f;
        double n3=2.0;
        byte n4=7;
        String s1=n1+"";
        String s2=n2+"";
        String s3=n3+"";
        String s4=n4+"";
        System.out.println(s1+" "+s2+" "+s3+" "+s4+" ");
        System.out.println(n1+n2);
        System.out.println("=============");
//字符串转基本数据类型（首先确保能转换）   通过基本类型包装类调用parsexx方法
        String s5="123";//一开始用345报错因为底下的byte接受不了
        //使用基本数据类型对应的包装类的相应方法得到基本数据类型
   //           类       方法
         int a=Integer.parseInt(s5);
         double b=Double.parseDouble(s5);
         float c=Float.parseFloat(s5);
         short d=Short.parseShort(s5);
         byte e=Byte.parseByte(s5);
         boolean f=Boolean.parseBoolean("false");
         long g=Long.parseLong(s5);
          System.out.println(a);
          System.out.println(b);
          System.out.println(c);
          System.out.println(d);
          System.out.println(e);
          System.out.println(f);
          System.out.println(g);
          System.out.println("");
//细节    “hello"就没法转了  格式不正确 会抛出异常 程序终止


             //将字符串转成char   字符串.charAt()
        System.out.println(s5.charAt(0));
                           //取出下标为0的即第一个  注意是字符‘1’
    }

}
