package org.example.testproject.controller;

import org.example.testproject.exception.ResourceNotFoundException;
import org.example.testproject.model.Client;
import org.example.testproject.model.Forms;
import org.example.testproject.repos.ClientRepos;
import org.example.testproject.repos.FormRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientRepos clientRepos;

    private final FormRepos formRepos;

    @Autowired
    public ClientController(ClientRepos clientRepos, FormRepos formRepos) {
        this.clientRepos = clientRepos;
        this.formRepos = formRepos;
    }

    //get all clients and sort by name
    @GetMapping
    public List<Client> getAllBanks(){
        return this.clientRepos.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }
    //get client by id
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable(value = "id") long clientId) {
        return this.clientRepos.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id :" + clientId));
    }
    //create client
    @PostMapping
    public Client createClient(@RequestBody Map<String, String> client) {
        Client newClient = new Client();
        newClient.setName(client.get("name"));
        newClient.setAddress(client.get("address"));
        newClient.setShort_title(client.get("short_title"));
        Forms forms = this.formRepos.getOne(Long.parseLong(client.get("forms_id")));
        newClient.setForms(forms);
        return this.clientRepos.save(newClient);
    }
    @PutMapping("/{id}")
    public Client updateClient(@RequestBody Map<String, String> client, @PathVariable(value = "id") long clientId) {
        Client existingClient = this.clientRepos.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id :" + clientId));
        existingClient.setName(client.get("name"));
        existingClient.setShort_title(client.get("short_title"));
        existingClient.setAddress(client.get("address"));
        Forms forms = this.formRepos.getOne(Long.parseLong(client.get("forms_id")));
        existingClient.setForms(forms);
        return this.clientRepos.save(existingClient);
    }
    //delete client by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable ("id") long clientId) {
        Client existingClient = this.clientRepos.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id :" + clientId));
        this.clientRepos.deleteById(clientId);
        return ResponseEntity.ok().build();
    }
}
