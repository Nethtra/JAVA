//打包语法
package chapter08;//需要放在类的最上面 一个java最多一个写package
//表示打包   包名

//导包语法 import 包名   放在package下面 类定义上面
import java.util.Scanner;//表示只引入scanner类
import java.util.*;//表示引入java.util下的所有类
//建议要用哪个类就单独导 全部导入会影响编译效率
//这里如果导自己写的外部包只能导入public类
//同包的类不用导就可以直接用

//包
//包的作用
//区分相同名的类
//包用来保存类 当类很多时可以方便的管理类
//控制访问范围

//同包类名不可以重复
//包里可以有子包

//包命名规范
//com.公司名.项目名.业务模块名
//每部分不能以数字开头

//常用包
//java.lang基本包 默认引入
//java.util工具包
//java.net网络包
//java.awt界面开发gui

//导包 往上看

//案例 用系统提供的类完成数组排序
public class aIDEA和包l访问修饰符 {
    public static void main(String[] args) {
        int[] arr={555,3,43,23,786};
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
//访问修饰符  可以修饰属性 方法 类  但修饰类时只有public和默认两种情况
//             本class    同包     子类     不同包
//public         T        T        T        T
//protected      T        T        T
//默认            T        T
//private        T
//子类涉及继承
//同包下的子类可以访问默认，不同包下子类不能访问默认，但可以访问受保护的
//子类与父类在同一个包下，子类可以访问父类默认修饰符修饰的属性
//子类与父类不在同一个包下，子类不可以访问父类默认修饰符修饰的属性

//IDEA
//src存放源码
//out存放编译后的class文件

//自己的快捷键
//ctrl y del当前行
//ctrl / 注释
//ctrl alt l 格式化代码
//alt insert 生成构造器
//ctrl h 查看类的层级关系
//ctrl b 定位到方法
//.var 自动分配变量名
//模板
//F7跳入方法
//F8逐语句
//shift F8 跳出方法
//F9执行到下一个断点






























