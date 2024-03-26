import jade.core.Agent;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;

public class Registro extends Agent {
    protected void setup() {
        // Criamos uma entrada no DF
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID()); // Informamos a AID do agente

        // Vamos criar um serviço
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Tipo"); // Tipo do Serviço
        sd.setName("Serviço1"); // Nome do Serviço
        // adicionamos o Serviço 1
        dfd.addServices(sd);

        // Vamos criar outro serviço
        sd = new ServiceDescription();
        sd.setType("Tipo de Serviço");
        sd.setName("Serviço2");
        dfd.addServices(sd);

        // Vamos registrar o agente no DF
        try {
            // register(agente que oferece, descrição)
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }
}
