import org.junit.*;

import static org.junit.Assert.*;

public class CharacterTest {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test(timeout = 50)
    public void testCharacterDamage() {
        PlayerChar c = new PlayerChar("Dummy Character");
        EnemyChar e = new EnemyChar("Dummy Enemy");
        Action.attack(c, e);
        assertEquals(90, e.getCurrHealth());
    }
}
