package chapter05控制结构;//选择 分支 和循环  和c基本一样

import java.util.Scanner;

//选择
//if
public class 选择和分支01 {
    public static void main(String[] args) {
        Scanner myscanner = new Scanner(System.in);
        int age = myscanner.nextInt();

        if (age > 18)
            System.out.println(age);
    }
//来看一种你之前忽略的情况
//比如 boolean b=true;        在java中只要有表达式  就会有值返回  c这么看来应该也一样
//if(b=false)     这条语句会赋值b  但是返回的是false因为赋的是false 如果赋其他值的话返回的就是true if就会执行 c也一样
//所以b被赋值了但这条if不会执行


//分支
//switch
//switch(表达式)表达式和下面的值数据类型必须一致或者能自动转换
//还有表达式的返回值必须是 byte short int enum String
//case 表达式的值（常量）（必须常量或者常量表达式）:
//break;
//default 可选
//穿透
//可以堆多个case 如case3:
//               case4:
//               case5:  printf("春天")；
//                        break;
}