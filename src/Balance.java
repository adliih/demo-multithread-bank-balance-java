package src;

public class Balance {
    public int id;
    public String name;
    public int age;
    public int balance;
    public int prevBalance;
    public int avgBalance;
    public int freeTransfer;
    
    public String thread1;
    public String thread2a;
    public String thread2b;
    public String thread3;
    
    @Override
    public String toString() {
        return "Balance: " + 
            this.id + ", " +
            this.name + ", " +
            this.age + ", " +
            this.balance + ", " +
            this.prevBalance + ", " +
            this.avgBalance + ", " +
            this.freeTransfer;
    }
}
