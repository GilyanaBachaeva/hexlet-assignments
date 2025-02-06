package exercise.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import static jakarta.persistence.GenerationType.IDENTITY;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Entity
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;
}
// END
/*Создайте модель Person со свойствами, которая будет представлять человека в нашем приложении.
У человека есть уникальный идентификатор, имя firstName и фамилия lastName.
Идентификатор должен генерироваться автоматически.*/