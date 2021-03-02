package ru.gorbenko.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.gorbenko.models.Students;
import ru.gorbenko.models.Subjects;

import java.util.List;

@Component
public class SubjectDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SubjectDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Subjects> showAll(){ return jdbcTemplate.query("SELECT * FROM subject", new BeanPropertyRowMapper<>(Subjects.class));}
    public List<Subjects> showMaxHour(){ return jdbcTemplate.query("select t1.* from subject as t1 \n" +
            "inner join \n" +
            "(select max(hour), semester from subject group by semester) as t2\n" +
            "on t1.hour=t2.max and t1.semester=t2.semester\n" +
            "order by t1.semester;", new BeanPropertyRowMapper<>(Subjects.class));}

}
