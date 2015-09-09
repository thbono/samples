package com.thbono.jmstest.view;

import com.thbono.jms.app.Notification;
import com.thbono.jms.app.NotificationRepository;
import com.thbono.jms.NotificationSender;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class HelloBean {

    @Inject
    private NotificationSender notificationSender;

    @Inject
    private NotificationRepository notificationRepository;

    public String getMessage() {
        return "Type a message here";
    }

    public void setMessage(String message) {
        new Thread(new NotificationRunnable(message)).start();
    }

    public List<Notification> getNotifications() {
        return notificationRepository.getNotifications();
    }

    public int getTotalSent() {
        return notificationRepository.getSent();
    }

    public int getTotalDelivered() {
        return notificationRepository.getDelivered();
    }

    public boolean isFinished() {
        return notificationRepository.getDelivered() > 0
                && notificationRepository.getDelivered() == notificationRepository.getSent();
    }

    private class NotificationRunnable implements Runnable {

        private String message;

        public NotificationRunnable(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 100; i++) {
                notificationSender.sendMessage(String.format("%s (%d)", message, i));
            }
        }
    }

}


