import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CasaVolta extends Casa{
    public CasaVolta(int indiceCasa){
        super(indiceCasa);
    }
    @Override
    public void aplicarEfeito(Jogador jogador, List<Jogador> jogadores){
            Scanner s = new Scanner(System.in);
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
                int escolha = s.nextInt();
                Jogador escolhido = outroJogadores.get(escolha - 1);
                escolhido.setPosicao(0);
                System.out.println("O jogador " + escolhido.getCor() + " foi escolhido e esta na posição zero");
    }
}
    // 17 e 27:
    // Se o competidor parar em uma dessas casas, ele escolhe um competidor para
    // voltar para o início do jogo.