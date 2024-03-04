package chapter07;

//方法递归
//来看几个例子
public class b方法递归 {
    public static void main(String[] args) {
        recursion myrecursion = new recursion();
        System.out.println(myrecursion.factorial(6));


        Fibonacci myFibonacci = new Fibonacci();
        System.out.println(myFibonacci.fb(7));

        monkey mymonkey = new monkey();
        System.out.println(mymonkey.peach(1));


        //老鼠出迷宫问题 也是用递归解决
        //用二维数组代替迷宫 规定老鼠的初始位置在(1,1) 走到右下角代表成功
        //0代表可走区域 1代表墙 2代表走过 3代表走过的死路
        //然后就是走的方法  可以有顺序 比如下右上左 不同顺序会影响结果
        //还有一个回溯的问题 就是到了死路会返回重新找路走
        int arr[][] = new int[8][7];
        for (int i = 0; i < 7; i++) {
            arr[0][i] = 1;
            arr[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            arr[i][0] = 1;
            arr[i][6] = 1;
        }//设置四周为墙
        //再来设置几个特殊的墙
        arr[3][1] = 1;
        arr[3][2] = 1;
        arr[2][2] = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }//先输出看看效果
        System.out.println();
        //接下来就是调用函数
        migong findway = new migong();
        findway.mouse(arr, 1, 1);//传入初始位置

        //输出
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        //有点厉害

        System.out.println("===================");
        hanuo tower = new hanuo();
        tower.move(3, 'A', 'B', 'C');
    }

}

class recursion {//阶乘

    public int factorial(int n) {
        if (n == 1)
            return 1;
        else
            return n * factorial(n - 1);
    }
}

//递归必须向退出递归的条件逼近 否则会栈溢出
class Fibonacci {//斐波那契

    public int fb(int n) {
        if (n == 1 || n == 2)
            return 1;
        else
            return fb(n - 1) + fb(n - 2);

    }
}

class monkey {//猴子一天吃一半桃子再多吃一个到第十天（第十天没吃）发现只剩一个了

    public int peach(int day) {
        if (day == 10)
            return 1;//第十天剩下的（吃之前的）
        else
            return (peach(day + 1) + 1) * 2;//第n天吃之前的
    }
}

class migong {//老鼠出迷宫

    public boolean mouse(int arr[][], int i, int j) //注意返回值boolean类型 传参三个
    {//理解一下Boolean返回的作用
        //按照规则来
        if (arr[6][5] == 2)//已经出迷宫
            return true;
        else {
            if (arr[i][j] == 0)//0的情况 先设为走这里2
            {
                arr[i][j] = 2;
                //然后递归判断每个方向的可能性
                //这里体现出设置为boolean返回类型的作用
                //就是如果进一条路最底层返回false的话 顶层就不会进这个方向的if 而且同时也设置了死路3
                //回溯也发生在这里 好好理解
                //这里设置方向
                if (mouse(arr, i + 1, j))//下
                    return true;
                else if (mouse(arr, i, j + 1))//右
                    return true;
                else if (mouse(arr, i - 1, j))//上
                    return true;
                else if (mouse(arr, i, j - 1))//左
                    return true;
                else//上下左右走不通的话
                {
                    arr[i][j] = 3;
                    return false;//告诉上一层走不通
                }
            } else//123的情况不走
            {
                return false;
            }
        }
    }

}

class hanuo {
    //汉诺塔问题
//规则 ABC三个塔 然后起初都在a 要把所有的都移动到c
//一次只能移动一块  只能小的洛在大的上
//这个程序是把步骤求出来
//没听懂
    public void move(int num, char a, char b, char c) {
        if (num == 1)//如果是1个盘
            System.out.println(a + "->" + c);
        else {
            move(num - 1, a, c, b);//上面的整体从a到b借助c
            System.out.println(a + "->" + c);//最下面从a到c
            move(num - 1, b, a, c);//b到c借助a
        }
    }
}
//还有一个八皇后问题 也是递归 不会