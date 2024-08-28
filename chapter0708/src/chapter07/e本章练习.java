package chapter07;

//本章练习
//1 实现方法max 求double数组的最大值并返回
class HomeWork01{
    public static void main(String[] args) {
        double[] arr={};
        DoubleArray doubleArray = new DoubleArray();
        Double res=doubleArray.findTheMax(arr);
        if(res==null)
            System.out.println("数组不能是null或{}!");
        else
            System.out.println(res);
    }
}
class DoubleArray{
    public Double findTheMax(double[] arr)//考虑传入数组为空的情况 返回值设为Double Double是一个类
    {
        if(arr!=null&&arr.length>0)//数组不能是null也不能是{}
        {
            double max = arr[0];
            for (int i = 0; i < arr.length; i++)
            {
                if (arr[i] > max)
                    max = arr[i];
            }
            return max;
        }
        else
            return null;//返回类型为Double 可以接收null
        //这里很巧妙 double就不能接收null
    }
}


//2 看图说话 问输出结果
class Test{
    int count=9;
    public void count1()
    {
        count =10;
        System.out.println("count1="+count);
    }
    public void count2()
    {
        System.out.println("count1="+count++);
    }
    //任何一个类都可以有主方法
    public static void main(String[] args) {
        new Test().count1();//这里new了一个对象但是没有引用接收  new Test()叫匿名对象 匿名对象只能使用一次 然后会销毁
        //然后这个匿名对象调用了count1
        Test t1=new Test();//又new了一个新对象
        t1.count2();
        t1.count2();
        }
    }
//总结
//类与对象定义
//对象在内存中存在形式
//成员方法传参机制
//方法递归调用
//作用域
//方法重载
//可变参数
//构造器
//this