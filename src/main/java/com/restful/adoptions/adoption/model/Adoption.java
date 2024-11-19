package com.restful.adoptions.adoption.model;

import com.restful.adoptions.pet.model.Pet;
import com.restful.adoptions.user.model.User;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name= "adoptions")
public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdoption;

    @ManyToOne
    @JoinColumn(name = "idPet")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date adoption_date = new Date();


    public Date getAdoption_date() {
        return adoption_date;
    }

    public void setAdoption_date(Date adoption_date) {
        this.adoption_date = adoption_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
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
        return Objects.equals(idAdoption, adoption.idAdoption) && Objects.equals(pet, adoption.pet) && Objects.equals(user, adoption.user) && Objects.equals(adoption_date, adoption.adoption_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdoption, pet, user, adoption_date);
    }
}
