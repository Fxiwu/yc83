import org.apache.ibatis.session.SqlSession;

import bean.Favorite;
import bean.Tag;
import bean.TagFavorite;
import dao.FavoriteMapper;
import dao.TagFavoriteMapper;
import dao.TagMapper;
import util.MyBatisHelper;

public class FavoriteBiz {

	public void addFavorite(Favorite f) {
		 SqlSession session = MyBatisHelper.openSession();
		 FavoriteMapper fm=session.getMapper(FavoriteMapper.class);
		 TagMapper tm=session.getMapper(TagMapper.class);
		 TagFavoriteMapper tfm=session.getMapper(TagFavoriteMapper.class);
		 
		 try {
			//添加链接到favorite表
			 fm.insert(f);
			 //拆分分类ftags
			 String[] tags=f.getFtags().split("[,，；;]");
			 for(String tag:tags) {
				 Tag tagObj=new Tag();
				 //直接修改分类的数量
				 if(tm.updateCount(tag)==0) {
					 //如果返回的结果为0表示没有修改到对应记录，那么就新增
					 tagObj.setTname(tag);
					 tm.insert(tagObj);
					 
				 }else {
					 //如果返回的结果为1表示修改数量成功
					 //并且查询数据对应的tag对象
					 tagObj=tm.selectByName(tag);
				 }
				 //根据分类tid和链接的fid，向中间表写入记录
				 TagFavorite tf=new  TagFavorite();
				 tf.setTid(tagObj.getTid());
				 tf.setFid(f.getFid());
				 tfm.insert(tf);
				 //提交
				 session.commit();
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//回滚
			session.rollback();
		}finally {
			session.close();
		}
	}
}
