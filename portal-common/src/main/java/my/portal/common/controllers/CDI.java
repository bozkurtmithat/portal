package my.portal.common.controllers;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import my.portal.common.Impl;

@Slf4j
public class CDI {

	private static final boolean mockEnabled = Boolean.parseBoolean(System.getProperty("mock.enable"));
	private static final String JAR_EXT = ".jar";
	private static final String CLASS_EXT = ".class";
	private static final Predicate<String> PACKAGE_IMP_PREDICATE = Pattern.compile(".*my/portal/[\\w/]*impl/.*")
			.asPredicate();

	private static Map<Class<?>, Object> lookupTable = new HashMap<>();
	private static Map<Class<?>, Class<?>> impls = new HashMap<>();

	static {
		Set<URL> uniqueResourceList = getResourceListWithManifest();
		String s = null;
		for (URL uri : uniqueResourceList) {
			try {
				s = uri.getFile();
				s = s.substring(s.indexOf('/'), s.lastIndexOf('/'));
				s = s.substring(isWindoz() ? 1 : 0, s.lastIndexOf('/'));
				if (s.contains(JAR_EXT)) {
					s = s.replace("!", "");
				}

				log.error("### Reading jar/bin {}", s);
				scanAnnotation(new File(s));
			} catch (Exception e) {

			}
		}
	}

	private static Set<URL> getResourceListWithManifest() {
		try {
			ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
			Enumeration<URL> resourceList = contextClassLoader.getResources("META-INF/MANIFEST.MF");
			Set<URL> uniqueResourceList = new HashSet<>();
			while (resourceList.hasMoreElements()) {
				uniqueResourceList.add(resourceList.nextElement());
			}
			return uniqueResourceList;
		} catch (IOException e) {
			e.printStackTrace();
			return new HashSet<URL>();
		}
	}

	private static FileFilter filter = new FileFilter() {

		public boolean accept(File file) {
			return !file.getName().contains("$") && ((file.isDirectory() && !file.getName().startsWith("."))
					|| file.getName().endsWith(JAR_EXT) || file.getName().endsWith(CLASS_EXT));
		}

	};

	private static void scanAnnotation(File f) throws IOException {
		if (f.isDirectory()) {
			File[] fileList = f.listFiles(filter);
			for (File file : fileList)
				scanAnnotation(file);
		} else if (f.getName().endsWith(JAR_EXT)) {
			scanAnnotation(new JarFile(f));
		} else if (f.getName().endsWith(CLASS_EXT))
			loadAnnotation(f);

	}

	private static void scanAnnotation(JarFile jar) {
		Enumeration<JarEntry> fileList = jar.entries();
		while (fileList.hasMoreElements()) {
			JarEntry file = fileList.nextElement();
			if (PACKAGE_IMP_PREDICATE.test(file.getName()) && file.getName().endsWith(CLASS_EXT)) {
				String className = file.getName().replaceAll("/", ".").substring(0, file.getName().length() - 6);
				loadAnnotation(className);
			}
		}
	}
	
	private static void loadAnnotation(File file) {

		if (!PACKAGE_IMP_PREDICATE.test(file.getPath())) {
			return;
		}

		String className = file.getPath().replace(File.separator, ".");
		int indexOf = className.indexOf("my.portal."); 
		className = className.substring(indexOf);
		log.error("classname:{}",className);
		className = className.substring(0, className.length() - CLASS_EXT.length());
		loadAnnotation(className);
	}

	private static void loadAnnotation(String className) {
		try {

			Class<?> claz;
			try {
				claz = CDI.class.getClassLoader().loadClass(className);
				if (claz.isAnnotationPresent(Impl.class)) {
					Impl op = (Impl) claz.getAnnotation(Impl.class);
					if(claz.getSimpleName().startsWith("Mock")) {
						if(mockEnabled)
							impls.put(op.value(), claz);
					}else{
						impls.put(op.value(), claz);
					}
				}
			} catch (Throwable e) {
				log.error("load annotation for {} is failed! ", className, e);
			}

		} catch (Throwable e) {
			log.error("Class Yüklenemedi : {}", className ,e);
		}
	}

	private static boolean isWindoz() {
		String OS_NAME = System.getProperty("os.name").toLowerCase(Locale.US);
		return ((OS_NAME != null) && (OS_NAME.indexOf("windows") >= 0));
	}

	public static void registerImpl(Class<?> interfaceClass, Class<?> implementationClass) {
		impls.put(interfaceClass, implementationClass);
	}
	
	public static <T> void registerLookup(Class<T> claz, T instance) {
		lookupTable.put(claz, instance);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T lookup(Class<T> claz) {
		return (T)lookupTable.get(claz);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getImpl(Class<T> c) {
		try {
			Class<T> implClass = (Class<T>) impls.get(c);
			return (T) implClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

}
