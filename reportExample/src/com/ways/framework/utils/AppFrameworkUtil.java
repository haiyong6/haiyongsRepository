package com.ways.framework.utils;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ways.auth.model.AuthConstants;
import com.ways.auth.model.MyUserDetails;
import flexjson.JSONSerializer;

public class AppFrameworkUtil {

	/**
	 *  四舍五入。保留num个小数点后的数字
	 * @param bh
	 * @param num
	 * @return
	 */
	public static String getNum(double bh, int num) {
		String numstr = "";
		numstr = new BigDecimal(bh).setScale(num, BigDecimal.ROUND_HALF_UP)+"";
		return numstr;
	}
	
	/**
	 *  去替换横杠
	 * @param str
	 * @param t_str
	 * @return
	 */
	public static String getReplaseStr(String str) {
		String t_str = null;
		if(str != null){
			t_str = str.replace("-", "");
		}
		return t_str;
	}
	
	/**
	 *  四舍五入。保留num个小数点后的数字
	 * @param bh
	 * @param num
	 * @return
	 */
	public static String getNum(String bh, int num) {
		
		if(AppFrameworkUtil.isEmpty(bh) || "-".equals(bh)) return "-";
		if("0.0".equals(bh) || "0".equals(bh)) return bh;
		String numstr = "";
		numstr = new BigDecimal(Double.parseDouble(bh)).setScale(num, BigDecimal.ROUND_HALF_UP)+"";
		return numstr;
	}
	
	/**
	 * 字符串空判断
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		boolean flag = true;
		if(null != str && 0 != str.trim().length()) flag = false;
		return flag;
	}
	
	/**
	 * 添加符号
	 * @param str
	 */
	public static String addSign(String str) {
		if(!AppFrameworkUtil.isEmpty(str) && !str.contains("-"))
		{
			str = "+" + str;
		}
		return str;
	}
	
	/**
	 * 添加千分位
	 * @param str
	 * @return
	 */
	public static String format(String str) {
		if(!isEmpty(str) && !"-".equals(str))
		{
			DecimalFormat df = new DecimalFormat("###,###");
			try {
				 str = df.format(Long.parseLong(str));
			} catch (Exception e) {
				return str;
			}
		}
		return str;
	}
	
	/**
	 * 序列化集合成JSON字符
	 * @param list
	 * @return
	 */
	public static String structureConfigParamsGroupJSONData(List<?> list) {
		JSONSerializer serializer = new JSONSerializer();
		String json = "";
		json = serializer.exclude("*.class").deepSerialize(list).replaceAll(":\\s*null\\s*", ":\"\"");
		return json;
	}
	
	public static String structureConfigParamsGroupJSONData(Map<String, ?> map) {
		JSONSerializer serializer = new JSONSerializer();
		String json = "";
		json = serializer.exclude("*.class").deepSerialize(map).replaceAll(":\\s*null\\s*", ":\"\"");
		return json;
	}
	
	/**
	 * 获取用户组ID
	 * @param request
	 * @return
	 */
	public static String getUserId(HttpServletRequest request) {
		String userId = "";
		MyUserDetails userDetails  = (MyUserDetails)request.getSession().getAttribute(AuthConstants.CURR_USER_DETAILS);
		if(null != userDetails) userId = userDetails.getUserId();
		return userId;
	}
	
	public static String serializableJSONData(Map<String, ?> map) {
		JSONSerializer serializer = new JSONSerializer();
		String json = "";
		json = serializer.exclude("*.class").deepSerialize(map).replaceAll(":\\s*null\\s*", ":\"\"");
		return json;
	}
	
	/**
	 * 获取用户组名称
	 * @param request
	 * @return
	 */
	public static String getUserName(HttpServletRequest request) {
		String userName = "";
		MyUserDetails userDetails  = (MyUserDetails)request.getSession().getAttribute(AuthConstants.CURR_USER_DETAILS);
		if(null != userDetails) userName = userDetails.getUserName();
		return userName;
	}
	
	/**
	 * response JSON数据
	 * @param response
	 * @param context
	 */
	public static void renderJSON(HttpServletResponse response,String context) {
		PrintWriter out = null;
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			out = response.getWriter();
			out.write(context);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
	
	/**
	 * 获取外网IP
	 * @param request
	 * @return
	 */
	public static String getRemoteHost(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}

	public static String getSystemDate() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		String curDate = s.format(calendar.getTime());  //当前日期
		return curDate;
	}
}
