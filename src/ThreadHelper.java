package src;

public class ThreadHelper {

    public static String getThreadInfo() {
        return Thread.currentThread().getThreadGroup().getName() + " " + Thread.currentThread().getName();
    }
    
}
