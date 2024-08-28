package 本章练习;

import java.util.Scanner;

/**
 * @author 王天一
 * @version 1.0
 */
//实现一个用户注册系统 要求用户名为2-4位 密码6位纯数字 邮箱要有@和. 输入正确提示注册成功 否则生成异常对象
class Test1 {
    public static void main(String[] args) {
        //先输入
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名……");
        String username = scanner.next();
        System.out.println("请输入密码……");
        String password = scanner.next();
        System.out.println("请输入邮箱……");
        String email = scanner.next();

        try {
            register(username, password, email);
            System.out.println("注册成功！");//没出异常会执行这
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void register(String un, String psw, String em) {
        if (un == null || psw == null || em == null)//判断输入为空
        {
            throw new RuntimeException("字段不能为空");
        }
        if (!(un.length() >= 2 && un.length() <= 4))//这里判断用户名
        {
            throw new RuntimeException("用户名不合法");
        }
        if (!(psw.length() == 6 && isDigital(psw)))//判断密码 注意新写了个方法来判断是不是纯数组 返回boolean
        {
            throw new RuntimeException("密码不合法");
        }

        int a = em.indexOf('@');//邮箱
        int b = em.indexOf('.');
        if (!(a > 0 && b > a + 1)) {
            throw new RuntimeException("邮箱不合法");
        }

    }

    public static boolean isDigital(String psw) {
        try {
            Integer.parseInt(psw);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
//写完之后顺便感受一下抛异常的作用
//美中不足的是都输错的话只会提示一次异常 这个还没找到办法 应该是catch的问题 只能catch类型不同的
//在try块之后可以跟随一个或多个catch块。每个catch块必须包含一个不同类型的异常处理程序。