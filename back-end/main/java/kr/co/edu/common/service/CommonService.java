package kr.co.edu.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kr.co.edu.common.dao.CommonDAO;
import kr.co.edu.common.vo.Test;

@Service
public class CommonService {

	@Autowired
	private CommonDAO commonDAO;
	
	public List<Test> testSelectList () {
		return commonDAO.testSelectList();
	}
}
