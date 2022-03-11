
CREATE TABLE todo (
    id serial primary key,
    first_name varchar(100),
    last_name varchar (100),
    email varchar (50),
    number varchar (50));

INSERT INTO todo (first_name, last_name, email, number) VALUES ('Иван', 'Федоров', 'ivan@mail.ru','89151231234'),
                                                               ('Алексей', 'Молчанов', 'alex@mail.ru','89151231235'),
                                                               ('Иван', 'Петров', 'ivanPetrov@mail.ru','89151231236'),
                                                               ('Михаил', 'Галустян', 'mixa@mail.ru','89151231237'),
                                                               ('Евгений', 'Караваев', 'evgen@mail.ru','89151231238'),
                                                               ('Николай', 'Никишин', 'nykolay@mail.ru','89151231239'),
                                                               ('Алексей', 'Волков', 'volk@mail.ru','89151231240');