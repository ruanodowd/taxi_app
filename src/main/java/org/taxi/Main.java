package org.taxi;

import java.util.List;
import java.util.Scanner;

public class Main implements VehicleHiringTest{
    public Map map;
    public Main(Map map) {
        this.map = map;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map map = new Map(5, 5);
        Main main = new Main(map);

        System.out.println("Welcome to Our Taxi App");

        // username and password
        System.out.print("Please enter your username: ");
        String username = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();
        
        if (username == null || password == null) {
            System.out.println("Invalid input");
            scanner.close();
            return;
        }
        
        System.out.println("Here's the current map");
        main.testAddToMap("RAWR", map.getLocation(2,2));
        main.testAddToMap("SKID", map.getLocation(2,2));
        main.testAddToMap("ZOOM", map.getLocation(1,0));
        showRectangularMap(map, 5, 5);
        
        
        System.out.println("Welcome what do you want to do?");
        System.out.println("1. Create new Taxi");
        System.out.println("2. Move the taxi");
        System.out.println("3. Remove the Taxi");
        System.out.println("4. Get the Taxi Location");
        System.out.println("5. Find nearby vehicles");
        
        int option = scanner.nextInt();

        if (option == 1) {
            System.out.print("Please enter the taxi registration: ");
            String reg = scanner.next();
            
            System.out.print("Please enter the x location: ");
            int x = scanner.nextInt();
            System.out.print("Please enter the y location: ");
            int y = scanner.nextInt();

            main.testAddToMap(reg, map.getLocation(x, y));
            System.out.println("Taxi is added to the location. Here's your new map");
            showRectangularMap(map, 5, 5);
        }
        
        else if (option == 2) {
            System.out.print("Please enter the taxi registration: ");
            String reg = scanner.next();
            
            System.out.print("Please enter the x location: ");
            int x = scanner.nextInt();
            System.out.print("Please enter the y location: ");
            int y = scanner.nextInt();

            main.testMoveVehicle(reg, map.getLocation(x, y));
            System.out.println("Taxi is moved to the location. Here's your new map");
            showRectangularMap(map, 5, 5);
        }

        else if (option == 3) {
            System.out.print("Please enter the taxi registration: ");
            String reg = scanner.next();

            main.testRemoveVehicle(reg);
            System.out.println("The taxi is removed from the map. Here's your new map");
            showRectangularMap(map, 5, 5);
        }
        
        else if (option == 4) {
            System.out.print("Please enter the taxi registration: ");
            String reg = scanner.next();

            Location loc = main.testGetVehicleLoc(reg);

            int x = loc.getX();
            int y = loc.getY();


            System.out.println("Taxi is found. It is at co-ordinate (" + x + "," + y + ")");
        }

        else if (option == 5) {
            System.out.print("Please enter the x location: ");
            int x = scanner.nextInt();
            System.out.print("Please enter the y location: ");
            int y = scanner.nextInt();
            System.out.print("Please enter the radius");
            int radius = scanner.nextInt();

            List<String> regs = main.testGetVehiclesInRange(map.getLocation(x, y), radius);

            System.out.println("Here are all the taxi registrations numbers near you!");
            for (String reg : regs) {
                System.out.println(reg);
            }
        }
        scanner.close();
    }

    public static void addTaxiToMap(String reg, Location loc) {
        // added the location parameter
        loc.addTaxi(new Taxi(reg));
    }
    @Override
    public boolean testAddToMap(String reg, Location loc) {
        //todo its more special than I thought
        // the taxi bank will help with this - ushen
        loc.addTaxi(new Taxi(reg));
        return true;
    }

    @Override
    public boolean testMoveVehicle(String reg, Location loc) {
        // what I need to do. When put the reg and location. 
        // take the taxi from current location move it to the final location 

        // find location 
        Taxi taxi = TaxiBank.returnSpecificTaxi(reg);
        if (taxi == null) {
            return false;
        }

        taxi.getLocation(map);

        // remove from location 
        testRemoveVehicle(reg);

        // add the taxi to the location 
        loc.addTaxi(taxi);

        return true;
    }

    @Override
    public boolean testRemoveVehicle(String reg) {
        // return the taxi associated with the registration
        Taxi taxi = TaxiBank.returnSpecificTaxi(reg);
        
        
        // check if null 
        if (taxi.equals(null)) {
            return false;
        }

        // removes taxi from the location where it is. 
        Location loc = testGetVehicleLoc(reg);
        if (loc == null) {
            return false;
        }
        
        
        loc.removeTaxi(taxi);
        return true;
    }
    
    @Override
    public Location testGetVehicleLoc(String reg) {
        Taxi taxi = TaxiBank.returnSpecificTaxi(reg);
        if (taxi.equals(null)) {
            return null;
        }
        
        return taxi.getLocation(map);
    }

    @Override
    public List<String> testGetVehiclesInRange(Location loc, int r) {
        // getting a list of registrations (ideally taxi objects however, return type suggests it's a string)
        ArrayList<String> taxis = new ArrayList<>();

        for (Taxi taxi: loc.getContainedTaxis()) {
            taxis.add(taxi.getRegistrationNumber());
        }

        // figuring out the neighbouring locations, I wouldn't worry about calling locations that aren't in the bounds for now.
        // because I've edited the getLocation class to return null. I won't add those to the array. 
        int upperXBound = loc.getX() + 2*r;
        int lowerXBound = loc.getX() - 2*r;

        int upperYBound = loc.getY() + 2*r;
        int lowerYBound = loc.getY() - 2*r;

        for (int i = lowerXBound; i <= upperXBound; i++) {
            for (int j = lowerYBound; j<= upperYBound; j++) {
                Location location = map.getLocation(i,j);
                if (!(location == null)) {
                    for (Taxi taxi: location.getContainedTaxis()) {
                        String reg = taxi.getRegistrationNumber();
                        if (!reg.equals(null)) {
                            taxis.add(reg);
                        }
                    }
                } 
            }
        }
        return taxis;
    }

    public static void showRectangularMap(Map map, int height, int width){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(map.getLocation(x, y)
                        .getContainedTaxis()
                        .size() + " ");
            }
            System.out.print("\n");
        }
    }
}