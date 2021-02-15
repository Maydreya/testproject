package org.example.testproject.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_c")
    private Long id_c;

    @Column(name="name")
    private String name;

    @Column(name="short_title")
    private String short_title;

    @Column(name="address")
    private String address;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_f")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne ()
    @JoinColumn (name="forms_id")
    private Forms forms;

    @JsonIgnore
    @OneToMany (mappedBy="client", fetch=FetchType.EAGER)
    private Set<Contribution> contributions;

    public Long getId_c() {
        return id_c;
    }

    public void setId_c(Long id_c) {
        this.id_c = id_c;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_title() {
        return short_title;
    }

    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Forms getForms() {
        return forms;
    }

    public void setForms(Forms forms) {
        this.forms = forms;
    }

    public Set<Contribution> getContributions() {
        return contributions;
    }

    public void setContributions(Set<Contribution> contributions) {
        this.contributions = contributions;
    }
}
