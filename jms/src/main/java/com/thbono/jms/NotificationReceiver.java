package com.thbono.jms;

import com.thbono.jms.app.Notification;
import com.thbono.jms.app.NotificationRepository;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.Calendar;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup",
        propertyValue = "java:/jms/queue/NotificacaoQueue"),
    @ActivationConfigProperty(propertyName = "destinationType",
        propertyValue = "javax.jms.Queue"),
})
public class NotificationReceiver implements MessageListener {

    @Inject
    private NotificationRepository notificationRepository;

    @Override
    public void onMessage(Message message) {
        try {
            Notification notification = message.getBody(Notification.class);
            notification.setDelivered(Calendar.getInstance().getTime());
            notificationRepository.registerDelivery(notification);
            Thread.sleep(500);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
