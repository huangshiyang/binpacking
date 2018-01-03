import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class BinPacking {

    static int nextFit(List<Integer> weight, int capacity) {
        int nBin = 0;
        int binRemain = capacity;
        for (int i = 0; i < weight.size(); i++) {
            if (weight.get(i) > binRemain) {
                nBin++;
                binRemain = capacity - weight.get(i);
            } else {
                binRemain -= weight.get(i);
            }
        }
        return nBin;
    }

    static int firstFit(List<Integer> weight, int capacity) {
        ArrayList<Integer> arrayBins = new ArrayList<Integer>();
        arrayBins.add(capacity);
        int j = 0;
        for (int i = 0; i < weight.size(); i++) {
            j = isPlace(weight.get(i), arrayBins);
            if (j >= 0) {
                arrayBins.set(j, arrayBins.get(j) - weight.get(i));
            } else {
                arrayBins.add(100);
            }
        }
        return arrayBins.size();
    }

    static int bestFit(List<Integer> weight, int capacity) {
        return 0;
    }

    static int worstFit(List<Integer> weight, int capacity) {
        return 0;
    }

    static int almostWorstFit(List<Integer> weight, int capacity) {
        return 0;
    }

    static int isPlace(Integer weight, List<Integer> arrayBins) {
        for (int j = 0; j < arrayBins.size(); j++) {
            if (weight < arrayBins.get(j)) {
                return j;
            }
        }
        return -1;
    }
}
