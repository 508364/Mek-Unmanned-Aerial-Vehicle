package com.example.drones;

public enum DroneTypes {
    SCOUT("侦察型"),
    CARGO("货运型"), 
    COMBAT("战斗型"),
    MINING("采矿型");
    
    private final String displayName;
    
    DroneTypes(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}