package pv826.entities;


import javax.persistence.*;

@Entity
@Table(name = "contactinfo")
public class ContactInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "street", nullable = true, insertable = true, updatable = true)
    private String street;

    @Column(name = "city", nullable = true, insertable = true, updatable = true)
    private String city;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    public ContactInfo() {
    }

    public ContactInfo(String street, String city) {
        this.street = street;
        this.city = city;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
