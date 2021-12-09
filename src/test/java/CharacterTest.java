import Controllers.Game;
import Entity.EnemyChar;
import Entity.Marth;
import UseCase.Action;
import org.junit.*;

import static org.junit.Assert.*;

public class CharacterTest {
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
    public void testCharacterDamage() {
        Game.enemyChar.add(e);
        Action.attack(m, e);
        assertEquals(70, e.getCurrHealth());
        Action.attack(m, e);
        assertEquals(40, e.getCurrHealth());
    }

    @Test(timeout = 50)
    public void testcharacterfeatures() {
        assertEquals("Entity.Marth", m.getName());
        assertEquals(90, m.getMaxHealth());
        assertEquals(30, m.getAttack());
        assertEquals(2, m.getSpeed());
    }

    @Test(timeout = 50)
    public void testMarthUltimate(){
        m.reduceCurrHealth(10);
        m.ultimate(e);
        assert e.getCurrHealth() == 60;
        assert m.getCurrHealth() == 90;
    }


}
