
public class Map {
    private Object[][] map;

    public Map() {
    this.map = new Object[2][2];
    }

    public void addPlayerChar(PlayerChar player1, PlayerChar player2) {
       this.map[0][0] = player1;
       this.map[0][1] = player2;
    }

    public void addEnemyChar(EnemyChar enemy1, EnemyChar enemy2) {
        this.map[1][0] = enemy1;
        this.map[1][1] = enemy2;
    }

    public String charPosition(Character character) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (this.map[i][j] == character) {
                    return "(" + i + "," + j + ")";
                }
            }
        }
        return null;
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


}
