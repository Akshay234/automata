import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Dfa {
    private static final String NAME = "name";
    private static final String INITIAL_STATE = "initialState";
    private static final String FINAL_STATES = "finalStates";
    private static final String TRANSITION = "transition";
    private final State initialState;
    private String name;
    private State currentState;
    private final Set<State> finalStates;
    private final Transition transition;

    private Dfa(String name, Set<State> finalStates, Transition transition, State initialState) {
        this.name = name;
        this.initialState = initialState;
        this.currentState = initialState;
        this.finalStates = finalStates;
        this.transition = transition;
    }

    public static Dfa create(Map<String, Object> args) {
        String name = (String) args.get(NAME);
        State initialState = (State) args.get(INITIAL_STATE);
        Set<State> finalStates = (Set<State>) args.get(FINAL_STATES);
        Transition transition = (Transition) args.get(TRANSITION);

        return new Dfa(name, finalStates, transition, initialState);
    }

    public boolean process(String instructions) {
        currentState = initialState;
        for (int i = 0; i < instructions.length(); i++) {
            currentState = transition.process(currentState, instructions.charAt(i));
        }
        return isFinalState();
    }

    private boolean isFinalState(){
        Iterator<State> listOfFinalStates = finalStates.iterator();
        while (listOfFinalStates.hasNext()){
            if(currentState.equals(listOfFinalStates.next())){
                return true;
            }
        }
        return false;
    }
}
