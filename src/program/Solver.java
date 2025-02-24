package program;

import java.util.List;

public class Solver {
    private int iterations = 0;

    public boolean solve(Board board, List<Piece> pieces) {
        return solveRecursive(board, pieces, 0);
    }

    private boolean solveRecursive(Board board, List<Piece> pieces, int index) {
        if (index == pieces.size()) {
            if (board.boardFull()) {
                board.printBoard();
                return true;
            }
            return false;
        }

        Piece piece = pieces.get(index);
        for (Piece orientation : piece.getNewPiece()) {
            for (int i = 0; i < board.getN(); i++) {
                for (int j = 0; j < board.getM(); j++) {
                    if (board.placePieceAvailable(orientation, i, j)) {
                        iterations++;
                        board.placePiece(orientation, i, j, (char) ('A' + index));
                        if (solveRecursive(board, pieces, index + 1)) {
                            return true;
                        }
                        board.removePiece(orientation, i, j);
                    }
                }
            }
        }
        return false;
    }

    public int getIterations() {
        return iterations;
    }
}


