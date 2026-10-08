import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelBookingSystem {

    public static void main(String[] args) {

        Customer customerA = new Customer("Customer A");

        Customer customerB = new Customer("Customer B");

        Customer customerC = new Customer("Customer C");

        Room standard = new StandardRoom("101");

        Room deluxe = new DeluxeRoom("201");

        Hotel hotel = new Hotel();

        hotel.addRoom(standard);
        hotel.addRoom(deluxe);

        LocalDate jan1 = LocalDate.of(2026, 1, 1);

        LocalDate jan5 = LocalDate.of(2026, 1, 5);

        hotel.checkAvailability(
                standard,
                jan1,
                jan5);

        Reservation reservationA = hotel.reserve(
                customerA,
                standard,
                jan1,
                jan5);

        LocalDate jan3 = LocalDate.of(2026, 1, 3);

        LocalDate jan7 = LocalDate.of(2026, 1, 7);

        hotel.checkAvailability(
                standard,
                jan3,
                jan7);

        if (reservationA != null) {

            reservationA.cancel(
                    LocalDate.of(2025, 12, 20));
        }

        Reservation reservationC = hotel.reserve(
                customerC,
                deluxe,
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 12));
    }
}

class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

abstract class Room {

    private String roomNumber;

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public abstract double calculatePrice(
            long nights);
}

class StandardRoom extends Room {

    public StandardRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(long nights) {
        return nights * 100;
    }
}

class DeluxeRoom extends Room {

    public DeluxeRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(long nights) {
        return nights * 180;
    }
}

class Suite extends Room {

    public Suite(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(long nights) {
        return nights * 300;
    }
}

class Reservation {

    private Customer customer;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean cancelled;

    public Reservation(
            Customer customer,
            Room room,
            LocalDate startDate,
            LocalDate endDate) {

        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cancelled = false;
    }

    public boolean overlaps(
            LocalDate start,
            LocalDate end) {

        return !end.isBefore(startDate)
                && !start.isAfter(endDate);
    }

    public double getPrice() {

        long nights = java.time.temporal.ChronoUnit.DAYS
                .between(startDate, endDate);

        return room.calculatePrice(nights);
    }

    public void cancel(LocalDate currentDate) {

        LocalDate deadline = startDate.minusDays(1);

        if (currentDate.isBefore(deadline)
                || currentDate.isEqual(deadline)) {

            cancelled = true;

            System.out.println(
                    "Reservation for "
                            + customer.getName()
                            + ", Room "
                            + room.getRoomNumber()
                            + " cancelled successfully.");

        } else {

            System.out.println(
                    "Cancellation deadline has passed.");
        }
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public Room getRoom() {
        return room;
    }
}

class Hotel {

    private List<Room> rooms = new ArrayList<>();

    private List<Reservation> reservations = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public boolean checkAvailability(
            Room room,
            LocalDate start,
            LocalDate end) {

        for (Reservation reservation : reservations) {

            if (!reservation.isCancelled()
                    && reservation.getRoom() == room
                    && reservation.overlaps(start, end)) {

                System.out.println(
                        "Room "
                                + room.getRoomNumber()
                                + " is not available.");

                return false;
            }
        }

        System.out.println(
                "Room "
                        + room.getRoomNumber()
                        + " is available.");

        return true;
    }

    public Reservation reserve(
            Customer customer,
            Room room,
            LocalDate start,
            LocalDate end) {

        if (!checkAvailability(room, start, end)) {
            return null;
        }

        Reservation reservation = new Reservation(
                customer,
                room,
                start,
                end);

        reservations.add(reservation);

        System.out.println(
                "Reservation confirmed for "
                        + customer.getName()
                        + ", Room "
                        + room.getRoomNumber());

        System.out.println(
                "Price: $"
                        + reservation.getPrice());

        return reservation;
    }
}