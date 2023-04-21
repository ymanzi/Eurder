package com.switchfully.eurder.domain.classes;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
@Table(name= "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    @SequenceGenerator(sequenceName = "seq_user", allocationSize = 1, name = "seq_user")
    private int id;

    @Embedded
    private Name name;
    @Embedded
    private Contact contact;

    public User() {
    }

    public User(Name name, Contact contact) {
        this.name = name;
        this.contact = contact;
    }

    public long getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Contact getContact() {
        return contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(contact, user.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contact);
    }
}
