package exercise.model;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// BEGIN
@Entity
@Table(name = "products") // Указываем имя таблицы, если необходимо
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"title", "price"}) // Сравнение по title и price
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматическая генерация идентификатора
    private Long id;
    private String title;
    private Double price;

    // Конструктор с параметрами, если нужен
    public Product(String title, Double price) {
        this.title = title;
        this.price = price;
    }
}
// END
