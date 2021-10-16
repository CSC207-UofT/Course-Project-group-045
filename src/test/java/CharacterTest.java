import org.junit.*;

import static org.junit.Assert.*;

public class CharacterTest {
    private PlayerChar c;
    private EnemyChar e;

    @Before
    public void setUp() {
        c = new PlayerChar("Dummy Character");
        e = new EnemyChar("Dummy Enemy");
    }

    @After
    public void tearDown() {
    }

    @Test(timeout = 50)
    public void testCharacterDamage() {
        Action.attack(c, e);
        assertEquals(90, e.getCurrHealth());
        Action.attack(c, e);
        assertEquals(80, e.getCurrHealth());
    }
}
