class DisjointSetConstantTime{

    private int[] parent;
    private int[] rank;
    private int numberOfComponents;

    /*
    when compression by path and union by rank is combined then disjoint set data structure attains constant time complexity
     */
    DisjointSetConstantTime(int size){

        numberOfComponents = size;
        parent = new int[size];
        rank = new int[size];
        for(int i= 0 ;i < size; i++){
            parent[i] = i;
            rank[i] = 1;
        }

    }


    /*
    optimization: compression by path
    from parent to child if any intermediate nodes are found then make their parents as root
    overall time complexity with normal union(): log(n)
     */
    int findParent(int ele){

        if(ele == parent[ele]){
            return ele;
        }

        parent[ele] = findParent(parent[ele]);
        return parent[ele];
    }

    /*
    optimization: union by rank
    make small rank tree as a child to bigger rank tree
    overall time complexity with normal findParent() : log(n)
     */
    boolean union(int x,int y){

        int xParent = findParent(x);
        int yParent  = findParent(y);

        if(xParent == yParent){
            //cannot do union since both are already present in same set
            return false;
        }

        //both the components have diff parents
        numberOfComponents--;
        if(rank[xParent] > rank[yParent]){

            parent[yParent] = xParent;

        }else if(rank[xParent] < rank[yParent]){

            parent[xParent] = yParent;

        }else {
            //both tree have equal rank
            parent[yParent] = xParent;
            rank[xParent]++;
        }

        return true;
    }

    public int getNumberOfComponents(){
        return this.numberOfComponents;
    }

}