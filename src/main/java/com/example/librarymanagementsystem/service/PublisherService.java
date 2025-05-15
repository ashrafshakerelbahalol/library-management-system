package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.entity.Publisher;
import com.example.librarymanagementsystem.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public Optional<Publisher> findById(int id) {
        return publisherRepository.findById(id);
    }
}
