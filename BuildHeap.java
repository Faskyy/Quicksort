import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BuildHeap {
   private int[] a;          // array to be turned into a heap
   private List<Swap> swaps; // list of swapped elements while building the heap
   private PrintWriter out;  // helps print the answer (swaps) to screen.

   public static void main(String[] args) {
      new BuildHeap().solve();
   }

   // solves the problem
   public void solve() {
      readData();
      generateSwaps();
      writeResponse();
   }


   // reads the integer array a[]
   private void readData() {
      In in = new In("5intsReverseSorted.txt");
      //In in = new In("10Kints.txt");
      int n = in.readInt();
      a = new int[n];
      for (int i = 0; i < n; ++i) {
         a[i] = in.readInt();
      }
   }

   // prints the answer
   private void writeResponse() {
      out = new PrintWriter(new BufferedOutputStream(System.out));
      out.println(swaps.size());
      for (Swap swap : swaps) {
         out.println(swap.index1 + " " + swap.index2);
      }
      out.close();
   }

   // swaps are generated when sinking array elements (see sink())
   private void generateSwaps() {
      swaps = new ArrayList<Swap>();
      
     

      // The following naive implementation sorts the given
      // sequence and saves the resulting sequence of swaps.
      // This turns the given array into a heap, but uses
      // (in the worst case) a quadratic number of swaps.
      // TODO: replace by a more efficient implementation
//      for (int i = 0; i < a.length; ++i) {
//         for (int j = i + 1; j < a.length; ++j) {
//            if (a[i] > a[j]) {
//               swaps.add(new Swap(i, j));
//               exch(a, i, j);
//            }
//         }
//      }
      
      //ologn takes in 10 swaps so worst case
      
      //converted to o(n) 
      //changed elements ai and aj of the arraya[] for i and j
      //convert the array into a heap using only O(n) swaps
      
      
      //become a heap for each i where 0≤i≤n−1
      
       for (int i = 0; i < a.length; i++) {
    	   //positions to swap    	   
   	   int k = 2*i + 1;
   	   int l = 2*i + 2;
   	   
   	   if (i == 0) {
   		   swaps.add(new Swap (k, a.length - 1));
   		   exch(a, k, a.length - 1);
   	   } 
   	   
   	   if (k < a.length - 1) {
   		   swaps.add(new Swap(i, k));
   		   exch(a, i, k);
   	   }
   	   else if (l < a.length - 1) {
   		   swaps.add(new Swap(i, l));
   		   exch(a, i, l);
   	   }
     }

      // int n = a.length-1;  // the largest index in a[]
   }

   // sinks element at position k in array a[] of size n
   // keeps a record of swapped elements.
   private void sink(int[] a, int k, int n) {
   }

   // helper: exchanges elements at positions i and j in array a[]
   private void exch(int[] a, int i, int j) {
      int swap = a[i];
      a[i] = a[j];
      a[j] = swap;
   }


   // helper class: a record of the indices of the swapped elements
   static class Swap {
      int index1;
      int index2;

      public Swap(int index1, int index2) {
         this.index1 = index1;
         this.index2 = index2;
      }
   }
}
