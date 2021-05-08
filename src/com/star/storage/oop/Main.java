package com.star.storage.oop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;


public class Main{

	private static final HashMap<String, Consumer<List<String>>> commands = new HashMap<>();

	public static void registerCommand(String name, Consumer<List<String>> command){
		commands.put(name, command);
	}

	public static void main(String[] args){
		TestManager.init();
		List<String> input;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a command:");
		while(true){
			input = Arrays.asList(s.nextLine().split(" "));
			if(input.get(0).equals("exit")){
				break;
			}else if(!commands.containsKey(input.get(0))){
				System.out.println("Command not found");
			}else{
				commands.get(input.get(0)).accept(input.subList(1, input.size()));
			}
		}
	}
}
