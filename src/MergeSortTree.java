import java.util.*;

public class MergeSortTree {

    HashMap<Integer, ArrayList<Integer>> tree;
    int inputSize;


    MergeSortTree(int[] input) {
        tree = new HashMap<>();
        inputSize = input.length - 1;
        buildTree(input, 0, inputSize, 0);
    }

    private ArrayList<Integer> buildTree(int[] input, int rangeStart, int rangeEnd, int treeIndex) {

        if (rangeStart == rangeEnd) {
            ArrayList<Integer> list = tree.getOrDefault(treeIndex, new ArrayList<>());
            list.add(input[rangeStart]);
            tree.put(treeIndex, list);
            return list;
        }


        int mid = getMid(rangeStart, rangeEnd);

        ArrayList<Integer> left = buildTree(input, rangeStart, mid, getLeft(treeIndex));
        ArrayList<Integer> right = buildTree(input, mid + 1, rangeEnd, getRight(treeIndex));
        ArrayList<Integer> currentList = getMergedList(left, right);

        tree.put(treeIndex, currentList);
        return currentList;
    }

    public int query(int start, int end, int k) {
        return query(start, end, 0, inputSize, k, 0);
    }

    private int query(int start, int end, int rangeStart, int rangeEnd, int k, int treeIndex) {
        if (start <= rangeStart && rangeEnd <= end) {

            ArrayList<Integer> list = tree.getOrDefault(treeIndex, new ArrayList<>());
            return searchLowerBound(list, k);
        }

        if (rangeEnd < start || rangeStart > end) {
            return 0;
        }

        int mid = getMid(rangeStart, rangeEnd);
        int left = query(start, end, rangeStart, mid, k, getLeft(treeIndex));
        int right = query(start, end, mid + 1, rangeEnd, k, getRight(treeIndex));

        return left + right;
    }

    private int searchLowerBound(ArrayList<Integer> list, int k) {

        if (list.isEmpty()) {
            return 0;
        }

        int low = 0;
        int high = list.size() - 1;

        while (low < high) {

            int mid = getMid(low, high);

            if (list.get(mid) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }

        }

        return low;
    }


    private ArrayList<Integer> getMergedList(ArrayList<Integer> left, ArrayList<Integer> right) {
        ArrayList<Integer> currentList = new ArrayList<>(left.size() + right.size());

        int li = 0, ri = 0;

        while (li < left.size() && ri < right.size()) {
            if (left.get(li) < right.get(ri)) {
                currentList.add(left.get(li));
                li++;
            } else {
                currentList.add(right.get(ri));
                ri++;
            }
        }

        while (li < left.size()) {
            currentList.add(left.get(li));
            li++;
        }

        while (ri < right.size()) {
            currentList.add(right.get(ri));
            ri++;
        }

        return currentList;
    }

    private int getMid(int start, int end) {
        return start + (end - start) / 2;
    }

    private int getLeft(int i) {
        return 2 * i + 1;
    }

    private int getRight(int i) {
        return 2 * i + 2;
    }


    private int getParent(int i) {
        return i / 2;
    }

}
