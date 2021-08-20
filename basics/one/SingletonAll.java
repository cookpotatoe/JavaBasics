package basics.one;

/**
 * @Author: cookpotatoe
 * @Date: 2021/8/20 13:57
 * @Description:
 * 1、单例类只能有一个实例。
 * 2、单例类必须自己创建自己的唯一实例。
 * 3、单例类必须给所有其他对象提供这一实例。
 * CSDN : https://blog.csdn.net/jason0539/article/details/23297037
 */
public class SingletonAll {
    //懒汉式单例类.在第一次调用的时候实例化自己
    //线程不安全
    public static class Singleton1 {
        private Singleton1() {}
        private static Singleton1 single = null;
        //静态工厂方法
        public static Singleton1 getInstance() {
            if (single == null) {
                single = new Singleton1();
            }
            return single;
        }
    }

    //实现线程安全的3种方式

    //1、静态内部类
    //利用了classloader的机制来保证初始化instance时只有一个线程，所以也是线程安全的，同时没有性能损耗，所以一般我倾向于使用这一种。
    public static class Singleton2 {
        private static class LazyHolder {
            private static final Singleton2 INSTANCE = new Singleton2();
        }
        private Singleton2 (){}
        public static final Singleton2 getInstance() {
            return LazyHolder.INSTANCE;
        }
    }

    //2、在getInstance方法上加同步
    //在方法调用上加了同步，虽然线程安全了，但是每次都要同步，会影响性能，毕竟99%的情况下是不需要同步的
    public static class Singleton3 {
        private Singleton3() {}
        private static Singleton3 single = null;
        //静态工厂方法
        public static synchronized Singleton3 getInstance() {
            if (single == null) {
                single = new Singleton3();
            }
            return single;
        }
    }

    //3、双重检查锁定
    //在getInstance中做了两次null检查，确保了只有第一次调用单例的时候才会做同步，这样也是线程安全的，同时避免了每次都同步的性能损耗
    public static class Singleton4 {
        private Singleton4() {}
        private static Singleton4 single = null;
        //静态工厂方法
        public static Singleton4 getInstance() {
            if (single == null) {
                synchronized (Singleton4.class) {
                    if (single == null) {
                        single = new Singleton4();
                    }
                }
            }
            return single;
        }
    }

    //饿汉式单例类.在类初始化时，已经自行实例化
    public static class Singleton5 {
        private Singleton5() {}
        private static final Singleton5 single = new Singleton5();
        //静态工厂方法
        public static Singleton5 getInstance() {
            return single;
        }
    }
}
