import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static boolean error(String choice) {
        try {
            Integer.parseInt(choice);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public static void finish() throws InterruptedException {
        System.out.println("Exiting..");
        Thread.sleep(1000);
        System.exit(0);
    }

    public static void main(String args[]) throws IOException, InterruptedException {

        Scanner input = new Scanner(System.in);
        RedBlackTree redBlackTree = new RedBlackTree();

        while (true) {
            System.out.print("0. to read from file\n1. to enter input from keyboard\npress any other key to exit\n");
            String choice = input.nextLine();
            if (error(choice)) {
                finish();
            } else {
                int x = Integer.parseInt(choice);
                if (x == 0) {
                    System.out.print("Enter file name: ");
                    String fileName = input.nextLine();
                    try {
                        input = new Scanner(new FileReader(new File(fileName)));
                    } catch (Exception e) {
                        System.out.println("File not found");
                        finish();
                    }
                    while (input.hasNext()) {
                        String readString = ((String) input.nextLine());
                        redBlackTree.insert(readString);
                    }
                    System.out.println("# File Read");
                    System.out.println("# Height = " + redBlackTree.getHeight() + " Size = " + redBlackTree.getSize());
                    x = 1;
                    input = new Scanner(System.in);
                }
                if (x == 1) {
                    while (true) {
                        System.out.println("0. to insert\n1. to search\n2. to print the tree\npress any other key to return to exit\n");
                        choice = input.nextLine();
                        if (error(choice)) {
                            finish();
                        } else {
                            x = Integer.parseInt(choice);
                            if (x == 0) {
                                System.out.print("Enter String to be inserted: ");
                                choice = input.nextLine();
                                boolean inserted = redBlackTree.insert(choice);
                                System.out.println("# " + (inserted ? "String Inserted successfully" : "String already exist"));
                                System.out.println("# Height = " + redBlackTree.getHeight() + " Size = " + redBlackTree.getSize());
                            } else if (x == 1) {
                                System.out.print("Enter String to be searched: ");
                                choice = input.nextLine();
                                boolean found = redBlackTree.search(choice);
                                System.out.println(("#") + (found ? "String found" : "String  not found"));
                                System.out.println("# Height = " + redBlackTree.getHeight() + " Size = " + redBlackTree.getSize());
                            } else if (x == 2) {
                                System.out.println("# Height = " + redBlackTree.getHeight() + " Size = " + redBlackTree.getSize());
                                System.out.println(redBlackTree.inOrderList());
                            } else {
                                finish();
                            }

                        }
                    }
                } else {
                    finish();
                }
            }
        }

    }

}
