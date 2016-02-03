package web.services;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public abstract class AbstractEJB {
	@PostConstruct
	public void init() {
		System.out.println("Created " + this);
	}
	@PreDestroy
	public void close() {
		System.out.println("Destroyed " + this);
	}
}
