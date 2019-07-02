package com.fans.service.impl;

import com.fans.model.House;
import com.fans.page.PageParams;
import com.fans.service.interfaces.HouseService;
import com.fans.service.interfaces.RecommendService;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName RecommendServiceImpl
 * @Description:
 * @Author fan
 * @Date 2019-07-02 11:35
 * @Version 1.0
 **/
@Service(value = "recommendService")
public class RecommendServiceImpl implements RecommendService {

    @Resource(name = "houseService")
    private HouseServiceImpl houseService;

    private final static Logger logger = LoggerFactory.getLogger(RecommendService.class);

    private static final String HOT_HOUSE_KEY = "hot_house";

    @Override
    public void increase(Long id) {
        try {
            String host = "127.0.0.1";
            Jedis jedis = new Jedis(host);
            jedis.zincrby(HOT_HOUSE_KEY, 1.0D, id + "");
            // 0代表第一个元素,-1代表最后一个元素，保留热度由低到高末尾10个房产
            jedis.zremrangeByRank(HOT_HOUSE_KEY, 0, -11);
            jedis.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public List<House> getHotHouse(Integer size) {
        House query = new House();
        List<Long> list = getHot();
        list = list.subList(0, Math.min(list.size(), size));
        if (list.isEmpty()) {
            return Lists.newArrayList();
        }
        query.setIds(list);
        final List<Long> order = list;
        List<House> houses = houseService.queryAndSetImage(query, PageParams.build(size, 1));
        Ordering<House> houseSort = Ordering.natural().onResultOf(hs -> {
            assert hs != null;
            return order.indexOf(hs.getId());
        });
        return houseSort.sortedCopy(houses);
    }

    private List<Long> getHot() {
        try {
            Jedis jedis = new Jedis("127.0.0.1");
            Set<String> idSet = jedis.zrevrange(HOT_HOUSE_KEY, 0, -1);
            jedis.close();
            return idSet.stream().map(Long::parseLong).collect(Collectors.toList());
        } catch (Exception e) {
            //有同学反应在未安装redis时会报500,在这里做下兼容,
            logger.error(e.getMessage(), e);
            return Lists.newArrayList();
        }
    }
}
