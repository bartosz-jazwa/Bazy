package hibernate;


import hibernate.dao.MemberDao;
import hibernate.dao.MemberDaoImpl;
import hibernate.dao.RunDao;
import hibernate.dao.RunDaoImpl;
import hibernate.entity.Member;
import hibernate.entity.Run;

import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {
        /*Component component = new Component();
        component.getServers().add(Protocol.HTTP,9000);

        component.getDefaultHost().attach("/runs", RunEndpoint.class);
        component.getDefaultHost().attach("/runs/{runId}", RunEndpoint.class);

        component.start();*/

        RunDao dao = new RunDaoImpl();
        Run run = dao.getRun(4);

        MemberDao memberDao = new MemberDaoImpl();
        Member member = memberDao.getMember(10);

        Set<Run> runSet=  member.getRun();

    }
}
