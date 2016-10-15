import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DFA {

    private final List<Character> instructions;
    private State currentState;
    private final Set<State> finalStates;
    private final Transition transition;

    private DFA(List<Character> instructions, State currentState, Set<State> finalStates, Transition transition) {
        this.instructions = instructions;
        this.currentState = currentState;
        this.finalStates = finalStates;
        this.transition = transition;
    }

    public static DFA create(Map<String, Object> args) {
        List<Character> instructions = (List<Character>) args.get("instructions");
        State initialState = (State) args.get("initialState");
        Set<State> finalStates = (Set<State>) args.get("finalStates");
        Transition transition = (Transition) args.get("transition");

        return new DFA(instructions, initialState, finalStates, transition);
    }

    public void processInstructions() {
        for (int i = 0; i < instructions.size(); i++) {
            currentState = transition.process(currentState, instructions.get(i));
        }
    }

    public boolean isFinalState(){
        boolean isObtained = false;
        Iterator<State> listOfFinalStates = finalStates.iterator();
        while (listOfFinalStates.hasNext()){
            isObtained = currentState.equals(listOfFinalStates.next());
        }
        return isObtained;
    }
}
