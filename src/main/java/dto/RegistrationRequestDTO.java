package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class RegistrationRequestDTO {

//    "username": "string",
//            "password": "l]T6M='aco\"ro7Mdl7.=?<n'a(io=v!b^7E;|"
    String username;
    String password;


}
