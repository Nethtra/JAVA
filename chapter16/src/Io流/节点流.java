package Io流;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author 王天一
 * @version 1.0
 */
//用于处理数据传输文件读写网络通讯
//Java中对于各种数据的输入输出操作以流的方式进行 java.io包下提供各种流类和接口
//流的分类
//按数据单位 字节流（按字节）(文本文件) 字符流（按字符）（二进制文件）
//按流向     输入流   输出流
//按角色     节点流  处理流（包装流）
//
//抽象基类 四个 剩下所有的流类都是从这四个上来的
//
//          字节流         字符流
// 输入流   InputStream    Reader
// 输出流   OutPutStream   Writer
//注意子类后缀的规律

//InputStream 所有类字节输入流的超类  常用子类 FileInputStream BufferedInputStream ObjectInputStream
class Test1 {

    //FileInputStream演示  我们从文件读到程序
    @Test
    public void fileRead() {
        //先创建输入流对象
        FileInputStream fis = null;
        try {//这里可能会抛异常 我们包起来
            //要注意文件里不能有中文 因为是按字节读取
            fis = new FileInputStream("src\\a.txt");
            //使用read()方法  从该输入流读取一个字节的数据
            //返回读到的字符的int 如果达到文件的末尾返回 -1 。
            //注意这个read读一个自动进一位  和那个c一样 所以要用到z
            int z;
            while ((z = fis.read()) != -1)//read还会抛异常  这里直接把底下异常类型改为他们的父类
            {
                System.out.print((char) z);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {//无论怎么样 都要close释放

            try {
                fis.close();//但是这里又要抛这b异常  再catch
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //还有一个重载的read(byte[] b) 从该输入流一次读取最多b.length字节的数据 并放到b。
    //这样一次多多个字节 效率会比读一个高
    //返回成功读入的字符数 直到读到到尾 返回-1
    @Test
    public void fileRead1() {
        //先创建输入流对象
        FileInputStream fis = null;
        try {//这里可能会抛异常 我们包起来
            fis = new FileInputStream("src\\a.txt");
            byte[] arr = new byte[8];//读入的数组
            int z = 0;//记录成功读取的长度
            while ((z = fis.read(arr)) != -1)//read还会抛异常  这里直接把底下异常类型改为他们的父类
            {
                System.out.println(new String(arr, 0, z));//看到第一次成功读了8个字节 第二次因为有中文就不一样了
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {//无论怎么样 都要close释放
            try {
                fis.close();//但是这里又要抛这b异常  再catch
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //FileOutPutStream 文件字节输出流
    @Test
    public void fileWrite() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("src\\a.txt", true);
            //有三个重载write方法 如果没有该文件会创建 有则会覆盖 close之前可以一直写
            //如果不想覆盖想要追加继续写的话 可以new的时候 第二个参数(append)改成true
            //fop.write('h');写一个字节
            //fop.write(new String("hello world!").getBytes());//传入一个byte类型的数组
            fos.write(new String("hello world!").getBytes(), 0, 5);
            //                                                  索引起始位置   字节数
            System.out.println("写入成功");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //文件拷贝
    //演示用字节输入输出流将一个文件拷贝到另一个位置
    @Test
    public void fileCopy() {
        FileInputStream fip = null;
        FileOutputStream fop = null;
        try {
            fip = new FileInputStream("C:\\Users\\lenovo\\Documents\\IdeaProjects\\Javanotes\\chapter16\\节点流处理流一览.png");
            fop = new FileOutputStream("C:\\Users\\lenovo\\Documents\\IdeaProjects\\Javanotes\\chapter16\\src\\节点流处理流一览.png");
            //创建输入输出流  分别对应源地址 和拷贝地址
            byte[] arr = new byte[102];//用数组read提高效率
            int len;//因为返回成功个数
            while ((len = fip.read(arr)) != -1)
                fop.write(arr, 0, len);//这里write要用索引和成功长度  不然会传多
            System.out.println("拷贝成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fip.close();
                fop.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //字符输入输出流

    //FileReader 文件字符输入流
    //和FileInputStream基本一样 只不过以字符为单位
    @Test
    public void fileReader() {
        FileReader fr = null;
        try {
            //因为是按字符 就是char 所以现在文件里可以有中文
            fr = new FileReader("src\\a.txt");
            //使用read()方法  每次读单个字符并返回int 如果达到文件的末尾返回 -1 。
            //还有一个read(char[])就是和FileInputStream基本一样  一个byte一个char
            int z;
            while ((z = fr.read()) != -1)//read还会抛异常  这里直接把底下异常类型改为他们的父类
            {
                System.out.print((char) z);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {//无论怎么样 都要close释放

            try {
                fr.close();//但是这里又要抛这b异常  再catch
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


//    FileWriter 文件字符输出流 和FileOutputStream基本一样
//    要注意的是创建流的时候是覆盖模式还是追加模式
//    然后write有五个重载
//    write(int)写入单个字符
//    write(char[])写入指定数组
//    write(char[] off len)写入数组指定部分
//    write(String str)//写入整个字符串
//    write(String off len)//写入字符串的指定部分
//    String.toCharArray()
//    FileWriter使用后必须close或者flush否则不会写入  因为真正写入的操作就在close里
}


//刚才上面讲的四个流都是节点流
//节点流就是对于一个特定的数据进行读写操作
//而包装流可以处理多个节点流 效率更高  例如BufferedWriter就是一个包装流
//里面有一个 private Writer out;属性 可以放多种Writer类型的节点流
//联系
//节点流直接与数据源相连 而包装流使用了修饰器设计模式p623和p624前面一点 不直接与数据源相连
//包装流优点  主要体现在 可以包装节点流 消除不同节点流差异 提供更方便的方法来完成大批量的数据输入输出