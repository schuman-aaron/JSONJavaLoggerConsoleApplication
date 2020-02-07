package net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsProxy {

	private Path path;
	
	public static PathsProxy get(String uri) {
		PathsProxy pathsProxy = new PathsProxy();
		pathsProxy.setPath(Paths.get(uri));
		return pathsProxy;
	}
	
	public PathsProxy resolve(String other) {
		setPath(getPath().resolve(other));
		return this;
	}

	public PathsProxy normalize() {
		setPath(getPath().normalize());
		return this;
	}
	
	public File toFile() {
		return getPath().toFile();
	}
	
	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}
}
