import java.util.ArrayList;
import java.util.List;

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
        ArrayList<Integer> arrayBins = new ArrayList<>();
        for (int i = 0; i < weight.size(); i++) {
            int j;
            for (j = 0; j < arrayBins.size(); j++) {
                if (arrayBins.get(j) >= weight.get(i)) {
                    arrayBins.set(j, arrayBins.get(j) - weight.get(i));
                    break;
                }
            }
            if (j == arrayBins.size()) {
                arrayBins.add(capacity - weight.get(i));
            }
        }
        return arrayBins.size();
    }

    static int bestFit(List<Integer> weight, int capacity) {
        ArrayList<Integer> arrayBins = new ArrayList<>();
        for (int i = 0; i < weight.size(); i++) {
            int j;
            int min = capacity;
            int binIndex = 0;
            for (j = 0; j < arrayBins.size(); j++) {
                if (arrayBins.get(j) >= weight.get(i) && arrayBins.get(j) - weight.get(i) < min) {
                    binIndex = j;
                    min = arrayBins.get(j) - weight.get(i);
                }
            }
            if (min == capacity) {
                arrayBins.add(capacity - weight.get(i));
            } else {
                arrayBins.set(binIndex, arrayBins.get(binIndex) - weight.get(i));
            }
        }
        return arrayBins.size();
    }

    static int worstFit(List<Integer> weight, int capacity) {
        ArrayList<Integer> arrayBins = new ArrayList<>();
        for (int i = 0; i < weight.size(); i++) {
            int j;
            int max = -1;
            int binIndex = 0;
            for (j = 0; j < arrayBins.size(); j++) {
                if (arrayBins.get(j) >= weight.get(i) && arrayBins.get(j) - weight.get(i) > max) {
                    binIndex = j;
                    max = arrayBins.get(j) - weight.get(i);
                }
            }
            if (max == -1) {
                arrayBins.add(capacity - weight.get(i));
            } else {
                arrayBins.set(binIndex, arrayBins.get(binIndex) - weight.get(i));
            }
        }
        return arrayBins.size();
    }

    static int almostWorstFit(List<Integer> weight, int capacity) {
        ArrayList<Integer> arrayBins = new ArrayList<>();
        for (int i = 0; i < weight.size(); i++) {
            int j;
            int max = -1;
            int max2 = -1;
            int binIndex = 0;
            int binIndex2 = 0;
            for (j = 0; j < arrayBins.size(); j++) {
                if (arrayBins.get(j) >= weight.get(i) && arrayBins.get(j) - weight.get(i) > max2) {
                    if (arrayBins.get(j) - weight.get(i) > max) {
                        binIndex2 = binIndex;
                        binIndex = j;
                        max2 = max;
                        max = arrayBins.get(j) - weight.get(i);
                    } else {
                        binIndex2 = j;
                        max2 = arrayBins.get(j) - weight.get(i);
                    }
                }
            }
            if (max == -1) {
                arrayBins.add(capacity - weight.get(i));
            } else if (max2 == -1) {
                arrayBins.set(binIndex, arrayBins.get(binIndex) - weight.get(i));
            } else {
                arrayBins.set(binIndex2, arrayBins.get(binIndex2) - weight.get(i));
            }
        }
        return arrayBins.size();
    }
}
