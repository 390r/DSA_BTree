

public interface Interface {

    /**
     *  Full method's descriptions in MyAVL class {@link MyBTree}.
     */

    /*
     * First step of any insertion. Calls func InsertKey {@link MyBTree#insertKey(MyNode, int)}
     *  Also checks balance after insertion recursively.
     */
    void insert(int value);


    /*
     * Traversal on Tree in order (left -> parent -> right). Recursive.
     *  If we are in leaf node - simply return all key here (from left to right).
     *  Else call this function recursively for all links in the node.
     */
    String inOrderTrav(MyNode startNode);

}
