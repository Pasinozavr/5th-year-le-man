/*
 * JournalTest.java
 *
 * Created on 5 juillet 2007, 09:02
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package journaltest;

import java.util.Random;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author lemeunie
 */
public class JournalTest {
    
    /** Creates a new instance of JournalTest */
    public JournalTest() {
    }
    
    public static void main(String[] args) {
        Context context = null;
        ConnectionFactory factory = null;
        Connection connection = null;
        String factoryName = "ConnectionFactory";
        Destination dest = null;
        Session session = null;
        MessageProducer sender = null;
        
        String machineName = null;
        String login = null;
        String applicationName = null;
        String destName = null;
        
        String messageText = null;
        
        if (args.length < 1 || args.length > 4) {
            System.out.println("usage: JournalTest <machine_name> <login> <application_name> <destination>");
            System.exit(1);
        }
        
        machineName = args[0];
        login = args[1];
        applicationName = args[2];
        destName = args[3];
        
        try {
            // create the JNDI initial context.
            context = new InitialContext();
            
            // look up the ConnectionFactory
            factory = (ConnectionFactory) context.lookup(factoryName);
            
            // look up the Destination
            dest = (Destination) context.lookup(destName);
            
            // create the connection
            connection = factory.createConnection();
            
            // create the session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
            // create the sender
            sender = session.createProducer(dest);
            
            // start the connection, to enable message sends
            connection.start();
            
            // Evenement <login> sur la machine
            TextMessage message = session.createTextMessage();
            message.setText("Login");
            message.setStringProperty("machineName", machineName);
            message.setStringProperty("login", login);
            sender.send(message);
            
            Random rand = new java.util.Random();
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            
            // Evenement <lancement de l'application>
            message = session.createTextMessage();
            message.setText("Lancement");
            message.setStringProperty("machineName", machineName);
            message.setStringProperty("login", login);
            message.setStringProperty("applicationName", applicationName);
            sender.send(message);
            
            try {
                Thread.sleep(rand.nextInt(5000));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            
            // Evenement <erreur> dans l'application
            message = session.createTextMessage();
            message.setText("Erreur : fichier toto.xml non trouve");
            message.setStringProperty("machineName", machineName);
            message.setStringProperty("login", login);
            message.setStringProperty("applicationName", applicationName);
            sender.send(message);
            
            try {
                Thread.sleep(rand.nextInt(100));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            
            // Evenement <arret> ou <arret anormal> de l'application
            message = session.createTextMessage();
            if (rand.nextInt(100) < 50)
                message.setText("Arret anormal");
            else
                message.setText("Arret normal");
            message.setStringProperty("machineName", machineName);
            message.setStringProperty("login", login);
            message.setStringProperty("applicationName", applicationName);
            sender.send(message);
            
            try {
                Thread.sleep(rand.nextInt(5000));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            
            // Evenement <logout> sur la machine
            message = session.createTextMessage();
            message.setText("Logout");
            message.setStringProperty("machineName", machineName);
            message.setStringProperty("login", login);
            sender.send(message);
            
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