package hu.zalatnai.odesk_test;

import hu.zalatnai.odesk_test.datastructure.DisjointSet;

/**
 * The basic idea here is to solve the problem by maintaining a list of connected components in the graph
 * - When two elements are connected with each other, they will be in the same connected component
 * - When two elements are being queried if they are connected, they need to be checked if they
 * belong to the same connected component.
 * <p>
 * This uses the Disjoint Set data structure to maintain a list of connected components.
 * Whenever two nodes in the network are connected, the sets in which the two nodes belong will be united to each other
 * Whenever two nodes are queried if they are connected, the result will be based upon if the two nodes belong to the same set
 */
public class Network {

    private final int elements;

    private final DisjointSet underlyingDisjointSet;

    public Network(int elements) {
        if (elements < 1) {
            throw new IllegalArgumentException();
        }

        this.elements = elements;
        underlyingDisjointSet = new DisjointSet(elements);
    }

    public void connect(int a, int b) {
        if (a < 1 || b < 1 || a > elements || b > elements) {
            throw new IllegalArgumentException();
        }

        underlyingDisjointSet.union(a - 1, b - 1);
    }

    public boolean query(int a, int b) {
        if (a < 1 || b < 1 || a > elements || b > elements) {
            throw new IllegalArgumentException();
        }

        return underlyingDisjointSet.find(a - 1) == underlyingDisjointSet.find(b - 1);
    }
}
