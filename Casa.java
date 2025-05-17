
import java.util.List;
    
public abstract class Casa 
{
    protected int indiceCasa;
    public Casa (int indiceCasa){
        this.indiceCasa = indiceCasa;
    }
    public abstract void aplicarEfeito(Jogador JogadorAtual, List<Jogador> jogadores);
    
    public int getIndiceCasa() {
        return indiceCasa;
    }
    public void setIndiceCasa(int indiceCasa){
        this.indiceCasa = indiceCasa;
    }
}
