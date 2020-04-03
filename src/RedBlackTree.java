import java.util.ArrayList;
import java.util.List;

public class RedBlackTree<T extends Comparable> {

    public Node<T> root;
    private int size;

    public RedBlackTree() {
        root = Node.getNIL();
        root.setP(Node.getNIL());
        size = 0;
    }

    public int height() {
        return height(root);
    }

    private int height(Node<T> cur) {
        return cur == Node.getNIL() ? -1 : 1 + (Math.max(height(cur.getR()), height(cur.getL())));
    }

    public boolean insertBadly(T value) {
        Node<T> x = root, y = Node.getNIL();
        while (x != Node.getNIL()) {
            if (x.getValue().equals(value)) return false;
            y = x;
            x = value.compareTo(x.getValue()) < 0 ? x.getL() : x.getR();
        }
        size++;
        Node<T> z = new Node<>(value, y);
        if (y == Node.getNIL())
            root = z;
        else if (value.compareTo(y.getValue()) < 0)
            y.setL(z);
        else
            y.setR(z);
        return true;
    }

    public boolean insert(T value) {
        Node<T> x = root, y = Node.getNIL();
        while (x != Node.getNIL()) {
            if (x.getValue().equals(value)) return false;
            y = x;
            x = value.compareTo(x.getValue()) < 0 ? x.getL() : x.getR();
        }
        size++;
        Node<T> z = new Node<>(value, y);
        if (y == Node.getNIL())
            root = z;
        else if (value.compareTo(y.getValue()) < 0)
            y.setL(z);
        else
            y.setR(z);
        insertionFix(z);
        return true;
    }

    private void insertionFix(Node<T> c) {

        while (!c.getP().isBlack()) {

            boolean isURight = (c.getP().getP().getL() == c.getP()), isCRight = (c.getP().getR() == c);

            Node<T> u = isURight ? c.getP().getP().getR() : c.getP().getP().getL();

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

    public boolean search(T value) {
        Node<T> cur = root;
        while (cur != Node.getNIL() && !(cur.getValue().equals(value))) {
            cur = (value.compareTo(cur.getValue()) < 0) ? cur.getL() : cur.getR();
        }
        return cur != Node.getNIL();
    }


    public List<T> inOrderList() {
        List<T> ret = new ArrayList<>();
        printInOrder(root, ret);
        return ret;
    }

    private void printInOrder(Node<T> cur, List<T> ret) {
        if (cur == Node.getNIL()) return;
        printInOrder(cur.getL(), ret);
        ret.add(cur.getValue());
        printInOrder(cur.getR(), ret);
    }

    public void leftRotate(Node<T> p) {
        if (p.getR() == Node.getNIL()) return;
        Node<T> c = p.getR();

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

    public void rightRotate(Node<T> p) {
        if (p.getL() == Node.getNIL()) return;
        Node<T> c = p.getL();
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
