package kr.go.knp_system.domain.notification;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
public class EmitterRepository {
    //     private final Map<Long, Set<SseEmitter>> teamEmitters = new ConcurrentHashMap<>();
    // private final Map<String, Set<SseEmitter>> userEmitters = new ConcurrentHashMap<>();

    // private static final long TIMEOUT = 60 * 60 * 1000L; // 1h

    // public SseEmitter addTeamEmitter(Long teamId) {
    //     SseEmitter emitter = new SseEmitter(TIMEOUT);
    //     teamEmitters.computeIfAbsent(teamId, k -> ConcurrentHashMap.newKeySet()).add(emitter);
    //     emitter.onCompletion(() -> removeTeamEmitter(teamId, emitter));
    //     emitter.onTimeout(() -> removeTeamEmitter(teamId, emitter));
    //     emitter.onError(e -> removeTeamEmitter(teamId, emitter));
    //     return emitter;
    // }

    // public SseEmitter addUserEmitter(String userId) {
    //     SseEmitter emitter = new SseEmitter(TIMEOUT);
    //     userEmitters.computeIfAbsent(userId, k -> ConcurrentHashMap.newKeySet()).add(emitter);
    //     emitter.onCompletion(() -> removeUserEmitter(userId, emitter));
    //     emitter.onTimeout(() -> removeUserEmitter(userId, emitter));
    //     emitter.onError(e -> removeUserEmitter(userId, emitter));
    //     return emitter;
    // }

    // public void sendToTeam(Long teamId, SseEmitter.SseEventBuilder event) {
    //     Set<SseEmitter> emitters = teamEmitters.getOrDefault(teamId, Set.of());
    //     broadcast(emitters, event, () -> teamEmitters.get(teamId));
    // }

    // public void sendToUser(String userId, SseEmitter.SseEventBuilder event) {
    //     Set<SseEmitter> emitters = userEmitters.getOrDefault(userId, Set.of());
    //     broadcast(emitters, event, () -> userEmitters.get(userId));
    // }

    // private void broadcast(Set<SseEmitter> emitters, SseEmitter.SseEventBuilder event,
    //                        Supplier<Set<SseEmitter>> currentSupplier) {
    //     List<SseEmitter> dead = new ArrayList<>();
    //     for (SseEmitter emitter : emitters) {
    //         try {
    //             emitter.send(event);
    //         } catch (Exception e) {
    //             dead.add(emitter);
    //         }
    //     }
    //     if (!dead.isEmpty()) {
    //         Set<SseEmitter> current = currentSupplier.get();
    //         if (current != null) current.removeAll(dead);
    //     }
    // }

    // private void removeTeamEmitter(Long teamId, SseEmitter emitter) {
    //     Set<SseEmitter> set = teamEmitters.get(teamId);
    //     if (set != null) set.remove(emitter);
    // }
    // private void removeUserEmitter(String userId, SseEmitter emitter) {
    //     Set<SseEmitter> set = userEmitters.get(userId);
    //     if (set != null) set.remove(emitter);
    // }
}
