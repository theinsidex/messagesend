package ru.neoflex.sender.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.neoflex.sender.Model.User;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class UserRepoMDB implements UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(User user) {
        List<User> users = findAll();
        if(users.size()==0){
            user.setId(0);
            mongoTemplate.insert(user);
        }
        else {
            long index =  users.get(users.size()-1).getId()+1;
            user.setId(index);
            mongoTemplate.insert(user);
        }
    }

    @Override
    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public User findById(long id) {
        Query query = new Query(where("_id").is(id));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public void remove(long id) {
        mongoTemplate.remove(findById(id));
    }
}
