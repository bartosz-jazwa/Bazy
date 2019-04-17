package jdbc.dao;

import jdbc.entity.Member;

import java.sql.SQLException;
import java.util.List;

public interface MemberDao {
    void save(Member member) throws SQLException;
    void delete(Integer id) throws SQLException;
    Member getMember(Integer id) throws SQLException;
    List<Member> getAll() throws SQLException;
    void update(Member member) throws SQLException;
}
