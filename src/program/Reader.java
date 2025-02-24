package program;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Reader {

    public static File readFile(String fileName) throws IOException {
        Path path = Paths.get("test",fileName);
        BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));

        String[] firstLine = reader.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]);
        int M = Integer.parseInt(firstLine[1]);
        int P = Integer.parseInt(firstLine[2]);
 
        String S = reader.readLine().trim();
        List<String> puzzlePiece = new ArrayList<>();

        Character last = null;
        String shape = "";
        int count = 0;
        while (count < P) {
            String line = reader.readLine();
            if (line == null) {
                puzzlePiece.add(shape.substring(0, shape.length() - 1));
                break;
            }

            Character current = null;
            for (char currentchar : line.toCharArray()) {
                if (currentchar >= 65 && currentchar <= 90) {
                    current =  currentchar;
                    break;
                }
            }
        
            if (last == null || last.equals(current)) {
                shape += line + System.lineSeparator();
            } else {
                puzzlePiece.add(shape.substring(0, shape.length() - 1));
                shape = line + System.lineSeparator();
                count += 1;
            }

            last = current;
        }
        reader.close();
        return new File(N, M, P, S, puzzlePiece);
    }
}