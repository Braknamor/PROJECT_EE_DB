package ch.hevs.businessobject;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
 
import java.util.ArrayList;
import java.util.List;
 
import javax.persistence.CascadeType;
import javax.persistence.Column;
 
@Entity
@Table(name="Artist")
public class Artist extends Music{
     
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="picture")
    private String picture;
     
    /********************
     *  Relations       *
     ********************/ 
    // with table Album
    @OneToMany(mappedBy="artist", cascade = CascadeType.ALL)
    private List<Album> albums;
     
    // with table Label
    @ManyToOne
    private Label label;
     
    /********************
     * Getter and Setter*
     ********************/
    // id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
     
    // Email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
     
    // collection of albums
    public List<Album> getAlbums() {
        return albums;
    }
    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
     
    // Label
    public Label getLabel() {
        return label;
    }
    public void setLabel(Label label) {
        this.label = label;
    }
     
    /********************
     *      Methods     *
     ********************/
     
    public void addAlbum(Album a){
        a.setArtist(this);
        albums.add(a);
    }
     
    public int getAlbumCount(){
        return albums.size();
    }
     
    public int getTotalSongCount(){
        int output = 0;
        for(Album a : albums){
            output += a.getSongCount();
        }
         
        return output;
    }
     
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }
    /**
     * @param picture the picture to set
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }
    /********************
     *  Constructors    *
     ********************/
    public Artist() {
        this.albums = new ArrayList<Album>();
        }
     
    public Artist(String name, String email) {
        this();
        this.name = name;
        this.email = email;
    }   
         
 
}
