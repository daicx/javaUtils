package com.lenovo.javautils.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandleUtil {
	/**
	 * 文件复制
	 * @author daicx1
	 * @date 2018年4月20日 上午10:44:43
	 * @param source
	 * @param target
	 * @throws IOException
	 */
	public static void copyFile(String source,String target) throws IOException {
		File file = new File(source);
		File out = new File(target);
		if(!out.exists()) {
			out.createNewFile();
		}
		FileInputStream is=new FileInputStream(file);
		FileOutputStream os = new FileOutputStream(out);
		int len;
		byte[] buf=new byte[1024];
		while((len=is.read(buf))>0) {
			os.write(buf, 0, len);
		}
		if(is!=null) {
			is.close();
		}
		if(os!=null) {
			os.close();
		}
	}
}
