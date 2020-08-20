package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private Grid sites[][];
    private int numberOfOpenSites;
    private WeightedQuickUnionUF uf;

    private class Grid {
        boolean isOpen;
        boolean isFull;
    }

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        sites = new Grid[N][N];
        numberOfOpenSites = 0;
        uf = new WeightedQuickUnionUF(N*N);
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) { return; }

        sites[row][col].isOpen = true;
        numberOfOpenSites += 1;

        if (row == 0) {
            sites[row][col].isFull = true;

        } else if ()
    }

    private int xyTo1D(int r, int c) {
        return r * sites.length + c;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return sites[row][col].isOpen;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return sites[row][col].isFull;
    }

    // number of open sites
    public int numberOfOpenSites() { return numberOfOpenSites; }

    // does the system percolate?
    public boolean percolates() {

    }

    // use for unit testing (not required)
    public static void main(String[] args) {

    }
}
