package net.dante;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.dante.user.SuspendedUserData;
import net.dante.user.User;

@RestController
@RequestMapping("/users")
public class UserController extends ItemController<User> {
    private final DataStore dataStore;

    public UserController(DataStore dataStore) {
        super(dataStore::getUsers, dataStore::addUser, input -> new User(input.getName(), input.getEmail()));
        this.dataStore = dataStore;
    }

    @PutMapping("/{id}/suspend")
    public ResponseEntity<Void> suspendUser(@PathVariable String id, @RequestBody SuspendRequest request) {
        return dataStore.getUsers().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .map(user -> {
                    user.suspend(new SuspendedUserData(request.reason()));
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    private record SuspendRequest(String reason) {
    }
}
