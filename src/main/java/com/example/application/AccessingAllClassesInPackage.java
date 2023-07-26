package com.example.application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.extern.java.Log;

@Log
public class AccessingAllClassesInPackage {
	public static Set<Class<?>> findAllClassesUsingClassLoader(String packageName) {
		log.info( () -> "Classes of Package " + packageName);
		var stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.replaceAll("[.]", "/"));
		if ( stream == null ) {
			return new HashSet<>();
		}
		var reader = new BufferedReader(new InputStreamReader(stream));
		return reader.lines().filter(line -> line.endsWith(".class")).map(line -> getClass(line, packageName))
				.collect(Collectors.toSet());
	}

	private static Class<?> getClass(final String className, final String packageName) {
		try {
			return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
		} catch (ClassNotFoundException e) {
			// handle the exception
		}
		return null;
	}
}
