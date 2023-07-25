package com.example.application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.stream.Collectors;


public class AccessingAllClassesInPackage {
	public static Set<Class<?>> findAllClassesUsingClassLoader(String packageName) {
		System.out.println("Classes of Package " + packageName);
		var stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.replaceAll("[.]", "/"));
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
