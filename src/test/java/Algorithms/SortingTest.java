package Algorithms;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SortingTest {
    static Stream<Integer[][]> ascSortingCases() {
        return Stream.of(
                new Integer[][]{ {}, {} },                                      // Empty array
                new Integer[][]{ {42}, {42} },                                  // Single element
                new Integer[][]{ {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5} },            // Already sorted
                new Integer[][]{ {5, 4, 3, 2, 1}, {1, 2, 3, 4, 5} },            // Reverse sorted
                new Integer[][]{ {3, 1, 2, 3, 3, 1}, {1, 1, 2, 3, 3, 3} },      // Duplicates
                new Integer[][]{ {-3, -1, -4, -2, 0}, {-4, -3, -2, -1, 0} },    // Negative numbers
                new Integer[][]{ {7, 7, 7, 7, 7}, {7, 7, 7, 7, 7} }             // All equal elements
        );
    }

    static Stream<Integer[][]> descSortingCases() {
        return Stream.of(
                new Integer[][]{ {}, {} },                                      // Empty array
                new Integer[][]{ {42}, {42} },                                  // Single element
                new Integer[][]{ {5, 4, 3, 2, 1}, {5, 4, 3, 2, 1} },            // Already sorted descending
                new Integer[][]{ {1, 2, 3, 4, 5}, {5, 4, 3, 2, 1} },            // Reverse sorted
                new Integer[][]{ {3, 1, 2, 3, 3, 1}, {3, 3, 3, 2, 1, 1} },      // Duplicates
                new Integer[][]{ {-3, -1, -4, -2, 0}, {0, -1, -2, -3, -4} },    // Negative numbers
                new Integer[][]{ {7, 7, 7, 7, 7}, {7, 7, 7, 7, 7} }             // All equal elements
        );
    }

    static List<Sorting.SortType> sortTypes() {
        return List.of(
                Sorting.SortType.BUBBLE,
                Sorting.SortType.MERGE,
                Sorting.SortType.INSERTION,
                Sorting.SortType.QUICKSORT,
                Sorting.SortType.QUICKSORT2,
                Sorting.SortType.SELECTION,
                Sorting.SortType.HEAP
        );
    }

    static Stream<Arguments> ascSortingCasesWithSortFunctions() {
        List<Sorting.SortType> sortTypes = sortTypes();

        return ascSortingCases()
                .flatMap(caseData -> sortTypes.stream()
                        .map(sortType -> Arguments.of(caseData, sortType))
                );
    }

    static Stream<Arguments> descSortingCasesWithSortFunctions() {
        List<Sorting.SortType> sortTypes = sortTypes();

        return descSortingCases()
                .flatMap(caseData -> sortTypes.stream()
                        .map(sortType -> Arguments.of(caseData, sortType))
                );
    }

    @ParameterizedTest
    @MethodSource({"ascSortingCasesWithSortFunctions"})
    void testSortFunctionsAsc(Integer[][] caseData, Sorting.SortType type) {
        Integer[] input = caseData[0];
        Integer[] expected = caseData[1];

        Integer[] sortedInput = switch (type) {
            case Sorting.SortType.BUBBLE -> Sorting.bubbleSort(input, false);
            case Sorting.SortType.MERGE -> Sorting.mergeSort(input, false);
            case Sorting.SortType.INSERTION -> Sorting.insertionSort(input, false);
            case Sorting.SortType.QUICKSORT -> Sorting.quickSort(input, false);
            case Sorting.SortType.QUICKSORT2 -> Sorting.quickSort2(input, false);
            case Sorting.SortType.SELECTION -> Sorting.selectionSort(input, false);
            case Sorting.SortType.HEAP -> Sorting.heapSort(input, false);
        };

        assertArrayEquals(expected, sortedInput, "Sorting failed for input: " + Arrays.toString(caseData[0]));
        assertArrayEquals(sortedInput, input, "The original array should be mutated");
    }

    @ParameterizedTest
    @MethodSource({"descSortingCasesWithSortFunctions"})
    void testSortFunctionsDesc(Integer[][] caseData, Sorting.SortType type) {
        Integer[] input = caseData[0];
        Integer[] expected = caseData[1];

        Integer[] sortedInput = switch (type) {
            case Sorting.SortType.BUBBLE -> Sorting.bubbleSort(input, true);
            case Sorting.SortType.MERGE -> Sorting.mergeSort(input, true);
            case Sorting.SortType.INSERTION -> Sorting.insertionSort(input, true);
            case Sorting.SortType.QUICKSORT -> Sorting.quickSort(input, true);
            case Sorting.SortType.QUICKSORT2 -> Sorting.quickSort2(input, true);
            case Sorting.SortType.SELECTION -> Sorting.selectionSort(input, true);
            case Sorting.SortType.HEAP -> Sorting.heapSort(input, true);
        };

        assertArrayEquals(expected, sortedInput, "Sorting failed for input: " + Arrays.toString(caseData[0]));
        assertArrayEquals(sortedInput, input, "The original array should be mutated");
    }

    @ParameterizedTest
    @MethodSource("ascSortingCasesWithSortFunctions")
    void testSortFunctionsAscNoMutate(Integer[][] caseData, Sorting.SortType type) {
        Integer[] input = caseData[0];
        Integer[] expected = caseData[1];

        Integer[] sortedInput = switch (type) {
            case Sorting.SortType.BUBBLE -> Sorting.bubbleSort(input, false, true);
            case Sorting.SortType.MERGE -> Sorting.mergeSort(input, false, true);
            case Sorting.SortType.INSERTION -> Sorting.insertionSort(input, false, true);
            case Sorting.SortType.QUICKSORT -> Sorting.quickSort(input, false, true);
            case Sorting.SortType.QUICKSORT2 -> Sorting.quickSort2(input, false, true);
            case Sorting.SortType.SELECTION -> Sorting.selectionSort(input, false, true);
            case Sorting.SortType.HEAP -> Sorting.heapSort(input, false, true);
        };

        assertArrayEquals(expected, sortedInput, "Sorting failed for input: " + Arrays.toString(caseData[0]));
        assertNotEquals(sortedInput, input, "The original array should be not be mutated");
    }

    @ParameterizedTest
    @MethodSource("descSortingCasesWithSortFunctions")
    void testSortFunctionsDescNoMutate(Integer[][] caseData, Sorting.SortType type) {
        Integer[] input = caseData[0];
        Integer[] expected = caseData[1];

        Integer[] sortedInput = switch (type) {
            case Sorting.SortType.BUBBLE -> Sorting.bubbleSort(input, true, true);
            case Sorting.SortType.MERGE -> Sorting.mergeSort(input, true, true);
            case Sorting.SortType.INSERTION -> Sorting.insertionSort(input, true, true);
            case Sorting.SortType.QUICKSORT -> Sorting.quickSort(input, true, true);
            case Sorting.SortType.QUICKSORT2 -> Sorting.quickSort2(input, true, true);
            case Sorting.SortType.SELECTION -> Sorting.selectionSort(input, true, true);
            case Sorting.SortType.HEAP -> Sorting.heapSort(input, true, true);
        };

        assertArrayEquals(expected, sortedInput, "Sorting failed for input: " + Arrays.toString(caseData[0]));
        assertNotEquals(sortedInput, input, "The original array should be not be mutated");
    }

    @ParameterizedTest
    @MethodSource("ascSortingCasesWithSortFunctions")
    void testUniversalSortAsc (Integer[][] caseData, Sorting.SortType type) {
        Integer[] input = caseData[0];
        Integer[] expected = caseData[1];

        Integer[] sortedInput = Sorting.sort(input, type, false);

        assertArrayEquals(expected, sortedInput, "Sorting failed for input: " + Arrays.toString(caseData[0]));
        assertArrayEquals(sortedInput, input, "The original array should be mutated");
    }

    @ParameterizedTest
    @MethodSource({"descSortingCasesWithSortFunctions"})
    void testUniversalSortDesc(Integer[][] caseData, Sorting.SortType type) {
        Integer[] input = caseData[0];
        Integer[] expected = caseData[1];

        Integer[] sortedInput = Sorting.sort(input, type, true);

        assertArrayEquals(expected, sortedInput, "Sorting failed for input: " + Arrays.toString(caseData[0]));
        assertArrayEquals(sortedInput, input, "The original array should be mutated");
    }

    @ParameterizedTest
    @MethodSource("ascSortingCasesWithSortFunctions")
    void testUniversalSortAscNoMutate(Integer[][] caseData, Sorting.SortType type) {
        Integer[] input = caseData[0];
        Integer[] expected = caseData[1];

        Integer[] sortedInput = Sorting.sort(input, type, false, true);

        assertArrayEquals(expected, sortedInput, "Sorting failed for input: " + Arrays.toString(caseData[0]));
        assertNotEquals(sortedInput, input, "The original array should be not be mutated");
    }

    @ParameterizedTest
    @MethodSource("descSortingCasesWithSortFunctions")
    void testUniversalSortDescNoMutate(Integer[][] caseData, Sorting.SortType type) {
        Integer[] input = caseData[0];
        Integer[] expected = caseData[1];

        Integer[] sortedInput = Sorting.sort(input, type, true, true);

        assertArrayEquals(expected, sortedInput, "Sorting failed for input: " + Arrays.toString(caseData[0]));
        assertNotEquals(sortedInput, input, "The original array should be not be mutated");
    }
}