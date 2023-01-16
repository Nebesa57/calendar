package com.example.calendar.repository;

import com.example.calendar.models.Message;
import com.example.calendar.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {


    List<Message> findAllByAuthor(User user);
}
