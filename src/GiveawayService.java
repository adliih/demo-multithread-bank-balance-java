package src;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GiveawayService {

    public List<Balance> getEligibles(List<Balance> balances) {
        balances.sort((o1, o2) -> {
            return o2.balance - o1.balance;
        });

        return balances.subList(0, 10);
    }
    
    public void updateEligibleBalances(List<Balance> balances) {
        ExecutorService es = Executors.newFixedThreadPool(8);
        for (Balance balance : balances) {
            es.submit(() -> {
                updateEligibleBalance(balance);
            });
        }
        es.shutdown();
    }

    private void updateEligibleBalance(Balance balance) {
        balance.balance += 10;
        balance.thread3 = ThreadHelper.getThreadInfo();
    }
}
