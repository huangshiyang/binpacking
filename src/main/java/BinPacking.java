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
        return 0;
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
}
