package kr.co.edu.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.edu.common.vo.Test;

@Mapper
public interface CommonDAO {

	public List<Test> testSelectList();
}
