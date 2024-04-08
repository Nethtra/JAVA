package com.Reflection;

import java.lang.reflect.Field;

/**
 * @author 王天一
 * @version 1.0
 */
//Class类分析
//1Class也是类  继承Object
//2Class类对象（反射）在类加载时生成
//3因为类加载只有一次 所以Class类对象（反射）只有一份 即使拿多个引用去接收也是同一个对象地址
//4Class类对象（反射）放在堆里
//5每个类的实例都会记得自己是由那个Class实例所生成
//6通过Class类对象（反射）可以完整地得到一个类的结构
//7类的.class字节码文件的二进制数据放在方法区   叫做类的元数据


//Class类常用方法
class Test4 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        String classAllPath = "com.Reflection.Dog";//类路径
        Class<?> cls = Class.forName(classAllPath);//获取类对象（反射）
        System.out.println(cls.getClass());//输出反射的运行类型 Class
        System.out.println(cls);//直接输出反射  会显示这是哪个类的反射 就是类路径
        System.out.println(cls.getPackage().getName());//得到包名
        System.out.println(cls.getName());//得到类名
        Object o = cls.newInstance();//通过反射创建对象实例
        System.out.println(o);//调用toString方法

        //通过反射获取属性   但是要传字段名进去
        Field field = cls.getField("name");
        System.out.println(field.get(o));//get传入对象实例得到属性
        field.set(o, "小查");//修改对象的属性
        System.out.println(field.get(o));

        //得到所有字段的名字
        Field[] fields = cls.getFields();//返回的是一个数组
        for (Field f : fields) {//增强for
            System.out.println(f.getName());
        }
    }
}


//获取Class类对象（反射）的方式
//在编译阶段可以通过 Class.forName
//在类加载阶段可以通过 类.class
//在Runtime阶段通过 对象实例.getClass
//还可以通过类加载器得到
class Test5 {

    public static void main(String[] args) throws ClassNotFoundException {
        //1已知类的全路径 通过forName(是一个静态方法)
        String classAllPath = "com.Reflection.Cat";
        Class cls1 = Class.forName(classAllPath);//要传入类的全路径 一般是通过配置文件读取
        System.out.println(cls1);
        //2 已知具体的类 类名.class 一般用于参数传递 例如反射要得到类的有参构造器 就需要数据类型的反射作为参数
        Class cls2 = Cat.class;
        System.out.println(cls2);
        //3已知类的实例 getClass  之前说的查看运行类型就是对象.getClass
        //所以其实有了对象实例 就可以拿到反射 正好印证每一个对象实例都记得他是谁的反射 所以看运行类型的实质是直接看反射
        Cat cat = new Cat();
        Class cls3 = cat.getClass();
        System.out.println(cls3);
        //4类加载器
        ClassLoader classLoader = cat.getClass().getClassLoader();//得到类加载器对象
        Class cls4 = classLoader.loadClass(classAllPath);
        System.out.println(cls4);//感觉没用 都getClass拿到了   还多此一举
        //而且可以验证这四个反射是同一个即一个类只有一个反射
        System.out.println(cls1.hashCode());
        System.out.println(cls2.hashCode());
        System.out.println(cls3.hashCode());
        System.out.println(cls4.hashCode());

        //还有两种特别的方法
        //5基本数据类型通过  基本数据类型.class
        System.out.println(int.class);
        //6基本数据类型对应的包装类通过  包装类.TYPE
        System.out.println(Integer.TYPE);

        //打印hashcode会发现56是同一个  说明底层会自动装箱拆箱
        System.out.println(int.class.hashCode());
        System.out.println(Integer.TYPE.hashCode());
    }
}

//那些类型有Class类对象
//外部类 内部类
//接口
//数组
//枚举
//注解
//基本数据类型
//void类型
//Class自己也有  Class.class