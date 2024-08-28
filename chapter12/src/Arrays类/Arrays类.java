package Arrays类;

import com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator;
//注意导的包
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author 王天一
 * @version 1.0
 */
//包含了一系列静态方法 用于操作管理数组
//toString方法 返回数组的字符串形式 比用for简洁还效率高
class Test {
    public static void main(String[] args) {
        Integer[] arr = {1, -90, 48, 18, 62};
        System.out.println(Arrays.toString(arr));
    }
}

//**sort**方法 默认从小到大排序
//要注意的是数组是引用类型  所以排序后内容已经变了
class Test1 {
    public static void main(String[] args) {
        Integer[] arr = {33, 12, 783, -3, 49};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        //但是 这只是普通的排序
        //还可以传入接口来实现 定制排序 Comparator接口  比较器
        //注意到后面的参数是实现了Comparator的匿名内部类 接口里实现了compare方法
        Arrays.sort(arr, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Integer i1 = (Integer) o1;
                Integer i2 = (Integer) o2;
                return i2 - i1;//2-1就是倒序
            }
        });
        //看源码也看不懂现在 先知道这么写
        //体现了接口编程的方式
        System.out.println(Arrays.toString(arr));
    }
}

//再用一个例子来理解一下定制排序
//比如冒泡定制
class Test2 {
    public static void main(String[] args) {
        Integer[] arr = {-1, 43, 21, 74, 33, -90};
        BubbleSort(arr, new Comparator() {//这里写匿名内部类直接定义好compare方法
            @Override
            public int compare(Object o1, Object o2) {
                //先向下转型
                Integer i1 = (Integer) o1;
                Integer i2 = (Integer) o2;
                return i2 - i1;//这里决定了排的顺序
            }
        });
        System.out.println(Arrays.toString(arr));
    }

    //这时候我们传入的参数除了数组 再传入一个接口 让接口中的方法来指定顺序
    public static void BubbleSort(Integer[] arr, Comparator c)//在写这个方法时还没有comparator类
    {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {//把if条件改为接口的方法 根据方法返回的值来排序
                if (c.compare(arr[j], arr[j + 1]) > 0)//这样写好了之后我们在调用时可以用匿名内部类来定义这个方法
                {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

}//来回想一下整体的思路 在定义这个sort方法时想到要多传入一个接口类型的参数
//而我们要让这个传入的对象的方法来决定排的顺序 所以if条件改为调用方法
//再回到main方法中 调用bubblesort时我们直接匿名内部类来定义好类（类里定义好方法）并new好对象传入
//内部类的方法决定了顺序
//现在再回头看test1里的sort方法 会清晰一点

//再来看其他的一些方法
class Test3 {
    public static void main(String[] args) {
        //1binarySearch 二分查找数组元素并返回索引 要求数组必须已经升序排列
//如果没有找到的话 看源码是 return -(low + 1);  // key not found.
//就是返回应该在的位置+1然后取反
        Integer arr[] = {12, 34, 78, 491};
        int index = Arrays.binarySearch(arr, 35);//注意参数
        System.out.println(index);

        //2copyOf 数组元素复制 从一个数组拷贝元素到另一个数组
        Integer[] arr1 = Arrays.copyOf(arr, arr.length);//前面是源 后面是要的长度
        System.out.println(Arrays.toString(arr1));
        //注意如果长度大于原数组会在后面加null 长度<0会抛异常

        //3fill 数组填充 把所有元素都改为指定元素
        Arrays.fill(arr1, 10);//前面是数组 后面是要填充为的内容
        System.out.println(Arrays.toString(arr1));

        //4equals 比较两个数组元素是否完全相同（索引也要一样）返回布尔值
        Integer arr2[] = {12, 34, 491, 78};
        System.out.println(Arrays.equals(arr, arr2));

        //5atList 将数组转换为List集合 返回的编译类型是List 运行类型是Arrays的内部类ArrayList
        List list = Arrays.asList(2, 4, 5, 6);
        System.out.println(list);
    }
}

//小练习题
//定义Book类里有price和name 然后建几个对象 分别按价格和name长度排个序
class Test4 {
    public static void main(String[] args) {
        Book[] books = new Book[4];
        books[0] = new Book("红楼梦", 70);
        books[1] = new Book("高等数学", 75);
        books[2] = new Book("新华字典", 40);
        books[3] = new Book("人类群星闪耀时", 8.45);
        //按价格排
        Arrays.sort(books, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                //先向下转型
                Book book1 = (Book) o1;
                Book book2 = (Book) o2;
                //这里不直接return book1.getPrice()- book2.getPrice()
                //的原因是返回类型是int
                if (book1.getPrice() - book2.getPrice() > 0)
                    return 1;
                else if (book1.getPrice() - book2.getPrice() < 0)
                    return -1;
                else
                    return 0;
            }
        });
        System.out.println(Arrays.toString(books));//注意别忘了在book类中重写toString方法

        //按名称排
        Arrays.sort(books, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Book book1 = (Book) o1;
                Book book2 = (Book) o2;
                return book1.getName().length() - book2.getName().length();
            }
        });
        System.out.println(Arrays.toString(books));
    }
}

class Book {
    private String name;
    private double price;

    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}