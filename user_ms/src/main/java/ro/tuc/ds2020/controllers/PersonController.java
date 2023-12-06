package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ro.tuc.ds2020.configs.JwtService;
import ro.tuc.ds2020.contracts.requests.LoginRequest;
import ro.tuc.ds2020.contracts.responses.LoginResponse;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.AuthenticationResponse;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.services.PersonService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/person")
public class PersonController {

    private final PersonService personService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public PersonController(PersonService personService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.personService = personService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @GetMapping()
    public ResponseEntity<List<PersonDetailsDTO>> getPersons() {
        List<PersonDetailsDTO> dtos = personService.findPersons();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<LoginResponse> insertPerson(@Valid @RequestBody PersonDetailsDTO personDetailsDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(personDetailsDTO.getPassword());
        personDetailsDTO.setPassword(hashedPassword);
        LoginResponse loginResponse = personService.insert(personDetailsDTO);

        // Send a request to the device service to create a new person
        // Create a RestTemplate instance to make the HTTP request
        RestTemplate restTemplate = new RestTemplate();

        // Define the URL of the device service's API endpoint
//        String deviceServiceUrl = "http://localhost:8000/person";
        String deviceServiceUrl = "http://172.16.0.5:8000/person";

        // Create headers with the appropriate content type
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        // Create a request entity with the id as the request body and headers
        HttpEntity<?> requestEntity = new HttpEntity<>(loginResponse.getId(), headers);
        ResponseEntity<Boolean> response =  restTemplate.exchange(deviceServiceUrl, HttpMethod.POST, requestEntity, Boolean.class);

        if (response.getBody() == Boolean.TRUE) {
            return new ResponseEntity<>(loginResponse, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDetailsDTO> getPerson(@PathVariable("id") int personId) {
        PersonDetailsDTO dto = personService.findPersonById(personId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updatePerson(@PathVariable int id, @Valid @RequestBody PersonDetailsDTO updatedPersonDTO) {
        try {
            personService.update(id, updatedPersonDTO);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            // Handle the case where the person with the given ID was not found
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Handle other exceptions if necessary
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePerson(@PathVariable int id) {
        try {
            boolean deleted = personService.delete(id);

            // Send a request to the device service to delete the person
            // Create a RestTemplate instance to make the HTTP request
            RestTemplate restTemplate = new RestTemplate();

            // Define the URL of the device service's API endpoint
//             String deviceServiceUrl = "http://localhost:8000/person/" + id;
            String deviceServiceUrl = "http://172.16.0.5:8000/person/" + id;

            // Create a request entity
            RequestEntity<?> requestEntity = RequestEntity.delete(new URI(deviceServiceUrl)).build();

            ResponseEntity<Boolean> response =  restTemplate.exchange(requestEntity, Boolean.class);

            if (response.getBody() == Boolean.FALSE) {
                deleted = false;
            }

            if (deleted) {
                return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
            } else {
                // Handle the case where the person with the given ID was not found
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Handle other exceptions if necessary
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
//        LoginResponse loginResponse = personService.login(loginRequest.getUsername(), loginRequest.getPassword());
//        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
//    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) throws Exception {
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        // Update SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT
        UserDetails userDetails = personService.loadUserByUsername(authenticationRequest.getUsername());
        String jwt = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
