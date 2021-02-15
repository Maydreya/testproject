package org.example.testproject.repos;

import org.example.testproject.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepos extends JpaRepository<Client, Long> {
}
