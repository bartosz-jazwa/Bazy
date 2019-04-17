package hibernate.utils;

import hibernate.dao.*;

public class DaoProvider {
    private static DaoProvider ourInstance = new DaoProvider();
    private RunDao runDao;
    private MemberDao memberDao;
    private ChipDao chipDao;
    public static DaoProvider getInstance() {
        return ourInstance;
    }

    private DaoProvider() {
        runDao = new RunDaoImpl();
        memberDao = new MemberDaoImpl();
        chipDao = new ChipDaoImpl();
    }

    public RunDao getRunDao() {
        return runDao;
    }

    public MemberDao getMemberDao() {
        return memberDao;
    }

    public ChipDao getChipDao() {
        return chipDao;
    }
}
