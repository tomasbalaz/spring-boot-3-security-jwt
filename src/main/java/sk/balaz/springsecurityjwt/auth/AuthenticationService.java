package sk.balaz.springsecurityjwt.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import sk.balaz.springsecurityjwt.config.JwtService;
import sk.balaz.springsecurityjwt.user.Role;
import sk.balaz.springsecurityjwt.user.User;
import sk.balaz.springsecurityjwt.user.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {

    var user = User.builder()
        .fistName(request.firstName())
        .lastName(request.lastName())
        .password(request.password())
        .role(Role.ADMIN)
        .build();
    userRepository.save(user);
    var jwtToken = jwtService.generateToken(user);

    return new AuthenticationResponse(jwtToken);
  }

  public AuthenticationResponse authenticate(AuthenticateRequest request) {
    // if user is not authenticated then throw an exception
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.email(),
            request.password()
        )
    );
    //TODO Create UserNotFoundException and catch it via @RestControllerAdvice
    var user = userRepository.findByEmail(request.email())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);

    return new AuthenticationResponse(jwtToken);
  }
}
