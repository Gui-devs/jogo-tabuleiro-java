import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class Tabuleiro {
    private ArrayList<Jogador> jogadores; 
    private int[] casas;
    private Random random;
    private List<Jogador>[][] tabuleiroVisual; 

    public Tabuleiro(){
        this.casas = new int[40];
        this.random = new Random();
        this.jogadores = new ArrayList<>(); 
        this.tabuleiroVisual = new ArrayList[4][10];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                tabuleiroVisual[i][j] = new ArrayList<>();
            }
        }
    }

    public void atualizarTabuleiroVisual() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                tabuleiroVisual[i][j].clear();
            }
        }
        for (Jogador jogador : jogadores) {
            if (jogador.getPosicao() >= 40) {
                jogador.setPosicao(39);
            }
            int linha = jogador.getPosicao() / 10;
            int coluna = jogador.getPosicao() % 10;
            tabuleiroVisual[linha][coluna].add(jogador);
        }
    }

    public void imprimirTabuleiroVisual() {
        System.out.println("\n============= Tabuleiro Visual =============");
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 10; j++) {
                    int numeroCasa = i * 10 + j;
                    System.out.print(numeroCasa + ".[" + tabuleiroVisual[i][j].size() + "]\t"); 
                }
                System.out.println();
            }
        System.out.println("=================================");

        System.out.println("\nLegenda dos Jogadores:");
            for (Jogador jogador : jogadores) {
                System.out.println(jogador.getClass().getSimpleName() + " | " + jogador.getCor() + " | Posição " + jogador.getPosicao());
            }
        System.out.println("=================================\n");

    }
    public boolean adicionarJogador(Jogador jogador){
        if(jogadores.size() < 6){
            jogadores.add(jogador);
            return true;
        }
        return false;
    } 
    Scanner scanner = new Scanner(System.in);
    public void jogarRodada(boolean modoDebug){
        for (Jogador jogador : jogadores) {
            if(jogador.getPosicao() >= 40){
                System.out.println("O jogador " + jogador.getCor() + " venceu !");
                return;
            }
            if(jogador.isPularRodada()){
                jogador.setPularRodada(false);
                System.out.println("O jogador " + jogador.getCor() + " está pulando a rodada");
                continue;
            }
            jogador.setJogadas(jogador.getJogadas() + 1);
            boolean repetirJogada;
            do {
                int[] dados;
                if(modoDebug){
                    int opc;
                    System.out.println("Informe o numero de casas que o jogador " + jogador.getCor() + "deve ir");
                    opc = scanner.nextInt();
                    jogador.avancar(opc);
                    casasEspeciais(jogador);
                    repetirJogada = false;
                } else {
                    dados = jogador.rolarDados(random);
                    int soma = dados[0] + dados[1];
                    jogador.avancar(soma);
                    casasEspeciais(jogador);
                    repetirJogada = (dados[0] == dados[1]);
                }
                System.out.println("O jogador " + jogador.getCor()  + " esta na casa " + jogador.getPosicao() );
                if(repetirJogada){
                    System.out.println("O jogador" + jogador.getCor() + " tirou valores igusis e jogará novamente");
                }
            } while (repetirJogada);
        }
    }
    public void casasEspeciais(Jogador jogador){
        int pos = jogador.getPosicao();
        if(pos == 10 || pos == 25 || pos == 38){
            jogador.setPularRodada(true);
            System.out.println("O jogador" + jogador.getCor() + " caiu na casa " + pos + " e vai pular a rodada");
        }
        if(pos == 13){
            System.out.println("Casa Surpresa !");
            trocarDeTipo(jogador);
        }
        if(pos == 5 || pos == 15 || pos == 30){
            if(!(jogador instanceof JogadorAzarado)){
                jogador.avancar(3);
                System.out.println(" O jogador " + jogador.getCor() +" Caiu na casa da sorte e andou tres casa a frente");
            } else {
                System.out.println("O jogador  " + jogador.getCor() + " é azarado e nao pode andar tres casas a frente");
            }
        }
        if(pos == 17 || pos == 27){
            System.out.println("Escolha um jogador para voltar ao inicio do jogo: ");
            ArrayList<Jogador> outroJogadores = new ArrayList<>();
            for (Jogador jogador2 : jogadores) {
                if(!jogador2.equals(jogador)){
                    outroJogadores.add(jogador2);
                }
            }

                if(outroJogadores.isEmpty()){
                    System.out.println("Nao há outros jogadores para escolher");
                    return;
                }

                for(int i = 0; i < outroJogadores.size(); i++){
                    System.out.println((i+1) + " - " + outroJogadores.get(i).getCor());
                }
                Scanner scanner = new Scanner(System.in);
                int escolha = scanner.nextInt();
                Jogador escolhido = outroJogadores.get(escolha - 1);
                escolhido.setPosicao(0);
                System.out.println("O jogador " + escolhido.getCor() + " foi escolhido e esta na posição zero");
                scanner.close();
            
        }
        if(pos == 20 || pos == 35){
            int menorPos = 40;
            Jogador jogadorMenorPos = null;
            for(Jogador j : jogadores){
                if(j != jogador && j.getPosicao() < menorPos){
                    menorPos = j.getPosicao();
                    jogadorMenorPos = j;
                }
            }
            
            if(jogadorMenorPos != null){
                int temp = jogador.getPosicao();
                jogador.setPosicao(jogadorMenorPos.getPosicao());
                jogadorMenorPos.setPosicao(temp);
                System.out.println("O jogador " + jogador.getCor() + " caiu na casa supresa e trocou de lugar com o jogador " + jogadorMenorPos.getCor());
            } else {
                System.out.println("Não existem jogadores com posições menores que a do jogador " + jogador.getCor());
            }
        }
    }
    public void trocarDeTipo(Jogador jogador){
        int indice = jogadores.indexOf(jogador);
        int sorteio = random.nextInt(3) + 1;
        String cor = jogador.getCor();
        int posicao = jogador.getPosicao();
        Jogador novoJogador = null;
        switch (sorteio) {
            case 1:
                novoJogador = new JogadorNormal(cor);
                System.out.println("Carta sorteada: jogador normal !");
                System.out.println("O jogador " + novoJogador.getCor() + " agora é um jogador normal !");                
                break;
            case 2:
                novoJogador = new JogadorSortudo(cor);
                System.out.println("Carta sorteada: jogador sortudo !");
                System.out.println("O jogador " + novoJogador.getCor() + " agora é um jogador sortudo !");                

                break;
            case 3: 
                novoJogador = new JogadorAzarado(cor);
                System.out.println("Carta sorteada: jogador azarado !");
                System.out.println("O jogador " + novoJogador.getCor() + " agora é um jogador azarado !");                

            default:
                break;
        }   
        if(novoJogador != null){
            novoJogador.setPosicao(posicao);
            jogadores.set(indice, novoJogador);
        }
    }
    public int[] getCasas() {
        return casas;
    }
    public void setCasas(int[] casas) {
        this.casas = casas;
    }
    public Random getRandom() {
        return random;
    }
    public void setRandom(Random random) {
        this.random = random;
    }
    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }
    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }
}
