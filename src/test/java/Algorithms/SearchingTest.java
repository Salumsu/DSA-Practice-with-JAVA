package Algorithms;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.CompareObjectTests;
import utils.ObjectTest;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SearchingTest {
    static Stream<Arguments> linearSearchCases() {
        return Stream.of(
                Arguments.of(new Integer[]{1, 2, 3, 4, 5}, 3, 2),   // Target exists in the middle
                Arguments.of(new Integer[]{1, 2, 3, 4, 5}, 1, 0),   // Target is the first element
                Arguments.of(new Integer[]{1, 2, 3, 4, 5}, 5, 4),   // Target is the last element
                Arguments.of(new Integer[]{1, 2, 3, 4, 5}, 6, -1),  // Target is not in the array
                Arguments.of(new Integer[]{}, 1, -1),               // Empty array
                Arguments.of(new String[]{"a", "b", "c"}, "b", 1),  // String array
                Arguments.of(new String[]{"a", "b", "c"}, "d", -1)  // Target not in string array
        );
    }

    @ParameterizedTest
    @MethodSource("linearSearchCases")
    <T extends Comparable<T>> void testLinearSearch (T[] arr, T item, int expectedResult) {
        int output = Searching.linearSearch(arr, item);

        assertEquals(expectedResult, output);
    }

    @ParameterizedTest
    @MethodSource("linearSearchCases")
    <T extends Comparable<T>> void testLinearSearchWithObj (T[] arr, T item, int expectedResult) {
        List<ObjectTest<T>> input = ObjectTest.createObjectTests(arr);
        // UNFORTUNATELY, WE NEED TO PASS AN OBJECT WITH SIMILAR VALUE.
        // YOU COULD ALSO PASS THE OBJECT ITSELF IF YOU HAVE THE REFERENCE E.G. input[0]
        // BUT WE MUST ALSO HANDLE CASES WHERE THE VALUE ISN'T IN INPUT
        // SO, WE INSTANTIATE A NEW OBJECT HERE
        int output = Searching.linearSearch(input, new ObjectTest<>(item), new CompareObjectTests<>());

        assertEquals(expectedResult, output);
    }


    static Stream<Arguments> sortedSearchCases() {
        return Stream.of(
                // Integer array cases
                Arguments.of(new Integer[]{1, 2, 3, 4, 5}, 3, 2),  // Target in the middle
                Arguments.of(new Integer[]{1, 2, 3, 4, 5}, 1, 0),  // Target at the beginning
                Arguments.of(new Integer[]{1, 2, 3, 4, 5}, 5, 4),  // Target at the end
                Arguments.of(new Integer[]{1, 2, 3, 4, 5}, 6, -1), // Target not in array
                Arguments.of(new Integer[]{1}, 1, 0),             // Single-element array (found)
                Arguments.of(new Integer[]{1}, 2, -1),            // Single-element array (not found)
                Arguments.of(new Integer[]{}, 1, -1),             // Empty array

                // String array cases
                Arguments.of(new String[]{"apple", "banana", "cherry"}, "banana", 1), // Target in the middle
                Arguments.of(new String[]{"apple", "banana", "cherry"}, "apple", 0),  // Target at the beginning
                Arguments.of(new String[]{"apple", "banana", "cherry"}, "cherry", 2), // Target at the end
                Arguments.of(new String[]{"apple", "banana", "cherry"}, "date", -1),  // Target not in array
                Arguments.of(new String[]{"apple"}, "apple", 0),                       // Single-element array (found)
                Arguments.of(new String[]{"apple"}, "banana", -1),                     // Single-element array (not found)
                Arguments.of(new String[]{}, "apple", -1)                              // Empty array
        );
    }

    @ParameterizedTest
    @MethodSource("sortedSearchCases")
    <T extends Comparable<T>> void testBinarySearch (T[] arr, T item, int expectedResult) {
        int output = Searching.binarySearch(arr, item);

        assertEquals(expectedResult, output);
    }

    @ParameterizedTest
    @MethodSource("sortedSearchCases")
    <T extends Comparable<T>> void testExponentialSearch (T[] arr, T item, int expectedResult) {
        int output = Searching.exponentialSearch(arr, item);

        assertEquals(expectedResult, output);
    }

    @ParameterizedTest
    @MethodSource("sortedSearchCases")
    <T extends Comparable<T>> void testBinarySearchWithObj (T[] arr, T item, int expectedResult) {
        List<ObjectTest<T>> input = ObjectTest.createObjectTests(arr);

        int output = Searching.binarySearch(input, new ObjectTest<>(item), new CompareObjectTests<>());

        assertEquals(expectedResult, output);
    }

    @ParameterizedTest
    @MethodSource("sortedSearchCases")
    <T extends Comparable<T>> void testExponentialSearchWithObj (T[] arr, T item, int expectedResult) {
        List<ObjectTest<T>> input = ObjectTest.createObjectTests(arr);

        int output = Searching.exponentialSearch(input, new ObjectTest<>(item), new CompareObjectTests<>());

        assertEquals(expectedResult, output);
    }

}