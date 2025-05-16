// import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class Validacao {
    public static void main(String[] args) {
            // Validando o Jogador azarado 
        JogadorAzarado azarado = new JogadorAzarado("Red");
        Random random = new Random();
        int resultado[] = azarado.rolarDados(random);
        System.out.println(Arrays.toString(resultado));
            // Provado que funciona pois ficou muito ruim. 
    }
}
