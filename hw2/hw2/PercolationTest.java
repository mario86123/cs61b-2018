package hw2;
import org.junit.Test;
import static org.junit.Assert.*;

public class PercolationTest {
    @Test
    public void test() {
        Percolation p = new Percolation(4);
        p.open(1, 1);
        assertEquals(1, p.numberOfOpenSites());
        assertEquals(true, p.isOpen(1, 1));
        p.open(2, 2);
        assertEquals(2, p.numberOfOpenSites());
        p.open(3, 2);
        p.open(0,1);
        assertEquals(4, p.numberOfOpenSites());
        assertEquals(true, p.isFull(1, 1));
        assertEquals(false, p.percolates());
        p.open(3,0);
        assertEquals(false, p.isFull(3, 0));
        assertEquals(false, p.percolates());
        p.open(2,1);
        assertEquals(true, p.isFull(2, 1));
        assertEquals(true, p.percolates());
        assertEquals(false, p.isFull(3, 0));
    }
}
