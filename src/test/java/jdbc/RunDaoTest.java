package jdbc;

import jdbc.dao.RunDao;
import jdbc.daoimpl.RunDaoImpl;
import jdbc.entity.Run;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RunDaoTest {
    @BeforeEach
    void cleanTable() {
        RunDao dao = new RunDaoImpl();
        try {
            List<Run> runs = dao.getAll();
            for (Run r : runs) {
                dao.delete(r.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveTest() {
        RunDao dao = new RunDaoImpl();
        Run run = new Run();
        run.setId(10);
        run.setName("Podbieg");
        run.setMembersLimit(100);
        run.setPlace("Rzeszow");
        run.setStartDate(new Date());
        run.setStartTime(new Date());

        try {
            dao.save(run);
            Run testResult = dao.getRun(run.getId());
            assertNotNull(testResult);
            assertEquals(run.getName(), testResult.getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteTest() {
        RunDao dao = new RunDaoImpl();
        Run run = new Run();
        run.setId(10);
        run.setName("Podbieg");
        run.setMembersLimit(100);
        run.setPlace("Rzeszow");
        run.setStartDate(new Date());
        run.setStartTime(new Date());

        try {
            dao.save(run);
            Run testResult = dao.getRun(run.getId());
            assertNotNull(testResult);
            dao.delete(run.getId());
            testResult = dao.getRun(run.getId());
            assertNull(testResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateTest() {
        RunDao dao = new RunDaoImpl();
        Run run = new Run();
        run.setId(10);
        run.setName("Podbieg");
        run.setMembersLimit(100);
        run.setPlace("Rzeszow");
        run.setStartDate(new Date());
        run.setStartTime(new Date());
        try {
            dao.save(run);
            run.setName("przebieg");
            dao.update(run);
            Run testResult = dao.getRun(run.getId());
            assertNotNull(testResult);
            assertEquals(run.getName(), testResult.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllTest() {
        RunDao dao = new RunDaoImpl();
        List<Run> runList = new ArrayList<>();
        try {
            for (int i = 0; i < 100; i++) {
                Run run = new Run();
                run.setId(i);
                run.setName(UUID.randomUUID().toString());
                run.setMembersLimit(new Random().nextInt());
                run.setPlace(UUID.randomUUID().toString());
                run.setStartDate(new Date());
                run.setStartTime(new Date());
                runList.add(run);
                dao.save(run);
            }

            List<Run> testResult = dao.getAll();
            assertNotNull(testResult);

            for (int i = 0; i < runList.size(); i++) {
                assertEquals(runList.get(i).getPlace(), testResult.get(i).getPlace());
                assertEquals(runList.get(i).getName(), testResult.get(i).getName());
                assertEquals(runList.get(i).getId(), testResult.get(i).getId());
                assertEquals(runList.get(i).getMembersLimit(), testResult.get(i).getMembersLimit());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
