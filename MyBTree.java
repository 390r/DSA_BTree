
public class MyBTree implements Interface{

    protected MyNode root = null;
    protected int order;

    public MyBTree(){
        MyNode root = new MyNode();
        root.isLeaf = true;
        root.numberOfElements = 0;
    }


    /**
     * First step of any insertion. Calls func InsertKey {@link MyBTree#insertKey(MyNode, int)}
     *  Also checks balance after insertion recursively.
     * @param val
     */
    public void insert(int val) {
        if (root == null){
            root = new MyNode();
        }

        MyNode temp = root;

        insertKey(root, val);

        if (temp.numberOfElements == 3){
            MyNode newRoot = new MyNode();
            root = newRoot;
            newRoot.isLeaf = false;
            newRoot.numberOfElements = 0;
            newRoot.children[1] = temp;
            breakNode(newRoot, 1, temp);
        }
    }


    /**
     * If node has less or equal than m-1 elements - just push new key on right position,
     *  also checks balance after insertion.
     *  It seems to work correctly...
     * @param rt
     * @param val
     */
    private void  insertKey(MyNode rt, int val){
        int i = rt.numberOfElements;

        if (rt.isLeaf){
            while (i>=1 && val < rt.values[i]){
                rt.values[i+1] = rt.values[i];
                i--;
            }
            rt.values[i+1] = val;
            rt.numberOfElements++;
        }
        else {
            while (i>=1 && val < rt.values[i]){
                i--;
            }
            i++;

            if(rt.children[i].numberOfElements == 3){
                breakNode(rt, i, rt.children[i]);

                if (val > rt.values[i])
                    i++;
            }

            insertKey(rt.children[i], val);
        }
        if(!rt.isLeaf && rt.children[i].numberOfElements == 3)
            breakNode(rt, i, rt.children[i]);
    }

    /**
     * Checks balance of node, if it's not well balanced - re-balancing it.
     * @param rootNode
     */
    private void checkBalanced(MyNode rootNode){
        // if leaf and overfull - re-balance.
        if (rootNode.isLeaf && rootNode.numberOfElements == 3) {
            MyNode temp = rootNode;

            MyNode newRoot = new MyNode();
            rootNode = newRoot;
            newRoot.isLeaf = false;
            newRoot.numberOfElements = 0;
            newRoot.children[1] = temp;
            breakNode(newRoot, 1, temp);
            System.out.println();
        }
        // else check balance of all linked nodes
        else {
            for (MyNode frch : rootNode.children) {
                if (frch != null && frch.numberOfElements == 3)
                    breakNode(rootNode, 1, frch);
            }
        }


    }


    /**
     * Handles troubles with insertion (node is full).
     * Creates extra node, redistribute all links and keys between two lowest nodes,
     *  then pushes middle key into the node above.
     * @param rtchild - parent for initial node.
     * @param index int - shows where to insert key in new node.
     * @param splitNode - initial node
     */
    private void breakNode (MyNode rtchild, int index, MyNode splitNode){

        // extra node for Triangle Restructuring
        MyNode swap = new MyNode();
        // must has at least one element (will be inserted later)
        swap.numberOfElements = 1;
        swap.isLeaf = splitNode.isLeaf;

        swap.values[1] = splitNode.values[3];

        // if leaf - push mid element into node above and restrict again
        if (!splitNode.isLeaf){
            for (int it = 1; it <= 2; it++) {
                swap.children[it] = splitNode.children[it + 2];
                splitNode.children[it + 2] = null;
            }
        }

        // after all replaces here will be exactly one element
        splitNode.numberOfElements = 1;


        for (int it = rtchild.numberOfElements+1; it >= index+1 ; it--){
            rtchild.children[it+1] = rtchild.children[it];
        }

        // put the link to the new (processed) node
        rtchild.children[index+1] = swap;

        for (int it = rtchild.numberOfElements; it >= index ; it--){
            rtchild.values[it+1] = rtchild.values[it];
        }
        rtchild.values[index] = splitNode.values[2];

        rtchild.numberOfElements++;

    }


    /**
     * Traversal on Tree in order (left -> parent -> right). Recursive.
     *  If we are in leaf node - simply return all key here (from left to right).
     *  Else call this function recursively for all links in the node.
     * @param root
     * @return
     */
    public String inOrderTrav(MyNode root){
        String str = "";
        int i = 1;

        if(!root.isLeaf){
            while (root.children[i] != null && i < root.numberOfElements + 1){
                str += inOrderTrav(root.children[i]) + root.values[i] + " ";
                i++;
            }
            return str + inOrderTrav(root.children[i]);
        }
        else{
            str += root.toString();
            return str;
        }



    }
}