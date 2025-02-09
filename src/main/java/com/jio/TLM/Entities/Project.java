package com.jio.TLM.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    private String project_id;

    private String project_name;

    private String project_description;

    @ManyToOne
    @JoinColumn(name = "odm_id")
    private Odm odm;

    @ManyToMany
    @JoinTable(name = "project_part")
    private List<Part> allParts = new ArrayList<>();


}
