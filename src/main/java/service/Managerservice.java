package service;

import config.Dao.AdminDao;
import model.Admin;

public class Managerservice {
    AdminDao adminDao = new AdminDao();

    public  void add (Admin admin){
        adminDao.createManagerDao(admin);
        adminDao.showListManager();
    }
    public  void edit (int id,Admin admin ){
        adminDao.updateManager(id,admin);
        adminDao.showListManager();
    }

    public void delete (int id){
        adminDao.deleteManagerDao(id);
        adminDao.showListManager();
    }

}
