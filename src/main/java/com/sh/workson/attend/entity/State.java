package com.sh.workson.attend.entity;

public enum State {
    WORK("정상출근"),
    QUIT("퇴근"),
    LATE("지각"),
    VACA("휴가");

    private final String displayName;

    State(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
