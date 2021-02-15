package org.example.testproject.controller;

import org.example.testproject.exception.ResourceNotFoundException;
import org.example.testproject.model.Bank;
import org.example.testproject.model.Client;
import org.example.testproject.model.Contribution;
import org.example.testproject.repos.BankRepos;
import org.example.testproject.repos.ClientRepos;
import org.example.testproject.repos.ContributionRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contributions")
public class ContributionController {
    private final ContributionRepos contributionRepos;
    private final BankRepos bankRepos;
    private final ClientRepos clientRepos;

    @Autowired
    public ContributionController(ContributionRepos contributionRepos, BankRepos bankRepos, ClientRepos clientRepos) {
        this.contributionRepos = contributionRepos;
        this.bankRepos = bankRepos;
        this.clientRepos = clientRepos;
    }
    //get all contributions
    @GetMapping
    public List<Contribution> getAllBanks(){
        return this.contributionRepos.findAll();
    }
    //get contribution by id
    @GetMapping("/{id}")
    public Contribution getContributionById(@PathVariable(value = "id") long contributionId) {
        return this.contributionRepos.findById(contributionId)
                .orElseThrow(() -> new ResourceNotFoundException("Contribution not found with id :" + contributionId));
    }
    //create contribution
    @PostMapping
    public Contribution createContribution(@RequestBody Map<String, String> contribution){
        Contribution newestContribution = new Contribution();
        return this.contributionRepos.save(newContribution(contribution, newestContribution));
    }
    @PutMapping("/{id}")
    public Contribution updateContribution(@RequestBody Map<String, String> contribution, @PathVariable(value = "id") long contributionId) {
        Contribution existingContribution = this.contributionRepos.findById(contributionId)
                .orElseThrow(() -> new ResourceNotFoundException("Contribution not found with id :" + contributionId));
        return this.contributionRepos.save(newContribution(contribution, existingContribution));
    }
    //delete contribution by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Contribution> deleteContribution(@PathVariable ("id") long contributionId) {
        Contribution existingContribution = this.contributionRepos.findById(contributionId)
                .orElseThrow(() -> new ResourceNotFoundException("Contribution not found with id :" + contributionId));
        this.contributionRepos.deleteById(contributionId);
        return ResponseEntity.ok().build();
    }

    private Contribution newContribution(Map<String, String> contribution, Contribution existingContribution){
        Bank bank = this.bankRepos.getOne(Long.parseLong(contribution.get("bank_id")));
        Client client = this.clientRepos.getOne(Long.parseLong(contribution.get("client_id")));
        existingContribution.setClient(client);
        existingContribution.setBank(bank);
        LocalDate date = LocalDate.parse(contribution.get("date_open"));
        existingContribution.setDate_open(date);
        existingContribution.setPercent(Double.parseDouble(contribution.get("percent")));
        existingContribution.setTerm_in_mouth(Integer.parseInt(contribution.get("term_in_mouth")));
        return existingContribution;
    }
}
