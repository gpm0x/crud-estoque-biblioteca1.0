package crudestoque;
import java.util.ArrayList;
import java.util.Scanner;

public class crudestoque {

    // =========================
    // CORES ANSI
    // =========================
    public static final String RESET = "\u001B[0m";
    public static final String AZUL_FORTE = "\u001B[34;1m";
    public static final String VERDE_FORTE = "\u001B[32;1m";
    public static final String VERMELHO_FORTE = "\u001B[31;1m";
    public static final String ROXO_FORTE = "\u001B[35;1m";
    public static final String NEGRITO = "\u001B[1m";

    static class Livro {

        int id;
        String titulo;
        String autor;
        int quantidade;
        double preco;

        public Livro(int id, String titulo, String autor, int quantidade, double preco) {
            this.id = id;
            this.titulo = titulo;
            this.autor = autor;
            this.quantidade = quantidade;
            this.preco = preco;
        }
    }

    static ArrayList<Livro> estoque = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int contadorId = 1;

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println(AZUL_FORTE + "\n========= MENU =========" + RESET);
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Listar Livros");
            System.out.println("3 - Buscar por Título");
            System.out.println("4 - Atualizar Livro");
            System.out.println("5 - Remover Livro");
            System.out.println("0 - Sair");
            System.out.print("\nEscolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    listarLivrosTabela();
                    break;
                case 3:
                    buscarPorTitulo();
                    break;
                case 4:
                    atualizarLivro();
                    break;
                case 5:
                    removerLivro();
                    break;
                case 0:
                    System.out.println(VERDE_FORTE + "\nSistema encerrado com sucesso!" + RESET);
                    break;
                default:
                    System.out.println(VERMELHO_FORTE + "Opção inválida!" + RESET);
            }

        } while (opcao != 0);
    }

    // =========================
    // CADASTRO MELHORADO
    // =========================
    public static void cadastrarLivro() {

        System.out.println(ROXO_FORTE + "\n========== CADASTRAR LIVRO ==========" + RESET);

        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();

        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();

        System.out.print("Preço (R$)....: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();

        Livro livro = new Livro(contadorId++, titulo, autor, quantidade, preco);
        estoque.add(livro);

        System.out.println(VERDE_FORTE + "\n✔ Livro cadastrado com sucesso!" + RESET);
    }

    // =========================
    // LISTAGEM EM TABELA
    // =========================
    public static void listarLivrosTabela() {

        System.out.println(AZUL_FORTE + NEGRITO + "\n================ ESTOQUE =================" + RESET
        );

        if (estoque.isEmpty()) {
            System.out.println(VERMELHO_FORTE + "Nenhum livro cadastrado." + RESET);
            return;
        }

        System.out.printf("%-5s %-20s %-20s %-10s %-10s\n",
                "ID", "TÍTULO", "AUTOR", "QTD", "PREÇO");

        System.out.println("---------------------------------------------------------------");

        for (Livro livro : estoque) {
            System.out.printf("%-5d %-20s %-20s %-10d R$ %-8.2f\n",
                    livro.id,
                    livro.titulo,
                    livro.autor,
                    livro.quantidade,
                    livro.preco);
        }
    }

    // =========================
    // BUSCA POR TÍTULO
    // =========================
    public static void buscarPorTitulo() {

        System.out.print("\nDigite parte do título: ");
        String busca = scanner.nextLine().toLowerCase();

        boolean encontrado = false;

        for (Livro livro : estoque) {
            if (livro.titulo.toLowerCase().contains(busca)) {

                if (!encontrado) {
                    System.out.println(AZUL_FORTE + "\n=== RESULTADOS ENCONTRADOS ===" + RESET);
                }

                System.out.printf("%-5d %-20s %-20s %-10d R$ %-8.2f\n",
                        livro.id,
                        livro.titulo,
                        livro.autor,
                        livro.quantidade,
                        livro.preco);

                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println(VERMELHO_FORTE + "Nenhum livro encontrado com esse título." + RESET);
        }
    }

    // =========================
    // ATUALIZAR
    // =========================
    public static void atualizarLivro() {

        System.out.print("\nDigite o ID do livro: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Livro livro : estoque) {
            if (livro.id == id) {

                System.out.print("Novo Título: ");
                livro.titulo = scanner.nextLine();

                System.out.print("Novo Autor: ");
                livro.autor = scanner.nextLine();

                System.out.print("Nova Quantidade: ");
                livro.quantidade = scanner.nextInt();

                System.out.print("Novo Preço: ");
                livro.preco = scanner.nextDouble();
                scanner.nextLine();

                System.out.println(VERDE_FORTE + "Livro atualizado com sucesso!" + RESET);
                return;
            }
        }

        System.out.println(VERMELHO_FORTE + "Livro não encontrado." + RESET);
    }

    // =========================
    // REMOVER
    // =========================
    public static void removerLivro() {

        System.out.print("\nDigite o ID do livro: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Livro livro : estoque) {
            if (livro.id == id) {
                estoque.remove(livro);
                System.out.println(VERDE_FORTE + "Livro removido com sucesso!" + RESET);
                return;
            }
        }

        System.out.println(VERMELHO_FORTE + "Livro não encontrado." + RESET);
    }
}
