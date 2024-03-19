package Math类;

/**
 * @author 王天一
 * @version 1.0
 */
//基本都是静态方法

//abs   返回绝对值
//pow   求幂并返回double
//ceil  向上取整并返回double
//floor 向下取整并返回double
//round 舍到整数返回long
//sqrt  求平方根返回double
//random返回>=0  <1的一个随机小数
//展开来讲讲random
class Test {
    public static void main(String[] args) {
        //设想如果我们要获取一个范围内的随机数 比如12-18的数
        //因为random原生返回小数 所以肯定会用到强转
        //限制最小12 就在最前加12
        //再来看最大18  18是强转之后的 因为random不会到1 所以这里要加到19 就是加（18-12+1）*Math.random()这个范围是0-7
        //所以最终范围就是>=12 <=18
        //大约就这个意思自己理解 (int)(a+Math.random()*(b-a+1));
        for (int i = 0; i < 10; i++) {
            System.out.println((int) (12 + Math.random() * (18 - 12 + 1)));
        }
    }
}