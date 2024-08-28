package Io流;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author 王天一
 * @version 1.0
 */
//文件在程序中以流的形式来操作
//数据从 Java程序（内存）到文件  叫输出流
//反过来叫输入流    就是以内存来看

//创建文件 三种方法
class newFile {
    public static void main(String[] args) throws IOException {
        //1new File(String pathname)    绝对路径
        File file1 = new File("C:\\Users\\lenovo\\Desktop\\a.txt");//这个只是在内存里new对象
        file1.createNewFile();//这才是真正在硬盘里创建文件
        System.out.println("a创建成功");
        //2new File(File parent , String child) 父目录文件对象+子路径
        File file2 = new File("C:\\Users\\lenovo\\Desktop");
        File file3 = new File(file2, "b.txt");
        file3.createNewFile();
        System.out.println("b创建成功");
        //3new File(String parent,String chile)  父目录+子路径
        File file4 = new File("C:\\Users\\lenovo\\Desktop", "c.txt");
        file4.createNewFile();
        System.out.println("c创建成功");
    }
}

//获取文件信息
//getName返回文件名
//getAbsolutelyPath返回文件绝对路径
//getParent返回文件父级目录
//length返回文件大小（字节）
//exists文件或目录是否存在
//isFile是否是文件
//isDirectory是否是一个目录  这么看来一个目录也可以创建文件对象
//delete删除空目录或者文件
//mkdir创建一级目录
//mkdirs创建多级目录
//creatNewFile创建文件