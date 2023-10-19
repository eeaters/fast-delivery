package io.eeaters.delivery.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils implements BeanFactoryAware {

    private static BeanFactory beanFactory;

    public static <T> T getBean(Class<T> tClass) {
        return beanFactory.getBean(tClass);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        SpringUtils.beanFactory = beanFactory;
    }
}
