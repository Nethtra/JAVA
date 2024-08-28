package 泛型;

import org.junit.jupiter.api.Test;

/**
 * @author 王天一
 * @version 1.0
 */
//当类中有很多方法需要测试就要都写在main中测试 因此代码要来回注销、创建对象等 还可能相互影响
//JUint用于直接运行某一个方法 相当于开了一块小main来专门运行这一个方法
//语法@Test 前提是包中不能有叫Test的类 然后Alt Enter引入JUnit 之后就可以用了
class Test6 {
    public static void main(String[] args) {

    }

    @Test
    public void m1() {
        System.out.println(1);
    }

    @Test
    public void m2() {
        System.out.println(2);
    }
}
//本章作业p567