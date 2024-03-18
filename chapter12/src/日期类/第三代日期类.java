package 日期类;

import javax.swing.text.DateFormatter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
/**
 * @author 王天一
 * @version 1.0
 */
//前两代日期类线程都不安全
//第三代日期类有三个
//LocalDate类 包含日期字段
//LocalTime类 包含时间字段
//LocalDateTime 包含日期和时间字段

class Test2{
    public static void main(String[] args) {
        //用now方法来返回一个当前日期时间的对象
        LocalDateTime ldt=LocalDateTime.now();
        System.out.println(ldt);//可以直接输出
        //也可以调get方法
        System.out.println(ldt.getYear()+"-"+ldt.getMonthValue()+"-"+ldt.getDayOfMonth()
        +" "+ldt.getHour()+":"+ldt.getMinute()+":"+ldt.getSecond());

        //注意到如果直接输出ldt长得也不好看
        //这里也可以对他进行格式化 需要用到DateTimeFormatter类 （也有DateFormatter……）
        //与LocalDateFormat类操作差不多 好吧不一样
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年 MM月 dd日 E HH:mm:ss");
        String str=dtf.format(ldt);
        System.out.println(str);

        //第三代日期类中还有加减天月数等方法 看源码看源码
        LocalDateTime ldt1=ldt.plusHours(123455);//注意返回对象接收
        System.out.println(dtf.format(ldt1));
        LocalDateTime ldt2=ldt.minusDays(134789);
        System.out.println(dtf.format(ldt2));
    }
}
//此外还有一个类 Instant时间戳
//提供了一系列与Date转换的方式
class Test3{
    public static void main(String[] args) {
        Date d=new Date();
        Instant i=d.toInstant();//Date转Instant
        Date d1 = Date.from(i);//Instant转Date
        //这样newInstant的对象
        Instant i1=Instant.now();
        System.out.println(i1);
    }
}
