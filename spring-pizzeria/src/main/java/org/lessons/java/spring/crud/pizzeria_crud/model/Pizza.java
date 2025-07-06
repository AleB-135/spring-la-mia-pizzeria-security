package org.lessons.java.spring.crud.pizzeria_crud.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "pizze")
public class Pizza implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "pizza_name", nullable = false)

    @NotBlank(message = "Name cannot be null, blank or empty")
    private String name;

    @NotBlank(message = "Description cannot be null, blank or empty")
    @Size(max = 2000, message = "Description cannot be longer than 2000 characters")
    private String description;

    // @Lob = Long Object
    @Lob
    @NotBlank(message = "Image Url must not be null, blank or empty")
    private String imgUrl;

    @Min(value = 1)
    private float price;

    public Pizza (){};


    public List<Offers> getOffers() {
        return this.offers;
    }

    public void setofferss(List<Offers> offers) {
        this.offers= offers;
    }
    

    @OneToMany (mappedBy = "pizza")
    private List<Offers> offers;

    @ManyToMany
    @JoinTable(
        name = "ingredient_pizza",
        joinColumns = @JoinColumn(name = "pizza_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients;

    public Set<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
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

    public void setNome(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return this.name + " " + this.description + " " + this.price;
    }

}
