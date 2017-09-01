package tool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 无向数据库索要数据的分页工具
 * @time 2017年7月25日9:45:18
 */
public class UnDataBasePageTool<T> implements Serializable {

    public List<Object> list = null;
    private int recordCount = 0;  //记录数
    private int pageSize = 0;   //每页记录数
    private int maxPage = 0;    //最大页数
    // 初始化分页信息
    public List getInitPage(List list, int Page, int pageSize) {
        List<Object> newList = new ArrayList<Object>();
        this.list = list;
        recordCount = list.size();
        this.pageSize = pageSize;
        this.maxPage = getMaxPage();
        Page = Math.round(Page);
        try {
            for (int i = (Page - 1) * pageSize; i <= Page * pageSize - 1; i++) {
                try {
                    if (i >= recordCount) {
                        break;
                    }
                } catch (Exception e) {

                }
                newList.add((Object) list.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newList;
    }


    // 获取指定页的数据
    public List<Object> getAppointPage(int Page) {
        Page = Math.round(Page);
        List<Object> newList = new ArrayList<Object>();
        try {
            for (int i = (Page - 1) * pageSize; i <= Page * pageSize - 1; i++) {
                try {
                    if (i >= recordCount) {
                        break;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                newList.add((Object) list.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newList;
    }

    // 获取最大记录数
    public int getMaxPage() {
        int maxPage = (recordCount % pageSize == 0) ? (recordCount / pageSize)
                : (recordCount / pageSize + 1);
        return maxPage;
    }

    // 获取总记录数
    public int getRecordSize() {
        return recordCount;
    }

    // 获取当前页数
    public int getPage(String str) {
        System.out.println("STR:" + str + "&&&&" + recordCount);
        if (str == null) {
            str = "0";
        }
        System.out.print("Page=??????");
        float oldPage = Float.parseFloat(str);
        int Page=Math.round(oldPage);
        System.out.print("Page="+Page);
        if (Page < 1) {
            Page = 1;
        } else {
            if (((Page - 1) * pageSize + 1) > recordCount) {
                Page = maxPage;
            }
        }
        return Page;
    }

}