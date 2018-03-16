package ch.adv.lib.array;

import org.junit.Assert;

public class Test {

    @org.junit.Test
    public void test() {
        //given
        Sum sum = new Sum();
        int a = 1;
        int b = 2;

        // when
        int actual = sum.sum(a, b);

        // then
        Assert.assertEquals(1 + 2, actual);
    }
}
