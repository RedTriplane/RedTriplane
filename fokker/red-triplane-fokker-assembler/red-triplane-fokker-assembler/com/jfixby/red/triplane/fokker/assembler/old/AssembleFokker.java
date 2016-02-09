package com.jfixby.red.triplane.fokker.assembler.old;

import java.io.IOException;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.RelativePath;
import com.jfixby.cmns.desktop.DesktopAssembler;
import com.jfixby.red.triplane.fokker.assembler.ds.AssemblerConfig;
import com.jfixby.red.triplane.fokker.assembler.ds.BuildInfo;
import com.jfixby.red.triplane.fokker.assembler.ds.ProjectAssemblerConfig;

public class AssembleFokker {

	private static final String SOURCE_FOLDERS_PREFIX = "sourceSets.main.java.srcDirs = ";

	public static void main(String[] args) throws Exception {

		DesktopAssembler.setup();

		BuildInfo build_info = readBuildInfo();

		String java_path = "fokker-assembler.config";
		File mp = LocalFileSystem.newFile(java_path);
		String config_data = mp.readToString();
		AssemblerConfig config = Json.deserializeFromString(AssemblerConfig.class, config_data);

		File workspace = LocalFileSystem.newFile(config.getInputWorkspace());
		File gradle_output = LocalFileSystem.newFile(config.getGradleOutputFolder());
		File android = gradle_output.child("android");
		File core = gradle_output.child("core");
		File ios = gradle_output.child("ios");
		File desktop = gradle_output.child("desktop");
		File html = gradle_output.child("html");

		File assets_output = android.child("assets");

		File assets = LocalFileSystem.newFile(config.getAssetsInput());
		File build_file = null;
		{
			RelativePath relative = JUtils.newRelativePath("red-triplane-fokker-core/red-triplane-fokker-core/com/jfixby/red/engine/core/BUILD.java");
			build_file = workspace.proceed(relative);
		}

		build_info.next();
		writeBuildInfo(build_info, build_file);
		build_info.print();

		assets_output.clearFolder();
		assets_output.getFileSystem().copyFolderContentsToFolder(assets, assets_output);

		copyProjects(workspace, config.core.listProjects(), core);
		copyProjects(workspace, config.desktop.listProjects(), desktop);
		copyProjects(workspace, config.android.listProjects(), android);
		copyProjects(workspace, config.ios.listProjects(), ios);
		copyProjects(workspace, config.html.listProjects(), html);

	}

	private static void copyProjects(File workspace, List<ProjectAssemblerConfig> projects, File core) throws IOException {
		List<String> folder_names = Collections.newList();

		for (ProjectAssemblerConfig project : projects) {
			folder_names.addAll(project.listSourceFolders());
			copyProject(workspace, project, core);

		}

		File build_gradle = core.child("build.gradle");
		String N = "\n";
		String data = build_gradle.readToString();
		List<String> lines = Collections.newList(data.split(N));
		String build_gradle_output_string = "";
		for (int i = 0; i < lines.size(); i++) {
			String line_i = lines.getElementAt(i);
			if (line_i.startsWith(SOURCE_FOLDERS_PREFIX)) {
				line_i = formGradleConfigLine(folder_names);
			}
			build_gradle_output_string = build_gradle_output_string + line_i + N;
		}
		build_gradle.writeString(build_gradle_output_string);
	}

	private static void copyProject(File workspace, ProjectAssemblerConfig project, File output_project_folder) throws IOException {
		File project_folder = workspace.child(project.getName());
		List<String> folder_names = project.listSourceFolders();
		for (String folder : folder_names) {
			File source_folder = project_folder.child(folder);
			copyFolder(source_folder, output_project_folder.child(folder));
		}

	}

	private static String formGradleConfigLine(List<String> folder_names) {
		String line = SOURCE_FOLDERS_PREFIX + "[ \"src/\"";
		for (int i = 0; i < folder_names.size(); i++) {
			line = line + ", \"" + folder_names.getElementAt(i) + "/\"";
		}
		line = line + " ]";
		return line;
	}

	private static void copyFolder(File input_folder, File output_folder) throws IOException {

		output_folder.makeFolder();
		output_folder.clearFolder();
		output_folder.getFileSystem().copyFolderContentsToFolder(input_folder, output_folder);
	}

	private static void writeBuildInfo(BuildInfo info, File build_file) throws IOException {
		AbsolutePath<FileSystem> build_info_file_path = LocalFileSystem.ApplicationHome().child(BUILD_INFO_FILE).getAbsoluteFilePath();
		File file = LocalFileSystem.newFile(build_info_file_path);
		String data = Json.serializeToString(info);
		file.writeBytes(data.getBytes());

		String template = LocalFileSystem.readFileToString(LocalFileSystem.ApplicationHome().child("BUILD.java").getAbsoluteFilePath());
		template = template.replaceAll("#BUILD_VERSION#", info.getVerstionString());

		File build_version_java_file = build_file;
		if (!build_version_java_file.exists()) {
			throw new Error(build_version_java_file + " does not exist!");
		}
		build_file.writeString(template);

	}

	private static final String BUILD_INFO_FILE = "BUILD.INFO";

	private static BuildInfo readBuildInfo() {
		BuildInfo build_info = null;

		try {
			AbsolutePath<FileSystem> build_info_file_path = LocalFileSystem.ApplicationHome().child(BUILD_INFO_FILE).getAbsoluteFilePath();
			File file = LocalFileSystem.newFile(build_info_file_path);
			String data = file.readToString();
			build_info = Json.deserializeFromString(BuildInfo.class, data);
		} catch (Exception e) {
			build_info = new BuildInfo();
		}

		return build_info;
	}

}