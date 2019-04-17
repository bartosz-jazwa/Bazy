package jdbc.daoimpl;

import jdbc.dao.MemberDao;
import jdbc.entity.Member;
import jdbc.utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl implements MemberDao {

    @Override
    public void save(Member member) throws SQLException {
        PreparedStatement statement = JdbcUtils.getInstance().getConnection().prepareStatement("insert into members(id, name, last_name, start_number, run_id) values (?,?,?,?,?)");
        statement.setInt(1,member.getId());
        statement.setString(2,member.getName());
        statement.setString(3,member.getLast_name());
        statement.setInt(4,member.getStart_number());
        statement.setInt(5,member.getRun_id());
        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        PreparedStatement statement = JdbcUtils.getInstance().getConnection().prepareStatement("delete from members where id=?");
        statement.setInt(1,id);
        statement.execute();
        statement.close();
    }

    @Override
    public Member getMember(Integer id) throws SQLException {
        PreparedStatement statement = JdbcUtils.getInstance().getConnection().prepareStatement("select * from members where id=?");
        statement.setInt(1,id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            Member member = new Member();
            member.setId(resultSet.getInt("id"));
            member.setName(resultSet.getString("name"));
            member.setLast_name(resultSet.getString("last_name"));
            member.setStart_number(resultSet.getInt("start_number"));
            member.setRun_id(resultSet.getInt("run_id"));
            return member;
        }
        return null;
    }

    @Override
    public List<Member> getAll() throws SQLException {
        List<Member> result = new ArrayList<>();

        PreparedStatement statement = JdbcUtils.getInstance().getConnection().prepareStatement("select * from members");

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Member member = new Member();
            member.setId(resultSet.getInt("id"));
            member.setName(resultSet.getString("name"));
            member.setLast_name(resultSet.getString("last_name"));
            member.setStart_number(resultSet.getInt("start_number"));
            member.setRun_id(resultSet.getInt("run_id"));
            result.add(member);
        }

        return result;
    }

    @Override
    public void update(Member member) throws SQLException {
        PreparedStatement statement = JdbcUtils.getInstance().getConnection().prepareStatement("update members set name=?, last_name=?, start_number=?, run_id=? where id=?");
        statement.setString(1,member.getName());
        statement.setString(2,member.getLast_name());
        statement.setInt(3,member.getStart_number());
        statement.setInt(4,member.getRun_id());
        statement.setInt(5,member.getId());
        statement.execute();
        statement.close();
    }
}
