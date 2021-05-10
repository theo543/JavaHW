package com.star.storage.oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class CommandParser{
	private final ArrayList<Command> list = new ArrayList<>();
	private final Runnable defaultCommand = () ->
			System.out.println("Type help for help");

	public CommandParser(){
		add("help", (a) -> outputCommands());
	}

	public List<Command> getCommands(){
		return Collections.unmodifiableList(list);
	}

	public void outputCommands(){
		System.out.println("Available commands:");
		for(var c : list){
			System.out.println(c.name());
		}
	}

	private List<Command> find(String name){
		List<Command> list = new ArrayList<>();
		for(Command a : this.list){
			if(a.name().startsWith(name)){
				list.add(a);
			}
		}
		return list;
	}

	public void add(Command c){
		list.add(c);
	}

	public void add(String name, Consumer<String> function){
		list.add(new Command(name, function));
	}

	public void parse(String input){
		if(input.isEmpty()){
			defaultCommand.run();
			return;
		}
		var match = Pattern.compile("^([-a-z0-9_]+)(?![^ ])").matcher(input);
		if(!match.find()){
			System.out.println("Invalid command name");
			return;
		}
		String name = match.group();
		List<Command> commands = find(name);
		if(commands.isEmpty()){
			System.out.println("Command not found");
		}else if(commands.size() > 1){
			System.out.println("Command ambiguous, matches:");
			for(var c : commands){
				System.out.println(c.name());
			}
		}else{
			commands.get(0).command().accept(match.replaceAll("").stripLeading().stripTrailing());
		}
	}

	public record Command(String name, Consumer<String> command){
	}
}
