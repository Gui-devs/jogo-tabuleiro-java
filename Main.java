import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Tabuleiro tabuleiro = new Tabuleiro();
        int opc, opcTipo;
        ArrayList<String> coresDisponiveis = new ArrayList<>();
        coresDisponiveis.addAll(Arrays.asList("Vermelho", "Verde", "Azul", "Amarelo", "Preto", "Branco"));
        int opcCor;
        boolean flagAd = true;
        do {
            System.out.println("=============================================");
            if(tabuleiro.getJogadores().size() < 6 && flagAd){
                System.out.println("        1- Adicionar Jogador");
            }        
    System.out.println("        2- Jogar");
    System.out.println("        3- Sair");        
    System.out.println("=============================================");
    opc = scanner.nextInt();
    if (opc == 1) {
        System.out.println("=============================================");
        System.out.println(" - Qual o tipo do jogador ?");
        System.out.println("   1 - Azarado\n   2 - Sortudo\n   3 - Normal ");
        System.out.println("=============================================");
        
        opcTipo = scanner.nextInt();
        scanner.nextLine(); // consumir quebra de linha
        System.out.println("=============================================");
        System.out.println("Qual a cor do jogador ?");
        for (int i=0; i<coresDisponiveis.size(); i++){
            System.out.println((i+1) + " - " + coresDisponiveis.get(i));
        }
        System.out.println("=============================================");
        opcCor = scanner.nextInt();
        String escolhaFeita = coresDisponiveis.get(opcCor - 1);
        coresDisponiveis.remove(opcCor-1);

        Jogador jogador = null;
        switch (opcTipo) {
            case 1:
                jogador = new JogadorAzarado(escolhaFeita);
                break;
            case 2:
                jogador = new JogadorSortudo(escolhaFeita);
                break;
            case 3:
                jogador = new JogadorNormal(escolhaFeita);
                break;
            default:
                System.out.println("Opção de tipo inválida.");
                continue;
        }
        tabuleiro.adicionarJogador(jogador);
    } else if (opc == 2) {
        flagAd = false;
        int opc2;
        if(tabuleiro.inicarJogo()){
        do {
        System.out.println("=============================================");
        System.out.println("1 - Inserir Casa\n2 - Rolar Dados");
        System.out.println("=============================================");
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
    } else {
        String escolhaFeita = tabuleiro.getJogadores().get(5).getCor();
        tabuleiro.getJogadores().remove(tabuleiro.getJogadores().get(5));
        System.out.println("Escolha novos tipos de jogadores!");
        System.out.println("=============================================");
        System.out.println("O jogador de novo tipo substituirá o último jogador escolhido !");
        System.out.println("=============================================");
        System.out.println("=============================================");
        System.out.println(" - Qual o tipo do jogador ?");
        System.out.println("   1 - Azarado\n   2 - Sortudo\n   3 - Normal ");
        System.out.println("=============================================");
        opcTipo = scanner.nextInt();
        scanner.nextLine();
        System.out.println("A cor do jogador de novo tipo é: " + escolhaFeita);
        Jogador jogador = null;
        switch (opcTipo) {
            case 1:
                jogador = new JogadorAzarado(escolhaFeita);
                break;
            case 2:
                jogador = new JogadorSortudo(escolhaFeita);
                break;
            case 3:
                jogador = new JogadorNormal(escolhaFeita);
                break;
            default:
                System.out.println("Opção de tipo inválida.");
                continue;
        }
        tabuleiro.adicionarJogador(jogador);
    }
    } else if (opc != 3) {
        System.out.println("Opção inválida.");
    }

} while (opc != 3);
    scanner.close();

    }
}