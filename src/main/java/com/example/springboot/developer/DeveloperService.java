package com.example.springboot.developer;

import org.springframework.stereotype.Service;

@Service
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public Developer findByEmail(String email) {
        return developerRepository.findByEmail(email);
    }

    public <S extends Developer> S save(S entity) {
        return developerRepository.save(entity);
    }

    public Iterable<Developer> findAll() {
        return developerRepository.findAll();
    }
}
