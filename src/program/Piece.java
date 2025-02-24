package program;

import java.util.*;

public class Piece 
{
    private char[][] block;
    private int height;
    private int width;

    public Piece(String pieceStr) {
        String[] lines = pieceStr.split("\n");
        height = lines.length;
        width = 0;
        for (String line : lines) {
            if (line.length() > width) {
                width = line.length();
            }
        }
        width -= 1;
        block = new char[height][width];

        for (int i = 0; i < height; i++) {
            String line = lines[i];
            for (int j = 0; j < width; j++) {
                try {
                    char currentchar = line.charAt(j);
                    if (currentchar >= 65 && currentchar <= 90) {
                        block[i][j] = currentchar;
                    }
                    else block[i][j] = '.';
                } 
                catch (Exception e) 
                {
                    block[i][j] = '.';
                }
            }
        }
    }

    public Piece(char[][] shape) 
    {
        this.height = shape.length;
        this.width = shape[0].length;
        this.block = new char[height][width];
        for (int i = 0; i < height; i++) 
        {
            System.arraycopy(shape[i], 0, this.block[i], 0, width);
        }
    }

    public int getHeight() 
    {
        return height;
    }

    public int getWidth() 
    {
        return width;
    }

    public char[][] getBlock() 
    {
        return block;
    }

    public Piece getRotatedPiece() 
    {
        int newHeight = width;
        int newWidth = height;
        char[][] newShape = new char[newHeight][newWidth];
        for (int i = 0; i < newHeight; i++) 
        {
            for (int j = 0; j < newWidth; j++) 
            {
                newShape[i][j] = block[height - 1 - j][i];
            }
        }
        return new Piece(newShape);
    }

    public Piece getMirroredPiece() 
    {
        char[][] newShape = new char[height][width];
        for (int i = 0; i < height; i++) 
        {
            for (int j = 0; j < width; j++) 
            {
                newShape[i][j] = block[i][width - 1 - j];
            }
        }
        return new Piece(newShape);
    }

    public List<Piece> getNewPiece() 
    {
        List<Piece> newPiece = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        Piece currentPiece = this;
        for (int i = 0; i < 4; i++) 
        {
            String rep = currentPiece.toString();
            if (!seen.contains(rep)) 
            {
                newPiece.add(currentPiece);
                seen.add(rep);
            }
            Piece mirrored = currentPiece.getMirroredPiece();
            rep = mirrored.toString();
            if (!seen.contains(rep)) {
                newPiece.add(mirrored);
                seen.add(rep);
            }
            currentPiece = currentPiece.getRotatedPiece();
        }
        return newPiece;
    }

    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++) {
            sb.append(new String(block[i]));
            if (i < height - 1) sb.append("\n");
        }
        return sb.toString();
    }
}