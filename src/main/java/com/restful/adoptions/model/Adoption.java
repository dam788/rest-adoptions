package com.restful.adoptions.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name= "adoptions")
public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdoption;

    // falta relaciones





    @Temporal(TemporalType.TIMESTAMP)
    private Date adoption_date = new Date();

    public Date getAdoption_date() {
        return adoption_date;
    }

    public void setAdoption_date(Date adoption_date) {
        this.adoption_date = adoption_date;
    }

    public Long getIdAdoption() {
        return idAdoption;
    }

    public void setIdAdoption(Long idAdoption) {
        this.idAdoption = idAdoption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adoption adoption = (Adoption) o;
        return Objects.equals(idAdoption, adoption.idAdoption) && Objects.equals(adoption_date, adoption.adoption_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdoption, adoption_date);
    }
}
