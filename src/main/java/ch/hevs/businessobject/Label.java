package ch.hevs.businessobject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Label")
public class Label extends Music{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="location")
	private String location;
	
	/********************
	 * 	Relations		*
	 ********************/
	
	@OneToMany(mappedBy="label", cascade = CascadeType.ALL)
	private List<Album> albums;
	
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
	
	// Name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// Location
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	// collection of artists
	public List<Album> getAlbums() {
		return albums;
	}
	public void setArtists(List<Album> albums) {
		this.albums = albums;
	}
	
	/********************
	 * 		Methods		*
	 ********************/
	public void addAlbum(Album a){
		a.setLabel(this);
		albums.add(a);
	}
	
	/********************
	 * 	Constructors	*
	 ********************/
	public Label() {
		this.albums = new ArrayList<Album>();
		}
	
	public Label(String name, String location) {
		this();
		this.name = name;
		this.location = location;
	}
	
	

}
