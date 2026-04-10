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

class InventoryService {

    private Map<String, Integer> roomInventory = new HashMap<>();

    public InventoryService() {
        roomInventory.put("Single", 2);
        roomInventory.put("Double", 1);
        roomInventory.put("Suite", 1);
    }

    public boolean isAvailable(String roomType) {
        return roomInventory.getOrDefault(roomType, 0) > 0;
    }

    public void decrementRoom(String roomType) {
        roomInventory.put(roomType, roomInventory.get(roomType) - 1);
    }

    public int getAvailableRooms(String roomType) {
        return roomInventory.getOrDefault(roomType, 0);
    }
}

class BookingService {

    private Queue<Reservation> bookingQueue;
    private InventoryService inventoryService;

    private Map<String, Set<String>> allocatedRooms = new HashMap<>();

    private Set<String> allocatedRoomIds = new HashSet<>();

    private int roomCounter = 1;

    public BookingService(Queue<Reservation> bookingQueue, InventoryService inventoryService) {
        this.bookingQueue = bookingQueue;
        this.inventoryService = inventoryService;
    }

    public void processBookings() {

        while (!bookingQueue.isEmpty()) {

            Reservation request = bookingQueue.poll();

            String guest = request.getGuestName();
            String roomType = request.getRoomType();

            System.out.println("\nProcessing booking for: " + guest + " | Room Type: " + roomType);

            if (inventoryService.isAvailable(roomType)) {

                String roomId = roomType.substring(0,1).toUpperCase() + roomCounter++;

                if (!allocatedRoomIds.contains(roomId)) {

                    allocatedRoomIds.add(roomId);

                    allocatedRooms.putIfAbsent(roomType, new HashSet<>());
                    allocatedRooms.get(roomType).add(roomId);

                    inventoryService.decrementRoom(roomType);

                    System.out.println("Reservation Confirmed!");
                    System.out.println("Guest: " + guest);
                    System.out.println("Allocated Room ID: " + roomId);
                    System.out.println("Remaining " + roomType + " Rooms: "
                            + inventoryService.getAvailableRooms(roomType));
                }

            } else {
                System.out.println("Reservation Failed: No available " + roomType + " rooms.");
            }
        }
    }
}

public class BookMyStay {

    public static void main(String[] args) {

        Queue<Reservation> bookingQueue = new LinkedList<>();

        bookingQueue.offer(new Reservation("Alice", "Single"));
        bookingQueue.offer(new Reservation("Bob", "Double"));
        bookingQueue.offer(new Reservation("Charlie", "Single"));
        bookingQueue.offer(new Reservation("David", "Suite"));
        bookingQueue.offer(new Reservation("Eva", "Single")); // will fail

        InventoryService inventory = new InventoryService();

        BookingService bookingService = new BookingService(bookingQueue, inventory);

        System.out.println("Processing Booking Requests...\n");

        bookingService.processBookings();
    }
}