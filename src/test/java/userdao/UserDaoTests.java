package userdao;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

public class UserDaoTests {
    private UserDao userDao;

    @Before
    public void setup(){
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test
    public void testGet() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "강민규";
        String password = "1234";
        User user = userDao.get(id);
        assertThat(user.getId(), CoreMatchers.is(id));
        assertThat(user.getName(), CoreMatchers.is(name));
        assertThat(user.getPassword(), CoreMatchers.is(password));
    }

    @Test
    public void testAdd() throws SQLException, ClassNotFoundException {
        String name = "알렉산드로";
        String password = "1111";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);
        user.setId(id);

        String changedName = "이청길";
        String changedPassword = "2222";
        user.setName(changedName);
        user.setPassword(changedPassword);

        userDao.update(user);

        User resultUser = userDao.get(id);
        assertThat(resultUser.getId(), CoreMatchers.is(id));
        assertThat(resultUser.getName(), CoreMatchers.is(changedName));
        assertThat(resultUser.getPassword(), CoreMatchers.is(changedPassword));
    }

    @Test
    public void testUpdate() throws SQLException, ClassNotFoundException {
        String name = "알렉산드로";
        String password = "1111";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);
        User resultUser = userDao.get(id);
        assertThat(resultUser.getId(), CoreMatchers.is(id));
        assertThat(resultUser.getName(), CoreMatchers.is(name));
        assertThat(resultUser.getPassword(), CoreMatchers.is(password));
    }

    @Test
    public void testDelete() throws SQLException, ClassNotFoundException {
        String name = "이청길";
        String password = "2222";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);

        userDao.delete(id);

        user = userDao.get(id);
        assertThat(user, nullValue());
    }

//    @Test
//    public void testHallaGet() throws SQLException, ClassNotFoundException {
//        Long id = 1l;
//        String name = "강민규";
//        String password = "1234";
//        DaoFactory daoFactory = new DaoFactory();
//        UserDao userDao = daoFactory.getUserDao();
//        User user = userDao.get(id);
//        assertThat(user.getId(), Is.is(id));
//        assertThat(user.getName(), Is.is(name));
//        assertThat(user.getPassword(), Is.is(password));
//    }

}
