package com.example.Agent_MongoDB.Repository;

import com.example.Agent_MongoDB.Entity.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Yassine Deriouch
 * @project Agent_MongoDB
 */
@Repository
public interface DepartmentRepository extends MongoRepository<Department, Integer> {
}
