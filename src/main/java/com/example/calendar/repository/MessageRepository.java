package com.example.calendar.repository;

import com.example.calendar.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message,Long> {
    Optional<Message> findBy();
}
