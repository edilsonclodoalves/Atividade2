import jade.core.Agent;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;
import java.util.Iterator;

public class Busca extends Agent {
    protected void setup() {
        // criamos uma entrada no DF
        DFAgentDescription template = new DFAgentDescription();

        // criamos um objeto contendo a descrição do serviço
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Tipo"); // definimos o tipo de serviço
        // Neste momento, poderia definir outras características
        // do serviço buscado para filtrar melhor a busca.
        // No caso, vamos buscar por serviços do tipo "Tipo"

        // adicionamos o serviço na entrada
        template.addServices(sd);

        try {
            // Vou buscar pelos agentes
            // A busca retorna um array DFAgentDescription
            // O parâmetro this indica o agente que está realizando a busca
            DFAgentDescription[] result = DFService.search(this, template);

            // Imprimo os resultados
            for (int i = 0; i < result.length; i++) {
                // result[i].getName() fornece a AID do agente
                String out = result[i].getName().getLocalName() + " provê ";

                // Para obter os serviços do agente invocamos
                // o método getAllServices();
                Iterator iter = result[i].getAllServices();
                while (iter.hasNext()) {
                    // Extraímos os serviços para um objeto ServiceDescription
                    ServiceDescription SD = (ServiceDescription) iter.next();
                    // Capturamos o nome do serviço
                    out += " " + SD.getName();
                }
                // Os serviços de cada agente são impressos na tela
                System.out.println(out);
            } // fim do laço for
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }
}
