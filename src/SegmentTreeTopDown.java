public class SegmentTreeTopDown {

    private int[] tree;  //store the tree
    private int offSet; //for start of child nodes

    SegmentTreeTopDown(int[] input){
        int nextPowerOf2 = new NextPowerOf2().nextPowerOf2(input.length);

        //if arr length is power of 2 then we wil require 2*n - 1 extra nodes
        tree = new int[nextPowerOf2 * 2 - 1];

        offSet = input.length - 1;

        constructSegmentUtil(input,0,offSet,0);
    }


    /*
    constructs tree in top down manner in O(n)
     */
    private void constructSegmentUtil(int[] input, int low, int high, int treeIndex){

        //leaf node fix and return
        if(low == high){
            tree[treeIndex] = input[low];
            return;
        }

        int mid = getMid(low,high);

        //build up left tree
        int leftChild = getLeftChildIndex(treeIndex);
        constructSegmentUtil(input,low,mid,leftChild);

        //build right tree
        int rightChild = getRightChildIndex(treeIndex);
        constructSegmentUtil(input,mid+1,high,rightChild);

        //fix current parent
        tree[treeIndex] = tree[leftChild] + tree[rightChild];
    }


    /*
    get sum range in O(log(n))
     */
    public int getSumRange(int start, int end){
        return getSumRange(start, end, 0, offSet,0);
    }

    private int getSumRange(int start, int end, int rangeStart, int rangeEnd, int treeIndex){

        //total overlap return sum at tree
        if(start <= rangeStart && rangeEnd <= end) {
            return tree[treeIndex];
        }

        // no overlap return 0
        if(rangeStart > end || rangeEnd < start){
            return 0;
        }

        int mid  = getMid(rangeStart,rangeEnd);

        //partial overlap return sum from left and right

        //get sum from left subtree
        int leftSum = getSumRange(start, end, rangeStart, mid, getLeftChildIndex(treeIndex));

        //get sum from right subtree
        int rightSum = getSumRange(start, end, mid+1, rangeEnd, getRightChildIndex(treeIndex));

        return leftSum + rightSum;
    }

    /*
    update value at index O(log(n))
     */
    public void updateValueAtIndex(int index, int delta){
        updateValueAtIndex(index, delta,0,offSet,0);
    }

    private void updateValueAtIndex(int index, int delta, int start, int end, int treeIndex){

        // if index is not present in the range return
        if(index < start || index > end){
            return;
        }

        //if start and end become equal, then treeIndex will be also equal to them and update
        //that value in segment tree at pos; will always be the root
        if(start == end){
            tree[treeIndex] += delta;
            return;
        }

        int mid = getMid(start,end);

        int leftChild = getLeftChildIndex(treeIndex);
        updateValueAtIndex(index,delta, start, mid, leftChild);

        int rightChild = getRightChildIndex(treeIndex);
        updateValueAtIndex(index,delta, mid + 1, end, rightChild);

        //fix parent
        tree[treeIndex] = tree[leftChild] + tree[rightChild];
    }


    private int getLeftChildIndex(int index){
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index){
        return 2 * index + 2;
    }

    private int getMid(int start, int end){
        return start + ( (end - start) / 2);
    }

    /*
    public static void main(String args[]){

        int input[] = new int[] {5,8,6,3,2,5,2,6,7,1,7,5,6,2,3,2};

        SegmentTreeTopDown segmentTree = new SegmentTreeTopDown(input);

        RangeQueryWithSQRT rangeQueryWithSQRT = new RangeQueryWithSQRT(input);

        System.out.println("Segment tree " + segmentTree.getSumRange(0,input.length-1));
        System.out.println("Sqrt algo " + rangeQueryWithSQRT.getSumRange(0,input.length-1));

        segmentTree.updateValueAtIndex(9,9);
        System.out.println("Segment tree "+ segmentTree.getSumRange(4,11));

        rangeQueryWithSQRT.updateValueAtIndex(9,10);
        System.out.println("Sqrt algo " + rangeQueryWithSQRT.getSumRange(4,11));

    }
     */
}
