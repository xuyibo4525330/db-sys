package com.jt.sys.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.exception.ServiceException;
import com.jt.common.vo.PageObject;
import com.jt.sys.dao.SysLogDao;
import com.jt.sys.entity.SysLog;
import com.jt.sys.service.SysLogService;
@Service
public class SysLogServiceImpl implements SysLogService {
	@Autowired
	private SysLogDao sysLogDao;
	
	@Override
	public int deleteObjects(Integer... ids) {
		//1.参数有效性验证
		if(ids==null||ids.length==0)
		throw new IllegalArgumentException("请先选中记录");
		//2.执行删除操作
		int rows=0;
		try{
		rows=sysLogDao.deleteObjects(ids);
		}catch(Throwable e){
		e.printStackTrace();
		//给运维人员发短信,并报警.
		throw new ServiceException("系统维护中,稍等片刻");
		}
		//3.验证并返回结果
		if(rows==0)
		throw new ServiceException("记录可能已经不存在");
		return rows;
	}

	@Override
	public PageObject<SysLog> findPageObjects(
			String username,
			Integer pageCurrent) {
		//1.参数有效性验证(只验证pageCurrent)
		if(pageCurrent==null||pageCurrent<1)
		throw new IllegalArgumentException("页码值不正确");
		//2.查询总记录数并进行验证
		int rowCount=sysLogDao.getRowCount(username);
		if(rowCount==0)
	    throw new ServiceException("没有对应记录");
		//3.查询当前页日志数据
		int pageSize=2;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysLog> records=
		sysLogDao.findPageObjects(username,
				startIndex, pageSize);
		//4.对查询结果进行封装
		PageObject<SysLog> po=new PageObject<>();
		po.setRecords(records);
		po.setRowCount(rowCount);
		po.setPageSize(pageSize);
		po.setPageCurrent(pageCurrent);
	  /*int pageCount=rowCount/pageSize;
		if(rowCount%pageSize!=0){
			pageCount++;
		}*/
		//po.setPageCount((rowCount-1)/pageSize+1);
		//5.返回结果
		return po;
	}
}
