package interfaces;

import enums.Event;
import enums.State;

public interface StateMachine {
    State getCurrentState();
    void processEvent(Event event);
}
