package com.example.accessories;

public enum AccessoryTypes {
    CAMERA("摄像头"),
    BATTERY("电池"),
    RADAR("雷达"),
    WEAPON("武器"),
    STORAGE("存储模块");
    
    private final String displayName;
    
    AccessoryTypes(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}