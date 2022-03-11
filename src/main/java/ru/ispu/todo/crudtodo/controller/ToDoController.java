package ru.ispu.todo.crudtodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ispu.todo.crudtodo.entity.ToDo;
import ru.ispu.todo.crudtodo.service.ToDoService;

/**
 * @author Евгений Караваев
 */


@Controller // Вызов объектов классов, представляющих собой бизнес-логику приложения
@RequestMapping("/views") // Аннотация принимающая адрес содержащийся в запросе
public class ToDoController {

    // Доступ к сервису
    private ToDoService toDoService;

    @Autowired // Аннотация @Autowired отмечает конструктор как требующий автозаполнения инъекцией зависимости Spring ( внедрение зависимости).
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    // Аннотация принимающая адрес содержащийся в запросе, возвращаем все объекты
    /*
    Model - модель может предоставлять атрибуты, используемые для визуализации представлений.
    Чтобы предоставить представлению полезные данные, мы просто добавляем эти данные в его объект модели,
    по типу ключ - значение
     */
    @RequestMapping( method = RequestMethod.GET)
    public String showToDo(Model model){ // Model здесь
        Iterable<ToDo> toDos = toDoService.findAllToDoServices(); // добавляем атрибут для возврата значений
        model.addAttribute("toDos",toDos);
        return "views";
    }

    // Здесь я добавляю новые данные, плюс проверяю на то что поля не пустые, для того чтобы не создавалась пустая сущность в БД
    @RequestMapping(value = "/addNew", method = RequestMethod.GET)
    public String addToDo(@ModelAttribute(value = "toDo") ToDo toDo){
        if(toDo.getFirstName() != null && !toDo.getFirstName().isEmpty() &&
                toDo.getLastName() != null && !toDo.getLastName().isEmpty() &&
                toDo.getEmail() != null && !toDo.getEmail().isEmpty() &&
                toDo.getNumber() != null && !toDo.getNumber().isEmpty()){
            toDoService.saveToDoServices(toDo); // Если не пустая сохраняем
        }else{
            toDoService.findAllToDoServices(); // Если пустая просто показываем все что было до этого
        }
        return "addNew";
    }

    //Переходим по Id сущности для ее изменения
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateIdToDo(Model model, @PathVariable(value = "id") Long id) {
        // Добавляем в модель и переходим
        model.addAttribute("toDo",toDoService.getByIdServices(id));
        toDoService.deleteByToDoServices(id); // удаляю старую сущность со старым Id
        return "update";
    }

    // Изменяю сущность
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateToDo(@ModelAttribute(value = "toDo") ToDo toDo){
        toDoService.saveToDoServices(toDo); // сохраняю сущность(измененную)
        return "redirect:/views";
    }

    // А почему бы и не посмотреть сущность по Id
    @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
    public String showIdToDo(Model model, @PathVariable(value = "id") Long id) {
        // Добавлю в модель и посмотрю
        model.addAttribute("toDos", toDoService.getByIdServices(id));
        return "todo";
    }

    // Удалю сущность по id
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET,RequestMethod.DELETE})
    public String deleteToDo(@PathVariable(value = "id") Long id){
        toDoService.deleteByToDoServices(id);
        return "redirect:/views";
    }

    /*
    Ищу сущность по имени и проверяю на пустое значение, если пустое то просто вывожу все сущности, если нет,
    то показываю. И добавляю в модель для вывода в клиентской части
     */
    @RequestMapping(value = "/search/first", method = RequestMethod.GET)
    public String searchFirstNameToDo(Model model,
                             @RequestParam(value = "firstName", required = false) String firstName){
        ToDo toDo = new ToDo();
        if(firstName != null && !firstName.isEmpty()){
            model.addAttribute("toDos",toDoService.searchByFirstToDoServices(firstName));
        }else{
            model.addAttribute("toDos",toDoService.findAllToDoServices());
        }
        model.addAttribute("toDo",toDo);
        model.addAttribute("firstName",firstName);
        toDoService.findAllToDoServices();
        return "views";
    }

    /*
    Ищу сущность по фамилии и проверяю на пустое значение, если пустое то просто вывожу все сущности, если нет,
    то показываю. И добавляю в модель для вывода в клиентской части.
     */
    @RequestMapping(value = "/search/last", method = RequestMethod.GET)
    public String searchLastNameToDo(Model model,
                             @RequestParam(value = "lastName", required = false) String lastName){
        ToDo toDo = new ToDo();
        if(lastName != null && !lastName.isEmpty()){
            model.addAttribute("toDos",toDoService.searchByLastNameToDoServices(lastName));
        }else{
            model.addAttribute("toDos",toDoService.findAllToDoServices());
        }
        model.addAttribute("toDo",toDo);
        model.addAttribute("lastName",lastName);
        toDoService.findAllToDoServices();
        return "views";
    }

}
