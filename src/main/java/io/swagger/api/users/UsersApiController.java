package io.swagger.api.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.entity.users.User;
import io.swagger.model.users.UserDetails;
import io.swagger.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-04-02T22:43:09.213512-04:00[America/New_York]")
@RestController
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final UserRepository userRepository;

    @Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request, UserRepository userRepository) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<UserDetails>> usersGet() {
        Iterable<User> users = userRepository.findAll();
        List<UserDetails> userDetailsList = StreamSupport.stream(users.spliterator(), true)
                .map(user -> new UserDetails()
                        .id(user.getId())
                        .email(user.getEmail())
                        .admin(user.isAdmin()))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(userDetailsList);
    }

}
