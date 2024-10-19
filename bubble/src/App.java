import java.io.*;
import java.util.*;

public class App {

    // Create an array of random integers between 0 and 100
    public static int[] createRandomArray(int arrayLength) {
        Random random = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = random.nextInt(101); // Random integer between 0 and 100
        }
        return array;
    }

    // Write the array to a file, each integer on a new line
    public static void writeArrayToFile(int[] array, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int num : array) {
                writer.write(num + "\n");
            }
            System.out.println("Array successfully written to " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file: " + e.getMessage());
        }
    }

    // Read integers from a file into an array
    public static int[] readFileToArray(String filename) {
        List<Integer> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from file: " + e.getMessage());
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    // Sort an integer array using Bubble Sort
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break; // If no two elements were swapped, the array is already sorted
            }
        }
    }

    // Main function to handle user's input
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Generate a random array and save to file");
            System.out.println("2. Read array from file, sort it, and save the sorted array");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            if (choice == 1) {
                // Option 1: Generate and save random array to file
                System.out.println("Enter the length of the array:");
                int length = scanner.nextInt();
                int[] randomArray = createRandomArray(length);
                
                System.out.println("Enter the filename to save the array:");
                String filename = scanner.next();
                
                writeArrayToFile(randomArray, filename);

            } else if (choice == 2) {
                // Option 2: Read, sort, and save the sorted array
                System.out.println("Enter the filename to read the array from:");
                String filename = scanner.next();
                int[] array = readFileToArray(filename);

                System.out.println("Sorting the array...");
                bubbleSort(array);

                System.out.println("Enter the filename to save the sorted array:");
                String sortedFilename = scanner.next();
                
                writeArrayToFile(array, sortedFilename);
                System.out.println("Sorted array saved to " + sortedFilename);

            } else if (choice == 3) {
                // Option 3: Exit
                System.out.println("Exiting program...");
                break;
            } else {
                System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }

        scanner.close();
    }
}
