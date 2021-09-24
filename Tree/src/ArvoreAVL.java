import java.util.LinkedList;
import java.util.Queue;

public class ArvoreAVL {
    
   static No raiz;

    public ArvoreAVL() {
        this.raiz = null;
    }

    public No getRaiz() {
        return raiz;
    }
    
//--------------------------------Adição em AVL-------------------------------//
    public void add(int data){
        No novo = new No(data);
        No atual = this.raiz;
        
        if (raiz == null){
            this.raiz = novo;
        }
        else{        
            while(true){
                if(novo.getData() < atual.getData()){ //se for menor

                    if(atual.getLeft() != null){
                        atual = atual.getLeft();
                    }
                    else{
                        novo.setPai(atual);
                        atual.setLeft(novo);
                        break;
                    }
                }
                else{
                    if(atual.getRight() != null){
                        atual = atual.getRight();
                    }
                    else{
                        novo.setPai(atual);
                        atual.setRight(novo);
                        break;
                    }
                }
            }
        }
//        balance(atual);
    }
    
//-----------------------------Método de Encontrar----------------------------//
    public No find(int key) {
        No atual = raiz;

        while (atual.getData() != key) {

            No aux = atual;

            if (key < atual.getData()) {// vai pra esquerda?
                atual = atual.getLeft();
            } else { // ou pra direita?         
                atual = atual.getRight();
            }

            if (atual == null) { // se nao tem filho          
                return null; // então não encontrou
            }

            atual.setPai(aux);
        }

        return atual;
    }
    
//-------------------Métodos de Rotação e Balanceamento-----------------------//
    private void replace(No a, No b) { //c substituto de n
        No paiA = find(a.getData());
        a.setPai(paiA.getPai());
        if (a == raiz) {
            raiz = b;
        } else {
            if (a == a.getPai().getLeft()) {
                a.getPai().setLeft(b);
            } else {
                a.getPai().setRight(b);
            }
        }
    }

    private void leftRotation(No a) {
        No b = a.getRight();
        //O pai de a, se houver, passa a ser o pai de b
        replace(a, b);
        b.setPai(a.getPai());
        //b se torna pai de a e c
        a.setPai(b);
        //o filho esquerdo de b (T2) passa a ser o filho direito de a
        a.setRight(b.getLeft());
        if (b.getLeft() != null) {
            b.getLeft().setPai(a);
        }
        b.setLeft(a);
    }

    private void rightRotation(No c) {
        No b = c.getLeft();
        //O pai de a, se houver, passa a ser o pai de b
        replace(c, b);
        b.setPai(c.getPai());
        //b se torna pai de a e c
        c.setPai(b);
        //o filho esquerdo de b (T2) passa a ser o filho direito de a
        c.setLeft(b.getRight());
        if (b.getRight() != null) {
            b.getLeft().setPai(c);
        }
        b.setLeft(c);
    }
    

    
    public void lrRotation(No n){
        if (height(n.getLeft().getRight())
             > height(n.getLeft().getLeft())) {

            leftRotation(n.getLeft());//left right rotation
            }
            rightRotation(n);
    }
    
    public void rlRotation(No n){
        if (height(n.getRight().getLeft())
                > height(n.getRight().getRight())) {

            rightRotation(n.getRight()); //right left rotation
        }
        leftRotation(n);
    }

    public int height(No e) {
        No n = (No) e;
        if (n == null) {
            return -1;
        } else {
            return 1 + Math.max(height(n.getLeft()),
                    height(n.getRight()));
        }
    }

    private boolean isBalanced(No n) {
        int esq = height(n.getLeft()) + 1;
        int dir = height(n.getRight()) + 1;
        int dif = esq - dir;
        return (Math.abs(dif) >= 2) ? false : true;
    }
    
    private int fatorBalanceamento (No atual) {
        return height( atual.getLeft() ) - height( atual.getRight() );
    }

    private void balance(No e) {
        if (e != null) {
            No n = (No) e;
            if (fatorBalanceamento(n) == 2 ) {
                //left unbalanced
                if (fatorBalanceamento(n.getLeft()) > 0) {
                    rightRotation(n);
                } 
                else {
                    lrRotation(n);
                }
            }
            else if (fatorBalanceamento(n) == -2) {
                //right unbalanced
                if (fatorBalanceamento(n.getRight()) < 0) {
                    leftRotation(n);
                } 
                else {
                     rlRotation(n);
                }
            }
        balance(n.getPai());
        }
    }
    
    private void balanceAula(No e) {
        if (e != null) {
            No n = (No) e;
            if (!isBalanced(n)) {
            //left unbalanced
                if (height(n.getLeft()) > height(n.getRight())) {
                    if (height(n.getLeft().getRight())
                            > height(n.getLeft().getLeft())) {
                        leftRotation(n.getLeft());//left right rotation
                    }
                    rightRotation(n);
                } 
                else {//right unbalanced
                    if (height(n.getRight().getLeft())
                            > height(n.getRight().getRight())) {
                        rightRotation(n.getRight()); //right left rotation
                    }
                    leftRotation(n);
                }
            }
            balance(n.getPai());
        }
        preOrdemR(raiz);
    }
    
    private void balanceArvore(){
        Queue<No> queue = new LinkedList<No>();
        
        if(raiz != null){
            
            queue.add(raiz);
            
            while(!queue.isEmpty()){
                No atual = queue.remove();
                balance(atual);
                                   
                if(atual.getLeft() != null){
                    queue.add(atual.getLeft());
                }
                if(atual.getRight() != null){
                    queue.add(atual.getRight());
                }
            }
        }
    }
    
//--------------------------------Questão 17----------------------------------//
    public void arvoreIsBalanced(){     
        Queue<No> queue = new LinkedList<No>();
        
        if(raiz != null){
            
            queue.add(raiz);
            
            while(!queue.isEmpty()){
                No atual = queue.remove();
                int esq = height(atual.getLeft()) + 1;

                int dir = height(atual.getRight()) + 1;
                int dif = esq - dir;
                    
                if (Math.abs(dif) >= 2) {
                    System.out.println(atual.getData());
                    System.out.println("Árvore não é AVL");                  
                }
                                   
                if(atual.getLeft() != null){
                    queue.add(atual.getLeft());
                }
                if(atual.getRight() != null){
                    queue.add(atual.getRight());
                }
            }
        }
        System.out.println("Árvore é AVL");
    }
    
    public boolean arvoreAVL(){     
        Queue<No> queue = new LinkedList<No>();
        
        if(raiz != null){
            
            queue.add(raiz);
            
            while(!queue.isEmpty()){
                No atual = queue.remove();
                int esq = height(atual.getLeft()) + 1;
                int dir = height(atual.getRight()) + 1;
                int dif = esq - dir;
                    
                if (Math.abs(dif) >= 2) {
                    System.out.println("Árvore não é AVL");
                    
                    return false;
                }
                                   
                if(atual.getLeft() != null){
                    queue.add(atual.getLeft());
                }
                if(atual.getRight() != null){
                    queue.add(atual.getRight());
                }
            }
        }
        System.out.println("Árvore é AVL");
        return true;
    }
    
    public int contarNos(No atual) {
        if (atual == null) {
            return 0;
        } else {    
            return (1 + contarNos(atual.getLeft()) + contarNos(atual.getRight()));
        }
    }

//--------------------------------Percursos-----------------------------------//
    public void preOrdemR(No atual) {
        if (atual != null) {
            System.out.println(atual.getData());
            preOrdemR(atual.getLeft());
            preOrdemR(atual.getRight());

        }
    }

    public void emOrdemR(No atual) {
        if (atual != null) {
            emOrdemR(atual.getLeft());
            System.out.println(atual.getData());
            emOrdemR(atual.getRight());
        }
    }

    public void posOrdemR(No atual) {
        if (atual != null) {
            posOrdemR(atual.getLeft());
            posOrdemR(atual.getRight());
            System.out.println(atual.getData());
        }
    }
    
    public void displayTree() {
        if (raiz == null) {
            System.out.println("Árvore vazia!");
            return;
        }
        String separator = String.valueOf("  |__");
        System.out.println(this.raiz.getData());
        displaySubTree(raiz.getLeft(), separator);
        displaySubTree(raiz.getRight(), separator);
    }
    
    private void displaySubTree(No node, String separator) {
        if (node != null) {
                No father = node.getPai();
                if (node.equals(father.getLeft()) == true) {
                        System.out.println(separator+node.getData() + "esq");
                }else{
                        System.out.println(separator+node.getData() + "dir");
                }			
                displaySubTree(node.getLeft(),  "     "+separator);
                displaySubTree(node.getRight(), "     "+separator);
        }
    }
//----------------------------------------------------------------------------//  
    public static void main(String[] args) {
        ArvoreAVL arvore = new ArvoreAVL();
        
        arvore.add(9);
        arvore.add(5);
        arvore.add(15);
        arvore.add(4);
        arvore.add(7);
        arvore.add(6);
        arvore.add(8);
        
        arvore.arvoreIsBalanced();
        
//        System.out.println("Pré-Ordem: ");
//        arvore.preOrdemR(raiz);
        
//        arvore.balanceAula(raiz);
        
//        arvore.displayTree();
        
//        System.out.println(arvore.contarNos(raiz));
//        
//        System.out.println("Pré-Ordem: ");;
//        arvore.preOrdemR(raiz);
//        
//        System.out.println("Em-Ordem: ");
//        arvore.emOrdemR(raiz);
//        
//        System.out.println("Pós-Ordem: ");
//        arvore.posOrdemR(raiz);       
        
    }
}
