import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;

public class Main {
    public static void packingAlgorithms(Pack p) {
        long startTime, endTime;

        System.out.println("Next Fit:");
        startTime = System.nanoTime();
        p.nextFit();
        endTime = System.nanoTime();
        System.out.print(p);
        System.out.println("Elapsed time: " + (double) (endTime - startTime) / 1000000.0 + " ms.\n");

        System.out.println("First Fit:");
        startTime = System.nanoTime();
        p.firstFit();
        endTime = System.nanoTime();
        System.out.print(p);
        System.out.println("Elapsed time: " + (double) (endTime - startTime) / 1000000.0 + " ms.\n");

        System.out.println("Best Fit:");
        startTime = System.nanoTime();
        p.bestFit();
        endTime = System.nanoTime();
        System.out.print(p);
        System.out.println("Elapsed time: " + (double) (endTime - startTime) / 1000000.0 + " ms.\n");

        System.out.println("Worst Fit:");
        startTime = System.nanoTime();
        p.worstFit();
        endTime = System.nanoTime();
        System.out.print(p);
        System.out.println("Elapsed time: " + (double) (endTime - startTime) / 1000000.0 + " ms.\n");

        System.out.println("Almost Worst Fit:");
        startTime = System.nanoTime();
        p.almostWorstFit();
        endTime = System.nanoTime();
        System.out.print(p);
        System.out.println("Elapsed time: " + (double) (endTime - startTime) / 1000000.0 + " ms.\n");
    }

    public static List<Integer> generateWeight(int number, int capacity, int distribution) {
        List<Integer> weight = new ArrayList<>();
        Random randomGenerator = new Random();
        if (distribution == 0) {
            for (int i = 0; i < number; i++) {
                weight.add(randomGenerator.nextInt(capacity) + 1);
            }
        } else if (distribution == 1) {
            for (int i = 0; i < number; i++) {
                weight.add((int) Math.round(randomGenerator.nextGaussian() + capacity / 2));
            }
        }
        return weight;
    }

    public static void testRandomsPack(int capacity, int distributin) throws IOException {
        File fileNextFit = new File("src/main/result/testNextFit.txt");
        File fileFirstFit = new File("src/main/result/testFirstFit.txt");
        File fileBestFit = new File("src/main/result/testBestFit.txt");
        File fileWorstFit = new File("src/main/result/testWorstFit.txt");
        File fileAlmostWorstFit = new File("src/main/result/testAlmostWorstFit.txt");
        File fileIdeal = new File("src/main/result/testIdeal.txt");
        fileIdeal.getParentFile().mkdirs();
        fileNextFit.getParentFile().mkdirs();
        fileFirstFit.getParentFile().mkdirs();
        fileBestFit.getParentFile().mkdirs();
        fileWorstFit.getParentFile().mkdirs();
        fileAlmostWorstFit.getParentFile().mkdirs();
        PrintWriter writerIdeal = new PrintWriter(fileIdeal);
        PrintWriter writerNextFit = new PrintWriter(fileNextFit);
        PrintWriter writerFirstFit = new PrintWriter(fileFirstFit);
        PrintWriter writerBestFit = new PrintWriter(fileBestFit);
        PrintWriter writerWorstFit = new PrintWriter(fileWorstFit);
        PrintWriter writerAlmostWorstFit = new PrintWriter(fileAlmostWorstFit);
        Pack p;
        List<Integer> weight;

        long startTime, endTime, duration;
        for (int i = 100; i <= 1000000; i *= 10) {
            weight = generateWeight(i, capacity, distributin);
            p = new Pack(weight, capacity);
            writerIdeal.println(i + " " + p.getIdeal());
            startTime = System.nanoTime();
            p.nextFit();
            endTime = System.nanoTime();
            duration = endTime - startTime;
            writerNextFit.println(i + " " + p.getNumberOfBins() + " " + (double) duration / 1000000.0);
            startTime = System.nanoTime();
            p.firstFit();
            endTime = System.nanoTime();
            duration = endTime - startTime;
            writerFirstFit.println(i + " " + p.getNumberOfBins() + " " + (double) duration / 1000000.0);
            startTime = System.nanoTime();
            p.bestFit();
            endTime = System.nanoTime();
            duration = endTime - startTime;
            writerBestFit.println(i + " " + p.getNumberOfBins() + " " + (double) duration / 1000000.0);
            startTime = System.nanoTime();
            p.worstFit();
            endTime = System.nanoTime();
            duration = endTime - startTime;
            writerWorstFit.println(i + " " + p.getNumberOfBins() + " " + (double) duration / 1000000.0);
            startTime = System.nanoTime();
            p.almostWorstFit();
            endTime = System.nanoTime();
            duration = endTime - startTime;
            writerAlmostWorstFit.println(i + " " + p.getNumberOfBins() + " " + (double) duration / 1000000.0);
        }
        writerIdeal.close();
        writerNextFit.close();
        writerFirstFit.close();
        writerBestFit.close();
        writerWorstFit.close();
        writerAlmostWorstFit.close();
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("NEED ARGS.");
            System.exit(0);
        } else {
            switch (args[0]) {
                case "algo":
                    List<String> files = new ArrayList<>();
                    files.add("exemples/exemple100.txt");
                    files.add("exemples/exemple500.txt");
                    files.add("exemples/exemple1000.txt");
                    files.add("exemples/monexemple.txt");
                    try {
                        for (String f : files) {
                            List<String> lines = new ArrayList<>();
                            List<Integer> weight = new ArrayList<>();
                            Files.lines(Paths.get(f)).forEach(lines::add);
                            int capacity = Integer.parseInt(lines.get(1));
                            String[] strings = lines.get(3).substring(0, lines.get(3).length() - 1).replace(" ", "").split(",");
                            for (String string : strings) {
                                weight.add(Integer.parseInt(string));
                            }
                            Pack p = new Pack(weight, capacity);
                            System.out.println(f + ":\nIdeal number of bins: " + p.getIdeal() + "\n");
                            packingAlgorithms(p);
                            System.out.println("-----------------------------------\n");
                        }
                    } catch (IOException e) {
                        System.out.println(args[0] + ": There was a problem processing this file.");
                    }
                    break;
                case "stat":
                    Scanner reader = new Scanner(System.in);
                    System.out.println("Number: ");
                    int number = reader.nextInt();
                    System.out.println("Capacity: ");
                    int capacity = reader.nextInt();
                    System.out.println("Distribution: \n1.Uniformly\n2.Normally\n(1/2)");
                    int distribution = reader.nextInt();
                    List<Integer> weight = generateWeight(number, capacity, distribution - 1);
                    Pack p = new Pack(weight, capacity);
                    System.out.println("Random mode:\nNumber: " + number + "\nCapacity: " + capacity + "\nIdeal number of bins: " + p.getIdeal() + "\n");
                    packingAlgorithms(p);
                    System.out.println("-----------------------------------\n");
                    break;
                case "defaultStat":
                    Scanner reader2 = new Scanner(System.in);
                    System.out.println("Capacity: ");
                    int capacity2 = reader2.nextInt();
                    System.out.println("Distribution: \n1.Uniformly\n2.Normally\n(1/2)");
                    int distribution2 = reader2.nextInt();
                    testRandomsPack(capacity2, distribution2 - 1);
                    break;
                default:
                    System.out.println("WRONG ARGS.");
                    break;
            }
        }
    }
}
