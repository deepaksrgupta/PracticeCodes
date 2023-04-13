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

    /*
    # Design

## Problem Statement
Imagine you work at a startup with many copies of many different services
running in production. You want to help engineers keep track of metrics like
number of signups, number of requests, number of errors, etc. So you introduce
the concept of "counters". You build a `Track` library that ships with every
service, and anyone can call `Track.increment("some-metric")` in their code to
increment the `some-metric` counter.

This kind of thing is really useful for quickly spotting anomalies. If you
graph number of signups over time and it suddenly goes to zero, then something
is probably wrong. Assume this is mostly an operational tool (for example,
we’re not keeping track of precise dollar amounts for accounting or request
counts for rate limiting). Imagine we mostly want to see the number of
signups per minute for the last 6 hours, or how many fraudulent cards were
detected hourly for the last day.

Starting from just that interface, design everything that happens after
`Track.increment("some-metric")` gets called on a service, from what happens
inside the service to how the data is sent, collected, stored and accessed.
Think about how to support accessing the data, but don’t worry too much about
any actual graphing UI.


## Sample System Diagram
```
+-----------+
| login-srv |--> Track.increment("new-signup")
|           |--> Track.increment("new-login")
+-----------+


+---------------------+
| fraud-detection-srv |
|                     |--> Track.increment("fraudulent-card")
+---------------------+


+----------------+
| some-other-srv |--> Track.increment("some-metric")
|                |--> Track.increment("some-other-metric")
+----------------+
```

## Sample Charts
The final output of the system. For example, these two charts represent
scenarios we might want to display using data from the system:

```
^ number of signups
|
|
|   ▉
| ▉ ▉
| ▉ ▉ ▉
| ▉ ▉ ▉ ▉ ▄
+----------> time

^ number of fraudulent cards
|
|     ▉
|     ▉
|     ▉
|     ▉
| ▄ ▉ ▉ ▄
+--------> time
```


{

}
     */


    /*
    func & non func requirement

func requirement

-> accept a metric from different clients & record that metric counter; write
-> show graph visualization of metrics vs time; read ; give minute granularity

non func requirement
available
reliable
durable


api design

post /increament

increamentMetric(metricName: String) : Boolean


getMetricsCount(startTime: DateTime, endTime: DateTime, granuralityLevel: {MINUTE, HOUR, DAY, MONTH} ) : List<Pair<Integer, DateTime>>


data model design

class Metric {
	id: String
	name: String
	minuteLevelDate: DateTime
	hitsCount: Int
}


capacity estimation (qps/ r:w)

100 services

10,000 metrics

100K * 100 => 10M qps => write requests





high level design


identify & resolve issues
     */
}
