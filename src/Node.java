public class Node {

    static private Node NIL;
    private String value;
    private Node l;
    private Node r;
    private Node p;
    private boolean black; // true for black and false for red


    public Node() {
        this.black = false; // red node by default
    }

    public Node(String value) {
        this();
        this.value = value;
        this.l = getNIL();
        this.r = getNIL();
    }

    public Node(String value, Node p) {
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getL() {
        return l;
    }

    public void setL(Node l) {
        this.l = l;
    }

    public Node getR() {
        return r;
    }

    public void setR(Node r) {
        this.r = r;
    }

    public Node getP() {
        return p;
    }

    public void setP(Node p) {
        this.p = p;
    }

    public boolean isBlack() {
        return black;
    }

    public void setBlack(boolean black) {
        this.black = black;
    }
}
