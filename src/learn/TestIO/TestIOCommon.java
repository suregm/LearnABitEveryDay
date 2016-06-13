package learn.TestIO;

import java.io.File;
import java.io.IOException;

/**
 * Created by sure GM on 2016/6/14 6:30.
 */
public class TestIOCommon {

	/**
	 * 递归遍历该目录下的所有文件名称（包含其下的所有目录名称），多层 listFiles()
	 * @param file
	 */
	public static void listFileAll(File file) {
		if (file != null) {
			if (file.isDirectory()) { // 判断是目录
				File[] files = file.listFiles();  // 遍历目录下的文件
				if (files != null) {
					for (int i=0; i<files.length; i++) {
						listFileAll(files[i]);  //递归调用
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
	    File file = new File("G://Code//dirCreatedByJava");
		// 创建目录 mkdir()
		boolean b = file.mkdir();
		if (b) {
			System.out.println("dir created successfully.");
			// 创建文件 createNewFile()
			file = new File("G://Code//dirCreatedByJava//fileCreatedByJava.txt");
			boolean b2 = file.createNewFile();
			if (b2) {
				System.out.println("file created successfully.");

				// 遍历该目录下的文件名称（包含其下的目录名称），只有一层 listFiles()
				file = new File("G://Code//dirCreatedByJava");
				File[] files = file.listFiles();
				for (int i=0; i<files.length; i++) {
					System.out.println(files[i]);
				}

				// 递归遍历其下所有文件及子目录名称 自定义函数listFileAll(File file)
				file = new File("G://Code//dirCreatedByJava");
				listFileAll(file);

				// 删除文件及目录
				// 判断存在与否 exists()
				if (file.exists()) {
					// 删除文件 file.delete()
					file = new File("G://Code//dirCreatedByJava//fileCreatedByJava.txt");
					boolean b3 = file.delete();
					if (b3) {
						System.out.println("file deleted successfully.");
						// 删除目录 dir.delete()
						file = new File("G://Code//dirCreatedByJava");
						if (file.exists()) {
							boolean b4 = file.delete();
							if (b4) {
								System.out.println("dir deleted successfully.");
							} else {
								System.out.println("dir deleted failed.");
							}
						}
					} else {
						System.out.println("file deleted failed.");
					}
				}
			} else {
				System.out.println("file created failed.");
			}
		} else {
			System.out.println("dir created failed.");
		}
	}
}
