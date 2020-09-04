package com.myproject.mailservice.config;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Getter
@Configuration
@ConfigurationProperties(prefix = "custom", ignoreUnknownFields = false)
@EnableConfigurationProperties({CustomProperties.class})
@Validated
public class CustomProperties {


    @NotNull
    private Storage storage = new Storage();

    @NotNull
    private Elastic elastic = new Elastic();


    public static class Storage {

        @NotBlank
        private String location;

    }


    public static class Elastic {

        @NotBlank
        private String hostname;

        @Range(min = 1024, max = 65535)
        private int port;

        @Pattern(regexp = "^(http|https)$")
        private String protocol;

		public String getHostname() {
			return hostname;
		}

		public void setHostname(String hostname) {
			this.hostname = hostname;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

		public String getProtocol() {
			return protocol;
		}

		public void setProtocol(String protocol) {
			this.protocol = protocol;
		}
        
        
        
        

    }

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public Elastic getElastic() {
		return elastic;
	}

	public void setElastic(Elastic elastic) {
		this.elastic = elastic;
	}
    
    
    
}
