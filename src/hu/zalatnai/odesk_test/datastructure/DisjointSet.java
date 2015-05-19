package hu.zalatnai.odesk_test.datastructure;

import java.util.Arrays;

public class DisjointSet {

    private final int[] parentArray;
    private final int[] rankArray;

    private final int elementCount;

    public DisjointSet(int elementCount) {
        if (elementCount < 1) {
            throw new IllegalArgumentException();
        }

        this.elementCount = elementCount;

        parentArray = new int[elementCount];
        rankArray = new int[elementCount];
        Arrays.fill(rankArray, 0);
        for (int i = 0; i < elementCount; i++) {
            parentArray[i] = i;
        }
    }

    /**
     * Unites two sets represented by x and y
     * @param x first element
     * @param y second element
     */
    public void union(int x, int y) {
        if (x >= elementCount || x < 0) {
            throw new IllegalArgumentException();
        }

        if (y >= elementCount || y < 0) {
            throw new IllegalArgumentException();
        }

        int rootOfX = find(x);
        int rootOfY = find(y);

        if (rootOfX == rootOfY) {
            return;
        }

        //attach the smaller tree to the larger tree
        if (rankArray[rootOfX] < rankArray[rootOfY]) {
            setParent(rootOfX, rootOfY);
        } else if (rankArray[rootOfX] > rankArray[rootOfY]) {
            setParent(rootOfY, rootOfX);
        } else {
            setParent(rootOfY, rootOfX);
            rankArray[rootOfX] = rankArray[rootOfX] + 1;
        }
    }

    /**
     * Returns the element which represents the set in which the supplied element lies.
     * @param element An element of a set
     * @return The index of element which represents the set
     */
    public int find(int element) {
        if (element >= elementCount || element < 0) {
            throw new IllegalArgumentException();
        }

        if (getParent(element) == element) {
            return element;
        } else {
            //Path compression, anything in a tree which is not attached to the root will be attached to the root
            int newParent = find(getParent(element));
            setParent(element, newParent);
            return newParent;
        }
    }

    private int getParent(int element) {
        return parentArray[element];
    }

    private void setParent(int element, int parent) {
        parentArray[element] = parent;
    }
}
