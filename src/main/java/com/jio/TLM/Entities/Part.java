package com.jio.TLM.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Part {

    @Id
    private int part_id;

    private String part_name;

    private String part_description;

    @ManyToMany(mappedBy = "allParts")
    private List<Project> associatedProjects = new ArrayList<>();

}
