package program;

import java.util.*;

public class File {
    int N, M, P;
    String S;
    List<String> puzzlePiece;

    public File(int N, int M, int P, String S, List<String> puzzlePiece) 
    {
        this.N = N;
        this.M = M;
        this.P = P;
        this.S = S;
        this.puzzlePiece = puzzlePiece;
    }
}