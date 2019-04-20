package com.yxb.xf.webapi_v2.java;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import scala.annotation.target.param;

/**
 * AIUI WebAPI V2接口调用示例
 * 
 * 运行方法：直接运行 main()
 * 
 * 结果： 控制台输出接口返回值信息
 * 
 * @author iflytek_aiui
 * 
 */
public class WebaiuiDemo {
	private static final String URL = "http://openapi.xfyun.cn/v2/aiui";
	private static final String APPID = "5cb9044f";
	private static final String API_KEY = "dc396461fb9758b76fcf1b9a9e56ab69";
	private static final String DATA_TYPE_AUDIO = "audio";
	private static final String DATA_TYPE_TEXT = "text";
	private static final String SCENE = "main";
	private static final String SAMPLE_RATE = "16000";
	private static final String AUTH_ID = "dedcace7ac509d33ce5bd08fd71e93ad";
	private static final String AUE = "raw";
	//结果级别 complete/完整
	private static final String RESULT_LEVEL = "complete";
	//
	private static final String topn = "4";


	private static final String FILE_PATH = "/Users/wensiyang/Documents/sofeware/idea/projiect/dmp_web/src/main/java/com/yxb/xf/webapi_v2/resource/16kVoice.pcm";
	// 个性化参数，需转义
	private static final String PERS_PARAM = "{\\\"auth_id\\\":\\\"2894c985bf8b1111c6728db79d3479ae\\\"}";
	
	public static void main(String[] args) throws IOException,ParseException, InterruptedException{
		Map<String, String> header = buildHeader();
		byte[] dataByteArray = readFile(FILE_PATH);
		//byte[] dataByteArray = "北京天气怎么用".getBytes();
		String result = httpPost(URL, header, dataByteArray);
		System.out.println(result);		
	}

	private static Map<String, String> buildHeader() throws UnsupportedEncodingException, ParseException {
		String curTime = System.currentTimeMillis() / 1000L + "";
		Map<String,String> map = new HashMap<>();
		//音频编码
		map.put("aue",AUE);
		//音频采样率
		map.put("sample_rate",SAMPLE_RATE);
		map.put("auth_id",AUTH_ID);
		map.put("scene",SCENE);
		map.put("data_type",DATA_TYPE_AUDIO);
		map.put("result_level",RESULT_LEVEL);
		map.put("topn","4");
		String param = JSON.toJSONString(map);
		//使用个性化参数时参数格式如下：
		//String param = "{\"aue\":\""+AUE+"\",\"sample_rate\":\""+SAMPLE_RATE+"\",\"auth_id\":\""+AUTH_ID+"\",\"data_type\":\""+DATA_TYPE+"\",\"scene\":\""+SCENE+"\",\"pers_param\":\""+PERS_PARAM+"\"}";
		String paramBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
		String checkSum = DigestUtils.md5Hex(API_KEY + curTime + paramBase64);

		Map<String, String> header = new HashMap<String, String>();
		header.put("X-Param", paramBase64);
		header.put("X-CurTime", curTime);
		header.put("X-CheckSum", checkSum);
		header.put("X-Appid", APPID);
		return header;
	}
	
	private static byte[] readFile(String filePath) throws IOException {
		InputStream in = new FileInputStream(filePath);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024 * 4];
		int n = 0;
		while ((n = in.read(buffer)) != -1) {
			out.write(buffer, 0, n);
		}
		byte[] data = out.toByteArray();
		in.close();
		return data;
	}
	
	private static String httpPost(String url, Map<String, String> header, byte[] body) {
		String result = "";
		BufferedReader in = null;
		OutputStream out = null;
		try {
			URL realUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
			for (String key : header.keySet()) {
				connection.setRequestProperty(key, header.get(key));
			}
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			//connection.setConnectTimeout(20000);
			//connection.setReadTimeout(20000);
			try {
				out = connection.getOutputStream();
				out.write(body);
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
