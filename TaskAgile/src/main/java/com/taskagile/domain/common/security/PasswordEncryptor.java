package com.taskagile.domain.common.security;

public interface PasswordEncryptor {
    String encrypt(String rawPassword);
}
