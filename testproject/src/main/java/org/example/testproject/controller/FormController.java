package org.example.testproject.controller;

import org.example.testproject.exception.ResourceNotFoundException;
import org.example.testproject.model.Bank;
import org.example.testproject.model.Forms;
import org.example.testproject.repos.FormRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forms")
public class FormController {
    private final FormRepos formRepos;

    @Autowired
    public FormController(FormRepos formRepos) {
        this.formRepos = formRepos;
    }
    //find all forms and sort by name
    @GetMapping
    public List<Forms> getAllForms(){
        return this.formRepos.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    //find form by id
    @GetMapping("/{id}")
    public Forms getBankById(@PathVariable(value = "id") long formId) {
        return this.formRepos.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id :" + formId));
    }

    //create form
    @PostMapping
    public Forms createForm(@RequestBody Forms form) {
        return this.formRepos.save(form);
    }

    //update form
    @PutMapping("/{id}")
    public Forms updateForm(@RequestBody Forms form, @PathVariable(value = "id") long formId) {
        Forms existingForm = this.formRepos.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id :" + formId));
        existingForm.setName(form.getName());
        return this.formRepos.save(existingForm);
    }

    //delete form by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Forms> deleteForm(@PathVariable ("id") long formId) {
        Forms existingForm = this.formRepos.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id :" + formId));
        this.formRepos.deleteById(formId);
        return ResponseEntity.ok().build();
    }

}
