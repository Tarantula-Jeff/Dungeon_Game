package dungeontester;

/** A room occupant in the dungeon. */
public abstract class Encounter {
    private final String name;
    private final char symbol;

    protected Encounter(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() { return name; }
    public char getSymbol() { return symbol; }
    public abstract String describe();
}
