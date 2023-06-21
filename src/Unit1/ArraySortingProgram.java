package Unit1;

import java.util.Arrays;

/*
 * TIME REPORT:
 * Array size: 1000
 * Selection Sort Time: 3 milliseconds. || 0.003 seconds.
 * Arrays.sort() Time: 1 milliseconds. || 0.003 seconds.
 * 
 * Array size: 10000
 * Selection Sort Time: 34 milliseconds. || 0.034 seconds.
 * Arrays.sort() Time: 5 milliseconds. || 0.034 seconds.
 * 
 * Array size: 100000
 * Selection Sort Time: 1651 milliseconds. || 1.651 seconds.
 * Arrays.sort() Time: 37 milliseconds. || 1.651 seconds.
 * 
 * Array size: 400000
 * Selection Sort Time: 26913 milliseconds. || 26.913 seconds.
 * Arrays.sort() Time: 130 milliseconds. || 0.13 seconds.
 * 
 * Array size: 1000000
 * Arrays.sort() Time: 245 milliseconds. || 0.245 seconds.
 */

public class ArraySortingProgram {

	static int ARRAY_SIZE = 1000000; // Change this value to adjust array size

	public static void main(String[] args) {
		/* 
		 * Create two arrays of type int[].
		 * Both arrays should be the same size,
		 * and the size should be given by a constant in the program so that you can change it easily.
		 */
		int[] array1 = new int[ARRAY_SIZE];
		int[] array2 = new int[ARRAY_SIZE];

		/*
		 * Fill the arrays with random integers. 
		 * The arrays should have identical contents, with the same random numbers in both arrays. 
		 */
		for (int i = 0; i < ARRAY_SIZE; i++) {
			array1[i] = (int) (Integer.MAX_VALUE * Math.random());
			array2[i] = array1[i];
		}

		/*
		 * Sort the first array using either Selection Sort or Insertion Sort. 
		 * You should add the sorting method to your program;
		 */

		// Sort the first array using Selection Sort
		System.out.println("Array size: " + ARRAY_SIZE );
		if(ARRAY_SIZE <= 400000) { // Added a safeguard for too large numbers to be run 400000 was already taking over 26 seconds
			long startTime1 = System.currentTimeMillis();
			selectionSort(array1);
			long runTime1 = System.currentTimeMillis() - startTime1;
			System.out.println("Selection Sort Time: " + runTime1 + " milliseconds. || " + runTime1/1000.0 + " seconds.");
		}

		// Sort the second array using Arrays.sort()
		long startTime2 = System.currentTimeMillis();
		Arrays.sort(array2);
		long runTime2 = System.currentTimeMillis() - startTime2;
		System.out.println("Arrays.sort() Time: " + runTime2 + " milliseconds. || " + runTime2/1000.0 + " seconds.");


	}

	/*
	 * 7.4.4 Selection Sort

		static void selectionSort(int[] A) {
			// Sort A into increasing order, using selection sort
			for (int lastPlace = A.length-1; lastPlace > 0; lastPlace--) {
				// Find the largest item among A[0], A[1], ...,
				// A[lastPlace], and move it into position lastPlace
				// by swapping it with the number that is currently
				// in position lastPlace.
				int maxLoc = 0; // Location of largest item seen so far.
				for (int j = 1; j <= lastPlace; j++) {
					if (A[j] > A[maxLoc]) {
						// Since A[j] is bigger than the maximum we’ve seen
						// so far, j is the new location of the maximum value
						// we’ve seen so far.
						maxLoc = j;
					}
				}
				int temp = A[maxLoc]; // Swap largest item with A[lastPlace].
				A[maxLoc] = A[lastPlace];
				A[lastPlace] = temp;
			} // end of for loop
		}
	 */

	// Selection Sort implementation with minor optimizations applied
	public static void selectionSort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int minIndex = i; // Assume the current element is the minimum
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[minIndex]) {
					minIndex = j; // Update the minimum index if a smaller element is found
				}
			}
			int temp = array[minIndex]; // Swap the minimum element with the element at index i
			array[minIndex] = array[i];
			array[i] = temp;
		}
	}
}
