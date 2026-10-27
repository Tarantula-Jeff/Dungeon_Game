package dungeontester;

/** Small iterator for walking through the dungeon cells. */
public class Iterator {
    private DungeonCell current;
    public Iterator(DungeonCell start) { current = start; }
    public boolean hasNext() { return current != null; }
    public DungeonCell next() { DungeonCell result = current; if (current != null) current = current.getNext(); return result; }
}
