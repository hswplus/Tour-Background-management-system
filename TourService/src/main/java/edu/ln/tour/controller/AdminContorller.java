package edu.ln.tour.controller;

import edu.ln.tour.dao.SupUserDao;
import edu.ln.tour.dto.RespDto;
import edu.ln.tour.pojo.SupUser;
import edu.ln.tour.service.SupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController("/")
public class AdminContorller {
    @Autowired
    SupUserService supUserService;
    @PostMapping("/login")
    public RespDto login(@RequestBody SupUser supUser){
        int result = supUserService.login(supUser);
        return new RespDto(result, result > 0 ? "登录成功" : "登录失败");
    }
}
