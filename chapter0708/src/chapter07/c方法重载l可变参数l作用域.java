package chapter07;

//方法重载overload
//java允许 同一个类中 多个重名方法 但要求形参列表不一致
//不一致是指可以 类型 数量 顺序 不一样
//理解就是 让编译器知道你调的是哪个函数
//比如sout out是Printstream的对象 println是方法 参数可以是多种类型
class OverLoad {
    public int calculate(int a, int b) {
        return a + b;
    }

    public double calculate(double a, int b) {
        return a + b;
    }

    public double calculate(int b, double a) {
        return a + b;
    }

    public double calculate(int a, double b, int c) {
        return a > b ? a : b > c ? a > b ? a : b : c;
    }
}
//可变参数 Variadic parameters
//当函数重载时只有参数个数不同时 可以将不同方法封装为一个方法
//可变参数的实参可以是0个到任意多个
//可变参数的实参可以是数组
//可变参数的本质就是数组
//可变参数可以和普通参数一起放在形参列表 但是可变参数必须在后面
//一个形参列表中只能出现一个可变参数

class VariadicParameters {
    //              int...表示方法接收可变参数
    //                 然后nums可以看为一个数组 存的都是int 个数在调用时看传过来多少就是多少
    public int sum(int... nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum;
    }
    //例如这个程序就比直接重载方便很多
}

class a {
    public static void main(String[] args) {
        OverLoad myOverload = new OverLoad();
        System.out.println(myOverload.calculate(1, 2, 3));
        //int arr1[]={1,2};
        //int arr2[]={2,3,4};
        //验证出的解释：可变参数的实参可以是数组 但只能传进一个数组 本质上还是传进去一堆数 nums.length就是数组长度 nums还是一维数组 并不是二维数组
        VariadicParameters myVariadicParameters = new VariadicParameters();
        System.out.println(myVariadicParameters.sum(1, 2, 3));
    }
}

//练习题
//有三个方法 分别是返回姓名和两门成绩总分 返回姓名和三门成绩总分 返回姓名和五门成绩总分
class OverLoadExercise {
    public String score(String str, double... scores)//这里可以返回类型为string型
    {
        int sum = 0;
        for (int i = 0; i < scores.length; i++) {
            sum += scores[i];
        }
        return str + "总成绩为" + sum;//
    }
}

class b {
    public static void main(String[] args) {
        OverLoadExercise overLoad = new OverLoadExercise();
        System.out.println(overLoad.score("丁真", 0, 0));
        System.out.println(overLoad.score("孙笑川", 100, 100, 100));
    }
}
//===========================================
//变量的作用域
//Java中变量只能在class内定义
//全局变量（属性）(成员变量)可以不初始化就使用 有初始值 基本都是0 作用域是整个类
//局部变量使用时必须先初始化 没有初始值 作用域是代码块
//局部和全局可以重名 也是就近
//全局的生命周期是对象的创建与销毁 局部的生命周期是代码块
//全局变量可以加访问修饰符
//不同类间可以访问全局变量  法1可以new个新对象访问  法2可以直接传入对象然后访问
//还有弹幕说开在堆里的都有初始值