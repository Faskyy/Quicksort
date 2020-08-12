/******************************************************************************
 *  Compilation:  javac Quick.java
 *  Execution:    java Quick   [ < input.txt ]
 *  Dependencies: In.java
 *  Data files:   tiny.txt, words3.txt
 *
 *  Sorts a sequence of strings from standard input using quicksort.
 *
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java Quick                       [ run Quick.java with tiny.txt as input]
 *  A E E L M O P R S T X              [ outputs one string per line ]
 *
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *
 *  % java Quick                      [run Quick.java with words3.txt as input]
 *  all bad bed bug dad ... yes yet zoo         [ outputs one string per line ]
 *
 ******************************************************************************/

import java.util.Random;

// Class Quick. Provides static methods for sorting an array using quicksort.
public class Quick {

    // This class should not be instantiated.
    private Quick() { }

    // Rearranges the array in ascending order, using the natural order.
    public static void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    // quicksort the subarray from a[lo] to a[hi]
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];   // the pivot - the leftmost element of a
        while (true) {
            // find item on lo to swap
            while (less(a[++i], v)) {
                if (i == hi) break;
            }

            // find item on hi to swap
            while (less(v, a[--j])) {
                if (j == lo) break;      // redundant since a[lo] acts as sentinel
            }

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }



   /***************************************************************************
    *  Helper sorting functions.
    ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        if (v == w) return false;   // optimization when reference equals
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    // shuffle the array
    public static void shuffle(Object[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + new Random().nextInt(n - i);     // between i and n-1
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    /**
     * Reads in a sequence of strings from standard input; quicksorts them;
     * and prints them to standard output in ascending order.
     */
    public static void main(String[] args) {
        String[] a = new In("tiny.txt").readAllStrings();
        //String[] a = new In("words3.txt").readAllStrings();
        Quick.sort(a);
        show(a);

        // test quicksort with integers
        In in = new In("10ints.txt");
        int n = in.readInt();
        Integer[] b = new Integer[n];
        for (int i = 0; i < n; i++) {
            b[i] = in.readInt();
        }

        Quick.sort(b);
        show(b);
    }
}
