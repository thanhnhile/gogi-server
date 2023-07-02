package vn.com.gigo.services.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.gigo.entities.custom.StoreAndCountOrders;
import vn.com.gigo.entities.custom.DailyRevenue;
import vn.com.gigo.repositories.OrderRepository;
import vn.com.gigo.repositories.ProductRepository;
import vn.com.gigo.services.StatisticsService;
import vn.com.gigo.utils.OrderStatus;

@Service
public class StatisticsServiceImpl implements StatisticsService {
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private ProductRepository productRepo;


	@Override
	public Object getAdmindStatistics() {
		HashMap<String, Object> result = new HashMap<>();
		//get count products
		result.put("countProducts", productRepo.count());
		//get count orders
		result.put("countOrders",  orderRepo.getCountOrdersToday());
		//get sumTotal order today
		result.put("total", orderRepo.getSumOrderTotal());
		//get count order by status
		result.put("inProgress", orderRepo.countByStatusEquals(OrderStatus.IN_PROGRESS.getValue()));
		result.put("delivering", orderRepo.countByStatusEquals(OrderStatus.DELIVERING.getValue()));
		result.put("success", orderRepo.countByStatusEquals(OrderStatus.SUCCESS.getValue()));
		result.put("canceled", orderRepo.countByStatusEquals(OrderStatus.CANCELED.getValue()));
		//get count order group by store today
		List<StoreAndCountOrders> list = getCountOrdersGroupByStore();
		result.put("stores", list);
		//get Weekly Revenue
		List<DailyRevenue> list2 = getDailyRevenue();
		result.put("weeklyRevenue", list2);
//		List<Object[]> rs = orderRepo.getWeeklyRevenue();
		return result;
	}
	private List<StoreAndCountOrders> getCountOrdersGroupByStore(){
		List<StoreAndCountOrders> list = new ArrayList<StoreAndCountOrders>();
		List<Object[]> rs = orderRepo.getCountOrderGroupByStore();
		rs.forEach(row -> {
			StoreAndCountOrders item = new StoreAndCountOrders();
			item.setStoreId(((BigInteger)row[0]).longValue());
			item.setStoreName((String) row[1]);
			item.setStoreAddress((String) row[2]);
			item.setCountOrders(((BigInteger)row[3]).intValue());
			list.add(item);
		});
		return list;
	}
	private List<DailyRevenue> getDailyRevenue(){
		List<DailyRevenue> list = new ArrayList<>();
		List<Object[]> rs = orderRepo.getWeeklyRevenue();
		rs.forEach(row -> {
			DailyRevenue item = new DailyRevenue();
			item.setRevenue((Double)row[0]);
			item.setDate((Date) row[2]);
			item.setCountOrders(((BigInteger)row[1]).intValue());
			list.add(item);
		});
		return list;
	}

}
