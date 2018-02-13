package com.gp.textfilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class Filter {
	
	public void perform() {
		String lineArgs;
		List<String> lines = new ArrayList<>();
		try (Scanner sc = new Scanner(System.in)){
			System.out.println("Enter argumets line:");
			lineArgs = sc.nextLine();
			System.out.println("Enter text lines:");
			String tmp;
			while (!(tmp = sc.nextLine()).equals("")) {
				lines.add(tmp);
			}
		}		
		filterText(lineArgs, lines);
	}

	private void filterText(String lineArgs, List<String> lines) {
		System.out.println("Answer:");
		boolean noMatches = true;
		String[] args = lineArgs.split(" ");
		for (String line: lines) {
			String[] words = line.replace(';', ' ').split(" ");
			boolean stop = false;
			for (String word: words) {
				if (stop) break;
				for (String arg: args) {
					try {
						if (word.matches(arg)) {
							System.out.println(line);
							stop = true;
							noMatches = false;
							break;
						}
					}
					catch (PatternSyntaxException ex) {
						if (word.compareTo(arg) == 0) {
							System.out.println(line);
							stop = true;
							noMatches = false;
							break;
						}						 
					}
				}
			}
		}
		if (noMatches) System.out.println("No matches found.");
	}
}
