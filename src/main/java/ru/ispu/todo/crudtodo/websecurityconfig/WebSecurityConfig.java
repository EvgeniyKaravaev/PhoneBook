package ru.ispu.todo.crudtodo.websecurityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/*
Это Spring Security - описание взято из документации:
WebSecurityConfigКласс помечается @EnableWebSecurityвключить поддержку веб -
безопасности Spring Security и обеспечивают интеграцию Spring MVC.
Он также расширяет WebSecurityConfigurerAdapterи переопределяет
несколько своих методов, чтобы установить некоторые особенности конфигурации веб-безопасности.

В configure(HttpSecurity)методе определяет ,
какие пути URL должны быть надежно закреплены и которые не должны.
В частности, / путь настроен , чтобы не требовать никакой аутентификации.
Все остальные пути должны быть аутентифицированы.

Когда пользователь успешно входит в систему, он перенаправляется на ранее запрошенную страницу,
требующую аутентификации. Существует пользовательская /login страница (указывается loginPage()),
и всем разрешено ее просматривать.

userDetailsService() Метод настраивает в памяти пользователя магазин с одним пользователем.
Этому пользователю присваивается имя пользователя user, пароль password и роль USER.

Остальная реализация в клиентской части
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*
    Метод configure(HttpSecurity http) отвечает за настройку защиты на уровне запросов и
    конфигурирование процессов авторизации.

    antMatchers — с помощью данного метода указывается http-метод и URL (или шаблон URL),
    доступ к которому необходимо ограничить.

    formLogin() — дает возможность настроить форму для авторизации.

    loginPage — URL формы авторизации.

    logout() — позволяет настроить правила выхода из учетной записи.

     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();
        return new InMemoryUserDetailsManager(user);
    }
}
