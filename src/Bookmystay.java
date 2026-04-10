import java.util.*;

class Room {
    private String type;
    private double price;
    private String amenities;

    public Room(String type, double price, String amenities) {
        this.type = type;
        this.price = price;
        this.amenities = amenities;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getAmenities() {
        return amenities;
    }
}

class Inventory {
    private Map<String, Integer> availability = new HashMap<>();

    public Inventory() {
        availability.put("Single", 5);
        availability.put("Double", 0);
        availability.put("Suite", 2);
    }

    public int getAvailableRooms(String roomType) {
        return availability.getOrDefault(roomType, 0);
    }

    public Set<String> getRoomTypes() {
        return availability.keySet();
    }
}

class SearchService {

    public void searchAvailableRooms(Inventory inventory, Map<String, Room> roomCatalog) {

        System.out.println("Available Rooms:\n");

        for (String type : inventory.getRoomTypes()) {

            int available = inventory.getAvailableRooms(type);

            if (available > 0) {

                Room room = roomCatalog.get(type);

                if (room != null) {
                    System.out.println("Room Type: " + room.getType());
                    System.out.println("Price: $" + room.getPrice());
                    System.out.println("Amenities: " + room.getAmenities());
                    System.out.println("Available Rooms: " + available);
                    System.out.println("-----------------------------");
                }
            }
        }
    }
}

public class BookMyStay{

    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        Map<String, Room> roomCatalog = new HashMap<>();

        roomCatalog.put("Single", new Room("Single", 1000, "WiFi, TV"));
        roomCatalog.put("Double", new Room("Double", 1800, "WiFi, TV, AC"));
        roomCatalog.put("Suite", new Room("Suite", 3500, "WiFi, TV, AC, Mini Bar"));

        SearchService searchService = new SearchService();

        System.out.println("Guest searching for available rooms...\n");

        searchService.searchAvailableRooms(inventory, roomCatalog);
    }
}