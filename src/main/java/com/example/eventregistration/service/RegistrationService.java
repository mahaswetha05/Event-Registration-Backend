package com.example.eventregistration.service;

import com.example.eventregistration.entity.*;
import com.example.eventregistration.repository.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public RegistrationService(
            RegistrationRepository registrationRepository,
            UserRepository userRepository,
            EventRepository eventRepository) {

        this.registrationRepository = registrationRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Transactional
    public Registration registerUser(Long userId, Long eventId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new RuntimeException("Event not found"));

        // Check duplicate registration
        if (registrationRepository
                .existsByUserIdAndEventId(userId, eventId)) {

            throw new RuntimeException(
                    "User is already registered for this event");
        }

        // Check event capacity
        long registeredCount =
                registrationRepository.countByEventId(eventId);

        if (registeredCount >= event.getCapacity()) {

            throw new RuntimeException("Event is full");
        }

        Registration registration =
                new Registration();

        registration.setUser(user);
        registration.setEvent(event);
        registration.setRegistrationDate(
                LocalDateTime.now());

        return registrationRepository.save(registration);
    }

    public List<Registration> getUserRegistrations(Long userId) {

        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }

        return registrationRepository.findByUserId(userId);
    }

    public List<Registration> getEventRegistrations(Long eventId) {

        if (!eventRepository.existsById(eventId)) {
            throw new RuntimeException("Event not found");
        }

        return registrationRepository.findByEventId(eventId);
    }

    public void cancelRegistration(Long registrationId) {

        if (!registrationRepository.existsById(registrationId)) {

            throw new RuntimeException(
                    "Registration not found");
        }

        registrationRepository.deleteById(registrationId);
    }
}