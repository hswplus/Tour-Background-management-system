package edu.ln.tour.controller;

import edu.ln.tour.dto.PageResultDto;
import edu.ln.tour.dto.RespDto;
import edu.ln.tour.pojo.Route;
import edu.ln.tour.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/route")
@CrossOrigin // 允许跨域访问
public class RouteController {
    // 注入 service
    @Autowired
    RouteService routeService;

    // 查询全部的旅游线路
    @RequestMapping("/findAll")
    public List<Route> findAll() {
//        return routeService.findAllWithSeller();
        return routeService.findAllWithSeller();
    }

    // 查询全部的旅游线路
    @RequestMapping("/findAllPage")
    public PageResultDto findAllPage(int pageNo, int pageSize) {
        return routeService.findAllPage(pageNo, pageSize);
    }

    // 根据查询条件进行查询
    @PostMapping("/findByQueryStringPage")
    public PageResultDto findByQueryStringPage(int pageNo, int pageSize, String queryString) {
        return routeService.findByQueryStringPage(pageNo, pageSize,queryString);
    }
    // 保存
    @PostMapping("/saveRoute")
    public RespDto saveRoute(@RequestBody Route route) {
        int res = routeService.saveRoute(route);
        return new RespDto(1, res > 0 ? "处理成功" : "处理失败");
    }

    // 根据cid查找产品线路
    @RequestMapping("/findRouteByCid")
    public List<Route> findRouteByCid(int cid) {
        return routeService.findRouteByCid(cid);
    }

    // LX 修改
    @PostMapping("/updateRoute")
    public RespDto updateRoute(@RequestBody Route route){
        System.out.println(route);
        int updateRet = routeService.updateRoute(route);
        return new RespDto(updateRet,updateRet>0?"处理成功":"处理失败");
    }


    // lyw删除
    @RequestMapping("/removeRouteByRid")
    public RespDto removeRouteByRid(Integer rid){
        try {
            routeService.removeRouteByRid(rid);
        } catch (Exception e) {
            e.printStackTrace();
            return new RespDto(0, "处理失败");
        }
        return new RespDto(1, "处理成功");
    }

    //修改是否上架
    @RequestMapping("/changeStatus")
    public int changeStatus(String rflag,int rid) {
        return routeService.changeStatus(rflag,rid);

    }

}
