package 大数据处理;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author 王天一
 * @version 1.0
 */
//BigInteger保存很大的整数
class Test1 {
    public static void main(String[] args) {
        //需要new对象并用字符串传入数字
        BigInteger bi = new BigInteger("312568907790433656436511999");
        //加减乘除运算时要用类里相应的方法 自己看源码
        System.out.println(bi);
    }
}

//BigDecimal保存精度很高的小数
class Test2 {
    public static void main(String[] args) {
        //与BigInteger一样要传入字符串
        BigDecimal bd = new BigDecimal("12423.341243123434121341423123486");
        //运算时也要调用方法
        //注意除法
        BigDecimal bd1 = new BigDecimal("1.1");
        //System.out.println(bd.divide(bd1)); 这样除时可能会抛异常（除不尽时）
        //可以改为
        bd.divide(bd1, BigDecimal.ROUND_CEILING);//表示精度保留到被除数
        System.out.println(bd.divide(bd1, BigDecimal.ROUND_CEILING));
    }
}
