package twitter.repos;

import org.springframework.data.repository.CrudRepository;
import twitter.domain.Message;

import java.util.List;

public interface Messagerepo extends CrudRepository<Message, Long> {

    List<Message> findByTag(String tag);
}
