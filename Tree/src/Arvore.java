import java.util.*;

public class Arvore {

    static No raiz;

    public Arvore() {
        this.raiz = null;
    }

    public No getRaiz() {
        return raiz;
    }

    public static void setRaiz(No raiz) {
        Arvore.raiz = raiz;
    }

    //-------------------------Método de Adicionar----------------------------//
//    public void add(int data) {
//        No novo = new No(data);
//
//        if (raiz == null) {
//            this.raiz = novo;
//        } else {
//            No atual = this.raiz;
//            while (true) {
//                if (novo.getData() < atual.getData()) {//se for menor
//                    if (atual.getLeft() != null) {
//                        atual = atual.getLeft();
//                    } else {
//                        atual.setLeft(novo);
//                        break;
//                    }
//                } else { //se for maior ou igual
//                    if (atual.getRight() != null) {
//                        //pega o nó a direita do no atual
//                        atual = atual.getRight();
//                    } else {
//                        atual.setRight(novo);
//                        break;
//                    }
//                }
//            }
//        }
//    }
    
    public void add(int data) {
            No novo = new No(data);
            No atual = this.raiz;

            if (raiz == null) {
                this.raiz = novo;
            } else {
                while (true) {
                    if (novo.getData() < atual.getData()) { //se for menor

                        if (atual.getLeft() != null) {
                            atual = atual.getLeft();
                        } else {
                            novo.setPai(atual);
                            atual.setLeft(novo);
                            break;
                        }
                    } else {
                        if (atual.getRight() != null) {
                            atual = atual.getRight();
                        } else {
                            novo.setPai(atual);
                            atual.setRight(novo);
                            break;
                        }
                    }
                }
            }
    }

    //-------------------------Método de Encontrar----------------------------//
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

    //------------------------Métodos de Percurso-----------------------------//
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

    //-----------------Busca em profundidade usando Pilha---------------------//
    public boolean buscaPP(int data) {

        Stack<No> stack = new Stack();
        stack.push(raiz);

        while (!stack.isEmpty()) {
            No folha = stack.pop();
            if (folha.getData() == data) {
                System.out.println("Achamos");
                return true;
            }
            if (folha.getRight() != null) {
                stack.push(folha.getRight());
            }
            if (folha.getLeft() != null) {
                stack.push(folha.getLeft());
            }
        }
        return false;
    }
    
    //-----------------Percurso em extensão com fila--------------------------//
    public void percursoEF(){     
        Queue<No> queue = new LinkedList<No>();
        
        if(raiz != null){
            
            queue.add(raiz);
            
            while(!queue.isEmpty()){
                No atual = queue.remove();
                System.out.println(atual.getData());
                
                if(atual.getLeft() != null){
                    queue.add(atual.getLeft());
                }
                if(atual.getRight() != null){
                    queue.add(atual.getRight());
                }
            }
        }
    }
    
    //----------------------------Nível e Grau--------------------------------//
    public int nivel(No e) {
        No n = (No) e;
        if (n == null) {
            return -1;
        } else {           
            return 1 + Math.max(                    
                    nivel(n.getLeft()),
                    nivel(n.getRight()));
        }
    }
    
    public int grau(No e){
        int grau = 0;
        while(true){
            if(e.getLeft() != null){
                grau += 1;
            }
            if (e.getRight() != null) {
                grau += 1;
            }
            return grau;
        }
    }
    
    public void ng() {
        Queue<No> queue = new LinkedList<No>();

        if (raiz != null) {

            queue.add(raiz);

            while (!queue.isEmpty()) {
                No atual = queue.remove();
                System.out.println("Nó: " + atual.getData() + "|" +
                                   "Nível: " + nivel(atual) + "|" + 
                                   "Grau: " + grau(atual));

                if (atual.getLeft() != null) {
                    queue.add(atual.getLeft());
                }
                if (atual.getRight() != null) {
                    queue.add(atual.getRight());
                }
            }
        }
    }
    
    //------------------------Antecessor e Sucessor---------------------------//
    public No antecessor(No atual) {
            No antecessor = atual;

            antecessor = antecessor.getLeft();

            while (antecessor.getRight() != null) {
                antecessor = antecessor.getRight();
            }

            return antecessor;
        }
    
    public No sucessor(No atual) {
        No sucessor = atual;

        sucessor = sucessor.getRight();

        while (sucessor.getLeft() != null) {
            sucessor = sucessor.getLeft();
        }

        return sucessor;
    }
    //--------------------------Método de Remoção-----------------------------//
    public No findHeir(No atual) {
        No herdeiro = atual.getRight();
        herdeiro.setPai(atual);

        while (herdeiro.getLeft() != null) {

            No aux = herdeiro;
            herdeiro = herdeiro.getLeft();
            herdeiro.setPai(aux);
        }
        if (herdeiro != atual.getRight()) {
            herdeiro.getPai().setLeft(herdeiro.getRight());
            herdeiro.setRight(atual.getRight());
        }
        return herdeiro;
    }

    public boolean remove(int descarte) {

        No atual = find(descarte);

        //------------Caso não exista---------//
        if (atual == null) {
            return false;
        } else {

            //------------Caso tenha 2 filhos---------//
            if (atual.getRight() != null && atual.getLeft() != null) {

                No herdeiro = findHeir(atual);
                //No caso do herdeiro ser o menor dos maiores              
                herdeiro.setLeft(atual.getLeft());
                //Herdeiro herda a subarvore a esquerda do nó que vai ser morto

                if (atual.getPai() != null) {//Se tem pai
                    if (atual.getData() >= atual.getPai().getData()) //O nó a ser removido é filho na direita do pai?
                    {
                        atual.getPai().setRight(herdeiro);
                    } else { //Ou da esquerda?
                        atual.getPai().setLeft(herdeiro);
                    }
                } else {//Se não tem pai
                    this.raiz = herdeiro;
                }
            } //------------Caso tenha 1 filho----------//
            else if (atual.getRight() != null || atual.getLeft() != null) {

                if (atual.getRight() != null) {
                    //Se o nó a ser removido tem filho na direita
                    if (atual.getPai() != null) {
                        //Se tem pai                   
                        if (atual.getData() >= atual.getPai().getData()) {
                            //O nó a ser removido é filho na direita do pai?
                            atual.getPai().setRight(atual.getRight());
                        } else { //Ou da esquerda?
                            atual.getPai().setLeft(atual.getRight());
                        }
                    } else { //Não tem pai
                        this.raiz = atual.getRight();
                    }
                } else {//Se o nó a ser removido tem filho na esquerda
                    if (atual.getPai() != null) {
                        //Se tem pai 
                        if (atual.getData() >= atual.getPai().getData()) {
                            //O nó a ser removido é filho na direita do pai?
                            atual.getPai().setRight(atual.getLeft());
                        } else { //Ou da esquerda?
                            atual.getPai().setLeft(atual.getLeft());
                        }
                    } else { //Não tem pai
                        this.raiz = atual.getLeft();
                    }
                }
            } //------------Caso não tenha filhos----------//
            else {

                if (atual.getData() >= atual.getPai().getData()) {
                    //Descobrir se o nó a ser eliminado ta na direita
                    atual.getPai().setRight(null);
                    // Pai seta direita null
                } else { //Ou na esquerda?
                    atual.getPai().setLeft(null);
                }
            }
            return true;
        }
    }  
    
    //------------------------------------------------------------------------//
    public No maiorNo(No atual){
        if (atual.getRight() == null) {
            return atual;
        }
        else{
            return maiorNo(atual.getRight());
        }
    }
    
    //------------------------------------------------------------------------//
    public No menorNo(No atual){
        if (atual.getLeft() == null) {
            return atual;
        }
        else{
            return menorNo(atual.getLeft());
        }
    }
    
    //------------------------------------------------------------------------//
    public int media(){
        Queue<No> queue = new LinkedList<No>();
        int media, soma = 0;
        
        if(raiz != null){
            
            queue.add(raiz);
            
            while(!queue.isEmpty()){
                No atual = queue.remove();
                soma += atual.getData();
                
                if(atual.getLeft() != null){
                    queue.add(atual.getLeft());
                }
                if(atual.getRight() != null){
                    queue.add(atual.getRight());
                }
            }
        }
        media = soma/contarNos(raiz);
        return media;
    }
    
    //------------------------------------------------------------------------//
    public int soma(){
        Queue<No> queue = new LinkedList<No>();
        int soma = 0;
        
        if(raiz != null){
            
            queue.add(raiz);
            
            while(!queue.isEmpty()){
                No atual = queue.remove();
                soma += atual.getData();
                
                if(atual.getLeft() != null){
                    queue.add(atual.getLeft());
                }
                if(atual.getRight() != null){
                    queue.add(atual.getRight());
                }
            }
        }
        return soma;
    }
    
    //------------------------------------------------------------------------//
    public int multiplos3(){
        Queue<No> queue = new LinkedList<No>();
        int count = 0;
        
        if(raiz != null){
            
            queue.add(raiz);
            
            while(!queue.isEmpty()){
                No atual = queue.remove();
                if(atual.getData() % 3 == 0){
                    count += 1;
                }
                if(atual.getLeft() != null){
                    queue.add(atual.getLeft());
                }
                if(atual.getRight() != null){
                    queue.add(atual.getRight());
                }
            }
        }
        return count;
    }
    
    //------------------------------------------------------------------------//
    public int contarNos(No atual) {
        if (atual == null) {
            return 0;
        } else {
            return (1 + contarNos(atual.getLeft()) + contarNos(atual.getRight()));
        }
    } 
    
    //------------------------------------------------------------------------//
    public boolean estritamenteBin(){
        Queue<No> queue = new LinkedList<No>();
        
        if(raiz != null){
            
            queue.add(raiz);
            
            while(!queue.isEmpty()){
                No atual = queue.remove();
                if(grau(atual) != 0 && grau(atual) != 2){
                    System.out.println("Árvore não é estritamente binária");
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
        System.out.println("Árvore é estritamente binária");
        return true;
    }
    
    //------------------------------------------------------------------------//
    public void removeFolhas(No atual) {
        if (atual != null) {
            if(grau(atual) == 0){
                remove(atual.getData());
            }
            removeFolhas(atual.getLeft());
            removeFolhas(atual.getRight());
        }
    }
    
    //-----------------------------------------------------------------------------//
    public void preOrdem() {
        No atual = raiz;
        Stack stack = new Stack();
        if (atual != null) {
             stack.push(atual);
             while (!stack.isEmpty()) {
                 atual = (No) stack.pop();
                 System.out.println(atual.getData());
                 if (atual.getRight() != null)
                      stack.push(atual.getRight());
                 if (atual.getLeft()  != null) 
                        // filho da esquerda empilhado
                        // após o da direita
                      stack.push(atual.getLeft());
                        // para estar no topo da pilha;
             }
        }
    }
    
    public void emOrdem() {
        No atual = raiz;
        Stack stack = new Stack();
        while (atual != null) {
            while (atual != null) {       // empilhar filho da direita (se houver)
                if (atual.getRight() != null) // e o próprio nó quando for
                {
                    stack.push(atual.getRight()); // para a esquerda;
                }
                stack.push(atual);
                atual = atual.getLeft();
            }
            atual = (No) stack.pop();   // pop em nó sem filho esquerdo
            while (!stack.isEmpty() && atual.getRight() == null) {
                // visita-lo e a todos os nós
                System.out.println(atual.getData());                 // sem filho direito;
                atual = (No) stack.pop();
            }
            System.out.println(atual.getData());                  // visitar também o primeiro nó com
            if (!stack.isEmpty()) // um filho direito (se houver);
            {
                atual = (No) stack.pop();
            } else {
                atual = null;
            }
        }
    }
    
    public void posOrdem() {
        No atual = raiz, aux = raiz;
        Stack stack = new Stack();
        while (atual != null) {
            for (; atual.getLeft() != null; atual = atual.getLeft()) {
                stack.push(atual);
            }
            while (atual != null && (atual.getRight() == null
                    || atual.getRight() == aux)) {
                System.out.println(atual.getData());;
                aux = atual;
                if (stack.isEmpty()) {
                    return;
                }
                atual = (No) stack.pop();
            }
            stack.push(atual);
            atual = atual.getRight();
        }
    }
    
    //------------------------------------------------------------------------//
    public void copyTree() {
        Queue<No> queue = new LinkedList<No>();

        if (raiz != null) {

            queue.add(raiz);

            while (!queue.isEmpty()) {
                No aux = raiz;
                No atual = queue.remove();
                Arvore copia = new Arvore();

                if (atual.getLeft() != null) {
                    queue.add(atual.getLeft());
                }
                if (atual.getRight() != null) {
                    queue.add(atual.getRight());
                }

                if (copia == null) {
                    copia.setRaiz(atual);
                } else {

                    if (atual.getData() < aux.getData()) {//se for menor
                        if (aux.getLeft() != null) {
                            aux = aux.getLeft();
                        } 
                        else {
                            aux.setLeft(atual);
                            break;
                        }
                    }
                    else { //se for maior ou igual
                        if (atual.getRight() != null) {
                        //pega o nó a direita do no atual
                            aux = aux.getRight();
                        } 
                        else {
                            aux.setRight(atual);
                            break;
                        }
                    } 
                    
                }               
                
            }
        }
    }
        
    //------------------------------------------------------------------------//    

    public static void main(String[] args) {
        Arvore arvore = new Arvore();
        arvore.add(13);
        arvore.add(10);
        arvore.add(25);
        arvore.add(2);
        arvore.add(12);
        arvore.add(20);
        arvore.add(31);
        arvore.add(29);
        
//        System.out.println("Pré Ordem");  
//        arvore.preOrdem();
        
//        System.out.println("Em ordem: ");
//        arvore.emOrdem();
//        
//        System.out.println("Pós ordem: ");
//        arvore.posOrdem();
        

//        System.out.println("Números de Nós: " + arvore.contarNos(raiz));
//        arvore.removeFolhas(raiz);
//        System.out.println("Números de Nós: " + arvore.contarNos(raiz));
        
//        System.out.println("Maior nó da árvore: " + arvore.maiorNo(raiz).getData());;
//        System.out.println("Menor nó da árvore: " + arvore.menorNo(raiz).getData());
//        System.out.println("Média: " + arvore.media());
//        System.out.println("Soma: " + arvore.soma());
//        System.out.println("Multiplos de 3: " + arvore.multiplos3());
//        System.out.println("Números de Nós: " + arvore.contarNos(raiz));
//        arvore.estritamenteBin();

//        arvore.buscaPP(20);
//        arvore.buscaPP(21);
//        
//        System.out.println("Em Ordem: ");
//        arvore.emOrdemR(arvore.getRaiz());
//        
//        System.out.println("Percurso: ");
//        arvore.percursoEF();
//        
//        System.out.println("Pré Ordem Recursivo: ");
//        arvore.preOrdemR(arvore.getRaiz());
//
//        System.out.println("Em Ordem: ");
//        arvore.emOrdem(arvore.getRaiz());
//
//        System.out.println("Pós Ordem Recursivo: ");
//        arvore.posOrdemR(arvore.getRaiz());
//
//        System.out.println("---------");
//
//        arvore.remove(12);
//
//        System.out.println("Em Ordem: ");
//        arvore.emOrdem(arvore.getRaiz());



    }

}


