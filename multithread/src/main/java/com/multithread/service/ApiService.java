package com.multithread.service;

import org.springframework.ui.ModelMap;

public interface ApiService {
    ModelMap getDataAsyncWithThreadPool() throws Exception;
    ModelMap getDataAsyncNoThreadPool() throws Exception;
    ModelMap getDataAsyncSeparateThread() throws Exception;
}
