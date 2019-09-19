/*
 * JournalMessage.java
 *
 * Created on 5 juillet 2007, 12:10
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package journal;

import java.io.Serializable;
import java.util.Date;
import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * Journal message representation. It's must be a JavaBean to be encode by XMLEncoder.
 * @author lemeunie
 */
public class JournalMessage {
        
    private String body;
    private String machineName;
    private String login;
    private String applicationName;
    private Date date;
    
    public JournalMessage() {}
    
    /** Creates a new instance of JournalMessage */
    public JournalMessage(TextMessage message) {
        try {
            date = new Date(message.getJMSTimestamp());
            machineName = message.getStringProperty("machineName");
            login = message.getStringProperty("login");
            applicationName = message.getStringProperty("applicationName");
            body = message.getText();
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setBody(String body) {
        this.body =  body;
    }
    
    public String getBody() {
        return body;
    }
    
    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }
    
    public String getMachineName() {
        return machineName;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getApplicationName() {
        return applicationName;
    }
    
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public String toString() {
        String string = new String();
        string = date + " [" + machineName + "]" + " [" + login + "]";
        if (applicationName != null)
            string = string + " [" + applicationName + "]";
        string = string + " : " + body;
        return string;
    }
}
