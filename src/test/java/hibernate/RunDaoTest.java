package hibernate;

import hibernate.dao.RunDao;
import hibernate.entity.Run;
import hibernate.utils.DaoProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

public class RunDaoTest {
    @Test
    public void saveTest(){
        RunDao dao = DaoProvider.getInstance().getRunDao();
        Run run = new Run();

        run.setName(UUID.randomUUID().toString());
        run.setPlace(UUID.randomUUID().toString());
        run.setStartDate(new Date());

        dao.save(run);

        Run test = dao.getRun(run.getId());
        Assertions.assertNotNull(test);
        Assertions.assertEquals(run.getName(),test.getName());
        Assertions.assertEquals(run.getPlace(),test.getPlace());
    }

    @Test
    public void deleteTest(){
        RunDao dao = DaoProvider.getInstance().getRunDao();
        Run run = new Run();
        run.setName(UUID.randomUUID().toString());
        run.setPlace(UUID.randomUUID().toString());
        run.setStartDate(new Date());

        dao.save(run);
        dao.delete(run.getId());
        Run test = dao.getRun(run.getId());
        Assertions.assertNull(test);
    }
}
