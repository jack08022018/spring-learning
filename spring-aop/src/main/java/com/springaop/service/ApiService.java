package com.springaop.service;

import org.springframework.ui.ModelMap;

public interface ApiService {
    ModelMap getDataAsyncWithThreadPool() throws Exception;
    ModelMap getDataAsyncNoThreadPool() throws Exception;
}
