
package de.wacodis.messagedashboard;

import java.util.Date;

/**
 *
 * @author Matthes Rieke <m.rieke@52north.org>
 */
public class MessageContainer {

    private Date date;
    private String topic;
    private Object message;

    public MessageContainer(Date date, String topic, Object message) {
        this.date = date;
        this.topic = topic;
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
    
    
}
