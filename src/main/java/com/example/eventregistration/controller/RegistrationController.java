package com.example.eventregistration.controller;

import com.example.eventregistration.entity.Registration;
import com.example.eventregistration.service.RegistrationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(
            RegistrationService registrationService) {

        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<Registration> registerUser(
            @RequestParam Long userId,
            @RequestParam Long eventId) {

        Registration registration =
                registrationService.registerUser(
                        userId, eventId);

        return new ResponseEntity<>(
                registration,
                HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Registration>>
    getUserRegistrations(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                registrationService
                        .getUserRegistrations(userId));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Registration>>
    getEventRegistrations(
            @PathVariable Long eventId) {

        return ResponseEntity.ok(
                registrationService
                        .getEventRegistrations(eventId));
    }

    @DeleteMapping("/{registrationId}")
    public ResponseEntity<String> cancelRegistration(
            @PathVariable Long registrationId) {

        registrationService
                .cancelRegistration(registrationId);

        return ResponseEntity.ok(
                "Registration cancelled successfully");
    }
}