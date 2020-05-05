public class MinCostForNTasksByNPerson {

    //https://www.hackerearth.com/practice/algorithms/dynamic-programming/bit-masking/tutorial/

    //solution implemented by me is top down and below commented code is bottom up by hacker earth to showcase example

    /*
    Let's try to improve it using dynamic programming. Suppose the state of  is , where  represents that person  to  have been assigned a task, and  is a binary number, whose  bit represents if the  task has been assigned or not.
Now, suppose, we have , we can assign a task  to person , iff  task is not yet assigned to any peron i.e.  then,  will be given as:
One thing to note here is  is always equal to the number set bits in , so we can remove that. So the dp state now is just , and if we have , then

here number of set bits in .
Complete algorithm is given below:

assign(N, cost)
    for i = 0 to power(2,N)
        dp[i] = INFINITY
    dp[0] = 0
    for mask = 0 to power(2, N)
        x = count_set_bits(mask)
        for j = 0 to N
            if jth bit is not set in i
                dp[mask|(1<<j)] = min(dp[mask|(1<<j)], dp[mask]+cost[x][j])
    return dp[power(2,N)-1]
Time complexity of above algorithm is  and space complexity is .
This is just one problem that can be solved using DP+bitmasking. There's a whole lot.
     */


    static int max = 100000;

    public static int[][] cache;

    public static int findMinCostForNTasksByNPerson(int [][]graph){
        cache = new int[graph.length][1<<graph[0].length];
        return helper(graph,0,0);
    }

    public static int helper(int [][]graph,int start,int mask){
        if(graph.length == start) return 0;


        if(cache[start][mask] != 0) return cache[start][mask];

        int result  =  max;


        for(int i = 0; i < graph[start].length; i++){

            if((mask & (1<<i)) == 0){

                result = Math.min(result,helper(graph,start+1, mask | (1<<i) ) + graph[start][i]);
            }

        }

        cache[start][mask] = result;

        return result;
    }

    public static void printCache(){
        System.out.println();
        for (int[] a: cache){
            for(int x: a){
                System.out.print(x+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int [][] graph = {
                {1,2,3,4},
                {8,7,6,5},
                {9,10,11,12},
                {16,15,14,13}
        };

        int x = findMinCostForNTasksByNPerson(graph);
        System.out.print(x);
        printCache();
    }
}
