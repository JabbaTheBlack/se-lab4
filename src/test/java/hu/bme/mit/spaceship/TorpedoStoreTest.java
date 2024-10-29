package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TorpedoStoreTest {
    private TorpedoStore store;


    @BeforeEach
    void setUp() {
        store = new TorpedoStore(5, 0.0);
    }

     @AfterEach
    void tearDown() {
        store = null;
    }

    @Test
    void fire_Success() {
        // Arrange
        store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }





    @Test
    void testFireWithExactCount() {
        store = new TorpedoStore(2, 0.0);

        boolean result = store.fire(2);

        assertTrue(result, "Firing should succeed when exactly enough torpedoes are present");
        assertTrue(store.isEmpty(), "Torpedo store should be empty after firing all torpedoes");
    }


    @Test
    void testInvalidFireRequestMoreThanAvailable() {
        store = new TorpedoStore(1, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(2), "Should throw exception when firing more than available");
    }

    @Test
    void testInvalidFireRequestLessThanOne() {
        store = new TorpedoStore(1, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(0), "Should throw exception when firing zero or negative amount");
    }

    @Test
    void testIsEmptyWithTorpedoes() {
        store = new TorpedoStore(1, 0.0);
        boolean empty = store.getTorpedoCount() == 0;
        assertFalse(empty, "isEmpty should return false when torpedoes are available");
    }

    @Test
    void testIsEmptyWithNoTorpedoes() {
        store = new TorpedoStore(0, 0.0);
        boolean empty = store.getTorpedoCount() == 0;
        assertTrue(empty, "isEmpty should return true when no torpedoes are left");
    }
}
