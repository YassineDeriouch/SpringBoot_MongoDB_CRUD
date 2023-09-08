    package com.example.Agent_MongoDB.Entity;

    import lombok.Data;
    import org.bson.types.ObjectId;
    import org.springframework.data.annotation.Id;
    import org.springframework.data.annotation.Transient;
    import org.springframework.data.mongodb.core.mapping.Document;

    @Document
    @Data
    public class Agent {
        @Transient public static final String SEQUENCE_NAME = "users_sequence";

        @Id
        private int idAgent;
        private String nom;
        private String prenom;
        private String email;
        private String adresse;
        private String telephone;

    }
