package sk.balaz.springsecurityjwt.auth;

public record RegisterRequest(
    String firstName,
    String lastName,
    String email,
    String password
) { }
