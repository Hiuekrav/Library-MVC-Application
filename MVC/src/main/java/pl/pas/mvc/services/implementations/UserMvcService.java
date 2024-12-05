package pl.pas.mvc.services.implementations;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import pl.pas.dto.create.UserCreateDTO;
import pl.pas.dto.output.ExceptionOutputDTO;

import java.util.List;

@Service
public class UserMvcService {

    @Value("${api.address}")
    private String apiAddress;

    @Value("${api.port}")
    private String apiPort;

    private RestClient restClient;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());


    @PostConstruct
    public void init() {
        String serverAddress = "http://" + apiAddress + ":" + apiPort;
        this.restClient = RestClient.builder().baseUrl(serverAddress).build();
    }


    public String registerUser(UserCreateDTO userCreateDTO) {
        return restClient.post()
                .uri("/api/users/create-reader")
                .contentType(MediaType.APPLICATION_JSON)
                .body(userCreateDTO)
                .exchange(
                        (clientRequest, clientResponse) -> {
                            if (!clientResponse.getStatusCode().is2xxSuccessful()) {
                                ExceptionOutputDTO exceptionOutputDTO = objectMapper.readValue(clientResponse.getBody(), new TypeReference<>() {});
                                System.out.println("exception: " +exceptionOutputDTO.message());
                                return exceptionOutputDTO.message();
                            }
                            return "registered.successfully";
                        }
                );
    }
}
