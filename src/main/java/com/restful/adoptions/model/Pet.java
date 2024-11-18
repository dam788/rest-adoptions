package com.restful.adoptions.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name= "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPet;

    private String name;
    private String description;
    private Integer birthday;
    private Double weight;
    private String size;
    private String gender;
    private Boolean active;
    private Boolean available;
    private String avatarUrl;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    @OneToOne
    @JoinColumn(name = "idSpecies")
    private Species species;

    @OneToOne
    @JoinColumn( name = "idLocation" )
    private Location location;

    @OneToMany
    private List<PetImage> petImages;

    public Long getIdPet() {
        return idPet;
    }

    public void setIdPet(Long idPet) {
        this.idPet = idPet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<PetImage> getPetImages() {
        return petImages;
    }

    public void setPetImages(List<PetImage> petImages) {
        this.petImages = petImages;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(idPet, pet.idPet) && Objects.equals(name, pet.name) && Objects.equals(description, pet.description) && Objects.equals(birthday, pet.birthday) && Objects.equals(weight, pet.weight) && Objects.equals(size, pet.size) && Objects.equals(gender, pet.gender) && Objects.equals(active, pet.active) && Objects.equals(available, pet.available) && Objects.equals(avatarUrl, pet.avatarUrl) && Objects.equals(createdAt, pet.createdAt) && Objects.equals(updatedAt, pet.updatedAt) && Objects.equals(species, pet.species) && Objects.equals(location, pet.location) && Objects.equals(petImages, pet.petImages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPet, name, description, birthday, weight, size, gender, active, available, avatarUrl, createdAt, updatedAt, species, location, petImages);
    }
}
