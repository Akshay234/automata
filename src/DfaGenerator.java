import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

public class DfaGenerator {
    private final String NAME = "name";
    private final String INITIAL_STATE = "initialState";
    private final String FINAL_STATES = "finalStates";
    private final String TRANSITION = "transition";
    private final String START_STATE = "start-state";
    private final String FINAL_STATES1 = "final-states";
    private final String DELTA = "delta";
    private final String name;
    private JSONObject tuple;


    public DfaGenerator(String name, JSONObject tuple){
        this.name = name;
        this.tuple = tuple;
    }

    public Dfa generate() throws IOException, JSONException {
        Map<String, Object> dfaArgs = new HashMap<>();
        JSONObject delta = tuple.getJSONObject(DELTA);
        State initialState = new State(tuple.getString(START_STATE));
        JSONArray finalStatesInfo = tuple.getJSONArray(FINAL_STATES1);
        dfaArgs.put(NAME,name);
        dfaArgs.put(INITIAL_STATE, initialState);
        dfaArgs.put(FINAL_STATES, getFinalStates(finalStatesInfo));
        dfaArgs.put(TRANSITION, getTransition(delta));
        return Dfa.create(dfaArgs);
    }

    private Set<State> getFinalStates(JSONArray info) throws JSONException {
        Set<State> finalStates = new HashSet<>();
        for (int i = 0; i < info.length(); i++) {
            finalStates.add(new State((String) info.get(i)));
        }
        return finalStates;
    }

    private Transition getTransition(JSONObject delta) throws JSONException {
        Map<String, State> paths = new HashMap<>();
        Iterator states = delta.keys();
        while (states.hasNext()){
            String state = (String) states.next();
            Iterator instructions = delta.getJSONObject(state).keys();
            while (instructions.hasNext()){
                String instruction = (String) instructions.next();
                String pathKey = new State(state).mapWith(instruction.charAt(0));
                paths.put(pathKey, new State((String) delta.getJSONObject(state).get(instruction)));
            }
        }
        return new Transition(paths);
    }
}