package kz.iitu.registration.service;

import kz.iitu.registration.model.EventInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final List<EventInfo> events = new ArrayList<>();
    private int nextId = 4;

    public EventService() {
        events.add(new EventInfo(1, "Web Design Workshop", "Room 101"));
        events.add(new EventInfo(2, "JavaScript Quiz", "Auditorium B"));
        events.add(new EventInfo(3, "IT Career Talk", "Main Hall"));
    }

    public List<EventInfo> getAllEvents(String title) {
        if (title == null || title.isBlank()) {
            return new ArrayList<>(events);
        }
        String query = title.toLowerCase();
        return events.stream()
                .filter(event -> event.title().toLowerCase().contains(query))
                .toList();
    }

    public Optional<EventInfo> getEventById(int id) {
        return events.stream()
                .filter(event -> event.id() == id)
                .findFirst();
    }

    public EventInfo createEvent(String title, String location) {
        EventInfo newEvent = new EventInfo(nextId++, title.trim(), location.trim());
        events.add(newEvent);
        return newEvent;
    }

    public Optional<EventInfo> updateEvent(int id, String title, String location) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).id() == id) {
                EventInfo updated = new EventInfo(id, title.trim(), location.trim());
                events.set(i, updated);
                return Optional.of(updated);
            }
        }
        return Optional.empty();
    }

    public boolean deleteEvent(int id) {
        return events.removeIf(event -> event.id() == id);
    }
}