package tool;

import org.apache.ibatis.session.RowBounds;

/**
 * 用于分页的工具类
 * pageData是传过来的携带分页数据的json
 * @time 2017年7月1日9:41:39
 */
public class PageTool {

    /**
     * 根据rowBound来分页
     * @param pageData
     * @return
     * @time 2017年7月1日9:42:05
     */
    public static RowBounds device(String pageData){

        int defaultPageNum = 1;
        int defaultPageSize = 6;
        Integer targetPage;
        RowBounds rowBounds;
        if(pageData == null){
            rowBounds = new RowBounds((defaultPageNum-1)*defaultPageSize,defaultPageSize);
        }else{
            targetPage = Integer.parseInt(JsonToMap.toHashMap(pageData).get("targetPage").toString());
            rowBounds = new RowBounds((targetPage-1)*defaultPageSize,defaultPageSize);
        }

        return rowBounds;
    }

}
