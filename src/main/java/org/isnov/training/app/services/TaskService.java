package org.isnov.training.app.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class TaskService {
    @Async("notificationTask")
    public void numberOfUserProperty(Integer numberUser) {
        try {
            Thread.sleep(10000);

            log.warn("Nombre de propietés : "+numberUser);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
