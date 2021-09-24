public class No {

    private int data;
    private No left;
    private No right;
    private No pai;

    public No(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.pai = null;
    }

    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public No getLeft() {
        return left;
    }

    public void setLeft(No left) {
        this.left = left;
    }

    public No getRight() {
        return right;
    }

    public void setRight(No right) {
        this.right = right;
    }

}