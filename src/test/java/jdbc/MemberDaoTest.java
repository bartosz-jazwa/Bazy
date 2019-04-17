package jdbc;

import jdbc.dao.MemberDao;
import jdbc.daoimpl.MemberDaoImpl;
import jdbc.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class MemberDaoTest {
    @BeforeEach
    void cleanTable() {
        MemberDao dao = new MemberDaoImpl();
        try {
            List<Member> members = dao.getAll();
            for (Member m : members) {
                dao.delete(m.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveTest() {
        MemberDao dao = new MemberDaoImpl();
        Member member = new Member();
        member.setId(10);
        member.setName("Jozek");
        member.setLast_name("Kowalski");
        member.setStart_number(100);
        member.setRun_id(10);

        try {
            dao.save(member);
            Member testResult = dao.getMember(member.getId());
            assertNotNull(testResult);
            assertEquals(member.getName(), testResult.getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void deleteTest() {
        MemberDao dao = new MemberDaoImpl();
        Member member = new Member();
        member.setId(10);
        member.setName("Jozek");
        member.setLast_name("Kowalski");
        member.setStart_number(100);
        member.setRun_id(10);

        try {
            dao.save(member);
            Member testResult = dao.getMember(member.getId());
            assertNotNull(testResult);
            dao.delete(member.getId());
            testResult = dao.getMember(member.getId());
            assertNull(testResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void updateTest() {
        MemberDao dao = new MemberDaoImpl();
        Member member = new Member();
        member.setId(10);
        member.setName("Jan");
        member.setLast_name("Kowalski");
        member.setStart_number(100);
        member.setRun_id(10);
        try {
            dao.save(member);
            member.setName("Adam");
            member.setLast_name("Nowak");
            member.setStart_number(200);
            member.setRun_id(2);
            dao.update(member);
            Member testResult = dao.getMember(member.getId());
            assertNotNull(testResult);
            assertEquals(member.getName(), testResult.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getAllTest() {
        MemberDao dao = new MemberDaoImpl();
        List<Member> members = new ArrayList<>();
        try {
            for (int i = 0; i < 100; i++) {
                Member member = new Member();
                member.setId(i);
                member.setName(UUID.randomUUID().toString());
                member.setLast_name(UUID.randomUUID().toString());
                member.setStart_number(new Random().nextInt());
                member.setRun_id(i);
                members.add(member);
                dao.save(member);
            }

            List<Member> testResult = dao.getAll();
            assertNotNull(testResult);

            for (int i = 0; i < members.size(); i++) {
                assertEquals(members.get(i).getId(),testResult.get(i).getId());
                assertEquals(members.get(i).getName(), testResult.get(i).getName());
                assertEquals(members.get(i).getLast_name(), testResult.get(i).getLast_name());
                assertEquals(members.get(i).getStart_number(), testResult.get(i).getStart_number());
                assertEquals(members.get(i).getRun_id(), testResult.get(i).getRun_id());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
