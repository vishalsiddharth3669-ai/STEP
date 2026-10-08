import java.util.*;

interface Seat {
    String getSeatNumber();

    double getPrice();
}

class RegularSeat implements Seat {
    private String seatNumber;

    public RegularSeat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return 150.00;
    }
}

class PremiumSeat implements Seat {
    private String seatNumber;

    public PremiumSeat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return 250.00;
    }
}

class ReclinerSeat implements Seat {
    private String seatNumber;

    public ReclinerSeat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return 400.00;
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

class Show {
    private String showTime;
    private boolean started;
    private Set<String> bookedSeats = new HashSet<>();

    public Show(String showTime) {
        this.showTime = showTime;
        this.started = false;
    }

    public boolean isAvailable(String seatNumber) {
        return !bookedSeats.contains(seatNumber);
    }

    public void bookSeat(String seatNumber) {
        bookedSeats.add(seatNumber);
    }

    public void releaseSeat(String seatNumber) {
        bookedSeats.remove(seatNumber);
    }

    public boolean hasStarted() {
        return started;
    }

    public void startShow() {
        started = true;
    }
}

class Booking {
    private Customer customer;
    private Show show;
    private List<Seat> seats;
    private boolean cancelled;

    public Booking(
            Customer customer,
            Show show,
            List<Seat> seats) {

        this.customer = customer;
        this.show = show;
        this.seats = seats;
        this.cancelled = false;
    }

    public double getTotal() {
        double total = 0;

        for (Seat seat : seats) {
            total += seat.getPrice();
        }

        return total;
    }

    public void cancel() {

        if (cancelled) {
            System.out.println("Booking is already cancelled.");
            return;
        }

        if (show.hasStarted()) {
            System.out.println(
                    "Cannot cancel booking: show has already started.");
            return;
        }

        for (Seat seat : seats) {
            show.releaseSeat(seat.getSeatNumber());
        }

        cancelled = true;

        System.out.println(
                customer.getName() +
                        "'s booking cancelled.");

        System.out.println(
                "Seats released.");
    }
}

public class CampusPremiereTicketCounter {

    public static Booking book(
            Customer customer,
            Show show,
            Seat... seats) {

        if (seats.length == 0) {
            System.out.println("Booking must contain at least one seat.");
            return null;
        }

        if (seats.length > 6) {
            System.out.println(
                    "Cannot book more than 6 seats.");
            return null;
        }

        for (Seat seat : seats) {

            if (!show.isAvailable(seat.getSeatNumber())) {
                System.out.println(
                        "Seat " +
                                seat.getSeatNumber() +
                                " is already booked for this show.");
                return null;
            }
        }

        List<Seat> selectedSeats = new ArrayList<>();

        for (Seat seat : seats) {
            show.bookSeat(seat.getSeatNumber());
            selectedSeats.add(seat);
        }

        Booking booking = new Booking(
                customer,
                show,
                selectedSeats);

        System.out.print(
                "Booking confirmed for " +
                        customer.getName() +
                        ": ");

        for (int i = 0; i < selectedSeats.size(); i++) {
            System.out.print(
                    selectedSeats.get(i).getSeatNumber());

            if (i < selectedSeats.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.printf(
                ". Total: ₹%.2f%n",
                booking.getTotal());

        return booking;
    }

    public static void main(String[] args) {

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Show show = new Show("7 PM");

        Booking ashaBooking = book(
                asha,
                show,
                new RegularSeat("A1"),
                new RegularSeat("A2"),
                new PremiumSeat("F5"));

        book(
                ravi,
                show,
                new RegularSeat("A2"));

        Booking raviBooking = book(
                ravi,
                show,
                new ReclinerSeat("R1"));

        ashaBooking.cancel();

        book(
                neha,
                show,
                new RegularSeat("A2"));
    }
}