package chapter05控制结构;

import java.util.Scanner;

//循环条件返回都是布尔类型的值
//while和c一样
//注意 i的作用域和c也一样就是在for内
//do while  先执行再判断（至少执行一次）  最后别忘了分号
//while可以一次都不执行
//for
public class 循环02 {
    public static void main(String[] args) {
        //来写一个空心金字塔
        //大约长这样     *
        //            * *
        //           *   *
        //          *     *
        //         *********
        //先联想一下大约和之前c打过的菱形差不多
        Scanner myScanner = new Scanner(System.in);
        int layer = myScanner.nextInt();//layer层数

        //上半
        for (int i = 0; i < layer; i++) {
            for (int j = 0; j < layer - i - 1; j++)//先打前面的空格
                System.out.print(" ");
            for (int k = 0; k < 2 * (i + 1) - 1; k++)//除了前面的空格后面从第一行开始分别要再打13579个字符 所以要循环13579……次
            {
                if (k == 0 || k == 2 * (i + 1) - 2 || i == layer - 1)//k==0和k==2*(i+1)-2是第一个和最后一个*  i==layer-1是最后一行
                    System.out.print("*");
                else
                    System.out.print(" ");//剩下的输出空格
            }
            System.out.println("");
        }

        //下半
        // *     *
        //  *   *
        //   * *
        //    *
        for (int i = 1; i <= layer - 1; i++) {//下面少打一层
            for (int j = 1; j <= i; j++)//先空格
                System.out.print(" ");
            for (int k = 2 * (layer - 1) - 1; k > 2 * (i - 1); k--) {//看不懂了
                if (k == 2 * (layer - 1) - 1 || k == 2 * (i - 1) + 1)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println("");
        }
    }
}

//java中的break有标签一说  但不提倡使用
//就是break使可以指定break的层数  标签名可以自己定  但也要符合规则
//举例
//abc2:
//{
//      abc1:
//        {
//              {
//                  break abc1;//表示break abc1以下的这块循环
//              }
//          }
// }
//continue 也有标签 指定退出本次哪层循环
//java中的return   退出方法  如果用在主方法中，表示退出程序
//java的字符串比较     用equals 方法   将字符串与指定的对象比较   返回true或false
class dingzhen {
    public static void main(String[] args) {
        String name = "丁真";
        System.out.println("以西".equals(name));//推荐 避免空指针
        System.out.println(name.equals("丁真"));
    }
}