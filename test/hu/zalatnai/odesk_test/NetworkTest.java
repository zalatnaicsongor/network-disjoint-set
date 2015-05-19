package hu.zalatnai.odesk_test;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NetworkTest {
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsIfANonPositiveIntegerIsSuppliedAsTheElementCount() {
        new Network(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQueryThrowsIfAnIllegalValueIsQueried() {
        Network network = new Network(3);
        network.query(-1, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConnectThrowsIfAnIllegalValueIsConnected() {
        Network network = new Network(3);
        network.connect(-1, 4);
    }

    @Test
    public void testBasicProperties() {
        Network network = new Network(3);
        network.connect(1, 2);
        assertTrue(network.query(1, 1)); //reflexive
        assertTrue(network.query(1, 2)); //symmetric
        assertTrue(network.query(2, 1)); //symmetric
        network.connect(3, 2);
        assertTrue(network.query(1, 3)); //transitive
        assertTrue(network.query(3, 1)); //transitive
    }

    @Test
    public void testTheNodesAreInitiallyNotConnected() {
        Network network = new Network(3);
        assertFalse(network.query(1, 2));
        assertFalse(network.query(1, 3));
        assertFalse(network.query(2, 3));
    }

    @Test
    public void testWithGivenData() {
        Network network = new Network(8);

        network.connect(1, 2);
        network.connect(6, 2);
        network.connect(2, 4);
        network.connect(5, 8);

        assertTrue(network.query(1, 6));
        assertTrue(network.query(6, 4));
        assertFalse(network.query(7, 4));
        assertFalse(network.query(5, 6));
    }

    @Test
    public void testWithSomePreGeneratedNodesAndVertices() {
        Network network = new Network(23);
        network.connect(1, 2);
        network.connect(2, 8);
        network.connect(8, 10);
        network.connect(7, 2);
        network.connect(9, 1);
        network.connect(23, 1);
        network.connect(23, 2);
        network.connect(23, 7);

        network.connect(11, 3);
        network.connect(11, 5);
        network.connect(11, 16);
        network.connect(16, 3);
        network.connect(16, 14);

        network.connect(12, 17);
        network.connect(19, 17);
        network.connect(12, 19);

        network.connect(16, 12);


        assertTrue(network.query(1, 23));
        assertTrue(network.query(1, 7));
        assertTrue(network.query(7, 2));
        assertTrue(network.query(10, 9));

        assertTrue(network.query(11, 16));
        assertTrue(network.query(14, 3));
        assertTrue(network.query(16, 5));
        assertTrue(network.query(16, 3));

        assertTrue(network.query(16, 12));
        assertTrue(network.query(16, 17));
        assertTrue(network.query(19, 3));

        assertFalse(network.query(16, 1));
        assertFalse(network.query(16, 23));
        assertFalse(network.query(6, 2));
        assertFalse(network.query(22, 23));
    }
}
