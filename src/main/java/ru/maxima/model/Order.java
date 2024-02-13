
package ru.maxima.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne //относится ко многим
    @JoinColumn(name = "person_id", referencedColumnName = "id") // ссылается на id
    private Person owner;

    public Order() {
    }

    public Order(Long id, String name, Person owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }


    public Order(String name, Person owner) {
        this.name = name;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

