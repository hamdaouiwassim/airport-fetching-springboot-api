package airportfetching.example.airportfetching.config;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private HttpStatus statusCode;
    private String errorMessage;
    private Object Body;
    public ErrorResponse(HttpStatus statusCode,String errorMessage){
        this(statusCode,errorMessage,errorMessage);

    }
    public int getStatusCodeValue(){
        return statusCode.value();
    }
}
