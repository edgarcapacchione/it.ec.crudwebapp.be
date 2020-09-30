package it.edgarcapacchione.crudwebapp.controller;

import it.edgarcapacchione.crudwebapp.exception.ResourceNotFoundException;
import it.edgarcapacchione.crudwebapp.model.User;
import it.edgarcapacchione.crudwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * This is the one and only users controller
 *
 * @author E. Capacchione
 *
 */
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepo;

    @Autowired
    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * <p>This method will get the users list</p>
     * @return the users list
     * @since 0.0.1
     */
    @GetMapping("/users")
    public List<User> getUsersList(){
        return userRepo.findAll();
    }

    /**
     * <p>This method will get the searched user's detail</p>
     * @param id the id of searched user
     * @return the searched user's detail
     * @since 0.0.1
     */
    @GetMapping("/user/{id}")
    public User getUserDetail(@PathVariable Long id){
        return userRepo
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found -> id: " + id));
    }

    /**
     * <p>This method will add a user</p>
     * @param u the user object who will be added (maybe)
     * @return the added user's detail
     * @since 0.0.1
     */
    @PostMapping("/users")
    public User createUser(@RequestBody User u){
        return userRepo.save(u);
    }

    /**
     * <p>This method will update the user info</p>
     * @param id of the user who will be updated
     * @return the updated user's detail
     * @since 0.0.1
     */
    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User u){
        User record = userRepo
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found -> id: " + id));

        record.setName(u.getName());
        record.setSurname(u.getSurname());
        record.setEmail(u.getEmail());
        record.setPassword(u.getPassword());

        return ResponseEntity.ok(userRepo.save(record));
    }

    /**
     * <p>This method will delete the targeted user</p>
     * @param id the id of user who will be incriminated
     * @return the id of the incriminated user
     * @since 0.0.1
     */
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id){
        User record = userRepo
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found -> id: " + id));

        userRepo.delete(record);
        return ResponseEntity.ok(id);
    }
}
