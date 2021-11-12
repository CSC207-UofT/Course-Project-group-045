import org.junit.*;

import static org.junit.Assert.*;

public class MapTest {
    private PlayerChar c;
    private PlayerChar a;
    private EnemyChar e;
    private EnemyChar b;
    private Map m = new Map();

    @Before
    public void setUp() {
        c = new PlayerChar("Dummy Character", 100, 40, 2);
        e = new EnemyChar("Dummy Enemy", 100, 40, 2);
        a = new PlayerChar("Player247", 100, 40, 2);
        b = new EnemyChar("Enemy555", 100, 40, 2);
    }

    @After
    public void tearDown() {
    }

    @Test(timeout = 50)
    public void testCharacterPosition() {
        m.addPlayerChar(c, a);
        m.addEnemyChar(e, b);
        assertEquals(m.charPosition(c), "(0,0)");
        assertEquals(m.charPosition(a), "(0,1)");
        assertEquals(m.charPosition(e), "(1,0)");
        assertEquals(m.charPosition(b), "(1,1)");
    }
}
