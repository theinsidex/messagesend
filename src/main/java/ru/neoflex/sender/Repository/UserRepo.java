package ru.neoflex.sender.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.neoflex.sender.Model.User;
import ru.neoflex.sender.Repository.Mapper.UserMapper;

import java.util.List;

@Repository
public class UserRepo implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final String ADDUSER="INSERT INTO User(name,email) VALUES (?,?)";
    private final String LISTUSERS="SELECT * FROM User";
    private final String GETUSER="SELECT * FROM User WHERE id=?";
    private final String DELETEUSER="DELETE FROM User WHERE id=?";

    public void insert(User user) {
        jdbcTemplate.update(ADDUSER,user.getName(),user.getEmail());
    }

    public List<User> findAll() {
       List<User> users = jdbcTemplate.query(LISTUSERS,new UserMapper());
        return users;
    }

    public User findById(long id) {
        User user = (User) jdbcTemplate.queryForObject(GETUSER,new Object[]{id},new UserMapper());
        return user;
    }

    public void remove(long id) {
        jdbcTemplate.update(DELETEUSER,id);
    }
}
