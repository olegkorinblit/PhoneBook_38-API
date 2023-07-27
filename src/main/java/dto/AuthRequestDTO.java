package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class AuthRequestDTO {
//    "username": "string",
//            "password": "C=gn*Mr-;Tm$K"
    String username;
    String password;
}
