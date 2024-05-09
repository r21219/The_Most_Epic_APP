package cz.osu.project_todoholecekp_hrtonm.Service;

import cz.osu.project_todoholecekp_hrtonm.Exception.AlreadyExistsException;
import cz.osu.project_todoholecekp_hrtonm.Exception.RecordNotFoundException;
import cz.osu.project_todoholecekp_hrtonm.Exception.UnAuthorizedException;
import cz.osu.project_todoholecekp_hrtonm.Model.AuthenticationResponse;
import cz.osu.project_todoholecekp_hrtonm.Model.Role;
import cz.osu.project_todoholecekp_hrtonm.Model.User;
import cz.osu.project_todoholecekp_hrtonm.Repository.UserRepository;
import cz.osu.project_todoholecekp_hrtonm.config.jwt.JwtService;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final Argon2 argon2 = Argon2Factory.create();

    @Override
    public User get(String name) {
        System.out.println(name);
        return userRepository.findById(name).orElseThrow(() -> new RecordNotFoundException("User does not exist"));
    }

    @Override
    public void delete(String name) {
        boolean exists = userRepository.existsById(name);
        if (exists) {
            userRepository.deleteById(name);
        } else throw new RecordNotFoundException("User does not exist");
    }

    @Override
    public AuthenticationResponse register(User customerRequest) {
        if (userRepository.findByName(customerRequest.getName()).isPresent()) {
            throw new AlreadyExistsException("User by that name already exists");
        }
        var user = User.builder()
                .name(customerRequest.getName())
                .password(argon2.hash(2, 65536, 1, customerRequest.getPassword().toCharArray()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse login(User customerRequest) {
        var user = userRepository.findByName(customerRequest.getName())
                .orElseThrow();
        if (!argon2.verify(user.getPassword(), customerRequest.getPassword().toCharArray())) {
            throw new UnAuthorizedException("Invalid password");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        customerRequest.getName(),
                        customerRequest.getPassword()
                )
        );

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
