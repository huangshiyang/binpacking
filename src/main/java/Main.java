import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void packingAlgorithms(Pack p) {
        long startTime, endTime;

        startTime = System.currentTimeMillis();
        System.out.println("Next Fit:");
        p.nextFit();
        System.out.print(p);
        endTime = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (endTime - startTime) + " ms.\n");

        startTime = System.currentTimeMillis();
        System.out.println("First Fit:");
        p.firstFit();
        System.out.print(p);
        endTime = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (endTime - startTime) + " ms.\n");

        startTime = System.currentTimeMillis();
        System.out.println("Best Fit:");
        p.bestFit();
        System.out.print(p);
        endTime = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (endTime - startTime) + " ms.\n");

        startTime = System.currentTimeMillis();
        System.out.println("Worst Fit:");
        p.worstFit();
        System.out.print(p);
        endTime = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (endTime - startTime) + " ms.\n");

        startTime = System.currentTimeMillis();
        System.out.println("Almost Worst Fit:");
        p.almostWorstFit();
        System.out.print(p);
        endTime = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (endTime - startTime) + " ms.\n");
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("NEED FILE.");
            System.exit(0);
        }
        try {
            for (String s : args) {
                List<String> lines = new ArrayList<>();
                List<Integer> weight = new ArrayList<>();
                int capacity = 0;
                Files.lines(Paths.get(s)).forEach(lines::add);
                capacity = Integer.parseInt(lines.get(1));
                String[] strings = lines.get(3).substring(0, lines.get(3).length() - 1).replace(" ", "").split(",");
                for (String string : strings) {
                    weight.add(Integer.parseInt(string));
                }
                Pack p = new Pack(weight, capacity);
                System.out.println(s + ":\nIdeal number of bins: " + p.getIdeal() + "\n");
                packingAlgorithms(p);
                System.out.println("-----------------------------------\n");
            }
        } catch (IOException e) {
            System.out.println(args[0] + ": There was a problem processing this file.");
        }
    }
}
