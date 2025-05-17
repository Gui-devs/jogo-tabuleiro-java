import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import java.util.List;

public class Tabuleiro {
    private ArrayList<Jogador> jogadores; 
    private int[] casas;
    private Random random;
    private List<Jogador>[][] tabuleiroVisual; 
    
    
    // private CasaSorte casaSorte = new CasaSorte(1);


    public Tabuleiro(){
        this.casas = new int[40];
        this.random = new Random();
        this.jogadores = new ArrayList<>(); 
        this.tabuleiroVisual = new List[4][10]; 

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
            if (jogadores.isEmpty()) {
                System.out.println("Adicione jogadores para poder jogar uma nova partida");
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
                    System.out.println("Informe o numero de casas que o jogador " + jogador.getCor() + " deve ir");
                    opc = scanner.nextInt();
                    jogador.avancar(opc);
                      if(jogador.getPosicao() >= 40){
                            System.out.println("O jogador " + jogador.getCor() + " venceu !");
                            return;
                        }
                    casasEspeciais(jogador, jogadores);
                    repetirJogada = false;
                } else {
                    dados = jogador.rolarDados(random);
                    int soma = dados[0] + dados[1];
                    jogador.avancar(soma);
                      if(jogador.getPosicao() >= 40){
                            System.out.println("=============================================");
                            System.out.println("O jogador " + jogador.getCor() + " venceu !");
                            for(int i = 0; i < jogadores.size();  i++){
                                System.out.println("Posição do jogador " + jogadores.get(i).getCor() + ": " +jogadores.get(i).getJogadas());
                            }
                            System.out.println(jogador.getJogadas());
                            System.out.println("=============================================");
                            return;
                        }
                    casasEspeciais(jogador, jogadores);
                    repetirJogada = (dados[0] == dados[1]);
                }
                System.out.println("O jogador " + jogador.getCor()  + " esta na casa " + jogador.getPosicao() );
                if(repetirJogada){
                    System.out.println("O jogador " + jogador.getCor() + " tirou valores iguais e jogará novamente");
                }
            } while (repetirJogada);
        }
    }
    public void casasEspeciais(Jogador jogador, ArrayList<Jogador> jogadores){
        int pos = jogador.getPosicao();
        if(pos == 10 || pos == 25 || pos == 38){
            CasaStop casaStop = new CasaStop(pos);
            casaStop.aplicarEfeito(jogador, jogadores);
        }
        if(pos == 13){
            CasaSurpresa casaSurpresa = new CasaSurpresa(pos);
            casaSurpresa.aplicarEfeito(jogador, jogadores);
        }
        if(pos == 5 || pos == 15 || pos == 30){
            CasaSorte casaSorte = new CasaSorte(pos);
            casaSorte.aplicarEfeito(jogador, jogadores);
        }
        if(pos == 17 || pos == 27){
                CasaVolta casaVolta = new CasaVolta(pos);
                casaVolta.aplicarEfeito(jogador, jogadores);
        }
        if(pos == 20 || pos == 35){
            CasaMagica casaMagica = new CasaMagica(pos);
            casaMagica.aplicarEfeito( jogador, jogadores);
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
