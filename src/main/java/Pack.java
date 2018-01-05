import java.util.*;

public class Pack {
    private List<Integer> items;
    private List<Bin> bins;
    private int capacity;

    public Pack(List<Integer> items, int capacity) {
        this.items = items;
        bins = new ArrayList<>();
        this.capacity = capacity;
    }

    public void nextFit() {
        reset();
        bins.add(new Bin(capacity));
        for (int i : items) {
            if (!bins.get(bins.size() - 1).add(i)) {
                bins.add(new Bin(capacity, i));
            }
        }
    }

    public void firstFit() {
        reset();
        Tree binsTree = new Tree(capacity);
        binsTree.addList(items);
        Bin[] bins = binsTree.getBins();
        for (Bin b : bins)
            if (b.getCurrentSize() != 0)
                this.bins.add(b);
    }

    public void bestFit() {
        reset();
        TreeSet<Bin> binsSet = new TreeSet<>();
        binsSet.add(new Bin(capacity));
        for (int i : items) {
            Bin b = new Bin(capacity, capacity - i);
            b = binsSet.floor(b);
            if (b != null) {
                binsSet.remove(b);
                b.add(i);
                binsSet.add(b);
            } else {
                binsSet.add(new Bin(capacity, i));
            }
        }
        bins.addAll(binsSet);
    }

    public void worstFit() {
        reset();
        PriorityQueue<Bin> binsQueue = new PriorityQueue<>(items.size(), new Comparator<Bin>() {
            @Override
            public int compare(Bin lb, Bin rb) {
                return Integer.compare(lb.getCurrentSize(), rb.getCurrentSize());
            }
        });
        binsQueue.add(new Bin(capacity));
        for (int i : items) {
            Bin b = binsQueue.remove();
            if (!b.add(i))
                binsQueue.add(new Bin(capacity, i));
            binsQueue.add(b);
        }
        bins.addAll(binsQueue);
    }

    public void almostWorstFit() {
        reset();
        PriorityQueue<Bin> binsQueue = new PriorityQueue<>(items.size(), new Comparator<Bin>() {
            @Override
            public int compare(Bin lb, Bin rb) {
                return Integer.compare(lb.getCurrentSize(), rb.getCurrentSize());
            }
        });
        binsQueue.add(new Bin(capacity));
        for (int i : items) {
            if (binsQueue.size() > 1) {
                Bin b = binsQueue.remove();
                Bin b2 = binsQueue.remove();
                if (!b2.add(i)) {
                    if (!b.add(i)) {
                        binsQueue.add(new Bin(capacity, i));
                    }
                }
                binsQueue.add(b);
                binsQueue.add(b2);
            } else {
                Bin b = binsQueue.remove();
                if (!b.add(i)) {
                    binsQueue.add(new Bin(capacity, i));
                }
                binsQueue.add(b);
            }
        }
        bins.addAll(binsQueue);
    }

    @Override
    public String toString() {
        bins.sort(new Comparator<Bin>() {
            @Override
            public int compare(Bin lb, Bin rb) {
                return Integer.compare(lb.getIndex(), rb.getIndex());
            }
        });
        String output = "The number of bins used: " + bins.size() + "\n";
        int a = Math.min(10, bins.size());
        Iterator it = bins.iterator();
        for (int i = 0; i < a; i++)
            output += "Bin " + (i + 1) + ":\t" + ((Bin) it.next()).getContents() + "\n";
        return output;
    }

    private void reset() {
        bins.clear();
        Bin.numberOfBins = 0;
    }

    public int getIdeal() {
        double total = 0;
        for (int i : items)
            total += i;
        return (int) Math.ceil(total / capacity);
    }
}
