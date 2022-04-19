import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class BoundedQueueTest {

    BoundedQueue boundedQueue;

    @BeforeEach
    void setUp() {
        boundedQueue = new BoundedQueue(1);
    }

    /**
     * C1: Constructor
     * C2: If argument is less than 0
     * Test Requirement: {TF, FT}
     */
    @org.junit.jupiter.api.Test
    void BoundedQueueConstructor() {
        // TF
        assertDoesNotThrow(() -> {
            new BoundedQueue(10);
        });

        // FT
        assertThrows(IllegalArgumentException.class, () -> {
            new BoundedQueue(-1);
        });
    }

    /**
     * C3: Make o the newest element of the queue
     * C4: If argument is null
     * C7: If queue is full
     * Test Requirement: {TFF, FTF, FFT}
     */
    @org.junit.jupiter.api.Test
    void enQueue() {

        Object object = new Object();

        // TFF
        assertDoesNotThrow(() -> {
            boundedQueue.enQueue(object);
        });
        assertEquals(object, boundedQueue.deQueue());

        // FTF
        assertThrows(NullPointerException.class, () -> {
            boundedQueue.enQueue(null);
        });
        assertFalse(boundedQueue.isFull());


        // FFT
        assertNotEquals(null, object);
        assertThrows(IllegalStateException.class, () -> {
            while(!boundedQueue.isFull())
                boundedQueue.enQueue(object);
            boundedQueue.enQueue(object);
        });

    }


    /**
     * C5: Remove and return oldest element of the queue
     * C6: If queue is empty
     * Test Requirement: {TF, FT}
     */
    @org.junit.jupiter.api.Test
    void deQueue() {

        Object object = new Object();
        boundedQueue.enQueue(object);

        // TF
        assertDoesNotThrow(()->{
            assertEquals(object, boundedQueue.deQueue());
        });

        // FT
        assertThrows(IllegalStateException.class, () -> {
            boundedQueue.deQueue();
        });


    }

    /**
     * C6: If queue is empty
     * Test Requirement: {F, T}
     */
    @org.junit.jupiter.api.Test
    void isEmpty() {
        Object object = new Object();

        // F
        boundedQueue.enQueue(object);
        assertFalse(boundedQueue.isEmpty());

        // T
        boundedQueue.deQueue();
        assertTrue(boundedQueue.isEmpty());

    }

    /**
     * C7: If queue is full
     * Test Requirement: {F, T}
     */
    @org.junit.jupiter.api.Test
    void isFull() {
        Object object = new Object();

        // F
        assertFalse(boundedQueue.isFull());

        // T
        while(!boundedQueue.isFull())
            boundedQueue.enQueue(object);
        assertTrue(boundedQueue.isFull());
    }
}