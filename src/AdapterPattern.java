import java.util.Random;

/*
Structural pattern --> An Adapter Pattern
An Adapter Pattern says that just "converts the interface of a class into another interface that a client wants"
 */

//custom class with properties
class BankDetails{
    private String bankName;
    private String accHolderName;
    private long accNumber;

    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public String getAccHolderName() {
        return accHolderName;
    }
    public void setAccHolderName(String accHolderName) {
        this.accHolderName = accHolderName;
    }
    public long getAccNumber() {
        return accNumber;
    }
    public void setAccNumber(long accNumber) {
        this.accNumber = accNumber;
    }
}

//inorder to extend bank details with new properties for new requirement create a new class which will extend base class and implement new interface with desirable properties
interface CreditCard {
    public void giveBankDetails();
    public void getCreditCard();
}


public class AdapterPattern extends  BankDetails implements CreditCard{

    @Override
    public void giveBankDetails() {
        //custom logic here
        Helper.display(getBankName()+ " "+ getAccHolderName());
    }

    @Override
    public void getCreditCard() {
        //custom logic here
        Helper.display( getBankName()  + " " + getAccHolderName());
    }

    public void setDetails(){
        setAccHolderName("Bart allen");
        setAccNumber(new Random().nextInt(100000));
        setBankName("Central city bank");
    }
}

/*
public static void main(String args[]) {
        AdapterPattern pattern = new AdapterPattern();
        pattern.setDetails();
        pattern.giveBankDetails();
        pattern.getCreditCard();
    }
 */