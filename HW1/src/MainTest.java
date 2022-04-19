import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void findLast() {
        int[] x = {2, 3, 5};
        int y = 2;
        assertEquals(0, Main.findLast(x, y));
    }

    @Test
    void lastZero() {
        int[] x = {0, 1, 0};
        assertEquals(2, Main.lastZero(x));
    }

    @Test
    void countPositive() {
        int[] x = {-4, 2, 0, 2};
        assertEquals(2, Main.countPositive(x));
    }

    @Test
    void oddOrPos() {
        int[] x = {-3, -2, 0, 1, 4};
        assertEquals(3, Main.oddOrPos(x));
    }
}