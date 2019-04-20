package net.datastructures;

import org.junit.Test;

import java.util.Random;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class SpatialTreeMapEfficientTest {
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
        //m.dump();
        return m;
    }


    @Test
    public void smallEfficiencySubMapTest() {
        SpatialTreeMap<Integer, Integer, Integer> st = small();
        CountingVisitor<Entry<Coord<Integer, Integer>, Integer>> s = new CountingVisitor<>();

        for (Entry<Coord<Integer, Integer>, Integer> e : st.subMap(new Coord<Integer,Integer>(-2, 5), new Coord<Integer,Integer>(1, -4), s)) {
        }

        assertEquals(5, s.getCount());
    }

    @Test
    public void smallEfficiencySubMapLinearTest() {
        SpatialTreeMap<Integer, Integer, Integer> st = small();
        CountingVisitor<Entry<Coord<Integer, Integer>, Integer>> s = new CountingVisitor<>();

        for (Entry<Coord<Integer, Integer>, Integer> e : st.subMapLinear(new Coord<Integer,Integer>(-2, 5), new Coord<Integer,Integer>(1, -4), s)) {
        }

        assertEquals(7, s.getCount());
    }


    @Test
    public void smallEfficiencySubMapNWTest() {
        SpatialTreeMap<Integer, Integer, Integer> st = small();
        CountingVisitor<Entry<Coord<Integer, Integer>, Integer>> s = new CountingVisitor<>();

        for (Entry<Coord<Integer, Integer>, Integer> e : st.subMap(new Coord<Integer,Integer>(-10, 13), new Coord<Integer,Integer>(-1, 8), s)) {
            System.out.println(e);
        }

        assertEquals(2, s.getCount());
    }

    @Test
    public void smallEfficiencySubMapLinearNWTest() {
        SpatialTreeMap<Integer, Integer, Integer> st = small();
        CountingVisitor<Entry<Coord<Integer, Integer>, Integer>> s = new CountingVisitor<>();

        for (Entry<Coord<Integer, Integer>, Integer> e : st.subMapLinear(new Coord<Integer,Integer>(-10, 13), new Coord<Integer,Integer>(-1, 8), s)) {
        }

        assertEquals(7, s.getCount());
    }

    @Test
    public void smallEfficiencySubMapNETest() {
        SpatialTreeMap<Integer, Integer, Integer> st = small();
        CountingVisitor<Entry<Coord<Integer, Integer>, Integer>> s = new CountingVisitor<>();

        for (Entry<Coord<Integer, Integer>, Integer> e : st.subMap(new Coord<Integer,Integer>(5, 9), new Coord<Integer,Integer>(15, 1), s)) {
        }

        System.out.println(s.getCount());
        assertEquals(4, s.getCount());
    }

    @Test
    public void smallEfficiencySubMapLinearNETest() {
        SpatialTreeMap<Integer, Integer, Integer> st = small();
        CountingVisitor<Entry<Coord<Integer, Integer>, Integer>> s = new CountingVisitor<>();

        for (Entry<Coord<Integer, Integer>, Integer> e : st.subMapLinear(new Coord<Integer,Integer>(5, 9), new Coord<Integer,Integer>(15, 1), s)) {
        }

        assertEquals(7, s.getCount());
    }

    @Test
    public void smallEfficiencySubMapEastTest() {
        SpatialTreeMap<Integer, Integer, Integer> st = small();
        CountingVisitor<Entry<Coord<Integer, Integer>, Integer>> s = new CountingVisitor<>();

        for (Entry<Coord<Integer, Integer>, Integer> e : st.subMap(new Coord<Integer,Integer>(14, 14), new Coord<Integer,Integer>(15, -10), s)) {
            System.out.println(e);
        }

        assertEquals(4, s.getCount());
    }

    @Test
    public void smallEfficiencySubMapLinearEastTest() {
        SpatialTreeMap<Integer, Integer, Integer> st = small();
        CountingVisitor<Entry<Coord<Integer, Integer>, Integer>> s = new CountingVisitor<>();

        for (Entry<Coord<Integer, Integer>, Integer> e : st.subMapLinear(new Coord<Integer,Integer>(14, 14), new Coord<Integer,Integer>(15, -10), s)) {
        }

        System.out.println(s.getCount());
        assertEquals(7, s.getCount());
    }

    @Test
    public void mediumEfficiencySubMapTest() {
        SpatialTreeMap<Integer, Integer, Integer> m = medium();
        CountingVisitor<Entry<Coord<Integer, Integer>, Integer>> s = new CountingVisitor<>();

        for (Entry<Coord<Integer, Integer>, Integer> e : m.subMap(new Coord<Integer,Integer>(-10, 10), new Coord<Integer,Integer>(10, -10), s)) {
        }
        System.out.println(s.getCount());
        assertTrue(s.getCount() > 6 && s.getCount() < 15);
    }

    @Test
    public void mediumEfficiencySubMapLinearTest() {
        SpatialTreeMap<Integer, Integer, Integer> m = medium();
        CountingVisitor<Entry<Coord<Integer, Integer>, Integer>> s = new CountingVisitor<>();

        for (Entry<Coord<Integer, Integer>, Integer> e : m.subMapLinear(new Coord<>(-10, 10), new Coord<>(10, -10), s)) {
        }
        System.out.println(s.getCount());
        assertEquals(20, s.getCount());
    }

    @Test
    public void mediumEfficiencySubMapQuadrantTest() {
        SpatialTreeMap<Integer, Integer, Integer> m = medium();
        CountingVisitor<Entry<Coord<Integer, Integer>, Integer>> s = new CountingVisitor<>();

        for (Entry<Coord<Integer, Integer>, Integer> e : m.subMap(new Coord<Integer,Integer>(4, 8), new Coord<Integer,Integer>(7, 3), s)) {
        }
        System.out.println(s.getCount());
        assertTrue(s.getCount() > 1 && s.getCount() < 10);
    }

    @Test
    public void mediumEfficiencySubMapLinearQuadrantTest() {
        SpatialTreeMap<Integer, Integer, Integer> m = medium();
        CountingVisitor<Entry<Coord<Integer, Integer>, Integer>> s = new CountingVisitor<>();

        for (Entry<Coord<Integer, Integer>, Integer> e : m.subMapLinear(new Coord<>(4, 8), new Coord<>(7, 3), s)) {
        }
        System.out.println(s.getCount());
        assertEquals(20, s.getCount());
    }

}