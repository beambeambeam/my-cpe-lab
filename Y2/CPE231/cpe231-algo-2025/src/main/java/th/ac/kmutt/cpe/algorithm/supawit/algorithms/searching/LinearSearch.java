package th.ac.kmutt.cpe.algorithm.supawit.searching;

import java.util.ArrayList;

public class LinearSearch<
    T extends Comparable<T>
> implements SearchStrategy<T> {

    public int search(ArrayList<T> list, T target) {
        if (list == null) return -1;
        for (int i = 0; i < list.size(); i++) {
            T item = list.get(i);
            if (item == null && target == null) return i;
            if (
                item != null && target != null && item.compareTo(target) == 0
            ) return i;
        }
        return -1;
    }
}
