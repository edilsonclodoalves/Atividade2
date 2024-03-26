import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AgenteContador extends Agent {
    protected void setup() {
        System.out.println("Agente Contador inicializado.\nAguardando informações...");

        addBehaviour(new CyclicBehaviour(this) {
            // início do comportamento
            Musicos[] musicos = new Musicos[5]; // vetor da classe Musicos
            int cont = 0;

            public void action() {
                ACLMessage msg = receive(); // captura nova mensagem
                if (msg != null) { // se existe mensagem
                    try { // extrai o objeto
                        musicos[cont] = (Musicos) msg.getContentObject();
                        // imprime as informações do objeto
                        musicos[cont].Imprimir();
                        cont++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    block(); // aguarda nova mensagem
                }
            }
        }); // término do comportamento
    }
}
