
import java.util.Scanner;

class Player {
    String name;
    Player next;

    Player(String name) {
        this.name = name;
        this.next = null;
    }
}

class CircularLinkedList {
    Player tail;

    void addLast(String name) {
        Player newPlayer = new Player(name);

        if (tail == null) {
            tail = newPlayer;
            tail.next = tail;
        } else {
            newPlayer.next = tail.next;
            tail.next = newPlayer;
            tail = newPlayer;
        }
    }

    void printTurns(int turns) {
        if (tail == null) {
            return;
        }

        Player current = tail.next;

        for (int i = 0; i < turns; i++) {
            if (i > 0) {
                System.out.print(" ");
            }

            System.out.print(current.name);
            current = current.next;
        }

        System.out.println();
    }
}

public class RoundRobinGameTurns {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CircularLinkedList players = new CircularLinkedList();

        int n = Integer.parseInt(sc.nextLine().trim());

        for (int i = 0; i < n; i++) {
            players.addLast(sc.nextLine().trim());
        }

        int turns = Integer.parseInt(sc.nextLine().trim());

        players.printTurns(turns);

        sc.close();
    }
}
