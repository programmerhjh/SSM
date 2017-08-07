package tool;

import com.ssm.model.Post;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExcelUtil {

	private static String UUIDkey;


	public static List<Post> getAllByExcel(String file){

		List<Post> list = new ArrayList<Post>();
		try {
			Workbook rwb=Workbook.getWorkbook(new File(file));
			Sheet rs=rwb.getSheet(0);//或者rwb.getSheet(0)
			int clos=rs.getColumns();//得到所有的列
			int rows=rs.getRows();//得到所有的行

			System.out.println(clos+" rows:"+rows);
			for (int i = 1; i < rows; i++) {
				for (int j = 0; j < clos; j++) {
					//第一个是列数，第二个是行数
					String postName = rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
					Integer postAuthor = Integer.valueOf(rs.getCell(j++, i).getContents());
					Integer postClicktimes = Integer.valueOf(rs.getCell(j++, i).getContents());
					String postCategory = rs.getCell(j++, i).getContents();
					String postPost = rs.getCell(j++, i).getContents();

					System.out.println("postName:"+postName+", postAuthor:"+postAuthor+", postClicktimes:"+postClicktimes+" ,postCategory:"+postCategory+",postPost:"+postPost);
					list.add(new Post(postName,postAuthor,postClicktimes,postCategory,postPost));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}


	public static String listToExcel(List<Post> postList) throws IOException {

		try {
			WritableWorkbook wwb;
			UUIDkey = String.valueOf(UUID.randomUUID());
			// 创建可写入的Excel工作簿
			String fileName = GetPropertyUtil.getFileAddress("Excel")+ UUIDkey+".xls";
			File file=new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			//以fileName为文件名来创建一个Workbook
			wwb = Workbook.createWorkbook(file);

			// 创建工作表
			WritableSheet ws = wwb.createSheet("数据库的帖子表", 0);

			//查询数据库中所有的数据
			List<Post> list= postList;
			//要插入到的Excel表格的行号，默认从0开始
			Label postId= new Label(0, 0, "帖子编号");//表示第
			Label postName= new Label(1, 0, "帖子名称");
			Label postCreatetime= new Label(2, 0, "帖子创建时间");
			Label postAuthor= new Label(3, 0, "帖子作者ID)");
			Label postClicktimes= new Label(4, 0, "帖子点击次数");
			Label postPost= new Label(5, 0, "帖子内容");
			Label postCategory= new Label(6, 0, "帖子种类");

			ws.addCell(postId);
			ws.addCell(postName);
			ws.addCell(postCreatetime);
			ws.addCell(postAuthor);
			ws.addCell(postClicktimes);
			ws.addCell(postPost);
			ws.addCell(postCategory);

			for (int i = 0; i < list.size(); i++) {

				Label labelPostId_i= new Label(0, i+1, list.get(i).getPostId()+"");
				Label labelPostName_i= new Label(1, i+1, list.get(i).getPostName());
				Label labelPostCreatetime_i= new Label(2, i+1, list.get(i).getPostCreatetime().toString());
				Label labelPostAuthor_i= new Label(3, i+1, list.get(i).getPostAuthor()+"");
				Label labelPostClicktimes_i= new Label(4, i+1, list.get(i).getPostClicktimes()+"");
				Label labelPostPost_i= new Label(5, i+1, list.get(i).getPostPost()+"");
				Label labelPostCategory_i= new Label(6, i+1, list.get(i).getPostCategory()+"");
				ws.addCell(labelPostId_i);
				ws.addCell(labelPostName_i);
				ws.addCell(labelPostCreatetime_i);
				ws.addCell(labelPostAuthor_i);
				ws.addCell(labelPostClicktimes_i);
				ws.addCell(labelPostPost_i);
				ws.addCell(labelPostCategory_i);
			}

			//写进文档
			wwb.write();
			// 关闭Excel工作簿对象
			wwb.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return UUIDkey+".xls";
	}


}
