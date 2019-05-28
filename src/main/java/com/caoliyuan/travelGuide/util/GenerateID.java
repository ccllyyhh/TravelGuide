package com.caoliyuan.travelGuide.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateID {
		/**
		 * 根据传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
		 *
		 * @param sformat
		 *            yyyyMMddhhmmss
		 * @return
		 */
		public static String getDate(String sformat) {
				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat(sformat);
				String dateString = formatter.format(currentTime);
				return dateString;
		}

		public static String getRandomNum(int num){
				String numStr = "";
				for(int i = 0; i < num; i++){
						numStr += (int)(10*(Math.random()));
				}
				return numStr;
		}
		/**
		 * 生成id
		 * @return
		 */
		public static Long getGeneratID(){
				String sformat = "MMddhhmmssSSS";
				int num = 3;
				String idStr = getDate(sformat) + getRandomNum(num);
				Long id = Long.valueOf(idStr);
				return id;
		}

		public static void main(String[] args) {
				for(int i = 0; i < 10; i++){
						System.out.println(getGeneratID());
				}
		}
}
