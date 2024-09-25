package com.example.shop4.service;

import org.springframework.stereotype.Service;

@Service
public interface CommentPermissionService {
    public boolean canComment(Long memberId, Long goodsId);
}
