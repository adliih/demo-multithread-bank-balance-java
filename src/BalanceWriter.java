package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BalanceWriter {

    private final List<String> headers;
    private final String filename;

    public BalanceWriter(String filename) throws IOException {
        this.filename = filename;
        headers = new ArrayList<>();
        initHeaders();
        initFile();
    }

    private void initHeaders() {
        headers.add("id");
        headers.add("Nama");
        headers.add("Age");
        headers.add("Balanced");
        headers.add("Previous Balanced");
        headers.add("Average Balanced");
        headers.add("Free Transfer");
        headers.add("No 1 Thread-No");
        headers.add("No 2a Thread-No");
        headers.add("No 2b Thread-No");
        headers.add("No 3 Thread-No");
    }

    private String balanceToCsvLine(Balance balance) {
        List<String> data = new ArrayList<>();
        data.add(String.valueOf(balance.id));
        data.add(String.valueOf(balance.name));
        data.add(String.valueOf(balance.age));
        data.add(String.valueOf(balance.balance));
        data.add(String.valueOf(balance.prevBalance));
        data.add(String.valueOf(balance.avgBalance));
        data.add(String.valueOf(balance.freeTransfer));
        data.add(String.valueOf(balance.thread1));
        data.add(String.valueOf(balance.thread2a));
        data.add(String.valueOf(balance.thread2b));
        data.add(String.valueOf(balance.thread3));
        return data.stream().collect(Collectors.joining(";"));
    }

    private BufferedWriter createWriter(boolean isAppend) throws IOException {
        return new BufferedWriter(new FileWriter(filename, isAppend));
    }

    private void writeLine(BufferedWriter writer, String line) throws IOException {
        writer.write(line);
        writer.newLine();
        writer.close();
    }

    private void initFile() throws IOException {
        String headersLine = headers.stream().collect(Collectors.joining(";"));
        writeLine(createWriter(false), headersLine);
    }

    public void writeBalances(List<Balance> balances) {
        try {
            balances.sort((o1, o2) -> o1.id - o2.id);
            for (Balance balance : balances) {
                writeLine(createWriter(true), balanceToCsvLine(balance));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
