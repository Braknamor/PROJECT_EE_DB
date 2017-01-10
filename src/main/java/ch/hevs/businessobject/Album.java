package ch.hevs.businessobject;

import java.util.ArrayList;
import java.util.List;
 
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
 
@Entity
@Table(name="Album")
public class Album extends Music {
     
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="ReleaseYear")
    private int release_year;   
    @Column(name="picture")
    private String picture; 
 
    /********************
     *  Relations       *
     ********************/
     
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name="artist_album")
    private Artist artist;
     
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Song> songs;
     
    @ManyToOne(cascade = CascadeType.ALL)
    private Label label;
 
    /********************
     * Getter and Setter*
     ********************/
    // Id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
 
    // Name of the album
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
 
    // Artist
    public Artist getArtist() {
        return artist;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
     
    public List<Song> getSongs() {
        return songs;
    }
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
     
    public int getRelease_year() {
        return release_year;
    }
    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }
     
    public Label getLabel() {
        return label;
    }
 
    public void setLabel(Label label) {
        this.label = label;
    }
    /********************
     *      Methods     *
     ********************/
    public void addSong(Song s){
        s.addAlbum(this);
        this.songs.add(s);
    }
     
    public int getSongCount(){
        return songs.size();
    }
     
 
    /**
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }
    /**
     * @param line the picture to set
     */
    public void setPicture(String line) {
        this.picture = line;
    }
    /********************
     *  Constructors    *
     ********************/
    public Album() {
        this.songs = new ArrayList<Song>();
        }
     
    public Album(String name, int release_year) {
        this();
        this.name = name;
        this.release_year = release_year;
    }
     
     
 
}
