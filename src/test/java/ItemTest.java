import Controllers.Game;
import Entity.EnemyChar;
import Entity.HpPot;
import Entity.Item;
import Entity.Marth;
import UseCase.Action;
import org.junit.*;

import static org.junit.Assert.*;

public class ItemTest {
    private Marth m;
    private EnemyChar e;

    @Before
    public void setUp() {
        m = new Marth();
        e = new EnemyChar("Dummy Enemy");
    }

    @After
    public void tearDown() {
    }

    @Test(timeout = 50)
    public void testItemUsage() {
        Game.enemyChar.add(e);
        Action.attack(m, e);
        assertEquals(70, e.getCurrHealth());
        Item health_potion = new HpPot();
        health_potion.use_item(e);
        assertEquals(90, e.getCurrHealth());
        Item health_potion2 = new HpPot();
        health_potion2.use_item(e);
        assertEquals(100, e.getCurrHealth());
    }
}
