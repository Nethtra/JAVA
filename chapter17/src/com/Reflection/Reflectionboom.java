package com.Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 王天一
 * @version 1.0
 */
//反射爆破


//反射爆破使用私有构造器创建对象
class Test6 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //拿到反射
        Class<?> cls = Class.forName("com.Reflection.User");
        //之前通过newInstance创建对象实例  这个调用的是无参构造器
        Object o1 = cls.newInstance();
        System.out.println(o1);

        //而如果想要调用有参构造器创建对象实例 需要先得到构造器对象 **getConstructor方法返回本类的public构造器对象**
        Constructor<?> constructor = cls.getConstructor(String.class);//里面传入数据类型的反射
        //然后用构造器对象newInstance
        Object o2 = constructor.newInstance("jacob");
        System.out.println(o2);

        //如果想访问私有构造器 得用**getDeclaredConstructor 获得本类的所有构造器对象**
        Constructor<?> declaredConstructor = cls.getDeclaredConstructor(int.class, String.class);
        //能拿到私有构造器对象 但是并不能操作 会报错
        //需要先禁用访问检查  这个操作就是爆破
        declaredConstructor.setAccessible(true);
        Object o3 = declaredConstructor.newInstance(10, "little");
        System.out.println(o3);
    }//所以反射机制可以爆破从而访问类的私有构造器 属性等
    //这也是反射的优点
}


//反射爆破操作属性
class Test7 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<?> cls = Class.forName("com.Reflection.User");
        Object o1 = cls.newInstance();
        //反射和对象都建好了 现在来操作属性
        //操作public 的score  **getField返回本类和父类的public属性**
        Field field1 = cls.getField("score");
        field1.set(o1, 100);//用反射修改值
        System.out.println(field1.get(o1));

        //操作private的address **getDeclaredField返回本类的所有属性**
        Field field2 = cls.getDeclaredField("address");
        //拿到属性对象后 一样爆破关闭访问检查才能使用
        field2.setAccessible(true);
        field2.set(null, "阿根廷");//因为是static 所以对象可以写null
        System.out.println(field2.get(null));
    }
}

//反射爆破操作方法
class Test8 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> cls = Class.forName("com.Reflection.User");//创建反射
        Object o = cls.newInstance();//创建对象
        //先来操作public方法 **getMethod能返回本类和父类的所有public方法**
        Method m1 = cls.getMethod("m1", String.class);//传入方法的名字 还有参数的反射 得到方法对象
        m1.invoke(o, "str");//调用方法 传入对象实例和具体参数
        //再来看private static方法  **getDeclaredMethod能返回本类的所有方法**
        Method m2 = cls.getDeclaredMethod("m2", int.class, char.class);
        m2.setAccessible(true);//爆破
        Object c = m2.invoke(null, 100, 'c');//调用传参 因为static对象可以为null
        //注意有返回值的话要用Object接收
        System.out.println(c);
    }
}

class User {
    private int age = 1;
    private String name = "niko";
    public double score;
    private static String address;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    private User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void m1(String s1) {
        System.out.println("hello world " + s1);
    }

    private static String m2(int n, char c) {
        return n + " " + c;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}