package managers;

import java.util.Properties;

import DataReaders.ConfigFileReader;

public class FileManager 
{
	public static void main(String arg[]) {
		System.out.println(ConfigFileReader.getInstance().getUrl());
		System.out.println(ConfigFileReader.getInstance().getEnv());
		System.out.println(ConfigFileReader.getInstance().getBrowser());
	}
}
