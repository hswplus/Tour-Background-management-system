package edu.ln.tour.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.ln.tour.dao.UserDao;
import edu.ln.tour.dao.UserDao;
import edu.ln.tour.dto.PageResultDto;
import edu.ln.tour.pojo.User;
import edu.ln.tour.service.UserService;
import edu.ln.tour.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public Page<User> listByPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Page<User> pageUsers =(Page<User> ) userDao.findAll();
        System.out.println("总条数:"+pageUsers.getTotal());
        return pageUsers;
    }

    @Override
    public PageResultDto findByUserPageQueryString(int pageNo, int pageSize, String queryString) {
        // 分页处理
        PageHelper.startPage(pageNo, pageSize);
        // 使用通用Mapper的Example
        Example example = new Example(User.class);
        // 用example来构成查询条件
        example.createCriteria().andLike("username", "%"+queryString+"%");
        example.or().andLike("name", "%"+queryString+"%");
        example.or().andLike("telephone", "%"+queryString+"%");
        Page<User> userPage = (Page<User>) userDao.selectByExample(example);
        PageResultDto resultDto = new PageResultDto(pageNo,pageSize,(int) userPage.getTotal(),queryString,userPage.getResult());

        return resultDto;
    }

    @Override
    public PageResultDto findAllUserPage(int pageNo, int pageSize) {
        // 通过插件进行翻页
        PageHelper.startPage(pageNo, pageSize);

        Page<User> routePage = (Page<User>) userDao.findAll();
        PageResultDto resultDto = new PageResultDto();
        resultDto.setPageSize(pageSize);
        resultDto.setPageNo(pageNo);
        resultDto.setData(routePage.getResult());
        resultDto.setPageTotal((int) routePage.getTotal());
        return resultDto;
    }

    @Override
    public int insertUser(User user){
        int i=0;
        try {
            String newPassword = Md5Util.encodeByMd5(user.getPassword());
            user.setPassword(newPassword); // 将加密后的密码设置到user对象里面去
            i = userDao.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int deleteUser(int uid) {
        return userDao.deleteByPrimaryKey(uid);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateByPrimaryKey(user);
    }

    @Override
    public int updateStatus(String status, int uid) {
        return userDao.updateStatus(status,uid);
    }
}
