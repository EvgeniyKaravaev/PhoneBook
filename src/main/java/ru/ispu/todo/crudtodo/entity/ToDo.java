package ru.ispu.todo.crudtodo.entity;

import lombok.*;
import org.springframework.http.converter.json.GsonBuilderUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * @author Евгений Караваев
 */

//Создаем сущность используя фреймворк Spring Data, библиотеку Hibernate.

@Data // Автоматически генерирует геттеры и сеттеры к полям класса
@ToString // Переопределяет метод toString под созданный класс
@NoArgsConstructor // Создает конструктор без аргументов автоматически
@Entity // Аннотация Hibernate, означает создание сущности в БД
@Table(name = "todo") // название нашей таблицы в БД
public class ToDo {

    @Id // Id задали первичный ключ сущности
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Генерируем айдишник
    @Column(name = "id") // имя столбца ко всем полям с этой аннотацией
    private Long id;



    @Column(name = "first_name")
    @NotNull(message = "firstname not be null") // если имя не равно NULL, но может быть пустым
    @Size(min = 2,max = 100)
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "lastname not be null")
    @Size(min = 2,max = 100)
    private String lastName;

    @Column(name = "email")
    @NotNull(message = "email not be null")
    @Size(min = 2,max = 50)
    @Email
    private String email;

    @Column(name = "number")
    @NotNull(message = "number not be null")
    @Size(min = 2,max = 50)
    private String number;

    // Конструктор с параметрами
    public ToDo(String firstName, String lastName, String email, String number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.number = number;
    }

    // Переопределили метод под свой класс
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return Objects.equals(id, toDo.id) && Objects.equals(firstName, toDo.firstName)
                && Objects.equals(lastName, toDo.lastName)
                && Objects.equals(email, toDo.email)
                && Objects.equals(number, toDo.number);
    }

    // Переопределили метод под свой класс
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, number);
    }
}
