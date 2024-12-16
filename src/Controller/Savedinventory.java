package Controller;
import java.io.File;
import java.io.IOException;


public class Savedinventory {
    private int amount;
    private int FKitemid;

    // construvtor to inisilasie savedinevntory object

    public Savedinventory(int amount, int FKitemid) {
        this.amount = amount;
        this.FKitemid = FKitemid;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getFKitemid() {
        return FKitemid;
    }
    public void setFKitemid(int FKitemid) {
    this.FKitemid = FKitemid;
    }
    @Override
    public String toString() {
        return String.format("amount: %d, FKitemid: %d", amount, FKitemid);
    }
}
