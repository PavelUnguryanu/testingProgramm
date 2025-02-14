package kz.bitlab.devoptest.devopstest.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
@Entity
@Table(name = "answer_option")
public class AnswerOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("text") // Указываем явное сериализуемое поле
    private String text;

    @JsonProperty("isCorrect") // Указываем для передачи данных в JSON
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    @JsonBackReference
    private Question question;

    // Геттеры и сеттеры
}