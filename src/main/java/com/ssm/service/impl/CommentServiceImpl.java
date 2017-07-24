package com.ssm.service.impl;

import com.ssm.mapper.CommentMapper;
import com.ssm.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by acer on 2017/7/22.
 */
@Transactional
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

}
