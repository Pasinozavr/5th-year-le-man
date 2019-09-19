/*
 * JournalApp.java
 *
 * Created on 5 juillet 2007, 09:01
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package journal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.jms.ConnectionFactory;
import javax.naming.NamingException;

/**
 * Controler Application Singleton Class
 * @author lemeunie
 */
public class JournalApp {
    
    private static JournalApp instance = null;
    
    private static String topic_name = null;
    private static String subscriber_name = null;
    
    /**
     * Creates the unique instance of JournalApp
     */
    public static JournalApp createInstance(String topic_name, String subscriber_name) {
        return new JournalApp(topic_name, subscriber_name);
    }
    
    /** Return the unique instance of the JournalApp class */
    public static JournalApp getInstance() {
        return instance;
    }
    
    /** Creates a new instance of JournalApp */
    protected JournalApp(String topic_name, String subscriber_name) {
        JournalApp.topic_name =  topic_name;
        JournalApp.subscriber_name = subscriber_name;
    }
    
    public static void main(String[] args) {
        Context context = null;
        String factoryName = "ConnectionFactory";
        ConnectionFactory factory = null;
        Topic topic = null;
        Connection connection = null;
        Session session = null;
        TopicSubscriber subscriber = null;
        
        if (args.length != 2) {
            System.out.println("usage: JournalApp <topic_name> <subscriber_name>");
            System.exit(1);
        }
        
        topic_name = args[0];
        subscriber_name = args[1];
        
        try {
            // create the JNDI initial context
            context = new InitialContext();
            
            // look up the ConnectionFactory
            factory = (ConnectionFactory) context.lookup(factoryName);
            
            // look up the Desination
            topic = (Topic) context.lookup(topic_name);
            
            // create the connection
            connection = factory.createConnection();
            
            // create the session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
            // create the durable subscriber
            subscriber = session.createDurableSubscriber(topic, subscriber_name);
            
            // register a listener
            subscriber.setMessageListener(new JournalListener());
            
            // create the journal persistance
            JournalPersistance.getInstance("save-" + new java.util.GregorianCalendar().getTimeInMillis());
            
            // start the connection, to enable message receipt
            connection.start();
            
            System.out.println("Press [return] to stop]");
            BufferedReader waiter = new BufferedReader(new InputStreamReader(System.in));
            
            waiter.readLine();

			// stop the connection before closing
			connection.stop();
            
            // stop journal persistance
            JournalPersistance.getInstance().arret();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        } finally {
            // close the context
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException exception) {
                    exception.printStackTrace();
                }
            }
            
            // close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
    
}
