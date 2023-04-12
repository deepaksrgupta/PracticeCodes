import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class BalanceAccounts {
    public List<String> balanceAccount(List<Pair<String,Integer>> input) throws Exception {

        List<String> result = new ArrayList<>();

        List<Pair<String, Integer>> unBalancedAccounts = new ArrayList<>();

        TreeSet<Pair<String,Integer>> balancedAccounts = new TreeSet<>(new Comparator<Pair<String, Integer>>() {
            @Override
            public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });


        for(Pair<String,Integer> account: input) {

            if(account.getValue() >= 100) {
                balancedAccounts.add(account);
            }else{
                unBalancedAccounts.add(account);
            }
        }


        for(Pair<String,Integer> account: unBalancedAccounts) {

            while(account.getValue() < 100) {


                if(balancedAccounts.isEmpty()) {
                    return result;
                }

                Pair<String,Integer> bigAccount = balancedAccounts.pollLast();

                int balanceToUse = bigAccount.getValue() - 100;

                if(balanceToUse == 0) {
                    throw new Exception("Accounts cannot be balanced");
                }

                if(100 - account.getValue() > balanceToUse) {

                    bigAccount.setValue(bigAccount.getValue() - balanceToUse);

                    account.setValue(account.getValue() + balanceToUse);

                    balancedAccounts.add(bigAccount);

                    //from: US, to: AU, amount: 20
                    result.add("from: " + bigAccount.getKey() + ", to: " + account.getKey() + ", amount:" + balanceToUse);

                }else {

                    int transferAmount = Math.min(balanceToUse, 100 - account.getValue());

                    bigAccount.setValue(bigAccount.getValue() - transferAmount);
                    account.setValue(account.getValue() + transferAmount);

                    balancedAccounts.add(bigAccount);
                    balancedAccounts.add(account);

                    result.add("from: " + bigAccount.getKey() + ", to: " + account.getKey() + ", amount:" + transferAmount);
                }

            }


        }

        return result;
    }

    /*
    At Stripe we keep track of where the money is and move money between bank
accounts to make sure their balances are not below some threshold. This is for
operational and regulatory reasons, e.g. we should have enough funds to pay
out to our users, and we are legally required to separate our users' funds
from our own. This interview question is a simplified version of a real-world
problem we have here.

Let's say there are at most 500 bank accounts, some of their balances are
above 100 and some are below. How do you move money between them so that they
all have at least 100?

Just to be clear we are not looking for the optimal solution, but a working one.

Example input:
     AU: 80
     US: 140
     MX: 110
     SG: 120
     FR: 70

Output:
     from: US, to: AU, amount: 20
     from: US, to: FR, amount: 20
     from: MX, to: FR, amount: 10
     */

    public static void main(String[] args) throws Exception {

        List<Pair<String,Integer>> input = new ArrayList<>();

        /*
             AU: 80
     US: 140
     MX: 110
     SG: 120
     FR: 70
         */
//        input.add(new Pair<>("AU",80));
//        input.add(new Pair<>("US",140));
//        input.add(new Pair<>("MX",110));
//        input.add(new Pair<>("SG",120));
//        input.add(new Pair<>("FR",70));




//        input.add(new Pair<>("AU",80));
//        input.add(new Pair<>("US",100));
//        input.add(new Pair<>("MX",100));
//        input.add(new Pair<>("SG",120));
//        input.add(new Pair<>("FR",100));


//        input.add(new Pair<>("AU",80));
//        input.add(new Pair<>("US",100));
//        input.add(new Pair<>("MX",100));
//        input.add(new Pair<>("SG",100));
//        input.add(new Pair<>("FR",70));

//        input.add(new Pair<>("AU",120));
//        input.add(new Pair<>("US",100));
//        input.add(new Pair<>("MX",100));
//        input.add(new Pair<>("SG",100));
//        input.add(new Pair<>("FR",170));

        /*
        A: 200,
        B: 40,
         C 40,
         D: 90,
         E: 150
         */

        input.add(new Pair<>("A",200));
        input.add(new Pair<>("B",40));
        input.add(new Pair<>("C",40));
        input.add(new Pair<>("D",90));
        input.add(new Pair<>("E",150));


        List<String> result = new BalanceAccounts().balanceAccount(input);


        for (String s: result){
            System.out.println(s);
        }


    }
}
