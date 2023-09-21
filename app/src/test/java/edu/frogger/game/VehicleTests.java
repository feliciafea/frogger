package edu.frogger.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class VehicleTests {

    @Test
    public void testVehicleCreation() {
        Vehicle car = new Car(0, 100);

        assertEquals(0, car.getPosX());
        assertEquals(100, car.getPosY());

        Vehicle racecar = new Racecar(0, 100);

        assertEquals(0, racecar.getPosX());
        assertEquals(100, racecar.getPosY());

        Vehicle truck = new Truck(0, 100);

        assertEquals(0, truck.getPosX());
        assertEquals(100, truck.getPosY());
    }

    @Test
    public void testVehiclesCanMove() {
        Vehicle[] vehicles = new Vehicle[6];
        int startPosX = 500;
        int startPosY = 600;
        vehicles[0] = new Car(startPosX, startPosY);
        vehicles[1] = new Car(startPosX, startPosY);
        vehicles[2] = new Racecar(startPosX, startPosY);
        vehicles[3] = new Racecar(startPosX, startPosY);
        vehicles[4] = new Truck(startPosX, startPosY);
        vehicles[5] = new Truck(startPosX, startPosY);
        for (int i = 0; i < 5; i++) {
            for (Vehicle vehicle : vehicles) {
                vehicle.move();
            }
        }

        for (Vehicle vehicle : vehicles) {
            assertNotEquals(startPosX, vehicle.getPosX());
            assertEquals(startPosY, vehicle.getPosY());
        }
    }

    @Test
    public void testCarMoveCorrectSpeed() {
        int startPosX = 500;
        int startPosY = 600;
        int moveCount = 5;

        Vehicle car = new Car(startPosX, startPosY);

        for (int i = 0; i < moveCount; i++) {
            car.move();
        }

        assertEquals(startPosX + (moveCount * car.getSpeed()), car.getPosX());
        assertEquals(startPosY, car.getPosY());
    }

    @Test
    public void testRacecarMoveCorrectSpeed() {
        int startPosX = 500;
        int startPosY = 600;
        int moveCount = 5;

        Vehicle raceCar = new Racecar(startPosX, startPosY);

        for (int i = 0; i < moveCount; i++) {
            raceCar.move();
        }

        assertEquals(startPosX + (moveCount * raceCar.getSpeed()), raceCar.getPosX());
        assertEquals(startPosY, raceCar.getPosY());
    }

    @Test
    public void testTruckMoveCorrectSpeed() {
        int startPosX = 500;
        int startPosY = 600;
        int moveCount = 5;

        Vehicle truck = new Truck(startPosX, startPosY);

        for (int i = 0; i < moveCount; i++) {
            truck.move();
        }

        assertEquals(startPosX + (moveCount * truck.getSpeed()), truck.getPosX());
        assertEquals(startPosY, truck.getPosY());
    }

    @Test
    public void vehicleCollisionDetection() {
        Vehicle racecar = new Racecar(500, 600);

        assertFalse(Util.checkCollision(480, 300, 160, racecar.getPosX(),
                racecar.getPosY(), racecar.getWidth() * 160));

        assertTrue(Util.checkCollision(540, 570, 160, racecar.getPosX(),
                racecar.getPosY(), racecar.getWidth() * 160));
    }

    @Test
    public void testVehicleSpawn() {
        // Create a car that spawns on the left side of the road
        Vehicle car1 = new Car(0, 100);

        // Create a car that spawns on the right side of the road
        Vehicle car2 = new Car(500, 200);

        // Assert that the first car's position is on the left side of the road
        assertTrue(car1.getPosX() < car2.getPosX());

        // Create a racecar that spawns on the left side of the road
        Vehicle raceCar1 = new Racecar(0, 100);

        // Create a racecar that spawns on the right side of the road
        Vehicle raceCar2 = new Racecar(500, 200);

        // Assert that the first racecar's position is on the left side of the road
        assertTrue(raceCar1.getPosX() < raceCar2.getPosX());

        // Create a truck that spawns on the left side of the road
        Vehicle truck1 = new Truck(0, 100);

        // Create a truck that spawns on the right side of the road
        Vehicle truck2 = new Truck(500, 200);

        // Assert that the first truck's position is on the left side of the road
        assertTrue(truck1.getPosX() < truck2.getPosX());
    }
}
