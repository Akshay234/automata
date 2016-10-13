import java.util.Map;

public class Transition {
    private Map<String, State> paths;

    public Transition(Map<String, State> paths) {
        this.paths = paths;
    }

    public State process(State currentState, Character character) {
        String pathKey = currentState.mapWith(character);
        State nextState = paths.get(pathKey);
        return nextState;
    }
}
