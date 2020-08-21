package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean sites[][];
    private int numberOfOpenSites;
    private WeightedQuickUnionUF noBottom;
    private WeightedQuickUnionUF withBottom;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) { throw new IllegalArgumentException("N cannot smaller than 1!"); }
        sites = new boolean[N][N];
        numberOfOpenSites = 0;
        noBottom = new WeightedQuickUnionUF(N*N + 1);
        withBottom = new WeightedQuickUnionUF(N*N + 2); // N*N -> top, N*N + 1 -> bottom
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row >= sites.length || col < 0 || col >= sites.length) {
            throw new IndexOutOfBoundsException("Out Of Bounds !");
        }
        if (isOpen(row, col)) { return; }

        sites[row][col] = true;
        numberOfOpenSites += 1;

        unionNeighbor(row, col);
        if (row == 0) {
            noBottom.union(xyTo1D(row, col), sites.length * sites.length);
            withBottom.union(xyTo1D(row, col), sites.length * sites.length);
        }
        if (row == sites.length - 1) {
            withBottom.union(xyTo1D(row, col), sites.length * sites.length + 1);
        }
    }

    private int xyTo1D(int r, int c) {
        return r * sites.length + c;
    }

    private void unionNeighbor(int row, int col) {
        if (row - 1 >= 0 && sites[row - 1][col]) {
            noBottom.union(xyTo1D(row - 1, col), (xyTo1D(row, col)));
            withBottom.union(xyTo1D(row - 1, col), (xyTo1D(row, col)));
        }
        if (row + 1 < sites.length && sites[row + 1][col]) {
            noBottom.union(xyTo1D(row + 1, col), (xyTo1D(row, col)));
            withBottom.union(xyTo1D(row + 1, col), (xyTo1D(row, col)));
        }
        if (col - 1 >= 0 && sites[row][col - 1]) {
            noBottom.union(xyTo1D(row, col - 1), (xyTo1D(row, col)));
            withBottom.union(xyTo1D(row, col - 1), (xyTo1D(row, col)));
        }
        if (col + 1 < sites.length && sites[row][col + 1]) {
            noBottom.union(xyTo1D(row, col + 1), (xyTo1D(row, col)));
            withBottom.union(xyTo1D(row, col + 1), (xyTo1D(row, col)));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= sites.length || col < 0 || col >= sites.length) {
            throw new IndexOutOfBoundsException("Out Of Bounds !");
        }
        return sites[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= sites.length || col < 0 || col >= sites.length) {
            throw new IndexOutOfBoundsException("Out Of Bounds !");
        }
        return noBottom.connected(sites.length * sites.length, xyTo1D(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() { return numberOfOpenSites; }

    // does the system percolate?
    public boolean percolates() {
        return withBottom.connected(sites.length * sites.length, sites.length * sites.length + 1);
    }

    // use for unit testing (not required)
//    public static void main(String[] args) {
//
//    }
}
