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
import pl.pas.rest.model.Book;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Service
public class BookMvcService {

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

    public List<Book> findAll() {
        return restClient.get()
                .uri("api/books/all")
                .exchange(
                        ((clientRequest, clientResponse) ->{
                            if (clientResponse.getStatusCode() == HttpStatus.OK
                                    || clientResponse.getStatusCode() == HttpStatus.NO_CONTENT)  {
                                return objectMapper.readValue(clientResponse.getBody(), new TypeReference<>() {});
                            }
                            else {
                                throw new ConnectException("Failed to fetch data from API. Status: " + clientResponse.getStatusCode());
                                //return null;
                            }
                        })

                );
    }

    public List<Book> findAllByTitle(String title) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/books")
                        .queryParam("title", title).build())
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
}
