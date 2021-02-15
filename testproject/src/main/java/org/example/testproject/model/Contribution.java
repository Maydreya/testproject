package org.example.testproject.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contributions")
public class Contribution {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_con")
    private Long id_con;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_b")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne()
    @JoinColumn(name="bank_id")
    private Bank bank;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_c")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne()
    @JoinColumn(name="client_id")
    private Client client;

    @Column(name="date_open")
    private LocalDate date_open;

    @Column(name="percent")
    private Double percent;

    @Column(name="term_in_mouth")
    private Integer term_in_mouth;

    public Long getId_con() {
        return id_con;
    }

    public void setId_con(Long id_con) {
        this.id_con = id_con;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getDate_open() {
        return date_open;
    }

    public void setDate_open(LocalDate date_open) {
        this.date_open = date_open;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Integer getTerm_in_mouth() {
        return term_in_mouth;
    }

    public void setTerm_in_mouth(Integer term_in_mouth) {
        this.term_in_mouth = term_in_mouth;
    }
}
