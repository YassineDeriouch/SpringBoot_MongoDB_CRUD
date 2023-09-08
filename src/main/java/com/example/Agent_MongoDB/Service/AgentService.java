package com.example.Agent_MongoDB.Service;

import com.example.Agent_MongoDB.Entity.Agent;
import com.example.Agent_MongoDB.Repository.AgentRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgentService {

    @Autowired private AgentRepository agentRepository;
    @Autowired private ModelMapper modelMapper;

    @Autowired private SequenceGeneratorService sequenceGeneratorService;
    @Transactional
    public Agent saveAgent(Agent agent) {
        /*if(isTelephoneExists(agent.getTelephone())){
            throw new IllegalArgumentException("telephone: "+agent.getTelephone()+"already exist");
        }*/
            agent.setIdAgent(sequenceGeneratorService.generateSequence(Agent.SEQUENCE_NAME));
            agent.setNom(agent.getNom());
            agent.setPrenom(agent.getPrenom());
            agent.setEmail(agent.getEmail());
            agent.setAdresse(agent.getAdresse());
            agent.setTelephone(agent.getTelephone());
            Agent savedAgent = agentRepository.save(agent);
            return modelMapper.map(savedAgent, Agent.class);
    }
    public boolean isTelephoneExists(String numTel) {
        List<Agent> agents = agentRepository.findAll();
        for (Agent agentList : agents) {
            if (agentList.getTelephone().equals(numTel)) {
                System.out.println("existing N° telephone " + numTel);
                return true;
            }
        }
        return false;
    }

    @Transactional
    public Agent updateAgent(int id, Agent agentEntity) throws Exception {

        Optional<Agent> agentOptional = agentRepository.findById(id);
        if(agentOptional.isPresent()){
            Agent agent = modelMapper.map(agentEntity, Agent.class);

            agent.setIdAgent(id);
            Agent updated = agentRepository.save(agent);
            return modelMapper.map(updated, Agent.class);
        } else {
            System.out.println("introuvable");
            throw new Exception("Agent not found !!");
        }
    }

    @Transactional
    public Agent getAgentByID(int id) throws Exception {
        Optional<Agent> agent = agentRepository.findById(id);
        if (agent.isPresent())
            return modelMapper.map(agent, Agent.class);
        else
            throw new Exception("Agent not found");
    }


    @PostConstruct
    @Transactional
    public List<Agent> getAllAgents() {
        return agentRepository.findAll().stream().map(agent -> modelMapper.map(agent, Agent.class))
                .collect(Collectors.toList());
    }

    /*      // RETURN PAGE OF OBJECTS
    @Transactional
    public Page<Agent> getAllAgentsByPaging(int nextPage, int pageSize) {
        Page<Agent> agentPage = agentRepository.findAll(PageRequest.of(nextPage, pageSize));
        return agentPage.map(agent -> modelMapper.map(agent, Agent.class));
    }*/
    @Transactional
    public List<Agent> getAllAgentsByPaging(int nextPage, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "idAgent");
        Page<Agent> agentPage = agentRepository.findAll(PageRequest.of(nextPage, pageSize,sort));
        List<Agent> agentList = agentPage.getContent();
//        return agentPage.map(agent -> modelMapper.map(agent, Agent.class));
        return agentList.stream().map(agent -> modelMapper.map(agent, Agent.class))
                .collect(Collectors.toList());
    }


    @Transactional
    public Agent deleteAgentByID(int id) throws Exception {
        Optional<Agent> agent = agentRepository.findById(id);
        if (agent.isPresent()) {
            agentRepository.deleteById(id);
            return modelMapper.map(agent, Agent.class);
        } else
            throw new Exception("Agent id= " + id + " not found");
    }


    @Transactional
    public List<Agent> deleteAllAgents() throws Exception {
        List<Agent> agents = agentRepository.findAll();
        if (!agents.isEmpty()) {
            agentRepository.deleteAll();
            return agents.stream().map(element -> modelMapper.map(element, Agent.class))
                    .collect(Collectors.toList());
        } else {
            throw new Exception("NO agent has been found !");
        }
    }

}
