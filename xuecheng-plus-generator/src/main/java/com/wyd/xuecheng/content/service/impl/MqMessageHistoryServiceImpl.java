package com.wyd.xuecheng.content.service.impl;

import com.wyd.xuecheng.content.model.po.MqMessageHistory;
import com.wyd.xuecheng.content.mapper.MqMessageHistoryMapper;
import com.wyd.xuecheng.content.service.MqMessageHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyd
 */
@Slf4j
@Service
public class MqMessageHistoryServiceImpl extends ServiceImpl<MqMessageHistoryMapper, MqMessageHistory> implements MqMessageHistoryService {

}
