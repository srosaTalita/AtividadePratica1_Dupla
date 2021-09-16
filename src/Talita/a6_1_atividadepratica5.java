package Talita;

import java.util.Scanner;

public class a6_1_atividadepratica5 {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            boolean invalido = true;
            System.out.println("----------------------------------------------------------");
            System.out.println("------------- CadDev - Ambiente de Cadastro --------------");
            System.out.println("------------------- Seja bem vindo(a)! -------------------");
            System.out.println("----------------------------------------------------------");

            System.out.println("Escolha uma opção de cadastro:");
            System.out.println("---------------\n1- Dev\n2- Linguagem\n3- Sair\n---------------\nDigite uma opção: ");

            int opcao;

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case (1):
                    System.out.println("Opção Escolhida: Cadastrar Dev");
                    System.out.println("Nome: ");
                    String nomeDev = sc.nextLine();
                    while (nomeDev.length() < 3) {
                        System.out.println("Nome precisa ter no mínimo 3 caracteres!\nNome: ");
                        nomeDev = sc.nextLine();
                    }

                    System.out.println("Sobrenome: ");
                    String sobrenomeDev = sc.nextLine();

                    while (sobrenomeDev.length() < 3) {
                        System.out.println("Sobrenome precisa ter no mínimo 3 caracteres!\nSobrenome: ");
                        sobrenomeDev = sc.nextLine();
                    }

                    System.out.println("Nível de senioridade: ");
                    String senioridade = sc.nextLine();

                    System.out.println("Idade: ");
                    int idadeDev = sc.nextInt();
                    sc.nextLine();
                    while (idadeDev < 0) {
                        System.out.println("A idade informada não é válida!\nIdade: ");
                        idadeDev = sc.nextInt();

                    }
                    System.out.printf("%s %s, %s, %d anos\n", nomeDev, sobrenomeDev, senioridade, idadeDev);
                    System.out.println("Dev cadastrado com sucesso! ");
                    break;

                case (2):
                    System.out.println("Opção Escolhida: Cadastrar Linguagem");
                    System.out.println("Nome: ");
                    String nomeLinguagem = sc.nextLine();
                    while (nomeLinguagem.length() < 3) {
                        System.out.println("Linguagem precisa ter no mínimo 3 caracteres!\nNome: ");
                        nomeLinguagem = sc.nextLine();
                    }

                    System.out.println("Descrição da Linguagem: ");
                    String descricao = sc.nextLine();

                    System.out.println("Aplicação da Linguagem: ");
                    String aplicacao = sc.nextLine();

                    System.out.println("---------------\n1- FrontEnd\n2- Backend\n3- Mobile\n---------------\nDigite uma opção: ");
                    int opcaoAplicacao = sc.nextInt();
                    sc.nextLine();

                    while (invalido){
                        if (opcaoAplicacao == 1) {
                            System.out.println("Você escolheu FrontEnd!");
                            invalido = false;
                        }
                        else if (opcaoAplicacao == 2) {
                            System.out.println("Você escolheu Backend!");
                            invalido = false;

                        }
                        else if (opcaoAplicacao == 3) {
                            System.out.println("Você escolheu Mobile!");
                            invalido = false;
                        }
                        else {
                            System.out.println("Escolha uma opção certa!\nOpção: ");
                            opcaoAplicacao = sc.nextInt();
                            sc.nextLine();
                        }
                    }
                    System.out.printf("%s, %s, %s\n", nomeLinguagem, descricao, aplicacao);
                    System.out.println("Linguagem cadastrada com sucesso! ");
                    break;
                case 3 :
                    System.out.println("Volte sempre (◠‿・)—☆");
                    break;
            }
        }
    }


