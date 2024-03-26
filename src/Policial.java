import jade.core.Agent;
import jade.core.behaviours.*;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

public class Policial extends Agent {
    protected void setup() {
        // Descrição do Serviço
        ServiceDescription servico = new ServiceDescription();
        // O serviço é prender ladrões
        servico.setType("prende ladrões");
        servico.setName(this.getLocalName());
        registraServico(servico);
        RecebeMensagens("ladrao", "Vou prender o ladrão");
    }

    // Método para registrar serviço
    protected void registraServico(ServiceDescription sd) {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }

    // Método para adicionar um comportamento para receber mensagens
    protected void RecebeMensagens(final String mensagem, final String resp) {
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    if (msg.getContent().equalsIgnoreCase(mensagem)) {
                        ACLMessage reply = msg.createReply();
                        reply.setContent(resp);
                        myAgent.send(reply);
                    }
                } else {
                    block();
                }
            }
        });
    }
}
