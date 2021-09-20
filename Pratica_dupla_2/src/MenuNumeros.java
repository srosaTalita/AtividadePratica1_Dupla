import java.sql.SQLOutput;
import java.util.*;
public class MenuNumeros {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------\n1- Números Pares\n2- Numeros Impares\n3- Numeros Primos\n4- Juros Compostos\n5- Sair\n---------------\nDigite uma opção: ");
        int opcao = sc.nextInt();
        sc.nextLine();
        while(opcao > 5 || opcao < 1){
            System.out.println("Opção Incorreta! Digite novamente: ");
            opcao = sc.nextInt();
            sc.nextLine();
        }
        switch (opcao) {
            case (1):
                System.out.println("Digite um número: ");
                int numPar = sc.nextInt();
                sc.nextLine();
                while(numPar < 0){
                    System.out.println("Número Inválido! Digite novamente: ");
                    numPar = sc.nextInt();
                    sc.nextLine();
                }
                System.out.printf("Números Pares de 1 até %d\n", numPar);
                for (int i = 0; i <= numPar; i++) {
                    if (i % 2 == 0){
                        System.out.println(i);
                    }
                }
                break;
            case (2) :
                System.out.println("Digite um número: ");
                int numImpar = sc.nextInt();
                sc.nextLine();
                while(numImpar < 0){
                    System.out.println("Número Inválido! Digite novamente: ");
                    numImpar = sc.nextInt();
                    sc.nextLine();
                }
                System.out.printf("Números Impares de 1 até %d\n", numImpar);
                for (int i = 0; i <= numImpar; i++) {
                    if (i % 2 != 0){
                        System.out.println(i);
                    }
                }
                break;
            case (3) :
                System.out.println("Digite um número: ");
                int numPrimo = sc.nextInt();
                sc.nextLine();
                while(numPrimo < 0){
                    System.out.println("Número Inválido! Digite novamente: ");
                    numPrimo = sc.nextInt();
                    sc.nextLine();
                }
                System.out.printf("Números Primos de 1 até %d\n", numPrimo);
                for (int i = 0; i <= numPrimo; i++) {
                    boolean isPrimo = true; // booleano: numero primo é verdadeiro
                    if(i == 1 || i == 0){
                        isPrimo = false; // se o numero é 1 ou 0,  primo é falso
                    }
                    for (int j = 2; j < i; j++) {
                        if (i % j == 0) {
                            isPrimo = false; // se o resto da divisão do numero j por i por = 0, ele nao é primo, então é false
                        }
                    }
                    if (isPrimo) { // imprimir os numeros primos após a verificação
                        System.out.println(i);
                    }
                }
                break;
            case (4) :
                System.out.println("Digite um valor: ");
                float investimento = sc.nextInt();
                sc.nextLine();
                while(investimento < 0){
                    System.out.println("Valor Inválido!! Digite novamente: ");
                    investimento = sc.nextInt();
                    sc.nextLine();
                }

                System.out.println("Digite a taxa de juros (Ex: 0,0): ");
                float taxaJuros = sc.nextFloat();
                sc.nextLine();
                while(taxaJuros < 0){
                    System.out.println("Valor Inválido! Digite novamente: ");
                    taxaJuros = sc.nextInt();
                    sc.nextLine();
                }

                System.out.println("Digite a quantidade de meses: ");
                float meses = sc.nextInt();
                sc.nextLine();
                while(meses < 0){
                    System.out.println("Valor Inválido! Digite novamente: ");
                    meses = sc.nextInt();
                    sc.nextLine();
                }

                float investimentoInicial = investimento;

                for (int i = 1; i <= meses; i++) {
                    investimento += (investimento * taxaJuros);
                    System.out.printf("No %dº mês o montante é de %.2f  reais\n", i, investimento);
                }
                System.out.printf("O lucro líquido é de: %.2f", (investimento - investimentoInicial));
                break;

            case (5) :
                System.out.println("Até mais, volte sempre (◠‿・)—☆");
                break;
        }
    }
}
