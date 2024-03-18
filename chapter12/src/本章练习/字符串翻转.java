package 本章练习;

import java.util.Arrays;

/**
 * @author 王天一
 * @version 1.0
 */
//给定字符串 实现方法public static String reverse(String str ,int start, int end)将字符串指定的位置进行交换
//
class Test{
    public static void main(String[] args) {
        String str="abcdefjhigklmn";
        String str1=null;
        try {
            str1=reverse(str,3,3);
        } catch (Exception e) {//这里捕获
            System.out.println("参数输入有误！");
            return;
        }
        System.out.println("翻转之后为 "+str1);
    }

    public static String reverse(String str,int start,int end)
    {
        //这里要注意的是 要做异常的判断
        if(!(str!=null && start<end && start>=0 && end<str.length()))
        {
            throw new RuntimeException();//往调用方法抛异常
        }
            //因为String是final类 所以先要转成char数组
            char[] arr = str.toCharArray();
            //现在对arr进行翻转操作
            for (int i = start, j = end; i < j; i++, j--) {
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            //然后返回String
            return new String(arr);
        }

}
