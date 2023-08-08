package com.airportFetching.airportfetching.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@Data
@Table(name="screens")
@NoArgsConstructor
public class Screen {
    @PrePersist
    protected void onCreate(){
        this.created_at = new Date(System.currentTimeMillis());

    }

    @PreUpdate
    protected void onUpdate(){
        this.updated_at= new Date(System.currentTimeMillis());

    }
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String country;
    private Date birth_date;
    private String location;
    private String case_id;
    private String name;
    private String type;
    private String gender;
    private String nationality;
    private Date created_at;
    private Date updated_at;

    public Screen(String country,String type,String name,String case_id , String gender , Date birth_date ,String location,String nationality){
        this.nationality=nationality;
        this.country = country;
        this.type = type;
        this.name = name;
        this.case_id = case_id;
        this.gender = gender;
        this.birth_date = birth_date;
        this.location = location;
        this.nationality = nationality;
    }


}
