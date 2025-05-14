import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Tabuleiro {
    private ArrayList<Jogador> jogadores; 
    private int[] casas;
    private Random random;
    public Tabuleiro(){
        this.casas = new int[40];
        this.random = new Random();
        this.jogadores = new ArrayList<>(); 
    }
    public boolean adicionarJogador(Jogador jogador){
        if(jogadores.size() < 6){
            jogadores.add(jogador);
            return true;
        }
        return false;
    }
    public void jogarRodada(boolean modoDebug){
        for (Jogador jogador : jogadores) {
            if(jogador.isPularRodada()){
                jogador.setPularRodada(false);
                System.out.println("O jogador " + jogador.getCor() + " está pulando a rodada");
                continue;
            }
            boolean repetirJogada;
            do {
                int[] dados;
                Scanner scanner = new Scanner(System.in);
                if(modoDebug){
                    int opc;
                    System.out.println("Informe o numero de casas que o jogador " + jogador.getCor() + "deve ir");
                    opc = scanner.nextInt();
                    jogador.avancar(opc);
                    repetirJogada = false;
                } else {
                    dados = jogador.getDados(random);
                    int soma = dados[0] + dados[1];
                    jogador.avancar(soma);
                    repetirJogada = (dados[0] == dados[1]);
                }
                System.out.println("O jogador " + " esta na casa " + jogador.getPosicao() );
                if(repetirJogada){
                    System.out.println("O jogador tirou valores igusis e jogará novamente");
                }

                if(jogador.getPosicao() >= 40){
                    System.out.println("O jogador " + jogador.getCor() + " venceu !");
                    scanner.close();
                    return;
                }

            } while (repetirJogada);
        }
    }
    public void casasEspeciais(Jogador jogador){
        int pos = jogador.getPosicao();
        if(pos == 10 || pos == 25 || pos == 38){
            jogador.setPularRodada(true);
            System.out.println("O jogador caiu na casa " + pos + " e vai pular a rodada");
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
                break;
            case 2:
                novoJogador = new JogadorSortudo(cor);
                System.out.println("Carta sorteada: jogador sortudo !");
                break;
            case 3: 
                novoJogador = new JogadorAzarado(cor);
                System.out.println("Carta sorteada: jogador azarado");

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
