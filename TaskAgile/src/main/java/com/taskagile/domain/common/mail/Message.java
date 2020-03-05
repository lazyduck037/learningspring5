package com.taskagile.domain.common.mail;

import org.apache.commons.lang3.StringUtils;

public interface Message {
    String getTo();
    String getSubject();
    String getBody();
    String getFrom();
}
