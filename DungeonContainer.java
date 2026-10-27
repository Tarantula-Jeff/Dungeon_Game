package dungeontester;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/** Builds and runs a dungeon represented by a custom doubly linked list. */
public class DungeonContainer {
    private final Random random = new Random();
    private DungeonCell head;
    private DungeonCell tail;
    private DungeonCell playerCell;
    private MainCharacter player;
    private int level = 1;

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            printBanner();
            System.out.print("What is your adventurer name? ");
            String name = scanner.nextLine().trim();
            player = new MainCharacter(name.isEmpty() ? "Adventurer" : name);
            createDungeon();
            gameLoop(scanner);
        }
    }

    private void createDungeon() {
        clear();
        int roomCount = 7 + (level * 2);
        List<Encounter> rooms = new ArrayList<>();
        rooms.add(new Empty("Entrance"));
        rooms.add(new Exit());
        String[] enemies = {"Cave Goblin", "Moss Goblin", "Ash Goblin", "Iron Goblin"};
        while (rooms.size() < roomCount) {
            if (random.nextInt(100) < 28) rooms.add(new Item("Moonleaf Potion", 25));
            else if (random.nextInt(100) < 58) rooms.add(new GoblinEnemy(enemies[random.nextInt(enemies.length)], level, random));
            else rooms.add(new Empty("Forgotten Chamber"));
        }
        Collections.shuffle(rooms.subList(1, rooms.size()), random);
        for (Encounter room : rooms) insert(room);
        playerCell = head;
        System.out.println("\nLevel " + level + " begins. Find the Moon Gate [E] and survive the dungeon.");
        inspectCurrentRoom();
    }

    private void gameLoop(Scanner scanner) {
        while (player.isAlive()) {
            System.out.println("\n[M]ap  [L]eft  [R]ight  [S]tats  [I]nventory  [H]elp  [Q]uit");
            System.out.print("> ");
            String choice = scanner.nextLine().trim().toLowerCase();
            switch (choice) {
                case "m" -> printMap();
                case "l" -> move(-1, scanner);
                case "r" -> move(1, scanner);
                case "s" -> System.out.println(player.statistics());
                case "i" -> usePotion();
                case "h" -> printHelp();
                case "q" -> { System.out.println("The dungeon waits for your return."); return; }
                default -> System.out.println("Choose one of the displayed commands.");
            }
        }
        System.out.println("\nYour lantern fades. Game over.");
    }

    private void move(int direction, Scanner scanner) {
        DungeonCell destination = direction < 0 ? playerCell.getPrevious() : playerCell.getNext();
        if (destination == null) { System.out.println("A solid wall blocks that direction."); return; }
        playerCell = destination;
        inspectCurrentRoom();
        Encounter encounter = playerCell.getValue();
        if (encounter instanceof Item item) collectItem(item);
        else if (encounter instanceof GoblinEnemy enemy) battle(enemy, scanner);
        else if (encounter instanceof Exit) nextLevel(scanner);
    }

    private void inspectCurrentRoom() { System.out.println("\n" + playerCell.getValue().describe()); }

    private void collectItem(Item item) {
        player.addPotion();
        playerCell.setValue(new Empty("Cleared Chamber"));
        System.out.println("You collect " + item.getName() + ". You now have " + player.getPotions() + " potion(s).");
    }

    private void battle(GoblinEnemy enemy, Scanner scanner) {
        System.out.println("\nCombat begins with " + enemy.getName() + "!");
        while (player.isAlive() && !enemy.isDefeated()) {
            System.out.println("[F]ight  [P]otion  [S]tats");
            System.out.print("> ");
            String action = scanner.nextLine().trim().toLowerCase();
            if (action.equals("s")) { System.out.println(player.statistics()); continue; }
            if (action.equals("p")) { usePotion(); continue; }
            if (!action.equals("f")) { System.out.println("Choose F, P, or S."); continue; }
            int playerDamage = Math.max(1, player.getAttack() + random.nextInt(7) - enemy.getDefence());
            enemy.takeDamage(playerDamage);
            System.out.println("You strike for " + playerDamage + " damage.");
            if (enemy.isDefeated()) break;
            int enemyDamage = Math.max(1, enemy.getAttack() + random.nextInt(5) - player.getDefence());
            player.takeDamage(enemyDamage);
            System.out.println(enemy.getName() + " hits you for " + enemyDamage + " damage. Health: " + player.getHealth() + "/" + player.getMaxHealth());
        }
        if (enemy.isDefeated()) {
            System.out.println("You defeated " + enemy.getName() + " and gained " + enemy.getExperienceReward() + " XP.");
            if (player.gainExperience(enemy.getExperienceReward())) System.out.println("Level up! You are now level " + player.getLevel() + ". Your health is restored.");
            playerCell.setValue(new Empty("Cleared Chamber"));
        }
    }

    private void nextLevel(Scanner scanner) {
        System.out.print("Step through the Moon Gate? [Y/N] ");
        if (!scanner.nextLine().trim().equalsIgnoreCase("y")) return;
        level++;
        player.heal(15);
        createDungeon();
    }

    private void usePotion() {
        if (!player.usePotion()) { System.out.println("Your inventory has no potions."); return; }
        System.out.println("You use a Moonleaf Potion. Health: " + player.getHealth() + "/" + player.getMaxHealth());
    }

    private void printMap() {
        StringBuilder map = new StringBuilder("Dungeon: ");
        Iterator iterator = new Iterator(head);
        while (iterator.hasNext()) {
            DungeonCell cell = iterator.next();
            map.append(cell == playerCell ? "[P]" : "[" + cell.getValue().getSymbol() + "]");
            if (iterator.hasNext()) map.append("—");
        }
        System.out.println(map);
    }

    private void printHelp() { System.out.println("Reach the Moon Gate [E]. Defeat goblins [G], collect items [!], and use potions to survive."); }
    private void printBanner() { System.out.println("\n=== DUNGEON OF THE MOON GATE ===\nA linked-list dungeon crawler. Explore, battle, and descend."); }
    private void clear() { head = null; tail = null; }
    public void insert(Encounter value) { DungeonCell cell = new DungeonCell(value); if (head == null) head = tail = cell; else { tail.setNext(cell); cell.setPrevious(tail); tail = cell; } }
}
