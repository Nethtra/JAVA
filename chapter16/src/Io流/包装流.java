package Io流;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author 王天一
 * @version 1.0
 */
//包装流（处理流）都遵循前面讲的修饰器模式

//BufferedReader  字符缓冲输入流
//BufferedWriter  字符缓冲输出流
//我们尝试来用BufferedReader来读一个文件
class Test2 {
    @Test
    public void FilesRead() {
        BufferedReader br = null;
        try {                                       //传入一个文件节点流对象
            br = new BufferedReader(new FileReader("src\\b.txt"));
            String str;
            while ((str = br.readLine()) != null)//readline方法一次读取一行 返回一个字符串 到结尾返回null
                //看似BufferReader在读取 实际上还是FileReader在读取
                System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();//关闭流只需关闭包装流 会自动调用节点流的close
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //BufferedWriter基本同理 要注意覆盖还是追加 true写在节点流里

    //尝试用BufferedReader与BufferedWriter拷贝文件
    //要注意的是 Reader Writer只能操作文本文件 不能操作二进制文件（图片 视频 word等）
    @Test
    public void fileCopy() {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\lenovo\\Documents\\IdeaProjects\\Javanotes\\chapter16\\b.txt"));
            bw = new BufferedWriter(new FileWriter("C:\\Users\\lenovo\\Documents\\IdeaProjects\\Javanotes\\chapter16\\c.txt"));
            String str;
            while ((str = br.readLine()) != null) {
                bw.write(str);
                bw.newLine();// newLine插入换行  这里注意读完一行要插入换行 否则文件会出错
            }
            System.out.println("拷贝成功");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


//刚才讲的是字符缓冲流
//现在来看字节缓冲流
//BufferedInputStream可以放InputStream子类类型的节点流
//BufferedOutputStream可以放OutputStream子类类型的节点流
//字节缓冲流拷贝 基本一样
//字节流能处理文本文件（中文不一定会乱码）和二进制文件
//关于中文乱码 https://blog.csdn.net/weixin_44720982/article/details/124314583

//对象流
//有时在保存数据时 我们要把数据类型和数据的值都保存到文件中 这样就叫序列化
//并且能供从文件恢复  这就是反序列化    比如普通保存100只是保存了100这个值 并没有指定他的数据类型
//为了让对象支持序列化 则必须让类序列化 就必须实现Serializable接口或者Externalizable接口
//ObjectInputStream 提供反序列化功能
//ObjectOutputStream 提供序列化功能

//现在我们演示一下用ObjectOutputStream将基本数据类型数据和一个Dog对象序列化
class Dog implements Serializable {//注意对象要想序列化，所在类就得实现 Serializable
    private String name;
    private int age;
    private static final long SerialVersionUID = 1L;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Test3 {
    @Test
    public void objectSerialization() {
        ObjectOutputStream oos = null;
        try {                                                         //这里节点流的文件类型可以随便定 因为就不是按文本存的
            oos = new ObjectOutputStream(new FileOutputStream("src\\data.txt"));
            oos.writeInt(100);//会先自动装箱都
            oos.writeUTF("hello world");//注意方法名
            oos.writeDouble(1.1);
            oos.writeChar('a');//因为基本数据类型都有实现Serializable接口 所以可以直接序列化
            oos.writeObject(new Dog("tom", 10));//传入一个对象
            System.out.println("数据序列化保存完毕");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //现在演示对刚才的反序列化
    @Test
    public void objectDeserialization() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("src\\data.txt"));
            System.out.println(ois.readInt());//方法会返回对应类型的数据
            System.out.println(ois.readUTF());
            System.out.println(ois.readDouble());
            System.out.println(ois.readChar());//注意序列化的顺序要和反序列化的顺序一致   否则报错
            //而且序列化之后 类比如Dog类不能再改动
            System.out.println(ois.readObject());//会出异常 直接throw 或者加到catch里
            //                  运行类型是dog编译类型是obj
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

//细节
//序列化类中可以添加private static final long SerialVersionUID=1L;来提高兼容性 这样序列化后再改类就不会报错了
//static 和transient成员不能被序列化（因为根本没那个方法） 但是不会报错
//序列化对象时要求里面的属性‘的类型’也实现序列化接口
//序列化有可继承性即如果父类实现了序列化接口  那么子类默认也实现序列化接口