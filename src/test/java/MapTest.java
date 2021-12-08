import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MapTest {
    private Marth p1;
    private Hector p2;
    private EnemyChar e1;
    private EnemyChar e2;
    private Map m = new Map(10,10);

    @Before
    public void setUp() {
        p1 = new Marth();
        p2 = new Hector();
        e1 = new EnemyChar("Test Enemy 1");
    }

    @After
    public void tearDown() {
    }

    @Test(timeout = 50)
    public void testCharacterPosition() {
        m.addChar(p1, 1, 5);
        m.addChar(p2, 3,8);
        assert m.charXPosition(p1) == 1;
        assert m.charYPosition(p1) == 5;
        assert m.charXPosition(p2) == 3;
        assert m.charYPosition(p2) == 8;
        assert m.getCharByPos(1, 5) == p1;
        assert m.getCharByPos(3, 8) == p2;
    }

    @Test(timeout = 50)
    public void testRemoveChar() {
        m.addChar(p1, 1, 1);
        m.addChar(e1, 3, 5);
        m.removeChar(p1);
        assert m.getCharByPos(1, 1) == null;
        assert m.getCharByPos(3, 5) == e1;
    }
}
