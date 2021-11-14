package com.one234gift.calllistservice.call.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallEvent {
    private CallEventType type;

    public boolean isRegster() {
        return type.equals(CallEventType.REGISTER);
    }

    public boolean isChange() {
        return type.equals(CallEventType.CHANGE);
    }

    public boolean isRemove() {
        return type.equals(CallEventType.REMOVE);
    }

    public enum CallEventType{
        REGISTER,CHANGE,REMOVE
    }
}
