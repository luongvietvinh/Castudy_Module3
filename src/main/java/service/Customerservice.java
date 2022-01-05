package service;

import config.Dao.CustomerDao;
import model.Customer;

public class Customerservice {
    CustomerDao customerDao = new CustomerDao();

    public  void add (Customer customer) {
        customerDao.createCustomerDao(customer);
        customerDao.showListCustomer();
    }

    public void delete (int id) {
        customerDao.deleteCustomeDao(id);
        customerDao.showListCustomer();
    }
    public void edit(int id,Customer customer) {
        customerDao.updateCustomer(id,customer);
        customerDao.showListCustomer();
    }
}
