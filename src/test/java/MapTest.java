import org.junit.*;

import static org.junit.Assert.*;

public class MapTest {
    private PlayerChar c;
    private EnemyChar e;
    private Map m = new Map();

    @Before
    public void setUp() {
        c = new PlayerChar("Dummy Character");
        e = new EnemyChar("Dummy Enemy");
    }

    @After
    public void tearDown() {
    }

    @Test(timeout = 50)
    public void testCharacterPosition() {

    }
}
