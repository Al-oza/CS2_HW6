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
        LinkedQuadTree<String> qt = new LinkedQuadTree<>();
        Position<String> r = qt.addRoot("A");
        qt.addNE(r, "B");
        assertEquals("A", qt.root().getElement());
        assertEquals("B", qt.ne(qt.root()).getElement());

        qt.addNE(qt.ne(qt.root()), "C");
        assertEquals("A", qt.root().getElement());
        assertEquals("B", qt.ne(qt.root()).getElement());
        assertEquals("C", qt.ne(qt.ne(qt.root())).getElement());
    }

    @Test
    public void addSWTest() {
        LinkedQuadTree<String> qt = new LinkedQuadTree<>();
        Position<String> r = qt.addRoot("A");
        qt.addSW(r, "B");
        assertEquals("A", qt.root().getElement());
        assertEquals("B", qt.sw(qt.root()).getElement());

        qt.addSW(qt.sw(qt.root()), "C");
        assertEquals("A", qt.root().getElement());
        assertEquals("B", qt.sw(qt.root()).getElement());
        assertEquals("C", qt.sw(qt.sw(qt.root())).getElement());
    }

    @Test
    public void addSETest() {
        LinkedQuadTree<String> qt = new LinkedQuadTree<>();
        Position<String> r = qt.addRoot("A");
        qt.addSE(r, "B");
        assertEquals("A", qt.root().getElement());
        assertEquals("B", qt.se(qt.root()).getElement());

        qt.addSE(qt.se(qt.root()), "C");
        assertEquals("A", qt.root().getElement());
        assertEquals("B", qt.se(qt.root()).getElement());
        assertEquals("C", qt.se(qt.se(qt.root())).getElement());
    }

    @Test
    public void setTest() {
        LinkedQuadTree<String> qt = new LinkedQuadTree<>();
        Position<String> r = qt.addRoot("A");
        qt.addSW(r, "B");
        assertEquals("A", qt.root().getElement());
        assertEquals("B", qt.sw(qt.root()).getElement());

        qt.addSW(qt.sw(qt.root()), "C");
        assertEquals("A", qt.root().getElement());
        assertEquals("B", qt.sw(qt.root()).getElement());
        assertEquals("C", qt.sw(qt.sw(qt.root())).getElement());

        qt.set(qt.sw(r), "D");
        assertEquals("D", qt.sw(qt.root()).getElement());
    }

    @Test
    public void everythingTest() {
        LinkedQuadTree<String> srng = new LinkedQuadTree<>();
        Position<String> r = srng.addRoot("a");
        srng.addNW(r, "b");
        srng.addNE(r, "c");
        srng.addSW(r, "d");
        srng.addSE(r, "e");
        assertEquals("a", srng.root().getElement());
        assertEquals("b", srng.nw(srng.root()).getElement());
        assertEquals("c", srng.ne(srng.root()).getElement());
        assertEquals("d", srng.sw(srng.root()).getElement());
        assertEquals("e", srng.se(srng.root()).getElement());
        srng.addSW(srng.ne(srng.root()), "f");
        srng.set(srng.se(srng.root()), "g");
        assertEquals(srng.sw(srng.ne(srng.root())).getElement(), "f");
        assertEquals(srng.se(srng.root()).getElement(), "g");
    }
}