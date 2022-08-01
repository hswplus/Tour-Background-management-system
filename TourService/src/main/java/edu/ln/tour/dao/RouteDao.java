package edu.ln.tour.dao;

import edu.ln.tour.pojo.Route;
import org.apache.ibatis.annotations.Mapper;



import java.util.List;

@Mapper
public interface RouteDao extends tk.mybatis.mapper.common.Mapper<Route> {
    public List<Route> findAll();
    public List<Route> findAllWithSeller();
    public Route selectByIdWithSeller(Integer rid);
    List<Route> findRouteByCid(int cid);

    void removeRouteAndFavoriteRelationsByRid(int rid);
    void removeRouteAndRouteImgRelationsByRid(Integer rid);
    void removeRouteByRid(Integer rid);

    int changeStatus(String rflag, int rid);

    void removeRouteAndCategoryRouteRelationsByRid(Integer rid);
}

