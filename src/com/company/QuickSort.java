package com.company;
import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        QuickSort quickSorter = new QuickSort();
        int[] arrA = {66, 0, 14, 32, 47, 55, 32, 94, 18, 87, 65, 51, 19, 11, 10, 19};
        System.out.println(Arrays.toString(arrA));
        quickSorter.sort(arrA);
        System.out.println(Arrays.toString(arrA));
    }

    public void sort(int[] inputArr) {
        sort(inputArr, 0, inputArr.length-1);
    }

    //Our recursive sort function.
    //Termination condition:  if low > high.  This indicates there are no values to sort.
    //The very general algorithm is: partition the array, then partition the partitions until there's nothing left to sort.
    //This is what we mean by "divide and conquer" algorithm.
    private void sort(int[] inputArr, int low, int high) {
        if (low < high+1) {
            int pivotIndex = partition(inputArr, low, high);
            sort(inputArr, low, pivotIndex-1);
            sort(inputArr, pivotIndex+1, high);
        }
    }

    //simple swap function using temp int variable
    private void swap(int[] inputArr, int index1, int index2) {
        int temp = inputArr[index1];
        inputArr[index1] = inputArr[index2];
        inputArr[index2] = temp;
    }

    // returns random pivot
    // low and high are inclusive
    //(high - low)+1 is the range for a random int, +low offsets for low index position
    private int getPivot(int low, int high) {
        Random rand = new Random();
        return rand.nextInt((high - low) + 1) + low;
    }

    // moves all n < pivot to left of pivot and all n > pivot to right of pivot, then returns pivot index.
    private int partition(int[] inputArr, int low, int high) {
        //initially move the pivot value to the low index
        swap(inputArr, low, getPivot(low, high));
        int border = low + 1;//border is now the index of the first non-pivot value
        //iterate through the array from border index to high index
        for (int i = border; i <= high; i++) {
            // Our low index now holds the pivot value.
            // We will loop through the array and check if the value at index i is less than our pivot value.
            // If so, swap the value at i with the value at the current border index and then increment the border index by one.
            // The border is simply the first index where the values bigger than the pivot value begin.
            // The values left of the border index will all be smaller than the pivot and the values at index positions
            // equal to the border index and to it right will all be larger than the pivot value.
            // tldr: We iterate through the array and push smaller values than our pivot onto the left of the array,
            // keeping track of the border between smaller and bigger.
            if (inputArr[i] < inputArr[low]) {
                swap(inputArr, i, border++);
            }
        }
        // Now that we have partitioned the smaller from the larger values,
        // we swap the pivot value with border-1, which is the index of the last smaller value.
        // This gives us and ordering of [smaller values][pivot value][larger values].
        swap(inputArr, low, border-1);
        // Then we return border-1 as the pivot index value, because that's where pivot is now located in the array.
        // Once we find this index, we know that we have placed the pivot value into its final position in the sorted array.
        return border-1;
    }
}
