import java.util.ArrayList;
import java.util.List;

public class RedBlackTree {

    private Node root;
    private int size;
    public RedBlackTree() {
        root = Node.getNIL();
        root.setP(Node.getNIL());
        size = 0;
    }

    public Node getRoot() {
        return root;
    }

    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(Node cur) {
        return cur == Node.getNIL() ? -1 : 1 + (Math.max(getHeight(cur.getR()), getHeight(cur.getL())));
    }


    public boolean insert(String value) {
        Node x = root, y = Node.getNIL();
        while (x != Node.getNIL()) {
            if (x.getValue().compareToIgnoreCase(value) == 0) return false;
            y = x;
            x = value.compareToIgnoreCase(x.getValue()) < 0 ? x.getL() : x.getR();
        }
        size++;
        Node z = new Node(value, y);
        if (y == Node.getNIL())
            root = z;
        else if (value.compareToIgnoreCase(y.getValue().toLowerCase()) < 0)
            y.setL(z);
        else
            y.setR(z);
        insertionFix(z);
        return true;
    }

    private void insertionFix(Node c) {

        while (!c.getP().isBlack()) {

            boolean isURight = (c.getP().getP().getL() == c.getP()), isCRight = (c.getP().getR() == c);

            Node u = isURight ? c.getP().getP().getR() : c.getP().getP().getL();

            if (!u.isBlack()) {
                c.getP().setBlack(true);
                u.setBlack(true);
                c.getP().getP().setBlack(false);
                c = c.getP().getP();
            } else {
                if (isCRight == isURight) {
                    c = c.getP();
                    if (isURight) leftRotate(c);
                    else rightRotate(c);
                }

                c.getP().setBlack(true);
                c.getP().getP().setBlack(false);

                if (isURight) rightRotate(c.getP().getP());
                else leftRotate(c.getP().getP());

            }
        }
        root.setBlack(true);
    }

    public boolean search(String value) {
        Node cur = root;
        while (cur != Node.getNIL() && !(cur.getValue().compareToIgnoreCase(value) == 0)) {
            cur = (value.compareToIgnoreCase(cur.getValue()) < 0) ? cur.getL() : cur.getR();
        }
        return cur != Node.getNIL();
    }


    public List<String> inOrderList() {
        List<String> ret = new ArrayList<>();
        printInOrder(root, ret);
        return ret;
    }


    private void printInOrder(Node cur, List<String> ret) {
        if (cur == Node.getNIL()) return;
        printInOrder(cur.getL(), ret);
        ret.add(cur.getValue());
        printInOrder(cur.getR(), ret);
    }

    public void leftRotate(Node p) {
        if (p.getR() == Node.getNIL()) return;
        Node c = p.getR();

        p.setR(c.getL());
        c.setP(p.getP());

        if (c.getL() != Node.getNIL())
            c.getL().setP(p);

        if (p.getP() == Node.getNIL()) {
            this.root = c;
        } else if (p.getP().getL() == p) {
            p.getP().setL(c);
        } else p.getP().setR(c);

        c.setL(p);
        p.setP(c);
    }

    public void rightRotate(Node p) {
        if (p.getL() == Node.getNIL()) return;
        Node c = p.getL();
        p.setL(c.getR());
        if (c.getR() != Node.getNIL())
            c.getR().setP(p);

        if (p.getP() == Node.getNIL())
            root = c;
        else if (p.getP().getL() == p)
            p.getP().setL(c);
        else p.getP().setR(c);
        c.setP(p.getP());

        c.setR(p);
        p.setP(c);
    }

    public int getSize() {
        return size;
    }
}
