package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class BalanceReader {

    private final String filename;

    public BalanceReader(String filename) {
        this.filename = filename;
    }

    public List<Balance> readCsv() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        reader.readLine(); // skip

        List<Balance> results = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(";");
            Balance balance = new Balance();
            balance.id = Integer.valueOf(data[0].trim());
            balance.name = data[1].trim();
            balance.age = Integer.valueOf(data[2].trim());
            balance.balance = Integer.valueOf(data[3].trim());
            balance.prevBalance = Integer.valueOf(data[4].trim());
            balance.avgBalance = Integer.valueOf(data[5].trim());
            balance.freeTransfer = Integer.valueOf(data[6].trim());

            results.add(balance);
        }
        reader.close();

        return results;
    }
}
