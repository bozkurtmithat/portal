package my.portal.command;

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
import my.portal.common.controllers.CDI;

@Slf4j
public class CommandLoader {

	private Map<String, CommandExecutor> commands = new HashMap<>();

	private final String JAR_EXT = ".jar";
	private final String CLASS_EXT = ".class";
	private FileFilter filter = (file) -> {
		return fileFilter(file);
	};
	private final Predicate<String> PACKAGE_IMP_PREDICATE = Pattern.compile("my/portal/[\\w/]*impl/").asPredicate();

	public static Map<String, CommandExecutor> loadCommands() {
		CommandLoader commandLoader = new CommandLoader();
		return commandLoader.commands;
	}

	private CommandLoader() {
		getResourceListWithManifest().forEach(this::normalizeAndScan);
	}

	private Set<URL> getResourceListWithManifest() {
		try {
			ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
			Enumeration<URL> resourceList = contextClassLoader.getResources("META-INF/MANIFEST.MF");
			Set<URL> uniqueResourceList = new HashSet<>();
			while (resourceList.hasMoreElements()) {
				uniqueResourceList.add(resourceList.nextElement());
			}
			return uniqueResourceList;
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			return new HashSet<URL>();
		}
	}

	private void normalizeAndScan(URL uri) {
		try {
			String s = uri.getFile();
			s = s.substring(s.indexOf('/'), s.lastIndexOf('/'));
			s = s.substring(isWindoz() ? 1 : 0, s.lastIndexOf('/'));
			if (s.contains(JAR_EXT)) {
				s = s.replace("!", "");
			}

			log.debug("### Reading jar/bin {}", s);
			scanAnnotation(new File(s));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	private boolean fileFilter(File file) {
		return !file.getName().contains("$") && ((file.isDirectory() && !file.getName().startsWith("."))
				|| file.getName().endsWith(JAR_EXT) || file.getName().endsWith(CLASS_EXT));
	}

	private void scanAnnotation(File f) throws IOException {
		if (f.isDirectory()) {
			File[] fileList = f.listFiles(filter);
			for (File file : fileList)
				scanAnnotation(file);
		} else if (f.getName().endsWith(JAR_EXT)) {
			scanAnnotation(new JarFile(f));
		} else if (f.getName().endsWith(CLASS_EXT))
			loadAnnotation(f);

	}

	private void loadAnnotation(File file) {

		if (!PACKAGE_IMP_PREDICATE.test(file.getPath())) {
			return;
		}

		String className = file.getPath().replace(File.separator, ".");
		log.debug("classname:{}", className);
//		className = className.substring(index);
		className = className.substring(0, className.length() - CLASS_EXT.length());
		loadAnnotation(className);
	}

	private void scanAnnotation(JarFile jar) {
		Enumeration<JarEntry> fileList = jar.entries();
		while (fileList.hasMoreElements()) {
			JarEntry file = fileList.nextElement();
			if (PACKAGE_IMP_PREDICATE.test(file.getName()) && file.getName().endsWith(CLASS_EXT)) {
				String className = file.getName().replaceAll("/", ".").substring(0, file.getName().length() - 6);
				loadAnnotation(className);
			}
		}
	}

	private void loadAnnotation(String className) {
		try {
			Class<?> claz = CDI.class.getClassLoader().loadClass(className);
			if (claz.isAnnotationPresent(Command.class) && CommandExecutor.class.isAssignableFrom(claz)) {
				Command command = (Command) claz.getAnnotation(Command.class);
				CommandExecutor commandClaz = CommandExecutor.class.cast(claz);
				commands.put(command.value(), commandClaz);
			}
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
		}
	}

	private boolean isWindoz() {
		String OS_NAME = System.getProperty("os.name").toLowerCase(Locale.US);
		return ((OS_NAME != null) && (OS_NAME.indexOf("windows") >= 0));
	}

}
