

/**
 * Node of BTree - has 2 arrays:
 *      1. Links to lower nodes;
 *      2. Values (numbers\chars);
 *
 * Each node also has int - number of it's elements
 *  and boolean - is that node leaf node or not.
 * @param <Type>
 */
public class MyNode<Type extends Comparable<Type>> {

    MyNode[] children;
    int[] values;
    int numberOfElements = 0;
    boolean isLeaf;
    MyNode<Type> parent;
    int order = 2;

    public MyNode(){
        this.isLeaf = true;
        this.values = new int[4];
        this.children = new MyNode[5];
    }


    public void insertInNode(int key){
        if (this.numberOfElements == 0){
            values[numberOfElements] = (key);
            numberOfElements++;
        }
        else {
            values[numberOfElements] = (key);
            numberOfElements++;
        }
    }

    public String toString() {
        String str = "";
        for (int i = 1; i < numberOfElements+1; i++)
            str += values[i] + " ";
        return str;
    }
}