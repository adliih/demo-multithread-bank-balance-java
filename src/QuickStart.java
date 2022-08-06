package src;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class QuickStart {
    public static void main(String[] args) {
        String inputFilename = new File("").getAbsolutePath().concat("/Before Eod.csv");
        String outputFilename = inputFilename.replace("Before Eod.csv", "After Eod.csv");
        try {
            BalanceReader balanceReader = new BalanceReader(inputFilename);
            BalanceWriter balanceWriter = new BalanceWriter(outputFilename);
            BalanceUpdater balanceUpdater = new BalanceUpdater();
            GiveawayService giveawayService = new GiveawayService();
            
            List<Balance> balances = balanceReader.readCsv();

            balanceUpdater.updateAvgBalances(balances);
            balanceUpdater.updateBenefits(balances);

            giveawayService.updateEligibleBalances(
                giveawayService.getEligibles(balances)  
            );

            balanceWriter.writeBalances(balances);
        } catch (Exception e) {
            e.printStackTrace();
        };
    }
}