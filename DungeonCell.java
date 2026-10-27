package dungeontester;

/** Node used by the custom doubly linked-list dungeon. */
public class DungeonCell {
    private Encounter value;
    private DungeonCell next;
    private DungeonCell previous;

    public DungeonCell(Encounter value) { this.value = value; }
    public Encounter getValue() { return value; }
    public void setValue(Encounter value) { this.value = value; }
    public DungeonCell getNext() { return next; }
    public void setNext(DungeonCell next) { this.next = next; }
    public DungeonCell getPrevious() { return previous; }
    public void setPrevious(DungeonCell previous) { this.previous = previous; }
}
