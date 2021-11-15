import java.util.ArrayList;

public class Map {
    private Character[][] map;
    static int col;
    static int row;
    private ArrayList <Character> enemyList = new ArrayList<Character>();

    public Map(int col, int row) {
        this.map = new Character[col][row];
        this.col = col;
        this.row = row;
    }

    public void addChar(Character char1, int col, int row) {
        this.map[col - 1][row - 1] = char1;
    }

    public ArrayList<Character> getEnemyList() {
        return enemyList;
    }
    public void addEnemyToList(Character enemy){
        enemyList.add(enemy);
    }

    public int charXPosition(Character character) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.map[i][j] == character) {
                    return i + 1;
                }
            }
        }
        return -1;
    }

    public int charYPosition(Character character) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.map[i][j] == character) {
                    return j + 1;
                }
            }
        }
        return -1;
    }

    public void removeChar(Character character) {
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (this.map[i][j] == character) {
                    this.map[i][j] = null;
                }
            }
        }
    }

    public Character getCharByPos(int col, int row) {
        if (col <= map.length && row <= map[0].length && col > 0 && row > 0 ) {
            return map[col - 1][row - 1];
        }
        return null;
    }
}