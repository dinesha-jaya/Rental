package lk.rental.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {

//    private long userId;
    private String username;
    private String password;
    private String userCategory;
}
