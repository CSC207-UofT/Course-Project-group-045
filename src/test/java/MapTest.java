import org.junit.*;

import static org.junit.Assert.*;

public class MapTest {
    private Marth c;
    private Hector a;
    private EnemyChar e;
    private EnemyChar b;
    private Map m = new Map(2,2);

    @Before
    public void setUp() {
        c = new Marth();
        e = new EnemyChar(new Object[]{"Sword", 75, 40, 2, 3, 1});
        a = new Hector();
        b = new EnemyChar(new Object[]{"Axe", 75, 50, 2, 3, 1});
    }

    @After
    public void tearDown() {
    }

    @Test(timeout = 50)
    public void testCharacterPosition() {
        m.addChar(c, 1, 1);
        m.addChar(e, 1,2);
        assertEquals(m.charXPosition(c), 1);
        assertEquals(m.charYPosition(c), 1);
        assertEquals(m.charXPosition(e), 1);
        assertEquals(m.charYPosition(e), 2);
    }
}
