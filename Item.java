package dungeontester;

/** A collectable item. */
public class Item extends Encounter {
    private final int healing;
    public Item(String name, int healing) { super(name, '!'); this.healing = healing; }
    public int getHealing() { return healing; }
    @Override public String describe() { return "You find " + getName() + ". It restores " + healing + " health when used."; }
}
