package com.tc.cache;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class CacheProperties {

    @Value("${tc.cache.size}")
    private Integer size;

	public Integer getSize() {
		return this.size;
	}

}
