package dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import bean.Favorite;
import bean.Tag;
import bean.TagFavorite;

public interface TagFavoriteMapper {
	
	
	@Insert("insert into tagfavorite values(#{tid},#{fid})")
	 
	int insert(TagFavorite f);
}
