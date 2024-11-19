package com.restful.adoptions.specie.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table( name = "species")
public class Species {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long idSpecies;

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getIdSpecies() {
        return idSpecies;
    }

    public void setIdSpecies(Long idSpecies) {
        this.idSpecies = idSpecies;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Species species = (Species) o;
        return Objects.equals(idSpecies, species.idSpecies) && Objects.equals(name, species.name) && Objects.equals(createdAt, species.createdAt) && Objects.equals(updatedAt, species.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSpecies, name, createdAt, updatedAt);
    }
}
