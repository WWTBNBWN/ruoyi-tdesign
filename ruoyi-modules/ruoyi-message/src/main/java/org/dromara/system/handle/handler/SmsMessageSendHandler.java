package org.dromara.system.handle.handler;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.dromara.common.core.enums.CommonStatusEnum;
import org.dromara.common.core.enums.MessageSupplierTypeEnum;
import org.dromara.common.core.enums.MessageTemplateMode;
import org.dromara.common.core.enums.MessageTypeEnum;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.sms4j.aliyun.config.AlibabaConfig;
import org.dromara.sms4j.api.SmsBlend;
import org.dromara.sms4j.api.entity.SmsResponse;
import org.dromara.sms4j.cloopen.config.CloopenConfig;
import org.dromara.sms4j.core.factory.SmsFactory;
import org.dromara.sms4j.ctyun.config.CtyunConfig;
import org.dromara.sms4j.emay.config.EmayConfig;
import org.dromara.sms4j.huawei.config.HuaweiConfig;
import org.dromara.sms4j.jdcloud.config.JdCloudConfig;
import org.dromara.sms4j.netease.config.NeteaseConfig;
import org.dromara.sms4j.provider.enumerate.SupplierType;
import org.dromara.sms4j.tencent.config.TencentConfig;
import org.dromara.sms4j.unisms.config.UniConfig;
import org.dromara.sms4j.yunpian.config.YunpianConfig;
import org.dromara.system.domain.SysMessageConfig;
import org.dromara.system.domain.SysMessageTemplate;
import org.dromara.system.handle.BaseMessageSendHandler;
import org.dromara.system.service.ISysMessageLogService;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 短信消息发送处理类
 *
 * @author hexm
 * @date 2023/07/24 11:00
 */
@Component
public class SmsMessageSendHandler extends BaseMessageSendHandler {

    public SmsMessageSendHandler(ISysMessageLogService messageLogService) {
        super(messageLogService);
    }

    /**
     * 获取消息类型
     */
    @Override
    public MessageTypeEnum getMessageType() {
        return MessageTypeEnum.SMS;
    }

    /**
     * 发送消息
     *
     * @param account  账号
     * @param message  消息变量
     * @param template 模板对象
     * @param config   消息配置
     */
    @Override
    public void send(List<String> account, Map<String, Object> message, SysMessageTemplate template, SysMessageConfig config) {
        LinkedHashMap<String, String> outputVars = getOutputVars(template, message);
        String content = getContent(template, outputVars);
        MessageSupplierTypeEnum supplierType = MessageSupplierTypeEnum.valueOf(config.getSupplierType());
        SmsBlend smsBlend = switch (supplierType) {
            case ALIBABA -> {
                AlibabaConfig alibabaConfig = JSONUtil.toBean(config.getConfigJson(), AlibabaConfig.class, true);
                if (StrUtil.isNotBlank(template.getSignature())) {
                    alibabaConfig.setSignature(template.getSignature());
                }
                yield SmsFactory.createSmsBlend(SupplierType.ALIBABA, alibabaConfig);
            }
            case HUAWEI -> {
                HuaweiConfig huaweiConfig = JSONUtil.toBean(config.getConfigJson(), HuaweiConfig.class, true);
                if (StrUtil.isNotBlank(template.getSignature())) {
                    huaweiConfig.setSignature(template.getSignature());
                }
                yield SmsFactory.createSmsBlend(SupplierType.HUAWEI, huaweiConfig);
            }
            case TENCENT -> {
                TencentConfig tencentConfig = JSONUtil.toBean(config.getConfigJson(), TencentConfig.class, true);
                if (StrUtil.isNotBlank(template.getSignature())) {
                    tencentConfig.setSignature(template.getSignature());
                }
                yield SmsFactory.createSmsBlend(SupplierType.TENCENT, tencentConfig);
            }
            case YUNPIAN -> {
                YunpianConfig yunpianConfig = JSONUtil.toBean(config.getConfigJson(), YunpianConfig.class, true);
                if (StrUtil.isNotBlank(template.getSignature())) {
                    yunpianConfig.setSignature(template.getSignature());
                }
                yield SmsFactory.createSmsBlend(SupplierType.YUNPIAN, yunpianConfig);
            }
            case UNI_SMS -> {
                UniConfig uniConfig = JSONUtil.toBean(config.getConfigJson(), UniConfig.class, true);
                if (StrUtil.isNotBlank(template.getSignature())) {
                    uniConfig.setSignature(template.getSignature());
                }
                yield SmsFactory.createSmsBlend(SupplierType.UNI_SMS, uniConfig);
            }
            case JD_CLOUD -> {
                JdCloudConfig jdCloudConfig = JSONUtil.toBean(config.getConfigJson(), JdCloudConfig.class, true);
                if (StrUtil.isNotBlank(template.getSignature())) {
                    jdCloudConfig.setSignature(template.getSignature());
                }
                yield SmsFactory.createSmsBlend(SupplierType.JD_CLOUD, jdCloudConfig);
            }
            case CLOOPEN -> {
                CloopenConfig cloopenConfig = JSONUtil.toBean(config.getConfigJson(), CloopenConfig.class, true);
                if (StrUtil.isNotBlank(template.getSignature())) {
                    cloopenConfig.setSignature(template.getSignature());
                }
                yield SmsFactory.createSmsBlend(SupplierType.CLOOPEN, cloopenConfig);
            }
            case EMAY -> {
                EmayConfig emayConfig = JSONUtil.toBean(config.getConfigJson(), EmayConfig.class, true);
                yield SmsFactory.createSmsBlend(SupplierType.EMAY, emayConfig);
            }
            case CTYUN -> {
                CtyunConfig ctyunConfig = JSONUtil.toBean(config.getConfigJson(), CtyunConfig.class, true);
                if (StrUtil.isNotBlank(template.getSignature())) {
                    ctyunConfig.setSignature(template.getSignature());
                }
                yield SmsFactory.createSmsBlend(SupplierType.CTYUN, ctyunConfig);
            }
            case NETEASE -> {
                NeteaseConfig neteaseConfig = JSONUtil.toBean(config.getConfigJson(), NeteaseConfig.class, true);
                if (StrUtil.isNotBlank(template.getSignature())) {
                    neteaseConfig.setSignature(template.getSignature());
                }
                yield SmsFactory.createSmsBlend(SupplierType.NETEASE, neteaseConfig);
            }
            default -> throw new ServiceException("不支持的消息类型");
        };
        MessageTemplateMode templateMode = MessageTemplateMode.valueOf(template.getTemplateMode());
        // 消息发送方式
        SmsResponse response;
        switch (templateMode) {
            case TEMPLATE_ID -> {
                if (account.size() == 1) {
                    response = smsBlend.sendMessage(account.get(0), template.getTemplateId(), outputVars);
                } else {
                    response = smsBlend.massTexting(account, template.getTemplateId(), outputVars);
                }
            }
            case TEMPLATE_CONTENT -> {
                if (account.size() == 1) {
                    response = smsBlend.sendMessage(account.get(0), content);
                } else {
                    response = smsBlend.massTexting(account, content);
                }
            }
            default -> throw new ServiceException("不支持的消息模板模式：" + templateMode);
        }
        // 记录发送记录
        saveLog(account, template, config, content, log -> {
            log.setIsSuccess(response.isSuccess() ? CommonStatusEnum.SUCCESS.getCodeNum() : CommonStatusEnum.FAIL.getCodeNum());
            log.setErrorCode(response.getErrorCode());
            log.setErrorMessage(response.getErrMessage());
            log.setBizId(response.getBizId());
            log.setMessage(response.getMessage());
        });
    }
}