package ir.ac.kntu.util;

import java.util.Scanner;

public class ScannerWrapper {
    private static final ScannerWrapper INSTANCE = new ScannerWrapper();
    private final Scanner scanner;

    public static ScannerWrapper getInstance() {
        return INSTANCE;
    }

    private ScannerWrapper() {
        scanner = new Scanner(System.in);
    }

    public int nextInt() {
        int n = scanner.nextInt();
        scanner.skip("\n");
        return n;
    }

    public double nextDouble() {
        return scanner.nextDouble();
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}
