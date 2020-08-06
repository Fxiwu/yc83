package dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import bean.Favorite;

public interface FavoriteMapper {

	@Insert("insert into favorite values(null"
			+ ",#{flabel},#{furl},#{fdesc},#{ftags})")
	@Options(useGeneratedKeys=true,
	
			keyColumn="fid",
			keyProperty="fid")
	int insert(Favorite f);
}
