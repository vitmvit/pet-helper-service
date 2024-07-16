package by.vitikova.discovery.scheduler;

import by.vitikova.discovery.EventDto;
import by.vitikova.discovery.service.EventService;
import by.vitikova.discovery.update.EventUpdateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static by.vitikova.discovery.constant.Constant.DEFAULT_COLOR;

@Component
public class DailyScheduler {

    private final EventService eventService;
    private static final Logger log = LoggerFactory.getLogger(DailyScheduler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    @Autowired
    public DailyScheduler(EventService eventService) {
        this.eventService = eventService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduleTask() {
        log.info("DailyScheduler {}", dateFormat.format(new Date()));
        var eventDtoList = eventService.findAll();
        for (EventDto item : eventDtoList) {
            if (item.getDateCreated().isBefore(LocalDateTime.now())) {
                if (!item.getDateCreated().toLocalDate().isEqual(LocalDate.now())) {
                    var event = new EventUpdateDto();
                    event.setId(item.getId());
                    event.setDictionaryId(item.getDictionaryId());
                    event.setTextColor(DEFAULT_COLOR);
                    event.setDescription(item.getDescription());
                    event.setDateCreated(item.getDateCreated());
                    eventService.update(event);
                }
            }
        }
    }
}
