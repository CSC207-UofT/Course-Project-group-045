import org.junit.*;

import static org.junit.Assert.*;

public class CharacterTest {
    private Character c;
    private EnemyChar e;
    private Takumi Takumi;

    @Before
    public void setUp() {
        c = new Marth();
        e = new EnemyChar(new Object[]{"Sword", 75, 40, 2, 3, 1});
        Takumi = new Takumi();
    }

    @After
    public void tearDown() {
    }

    @Test(timeout = 50)
    public void testCharacterDamage() {
        Action.attack(c, e);
        assertEquals(45, e.getCurrHealth());
        Action.attack(c, e);
        assertEquals(15, e.getCurrHealth());
    }

    @Test(timeout = 50)
    public void testcharacterfeatures() {
        assertEquals("Takumi", Takumi.getName());
        assertEquals(75, Takumi.getMaxHealth());
        assertEquals(40, Takumi.getAttack());
        assertEquals(2, Takumi.getSpeed());
    }



}
