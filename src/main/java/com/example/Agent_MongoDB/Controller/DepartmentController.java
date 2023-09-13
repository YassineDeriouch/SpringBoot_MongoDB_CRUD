package com.example.Agent_MongoDB.Controller;

import com.example.Agent_MongoDB.Entity.Agent;
import com.example.Agent_MongoDB.Entity.Department;
import com.example.Agent_MongoDB.Service.DepartmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yassine Deriouch
 * @project Agent_MongoDB
 */
@RestController
@RequestMapping("Department")
@CrossOrigin(value = "*")
@Tag(name = "Department", description = "Departments system")
@Data
public class DepartmentController {

    @Autowired private DepartmentService departmentService;

    @PostMapping("/save")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) throws Exception {
        return new ResponseEntity<>(departmentService.saveDepartment(department), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/{idDepartment}/add/agents")
    public ResponseEntity<Department> addAgentsToDepartment(@PathVariable int idDepartment, @RequestBody List<Agent> agent) throws Exception {
        try {
            return new ResponseEntity<>(departmentService.addAgentToDepartment(idDepartment,agent),HttpStatus.OK);
        } catch (Exception e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("get/all")
    public ResponseEntity<List<Department>> saveDepartment() throws Exception {
        try {
            return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatusCode.valueOf(200));
        }catch (Exception exception){
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
