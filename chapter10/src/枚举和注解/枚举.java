package 枚举和注解;

//Enumeration
//枚举还是类 当一个类的对象已经确定为有限的几个值而且需要只读不能修改时 可以设计成枚举类 比如season季节类
//枚举是一种特殊类一组常量的集合
//实现方式
//1自定义枚举
//属性 构造器私有化 去掉set方法
//在类的内部创建对象用 public static final修饰 对象名要大写
//这里联想一下单例设计模式
//2使用enum关键字
//用enum替代class
//属性 构造器私有化 删set方法
//枚举的对象常量写在最前  算了直接看例子
@SuppressWarnings({"all"})
class Test0 {
    public static void main(String[] args) {
        System.out.println(Season.AUTUMN);
        System.out.println(Season.SPRING.ordinal());
    }
}

@SuppressWarnings({"all"})
enum Season {
    SPRING("春"), SUMMER("夏"), AUTUMN("秋"), WINTER("冬");//用，隔
    //如果使用无参构造器可以省略（）
    private String season;

    private Season(String season) {
        this.season = season;
    }

    public String getSeason() {
        return season;
    }

    @Override
    public String toString() {
        return "season{" +
                "season='" + season + '\'' +
                '}';
        //注意也可以根据需求改写成return season;
    }
}

//javap反编译class文件可以看出当使用enum关键字时默认会隐式地继承一个叫Enum的类所以不能再继承其他类 而且你定义的枚举是一个final类
//但可以接接口
@SuppressWarnings({"all"})
enum Gender {
    BOY, GIRL;
}//这样没错 构造器默认私有化了

//Enum中的一些方法
//toString() 是return name 返回枚举常量名 可以在enum重写
//name() 返回枚举常量名 如果toString不重写的话就和name一个效果
//ordinal() 返回当前枚举常量的位置号
//values 返回枚举类的所有常量 以枚举类型的数组的形式
//valueOf("") 将字符串转换成枚举常量 要求字符串必须为已有枚举常量名
//compareTo(枚举常量) 比较两个枚举常量的编号 返回前-后
//下面是这些方法的实验
@SuppressWarnings({"all"})
class Test1 {
    public static void main(String[] args) {
        Season autumn = Season.SPRING;
        System.out.println(autumn.name());
        System.out.println(autumn.ordinal());
        Season[] season = Season.values();//接收
        for (Season seasons : season)//增强for  就是把season的值拿出来放到seasons里 循环一次就放一次
        {
            System.out.println(seasons);//这里注意Season里重写了toString所以打出来的是信息
        }
        //原理是调用方法后去匹配传入的字符串与枚举常量名 所以这个newSeason就是一个新的引用
        Season newSeason = Season.valueOf("SPRING");
        System.out.println(newSeason);
        System.out.println(Season.AUTUMN.compareTo(Season.SPRING));
    }

}

//注解 https://www.runoob.com/w3cnote/java-annotation.html