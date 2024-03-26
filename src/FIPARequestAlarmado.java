import jade.core.Agent;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;
//para implementar o protoclo request importamos a seguinte classe:
import jade.domain.FIPANames;

public class FIPARequestAlarmado extends Agent {
    protected void setup() {
        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            String[] agentNames = ((String) args[0]).split(",");

            System.out.println("Solicitando ajuda a várias centrais de bombeiros...");
            ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
            //montando a mensagem a se enviada posteriormente
            for (int i = 0; i < args.length; i++) {
                msg.addReceiver(new AID((String) args[i], AID.ISLOCALNAME));
            }
            msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
            msg.setContent("Fogo a 5 kms");
            addBehaviour(new Iniciador(this, msg));
        } else {
            System.out.println("Especifique o nome de pelo menos uma central de bombeiros");
        }
    }

    class Iniciador extends AchieveREInitiator {
        public Iniciador(Agent a, ACLMessage msg) {
            super(a, msg);
        }

        protected void handleAgree(ACLMessage agree) {
            System.out.println("Central de bombeiros " + agree.getSender().getName() +
                    " informa que saiu para apagar o fogo");
        }

        protected void handleRefuse(ACLMessage refuse) {
            System.out.println("Central de bombeiros " + refuse.getSender().getName() +
                    " responde que o fogo está muito longe e não pode apagá-lo");
        }

        protected void handleNotUnderstood(ACLMessage notUnderstood) {
            System.out.println("Central de bombeiros " + notUnderstood.getSender().getName() +
                    " por algum motivo não entendeu a solicitação");
        }

        protected void handleFailure(ACLMessage failure) {
            if (failure.getSender().equals(getAMS())) {
                System.out.println("Alguma das centrais de bombeiro não existe");
            } else {
                System.out.println("Falha na central de bombeiros " + failure.getSender().getName() +
                        ": " + failure.getContent().substring(1, failure.getContent().length() - 1));
            }
        }

        protected void handleInform(ACLMessage inform) {
            System.out.println("Central de bombeiros" + inform.getSender().getName() +
                    " informa que apagou o fogo");
        }
    }
}
