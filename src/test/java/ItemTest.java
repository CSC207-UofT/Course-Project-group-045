import org.junit.*;

import static org.junit.Assert.*;

public class ItemTest {
    private PlayerChar c;
    private EnemyChar e;

    @Before
    public void setUp() {
        c = new PlayerChar("Dummy Character", 100, 40, 2);
        e = new EnemyChar("Dummy Enemy", 100, 40, 2);
    }

    @After
    public void tearDown() {
    }

    @Test(timeout = 50)
    public void testItemUsage() {
        Action.attack(c, e);
        assertEquals(90, e.getCurrHealth());
        Item health_potion = new HpPot();
        health_potion.use_item(e);
        assertEquals(100, e.getCurrHealth());
        assertEquals(0, health_potion.check_usage());
        Item health_potion2 = new HpPot();
        health_potion2.use_item(e);
        assertEquals(100, e.getCurrHealth());
    }
}
