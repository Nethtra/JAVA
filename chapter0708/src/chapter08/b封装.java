package chapter08;
//encapsulation
//将抽象出的属性和方法封装在一起 数据被保护在内部 程序的其他部分只有通过授权的操作方法才能对数据进行操作
//作用
//隐藏实现细节
//可以对数据进行验证

//实现步骤
//将属性private化
//提供一个public set方法用于对属性判断并赋值
//提供一个public get方法用于获取属性的值

//通过一道题来理解封装的作用
//题干 创建程序定义两个类 Account和AccountTest Account要求具有属性 姓名（长度2-6）余额（>=20）密码（>=六位）,如果不满足给出提示信息并赋默认值
//在AccountTest中测试
class Account {
    //要满足数据安全性 所以封装
    //private化使只能通过我们本类自己的set方法来赋值
    private String name;
    private double balance;
    private String psw;

    //快捷键alt insert 快速创建get和set方法
    public Account() {

    }//保留一下默认构造器
    //写了有参构造器之后 你不写无参的话不能用无参的了 每次new对象必须带参
    //不提供无参构造器要是有有参构造器，创建类的时候就必须要初始化值 ，有时候不想初始化只是想实例化然后调用方法，并不想直接传参

    public Account(String name, double balance, String psw) {
        //this.name = name;
        //this.balance = balance;
        //this.psw = psw;   如果这样的话初始化调用构造器时还是可以绕过我们下面设的条件
        //就是虽然你在其他类里new了对象后再 account.name=dingzhen是不可以的（因为private）
        //但是用构造器可以初始化 绕过private
        //这时就要在构造器里调我们的方法  就可以设上条件了
        setName(name);
        setBalance(balance);
        setPsw(psw);
    }

    public String getName() {
        return name;
        //看好get方法的形式 要return
    }

    public void setName(String name) {
        //这是set方法的形式 就是赋个值
        //这样就可以加入赋值的规定 保证数据合理与安全


        if (name.length() >= 2 && name.length() <= 6)
            this.name = name;
        else {
            System.out.println("姓名长度必须在2-6范围!已初始化默认姓名丁真!");
            this.name = "丁真";
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance >= 20)
            this.balance = balance;
        else {
            System.out.println("薪水必须>=20!已初始化默认薪水0!");
            this.balance = 0;
        }
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        if (psw.length() >= 6)
            this.psw = psw;
        else {
            System.out.println("密码长度必须大于6位!已初始化密码为123456!");
            this.psw = "123456";
        }
    }
    //当我们这样把set和get方法写完后 对于初始化的数据就有了判断条件 而且只能调用我们的方法来初始化（因为是private）
    //但是还有一个问题 如果我们要写构造器的话
    //往上看

    //最后我们写一个显示信息的方法
    public void show() {
        //这里我们也可以加一个访问权限 有权限才能显示信息
        //同理那些get函数也可以加权限
        System.out.println("姓名: " + name + "  薪水: " + balance + "  密码: " + psw);
    }
}


class AccountTest {
    public static void main(String[] args) {
        Account 孙笑川 = new Account("孙", 2, "222");
        孙笑川.show();
    }
}