package com.chocfun.baselib.util;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class XTextUtilTest {

    @Test
    public void isEmpty() {
        Assert.assertTrue(XTextUtil.isEmpty(null));
        Assert.assertTrue(XTextUtil.isEmpty(""));
        Assert.assertFalse(XTextUtil.isEmpty("ahsk"));
    }
}