package 日期类;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;//导入Date类
/**
 * @author 王天一
 * @version 1.0
 */
//这是第一代日期类
class Test1{
    public static void main(String[] args)throws ParseException{
        //1
        Date d1 = new Date();//无参构造器返回的是系统当前的时间
        System.out.println(d1);//只不过默认格式有点不一样
        //要改格式的话 创建SimpleDateFormat对象再调用方法来改 **看api文档的格式**
        //这里要用构造器指定格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy年 MM月 dd日 E HH:mm:ss");
        String str=format.format(d1);//然后调format方法转换 注意返回的是字符串
        System.out.println(str);

        //2
        //看源码还有个有参构造器 参数是毫秒long 返回一个距时间戳的时间
        Date d2 = new Date(100000);
        System.out.println(d2);

        //3可以把一个格式化的String转换成Date
        //还是要用到SimpleDateFormat的对象 但是注意给定字符串格式必须与SimpleDateFormat中定义的格式一样
        //这里会处异常 可以抛一下
        Date d3= format.parse("2023年 02月 02日 星期四 12:39:40");
        System.out.println(d3);
        System.out.println(format.format(d3));//这是再转回去
    }
}
