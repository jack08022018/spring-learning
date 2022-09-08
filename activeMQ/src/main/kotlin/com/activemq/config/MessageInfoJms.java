package com.activemq.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class MessageInfoJms implements Serializable {
	private static final long serialVersionUID = 1L;
	private int batchCode;
    private int clientMemCode;
    private String clientCode = "";
    private String classCode = "";

    public MessageInfoJms(){}

    public MessageInfoJms(String clientCode, String classCode) {
        this.clientCode = clientCode;
        this.classCode = classCode;
    }
}
