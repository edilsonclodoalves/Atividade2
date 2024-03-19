import jade.core.Agent;
import jade.core.AID;

public class CompradorDeLivros extends Agent {
    private String livrosComprar;
    protected void setup() {
// imprime mensagem de Bem Vindo
        System.out.println("Olá!!! Eu sou o Agente Comprador " + getLocalName()
                + " e estou pronto para comprar!");
// captura o título do livro que comprará, que foi passado como argumento de inicialização
        java.lang.Object[] args = getArguments();
        if (args != null && args.length > 0) {
            livrosComprar = (String) args[0];
            System.out.println("Pretendo comprar o livro: " + livrosComprar);
        } else {
            // finalizao agente
            System.out.println("Nao tenho livros para comprar!");
            doDelete(); // invoca a execução do método takeDown ()
        }
    }
    protected void takeDown() {
        System.out.println("Agente Comprador" + getAID().getName() + " está finalizado");
    }
}