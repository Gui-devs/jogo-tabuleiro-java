import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Tabuleiro tabuleiro = new Tabuleiro();
        int opc, opcTipo;
        String opcCor;
        do {
    System.out.println("1- Adicionar Jogador");        
    System.out.println("2- Jogar");
    System.out.println("3- Sair");        
    opc = scanner.nextInt();

    if (opc == 1) {
        System.out.println("Qual o tipo do jogador ?");
        System.out.println("1 - Azarado\n2 - Sortudo\n3 - Normal ");
        opcTipo = scanner.nextInt();
        scanner.nextLine(); // consumir quebra de linha
        System.out.println("Qual a cor do jogador ?");
        opcCor = scanner.nextLine();

        Jogador jogador = null;
        switch (opcTipo) {
            case 1:
                jogador = new JogadorAzarado(opcCor);
                break;
            case 2:
                jogador = new JogadorSortudo(opcCor);
                break;
            case 3:
                jogador = new JogadorNormal(opcCor);
                break;
            default:
                System.out.println("Opção de tipo inválida.");
                continue;
        }
        tabuleiro.adicionarJogador(jogador);
    } else if (opc == 2) {
        int opc2;
        do {
        System.out.println("1 - Inserir Casa\n2 - Rolar Dados");
        opc2 = scanner.nextInt();
        if (opc2 == 1) {
            tabuleiro.jogarRodada(true);
            tabuleiro.atualizarTabuleiroVisual();
            tabuleiro.imprimirTabuleiroVisual();
        } else if (opc2 == 2) {
            tabuleiro.jogarRodada(false);
            tabuleiro.atualizarTabuleiroVisual();
            tabuleiro.imprimirTabuleiroVisual();
        }
        } while (opc2 != 2);

    } else if (opc != 3) {
        System.out.println("Opção inválida.");
    }

} while (opc != 3);
    scanner.close();

    }
}