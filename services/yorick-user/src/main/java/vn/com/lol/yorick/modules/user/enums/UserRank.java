package vn.com.lol.yorick.modules.user.enums;

import vn.com.lol.common.exceptions.ResourceNotFoundException;

import java.util.Arrays;

public enum UserRank {
    NONE,
    BRONZE,
    SILVER,
    GOLD,
    PLATINUM,
    DIAMOND;

    public static UserRank findUserRankByValue(String value) throws ResourceNotFoundException {
        return Arrays.stream(values())
                .filter(s -> s.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Can not found the user rank " + value));
    }
}
