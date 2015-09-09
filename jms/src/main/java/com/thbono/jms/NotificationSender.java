package com.thbono.jms;

import com.thbono.jms.app.Notification;
import com.thbono.jms.app.NotificationRepository;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import java.util.Calendar;

@Stateless
public class NotificationSender {

    @Inject
    private JMSContext context;

    @Resource(mappedName = "java:/jms/queue/NotificacaoQueue")
    private Queue notificacaoQueue;

    @Inject
    private NotificationRepository notificationRepository;

    public void sendMessage(String message) {
        context.createProducer().send(notificacaoQueue, new Notification(message, Calendar.getInstance().getTime()));
        notificationRepository.registerSent();
    }

}
