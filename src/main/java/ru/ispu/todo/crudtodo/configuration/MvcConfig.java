package ru.ispu.todo.crudtodo.configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
Это Spring Security
addViewControllers() Метод -который переопределяет метод того же имени в WebMvcConfigurer)
добавляет 1 контроллер представления
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

}