import { R, TableDataInfo } from '@/api/model/resultModel';
import {
  SysMessageTemplateForm,
  SysMessageTemplateQuery,
  SysMessageTemplateVo,
} from '@/api/system/model/messageTemplateModel';
import { request } from '@/utils/request';
import {SysMessageKeyVo} from "@/api/system/model/messageKeyModel";
import {SysMessageConfigVo} from "@/api/system/model/messageConfigModel";

// 查询消息模板列表
export function listMessageTemplate(query?: SysMessageTemplateQuery) {
  return request.get<TableDataInfo<SysMessageTemplateVo>>({
    url: '/system/messageTemplate/list',
    params: query,
  });
}

// 查询消息模板详细
export function getMessageTemplate(messageTemplateId: number) {
  return request.get<R<SysMessageTemplateVo>>({
    url: `/system/messageTemplate/${messageTemplateId}`,
  });
}

// 新增消息模板
export function addMessageTemplate(data: SysMessageTemplateForm) {
  return request.post<R<void>>({
    url: '/system/messageTemplate',
    data,
  });
}

// 修改消息模板
export function updateMessageTemplate(data: SysMessageTemplateForm) {
  return request.put<R<void>>({
    url: '/system/messageTemplate',
    data,
  });
}

// 删除消息模板
export function delMessageTemplate(messageTemplateIds: number | Array<number>) {
  return request.delete<R<void>>({
    url: `/system/messageTemplate/${messageTemplateIds}`,
  });
}

// 获取消息配置
export function getMessageConfigs(messageType: string) {
  return request.get<R<SysMessageConfigVo[]>>({
    url: `/system/messageTemplate/messageConfigs`,
    params: { messageType },
  });
}

// 获取消息常量
export function getMessageKeys() {
  return request.get<R<SysMessageKeyVo[]>>({
    url: `/system/messageTemplate/messageKeys`,
  });
}
