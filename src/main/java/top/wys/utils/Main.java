package top.wys.utils;

import java.util.Map;

/**
 * @author 郑明亮
 * @time 2017年6月20日 上午11:10:49
 * @description <p> </p>
 * @modifyBy
 * @modifyTime 
 * @modifyDescription<p> </p>
 */
public class Main {
	public static void main(String[] args) {
		System.out.println("this is a zhengmingliang's tool");
		Map<String, String> map = AutoLoadProperties.SYSTEM_CONFIG;
		System.out.println(map);
		String path = Main.class.getClassLoader().getResource("").getPath();
		System.out.println(path);
		System.out.println(RandomUtils.getUUID());
	}
}
