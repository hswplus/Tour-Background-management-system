package edu.ln.tour.service.impl;

import edu.ln.tour.dao.SupUserDao;
import edu.ln.tour.pojo.SupUser;
import edu.ln.tour.service.SupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupUserServiceImpl implements SupUserService {
    @Autowired
    SupUserDao supUserDao;
    @Override
    public int login(SupUser supUser) {
        SupUser user = supUserDao.selectByPrimaryKey(supUser.getAid());
        if (user.getApassword() != null && user.getApassword().equals(supUser.getApassword())) {
            return 1;
        }
        return 0;
    }
}
