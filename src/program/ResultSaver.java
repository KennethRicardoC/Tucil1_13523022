package program;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResultSaver {
    public static void saveResult(Board board, long startTime, long endTime, String fileName, int iterations) {
        Path filePath = Paths.get("test", fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            writer.write("Bentuk Akhir Papan:\n");
            board.printBoardToFile(writer);

            double elapsedTimeInMillis = (endTime - startTime) * 1E-6;
            writer.write("\nWaktu Pencarian: " + String.format("%.3f", elapsedTimeInMillis) + " ms\n");
            writer.write("Jumlah Iterasi: " + iterations + "\n");

            System.out.println("Hasil telah disimpan di " + filePath.toString());
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan hasil: " + e.getMessage());
        }
    }
}
