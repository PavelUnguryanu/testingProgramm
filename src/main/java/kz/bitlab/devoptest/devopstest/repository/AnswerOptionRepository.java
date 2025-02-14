package kz.bitlab.devoptest.devopstest.repository;
import kz.bitlab.devoptest.devopstest.model.AnswerOption;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AnswerOptionRepository extends JpaRepository<AnswerOption, Long> {
}