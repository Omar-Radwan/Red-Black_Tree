public class Node<T extends Comparable> {

    static private Node NIL  ;
    private T value;
    private Node<T> l;
    private Node<T> r;
    private Node<T> p;
    private boolean black; // true for black and false for red


    public Node() {
        this.black = false; // red node by default
    }

    public Node(T value) {
        this();
        this.value = value;
        this.l = getNIL();
        this.r = getNIL();
    }

    public Node(T value, Node<T> p) {
        this(value);
        this.p = p;
    }

    public static Node getNIL() {
        if (NIL == null) {
            NIL = new Node();
            NIL.setBlack(true);
        }
        return NIL;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getL() {
        return l;
    }

    public void setL(Node<T> l) {
        this.l = l;
    }

    public Node<T> getR() {
        return r;
    }

    public void setR(Node<T> r) {
        this.r = r;
    }

    public Node<T> getP() {
        return p;
    }

    public void setP(Node<T> p) {
        this.p = p;
    }

    public boolean isBlack() {
        return black;
    }

    public void setBlack(boolean black) {
        this.black = black;
    }
}
