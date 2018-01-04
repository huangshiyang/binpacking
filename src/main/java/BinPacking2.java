/**
 * Created by salah-eddinealaoui on 04/01/2018.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class BinPacking2 {

    static int bestFit(List<Integer> weight, int capacity) {

        long startTime = System.currentTimeMillis();
        ArrayList<Integer> arrayBins = new ArrayList<>();
        ArrayList<ArrayList<Integer>> bestFitArray = new ArrayList<ArrayList<Integer>>();
        arrayBins.add(capacity);
        for (int i = 0; i <= capacity; i++) {
            bestFitArray.add(new ArrayList<Integer> ());
        }
        bestFitArray.get(capacity).add(0);
        int nombre = 0;
        for (int i = 0; i < weight.size(); i++) {
            nombre = getBestContains(weight.get(i), bestFitArray);
            if (nombre == -1) {
                arrayBins.add(capacity - weight.get(i));
                bestFitArray.get(capacity - weight.get(i)).add(arrayBins.size() - 1);
            }
        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
        return arrayBins.size();
    }

    static int getBestContains(int weight, ArrayList<ArrayList<Integer>> bestFitArray) {
        int bestBins = -1;
        for (int i = weight; i < bestFitArray.size(); i++) {
            bestBins = getBestBin(weight, bestFitArray.get(i));
            if (bestBins != -1) {
                bestFitArray.get(i - weight).add(bestBins);
                bestFitArray.get(i).remove(0);
                return bestBins;
            }
        }
        return -1;
    }

    static int getBestBin(int weight, ArrayList<Integer> bestFitArray) {
        if (!bestFitArray.isEmpty()) {
            if (bestFitArray.get(0) == -1) {
                return -1;
            } else {
                return bestFitArray.get(0);
            }
        }
        return -1;
    }
}
