package io.eeaters.delivery.third.atomic;

import io.eeaters.delivery.config.third.ThirdConfig;
import io.eeaters.delivery.entity.dto.third.CreateDeliveryDTO;
import io.eeaters.delivery.entity.dto.third.CreatePreDeliveryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import java.util.Optional;

@Slf4j
public abstract class AbstractDeliveryAtomic implements BaseDeliveryAtomic {

    private static final String channelMockFormatter = "third.delivery.%s.isMock";

    @Autowired
    ThirdConfig thirdConfig;

    @Autowired
    Environment environment;


    @Override
    public CreatePreDeliveryDTO createPreOrder(CreateDeliveryDTO createDeliveryDTO) {
        if (isMock()) {
            log.info("{} 渠道开启了挡板, 预下单接口直接返回。", supportChannel().getName());
            //todo: mock一下数据
            //return
        }
        return createPreDeliveryInternal(createDeliveryDTO);
    }


    @Override
    public void createDelivery(CreateDeliveryDTO createDeliveryDTO) {
        if (isMock()) {
            log.info("{} 渠道开启了挡板, 预下单接口直接返回。", supportChannel().getName());
            //todo: mock一下数据
            //return
        }
        createDeliveryInternal(createDeliveryDTO);
    }

    protected abstract CreatePreDeliveryDTO createPreDeliveryInternal(CreateDeliveryDTO createDeliveryDTO);

    protected abstract void createDeliveryInternal(CreateDeliveryDTO createDeliveryDTO);

    protected boolean isMock() {
        if (thirdConfig.getDelivery().getIsMock()) {
            return true;
        }
        String channelCode = supportChannel().getCode();
        String channelIsMock = String.format(channelMockFormatter, channelCode);

        return Optional.ofNullable(environment.getProperty(channelIsMock))
                .map(Boolean::valueOf)
                .orElse(false);
    }

    public ThirdConfig getThirdConfig() {
        return thirdConfig;
    }
}
