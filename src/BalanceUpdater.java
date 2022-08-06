package src;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BalanceUpdater {

    public void updateAvgBalances(List<Balance> balances) {
        ExecutorService es = Executors.newCachedThreadPool();
        for (Balance balance : balances) {
            es.submit(() -> {
                updateAvgBalance(balance);
            });
        }
        es.shutdown();
    }

    public void updateBenefits(List<Balance> balances) {
        ExecutorService es = Executors.newCachedThreadPool();
        for (Balance balance : balances) {
            es.submit(() -> {
                updateBenefit(balance);
            });
        }
        es.shutdown();
    }

    public void updateAvgBalance(Balance balance) {
        balance.avgBalance = (balance.prevBalance + balance.balance) / 2;
        balance.thread1 = ThreadHelper.getThreadInfo();
    }

    public void updateBenefit(Balance balance) {
        if (balance.balance >= 100 && balance.balance <= 150) {
            balance.freeTransfer = 5;
            balance.thread2a = ThreadHelper.getThreadInfo();
        } else if (balance.balance > 150) {
            balance.freeTransfer = 25;
            balance.thread2b = ThreadHelper.getThreadInfo();
        }
    }
}
