package cn.e3mall.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;
import cn.e3mall.pojo.TbContentCategoryExample.Criteria;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private TbContentCategoryMapper contentcategoryMapper;
	public List<EasyUITreeNode> getContentCatList(Long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentcategoryMapper.selectByExample(example);
		List<EasyUITreeNode> nodeList =  new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			nodeList.add(node);
		}
		return nodeList;
	}
	
	public E3Result addContentCategory(long parentId, String name) {
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		//排序默认1
		contentCategory.setSortOrder(1);
		//1.正常 2.删除
		contentCategory.setStatus(1);
		contentCategory.setName(name);
		contentCategory.setParentId(parentId);
		contentCategory.setIsParent(false);
		contentcategoryMapper.insert(contentCategory);
		//修改父结点的isparent属性
		TbContentCategory parent = contentcategoryMapper.selectByPrimaryKey(parentId);
		if(!parent.getIsParent()) {
			parent.setIsParent(true);
			contentcategoryMapper.updateByPrimaryKey(parent);
		}
		
		return E3Result.ok(contentCategory);
	}

	public E3Result updateContentCategory(Long id, String name) {
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setId(id);
		contentCategory.setName(name);
		contentCategory.setUpdated(new Date());
		contentcategoryMapper.updateByPrimaryKeySelective(contentCategory);	
		return E3Result.ok();
	}

}
