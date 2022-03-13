package com.getir.project.bookretail.util.constant;

import com.getir.project.bookretail.util.ResponseMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ErrorConstantTest {

    @Test
    public void constantTest() {
        assertThat(ErrorConstant.class, allOf(hasValidBeanConstructor()));
        assertThat(ResponseMessage.class, allOf(hasValidBeanConstructor()));

        // -------------------------

        String error = ErrorConstant.SYSTEM_ERROR;
        String error1 = ErrorConstant.NOT_BLANK_FIELD;
        String error2 = ErrorConstant.DATE_NOT_VALID;
        String error3 = ErrorConstant.METHOD_ARG_NOT_VALID;

        Assert.assertEquals(error, ErrorConstant.SYSTEM_ERROR);
        Assert.assertEquals(error1, ErrorConstant.NOT_BLANK_FIELD);
        Assert.assertEquals(error2, ErrorConstant.DATE_NOT_VALID);
        Assert.assertEquals(error3, ErrorConstant.METHOD_ARG_NOT_VALID);

    }

}