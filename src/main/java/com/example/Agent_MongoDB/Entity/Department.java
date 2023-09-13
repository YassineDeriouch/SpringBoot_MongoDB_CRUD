package com.example.Agent_MongoDB.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author Yassine Deriouch
 * @project Agent_MongoDB
 */
@Document
@Data
public class Department {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private int idDepartment;
    private String department_name;
    private List<Agent> agents;
}
