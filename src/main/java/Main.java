import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.io.PrintWriter;
import java.io.File;

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

    public static List<Integer> generateWeight(int number, int capacity) {
        List<Integer> weight = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            weight.add(ThreadLocalRandom.current().nextInt(0, capacity + 1));
        }
        return weight;
    }

    public static void testRandomsPack(int capacity) throws IOException {
        File fileNextFit = new File("src/main/result/testNextFit.txt");
        File fileFirstFit = new File("src/main/result/testFirstFit.txt");
        File fileBestFit = new File("src/main/result/testBestFit.txt");
        File fileWorstFit = new File("src/main/result/testWorstFit.txt");
        File fileAlmostWorstFit = new File("src/main/result/testAlmostWorstFit.txt");
        fileNextFit.getParentFile().mkdirs();
        fileFirstFit.getParentFile().mkdirs();
        fileBestFit.getParentFile().mkdirs();
        fileWorstFit.getParentFile().mkdirs();
        fileAlmostWorstFit.getParentFile().mkdirs();
        PrintWriter writerNextFit = new PrintWriter(fileNextFit);
        PrintWriter writerFirstFit = new PrintWriter(fileFirstFit);
        PrintWriter writerBestFit = new PrintWriter(fileBestFit);
        PrintWriter writerWorstFit = new PrintWriter(fileWorstFit);
        PrintWriter writerAlmostWorstFit = new PrintWriter(fileAlmostWorstFit);
        Pack p;
        List<Integer> weight = new ArrayList<Integer>();

        long startTime, endTime, duration;
        for (int i = 100; i < 10000; i+=100) {
            weight = generateWeight(i, capacity);
            p = new Pack(weight, capacity);
            startTime = System.currentTimeMillis();
            p.nextFit();
            endTime = System.currentTimeMillis();
            duration = endTime - startTime;
            writerNextFit.println(i + " " + p.getNumberOfBins() + " " + duration);
            startTime = System.currentTimeMillis();
            p.firstFit();
            endTime = System.currentTimeMillis();
            duration = endTime - startTime;
            writerFirstFit.println(i + " " + p.getNumberOfBins() + " " + duration);
            startTime = System.currentTimeMillis();
            p.bestFit();
            endTime = System.currentTimeMillis();
            duration = endTime - startTime;
            writerBestFit.println(i + " " + p.getNumberOfBins() + " " + duration);
            startTime = System.currentTimeMillis();
            p.worstFit();
            endTime = System.currentTimeMillis();
            duration = endTime - startTime;
            writerWorstFit.println(i + " " + p.getNumberOfBins() + " " + duration);
            startTime = System.currentTimeMillis();
            p.almostWorstFit();
            endTime = System.currentTimeMillis();
            duration = endTime - startTime;
            writerAlmostWorstFit.println(i + " " + p.getNumberOfBins() + " " + duration);
        }
        writerNextFit.close();
        writerFirstFit.close();
        writerBestFit.close();
        writerWorstFit.close();
        writerAlmostWorstFit.close();
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
                testRandomsPack(10);
            }
        } catch (IOException e) {
            System.out.println(args[0] + ": There was a problem processing this file.");
        }
    }
}
