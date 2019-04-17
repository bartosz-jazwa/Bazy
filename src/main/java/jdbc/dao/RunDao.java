package jdbc.dao;

import jdbc.entity.Run;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface RunDao {
    void save(Run run) throws SQLException;
    void delete(Integer id) throws SQLException;
    Run getRun(Integer id) throws SQLException;
    List<Run> getAll() throws SQLException;
    void update(Run run) throws SQLException;
    List<Run> getByDate(Date start, Date end) throws SQLException;

}
