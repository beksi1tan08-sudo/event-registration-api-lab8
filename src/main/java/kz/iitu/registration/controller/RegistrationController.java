package kz.iitu.registration.controller;

import kz.iitu.registration.model.EventInfo;
import kz.iitu.registration.service.EventService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    private final EventService eventService;

    @Value("${spring.application.name:event-registration-api}")
    private String appName;

    @Value("${app.course:Web Development}")
    private String course;

    @Value("${app.semester:Fall 2026}")
    private String semester;

    public RegistrationController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of("message", "Welcome to Event Registration API!");
    }

    @GetMapping("/info")
    public Map<String, String> info() {
        return Map.of(
                "application", appName,
                "course", course,
                "semester", semester
        );
    }

    @GetMapping("/events")
    public List<EventInfo> getEvents(@RequestParam(required = false) String title) {
        return eventService.getAllEvents(title);
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<EventInfo> getEventById(@PathVariable int id) {
        return eventService.getEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/events")
    public ResponseEntity<EventInfo> createEvent(@RequestBody EventInfo request) {
        if (isInvalid(request)) {
            return ResponseEntity.badRequest().build();
        }
        EventInfo created = eventService.createEvent(request.title(), request.location());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<EventInfo> updateEvent(@PathVariable int id, @RequestBody EventInfo request) {
        if (isInvalid(request)) {
            return ResponseEntity.badRequest().build();
        }
        return eventService.updateEvent(id, request.title(), request.location())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable int id) {
        if (eventService.deleteEvent(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private boolean isInvalid(EventInfo request) {
        return request == null
                || request.title() == null || request.title().isBlank()
                || request.location() == null || request.location().isBlank();
    }
}