package com.muyu.bms.util;

import com.muyu.bms.vo.Borrow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BmsUtil {
/*
		工具方法 start
	 */

	/**
	 * 输入两个时间，一个是开始时间，另一个是结束时间
	 * 两者相比较，判断是不是超过31天
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public boolean checkOverdue(String startTime ,String endTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date end = sdf.parse(endTime);
			Date start = sdf.parse(startTime);
			long day = 60*1000*60*24; // 1天
			if(end.getTime()-(day*31) > start.getTime()){
				return true;
			}else{
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 根据sid查询出这个学生的借书记录
	 * 然后根据借书记录中的借书时间和归还时间判断逾期
	 * 逾期分为下面三种情况
	 * 1. 没借过书，不会逾期
	 * 2. 借了书还没还
	 *    取出借书时间，然后取出当前时间，如果不超过31天，则没逾期
	 *    取出借书时间，然后取出当前时间，如果超过31天，则逾期
	 * 3. 借了书并且还了书
	 *    直接计算借取时间，如果超过31天，则逾期
	 * @param sid
	 * @return
	 */
	public String checkOverdueBySid(int sid,List<Borrow> borrows){
		int num=0;
		if(borrows==null) {
			return "无逾期记录";
		}else{
			for (Borrow borrow : borrows) {
				if(borrow!=null) {
					if ("".equals(borrow.getReturnTime()) || borrow.getReturnTime() == null) {
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String nowTime = sdf.format(date);
						if (checkOverdue(borrow.getBorrowTime(), nowTime)) {
							num += 1;
						}
					} else {
						if (checkOverdue(borrow.getBorrowTime(), borrow.getReturnTime())) {
							num += 1;
						}
					}
				}
			}
			if(num==0){
				return "无逾期记录";
			}else{
				return "逾期"+num+"次";
			}
		}

	}
	/*
		工具方法 end
	 */
}
