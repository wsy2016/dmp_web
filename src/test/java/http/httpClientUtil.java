package http;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

/**
 * Description:
 * <p>
 * Author: wsy
 * <p>
 * Date: 2019/4/9 16:10
 */
public class httpClientUtil {


    @Test
    public void test1() {

        for (int i = 0;i<100;i++){
            RestTemplate template = new RestTemplate();
            String s = template.getForObject("https://www.baidu.com/s?ie=UTF-8&wd=csnd", String.class);
            System.out.println(s);
        }

    }
}
