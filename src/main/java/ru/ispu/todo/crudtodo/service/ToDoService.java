package ru.ispu.todo.crudtodo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ispu.todo.crudtodo.entity.ToDo;
import ru.ispu.todo.crudtodo.repository.ToDoRepository;

/**
 * @author Евгений Караваев
 */

/*
Одно из главных преимуществ Spring Data JPA состоит в том,
что можно не заботиться о реализации основных функциональных возможностей CRUD,
что можно увидеть ниже после точки: пример- toDoRepository.findAll().
 */
// Сервис
@Service
public class ToDoService {
    // Добавляем поле репоситория для внедрения зависимоти в Spring
    private ToDoRepository toDoRepository;
    @Autowired // Внедряем зависимость
    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }
    // Возвращает все объекты данного типа
    public Iterable<ToDo> findAllToDoServices(){
        return toDoRepository.findAll();
    }

    // Сохраняет указанную сущность
    public ToDo saveToDoServices(ToDo toDo){
        return toDoRepository.save(toDo);
    }

    // Возвращает сущность по id
    public ToDo getByIdServices(Long id) {
        return toDoRepository.getById(id);
    }

    // Удаляет сущность по id
    public void deleteByToDoServices(Long id){
        toDoRepository.deleteById(id);
    }

    // Ищем слово по имени
    public Iterable<ToDo> searchByFirstToDoServices(String firstName) {
        return toDoRepository.findByFirstName(firstName);
    }

    // Ищем слово по фамилии
    public Iterable<ToDo> searchByLastNameToDoServices(String lastName) {
        return toDoRepository.findByLastName(lastName);
    }
}
