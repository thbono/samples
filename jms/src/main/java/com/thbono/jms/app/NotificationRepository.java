package com.thbono.jms.app;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Singleton
public class NotificationRepository {

    private int sent;

    private int delivered;

    private List<Notification> notifications;

    @PostConstruct
    public void postConstruct() {
        notifications = new ArrayList<>();
        sent = 0;
        delivered = 0;
    }

    public void registerDelivery(Notification notification) {
        notifications.add(notification);
        delivered++;
    }

    public void registerSent() {
        sent++;
    }

    public int getSent() {
        return sent;
    }

    public int getDelivered() {
        return delivered;
    }

    public List<Notification> getNotifications() {
        return Collections.unmodifiableList(notifications);
    }

}
