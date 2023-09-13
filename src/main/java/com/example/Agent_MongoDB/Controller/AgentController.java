package com.example.Agent_MongoDB.Controller;

import com.example.Agent_MongoDB.Entity.Agent;
import com.example.Agent_MongoDB.Service.AgentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("Agents")
@CrossOrigin(value = "*")
@Tag(name = "Agent", description = "Gestion des Agents")
@Data
public class AgentController {

    @Autowired private AgentService agentService;
   
    @PostMapping("/save")
    public ResponseEntity<Agent> SaveAgent(@RequestBody Agent agent) {
        try {
            return new ResponseEntity<>(agentService.saveAgent(agent), HttpStatus.OK);
        }catch (IllegalArgumentException exception){
            exception.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le numéro de téléphone est déjà affécté à un agent");
        }
    }
    
    @PutMapping("/update/{idAgent}")
    public ResponseEntity<Agent> updateAgent(@PathVariable int idAgent, @RequestBody Agent agent) {
        try {
            return new ResponseEntity<>(agentService.updateAgent(idAgent, agent), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //return new ResponseEntity<>(agentService.updateAgent(idAgent, agent), HttpStatus.OK);

    }
    
    @GetMapping("/get/all")
    public ResponseEntity<Iterable<Agent>> getAllAgents() {
        try {
            return new ResponseEntity<>(agentService.getAllAgents(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

   /*@GetMapping("/get/all/pagination")
   public ResponseEntity<Page<Agent>> getAllAgentsPaging(@RequestParam int nextPage, @RequestParam int pageSize) {
       try {
           Page<Agent> agentPage = agentService.getAllAgentsByPaging(nextPage, pageSize);
           return new ResponseEntity<>(agentPage, HttpStatus.OK);
       } catch (Exception e) {
               e.printStackTrace();
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }catch (Exception e) {
               e.printStackTrace();
               return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
           }
   }*/

    @GetMapping("/get/all/pagination")
    public ResponseEntity<List<Agent>> getAllAgents(@RequestParam int nextPage, @RequestParam int pageSize) {
        try {
            List<Agent> agents = agentService.getAllAgentsByPaging(nextPage, pageSize);
            return new ResponseEntity<>(agents, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/get/id")
    public ResponseEntity<Agent> getAgentById(@RequestParam int idAgent) {
        try {
            return new ResponseEntity<>(agentService.getAgentByID(idAgent), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/delete/id")
    public ResponseEntity<Agent> deleteAgent(@RequestParam int idAgent) {
        try {
            return new ResponseEntity<>(agentService.deleteAgentByID(idAgent), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/all")
    public ResponseEntity<List<Agent>> deleteAllAgents() {
        try {
            List<Agent> agent = agentService.deleteAllAgents();
            return new ResponseEntity<>(agent, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
