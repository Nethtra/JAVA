package chapter07;

//现有技术不能完美解决新需求 于是引入类与对象oop
//java最大的特点就是面向对象
//一个程序就是一个世界 世界上的每一个事物就是对象 对象有 属性（成员变量）和行为（成员方法）
//对象的特点（属性 行为）提取出来就是类 就是自定义的数据类型
//比如int是Java给的数据类型 100就是int的一个对象
//对象是类的具体实例 类是对象的数据类型 是个数据类型
public class a类与对象 {
    public static void main(String[] args) {
//    数据类型  名    new  也可以先声明再创建
        Cat cat1 = new Cat();//这个时候会在方法区加载cat类的信息
        //cat1只是个对象名（引用）    new出来的空间才是真正的对象
        //对象会默认初始化 和数组的规则一样
        cat1.age = 10;//访问成员变量.
        cat1.name = "你好世界";
        cat1.weight = 8.6;
        System.out.println("我叫" + cat1.name + "年龄" + cat1.age + "体重" + cat1.weight);
        cat1.speak(3);//成员方法需要调用后才生效  实参
    }
}

//类是自定义的数据类型
class Cat {//这就是cat类
    String name;
    int age;
    double weight;//这些叫成员变量

    //然后还有成员方法
    //公共的（访问修饰符） 没有返回值 方法名 括号里是形参列表 没有不写 可以是任意类型 可以直接把数组传过去（无损）也可以返回引用类型 返回值也可以是任意类型
    //方法不能嵌套定义
    public void speak(int n) {
        System.out.println("我是一只好猫" + n);
    }

    public int[] getSum(int a, int b) {
        int[] arr = new int[2];
        arr[0] = a + b;
        arr[1] = a - b;
        return arr;
    }
}
//数组和对象还有字符串都是引用类型   因为是引用类型 所以也可以cat2=cat1
//对象在内存中的形式
//以刚才cat1为例 cat1这个对象名在栈里 我在堆里new一个内存空间 获得一个地址值 把他赋给cat1 所以cat1指向一个new在堆里的内存空间
//age和weight直接存在堆里 name是字符串存在方法区的常量池里 堆里的name是一个地址指向常量池中的字符串
//这里讨论以下方法的调用机制
//main在栈里运行 每调用一次方法就会在栈里开一块独立的空间来执行该方法 当方法返回结束后 谁调用返回给谁 临时空间被销毁
//同类中直接可以相互调用
//不同类间调用方法需要通过对象名才能调   公共类和其他类其实也算跨类
//p213回去再看一下 理解一遍