package org.buildcode.syncservice.api.constants;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public enum RideStatus {
    ACTIVE(BigInteger.valueOf(0)),
    COMPLETED(BigInteger.valueOf(1)),
    CANCELLED(BigInteger.valueOf(2));

    private final BigInteger value;
    private static final Map<BigInteger, RideStatus> VALUE_MAP = new HashMap<>();

    // Static block to populate the map
    static {
        for (RideStatus status : RideStatus.values()) {
            VALUE_MAP.put(status.value, status);
        }
    }

    // Constructor
    RideStatus(BigInteger value) {
        this.value = value;
    }

    public BigInteger getValue() {
        return value;
    }

    // Method to map BigInteger to RideStatus
    public static RideStatus fromValue(BigInteger value) {
        RideStatus status = VALUE_MAP.get(value);
        if (status == null) {
            throw new IllegalArgumentException("Unknown RideStatus value: " + value);
        }
        return status;
    }
}