/**
 * java文档注释
 *
 * @author wty
 * @version 0.0
 * 可以用cmd javadoc解析生成网页文件
 * 示例：javadoc -d 文件位置文件夹名（想存放的地方） -x -y 源文件名
 */
//https://www.cnblogs.com/boring09/p/4274893.html 关于文档注释
package chapter0102;

//表示hello是一个类   是一个public公有的类
//{}表示类的开始结束
public class hello {
    public static void main(String[] args)
    //表示一个主方法  程序的入口    {}方法的开始和结束
    {
        System.out.println("hello world!");//输出
    }
}

class dog {
    public static void main(String[] args) {
        System.out.println("hello dog!");
    }
}
//可用cmd运行 查看cmd的属性是GBK编码   当java中出现中文时 如果想用cmd编译运行需要修改编码为GBK才行
//注意进入cmd的方式 从文件地址
//使用 javac空格文件名（hello.java）编译
//然后 java空格(hello)运行
//执行流程  .java   javac命令编译后生成  .class字节码文件  再用java命令    使装载到jvm运行

//Java Development Kit
//JDK=JRE+开发工具
//JRE=JVM（虚拟机）+核心类库  想运行只装jre就行
//Java Runtime Environment

//细节
//源文件基本组成部分是类  入口是main方法 固定格式
//一个源文件最多一个public类 可以有多个其他类 可以有多个主方法
//主方法也可放在别的类中运行
//源文件的名称必须是public类的名称
//一个类中只能有一个主方法

//java转义字符 和c差不多
//但\r （回到某一行的开头）  会覆盖原来的内容   \b也是（退格）
class a {
    public static void main(String[] args) {
        System.out.println("转义字ddd符\ndggg\r555\b");//
    }
}
//单行注释
/*
多行注释
*/

//代码规范
//行宽不超过80
//对类和方法的注释用文档注释
//非javadoc注释是写给维护者看的
//运算符两边加空格为了美观

//DOS磁盘操作系统   可用cmd对其发送指令
//两个重要概念
//首先  一个文件夹就是一个目录
//相对路径  从当前目录开始形成的路径
//绝对路径  从顶层开始形成的路径
//举例  目前我在C:\Users\wang8\Documents\Visual Studio 2022目录下
//现在要跳转到C:\Users\wang8\.vscode\extensions目录下
//相对路径就是  ../../.vscode/extensions   ../表示往上一目录跳转
//绝对路径就是C:\Users\wang8\.vscode\extensions


//常用dos命令
//查看当前目录内容 dir
//切换目录 cd+路径     注意cd只能在同一盘符下工作
//切盘 直接举例  d:切到D盘
//返回上一级 cd ..
//切回根目录 cd \
//查看子目录（树状图)路径+tree
//清屏cls
//退出 exit
//创建目录 md
//删除目录 rd
//拷贝文件 copy
//删除文件 del
//输入内容到文件 echo(同时创建文件并输入内容)
//剪切 move