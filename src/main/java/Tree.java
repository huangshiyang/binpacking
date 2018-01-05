import java.util.List;

public class Tree {
    private Bin[] participants;
    private Integer[] winners;
    private int place;
    private int capacity;
    private int remainPlace;

    public Tree(int capacity) {
        place = 4;
        this.capacity = capacity;
        remainPlace = 4;
        participants = new Bin[place];
        winners = new Integer[place];
        for (int i = 0; i < place; i++) {
            participants[i] = new Bin(this.capacity);
        }
        winners[0] = -1;
        updateAll();
    }

    private void update(int start) {
        int index = 2 * start;
        index -= place;
        if (participants[index].getCurrentSize() < participants[index + 1].getCurrentSize()) {
            winners[start] = index;
        } else {
            winners[start] = index + 1;
        }
        while (true) {
            if (start % 2 == 0) {
                start = start / 2;
            } else {
                start = (start - 1) / 2;
            }
            index = 2 * start;
            if (participants[winners[index]].getCurrentSize() < participants[winners[index + 1]].getCurrentSize()) {
                winners[start] = winners[index];
            } else {
                winners[start] = winners[index + 1];
            }
            if (start == 1) {
                break;
            }
        }
    }

    private void updateAll() {
        for (int i = place - 1; i > 0; i--) {
            int index = 2 * i;
            if (index >= place) {
                index -= place;
                if (participants[index].getCurrentSize() < participants[index + 1].getCurrentSize()) {
                    winners[i] = index;
                } else {
                    winners[i] = index + 1;
                }
            } else {
                if (participants[winners[index]].getCurrentSize() < participants[winners[index + 1]].getCurrentSize()) {
                    winners[i] = winners[index];
                } else {
                    winners[i] = winners[index + 1];
                }
            }
        }
    }

    private void add(int e) {
        int root = 1, index;
        while (true) {
            index = 2 * root;
            if (index > place - 1) {
                break;
            }
            if (participants[winners[index]].canFit(e)) {
                root = index;
            } else {
                root = index + 1;
            }
        }
        index -= place;
        if (participants[index].canFit(e)) {
            if (participants[index].getCurrentSize() == 0) {
                remainPlace--;
            }
            participants[index].add(e);
        } else {
            if (participants[index + 1].getCurrentSize() == 0) {
                remainPlace--;
            }
            participants[index + 1].add(e);
        }
        if (remainPlace == 0) {
            doubleTheSize();
        } else {
            update(root);
        }
    }

    public void addList(List<Integer> items) {
        for (int i : items)
            add(i);
    }

    private void doubleTheSize() {
        int start = place;
        place *= 2;
        remainPlace = place - start;
        Bin[] new_participants = new Bin[place];
        System.arraycopy(participants, 0, new_participants, 0, start);
        for (int i = start; i < place; i++) {
            new_participants[i] = new Bin(capacity);
        }
        participants = new_participants;
        winners = new Integer[place];
        winners[0] = -1;
        updateAll();
    }

    public Bin[] getBins() {
        return participants;
    }
}
