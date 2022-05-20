package com.best_duck.config;

import com.ejlchina.okhttps.HTTP;
import com.ejlchina.okhttps.fastjson.FastjsonMsgConvertor;

public interface HttpConfig {
  HTTP http =
      HTTP.builder()
          .baseUrl("http://localhost:8081/backend_war/webapi") // 设置 BaseUrl
          .bodyType("json")
          .addMsgConvertor(new FastjsonMsgConvertor())
          .build();
}
