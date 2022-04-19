public class Main {
    /**
     * Find last index of element
     *
     * @param x array to search
     * @param y value to look for
     * @return last index of y in x; -1 if absent
     * @throws NullPointerException if x is null
     */
    public static int findLast(int[] x, int y) {
        for (int i = x.length - 1; i >= 0; i--) {
            if (x[i] == y) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Find last index of zero
     *
     * @param x array to search
     * @return last index of 0 in x; -1 if absent
     * @throws NullPointerException if x is null
     */
    public static int lastZero(int[] x) {
        for (int i = x.length-1; i >= 0; i--) {
            if (x[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Count positive elements
     *
     * @param x array to search
     * @return count of positive elements in x
     * @throws NullPointerException if x is null
     */
    public static int countPositive(int[] x) {
        int count = 0;
        for (int i = 0; i < x.length; i++) {
            if (x[i] > 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * Count odd or postive elements
     *
     * @param x array to search
     * @return count of odd/positive values in x
     * @throws NullPointerException if x is null
     */
    public static int oddOrPos(int[] x) {
        int count = 0;
        for (int i = 0; i < x.length; i++) {
            if (x[i] % 2 == 1 || x[i]%2 == -1 || x[i] > 0) {
                count++;
            }
        }
        return count;
    }

}
