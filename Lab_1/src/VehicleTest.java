import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class VehicleTest {

    private Vehicle vehicle1, vehicle2;

    @BeforeEach
    void setUp() {
        vehicle1 = new Vehicle();
        vehicle2 = new Vehicle(1, "south");
    }

    @AfterEach
    void tearDown() {
        if (vehicle1 != null) {
            vehicle1.finalize();
            vehicle1 = null;
        }

        if (vehicle2 != null) {
            vehicle2.finalize();
            vehicle2 = null;
        }
    }

    @Test
    void testFinalize() {
        vehicle1.finalize();
        vehicle1 = null;
        assertEquals(1, Vehicle.totalVehicle());

        vehicle2.finalize();
        vehicle2 = null;
        assertEquals(0, Vehicle.totalVehicle());
    }

    @Test
    void setSpeed() {
        vehicle1.setSpeed(2);
        assertEquals(2, vehicle1.getSpeed());

        vehicle2.setSpeed(3);
        assertEquals(3, vehicle2.getSpeed());
    }

    @Test
    void setDir() {
        vehicle1.setDir("east");
        assertEquals("east", vehicle1.getDir());

        vehicle2.setDir("west");
        assertEquals("west", vehicle2.getDir());
    }

    @Test
    void getSpeed() {
        assertEquals(0, vehicle1.getSpeed());
        assertEquals(1, vehicle2.getSpeed());
    }

    @Test
    void getDir() {
        assertEquals("north", vehicle1.getDir());
        assertEquals("south", vehicle2.getDir());
    }

    @Test
    void totalVehicle() {
        assertEquals(2, Vehicle.totalVehicle());
    }
}