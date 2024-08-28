package com.Reflection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.Properties;

/**
 * @author 王天一
 * @version 1.0
 */
//引入 根据配置文件的信息创建对象并调用方法
class Test1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //传统方式
        // Cat cat = new Cat();
        // cat.hi();
        //当用传统方式时  如果需求更改，比如调用hi方法改为调用cry方法  则在此文件中必须要改源码
        //而使用反射可以解决这个问题
        //先加载配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\re.properties"));
        //拿到类和方法的信息
        String classfullpath = properties.get("classfullpath").toString();
        String methodName = properties.get("methodName").toString();

        //加载类，拿到类的反射 就是cls
        Class cls = Class.forName(classfullpath);
        //用反射创建加载的类的对象实例o
        Object o = cls.newInstance();
        System.out.println(o.getClass());//看看运行类型 就是cat
        //用反射创建加载的类的方法的对象method1
        Method method1 = cls.getMethod(methodName);
        //通过方法对象来调用配置文件中要求的方法
        method1.invoke(o);//注意语法
        //可以发现我们全程都没有new对象，也没有用到任何一个具体的方法名，只需要配置文件就能完成类的对象创建与方法调用
        //用了反射后  当需求更改以后  可以不修改源码 只该配置文件信息  调用的方法也会跟着改
        //符合ocp原则 即不修改源码也达到扩展功能的作用
    }
}//不知道是不是包名不能是中文 一开始错了
//反射机制借助Reflection api来获取类的内部信息
//类加载完成后 堆中会产生一个Class类型的对象（一个类只有一个）这个对象包含了类的完整结构信息
//通过这个对象就可以得到类的结构   这个对象就叫做反射 比如这一句cls就是反射 Class cls=Class.forName(classfullpath);
//Java程序运行阶段
//编译时将源码编译成.class字节码文件   当Runtime 使用到类时（比如new对象或者使用Class.forName） 都会触发类加载
//而此时就会通过类加载器生成一个该类的Class类对象放在堆区 这个类对象就是反射 由.class文件生成的类的字节码二进制数据放入方法区
//当我们new起来对象后 该对象其实知道自己的反射是谁  所以得到反射后就可以创建对象  调用属性等

//gpt
//Java类的加载是在运行时（Runtime）进行的，即在Java程序运行期间动态地加载类。Java中的类加载器（Class Loader）负责将Java类加载到JVM中。
//当Java程序需要使用一个类时，JVM首先检查该类是否已经加载，如果没有加载，则通过类加载器加载该类。
//类加载器将.class文件中的字节码加载到内存中，并将其转换为JVM能够理解的格式，然后生成一个对应的Class对象，该对象包含了该类的所有信息，包括方法、字段、注解等。
//Java类的加载是延迟加载的，即只有在需要使用该类时才会进行加载。这种延迟加载的方式可以提高程序的启动速度，并节省内存空间。
//类加载器采用双亲委派模型（Parent Delegation Model）来加载类，即先由父类加载器尝试加载类，如果父类加载器无法加载该类，则由子类加载器尝试加载。
//这种机制可以保证类的唯一性，并避免重复加载。在Java程序运行期间，类的加载可以分为三个阶段：加载（Loading）、链接（Linking）和初始化（Initialization）。
//在加载阶段，类加载器将类的字节码加载到JVM中。在链接阶段，将进行验证（Verification）、准备（Preparation）和解析（Resolution）等操作。
//在初始化阶段，将对类进行初始化，并执行static代码块等操作。只有在需要使用类的信息时，JVM才会进行链接和初始化操作。

//所以Java反射机制可以
//在运行时判断任一对象所属的类
//在运行时构造任意类的对象
//在运行时得到任意类的成员变量和方法
//在运行时调用一个对象的成员变量和方法
//生成动态代理


//反射相关类
//都在java.lang.reflect包内
//java.long.Class  Class对象表示某个类加载后在堆中的反射对象
//java.long.reflect.Method  Method对象表示类的方法
//java.long.reflect.Field   Field对象代表类的成员变量
//java.long.reflect.Constructor  Constructor对象代表类的构造器
//刚才用过了Class 和 Method  现在来看看后面两个
class Test2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\re.properties"));
        //拿到类和方法的信息
        String classfullpath = properties.get("classfullpath").toString();
        String methodName = properties.get("methodName").toString();
        //加载类，拿到类的反射 就是cls
        Class cls = Class.forName(classfullpath);
        //用反射创建加载的类的对象实例
        Object o = cls.newInstance();
        //用反射创建加载的类的方法的对象
        Method method1 = cls.getMethod(methodName);
        //通过方法对象来调用配置文件中要求的方法
        method1.invoke(o);//注意语法  方法对象.invoke（类对象）

        System.out.println("===========================");
        //得到成员变量 用Field对象   注意getField只能get到非private对象
        Field field = cls.getField("age");
        System.out.println(field.get(o));//输出 注意语法 还是 字段对象.方法（类对象）
        //得到构造器 用Constructor对象  不传参数默认返回无参构造器
        Constructor constructor1 = cls.getConstructor();
        System.out.println(constructor1);
        //有参
        Constructor constructor2 = cls.getConstructor(String.class);//这里要传入的是String的反射即String的Calss对象
        System.out.println(constructor2);

    }
}//不知道是不是包名不能是中文 一开始错了


//反射的优缺点
//优点  可以动态创建和使用对象 使用灵活
//缺点  反射是解释执行 对执行速度有影响
//引出反射调用的优化
//刚才说的Constructor Field Method类都是AccessibleObject的子类
//其对象都有setAccessible方法  此方法用来启动或禁用访问安全检查的开关
//参数true表示使用时反射对象取消访问检查 能提高反射的效率 参数为false表示对反射对象执行访问检查
class Test3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\re.properties"));
        //拿到类和方法的信息
        String classfullpath = properties.get("classfullpath").toString();
        String methodName = properties.get("methodName").toString();
        //加载类，拿到类的反射 就是cls
        Class cls = Class.forName(classfullpath);
        //用反射创建加载的类的对象实例
        Object o = cls.newInstance();
        //用反射创建加载的类的方法的对象
        Method method1 = cls.getMethod(methodName);
        method1.setAccessible(true);//禁用method1的安全检查
        //通过方法对象来调用配置文件中要求的方法
        method1.invoke(o);//注意语法  方法对象.方法（类对象）
    }
}
