package ru.ispu.todo.crudtodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ispu.todo.crudtodo.entity.ToDo;

import static org.hibernate.loader.Loader.SELECT;


/*
 Здесь мы используем Spring Data JPA.
 Одно из главных преимуществ Spring Data JPA состоит в том,
 что можно не заботиться о реализации основных функциональных возможностей CRUD.
 Используя Spring Data JPA, мы оперируем репозиториями, а не DAO.
 Интерфейс JpaRepository<T, ID> предоставляет множество методов для CRUD-операций ( все уже вшито )
 */
@Repository // Это репозиторий приложения
public interface ToDoRepository extends JpaRepository<ToDo,Long> {

    // Метод поиска по имени
    Iterable<ToDo> findByFirstName(String firstName);

    /*
    Метод поиска по фамилии, здесь главное чтобы метод начинался с findBy,
    после чего сам репозиторий предоставит нам выбор поиска сущности (все просто).
    Предыдущий метод имеет точно такую же функциональность.
     */
    Iterable<ToDo> findByLastName(String lastName);
}
