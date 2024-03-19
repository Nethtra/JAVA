package 本章练习;

/**
 * @author 王天一
 * @version 1.0
 */
//实现程序 输入形式为 Wang T(t)ian Yi的人名 并以 Yi,Wang.T的形式打印
//再次提醒 字符串不能用[] 获取单个字符要用 str.charAt(i);
class Test3 {
    public static void main(String[] args) {
        formatname("Wang Tian Yi");
    }

    public static void formatname(String name) {//首先分割字符串
        String[] arr = name.split(" ");
        //然后我们用format格式化
        String format = String.format("%s,%s.%c", arr[2], arr[0], arr[1].toUpperCase().charAt(0));
        System.out.println(format);//输出
    }

}//test1