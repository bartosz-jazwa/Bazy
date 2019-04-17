package hibernate.dao;

import hibernate.entity.Member;

import java.util.List;

public interface MemberDao {
    void save(Member run);
    void delete(Integer id);
    Member getMember(Integer id);
    List<Member> getAll();
}
