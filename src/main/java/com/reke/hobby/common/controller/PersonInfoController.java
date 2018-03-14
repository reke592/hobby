package com.reke.hobby.common.controller;

import com.reke.hobby.common.exception.ResourceNotFoundException;
import com.reke.hobby.common.model.PersonInfo;
import com.reke.hobby.common.repository.PersonInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonInfoController {

    @Autowired
    private PersonInfoRepository personInfoRepository;

    // Get all person info
    @GetMapping("/person_info")
    public List<PersonInfo> getAllPersonInfo() {
        return personInfoRepository.findAll();
    }

    @PostMapping("/person_info")
    public PersonInfo createPersonInfo(@Valid @RequestBody PersonInfo personInfo) {
        return personInfoRepository.save(personInfo);
    }

    @GetMapping("/person_info/{id}")
    public PersonInfo getPersonInfoById(@PathVariable(value = "id") Long personInfoId) {
        return personInfoRepository.findById(personInfoId)
                .orElseThrow(() -> new ResourceNotFoundException("PersonInfo", "id", personInfoId));
    }

    @PutMapping("/person_info/{id}")
    public PersonInfo updatePersonInfo(@PathVariable(value = "id") Long personInfoId,
                                       @Valid @RequestBody PersonInfo personInfo) {
        PersonInfo info = personInfoRepository.findById(personInfoId)
                .orElseThrow(() -> new ResourceNotFoundException("PersonInfo", "id", personInfoId));

        info.setFirstName(personInfo.getFirstName());
        info.setMiddleName(personInfo.getMiddleName());
        info.setLastName(personInfo.getLastName());

        PersonInfo updatedInfo = personInfoRepository.save(info);
        return updatedInfo;
    }

    @DeleteMapping("/person_info/{id}")
    public ResponseEntity<?> deletePersonInfo(@PathVariable(value = "id") Long personInfoId) {
        PersonInfo info = personInfoRepository.findById(personInfoId)
                .orElseThrow(() -> new ResourceNotFoundException("PersonInfo", "id", personInfoId));
        personInfoRepository.delete(info);

        return ResponseEntity.ok().build();
    }
}
