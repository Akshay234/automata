public class State {
    private final String name;

    State(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;
        return name != null ? name.equals(state.name) : state.name == null;
    }

    public String mapWith(Character character) {
        return name + " " + character;
    }
}
