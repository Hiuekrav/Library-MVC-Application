package pl.pas.rest.repositories.interfaces;

import com.mongodb.client.MongoClient;
import pl.pas.rest.mgd.AbstractEntityMgd;

import java.util.List;
import java.util.UUID;

/**
 * Ogolne repozytorium, z bazowymi metodami dla wszystkich obiektow(encji)
 * @param <T> - mapper z klasy modelu na dokument
 */
public interface IObjectRepository<T extends AbstractEntityMgd> {

    T findById(UUID id);

    T findByIdOrNull(UUID id);

    List<T> findAll();

    T save(T doc);

    void deleteById(UUID id);

    MongoClient getClient();
}
