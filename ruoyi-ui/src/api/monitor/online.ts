import { request } from '@/utils/request';
import { SysUserOnline, SysUserOnlineQuery } from '@/api/monitor/model/userOnlineModel';
import { R, TableDataInfo } from '@/api/model/resultModel';

// 查询在线用户列表
export function list(query: SysUserOnlineQuery) {
  return request.get<TableDataInfo<SysUserOnline>>({
    url: '/monitor/online/list',
    params: query,
  });
}

// 强退用户
export function forceLogout(tokenId) {
  return request.delete<R<void>>({
    url: `/monitor/online/${tokenId}`,
  });
}
