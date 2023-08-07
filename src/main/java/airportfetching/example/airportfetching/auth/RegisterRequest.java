package airportfetching.example.airportfetching.auth;

import lombok.*;

@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String email;
    private String password;
    private String user_name;
    private String mobile_number;
    private String first_name;
    private String last_name;
}
