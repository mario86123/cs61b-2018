package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    private static class Position {
        int x, y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
        Position() {
            this.x = 0;
            this.y = 0;
        }
        void nextPos(int sideLength) {
            x += 3 * sideLength + 1;
            if (x + 3 * sideLength + 1 > WIDTH) {
                x = (x + 2 * sideLength -1) % WIDTH;
                y += sideLength;
            }
        }
    }
    private static TETile randomTile() {
        java.util.Random rand = new Random();
        int tileNum = rand.nextInt(3);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.TREE;
            default: return Tileset.NOTHING;
        }
    }
    public static void addHexagon(TETile[][] tiles, Position p, int sideLength, TETile t) {
        for (int j = 0; j < sideLength; j += 1) {
            for (int i = 0; i < sideLength + 2 * j; i += 1) {
                tiles[p.x + sideLength - 1 + i - j][p.y + j] = t;
                tiles[p.x + sideLength - 1 + i - j][p.y + sideLength * 2 - j - 1] = t;
            }
        }
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] hexWorld = new TETile[WIDTH][HEIGHT];

        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                hexWorld[x][y] = Tileset.NOTHING;
            }
        }
        Position p = new Position();
        int sideLength = 3;
        for (; p.y + 2 * sideLength < HEIGHT; p.nextPos(sideLength)) {
            addHexagon(hexWorld, p, sideLength, randomTile());
        }
        ter.renderFrame(hexWorld);
    }
}
