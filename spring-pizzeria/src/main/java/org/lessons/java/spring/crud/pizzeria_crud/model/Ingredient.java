package org.lessons.java.spring.crud.pizzeria_crud.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name= "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "An ingredient's name cannot be null, empty or blank")
    @Size(min = 3, message = "An ingredient must have at least 3 characters")
    private String name;

    @Lob
    private String description;

    @ManyToMany (mappedBy = "ingredients")
    private Set<Pizza> pizze;

    public Set<Pizza> getPizze() {
        return this.pizze;
    }

    public void setPizze(Set<Pizza> pizze) {
        this.pizze = pizze;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
