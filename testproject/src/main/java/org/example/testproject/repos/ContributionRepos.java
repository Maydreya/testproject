package org.example.testproject.repos;

import org.example.testproject.model.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContributionRepos extends JpaRepository<Contribution, Long> {

}
