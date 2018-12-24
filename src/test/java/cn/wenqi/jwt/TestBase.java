package cn.wenqi.jwt;

import org.junit.Test;

/**
 * @author wenqi
 * @since v1.0
 */
public class TestBase {


    @Test
    public void test0(){
        float v=Float.intBitsToFloat(0x8000000);
        System.out.println(v);
        System.out.println(v==+0.0F);
        System.out.println(+0.0F==(-0.0F));
        System.out.println(1.0F/(-0.0F));
        System.out.println(+0.0F/+0.0F);
    }
}
