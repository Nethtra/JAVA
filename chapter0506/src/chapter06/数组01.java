package chapter06;
import java.util.Scanner;
//数组也是一种数据类型 是引用类型  一组同一类型的数据
//先来看初始化方式
//1静态初始化
public class 数组01 {
    public static void main(String[] args) {
        double[] hens={1,2.3,4};//这里定义的时候和c不一样 []可以写在数组名前也可以写在后
        //前面括号不能加个数
        //但使用时还是hens[0]下标也是从0开始 Java中的下标叫索引
        //可以通过数组名.length来获取数组的长度
        System.out.println("数组长度 " + hens.length);
    }
}
//2动态初始化
class array{
    public static void main(String[] args) {
         //数据类型 数组名[]=new 数据类型[元素个数];动态初始化元素个数可以是变量
        //还有一种方式 先 数据类型 数组名[];
        //再            数组名=new 数据类型[元素个数]；
        double score[]=new double[4];//double score[];这里只是声明
                                     //score=new double[4] 这里才分配空间
        System.out.println(score);//[D@1b6d3586  这样输出的是申请的内存空间的值
        Scanner myscanner=new Scanner(System.in);
        for(int i=0;i<score.length;i++)
        {
              score[i]=myscanner.nextDouble();
        }
       for(int i=0;i<score.length;i++) {
           System.out.println(score[i]);
       }
       char a='a'+1;//这样是没问题的
       int i=6;
      // char b='b'+i;//但是如果加了个变量的话会报错 需要强转
    }
}
//注意点
//数组可以是任何数据类型  包括基本数据类型和引用数据类型  比如有String abc[]={ }
//java数组创建后有默认值 基本数据类型是0  boolean是false float和double是0.0 char是空字符 string是null    好像只适用于动态初始化 静态初始化必须要赋初值
//而c语言规定，普通数组没有赋初值，默认的数组元素值是随机数，不是0。如果在定义数组时，数据类型前面加上关键字static，数组变成了静态数组；或者把数组定义在函数的外面，成为全局变量数组，这时数组元素的值自动赋值为0。
//数组是引用类型 数组数据是对象
//前面的括号不能加个数


//数组赋值机制
//java中的数组可以相互赋值 但是
//基本数据类型赋值 是值拷贝
//引用数据类型赋值是 地址拷贝即传递地址   和函数调用差不多 给新数组改了元素会影响原数组
class assignment{

    public static void main(String[] args) {
        int arr1[]={1,2,3,4};
        int arr2[]=arr1;
        //也就是说数组名只是一个引用  都指向那一块内存空间
        arr2[0]=10;
        System.out.println(arr1[0]);

    }//但这里肯定不会被释放
}
//如果是arr1[]={1,2,3}
//     arr2[]={3,2,1}
//然后  arr2=arr1  这样arr2原来的内存空间就没有变量再引用 会自动被jvm释放

//再来深入探讨一下  Java中赋值分为值拷贝和引用传递（地址拷贝）
//值拷贝顾名思义   引用拷贝就是把地址拷贝一份
//数组赋值是引用传递 此外数组名指向的也是地址



//然后jvm内存分为  栈 堆 方法区
//栈存储局部变量（定义在方法中的变量）使用完立即消失
//堆存储new出来的对象数组等 每一个new出来的东西都有地址值 使用完会在垃圾回收期空闲时回收
//方法区有常量池还有类加载信息
//以int[] arr=new int[3]为例
//arr是局部变量 在栈里 但是new的对象在堆里 有一个地址值 上面这一句就是把这个地址值给arr  所以就是arr指向了这个地址值
//即int[] arr在栈 new int[3]在堆 引用在栈  数据在堆  通过地址相连

//例题 数组添加
//先静态分配 然后动态扩容 是否继续y/n
class add{
    public static void main(String[] args) {
        int[] arr = {19, 234, 34, 5, 637};//静态分配
        //考虑用dowhile
        lable:
        do {
            int[] Newarr = new int[arr.length + 1];//new一个新的 长度是原来加一
            for (int i = 0; i < arr.length; i++)//先赋值前面的
            {
                Newarr[i] = arr[i];
            }
            System.out.println("请输入要添加的数字");
            Scanner myscanner = new Scanner(System.in);//添加最后一个
            Newarr[arr.length] = myscanner.nextInt();

            arr=Newarr;//直接赋值 然后将Newarr销毁
            System.out.println("是否继续 y/n");
            char judge=myscanner.next().charAt(0);
            lable1:
            //以下都是为输入的不是yn的情况做的
            while(true) {
                if (judge == 'n')
                    break lable;
                else if (judge != 'n' && judge != 'y')
                {
                    System.out.println("输入有误！请输入y/n!");
                    judge=myscanner.next().charAt(0);
                }
                else if(judge=='y')
                {
                    break lable1;
                }
            }

        }
        while(true);//构建死循环

        for(int i=0;i< arr.length;i++)//输出
        {
            System.out.println(arr[i]);
        }
    }
}
//同理还有数组的缩减 比添加更简单


//排序介绍
//内部排序  所有数据都加载到内存中排序
//外部排序  数据量大无法全部加载 借助外部存储

//Java的冒泡
//外层是确定最大值n-1次 内层是交换n-1-i

//二维数组  先理解是由多个一维数组组成 除了一维数组那样的声明方式外 还有 int[] y[]=第三种声明方法
//arr.length 是行数或者一维数组数
//arr[i].length是 每行的元素个数
//来看初始化
//动态初始化1  数据类型[][] 数组名=new 数据类型[][];
//动态初始化2  也是先声明再new
//动态初始化3  每个一维数组元素个数不同  列数可省略
//静态初始化   也有个例外比如  String str[]=new String[]{"a","b","c"};这是对的但是右边括号里不能加3也是
//这里要注意Java的二维数组每个一维数组元素个数可以不同 可以在new的时候控制
//行数一定要指明
//下面是展示动态初始化3的一个例子
class twodimensionnalarrary{
    public static void main(String[] args) {
        int s=5;//动态初始化元素个数可以是变量
        int arr[][]=new int[s][];//先把二维开起来
        for(int i=0;i<arr.length;i++)
        {
            arr[i]=new int[i+1];//这里是开每一行
            for(int j=0;j<arr[i].length;j++)//赋值每个
            {
                arr[i][j]=i+1;
            }
            for(int k=0;k<arr[i].length;k++)//正好输出
            {
                System.out.print(arr[i][k]);
            }
            System.out.println("");
        }
    }
}
//还有个例题打印杨辉三角
//int[] x,y[];x是一维 y是二维


//例题 在原来的一个有序数组插入一个新数据，仍使之有序
class insertArrat{
    public static void main(String[] args) {
        int arr[]={19,51,81,93};
        Scanner myscanner=new Scanner(System.in);
        int insert=myscanner.nextInt();
        //首先记录要插入的位置(新数组的下标)
        int z=-1;//不能是0
        for(int i=0;i<arr.length;i++)
        {
            if(insert<arr[i])
            {
                z=i;
                break;
            }
        }
        if(z==-1)//放在最后的情况
            z=arr.length;

        //然后就是开始添加
        int Newarr[]=new int[arr.length+1];
        for(int i=0,j=0;i<Newarr.length;i++)//这里注意拷贝用的双指针的思想
        {
            if(i!=z)
            {
                Newarr[i]=arr[j];
                j++;
            }
            else
            {
                Newarr[i]=insert;
            }

        }
        for(int i=0;i<Newarr.length;i++)
        {
            System.out.print(Newarr[i]+"  ");
        }
    }
}

//随机数的产生方法  (int)(Math.random()*100)+1  表示1-100的随机数

























