package net.datastructures;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedQuadTreeTest {

    @Test
    public void addNWTest() {
        LinkedQuadTree<String> qt = new LinkedQuadTree<>();
        Position<String> r = qt.addRoot("A");
        qt.addNW(r, "B");
        assertEquals("A", qt.root().getElement());
        assertEquals("B", qt.nw(qt.root()).getElement());

        qt.addNW(qt.nw(qt.root()), "C");
        assertEquals("A", qt.root().getElement());
        assertEquals("B", qt.nw(qt.root()).getElement());
        assertEquals("C", qt.nw(qt.nw(qt.root())).getElement());
    }

    @Test
    public void addNETest() {
        // write a test
        assertTrue(false);
    }

    @Test
    public void addSWTest() {
        // write a test
        assertTrue(false);
    }

    @Test
    public void addSETest() {
        // write a test
        assertTrue(false);
    }

    @Test
    public void setTest() {
        // write a test for the LinkedQuadTree.set method
        assertTrue(false);
    }

    @Test
    public void everythingTest() {
        // write a test
        assertTrue(false);
    }
}