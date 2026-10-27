package dungeontester;

/** The exit that completes a dungeon level. */
public class Exit extends Encounter {
    public Exit() { super("Moon Gate", 'E'); }
    @Override public String describe() { return "A silver gate hums with energy. Step through it to descend deeper."; }
}
