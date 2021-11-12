import org.junit.*;

import static org.junit.Assert.*;

public class CharacterTest {
    private PlayerChar c;
    private EnemyChar e;
    private Jhin jihn;

    @Before
    public void setUp() {
        c = new PlayerChar("Dummy Character", 100, 40, 2);
        e = new EnemyChar("Dummy Enemy", 100, 40, 2);
        jihn = new Jhin(true);
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

    @Test(timeout = 50)
    public void testcharacterfeatures() {
        assertEquals("Jhin", jihn.getName());
        assertEquals(75, jihn.getMaxHealth());
        assertEquals(60, jihn.getAttack());
        assertEquals(2, jihn.getSpeed());
    }


}
