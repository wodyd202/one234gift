package com.one234gift.saleshistoryservice.salesHistory.command.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.one234gift.saleshistoryservice.salesHistory.domain.event.AbstractCallReservationEvent;
import com.one234gift.saleshistoryservice.salesHistory.domain.event.CallReservedEvent;
import com.one234gift.saleshistoryservice.salesHistory.domain.event.ChangedCallReservation;
import com.one234gift.saleshistoryservice.salesHistory.domain.event.RemovedCallReservationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Async("kafkaExecutor")
@Slf4j
@Profile("!test")
@Component
public class SalesHistoryEventListener {
    @Value("${call-reservation.topic}")
    private String TOPIC;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * @param event
     * # 예약콜 등록 이벤트 리스너
     */
    @EventListener
    protected void handle(CallReservedEvent event) throws Exception {
        sendEvent(event);
    }

    /**
     * @param event
     * # 예약콜 변경 이벤트 리스너
     */
    @EventListener
    protected void handle(ChangedCallReservation event) throws Exception {
        sendEvent(event);
    }

    /**
     * @param event
     * # 예약콜 삭제 이벤트 리스너
     */
    @EventListener
    protected void handle(RemovedCallReservationEvent event) throws Exception {
        sendEvent(event);
    }

    private void sendEvent(AbstractCallReservationEvent event) throws Exception{
        String eventInfo = objectMapper.writeValueAsString(event);

        log.info("send event {} to topic {}", eventInfo, TOPIC);
        kafkaTemplate.send(TOPIC, eventInfo);
    }
}
