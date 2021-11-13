import java.util.ArrayList;

public class Map {
    private Character[][] map;
    private ArrayList<Character> enemyList = new ArrayList<Character>();

    public Map(int col, int row) {
    this.map = new Character[col][row];
    }

    public void addPlayerChar(PlayerChar player1, int col, int row) {
       this.map[col][row] = player1;
    }

    public void addEnemyChar(EnemyChar enemy1, int col, int row) {
        this.map[col][row] = enemy1;
        enemyList.add(enemy1);
    }

    public  ArrayList<Character> getEnemyList(){
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

    public Character getCharByPos(int col, int row){
        return map[col][row];
    }

}
