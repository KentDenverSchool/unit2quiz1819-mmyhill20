public class Node {
    private Node point;
    //inside node:
    private String store;//will not work with ints, user must use wrapper class

    public Node(String store, Node point) {
        this.store = store;
        this.point = point;
    }

    public Node(String store) {
        this.store = store;
        this.point = null;//set things to null instead of leaving them empty
    }

    public void changePointer(Node n) {
        point = n;
    }

    public Node getPoint() {
        return point;
    }

    public String getData() {
        return store;
    }
}


