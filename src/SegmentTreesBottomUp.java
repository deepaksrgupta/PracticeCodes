import java.util.*;
import java.io.*;


public class SegmentTreesBottomUp {

    int[] segmentTree;
    int segmentTreeLeafStart;

    void buildTree(int[] input) {
        segmentTree = new int[2 * input.length];
        segmentTreeLeafStart = input.length;

        //fill up leaf elements
        for (int i = segmentTreeLeafStart, j = 0;  i < 2 * input.length; i++,  j++)
            segmentTree[i] = input[j];

        //fill up intermediates nodes
        for (int i = segmentTreeLeafStart - 1; i > 0; --i)
            segmentTree[i] = segmentTree[i * 2] + segmentTree[i * 2 + 1];
    }

    void updateSegmentTree(int position,int value) {
        //update leaf node
        position += segmentTreeLeafStart;
        segmentTree[position] = value;

        //update intermediate nodes
        while (position > 1) {
            int left  = position;
            int right  = position;

            if (position%2 == 0)
            {
                right = position +1;
            }else{
                left = position -1;
            }

            segmentTree[position/2] = segmentTree[left] + segmentTree[right];
            position /= 2;
        }
    }

    void printTree() {
        System.out.println();
        for (int i: segmentTree)
            System.out.print(i+" ");
    }

    public int sumRange(int l, int r) {
        // get leaf with value 'l'
        l += segmentTreeLeafStart;
        // get leaf with value 'r'
        r += segmentTreeLeafStart;
        int sum = 0;
        while (l <= r) {
            if ((l % 2) == 1) {
                sum += segmentTree[l];
                l++;
            }
            if ((r % 2) == 0) {
                sum += segmentTree[r];
                r--;
            }
            l /= 2;
            r /= 2;
        }
        return sum;
    }

    /*
    public static void main(String[] args) throws Exception {

        int[] input = {1,5,2,4,3,5,2,3,8,3,7,9,10};

//        Random r = new Random();
//
//        for(int i=0;i<input.length;i++)
//        {
//
//        }

        SegmentTrees tree = new SegmentTrees();

        tree.buildTree(input);
        tree.printTree();
        //tree.updateSegmentTree(3,0);
        //tree.printTree();
        //tree.updateSegmentTree(2,0);
        //tree.printTree();
//        System.out.println("\n"+tree.sumRange(3,3));
//        System.out.println("\n"+tree.sumRange(3,4));
//        System.out.println("\n"+tree.sumRange(3,5));
        //System.out.println("\n"+tree.sumRange(2,5));
        //System.out.println("\n"+tree.sumRange(1,5));
        //System.out.println("\n"+tree.sumRange(1,6));
        System.out.println("\n"+tree.sumRange(0,input.length-1));
    }
    */
}
