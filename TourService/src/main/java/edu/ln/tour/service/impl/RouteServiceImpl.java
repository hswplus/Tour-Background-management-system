package edu.ln.tour.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.ln.tour.dao.RouteDao;
import edu.ln.tour.dto.PageResultDto;
import edu.ln.tour.pojo.Route;
import edu.ln.tour.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    RouteDao routeDao;

    @Override
    public List<Route> findAll() {
        return routeDao.findAll();
    }

    @Override
    public List<Route> findAllWithSeller() {
        return routeDao.findAllWithSeller();
    }


    @Override
    public Route selectByIdWithSeller(Integer rid) {
        return routeDao.selectByIdWithSeller(rid);
    }

    @Override
    public int saveRoute(Route route) {
        // 日期内容截取
        route.setRdate(route.getRdate().substring(0, 10));
        return routeDao.insert(route);
    }

    // 根据cid查找产品线路
    @Override
    public List<Route> findRouteByCid(int cid) {
        return routeDao.findRouteByCid(cid);
    }

    @Override
    public PageResultDto findAllPage(int pageNo, int pageSize) {

        // 通过插件进行翻页
        PageHelper.startPage(pageNo, pageSize);

        Page<Route> routePage = (Page<Route>) routeDao.findAll();
        PageResultDto resultDto = new PageResultDto();
        resultDto.setPageSize(pageSize);
        resultDto.setPageNo(pageNo);
        resultDto.setData(routePage.getResult());
        resultDto.setPageTotal((int) routePage.getTotal());
        return resultDto;
    }

    // 根据查询条件进行查询
    @Override
    public PageResultDto findByQueryStringPage(int pageNo, int pageSize, String queryString) {
        // 分页处理
        PageHelper.startPage(pageNo, pageSize);
        // 使用通用Mapper的Example
        Example example = new Example(Route.class);
        // 用example来构成查询条件
        example.createCriteria().andLike("rname", "%"+queryString+"%");
        Page<Route> routePage = (Page<Route>) routeDao.selectByExample(example);

        PageResultDto resultDto = new PageResultDto(pageNo,pageSize,(int) routePage.getTotal(),queryString,routePage.getResult());

        return resultDto;
    }

    @Override
    public void removeRouteByRid(Integer rid) {
        routeDao.removeRouteAndFavoriteRelationsByRid(rid);
        routeDao.removeRouteAndRouteImgRelationsByRid(rid);
        routeDao.removeRouteAndCategoryRouteRelationsByRid(rid);

        routeDao.removeRouteByRid(rid);
    }

    @Override
    public int updateRoute(Route route) {
        return routeDao.updateByPrimaryKey(route);
    }

    //修改是否上架
    @Override
    public int changeStatus(String rflag, int rid) {
        return routeDao.changeStatus(rflag,rid);
    }
}
