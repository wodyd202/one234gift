package com.one234gift.calllistservice.call.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.one234gift.calllistservice.call.application.model.CallEvent;
import com.one234gift.calllistservice.call.application.model.ChangedCallEvent;
import com.one234gift.calllistservice.call.application.model.RemovedCallEvent;
import com.one234gift.calllistservice.call.application.model.ReservatedCallEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
@AllArgsConstructor
public class CallEventListener {
    private ObjectMapper objectMapper;
    private ReservationCallService reservationCallService;

    @KafkaListener(topics = "${call-reservation.topic}", groupId = "${call-reservation.topic}")
    protected void handle(String message) throws Exception{
        CallEvent callEvent = objectMapper.readValue(message, CallEvent.class);
        // 예약콜 생성
        if(callEvent.isRegster()){
            ReservatedCallEvent registerReservationEvent = objectMapper.readValue(message, ReservatedCallEvent.class);
            reservationCallService.register(registerReservationEvent);
        }
        // 예약콜 변경
        if(callEvent.isChange()){
            ChangedCallEvent changedCallEvent = objectMapper.readValue(message, ChangedCallEvent.class);
            reservationCallService.changeWhen(changedCallEvent);
        }
        // 예약콜 삭제
        if(callEvent.isRemove()){
            RemovedCallEvent removedCallEvent = objectMapper.readValue(message, RemovedCallEvent.class);
            reservationCallService.remove(removedCallEvent);
        }
    }
}
