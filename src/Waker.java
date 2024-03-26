import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;

public class Waker extends Agent {
    protected void setup() {
        addBehaviour(new WakerBehaviour(this, 10000) {
            protected void onWake() {
                // realizar operação X
            }
        });
    }
}
