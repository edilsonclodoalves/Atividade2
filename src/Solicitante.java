import jade.core.Agent;
import jade.core.behaviours.*;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

public class Solicitante extends Agent {
    protected void setup() {
        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            String argumento = (String) args[0];

            if (argumento.equalsIgnoreCase("fogo")) {
                ServiceDescription servico = new ServiceDescription();
                servico.setType("apaga fogo");
                busca(servico, "fogo");
            } else if (argumento.equalsIgnoreCase("ladrao")) {
                ServiceDescription servico = new ServiceDescription();
                servico.setType("prende ladrao");
                busca(servico, "ladrao");
            } else if (argumento.equalsIgnoreCase("doente")) {
                ServiceDescription servico = new ServiceDescription();
                servico.setType("salva vidas");
                busca(servico, "doente");
            }
        }

        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    System.out.println(msg.getSender() + ": " + msg.getContent());
                } else {
                    block();
                }
            }
        });
    }

    protected void busca(final ServiceDescription sd, final String Pedido) {
        addBehaviour(new TickerBehaviour(this, 60000) {
            protected void onTick() {
                DFAgentDescription dfd = new DFAgentDescription();
                dfd.addServices(sd);

                try {
                    DFAgentDescription[] resultado = DFService.search(myAgent, dfd);
                    if (resultado.length != 0) {
                        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                        msg.addReceiver(resultado[0].getName());
                        msg.setContent(Pedido);
                        myAgent.send(msg);
                        stop(); // finaliza comportamento
                    }
                } catch (FIPAException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
