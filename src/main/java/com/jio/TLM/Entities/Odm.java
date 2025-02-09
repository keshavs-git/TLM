package com.jio.TLM.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "odms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Odm {

    @Id
    private String odm_id;

    private String odm_name;

    private String odm_description;

    @OneToMany(mappedBy= "odm", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Project> projectList = new ArrayList<>();

}
