package sk.balaz.springsecurityjwt.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

  private Integer id;
  private String fistName;
  private String lastName;
  private String email;
  private String password;

}
