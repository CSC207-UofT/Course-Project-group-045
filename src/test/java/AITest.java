import org.junit.*;

import static org.junit.Assert.*;
public class AITest {
    private Character marth;
    private Character hector;
    private Character enemy1;
    private Map fiveByFive = new Map(5,5);
    private Game testGame;

    @Before
    public void setUp() {
        marth = new Marth();
        hector = new Hector();
        enemy1 = new EnemyChar(new Object[]{"Axe", 100, 40, 2, 3, 1});
        testGame = new Game();

        testGame.currMap = fiveByFive;


    }

    @After
    public void tearDown() {
    }

    @Test(timeout = 50)
    public void testOneEnemyClose() {
        testGame.playerChar.add(marth);
        fiveByFive.addChar(enemy1, 1, 1);
        fiveByFive.addChar(marth,1,2);
        Action.AI(enemy1);
        assertEquals(marth.getCurrHealth(), 60);
        assertEquals(fiveByFive.charXPosition(enemy1), 1);
        assertEquals(fiveByFive.charYPosition(enemy1), 1);
    }

    @Test(timeout = 50)
    public void testOneEnemyRanged() {
        testGame.playerChar.add(marth);
        fiveByFive.addChar(enemy1, 1, 1);
        fiveByFive.addChar(marth,1,3);
        Action.AI(enemy1);
        assertEquals(marth.getCurrHealth(), 60);
        assertEquals(fiveByFive.charXPosition(enemy1), 1);
        assertEquals(fiveByFive.charYPosition(enemy1), 2);
    }
    @Test(timeout = 50)
    public void testTwoEnemies() {
        testGame.playerChar.add(marth);
        testGame.playerChar.add(hector);
        fiveByFive.addChar(enemy1, 1, 1);
        fiveByFive.addChar(marth,1,3);
        fiveByFive.addChar(hector,3 ,1);
        hector.reduceCurrHealth(10);
        Action.AI(enemy1);
        assertEquals(hector.getCurrHealth(), 50);
        assertEquals(marth.getCurrHealth(), 100);
        assertEquals(fiveByFive.charXPosition(enemy1), 2);
        assertEquals(fiveByFive.charYPosition(enemy1), 1);
    }
    @Test(timeout = 50)
    public void enemyOutOfRangeStraight() {
        testGame.playerChar.add(marth);
        fiveByFive.addChar(enemy1, 1, 1);
        fiveByFive.addChar(marth,1,5);
        Action.AI(enemy1);
        assertEquals(marth.getCurrHealth(), 100);
        assertEquals(fiveByFive.charXPosition(enemy1), 1);
        assertEquals(fiveByFive.charYPosition(enemy1), 3);
    }
    @Test(timeout = 50)
    public void enemyOutOfRangeDiagonal() {
        testGame.playerChar.add(marth);
        fiveByFive.addChar(enemy1, 1, 1);
        fiveByFive.addChar(marth,3,3);
        Action.AI(enemy1);
        assertEquals(marth.getCurrHealth(), 100);
        assertEquals(fiveByFive.charXPosition(enemy1), 3);
        assertEquals(fiveByFive.charYPosition(enemy1), 1);
    }


    }

