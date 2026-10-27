package dungeontester;

/** A room without an encounter. */
public class Empty extends Encounter {
    public Empty(String name) { super(name, '.'); }
    @Override public String describe() { return "The room is quiet. Only cold stone and distant echoes remain."; }
}
