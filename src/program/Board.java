package program;

import java.io.BufferedWriter;
import java.io.IOException;

public class Board {

    char[][] board;
    int N, M;

    private static final String[] Colour = {
        "\u001B[31m", // Red (A)
        "\u001B[34m", // Blue (B)
        "\u001B[32m", // Green (C)
        "\u001B[33m", // Yellow (D)
        "\u001B[36m", // Cyan (E)
        "\u001B[35m", // Magenta (F)
        "\u001B[37m", // White (G)
        "\u001B[90m", // Gray (H)
        "\u001B[91m", // Light Red (I)
        "\u001B[94m", // Light Blue (J)
        "\u001B[92m", // Light Green (K)
        "\u001B[93m", // Light Yellow (L)
        "\u001B[96m", // Light Cyan (M)
        "\u001B[95m", // Light Magenta (N)
        "\u001B[97m", // Light White (O)
        "\u001B[41m", // Red Background (P)
        "\u001B[44m", // Blue Background (Q)
        "\u001B[42m", // Green Background (R)
        "\u001B[43m", // Yellow Background (S)
        "\u001B[46m", // Cyan Background (T)
        "\u001B[45m", // Magenta Background (U)
        "\u001B[47m", // White Background (V)
        "\u001B[101m", // Light Red Background (W)
        "\u001B[104m", // Light Blue Background (X)
        "\u001B[103m",  // Light Green Background (Y)
        "\u001B[102m"  // Light Yellow Background (Z)
    };

    public Board(int N, int M) {
        this.N = N;
        this.M = M;
        board = new char[N][M];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                board[i][j] = '.';
            }
        }
    }

    public int getN(){
        return N;
    }

    public int getM(){
        return M;
    }

    public void printBoard() {
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                char tile = board[i][j];
                if (tile == '.'){
                    System.out.print("\u001B[0m" + tile + " ");
                } else {
                    int colourIndex = (tile - 'A') % Colour.length;
                    System.out.print(Colour[colourIndex] + tile + " \u001B[0m");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean placePieceAvailable(Piece piece, int row, int col){
        char[][]shape = piece.getBlock();
        int height = piece.getHeight();
        int width = piece.getWidth();

        if (row + height > N || col + width > M){
            return false;
        }
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if(shape[i][j] != '.' && board[row + i][col + j] != '.'){
                    return false;
                }
            }
        }
        return true;
    }
    
    public void placePiece(Piece piece, int row, int col, char pieceType){
        char[][]shape = piece.getBlock();
        int height = piece.getHeight();
        int width = piece.getWidth();

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if (shape[i][j] != '.'){
                    board[row + i][col + j] = pieceType;
                }
            }
        }
    }

    public void removePiece(Piece piece, int row, int col){
        char[][]shape = piece.getBlock();
        int height = piece.getHeight();
        int width = piece.getWidth();

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if (shape[i][j] != '.'){
                    board[row + i][col + j] = '.';
                }
            }
        }
    }

    public boolean boardFull(){
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (board[i][j] == '.'){
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoardToFile(BufferedWriter writer) throws IOException{
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                writer.write(board[i][j]);
            }
            writer.newLine();
        }
        writer.newLine();
    }
}