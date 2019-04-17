package jdbc;

import jdbc.dao.RunDao;
import jdbc.daoimpl.RunDaoImpl;
import jdbc.entity.Run;

import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class Main {


    public static void main(String[] args) throws SQLException {

        Run run = new Run();
        run.setId(1);
        run.setName("rzeszowska piatka");
        run.setPlace("Rzeszow");
        run.setStartDate(new Date());
        run.setStartTime(new Date());
        run.setMembersLimit(100);

        RunDao runDao = new RunDaoImpl();
        //runDao.save(run);


        Run run2 = new Run();
        run2.setId(2);
        run2.setName("rzeszowska piatka");
        run2.setPlace("Rzeszow");
        run2.setStartDate(new Date());
        run2.setStartTime(new Date());
        run2.setMembersLimit(100);


        //runDao.save(run2);

        run.setName("Krakowska piatka");
        run.setPlace("Krakow");
        //runDao.update(run);

        //run = runDao.getRun(1);

        List<Run> runList = runDao.getByDate(Date.from(Instant.now()),Date.from(Instant.now()));
    }

}
