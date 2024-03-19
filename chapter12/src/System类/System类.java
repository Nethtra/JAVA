package System类;

import java.util.Arrays;

/**
 * @author 王天一
 * @version 1.0
 */
//System类的方法
//exit(0)表示程序正常退出
//currentTimeMillis 返回当前时间距离时间戳的毫秒数
//arraycopy 数组拷贝 下面展示一下
class Test {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int[] arr1 = new int[3];
        System.arraycopy(arr, 0, arr1, 0, arr.length);
        //解释一下5个参数
        //1源数组
        //2源数组从哪个下标开始拷贝
        //3目标数组
        //4目标数组从哪个下标开始粘贴
        //5拷贝长度  越界会抛异常
        System.out.println(Arrays.toString(arr1));
        System.out.println(System.currentTimeMillis());
    }
}
//Arrays.copyOf底层用的也是arraycopy 但一般还是用Arrays.copyOf