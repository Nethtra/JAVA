package 枚举和注解;

/**
 * @author 王天一
 * @version 1.0
 */
//注解Annotation
//注释和注解不同的是注解可以被编译运行
//使用注解时要在前加@ 并把该注解当为修饰符使用
//@Override
//只能修饰方法
//表示重写父类方法 但也可以不写  相当于一种规范
//如果写了Override但是下面的方法不是重写的话会报错
//@Deprecated
//修饰某个元素（类 方法 包 构造器 属性 局部变量 参数）表示该元素已过时  但不影响使用
//作用时做到新旧版本的过度
//@SuppressWarnings
//忽略警告
//我觉得只用记住@SuppressWarnings({"all"}) 无视警告，继续访问！
//范围和Deprecated差不多 就是放在谁上边就忽略哪部分警告

//元注解 注解的注解（了解 看p436）
//@interface表示注解类
//@Target指定注解可以在哪使用
//@Retention指定注解的作用范围(SOURCE CLASS　RUNTIME)
//@Documented指定注解是否会在Javadoc中体现
//@Inherited子类会继承父类注解