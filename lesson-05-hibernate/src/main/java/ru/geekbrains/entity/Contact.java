package ru.geekbrains.entity;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String contact;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    public Contact() {
    }

    public Contact(Long id, String type, String contact, User user) {
        this.id = id;
        this.type = type;
        this.contact = contact;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", contact='" + contact + '\'' +
                ", user=" + user.getUsername() +
                '}';
    }
}
