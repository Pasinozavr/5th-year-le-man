/*
 * JournalListener.java
 *
 * Created on 5 juillet 2007, 10:13
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package journal;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import journal.JournalPersistance;

/**
 *
 * @author lemeunie
 */
public class JournalListener implements MessageListener {
    
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {                        
            TextMessage messageText = (TextMessage) message;            
            JournalMessage jMessage = new JournalMessage(messageText);            
            System.out.println(jMessage);
            JournalPersistance.getInstance().tracer(jMessage);
        }
    }
    
}
