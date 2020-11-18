import java.util.Arrays;

/*
links:
https://www.youtube.com/watch?v=RgITNht_f4Q&ab_channel=WilliamFiset
https://www.youtube.com/watch?v=uSFzHCZ4E-8&ab_channel=StableSort
 */
public class FenwickTree {
    private int[] tree;
    private int[] inputArray;

    FenwickTree(int[] array){
        this.inputArray = Arrays.copyOf(array,array.length);
        this.buildTree();
    }

    /*
    build fenwick tree in O(n) time
    because for the givenc current node you need to update value at parent and that's it.
    next time when we reach to parent it will push value to its parents much like how heap building works in O(n)
     */
    private void buildTree(){

        //in fenwick we start with 1 index and ignore 0 element
        tree = new int[this.inputArray.length +1];
        for(int i = 0; i < inputArray.length; i++){
            tree[i+1] = inputArray[i];
        }

        //for each node get its parents and update its value
        for(int i = 1; i < tree.length; i++){

            int parent = getParent(i);

            if(parent < tree.length) {
                tree[parent] += tree[i];
            }

        }
    }

    /*
    finds the sum in the given range in O(logn)
     */
    public int getSum(int start, int end){

        if(start == end){
            return 0;
        }


        //+1 is added because fenwick tree starts with 1
        int startSum = getPrefixSum(start + 1); // finds A[0-Start]

        int endSum = getPrefixSum(end); // finds A[0-End] also need to end+1 because it is being covered in startSum

        return startSum - endSum;
    }

    /*
    updates the value at current index in O(logn)
     */
    public void update(int index, int value){

        if(index >= this.inputArray.length){
            return;
        }

        //get delta with original value
        int delta  = value - this.inputArray[index];
        this.inputArray[index] = value;

        //update fenwick tree with proper index
        updateWithDelta(index+1, delta);
    }

    /*
    updates the fenwick tree by updating all parents nodes
     */
    private void updateWithDelta(int index, int delta){


        while (index < this.tree.length){

            tree[index] += delta;

            //keep updating parent falling the range
            index = getParent(index);
        }

    }

    /*
    finds the prefix sum from 0 to x in logn time
     */
    private int getPrefixSum(int n){

        int sum = 0;

        while ( n > 0 ){
            sum += this.tree[n];

            //get child because we are going towards x to 0
            n  = getChild(n);
        }

        return sum;
    }

    /*
    utility method to find exact location of LSB in Integer representation
     */
    private int getLSBInteger(int n){
        return n & -n;
    }

    /*
    utility func to find the parent of the given node
     */
    private int getParent(int n){
        //note adding gives out parent node
        return n + getLSBInteger(n);
    }

    /*
    utility func to find the child of the given node
     */
    private int getChild(int n){
        //note subtracting gives out child node
        return n - getLSBInteger(n);
    }

    public static void main(String args[]){

        int[] arr  = new int[16];

        for(int i= 0; i < arr.length; i++)
            arr[i] = i+1;

        FenwickTree tree = new FenwickTree(arr);

        System.out.println(tree.getSum(12,10));
    }
}
