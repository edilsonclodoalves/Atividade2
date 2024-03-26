import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AgenteBombeiro2 extends Agent {
    protected void setup() {
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msg = myAgent.receive();
                if (msg != null) {
                    String content = msg.getContent();
                    if (content.equalsIgnoreCase("Fogo")) {
                        System.out.println("O agente " + msg.getSender().getName() +
                                " avisou de um incêndio");
                        System.out.println("Vou ativar os procedimentos de " +
                                "combate ao incêndio!");
                    }
                } else {
                    block();
                }
            }
        });
    }
}
