package tool;

import org.apache.ibatis.session.RowBounds;

/**
 * pageData是传过来的携带分页数据的json
 */
public class PageTool {

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
