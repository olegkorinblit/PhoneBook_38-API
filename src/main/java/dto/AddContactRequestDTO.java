package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class AddContactRequestDTO {
//         "id": "string",
//         "name": "string",
//         "lastName": "string",
//         "email": "string",
//         "phone": "61776954477926",
//         "address": "string",
//         "description": "string"
String id;
String name;
String lastName;
String email;
String phone;
String address;
String description;
}
