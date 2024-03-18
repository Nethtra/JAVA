package 日期类;
import java.util.Calendar;//注意导的包
/**
 * @author 王天一
 * @version 1.0
 */
//这是第二代日期类
//是一个抽象类 构造器被private 只能通过getInstance来获取对象
class Test{
    public static void main(String[] args) {
        Calendar cal=Calendar.getInstance();//获取对象
        System.out.println(cal);//看源码 直接这样输出来是很恐怖的 会把所有字段都输出
        //下面可以自定义输出                                         //这个month默认从0开始所以要加一
        System.out.println(cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+
                cal.get(Calendar.DAY_OF_MONTH)+" "+cal.get(Calendar.HOUR_OF_DAY)+":"+
                cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND));
    }//与Date相比不足是不能格式化
}