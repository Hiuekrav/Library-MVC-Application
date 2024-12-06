package pl.pas.mvc.services.implementations;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import pl.pas.dto.create.RentCreateDTO;
import pl.pas.dto.create.RentCreateShortDTO;
import pl.pas.dto.output.ExceptionOutputDTO;
import pl.pas.dto.output.RentOutputDTO;
import pl.pas.mvc.DTO.EndRemoveRentForm;
import pl.pas.mvc.DTO.RentForm;
import pl.pas.mvc.DTO.RentNowForm;
import pl.pas.rest.model.users.User;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Service
public class RentMvcService {

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

    public String createRent(RentForm rentForm) {
        User foundUser = findUserByEmail(rentForm.email());
        if (foundUser== null) {
            return "user.not.found.exception";
        }

        RentCreateDTO rentCreateDTO = new RentCreateDTO(
                rentForm.beginTime(),
                rentForm.endTime(),
                foundUser.getId(),
                rentForm.bookId()
        );
        return restClient.post()
                .uri("api/rents")
                .body(rentCreateDTO)
                .exchange(
                        (((clientRequest, clientResponse) -> {
                            if (clientResponse.getStatusCode() == HttpStatus.CREATED) {
                                return "rent.successfully.created";
                            }
                            else {
                                ExceptionOutputDTO exceptionOutputDTO = objectMapper.readValue(clientResponse.getBody(), new TypeReference<>() {});
                                return exceptionOutputDTO.message();
                            }
                        }))
                );
    }

    public String createRentNow(RentNowForm rentNowForm) {
        User foundUser = findUserByEmail(rentNowForm.email());
        if (foundUser== null) {
            return "user.not.found.exception";
        }

        RentCreateShortDTO rentCreateShortDTO = new RentCreateShortDTO(foundUser.getId(), rentNowForm.bookId());

        return restClient.post()
                .uri("api/rents/now")
                .body(rentCreateShortDTO)
                .exchange(
                        (((clientRequest, clientResponse) -> {
                            if (clientResponse.getStatusCode() == HttpStatus.CREATED) {
                                return "rent.successfully.created";
                            }
                            else {
                                ExceptionOutputDTO exceptionOutputDTO = objectMapper.readValue(clientResponse.getBody(), new TypeReference<>() {});
                                return exceptionOutputDTO.message();
                            }
                        }))
                );
    }

    public List<RentOutputDTO> findAll() {
        return restClient.get()
                .uri("api/rents/all")
                .exchange(
                        ((clientRequest, clientResponse) ->{
                            if (clientResponse.getStatusCode() == HttpStatus.OK)  {
                                return objectMapper.readValue(clientResponse.getBody(), new TypeReference<>() {});
                            }
                            else {
                                return new ArrayList<>();
                            }
                        })

                );
    }

    private User findUserByEmail(String email) {
        List<User> foundUser = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("api/users")
                        .queryParam("email", email).build())
                .exchange(
                        (((clientRequest, clientResponse) -> {
                            if (clientResponse.getStatusCode() == HttpStatus.OK) return objectMapper.readValue(clientResponse.getBody(), new TypeReference<>() {});
                            return null;
                        }))
                );
        if (foundUser== null || foundUser.size() != 1) {
            return null;
        }
        return foundUser.getFirst();
    }

    public List<RentOutputDTO> getAllActive() {
        return restClient.get()
                .uri("api/rents/active")
                .exchange(
                        (((clientRequest, clientResponse) -> {
                            if (clientResponse.getStatusCode() == HttpStatus.NO_CONTENT) {
                                return new ArrayList<>();
                            }
                            return objectMapper.readValue(clientResponse.getBody(), new TypeReference<>() {});
                        }))
                );
    }

    public List<RentOutputDTO> getAllArchive() {
        return restClient.get()
                .uri("api/rents/archive")
                .exchange(
                        (((clientRequest, clientResponse) -> {
                            if (clientResponse.getStatusCode() == HttpStatus.NO_CONTENT) {
                                return new ArrayList<>();
                            }
                            return objectMapper.readValue(clientResponse.getBody(), new TypeReference<>() {});
                        }))
                );
    }

    public List<RentOutputDTO> getAllFuture() {
        return restClient.get()
                .uri("api/rents/future")
                .exchange(
                        (((clientRequest, clientResponse) -> {
                            if (clientResponse.getStatusCode() == HttpStatus.NO_CONTENT) {
                                return new ArrayList<>();
                            }
                            return objectMapper.readValue(clientResponse.getBody(), new TypeReference<>() {});
                        }))
                );
    }


    public String endRent(EndRemoveRentForm endRentForm) {
        String uri = "api/rents/%s/end".formatted(endRentForm.rentId());
        System.out.println("uri: " + uri);
        return restClient.post()
                .uri(uri)
                .exchange(
                        (((clientRequest, clientResponse) -> {
                            if (clientResponse.getStatusCode() == HttpStatus.NO_CONTENT) {
                                return "rent.successfully.ended";
                            }
                            else {
                                ExceptionOutputDTO exceptionOutputDTO = objectMapper.readValue(clientResponse.getBody(), new TypeReference<>() {});
                                return exceptionOutputDTO.message();
                            }
                        }))
                );

    }


    public String removeRent(EndRemoveRentForm removeRentForm) {
        String uri = "api/rents/%s".formatted(removeRentForm.rentId());
        return restClient.delete()
                .uri(uri)
                .exchange(
                        (((clientRequest, clientResponse) -> {
                            if (clientResponse.getStatusCode() == HttpStatus.NO_CONTENT) {
                                return "rent.successfully.deleted";
                            }
                            else {
                                ExceptionOutputDTO exceptionOutputDTO = objectMapper.readValue(clientResponse.getBody(), new TypeReference<>() {});
                                return exceptionOutputDTO.message();
                            }
                        }))
                );
    }
}
