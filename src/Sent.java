import jade.core.Agent;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class Sent extends Agent {

    protected void setup() {
        // Coloque aqui o código de inicialização do agente
        System.out.println("Agent " + getLocalName() + " started.");
        // Chamada do método para enviar mensagem
        sendMessage();
    }

    private void sendMessage() {
        ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
        msg.setOntology("PC-ontology");
        msg.setLanguage("Jess");
        msg.addReceiver(new AID("pcseller", AID.ISLOCALNAME));
        msg.setContent("(pc-offer (mb 256) (processor celeron) (price ?p))");
        send(msg);
    }
}
