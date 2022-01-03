package service;

import config.Dao.ManagerDao;
import model.Admin;

public class Managerservice {
    ManagerDao managerDao = new ManagerDao();

    public  void add (Admin admin){
        managerDao.createManagerDao(admin);
        managerDao.showListManager();
    }
    public  void edit (int id,Admin admin ){
        managerDao.updateManager(id,admin);
        managerDao.showListManager();
    }

    public void delete (int id){
        managerDao.deleteManagerDao(id);
        managerDao.showListManager();
    }

}
