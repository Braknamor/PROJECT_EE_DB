package ch.hevs.test;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

import ch.hevs.businessobject.*;

public class PersistenceTest {

	@Test
	public void test() {
		EntityTransaction tx = null;
		try {
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankPU");
			EntityManager em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			HashMap<String, Artist> artists = new HashMap<String, Artist>();
			HashMap<String, Label> labels = new HashMap<String, Label>();
			HashMap<String, Album> albums = new HashMap<String, Album>();
			HashMap<String, Song> songs = new HashMap<String, Song>();
			
			
			// Load data from csv file
			BufferedReader br = new BufferedReader(new FileReader("music.csv"));
			String line_feed;
			while((line_feed = br.readLine()) != null){
				String[] line = line_feed.split(",");
				
				//create objects
				Song s;
				Album a;
				Artist ar;
				Label l;
				
				
				//check if song exists
				if(songs.containsKey(line[0])){
					s = (Song)songs.get(line[0]);
				}else{
					s = new Song(line[0], line[5]);
					songs.put(line[0], s);
				}
				
				//check if album exists
				if(albums.containsKey(line[2])){
					((Album)albums.get(line[2])).addSong(s);
					a = (Album)albums.get(line[2]);
					albums.replace(line[2], a);
				}else{
					a = new Album(line[2], Integer.parseInt(line[4]));
					a.addSong(s);
					a.setPicture(line[6]);
					albums.put(line[2], a);
				}
				
				//song exists for sure
				songs.replace(line[0], s);
				
				//check if label exists
				if(labels.containsKey(line[3])){
					((Label)labels.get(line[3])).addAlbum(a);
					l = ((Label)labels.get(line[3]));
				}else{	
					l = new Label(line[3], null);
				}
				
				l.addAlbum(a);
				labels.put(line[3], l);
				
				//check if artist exists
				if(artists.containsKey(line[1])){
					((Artist)artists.get(line[1])).addAlbum(a);
					ar = (Artist)artists.get(line[1]);
				}else{
					ar = new Artist(line[1], null);
					ar.addAlbum(a);
					ar.setPicture(line[7]);
					artists.put(line[1], ar);
				}
			}
			
			Iterator it = artists.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        em.persist((Artist)pair.getValue());
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		    
			
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();

			try {
				tx.rollback();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (Exception e1){
				e1.printStackTrace();
			}

		}

	}
}
