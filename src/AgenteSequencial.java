import jade.core.Agent;
import jade.core.behaviours.*;

public class AgenteSequencial extends Agent {
    protected void setup() {
        // Mensagem de inicialização do agente
        System.out.println("Olá! Meu nome é " + getLocalName());
        System.out.println("Vou executar três comportamentos:");

        // Criamos um objeto da classe SequentialBehaviour
        SequentialBehaviour comportamento = new SequentialBehaviour(this) {
            public int onEnd() {
                myAgent.doDelete();
                return 0;
            }
        };

        // Adicionamos o primeiro comportamento
        comportamento.addSubBehaviour(new WakerBehaviour(this, 500) {
            long t0 = System.currentTimeMillis();
            protected void onWake() {
                System.out.println((System.currentTimeMillis() - t0) +
                        ": Executei meu primeiro comportamento após meio segundo!");
            }
        });

        // Adicionamos o segundo comportamento
        comportamento.addSubBehaviour(new OneShotBehaviour(this) {
            public void action() {
                System.out.println("Executei meu segundo comportamento");
            }
        });

        // Adicionamos o terceiro comportamento
        comportamento.addSubBehaviour(new TickerBehaviour(this, 700) {
            int exec = 0;
            long t1 = System.currentTimeMillis();
            protected void onTick() {
                if (exec == 3) stop();
                else {
                    System.out.println((System.currentTimeMillis() - t1) +
                            ": Estou executando meu terceiro comportamento");
                    exec++;
                }
            }
        });

        // Acionamos sua execução
        addBehaviour(comportamento);
    }

    protected void takeDown() {
        System.out.println("Fui finalizado com sucesso");
    }
}
