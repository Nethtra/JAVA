package 异常;

import javax.lang.model.type.ArrayType;
import java.util.Scanner;

/**
 * @author 王天一
 * @version 1.0
 */
//Exception
//异常不是致命错误error
//分为编译时异常和运行时异常 运行时异常编译器无法检查可以不处理 编译时异常必须处理
//Java有异常处理机制 使程序出现异常时仍能继续运行

//异常体系Throwable类
//看体系图 右键找diagram

//常见运行时异常
//NullPointerException空指针异常 当程序试图在需要对象的地方使用null时就会抛出该异常
//数学运算异常ArithmeticException 当出现异常的运算条件时抛出 如除0
//数组索引越界异常ArrayIndexOutofBoundsException 非法索引
//类型转换异常ClassCastException当试图将对象强制转换为不是实例的子类时（向下转型）
//数字格式不正确NumberFormatException当程序试图将字符串转换成一种数值类型但不能转换时

//异常处理
//两种方式
//try catch finally 捕获异常自行处理
//快捷键ctrl alt t 使用try catch
//try{可能有异常的部分
// 当存在异常时系统将异常封装成Exception对象e并传递给catch
// 得到对象e后程序员在catch自行处理
//}catch(Exception e){
// 对异常的处理
// 如果没有异常发生则catch代码块不执行
// }finally{
// 可以没有finally
// 不管有没有异常都会执行
// 如果希望不管有没有异常都执行可以用finally 通常将释放资源等的代码放在finally
// }
//注意点
//首先异常被捕获后 catch后面的就能继续运行 否则会崩
//如果异常发生了则剩余的try中的代码不会再执行 有catch的话会直接进入catch 然后继续执行catch之后的
//如果try中可能有多个异常可以用多个catch分别捕获 但子类异常要写在前(子类的顺序无所谓)(这里要先去看那个图 知道Exception是所有异常的父类)
//还有一种使用方法是try finally 相当于没有捕获 程序会直接崩 但是还会执行finally 可以用来完成一些操作
class Person {
    String name = "jacki";
}

class Error_ {
    public static void main(String[] args) {
        try {
            Person person = new Person();
            person = null;
            System.out.println(person);
            int a = 10;
            int b = 0;
            System.out.println(a / b);
            System.out.println("你看我还执行吗");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());//输出异常信息
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("finally继续执行");
        }
        System.out.println("执行吗");
    }
}


//trycatch练习1
class Exception1 {
    public static int method() {
        int i = 1;
        String[] str = new String[3];
        try {
            if (str[0].equals("tom"))
                return ++i;
            else
                return ++i;
        } catch (Exception e) {
            return ++i;//执行到这里时能看出来中间变量temp是2
            // 因为finally无论如何都会执行所以程序会先等着不return
            //所以最后return回去的还是temp2
        } finally {//继续这里
            i++;//3
            System.out.println(i);//3
        }
    }
}

class Test {
    public static void main(String[] args) {
        System.out.println(Exception1.method());//2
    }
}

//trycatch练习2 直到输入整数才退出
class Exception2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个整数");
        while (true) {
            try {
                int num = Integer.parseInt(scanner.next());
                System.out.println("你输入的是" + num);
                break;
            } catch (NumberFormatException e) {
                System.out.println("重新输入");
            }
        }
    }
}

//如果不使用异常处理机制，输错程序直接停止，使用异常处理机制可以让程序继续运行
class Exception21 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个整数");
        while (true) {
            int num = Integer.parseInt(scanner.next());
            System.out.println("你输入的是" + num);
            break;
        }
    }
}

//如果方法中可能出现某种异常但不确定如何处理 可以显式地抛出 交给方法的调用者处理
//throws抛出异常给调用的上一级 顶级是JVM
//jvm处理异常的方式是输出异常信息detailMessage再终止程序
//如果存在运行异常没有catch或throws处理会默认throw抛出
//throws后可以接异常列表 也可以是他们的父类
//语法 写在方法的（）后面 throws 异常类型
//当子类重写父类的方法时规定子类重写的方法抛出的异常类型和父类抛出的一致或者是他的子类
//throw后交给上一调用者处理 上一级可以trycatch或者throw 如果抛的是runtimeexception则不会报//runtime异常有隐式处理


//自定义异常
//程序员自己定义的异常类 继承Exception或者RuntimeException
//如果继承Exception则属于编译异常
//如果继承RuntimeException则属于运行时异常
//一般定义为运行时异常
//至于为什么做成运行时异常因为编译时异常必须得显式地处理
//语法 throw关键字 看下面
class Test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你的岁数");
        int age = scanner.nextInt();
        if (age < 0 || age > 120)
            throw new AgeException("年龄不合法！");
    }
}

class AgeException extends RuntimeException {
    public AgeException(String message) {//构造器
        super(message);//可以ctrl b看看super顶层是什么 这里就是new了对象后调构造器输入信息 往上传最后jvm会输出这个异常信息
    }
}
//throw和throws区别
//throws放在方法声明（）的后边 throw放在方法体中
//throws后跟异常类型 throw后跟异常对象
//还有就是throw和return差不多 意思就是如果后面有finally的话会先执行finally最后再抛出

//本章练习
//1接收命令行的两个参数
//计算两数相除 要求使用方法cal(int n1,int n2)
//对数据格式不正确 命令行参数不正确 除0 这三种情况进行异常处理
class Test2 {//好好理解这道题

    public static void main(String[] args) {
        try {
            if (args.length != 2)                                 //detailmessage
                throw new ArrayIndexOutOfBoundsException("命令行参数输入个数不正确！");
//throw出的异常也可以被catch抓住
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);

            cal(a, b);
        } catch (NumberFormatException e) {
            System.out.println("命令行参数输入的不是数字！");
        } catch (ArithmeticException e) {
            System.out.println("除了0！");
        } catch (ArrayIndexOutOfBoundsException e) {//getmessage方法返回detailmessage  而异常的构造器就是给detailmessage这个属性赋值
            System.out.println(e.getMessage());
        }
        //这样catch的话就不会交给jvm来处理异常了 是你catch处理的
        // 可以试试把if拿到try外看看 那样就是throw后不被catch 所以会交给jvm处理

    }

    public static double cal(int n1, int n2)//cal方法
    {
        return n1 / n2;
    }
}
//综上自己的想法
//trycatch非常形象 有异常抛出的话会被抓住然后在catch里解决 后面的代码就能继续执行
//如果不用catch的话可以抛出给上一调用者