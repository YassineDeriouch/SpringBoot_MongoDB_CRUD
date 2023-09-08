package com.example.Agent_MongoDB.Repository;

import com.example.Agent_MongoDB.Entity.Agent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yassine Deriouch
 * @project Agent_MongoDB
 */
@Repository
public interface AgentRepository extends MongoRepository<Agent, Integer> {
}
