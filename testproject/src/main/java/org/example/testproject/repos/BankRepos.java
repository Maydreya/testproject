package org.example.testproject.repos;

import org.example.testproject.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepos extends JpaRepository<Bank, Long> {
}
