package ch.hevs.businessobject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Song")
public class Song extends Music{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="style")
	private String style;
	
	/********************
	 * 	Relations		*
	 ********************/
	
	@ManyToMany(mappedBy="songs", cascade = CascadeType.ALL)
	private List<Album> albums;
	
	// relation song-artist (featuring)
	
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
	
	// Name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// Style
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}

	// collection of albums
	public List<Album> getAlbums() {
		return albums;
	}
	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
	
	/********************
	 * 		Methods		*
	 ********************/
	public void addAlbum(Album a){
		albums.add(a);
	}
	
	
	/********************
	 * 	Constructors	*
	 ********************/
	public Song () {
		this.albums = new ArrayList<Album>();
		}
	
	public Song(String name, String style) {
		this();
		this.name = name;
		this.style = style;
	}
	
}
