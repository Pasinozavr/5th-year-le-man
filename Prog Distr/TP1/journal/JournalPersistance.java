/*
 * JournalPersitance.java
 *
 * Created on 5 juillet 2007, 12:15
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package journal;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author lemeunie
 */
public class JournalPersistance extends Thread {
    
    private static JournalPersistance instance = null; //l'instance unique
    private boolean stop; // indicateur pour continuer ou arr�tre ce thread
    private String name; //nom pour cr�er le fichier trace ouvert en �criture
    private XMLEncoder out; // La sortie du traceur
    private List evenements; // Liste des traces � �crire
    
    /**
     * M�thode d'acc�s � l'unique instance
     * @param name Le nom sous lequel la session est ouverte
     */
    public static JournalPersistance getInstance(String name) {
        // Si c'est le premier appel, on cr�e le thread et on le lance
        if (instance == null) {
            instance = new JournalPersistance(name);
            instance.setPriority(Thread.MIN_PRIORITY); // On prend le moins de temps possible
            instance.start();
        }
        return instance;
    }
    
    /**
     * M�thode d'acc�s � l'unique instance
     * @return L'unique instance de cette classe
     */
    public static JournalPersistance getInstance() {
        return instance;
    }
        
   /*
    * (non-Javadoc)
    * @see java.lang.Runnable#run()
    */
    public void run() {
        // D�but de la session
        ouverture();
        
        // Session en cours
        while (!stop) {
            ecriture();
            try { Thread.sleep(5); } catch(InterruptedException e) { return ;}
        }
        
        // Fin de la session
        fermeture();
    }
    
    /**
     * Demande d'arr�t du traceur
     */
    public synchronized void arret() {
        stop = true;
    }
    
    /**
     * Ecriture d'une trace donn�e dans le fichier de trace.
     * @param objet L'objet � ajouter dans la trace
     */
    public void tracer(Object objet) {        
        synchronized(evenements) {
            try { evenements.wait(); } catch (InterruptedException e) { e.printStackTrace(); }
            evenements.add(objet);
        }
    }
    
    /**
     * Construction priv� du traceur.<br>
     * Il n'y a qu'un traceur par application.
     * @param name Le nom sous lequel la session a �t� ouverte
     */
    private JournalPersistance(String name) {
        this.name = new String(name);
        evenements = new LinkedList();
        stop = false;
    }
    
    /**
     * Ecriture dans le fichier de trace.
     */
    private void ecriture() {
        synchronized(evenements) {
            if (!evenements.isEmpty()) {
                out.writeObject(evenements.remove(0));
                out.flush();
            }
            evenements.notify();
        }
    }
    
    /**
     * Ouverture du fichier en �criture. Il faut regarder s'il n'existe pas d�j�.
     * S'il existe, il faut modifier le nom donn�.
     */
    private void ouverture() {
        String filename = new String(name);
        File f = new File(filename + ".xgz");
        int i = 1;
        
        while (f.exists()) {
            filename = name + " (" + (i++) + ")";
            f = new File(filename + ".xgz");
        }
        
        try {
            // Ouverture du fichier XML
            out = new XMLEncoder(new GZIPOutputStream(new FileOutputStream(filename + ".xgz")));            
            
        } catch (IOException e) { e.printStackTrace(); }
    }
    
    /**
     * Fermeture du fichier XML de trace.
     */
    private void fermeture() {
        out.flush();
        out.close();
    }    

}

