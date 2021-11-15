import java.util.ArrayList;

public class Map {
    private Character[][] map;
    static int col;
    static int row;
    private ArrayList<Character> enemyList = new ArrayList<Character>();

    public Map(int col, int row) {
        this.map = new Character[col][row];
        this.col = col;
        this.row = row;
    }

    public void addChar(Character char1, int col, int row) {
        this.map[col][row] = char1;
    }

    public ArrayList<Character> getEnemyList() {
        return enemyList;
    }

    public int charXPosition(Character character) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (this.map[i][j] == character) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int charYPosition(Character character) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (this.map[i][j] == character) {
                    return j;
                }
            }
        }
        return -1;
    }

    public void removeChar(Character character) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (this.map[i][j] == character) {
                    this.map[i][j] = null;
                }
            }
        }
    }

    public Character getCharByPos(int col, int row) {
        if (col <= map.length && row <= map[col].length) {
            return map[col][row];
        }
        return null;
    }
}
