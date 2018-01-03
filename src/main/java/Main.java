import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = new ArrayList<>();
        List<Integer> weight = new ArrayList<>();
        int capacity = 0;
        Files.lines(Paths.get("../resources/exemple100.txt")).forEach(lines::add);
        capacity = Integer.parseInt(lines.get(1));
        String[] strings = lines.get(3).substring(0, lines.get(3).length() - 1).replace(" ", "").split(",");
        for (int i = 0; i < strings.length; i++) {
            weight.add(Integer.parseInt(strings[i]));
        }
        int nBin = BinPacking.nextFit(weight, capacity);
        System.out.println(nBin);
        nBin = BinPacking.firstFit(weight, capacity);
        System.out.println(nBin);
    }
}
