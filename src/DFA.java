import java.util.List;
import java.util.Map;

public class DFA {

    private final List<Character> instructions;
    private State currentState;
    private final State finalState;
    private final Transition transition;

    private DFA(List<Character> instructions, State currentState, State finalState, Transition transition) {
        this.instructions = instructions;
        this.currentState = currentState;
        this.finalState = finalState;
        this.transition = transition;
    }

    public static DFA create(Map<String, Object> args) {
        List<Character> instructions = (List<Character>) args.get("instructions");
        State initialState = (State) args.get("initialState");
        State finalState = (State) args.get("finalState");
        Transition transition = (Transition) args.get("transition");

        return new DFA(instructions, initialState, finalState, transition);
    }

    public boolean processInstructions() {
        for (int i = 0; i < instructions.size(); i++) {
            currentState = transition.process(currentState, instructions.get(i));
        }
        return currentState.equals(finalState);
    }
}
