package kz.bitlab.devoptest.devopstest.repository;
import kz.bitlab.devoptest.devopstest.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT * FROM question ORDER BY RANDOM() LIMIT 20", nativeQuery = true)
    List<Question> getRandomQuestions();
}