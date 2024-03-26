import jade.core.Agent;
import jade.core.behaviours.*;

public class AgenteCompParalelo extends Agent {
    protected void setup() {
        System.out.println("Olá! Eu sou o agente " + getLocalName());
        System.out.println("Vou executar três comportamentos concorrentemente");

        ParallelBehaviour s = new ParallelBehaviour(this,  2) {
            public int onEnd() {
                System.out.println("Comportamento Composto Finalizado com Sucesso!");
                return 0;
            }
        };

        addBehaviour(s);

        s.addSubBehaviour(new SimpleBehaviour(this) {
            int qtd = 1;

            public void action() {
                System.out.println("Comportamento 1: Executando pela " + qtd + " vez");
                qtd = qtd + 1;
            }

            public boolean done() {
                if (qtd == 4) {
                    System.out.println("Comportamento 1 - Finalizado");
                    return true;
                } else {
                    return false;
                }
            }
        });

        s.addSubBehaviour(new SimpleBehaviour(this) {
            int qtd = 1;

            public void action() {
                System.out.println("Comportamento 2: Executando pela " + qtd + " vez");
                qtd = qtd + 1;
            }

            public boolean done() {
                if (qtd == 8) {
                    System.out.println("Comportamento 2 - Finalizado");
                    return true;
                } else {
                    return false;
                }
            }
        });

        s.addSubBehaviour(new SimpleBehaviour(this) {
            int qtd = 1;

            public void action() {
                System.out.println("Comportamento 3: Executando pela " + qtd + " vez");
                qtd = qtd + 1;
            }

            public boolean done() {
                if (qtd == 10) {
                    System.out.println("Comportamento 3 - Finalizado");
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}
