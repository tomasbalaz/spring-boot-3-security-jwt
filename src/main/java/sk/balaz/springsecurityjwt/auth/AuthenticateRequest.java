package sk.balaz.springsecurityjwt.auth;

public record AuthenticateRequest(
    String email,
    String password
) { }
