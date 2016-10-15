import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

public class InputParser {
    private String fileData;

    public InputParser(String fileData){
        this.fileData = fileData;
    }

    public Set<DFA> parse() throws IOException, JSONException {
        JSONArray jsonarray = new JSONArray(fileData);
        Set dfaCollection = new HashSet();
        for (int i = 0; i < jsonarray.length(); i++) {
            Map<String, Object> dfaArgs = new HashMap<>();
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            JSONObject tuple = jsonobject.getJSONObject("tuple");
            JSONArray alphabets = tuple.getJSONArray("alphabets");
            JSONObject delta = tuple.getJSONObject("delta");
            State initialState = new State(tuple.getString("start-state"));
            JSONArray finalStatesInfo = tuple.getJSONArray("final-states");

            dfaArgs.put("initialState", initialState);
            dfaArgs.put("finalStates", getFinalStates(finalStatesInfo));
            dfaArgs.put("instructions", getInstructions(alphabets));
            dfaArgs.put("transition", getTransition(delta));
            DFA dfa = DFA.create(dfaArgs);
            dfaCollection.add(dfa);
        }
        return dfaCollection;
    }

    private List<Character> getInstructions(JSONArray alphabets) throws JSONException {
        List<Character> instructions = new ArrayList<>();
        for (int i = 0; i < alphabets.length(); i++) {
            String alphabet = (String) alphabets.get(i);
            instructions.add(alphabet.charAt(0));
        }
        return instructions;
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
            String next = (String) states.next();
            Iterator listOfTransitions = delta.getJSONObject(next).keys();
            while (listOfTransitions.hasNext()){
                String transition = (String) listOfTransitions.next();
                String pathKey = appendWithSpace(next, transition);
                paths.put(pathKey, new State((String) delta.getJSONObject(next).get(transition)));
            }
        }
        return new Transition(paths);
    }

    private String appendWithSpace(String state, String instruction) {
        return state+" "+instruction;
    };
}