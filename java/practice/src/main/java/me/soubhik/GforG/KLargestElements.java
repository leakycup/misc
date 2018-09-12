package me.soubhik.GforG;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by sb8 on 9/9/18.
 */
public class KLargestElements {
    private static class SolutionUsingQuickSort {
        private final Random random = new Random();

        private int selectIndex(int start, int end) {
            int bound = end - start;
            int r = random.nextInt(bound);

            return (r + start);
        }

        private int partitionMultiIterations(int[] input, int start, int end, int pivot) {
            int pivotValue = input[pivot];

            //first, determine the final positon of the pivot element. O(n)
            int numElementsInLeft = 0;
            for (int i = start; i < end; i++) {
                if (i == pivot) {
                    continue;
                }
                if (input[i] <= pivotValue) {
                    numElementsInLeft++;
                }
            }
            int newPivot = numElementsInLeft + start;

            //place pivot element in its final position: O(1): one swap
            input[pivot] = input[newPivot];
            input[newPivot] = pivotValue;

            //left partition: elements at indexes start to (newPivot-1) [inclusive].
            //right partition: elements at indexes (newPivot+1) to (end-1) [inclusive].
            //for every element in left partition that is > pivot element, there's one element in right partition
            //that is <= pivot element. find and swap them.
            //this is O(n) as a pair of elements is swapped at most once. once swapped, both the elements are in their
            //final positions.
            int upper = newPivot + 1;
            for (int i = start; i < newPivot; i++) {
                if (input[i] > pivotValue) {
                    int outOfPlace = input[i];
                    for (int j = upper; j < end; j++) {
                        if (input[j] <= pivotValue) {
                            upper = j;
                            break;
                        }
                    }
                    input[i] = input[upper];
                    input[upper] = outOfPlace;
                    upper++;
                }
            }

            return newPivot;
        }

        private int partition(int[] input, int start, int end, int pivot) {
            int pivotValue = input[pivot];

            //place pivot in the left partition
            input[pivot] = input[start];
            input[start] = pivotValue;

            //the variable upper tracks the final position of the pivot.
            int upper = start;
            for (int i = end-1; i > start; i--) {
                if (input[i] <= pivotValue) {
                    upper = i;
                    break;
                }
            }

            if (upper == start) {
                return upper;
            }

            //the variable lower tracks the next element whose final position needs to be determined
            int lower = start + 1;
            while (lower <= upper) {
                if (input[lower] <= pivotValue) {
                    lower++;
                } else {
                    int outOfPlace = input[lower];
                    input[lower] = input[upper];
                    input[upper] = outOfPlace;
                    upper--;
                }
            }

            //place pivot in its final position
            input[start] = input[upper];
            input[upper] = pivotValue;

            return upper;
        }

        private void quickSort(int[] input) {
            quickSortRecursive(input, 0, input.length);
        }

        private void quickSortRecursive(int[] input, int start, int end) {
            if ((end - start) <= 1) {
                return;
            }

            int pivot = selectIndex(start, end);

            int newPivot = partition(input, start, end, pivot);
            printDebug(input, pivot, newPivot);
            quickSortRecursive(input, start, newPivot);
            quickSortRecursive(input, newPivot+1, end);
        }

        private void printDebug(int[] input, int pivot, int newPivot) {
            System.err.print("(" + pivot + ")");
            System.err.print(" (" + newPivot + ")");
            System.err.print(" (");
            for (int i: input) {
                System.err.print(i + " ");
            }
            System.err.print(")\n");
        }

        private void printKLargestElementsUsingQuickSort(int[] input, int k) {
            quickSort(input);
            for (int i = input.length-1; i >= input.length - k; i--) {
                System.out.print(input[i]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    private static void testPartition(int[] input, int start, int end, int pivot, int expected) {
        SolutionUsingQuickSort test = new SolutionUsingQuickSort();

        int actual = test.partition(input, start, end, pivot);
        assert (actual == expected);
    }

    private static void testQuickSort(int[] input) {
        SolutionUsingQuickSort test = new SolutionUsingQuickSort();

        int origLength = input.length;

        if (origLength == 0) {
            test.quickSort(input);
            assert (input.length == origLength);
        }

        if (origLength == 1) {
            int origFirstElement = input[0];
            test.quickSort(input);
            assert (input.length == origLength);
            assert (input[0] == origFirstElement);
        }

        int[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected);

        test.quickSort(input);
        assert (input.length == origLength);
        for (int i = 0; i < input.length; i++) {
            System.err.println(expected[i] + " : " + input[i]);
            assert (expected[i] == input[i]);
        }
    }

    private static void testMain() {
        //test 1
        int[] input1 = {4, 2, 9, 12, 6};
        testPartition(input1, 0, 5, 2, 3);

        //test 2
        int[] input2 = {4, 2, 9, 12, 6};
        testPartition(input2, 0, 5, 0, 1);

        //test 3
        int[] input3 = {4, 2, 9, 12, 6};
        testPartition(input3, 0, 5, 4, 2);

        //test 4
        int[] input4 = {4, 2, 9, 12, 6};
        testPartition(input4, 0, 5, 1, 0);

        //test 5
        int[] input5 = {4, 2, 9, 12, 6};
        testPartition(input5, 0, 5, 3, 4);

        //test 6
        int[] input6 = {4, 2, 9, 12, 6};
        testQuickSort(input6);

        //test 7
        int[] input7 = {2, 2, 6, 4, 9, 3};
        testPartition(input7, 0, 6, 0, 1);

        //test 8
        int[] input8 = {2, 2, 6, 4, 9, 3};
        testPartition(input8, 1, 5, 3, 2);

        //test 9
        int[] input9 = {2, 2, 6, 4, 9, 3};
        testQuickSort(input9);

        //test 8
        //int[] input8 = {23, 5, 0};
        //testPartition(input8, 0, 3, 2, 0);
    }

    private static class Problem {
        int[] input;
        int k;
    }

    private static void gForGMain() {
        Scanner in = new Scanner(System.in);
        int testCases = in.nextInt();
        Problem[] problems = new Problem[testCases];
        for (int i = 0; i < testCases; i++) {
            int numElements = in.nextInt();
            int k = in.nextInt();
            int[] input = new int[numElements];
            for (int j=0; j<numElements; j++) {
                input[j] = in.nextInt();
            }
            problems[i] = new Problem();
            problems[i].input = input;
            problems[i].k = k;
        }

        SolutionUsingQuickSort solution = new SolutionUsingQuickSort();
        for (Problem problem: problems) {
            solution.printKLargestElementsUsingQuickSort(problem.input, problem.k);
        }
    }

    public static void main(String[] args) {
        testMain();
        //gForGMain();
    }
}
