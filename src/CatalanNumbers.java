public class CatalanNumbers {
    public static int[] cache = new int[10000];

    public static int catalan(int n) {
        if (n == 1) {
            return 1;
        }

        if (cache[n] != 0) {
            return cache[n];
        }

        int res = 0;

        for (int i = 1; i < n; i++) {
            res = res + (catalan(i) * catalan(n - i));
        }

        cache[n] = res;

        return res;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 21; i++) {
            System.out.println(i + " " + catalan(i));
        }

    }
}
