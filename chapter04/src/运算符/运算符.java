package 运算符;
import java.util.Scanner;
//         包     类
//分类 算数 赋值 关系 逻辑 位运算 三元
public class 运算符 {
    public static void main(String[] args) {
        //算数运算符  和c一样
        // 注意+可以字符串相加
      double n1=10/4;//2.0
        System.out.println(n1);
        //取模%
        //值的符号与被除数相同
        //前自增  先自增后赋值
        //后自增  先赋值后自增

        //这里自增要注意
        //java采用中间变量缓存机制  在java中，执行自增运算时，会为每一个自增操作分配一个临时变量
        //即上面说的赋值是赋值给中间变量  **运算时最终使用的，并不是变量本身，而是被赋了值的临时变量temp.**
//看这段代码
        int i = 0;
        for(int j = 0;j < 5; j++)
        {//                                      0       0  1        0           0
            i = i++;//后自增  首先i的值被赋给临时变量temp  然后i自增    最后temp的值被赋给i  所以结果还是0 五个0
           // i++;   但如果是这样的话就没有最后一步 i=temp 正常输出12345   其实就是少了第三步
     //                             temp=i             i=i+1    |          i=temp
             System.out.println("i =" + i);}
//==================================================================
//再来看这个
        int k = 0;
        for(int j = 0;j < 5; j++)
        {
             k = ++k;  //首先k自增    然后k的值被赋给temp    最后temp的值被赋给k  这个正常输出12345
           // ++k;    这个也是正常
             //             k=k+1           temp=k      |        k=temp
            System.out.println("i =" +k);}


        //666         这两段码拿到c上结果相同   都是12345  说明c没有中间变量缓存机制

        //关系运算符   注意==别写错了
        //所有关系运算符的结果都是boolean类型
        //和c一样 多了一个instanceof   检查是否是类的对象
        int a=1209;
        int b=23490;
System.out.println(a>b);
boolean flag=a<=b;
System.out.println(flag);
    }
}


class LogicOperator{
    public static void main(String[] args) {
        //逻辑运算符
        //&逻辑与     |逻辑或    ^逻辑异或  ！非
        //&&短路与    ||短路或

        //短路与  和逻辑与的区别
        //短路与&&  在第一个条件为false时不会再判断第二个条件  效率更高
        //逻辑与&   不管第一个条件是什么都会再判断第二个条件
       //但是要注意逻辑与短路只有在第一个为false时才有区别   如果第一个是true还是得判断第二个
        //开发中用短路  效率高
        int a=10,b=20;
        if(a<10&&++b>15)//换一下&就看出区别了
        {
            System.out.println("zhen");
        }
            System.out.println("a="+a+"b="+b);

        //短路或||   如果第一个为true第二个不再判断   效率高    和短路与差不多
        //逻辑或|                       会
          System.out.println(!(10>100));

          //看道题    有。意思
        boolean x=true;
        boolean y=false;
        short z=46;
        if((z++==46)&&(y=true)) z++;//48
        if((x=false)||(++z==49)) z++;//50
        System.out.println("z="+z);//50
    }
}


class AssignOperator{
    public static void main(String[] args) {
        //赋值运算符
        //左边只能是变量
        //复合赋值运算符会自动进行强制类型转换  具体看下例

        byte b=10;
        //b=b+2;  这样就不行
       b+=2;//相当于  b=(byte) (b+2)
        System.out.println(b);
    }
}



class ternaryoperator{
    public static void main(String[] args) {
        //三元运算符 和c一样  实际上是个if语句
        //条件？表达式1：表达式2
        //条件true返回1的值  false返回2的值
        //可以用来简单的排序
        int a=10,b=34;
        int res=a<b?a++:b--;//先返回a后自增
        //好好理解
        System.out.println("a="+a);//11
        System.out.println("b="+b);//34
        System.out.println("res="+res);//10
    }
}
//运算符优先级看图

  //标识符命名规则
//标识符：对变量方法类等的命名时使用的字符
//规则   字母数字_和$
//       数字不开头
//       不使用关键字和保留字 但可以包含
//       java严格区分大小写
//       不能包含空格
  //标识符命名规范
//包名        多单词组成时所有字母小写 单词间加.    com.abc.wer
//类、接口名   多单词组成时每个单词首字母大写    XxxYyyZzz
//方法 变量名  多单词组成时第一个单词首字母小写 第二个单词开始首字母大写 xxxYyyZzz
//常量名      所有字母都大写 单词间加下划线_        XXX_YYY_ZZZ


  //键盘输入语句
//接收用户输入的数据 需要一个对象
class Input{
      public static void main(String[] args) {
          //首先导入类  所在的包  (这里是Scanner类) 要放在文件开头
          Scanner myscanner=new Scanner(System.in);//创建类的对象
          //        对象名    新

          System.out.println("请输入名字");
          String name=myscanner.next();//接收输入的字符串
          //                    方法
          System.out.println("请输入年龄");
          int age=myscanner.nextInt();//接收int
          System.out.println("名字="+name);
          System.out.println("年龄="+age);
      }
  }



//进制
//二级制0b开头
//八进制0开头
//十进制
//十六进制0x开头
//进制转换要会

//十转二  除二直到商为零倒取余
//十转八  除八直到商为零倒取余
//十转十六  一样

//二转八  三位一组
//二转十六  四位一组
//八转二   拆成三位一组
//十六转二  拆成四位一组

//关于原反补码

//java中没有无符号数  全都有符号
//二进制最高位是符号位  0正1负
//正数三码一样
//负数反码=原码符号位不变 其他位取反
//负数补码=反码+1
//0的反码补码都是0
////计算机运算时以补码运算
////看运算结果时要看原码

class BitOperator{
    public static void main(String[] args) {
        //按位& | ^ ~
        //先来感受一下计算机运算的方式
        //~2  按位取反2
        //首先2的原码是                         00000000 00000000 00000000 00000010
        //然后转到补码准备计算（三码合一一样）       00000000 00000000 00000000 00000010
        //按位取反                              11111111 11111111 11111111 11111101
         //得到的是补码 但要看结果要看原码 所以要转回去
        //一看最高位1 所以得一步一步转
        //转成反码 -1先                            11111111 11111111 11111111 11111100
        //转成原码   最高位不变 其他取反              10000000 00000000 00000000 00000011
        //注意只有原变反或者反变原的时候最高位不变
        //这时是原码   最高位1  -3

        //位运算                   正数补0         负数补1
        //>>算数右移  符号位不变 就是如果符号位是1就补1 0就补0比如   11000010右移一位是  11100001
        //<<算数左移  和普通左移一样 符号可能变   但注意范围  比如Int类型  左移超过32位 实际会取模即 如果左移33位就33%32=1 位移一位
        //>>>逻辑右移(无符号右移)  就是不考虑符号位 右移后高位补0   正数负数都补0
        //没有 逻辑左移  这么看来算数左移就是逻辑左移
        //可以想想 只有右移才会带来符号问题所以要分算数和无符号
        //还有右移相当于除2  左移相当于乘2
        //byte a=(byte) 0b10001011;
        //byte b=a<<1;
        //System.out.println(b);//？？？？？？为什么不对
    }

}
//注意小数参与运算 得到的是近似值
