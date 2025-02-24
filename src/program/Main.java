package program;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner consoleScanner = new Scanner(System.in)) { 
            System.out.print("Masukkan nama file test case (.txt): ");
            String fileName = consoleScanner.nextLine();
            File testCase = Reader.readFile(fileName);

            Board board = new Board(testCase.N, testCase.M);
            List<Piece> pieces = testCase.puzzlePiece.stream().map(Piece::new).toList();

            Solver solver = new Solver();
            long startTime = System.nanoTime();
            boolean result = solver.solve(board, pieces);
            long endTime = System.nanoTime();

            if (!result) {
                System.out.println("Tidak ada solusi yang ditemukan.");
            }

            System.out.printf("Waktu pencarian: %.3f ms\n", (endTime - startTime) * 1E-6);
            System.out.println("Jumlah iterasi: " + solver.getIterations() + " kali");

            System.out.print("Apakah hasil ingin disimpan (y/n)? ");
            if (consoleScanner.nextLine().trim().equalsIgnoreCase("y")) {
                System.out.print("Nama file yang disimpan: ");
                String saveFileName = consoleScanner.nextLine();
                ResultSaver.saveResult(board, startTime, endTime, saveFileName + ".txt", solver.getIterations());
            }
        } catch (IOException e) {
            System.err.println("Gagal membaca file: " + e.getMessage());
        }
    }
}
