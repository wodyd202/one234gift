package com.one234gift.calllistservice.call.command.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.one234gift.calllistservice.call.command.application.model.CallEvent;
import com.one234gift.calllistservice.call.command.application.model.ChangedCallEvent;
import com.one234gift.calllistservice.call.command.application.model.RemovedCallEvent;
import com.one234gift.calllistservice.call.command.application.model.ReservatedCallEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
@AllArgsConstructor
public class CallEventListener {
    private ObjectMapper objectMapper;
    private RegisterReservationCallService registerReservationCallService;
    private ChangeReservationCallService changeReservationCallService;
    private RemoveReservationCallService removeReservationCallService;

    @KafkaListener(topics = "${call-reservation.topic}", groupId = "${call-reservation.topic}")
    protected void handle(String message) throws Exception{
        CallEvent callEvent = objectMapper.readValue(message, CallEvent.class);
        // 예약콜 생성
        if(callEvent.isRegster()){
            ReservatedCallEvent registerReservationEvent = objectMapper.readValue(message, ReservatedCallEvent.class);
            registerReservationCallService.register(registerReservationEvent);
        }
        // 예약콜 변경
        if(callEvent.isChange()){
            ChangedCallEvent changedCallEvent = objectMapper.readValue(message, ChangedCallEvent.class);
            changeReservationCallService.changeWhen(changedCallEvent);
        }
        // 예약콜 삭제
        if(callEvent.isRemove()){
            RemovedCallEvent removedCallEvent = objectMapper.readValue(message, RemovedCallEvent.class);
            removeReservationCallService.remove(removedCallEvent);
        }
    }
}
