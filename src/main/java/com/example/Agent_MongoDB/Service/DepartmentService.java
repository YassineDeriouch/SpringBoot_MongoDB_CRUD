package com.example.Agent_MongoDB.Service;

import com.example.Agent_MongoDB.Entity.Agent;
import com.example.Agent_MongoDB.Entity.Department;
import com.example.Agent_MongoDB.Repository.DepartmentRepository;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Yassine Deriouch
 * @project Agent_MongoDB
 */
@Data
@Service
public class DepartmentService {
    @Autowired private DepartmentRepository departmentRepository;
    @Autowired private ModelMapper modelMapper;
    @Autowired private SequenceGeneratorService sequenceGeneratorService;

    @Transactional
    public Department saveDepartment(Department department) {
        department.setIdDepartment(sequenceGeneratorService.generateSequence(Department.SEQUENCE_NAME));
        department.setDepartment_name(department.getDepartment_name());
        department.setAgents(department.getAgents());
        Department savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment, Department.class);
    }

    @Transactional
    public Department addAgentToDepartment(int idDepartment,List<Agent> agentsList) throws Exception {
        Optional<Department> departmentOptional = departmentRepository.findById(idDepartment);
        if(departmentOptional.isPresent()) {
            Department department = departmentOptional.get();
            department.setAgents(agentsList);
            System.out.println(department);
            Department savedDepartment = departmentRepository.save(department);
            return modelMapper.map(savedDepartment,Department.class);
        }else
            throw new Exception();
    }

    @PostConstruct
    @Transactional
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll().stream().map(department -> modelMapper.map(department, Department.class))
                .collect(Collectors.toList());
    }
}
