package ru.gorbenko.DAO;


import ru.gorbenko.models.Students;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentsDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public  StudentsDAO (JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Students> showAll(){ return jdbcTemplate.query("SELECT * FROM students", new BeanPropertyRowMapper<>(Students.class));}
    public List<Students> showSelected(int kurs, int age){ return jdbcTemplate.query("SELECT * FROM students WHERE age>? and kurs=?",
            new Object[]{age, kurs},
            new BeanPropertyRowMapper<>(Students.class));}
}
