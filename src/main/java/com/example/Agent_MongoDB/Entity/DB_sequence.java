package com.example.Agent_MongoDB.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Yassine Deriouch
 * @project Agent_MongoDB
 */

@Document(collection = "database_sequences")
@Data
public class DB_sequence {
        @Id
        private String id;
        private int seq;
}
