import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.SubscriptionInitiator;

public class Solicitante2 extends Agent {
    protected void setup() {
        // O método setup não mudou significativamente, exceto que Busca()
        // agora é chamado de PedeNotificacao()
        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            String argumento = (String) args[0];
            if (argumento.equalsIgnoreCase("fogo")) {
                ServiceDescription servico = new ServiceDescription();
                servico.setType("apaga fogo");
                PedeNotificacao(servico, "fogo");
            } else if (argumento.equalsIgnoreCase("ladrão")) {
                ServiceDescription servico = new ServiceDescription();
                servico.setType("prende ladrão");
                PedeNotificacao(servico, "ladrão");
            } else if (argumento.equalsIgnoreCase("doente")) {
                ServiceDescription servico = new ServiceDescription();
                servico.setType("salva vidas");
                PedeNotificacao(servico, "doente");
            }
        }

        // Comportamento para receber mensagens que não são do protocolo FIPA-Subscribe
        MessageTemplate filtro = MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_SUBSCRIBE);
        MessageTemplate filtro2 = MessageTemplate.not(filtro);

        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                // Recebe apenas mensagens que passam pelo filtro2
                ACLMessage msg = receive(filtro2);
                if (msg != null) {
                    System.out.println(msg.getSender() + ": " + msg.getContent());
                } else {
                    block();
                }
            }
        });
    }

    protected void PedeNotificacao(final ServiceDescription sd, final String Pedido) {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.addServices(sd);

        // Cria mensagem de subscrição para o DF
        ACLMessage msg = DFService.createSubscriptionMessage(this, getDefaultDF(), dfd, null);

        // Inicia comportamento de subscrição esperando pela notificação do DF
        addBehaviour(new SubscriptionInitiator(this, msg) {
            protected void handleInform(ACLMessage inform) {
                try {
                    DFAgentDescription[] dfds = DFService.decodeNotification(inform.getContent());
                    ACLMessage mensagem = new ACLMessage(ACLMessage.INFORM);
                    mensagem.addReceiver(dfds[0].getName()); // Assume que quer se comunicar com o primeiro agente encontrado
                    mensagem.setContent(Pedido);
                    myAgent.send(mensagem);
                } catch (FIPAException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
