import java.util.*;

class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
        System.out.println("Booking request added for Guest: "
                + reservation.getGuestName() + " | Room Type: "
                + reservation.getRoomType());
    }

    public void showRequests() {

        System.out.println("\nCurrent Booking Request Queue (FIFO Order):");

        if (requestQueue.isEmpty()) {
            System.out.println("No booking requests available.");
            return;
        }

        int position = 1;

        for (Reservation r : requestQueue) {
            System.out.println(position + ". Guest: " + r.getGuestName()
                    + " | Room Type: " + r.getRoomType());
            position++;
        }
    }
}

public class BookMyStay{

    public static void main(String[] args) {

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        System.out.println("Guests submitting booking requests...\n");

        Reservation r1 = new Reservation("Alice", "Single");
        Reservation r2 = new Reservation("Bob", "Double");
        Reservation r3 = new Reservation("Charlie", "Suite");
        Reservation r4 = new Reservation("David", "Single");
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);
        bookingQueue.addRequest(r4);

        bookingQueue.showRequests();

        System.out.println("\nAll requests are stored in arrival order and waiting for allocation.");
    }
}