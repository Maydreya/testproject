package org.example.testproject.repos;

import org.example.testproject.model.Forms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepos extends JpaRepository<Forms, Long> {
}
