import java.util.List;


public class CasaMagica extends Casa{
    public CasaMagica(int indiceCasa){
        super(indiceCasa);
    }
    @Override
    public void aplicarEfeito(Jogador jogador, List<Jogador> jogadores){
            
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
            } 
            else {
                System.out.println("Não existem jogadores com posições menores que a do jogador " + jogador.getCor());
            }
    }
}
    // 20 e 35:
    // Casas mágicas: se o competidor parar em uma delas, ele troca de lugar com o
    // jogador que está mais atrás no jogo.
    // Caso ele seja o último, ele não sai do lugar.