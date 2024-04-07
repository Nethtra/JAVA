package Io流;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Properties;
import java.util.function.Predicate;

/**
 * @author 王天一
 * @version 1.0
 */
//标准输入输出流 默认输出流为屏幕 输入流为键盘
//System.in  编译类型 InputStream
//           运行类型 BufferedInputStream
//System.out 编译类型 PrintStream
//           运行类型 PrintStream


//转换流 用于字节流向字符流之间的转换 主要是解决编码问题
//因为字符流不能直接改编码（即读写的编码默认按照java） 所以当java的编码与文本文件的编码不一致时比如读取时 就可能出现乱码
//但是文本文件还是得用字符流读取 转换流就是来解决这个问题的
//转换流将字节流转为字符流 并且能指定编码类型
//InputStreamReader Reader的子类 可以将InputStream包装为Reader
//OutputStreamWriter Writer的子类 可以将OutputStream包装为Writer

class Test4 {
    //演示按照指定编码读出
    @Test
    public void flowConvert() throws IOException {
        String filePath = "C:\\Users\\lenovo\\Documents\\IdeaProjects\\Javanotes\\chapter16\\d.txt";
//这里是先把FileInputStream转成InputStreamReader字符流 然后再转成包装流（因为好用）最后输出
        //不会乱码  如果是正常使用FileReader会乱码                                            //字节转字符 途中指定编码 意思就是改了流的编码
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "gbk"));
        System.out.println(br.readLine());
        br.close();//关闭时关闭外层
    }

    //演示按照指定编码输出
    @Test
    public void flowConvert1() throws IOException {
        String filePath = "C:\\Users\\lenovo\\Documents\\IdeaProjects\\Javanotes\\chapter16\\e.txt";
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "gbk"));
        //一样的 这时就按gbk输出到文件
        bw.write("你好世界");
        bw.close();
    }
}


//打印流 只有输出流 没有输入流
//PrintStream(OutputStream子类)和PrintWriter（Writer子类）
class Test5 {
    @Test
    public void print() throws IOException {
        PrintStream ps = System.out; //可以指定打印流为默认屏幕
        ps.print("hello");
        //print底层用的是write 所以也可以直接用write
        ps.write("hello".getBytes());

        PrintStream ps1 = new PrintStream("C:\\Users\\lenovo\\Documents\\IdeaProjects\\Javanotes\\chapter16\\f.txt");
        //也可以指定为文件
        ps1.print(10);

        System.setOut(ps1);//可以修改系统的打印流的位置
        System.out.println("你看看我现在打印在哪");
    }
    //那个PrintWriter看构造器没看出区别
}


//Properties类
//配置文件的格式具有很强的规律性 即 键=值（kv）
//如果用普通的io流读取会很麻烦 包括输出也是很麻烦
//所以引出了一个专门读写配置文件的类Properties
//而为什么要用配置文件  因为配置文件存储密码用户名等 便于修改
//注意键=值 值默认是String不需要用引号  =两边也不要加空格

class Tstt5 {
    //演示用Properties类读取配置文件
    @Test
    public void propertiesFile() throws IOException {
        Properties properties = new Properties();//创建properties对象
        //load加载指定配置文件 注意参数
        properties.load(new FileReader("src\\mysql.properties"));
        //list数据显示到指定位置
        properties.list(System.out);
        //getProperty(key) 根据key获取value 返回String
        System.out.println(properties.getProperty("psw"));
    }

    //演示用Properties类创建配置文件 并添加kv
    @Test
    public void newProperties() throws IOException {
        Properties properties = new Properties();//先new对象
        //setProperty设置kv到 Properties对象
        //因为Properties是HashTable的子类 所以这里设置kv的规则和集合差不多 key相同就替换value 修改也是用set改就行
        properties.setProperty("user", "王天一");//如果还有中文 保存到配置文件时会存储为unicode码
        properties.setProperty("psw", "123456");
        properties.setProperty("ip", "1.100.86");
        //store才是真正将Properties对象中的kv存到配置文件
        properties.store(new FileWriter("src\\mysql1.properties"), "第二个参数是注释可以加或者置null");
        System.out.println("创建配置文件成功");
    }
}
