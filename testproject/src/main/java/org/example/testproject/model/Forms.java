package org.example.testproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "forms")
public class Forms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_f")
    private Long id_f;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany (mappedBy="forms", fetch=FetchType.EAGER)
    private Set<Client> clients;

    public Long getId_f() {
        return id_f;
    }

    public void setId_f(Long id_f) {
        this.id_f = id_f; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}
