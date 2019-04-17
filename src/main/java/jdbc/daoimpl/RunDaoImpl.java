package jdbc.daoimpl;

import jdbc.dao.RunDao;
import jdbc.entity.Run;
import jdbc.utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RunDaoImpl implements RunDao {
    @Override
    public void save(Run run) throws SQLException {
        PreparedStatement statement = JdbcUtils.getInstance().getConnection().prepareStatement("insert into runs(id, name, place, start_date, start_time, members_limit) values (?,?,?,?,?,?)");
        statement.setInt(1,run.getId());
        statement.setString(2,run.getName());
        statement.setString(3,run.getPlace());
        java.sql.Date startDate = new java.sql.Date(run.getStartDate().getTime());
        java.sql.Date startTime = new java.sql.Date(run.getStartTime().getTime());

        statement.setDate(4,startDate);
        statement.setDate(5,startTime);
        statement.setInt(6,run.getMembersLimit());
        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        PreparedStatement statement = JdbcUtils.getInstance().getConnection().prepareStatement("delete from runs where id=?");
        statement.setInt(1,id);
        statement.execute();
        statement.close();
    }

    @Override
    public Run getRun(Integer id) throws SQLException {
        PreparedStatement statement = JdbcUtils.getInstance().getConnection().prepareStatement("select * from runs where id=?");
        statement.setInt(1,id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            Run run = new Run();
            run.setId(resultSet.getInt("id"));
            run.setMembersLimit(resultSet.getInt("members_limit"));
            run.setPlace(resultSet.getString("place"));
            run.setName(resultSet.getString("name"));
            run.setStartTime(resultSet.getTime("start_time"));
            run.setStartDate(resultSet.getDate("start_date"));
            return run;
        }

        return null;
    }

    @Override
    public List<Run> getAll() throws SQLException {
        List<Run> result = new ArrayList<>();

        PreparedStatement statement = JdbcUtils.getInstance().getConnection().prepareStatement("select * from runs");

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Run run = new Run();
            run.setId(resultSet.getInt("id"));
            run.setMembersLimit(resultSet.getInt("members_limit"));
            run.setPlace(resultSet.getString("place"));
            run.setName(resultSet.getString("name"));
            run.setStartTime(resultSet.getTime("start_time"));
            run.setStartDate(resultSet.getDate("start_date"));
            result.add(run);
        }

        return result;
    }

    @Override
    public void update(Run run) throws SQLException {
        PreparedStatement statement = JdbcUtils.getInstance().getConnection().prepareStatement("update runs set name=?, place=?, start_date=?, start_time=?, members_limit=? where id=?");
        statement.setString(1,run.getName());
        statement.setString(2,run.getPlace());
        java.sql.Date startDate = new java.sql.Date(run.getStartDate().getTime());
        java.sql.Date startTime = new java.sql.Date(run.getStartTime().getTime());
        statement.setDate(3,startDate);
        statement.setDate(4,startTime);
        statement.setInt(5,run.getMembersLimit());
        statement.setInt(6,run.getId());
        statement.execute();
        statement.close();
    }

    @Override
    public List<Run> getByDate(Date start, Date end) throws SQLException {
        List<Run> result = new ArrayList<>();

        PreparedStatement statement = JdbcUtils.getInstance().getConnection().prepareStatement("select * from runs where start_date between ? and ?");
        java.sql.Date startDate = new java.sql.Date(start.getTime());
        java.sql.Date endDate = new java.sql.Date(end.getTime());
        statement.setDate(1,startDate);
        statement.setDate(2,endDate);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Run run = new Run();
            run.setId(resultSet.getInt("id"));
            run.setMembersLimit(resultSet.getInt("members_limit"));
            run.setPlace(resultSet.getString("place"));
            run.setName(resultSet.getString("name"));
            run.setStartTime(resultSet.getTime("start_time"));
            run.setStartDate(resultSet.getDate("start_date"));
            result.add(run);
        }

        return result;
    }
}
