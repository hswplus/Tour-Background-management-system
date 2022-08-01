package edu.ln.tour.controller;


import edu.ln.tour.dto.PageResultDto;
import edu.ln.tour.dto.RespDto;
import edu.ln.tour.pojo.User;
import edu.ln.tour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    // 查询全部的用户
    @RequestMapping("/findAllUserPage")
    public PageResultDto findAllUserPage(int pageNo, int pageSize) {
        return userService.findAllUserPage(pageNo, pageSize);
    }

    // 根据查询条件进行查询
    @PostMapping("/findByUserPageQueryString")
    public PageResultDto findByUserPageQueryString(int pageNo, int pageSize, String queryString) {
        return userService.findByUserPageQueryString(pageNo, pageSize,queryString);
    }
    //添加用户
    @RequestMapping(value = "/insertUser")
    public RespDto insertUser(@RequestBody User user)
    {
        int i = userService.insertUser(user);
        return  new RespDto(i,"添加成功！",null) ;
    }

    //删除用户
    @RequestMapping(value = "/deleteUser")
    public RespDto delectUser(int uid){
        int ru=userService.deleteUser(uid);
        return new RespDto(ru,ru>=0?"处理成功":"处理失败",null);
    }

    //修改用户数据
    @RequestMapping(value = "/updateUser")
    public int updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    //修改用户状态
    @RequestMapping(value = "/updateStatus")
    public int updateStatus(String status,int uid)
    {
        return userService.updateStatus(status,uid);
    }

}

