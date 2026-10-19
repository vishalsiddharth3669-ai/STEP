
import java.util.Scanner;

class Coach {
    String name;
    Coach prev;
    Coach next;

    Coach(String name) {
        this.name = name;
        this.prev = null;
        this.next = null;
    }
}

class DoublyLinkedList {
    Coach head;
    Coach tail;

    void addLast(String name) {
        Coach newCoach = new Coach(name);

        if (head == null) {
            head = newCoach;
            tail = newCoach;
        } else {
            tail.next = newCoach;
            newCoach.prev = tail;
            tail = newCoach;
        }
    }

    void remove(String name) {
        Coach current = head;

        while (current != null && !current.name.equals(name)) {
            current = current.next;
        }

        if (current == null) {
            return;
        }

        if (current == head) {
            head = current.next;
        } else {
            current.prev.next = current.next;
        }

        if (current == tail) {
            tail = current.prev;
        } else {
            current.next.prev = current.prev;
        }
    }

    void printForward() {
        System.out.print("Forward: ");
        Coach current = head;

        while (current != null) {
            System.out.print(current.name);
            if (current.next != null) {
                System.out.print(" <-> ");
            }
            current = current.next;
        }

        System.out.println();
    }

    void printBackward() {
        System.out.print("Backward: ");
        Coach current = tail;

        while (current != null) {
            System.out.print(current.name);
            if (current.prev != null) {
                System.out.print(" <-> ");
            }
            current = current.prev;
        }

        System.out.println();
    }
}

public class TrainCoachesBothWays {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DoublyLinkedList train = new DoublyLinkedList();

        int n = Integer.parseInt(sc.nextLine().trim());

        for (int i = 0; i < n; i++) {
            train.addLast(sc.nextLine().trim());
        }

        String removeName = sc.nextLine().trim();

        train.remove(removeName);
        train.printForward();
        train.printBackward();

        sc.close();
    }
}
