package com.openclassrooms.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.api.dto.UserDTO;
import com.openclassrooms.api.dto.UserUpdateDTO;
import com.openclassrooms.api.mappers.UserMapper;
import com.openclassrooms.api.models.User;
import com.openclassrooms.api.repositories.UserRepository;
import com.openclassrooms.api.repositories.SubscriptionRepository;

import java.time.LocalDateTime;

import java.util.Optional;


@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper; // Ajout de la dépendance

    public UserService(AuthenticationManager authenticationManager,
                      JWTService jwtService,
                      PasswordEncoder passwordEncoder,
                      UserRepository userRepository,
                      SubscriptionRepository subscriptionRepository,
                      UserMapper userMapper) { // Injection de la dépendance
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper; // Initialisation de la dépendance
    }

    public String register(String name, String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");
        LocalDateTime now = LocalDateTime.now();
        user.setCreated_at(now);
        user.setUpdated_at(now);

        userRepository.save(user);

        return jwtService.generateSimpleToken(user.getEmail());
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public String authenticate(String email, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
            );

            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(authentication);
            } else {
                throw new RuntimeException("Invalid credentials");
            }
        } catch (Exception e) {
            throw new RuntimeException("Invalid credentials");
        }
    }

    public Optional<UserDTO> getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return Optional.of(UserDTO.fromEntity(user));
        }
        return Optional.empty();
    }

    public UserDTO getCurrentUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Charge les sujets abonnés
        User userWithSubjects = userRepository.findWithSubscribedSubjectsById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userMapper.toDTO(userWithSubjects);
    }

    // Assure-toi que cette méthode existe bien
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }



    public Optional<UserUpdateDTO> updateUser(UserUpdateDTO userUpdateDTO) {
        Optional<User> userOptional = userRepository.findById(userUpdateDTO.getId());
    
        if (userOptional.isPresent()) {
            User user = userOptional.get();
    
            // Mise à jour des champs name et email si non null
            if (userUpdateDTO.getName() != null) {
                user.setName(userUpdateDTO.getName());
            }
            
            if (userUpdateDTO.getEmail() != null) {
                user.setEmail(userUpdateDTO.getEmail());
            }
            
            // Mise à jour du mot de passe si non null ou non vide
            if (userUpdateDTO.getPassword() != null && !userUpdateDTO.getPassword().isEmpty()) {
                // Hash du mot de passe avec le PasswordEncoder
                user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
            }
            
            // Mise à jour de la date de modification
            user.setUpdated_at(LocalDateTime.now());
    
            try {
                userRepository.save(user);
                return Optional.of(userUpdateDTO);
            } catch (Exception e) {
                throw e; // ou retournez une Optional.empty() selon votre logique
            }
        } else {
            return Optional.empty();
        }
    }
}

