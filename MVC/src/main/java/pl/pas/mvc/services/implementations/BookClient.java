package pl.pas.mvc.services.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import pl.pas.mvc.services.interfaces.IBookClient;
import pl.pas.rest.model.Book;

import java.io.InputStream;
import java.net.URI;
import java.util.List;

@NoArgsConstructor
@Service
public class BookClient implements IBookClient {

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
                                //todo connection error message?
                                return null;
                            }
                        })

                );
    }
}
