package org.example.testproject.controller;

import org.example.testproject.exception.ResourceNotFoundException;
import org.example.testproject.model.Bank;
import org.example.testproject.repos.BankRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
public class BankController {

    private final BankRepos bankRepos;

    @Autowired
    public BankController(BankRepos bankRepos) {
        this.bankRepos = bankRepos;
    }

    //get all banks and sort by name
    @GetMapping
    public List<Bank> getAllBanks(){
        return this.bankRepos.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }
    //get bank by id
    @GetMapping("/{id}")
    public Bank getBankById(@PathVariable(value = "id") long bankId) {
        return this.bankRepos.findById(bankId)
                .orElseThrow(() -> new ResourceNotFoundException("Bank not found with id :" + bankId));
    }
    //create bank
    @PostMapping
    public Bank createBank(@RequestBody Bank bank) {
        return this.bankRepos.save(bank);
    }
    //update bank
    @PutMapping("/{id}")
    public Bank updateBank(@RequestBody Bank bank, @PathVariable(value = "id") long bankId) {
        Bank existingBank = this.bankRepos.findById(bankId)
                .orElseThrow(() -> new ResourceNotFoundException("Bank not found with id :" + bankId));
        existingBank.setName(bank.getName());
        existingBank.setBik(bank.getBik());
        return this.bankRepos.save(existingBank);
    }
    //delete bank by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Bank> deleteBank(@PathVariable ("id") long bankId) {
        Bank existingBank = this.bankRepos.findById(bankId)
                .orElseThrow(() -> new ResourceNotFoundException("Bank not found with id :" + bankId));
        this.bankRepos.deleteById(bankId);
        return ResponseEntity.ok().build();
    }
}
