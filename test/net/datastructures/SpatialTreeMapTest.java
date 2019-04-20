package net.datastructures;

import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;

public class SpatialTreeMapTest {

    private SpatialTreeMap<Integer, Integer, Integer> small() {
        SpatialTreeMap<Integer, Integer, Integer> m = new SpatialTreeMap<>();
        m.put(new Coord<>(0, 0), 0);
        m.put(new Coord<>(-3, 4), 1);
        m.put(new Coord<>(3, 2), 2);
        m.put(new Coord<>(-5, -6), 3);
        m.put(new Coord<>(6, -5), 4);
        m.put(new Coord<>(10, 12), 5);
        m.put(new Coord<>(7, 7), 6);
        //m.dump();
        return m;
    }

    private SpatialTreeMap<Integer, Integer, Integer> medium() {
        SpatialTreeMap<Integer, Integer, Integer> m = new SpatialTreeMap<>();
        Random r = new Random(2230); // deterministic
        for (int n=0; n<20; n++) {
            int i = r.nextInt(25);
            int j = r.nextInt(25);
            m.put(new Coord<>(i, j), n);
        }
        m.dump();
        return m;
    }

    @Test
    public void testSmallPut() {
        SpatialTreeMap<Integer, Integer, Integer> m = small();
        assertEquals(7, m.size());
        assertEquals(4, m.treeHeight());
    }

    @Test
    public void testMediumPut() {
        SpatialTreeMap<Integer, Integer, Integer> m = medium();
        assertEquals(20, m.size());
        assertEquals(6, m.treeHeight());
    }

    @Test
    public void testSmallGet() {
        SpatialTreeMap<Integer, Integer, Integer> m = small();
        assertEquals(null, m.get(new Coord<>(0,2)));
        assertEquals(null, m.get(new Coord<>(-6,-5)));
        assertEquals((int)3, (int)m.get(new Coord<>(-5,-6)));
        assertEquals((int)6, (int)m.get(new Coord<>(7,7)));
        assertEquals((int)0, (int)m.get(new Coord<>(0,0)));
    }

    @Test
    public void testMediumGet() {
        SpatialTreeMap<Integer, Integer, Integer> m = medium();
        assertEquals(null, m.get(new Coord<>(0,2)));
        assertEquals(null, m.get(new Coord<>(0,0)));
        assertEquals((int)3, (int)m.get(new Coord<>(11,3)));
        assertEquals((int)18, (int)m.get(new Coord<>(10, 15)));
        assertEquals((int)12, (int)m.get(new Coord<>(1,1)));
    }

    @Test
    public void smallSubMapTest() {
        SpatialTreeMap<Integer, Integer, Integer> st = small();
        Set<Coord<Integer, Integer>> found = new HashSet<>();
        Visitor<Entry<Coord<Integer, Integer>, Integer>> s = new CountingVisitor<>();
        //Visitor<Entry<Coord<Integer, Integer>, Integer>> s = new PrintVisitor<>();

        for (Entry<Coord<Integer, Integer>, Integer> e : st.subMap(new Coord<Integer,Integer>(-2, 3), new Coord<Integer,Integer>(6, -6), s)) {
            found.add(e.getKey());
            //System.out.println(e);
        }

        assertEquals(3, found.size());
        assertTrue(found.contains(new Coord<>(0,0)));
        assertTrue(found.contains(new Coord<>(3,2)));
        assertTrue(found.contains(new Coord<>(6,-5)));
    }

    @Test
    public void smallSubMapEmptyTest() {
        SpatialTreeMap<Integer, Integer, Integer> st = small();
        Set<Coord<Integer, Integer>> found = new HashSet<>();
        Visitor<Entry<Coord<Integer, Integer>, Integer>> s = new CountingVisitor<>();
        //Visitor<Entry<Coord<Integer, Integer>, Integer>> s = new PrintVisitor<>();

        for (Entry<Coord<Integer, Integer>, Integer> e : st.subMap(new Coord<Integer,Integer>(-10, 13), new Coord<Integer,Integer>(8, 8), s)) {
            found.add(e.getKey());
            //System.out.println(e);
        }

        assertEquals(0, found.size());
    }

    @Test
    public void smallSubMapTest2() {
        SpatialTreeMap<Integer, Integer, Integer> st = small();
        Set<Coord<Integer, Integer>> found = new HashSet<>();
        Visitor<Entry<Coord<Integer, Integer>, Integer>> s = new CountingVisitor<>();
        //Visitor<Entry<Coord<Integer, Integer>, Integer>> s = new PrintVisitor<>();

        for (Entry<Coord<Integer, Integer>, Integer> e : st.subMap(new Coord<Integer,Integer>(5, 10), new Coord<Integer,Integer>(15, 1), s)) {
            found.add(e.getKey());
            //System.out.println(e);
        }

        assertEquals(1, found.size());
        assertTrue(found.contains(new Coord<>(7,7)));
    }

    @Test
    public void mediumSubMapTest() {
        SpatialTreeMap<Integer, Integer, Integer> m = medium();

        Set<Coord<Integer, Integer>> found = new HashSet<>();
        Visitor<Entry<Coord<Integer, Integer>, Integer>> s = new CountingVisitor<>();
        //Visitor<Entry<Coord<Integer, Integer>, Integer>> s = new PrintVisitor<>();

        for (Entry<Coord<Integer, Integer>, Integer> e : m.subMap(new Coord<Integer,Integer>(-10, 10), new Coord<Integer,Integer>(10, -10), s)) {
            found.add(e.getKey());
            System.out.println(e);
        }
        assertEquals(6, found.size());
        assertTrue(found.contains(new Coord<>(0, 10)));
        assertTrue(found.contains(new Coord<>(6, 9)));
        assertTrue(found.contains(new Coord<>(2, 6)));
        assertTrue(found.contains(new Coord<>(10, 2)));
        assertTrue(found.contains(new Coord<>(10, 1)));
        assertTrue(found.contains(new Coord<>(1, 1)));
    }


    @Test
    public void smallSubMapLinearTest2() {
        SpatialTreeMap<Integer, Integer, Integer> st = small();
        Set<Coord<Integer, Integer>> found = new HashSet<>();
        Visitor<Entry<Coord<Integer, Integer>, Integer>> s = new CountingVisitor<>();
        //Visitor<Entry<Coord<Integer, Integer>, Integer>> s = new PrintVisitor<>();

        for (Entry<Coord<Integer, Integer>, Integer> e : st.subMapLinear(new Coord<Integer,Integer>(5, 10), new Coord<Integer,Integer>(15, 1), s)) {
            found.add(e.getKey());
            //System.out.println(e);
        }

        assertEquals(1, found.size());
        assertTrue(found.contains(new Coord<>(7,7)));
    }


    @Test
    public void smallSubMapLinearTest() {
        SpatialTreeMap<Integer, Integer, Integer> st = small();
        Set<Coord<Integer, Integer>> found = new HashSet<>();
        Visitor<Entry<Coord<Integer, Integer>, Integer>> s = new CountingVisitor<>();
        //Visitor<Entry<Coord<Integer, Integer>, Integer>> s = new PrintVisitor<>();

        for (Entry<Coord<Integer, Integer>, Integer> e : st.subMapLinear(new Coord<Integer,Integer>(-2, 3), new Coord<Integer,Integer>(6, -6), s)) {
            found.add(e.getKey());
            //System.out.println(e);
        }

        assertEquals(3, found.size());
        assertTrue(found.contains(new Coord<>(0,0)));
        assertTrue(found.contains(new Coord<>(3,2)));
        assertTrue(found.contains(new Coord<>(6,-5)));
    }


    @Test
    public void mediumSubMapLinearTest() {
        SpatialTreeMap<Integer, Integer, Integer> m = medium();

        Set<Coord<Integer, Integer>> found = new HashSet<>();
        Visitor<Entry<Coord<Integer, Integer>, Integer>> s = new CountingVisitor<>();
        //Visitor<Entry<Coord<Integer, Integer>, Integer>> s = new PrintVisitor<>();

        for (Entry<Coord<Integer, Integer>, Integer> e : m.subMapLinear(new Coord<>(-10, 10), new Coord<>(10, -10), s)) {
            found.add(e.getKey());
            //System.out.println(e);
        }
        assertEquals(6, found.size());
        assertTrue(found.contains(new Coord<>(0, 10)));
        assertTrue(found.contains(new Coord<>(6, 9)));
        assertTrue(found.contains(new Coord<>(2, 6)));
        assertTrue(found.contains(new Coord<>(10, 2)));
        assertTrue(found.contains(new Coord<>(10, 1)));
        assertTrue(found.contains(new Coord<>(1, 1)));
    }

    @Test
    public void smallSubMapLinearEmptyTest() {
        SpatialTreeMap<Integer, Integer, Integer> st = small();
        Set<Coord<Integer, Integer>> found = new HashSet<>();
        Visitor<Entry<Coord<Integer, Integer>, Integer>> s = new CountingVisitor<>();
        //Visitor<Entry<Coord<Integer, Integer>, Integer>> s = new PrintVisitor<>();

        for (Entry<Coord<Integer, Integer>, Integer> e : st.subMapLinear(new Coord<Integer,Integer>(-10, 13), new Coord<Integer,Integer>(8, 8), s)) {
            found.add(e.getKey());
            //System.out.println(e);
        }

        assertEquals(0, found.size());
    }


}