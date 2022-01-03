package service;

import config.Dao.ManagerDao;
import model.Manager;

public class Managerservice {
    ManagerDao managerDao = new ManagerDao();

    public  void add (Manager manager){
        managerDao.createManagerDao(manager);
        managerDao.showListManager();
    }
    public  void edit (int id, Manager manager){
        managerDao.updateManager(id,manager);
        managerDao.showListManager();
    }

    public void delete (int id){
        managerDao.deleteManagerDao(id);
        managerDao.showListManager();
    }

}
