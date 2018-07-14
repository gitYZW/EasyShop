package cn.e3mall.sso.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.mapper.TbUserMapper;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.pojo.TbUserExample;
import cn.e3mall.pojo.TbUserExample.Criteria;
import cn.e3mall.sso.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {
	@Autowired
	private TbUserMapper userMapper;
	
	public E3Result checkData(String param, int type) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		//判断数据类型
		if(type==1) {
			criteria.andUsernameEqualTo(param);
		}else if(type==2) {
			criteria.andPhoneEqualTo(param);
		}else if(type==3) {
			criteria.andEmailEqualTo(param);
		}else {
			return E3Result.build(400,"数据类型错误");
		}
		//查询结果
		List<TbUser> list = userMapper.selectByExample(example);
		if(list!=null&&list.size()>0) {
			//数据存在返回false
			return E3Result.ok(false);
		}
		return E3Result.ok(true);
	}

	public E3Result register(TbUser user) {
		//校验数据是否为空
		if(StringUtils.isBlank(user.getUsername())||StringUtils.isBlank(user.getPassword())
				||StringUtils.isBlank(user.getPhone())) {
			return E3Result.build(400,"用户信息不全,注册失败");
		}
		E3Result result = checkData(user.getUsername(),1);
		if(!(boolean) result.getData()) {
			return E3Result.build(400,"用户名已存在");
		}
		result=checkData(user.getPhone(),2);
		if(!(boolean) result.getData()) {
			return E3Result.build(400,"手机号已被注册");
		}
		user.setCreated(new Date());
		user.setUpdated(new Date());
		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Pass);
		userMapper.insert(user);
		return E3Result.ok();
	}

}
