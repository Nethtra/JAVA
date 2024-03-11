package chapter08;

//java所有的类都是Object类的子类（都继承Object）
//Object类是所有类的父类 含有很多方法
//==与equals对比
//==是一个比较运算符既可以判断基本类型 也可以判断引用类型
//判断基本类型比较值是否相等
//判断引用类型比较地址是否相同

//equals方法  是Object类中的方法 只能判断引用类型 默认比较地址是否相等(object中)
//但是Object的子类中往往重写了该方法 比如String类中是比较的就是字符串的内容是否一样 还有Integer是判断值是否一样
//ctrl b查看源码
//这是Object类的equals
//public boolean equals(Object obj) {
//        return (this == obj);
//        }

//做个练习 自己重写equals方法
//判断两个Person_对象的内容是否相等 如果两个对象的各个属性都一样返回true反之false
class Test_ {
    public static void main(String[] args) {
        Person_ person_1 = new Person_("库克", 50, '男');
        Person_ person_2 = new Person_("库克", 50, '男');
        //让我们先看看没有重写的equals
        //System.out.println(person_1.equals(person_2));结果是false 和题干要求不符 题干要求属性都一样就是true
        //因为Person_类中没有重写equals 这样调用还是调的Object的equals  而他的equals是比较的地址 所以肯定false
        //我觉得这里也能多少体会到一点重写的作用

        System.out.println(person_1.equals(person_2));//重写后返回true
        System.out.println(person_1);
    }
}

class Person_ {
    private String name;
    private int age;
    private char gender;

    //自己重写equals
    public boolean equals(Object obj)//这里实参传过来时就已经向上转型了
    {
        if (obj == this)
            return true;//地址相同直接返回true
        if (obj instanceof Person_) {
            //这里先要向下转型 才能访问到obj的属性（思路就是比obj和this的属性）
            return this.name.equals(((Person_) obj).name) && this.age == ((Person_) obj).age && this.gender == ((Person_) obj).gender;
            //这里我合成了   也可以把向下转型细写出来先
        }
        return false;
    }

    public Person_(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}


//hashCode方法 hashCode();十进制
//以后集合还会用到 先知道
//能提高具有哈希结构的容器的效率
//两个引用指向相同对象则哈希值一样
//反之不一样
//哈希值是根据地址然后转化成一串数字 所以并不是实际的地址
//在学集合时 如果需要的话 hashCode也会重写


//toString方法 toString();
//Object类的toString默认返回全类名（包名和类名）+@+哈希值的十六进制
//子类往往重写toString方法用于返回对象的属性信息 可以用快捷键alt insert重写
//当sout一个对象时 就会默认调用.toString();
class book {
    double price;
    String name;

    @Override
    public String toString() {
        return "book{" +
                "price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}

//finalize方法 finalize();
//当对象被回收时系统自动调用finalize方法 子类可以重写该方法来做一些释放资源的操作
//对象什么时候被回收：当某个对象没有任何引用时 jvm认为对象是一个垃圾对象 会调用垃圾回收机制 并调用finalize方法
//注意的是垃圾回收机制由系统决定是否调用 当一个对象没有任何引用时不一定马上就会调finalize
//可以通过System.gc()手动触发