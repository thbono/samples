package com.thbono.jms.app;

import java.io.Serializable;
import java.util.Date;

public class Notification implements Serializable {

    private String message;
    private Date sent;
    private Date delivered;

    public Notification(String message, Date sent) {
        this.message = message;
        this.sent = sent;
    }

    public void setDelivered(Date delivered) {
        this.delivered = delivered;
    }

    public String getMessage() {
        return message;
    }

    public Date getSent() {
        return sent;
    }

    public Date getDelivered() {
        return delivered;
    }

}
