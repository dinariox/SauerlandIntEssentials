package de.dinario.sauerlandIntEssentials;

import org.bukkit.Location;

import java.util.HashMap;

public final class DeathPositionLogger {

    private static DeathPositionLogger instance;
    private HashMap<String, Location> deathPositions = new HashMap<>();

    private DeathPositionLogger() {
    }

    public static DeathPositionLogger getInstance() {
        if (instance == null) {
            instance = new DeathPositionLogger();
        }
        return instance;
    }

    public void addDeathPosition(String playerUUID, Location location) {
        deathPositions.put(playerUUID, location);
        System.out.println("DeathPositionLogger: " + playerUUID + ": " + location.getX() + ", " + location.getY() + ", " + location.getZ());
    }

    public Location getDeathPosition(String playerUUID) {
        return deathPositions.get(playerUUID);
    }

}
