package com.example.calendar.controllers;


import com.example.calendar.models.Message;
import com.example.calendar.models.User;
import com.example.calendar.repository.MessageRepository;
import com.example.calendar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping()
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    List<Message> arrayMesage;


    @PostMapping(value = "messageAdd/{id}")
    public Message createMesage(@RequestBody Message message, @PathVariable("id") Long id) {
        return addMesage(message, id);
    }

    private Message addMesage(Message message, Long id) {
        return messageRepository.save(new Message(message.getText(), message.getYers(), message.getMounth(), message.getDays(), message.getClock(), message.getMinuts(), userRepository.findById(id).get()));
    }


    @PostMapping(value = "mail")
    public void pushMessage() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("testmessage2023@gmail.com");
        mailSender.setPassword("moonaafeubedmxqg");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        arrayMesage = new ArrayList<>();
        messageRepository.findAll().forEach(arrayMesage::add);

        for(Message message: arrayMesage){
            String yers = message.getYers();
            String mounth = message.getMounth();
            String days = message.getDays();
            String clock = message.getClock();
            String minuts = message.getMinuts();
            String dats = yers+"-"+mounth+"-"+days+" "+clock+":"+minuts;
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime());
            if(dats.equals(timeStamp)){

                User user = message.getAuthor();

                SimpleMailMessage mes = new SimpleMailMessage();
                mes.setFrom("testmessage2023@gmail.com");
                mes.setTo(user.getEmail());
                mes.setText(message.getText());
                mailSender.send(mes);

            }

        }


    }

}