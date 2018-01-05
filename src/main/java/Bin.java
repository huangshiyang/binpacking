import java.util.ArrayList;
import java.util.List;

public class Bin implements Comparable<Bin> {
    private int currentSize;
    private int capacity;
    private int index;
    protected static int numberOfBins = 0;
    private List<Integer> contents;

    public Bin(int capacity) {
        this.capacity = capacity;
        currentSize = 0;
        index = numberOfBins++;
        contents = new ArrayList<>();
    }

    public Bin(int capacity, int currentSize) {
        this.capacity = capacity;
        this.currentSize = currentSize;
        index = numberOfBins++;
        contents = new ArrayList<>();
        contents.add(currentSize);
    }

    public boolean add(int item) {
        if (currentSize + item > capacity) {
            return false;
        }
        currentSize += item;
        contents.add(item);
        return true;
    }

    @Override
    public int compareTo(Bin b) {
        if (currentSize == b.currentSize) {
            return index - b.index;
        } else {
            return currentSize - b.currentSize;
        }
    }

    public boolean canFit(int item) {
        return capacity - currentSize - item > 0;
    }

    public List<Integer> getContents() {
        return contents;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public int getIndex() {
        return index;
    }
}
